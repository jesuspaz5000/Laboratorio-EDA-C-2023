import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.Timer;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class InterfazGrafica {
    private Trie<Character> trie;
    private JTextField insertField;
    private JTextField searchField;
    private JTextField replaceField1;
    private JTextField replaceField2;
    private JTextArea outputArea;

    public InterfazGrafica() {
        trie = new Trie<>(26);
        createAndShowGui();
    }
    
    private void insertAction() {
        String text = insertField.getText();
        String[] words = text.split("\\W+"); // Divide el texto en palabras, excluyendo cualquier carácter que no sea una letra o número
        for (String word : words) {
            trie.insertar(toCharacterArray(word));
        }
        outputArea.append("Palabra insertada: " + text + "\n");
        insertField.setText("");
    }
    
    private void searchAction() {
        String word = searchField.getText();
        boolean result = trie.buscar(toCharacterArray(word));
        outputArea.append("Encontrado: " + word + " --> " + result + "\n");
        searchField.setText("");
    }
    
    private void replaceAction() {
        String wordToReplace = replaceField1.getText();
        String newWord = replaceField2.getText();
        if(trie.reemplazar(toCharacterArray(wordToReplace), toCharacterArray(newWord))) {
            outputArea.append("Reemplazo de " + wordToReplace + " por " + newWord + "\n");
        } else {
            outputArea.append("No se pudo reemplazar " + wordToReplace + " porque no se encuentra en el Trie.\n");
        }
        replaceField1.setText("");
        replaceField2.setText("");
    }

    
    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setBorder(new RoundedBorder(10));
        if(text.equals("Insertar")) {
            button.setPreferredSize(new Dimension(70, 25));
        }
        if(text.equals("Buscar")) {
            button.setPreferredSize(new Dimension(70, 25));
        }
        
        // Guarda el color original del botón
        button.setBackground(new Color(139, 205, 237));
        Color originalColor = button.getBackground();
        Color lightGray = new Color(169, 230, 230);

        // Agrega un MouseListener al botón
        button.addMouseListener(new MouseAdapter() {
            Timer timer = new Timer(10, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Color c = button.getBackground();

                    if (c.equals(lightGray)) {
                        timer.stop(); // Detiene el temporizador cuando se alcanza el color de desplazamiento
                    } else {
                        button.setBackground(new Color(
                                c.getRed() + (c.getRed() < lightGray.getRed() ? 1 : -1),
                                c.getGreen() + (c.getGreen() < lightGray.getGreen() ? 1 : -1),
                                c.getBlue() + (c.getBlue() < lightGray.getBlue() ? 1 : -1)
                        )); // Cambia gradual del color
                    }
                }
            });

            public void mouseEntered(MouseEvent me) {
                timer = new Timer(10, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Color c = button.getBackground();
                        if (c.equals(lightGray)) {
                            timer.stop(); // Detiene el temporizador cuando se alcanza el color de desplazamiento
                        } else {
                            button.setBackground(new Color(
                                adjustColorValue(c.getRed(), lightGray.getRed()),
                                adjustColorValue(c.getGreen(), lightGray.getGreen()),
                                adjustColorValue(c.getBlue(), lightGray.getBlue())
                            )); // Cambia gradual del color
                        }
                    }
                });
                timer.start(); // Iniciar el temporizador cuando se pasa el mouse
            }

            private int adjustColorValue(int currentValue, int targetValue) {
                if (currentValue < targetValue) {
                    return currentValue + 1;
                } else if (currentValue > targetValue) {
                    return currentValue - 1;
                } else {
                    return currentValue;
                }
            }


            public void mouseExited(MouseEvent me) {
                button.setBackground(originalColor); // Restaura el color original cuando el mouse sale
                timer.stop(); // Detiene el temporizador
            }
        });
        
        button.addActionListener(action);
        button.setBorderPainted(false);
        return button;
    }


    private JTextField createTextField(ActionListener action) {
        JTextField textField = new JTextField(10);
        textField.addActionListener(action);
        return textField;
    }

    private JPanel createPanel(String buttonText, ActionListener action, ActionListener textFieldAction2) {
        JTextField textField1 = createTextField(action);
        JTextField textField2 = textFieldAction2 != null ? createTextField(textFieldAction2) : null;
        JButton button = createButton(buttonText, action);

        if (buttonText.equals("Insertar")) {
            insertField = textField1;
        } else if (buttonText.equals("Buscar")) {
            searchField = textField1;
        } else if (buttonText.equals("Reemplazar")) {
            replaceField1 = textField1;
            replaceField2 = textField2;
        }

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, -60, 10));
        panel.add(new JLabel(buttonText + ":"));
        panel.add(textField1);
        if (textField2 != null) {
            panel.add(new JLabel("por"));
            panel.add(textField2);
        }
        if(buttonText.equals("Reemplazar")) {
        	button.setPreferredSize(new Dimension(100, 25));
        }
        panel.add(button);

        return panel;
    }
    
    private void fileAction() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (Scanner scanner = new Scanner(selectedFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split("\\W+");
                    for (String word : words) {
                        trie.insertar(toCharacterArray(word));
                    }
                }
                outputArea.append("Palabras insertadas del archivo: " + selectedFile.getName() + "\n");
            } catch (FileNotFoundException e) {
                outputArea.append("Error reading file: " + e.getMessage() + "\n");
            }
        }
    }
    
    private JPanel createPanelWithFileButton(String buttonText, ActionListener action) {
        JTextField textField1 = createTextField(action);
        JButton button = createButton(buttonText, action);
        JButton fileButton = createButton("Cargar archivo", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileAction();
            }
        });
        fileButton.setPreferredSize(new Dimension(110, 25));

        if (buttonText.equals("Insertar")) {
            insertField = textField1;
        }

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, -60, 10));
        panel.add(new JLabel(buttonText + ":"));
        panel.add(textField1);
        panel.add(button);
        panel.add(fileButton); // Añade el botón de cargar archivo al mismo panel

        return panel;
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("Trie GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);  // Habilita el ajuste de línea
        outputArea.setWrapStyleWord(true);  // Ajusta la línea en los límites de las palabras

        JButton showDataButton = createButton("Mostrar datos", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.append("Data: " + trie.getWords() + "\n");
            }
        });
        showDataButton.setPreferredSize(new Dimension(110, 25));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ActionListener insertAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertAction();
            }
        };

        ActionListener searchAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        };

        ActionListener replaceAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                replaceAction();
            }
        };

        // Crea el título
        JLabel title = new JLabel("Laboratorio 06: Trie");
        title.setHorizontalAlignment(JLabel.CENTER); // Centra el texto horizontalmente
        title.setFont(new Font("Verdana", Font.BOLD, 20)); // Establece la fuente y tamaño del texto
        frame.getContentPane().add(title, BorderLayout.NORTH); // Añade el título al norte del marco
        // Los parámetros son (arriba, izquierda, abajo, derecha)
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        panel.add(createPanelWithFileButton("Insertar", insertAction));
        panel.add(createPanel("Buscar", searchAction, null));
        panel.add(createPanel("Reemplazar", replaceAction, replaceAction));

        // Panel para mostrar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(showDataButton);
        buttonPanel.add(Box.createHorizontalGlue());
        panel.add(buttonPanel);
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Ajusta los bordes (márgenes) del JScrollPane
        panel.add(scrollPane);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private Character[] toCharacterArray(String str) {
        Character[] characterArray = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            characterArray[i] = str.charAt(i);
        }
        return characterArray;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica();
            }
        });
    }
}
