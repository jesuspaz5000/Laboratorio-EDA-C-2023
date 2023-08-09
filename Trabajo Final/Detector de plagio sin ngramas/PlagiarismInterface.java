import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class PlagiarismInterface {

    private JFrame frame;
    private JTextArea resultTextArea;
    private PlagiarismChecker checker;

    public PlagiarismInterface() {
        checker = new PlagiarismChecker();

        frame = new JFrame("Detector de Plagio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(3, 1));

        // Panel para cargar archivos
        JPanel loadFilesPanel = new JPanel();
        JButton loadFilesButton = new JButton("Cargar Archivos");
        loadFilesPanel.add(loadFilesButton);
        frame.add(loadFilesPanel);

        loadFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles();
                    String[] filePaths = new String[selectedFiles.length];
                    for (int i = 0; i < selectedFiles.length; i++) {
                        filePaths[i] = selectedFiles[i].getAbsolutePath();
                    }

                    boolean success = checker.loadFiles(filePaths);
                    if (success) {
                        resultTextArea.append("Documentos cargados exitosamente.\n");
                    } else {
                        resultTextArea.append("Error al cargar algunos documentos.\n");
                    }
                }
            }
        });

        // Panel para ingresar texto y verificar
        JPanel checkPanel = new JPanel();
        JTextField textField = new JTextField(20);
        JButton checkButton = new JButton("Verificar");
        checkPanel.add(textField);
        checkPanel.add(checkButton);
        frame.add(checkPanel);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = textField.getText();
                if (content == null || content.trim().isEmpty()) {
                    resultTextArea.append("Por favor, ingrese contenido para verificar.\n");
                    return;
                }

                // Guardar el contenido en un archivo temporal
                Path tempPath = null;
                try {
                    tempPath = Files.createTempFile("checker_", ".txt");
                    Files.write(tempPath, content.getBytes());
                } catch (IOException ioException) {
                    resultTextArea.append("Error al crear archivo temporal.\n");
                    return;
                }

                // Verificar el contenido contra la base de datos
                ResultChecker results = checker.verifyPlagiarism(tempPath.toString());

                // Mostrar los resultados
                resultTextArea.append("Resultados de detección de plagio:\n");
                for (int i = 0; i < results.getResultSize(); i++) {
                    resultTextArea.append("Documento " + (i + 1) + ": " + results.resultPlagio(i));
                }

                // Borrar el archivo temporal
                try {
                    Files.delete(tempPath);
                } catch (IOException ioException) {
                    resultTextArea.append("Error al borrar archivo temporal.\n");
                }
            }
        });

        // Panel para mostrar resultados
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlagiarismInterface();
            }
        });
    }
}
