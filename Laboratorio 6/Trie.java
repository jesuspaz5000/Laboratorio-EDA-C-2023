public class Trie<T> {
    private Node<T> raiz;
    private final int numKeys;

    // Constructor
    public Trie(int numKeys) {
        this.numKeys = numKeys;
        raiz = new Node<>(numKeys);
    }

    // Funcion para insertar una palabra en el Trie
    public void insertar(T[] palabra) {
        Node<T> nodo = raiz;
        for (T key : palabra) {
            int index = convertKeyToIndex(key); 
            if (nodo.getHijos()[index] == null) {
                nodo.getHijos()[index] = new Node<>(numKeys);
            }
            nodo = nodo.getHijos()[index];
        }
        nodo.setEsFinDePalabra(true);
    }

    // Funcion para buscar una palabra en el Trie
    public boolean buscar(T[] palabra) {
        Node<T> nodo = raiz;
        for (T key : palabra) {
            int index = convertKeyToIndex(key); 
            if (nodo.getHijos()[index] == null) {
                return false;
            }
            nodo = nodo.getHijos()[index];
        }
        return nodo.isEsFinDePalabra();
    }

    public boolean reemplazar(T[] palabraOriginal, T[] nuevaPalabra) {
        // Primero intenta eliminar la palabra original
        if (eliminar(palabraOriginal)) {
            // Si la eliminación fue exitosa, inserta la nueva palabra
            insertar(nuevaPalabra);
            return true;
        } else {
            // Si la eliminación no fue exitosa, no hagas nada y devuelve false
            return false;
        }
    }

    
    public boolean eliminar(T[] palabra) {
        return eliminar(raiz, palabra, 0) != null;
    }

    private Node<T> eliminar(Node<T> nodoActual, T[] palabra, int index) {
        if (index == palabra.length) {
            // Cuando la última letra de la palabra ha sido procesada,
            // marca el nodoActual como no fin de palabra y comprueba si tiene algún hijo
            if (!nodoActual.isEsFinDePalabra()) {
                return null;
            }
            nodoActual.setEsFinDePalabra(false);
            return nodoActual.getHijos().length == 0 ? null : nodoActual;
        }

        int charIndex = convertKeyToIndex(palabra[index]);
        Node<T> nodo = nodoActual.getHijos()[charIndex];
        if (nodo == null) {
            return null;
        }

        Node<T> result = eliminar(nodo, palabra, index + 1);

        // Si el hijo debe ser eliminado
        if (result == null) {
            nodoActual.getHijos()[charIndex] = null;
            return nodoActual.getHijos().length == 0 && !nodoActual.isEsFinDePalabra() ? null : nodoActual;
        }
        return nodoActual;
    }
    
    private int convertKeyToIndex(T key) {
        // Implementacion de la lógica para convertir la clave en un índice.
    	return Character.toLowerCase((Character) key) - 'a';
    }
    
    public String getWords() {
        StringBuilder words = new StringBuilder();
        getWordsHelper(raiz, "", words);
        return words.toString();
    }

    private void getWordsHelper(Node<T> node, String wordSoFar, StringBuilder words) {
        if (node.isEsFinDePalabra()) {
            words.append(wordSoFar).append(" ");  // Agrega un espacio como delimitador
        }
        for (int i = 0; i < numKeys; i++) {
            Node<T> child = node.getHijos()[i];
            if (child != null) {
                char c = (char) (i + 'a');  // Convertimos el índice de nuevo a un carácter
                getWordsHelper(child, wordSoFar + c, words);
            }
        }
    }

}
