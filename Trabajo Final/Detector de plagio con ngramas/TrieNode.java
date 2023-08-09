public class TrieNode<T> {
    private T data;
    private LinkedList<TrieNode<T>> children;
    private boolean isEndOfWord;
    private LinkedList<Integer> documentIndices; // Lista de índices de documentos

    public TrieNode(T data) {
        this.data = data;
        this.children = new LinkedList<>();
        this.isEndOfWord = false;
        this.documentIndices = new LinkedList<>(); // Inicializamos la lista
    }

    public T getData() {
        return data;
    }

    public LinkedList<TrieNode<T>> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public TrieNode<T> getChild(T data) {
        for (int i = 0; i < children.getSize(); i++) {
            if (children.get(i).getData().equals(data)) {
                return children.get(i);
            }
        }
        return null;
    }

    public void addDocumentIndex(int docIndex) {
        documentIndices.add(docIndex);
    }

    public LinkedList<Integer> getDocumentIndices() {
        return documentIndices;
    }
}
