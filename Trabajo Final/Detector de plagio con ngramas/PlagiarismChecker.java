import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlagiarismChecker {
    // Base de datos usando un Trie
    private Trie<String> database;
    // Campo para mantener un registro del número total de archivos en la base de datos
    private int totalFiles;

    public PlagiarismChecker() {
        this.database = new Trie<>("\0");
        this.totalFiles = 0;  // Inicializamos a 0 al principio
    }

    public boolean loadFiles(String[] paths) {
        totalFiles = paths.length;  // Establecer el número total de archivos
        int nGramSize = 3;  // Definir el tamaño de los n-gramas, en este caso trigramas
        for (int docIndex = 0; docIndex < paths.length; docIndex++) {
            String path = paths[docIndex];
            try {
                String content = readFile(path);
                content = preprocessContent(content);
                LinkedList<String> nGrams = splitIntoNGrams(content, nGramSize);
                for (int i = 0; i < nGrams.getSize(); i++) {
                    LinkedList<String> stringList = stringToLinkedListOfString(nGrams.get(i));
                    database.insert(stringList, docIndex);  // Insertar cada n-grama convertido en el Trie con el índice del documento
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;  // Retorna false si hay algún error durante la lectura.
            }
        }
        return true;  // Retorna true si todos los archivos se cargaron correctamente.
    }

    public ResultChecker verifyPlagiarism(String path) {
        ResultChecker result = new ResultChecker(totalFiles);  // Usamos el campo totalFiles aquí
        int nGramSize = 3;  // Definir el tamaño de los n-gramas, en este caso trigramas
        try {
            String content = readFile(path);
            content = preprocessContent(content);
            LinkedList<String> nGrams = splitIntoNGrams(content, nGramSize);
            for (int i = 0; i < nGrams.getSize(); i++) {
                LinkedList<String> stringList = stringToLinkedListOfString(nGrams.get(i));
                LinkedList<Integer> matchedDocuments = database.search(stringList);  // Buscar cada n-grama convertido en el Trie
                for (int j = 0; j < matchedDocuments.getSize(); j++) {
                    int docIndex = matchedDocuments.get(j);
                    result.setResultAt(docIndex, true); // Marcamos el documento como plagio
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;  // Retorna null si hay algún error durante la lectura.
        }
        return result;  // Retorna los resultados del sistema de detección de plagio.
    }

    // Método para convertir una String en LinkedList<Character>
    private LinkedList<String> stringToLinkedListOfString(String word) {
        LinkedList<String> strList = new LinkedList<>();
        for (char c : word.toCharArray()) {
            strList.add(String.valueOf(c)); // Convierte el caracter a String
        }
        return strList;
    }

    private String readFile(String path) throws IOException {
        String content = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                content += line + "\n";
            }
        }
        return content;
    }

    
    private LinkedList<String> splitIntoNGrams(String content, int n) {
        String[] wordsArray = content.split("\\W+");
        LinkedList<String> nGrams = new LinkedList<>();

        for (int i = 0; i < wordsArray.length; i++) {
            String nGram = "";
            for (int j = i; j < i + n && j < wordsArray.length; j++) {
                nGram += wordsArray[j].toLowerCase() + " ";
            }
            nGram = nGram.substring(0, nGram.length() - 1);  // Removemos el último espacio
            nGrams.add(nGram);
            if (i + n >= wordsArray.length) {  // Si alcanzamos o pasamos el final del array, salimos del bucle
                break;
            }
        }

        return nGrams;
    }

    private String preprocessContent(String content) {
        // Convierte todo a minúsculas
        content = content.toLowerCase();

        // Elimina tildes y diacríticos
        content = content.replace('á', 'a').replace('é', 'e').replace('í', 'i').replace('ó', 'o').replace('ú', 'u');

        // Elimina las comas, puntos y otros caracteres especiales (puedes agregar más según lo necesites)
        content = content.replaceAll("[,.;:?!]", "");

        return content;
    }


}
