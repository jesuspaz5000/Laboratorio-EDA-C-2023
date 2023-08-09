public class Trie<T> {
    private TrieNode<T> root;

    public Trie(T rootValue) {
        root = new TrieNode<>(rootValue);
    }

    public void insert(LinkedList<T> sequence, int docIndex) {
        TrieNode<T> current = root;
        for (int i = 0; i < sequence.getSize(); i++) {
            T item = sequence.get(i);
            TrieNode<T> child = current.getChild(item);
            if (child == null) {
                child = new TrieNode<>(item);
                current.getChildren().add(child);
            }
            current = child;
        }
        current.setEndOfWord(true);
        current.addDocumentIndex(docIndex);
    }

    public LinkedList<Integer> search(LinkedList<T> sequence) {
        TrieNode<T> current = root;
        for (int i = 0; i < sequence.getSize(); i++) {
            T item = sequence.get(i);
            TrieNode<T> child = current.getChild(item);
            if (child == null) {
                return new LinkedList<>(); // Si no se encuentra el n-grama, devolvemos una lista vacía
            }
            current = child;
        }
        return current.getDocumentIndices();
    }
    
}
