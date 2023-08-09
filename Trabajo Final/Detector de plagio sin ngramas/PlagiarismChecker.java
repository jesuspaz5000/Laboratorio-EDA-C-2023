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
        totalFiles = paths.length;
        for (int docIndex = 0; docIndex < paths.length; docIndex++) {
            String path = paths[docIndex];
            try {
                String content = readFile(path);
                content = preprocessContent(content);
                LinkedList<String> words = splitIntoWords(content);
                for (int i = 0; i < words.getSize(); i++) {
                    LinkedList<String> wordList = stringToLinkedListOfString(words.get(i));
                    database.insert(wordList, docIndex);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public ResultChecker verifyPlagiarism(String path) {
        ResultChecker result = new ResultChecker(totalFiles);
        try {
            String content = readFile(path);
            content = preprocessContent(content);
            LinkedList<String> wordsToCheck = splitIntoWords(content);

            for (int docIndex = 0; docIndex < totalFiles; docIndex++) {
                int matchedWordCount = 0;
                for (int i = 0; i < wordsToCheck.getSize(); i++) {
                    LinkedList<String> wordList = stringToLinkedListOfString(wordsToCheck.get(i));
                    LinkedList<Integer> matchedDocuments = database.search(wordList);
                    if (matchedDocuments.contains(docIndex)) {
                        matchedWordCount++;
                    }
                }
                double matchPercentage = (double) matchedWordCount / wordsToCheck.getSize();
                if (matchPercentage >= 0.8) {  // Si el 80% o más de las palabras coinciden
                    result.setResultAt(docIndex, true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }




    // Método para convertir una String en LinkedList<Character>
    private LinkedList<String> stringToLinkedListOfString(String word) {
        LinkedList<String> strList = new LinkedList<>();
        for (char c : word.toCharArray()) {
            strList.add(String.valueOf(c)); // Convierte el caracter a String
        }
        return strList;
    }

    private LinkedList<String> splitIntoWords(String content) {
        String[] wordsArray = content.split("\\W+");
        LinkedList<String> words = new LinkedList<>();
        for (String word : wordsArray) {
            words.add(word);
        }
        return words;
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

    private String preprocessContent(String content) {
        // Convierte todo a minúsculas
        content = content.toLowerCase();

        // Elimina tildes y diacríticos
        content = content.replace('á', 'a').replace('é', 'e').replace('í', 'i').replace('ó', 'o').replace('ú', 'u');

        // Elimina las comas, puntos y otros caracteres especiales (puedes agregar más según lo necesites)
        content = content.replaceAll("[,.;:?!]", " "); // Reemplaza por espacios para evitar palabras concatenadas

        return content.trim(); // Elimina espacios extra al principio y al final
    }

}
