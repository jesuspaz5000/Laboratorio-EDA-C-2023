public class Node<T> {
    private Node<T>[] hijos;
    private boolean esFinDePalabra;
    
    @SuppressWarnings("unused")
    private int numKeys;

    // Constructor
    public Node(int numKeys) {
        this.numKeys = numKeys;
        hijos = new Node[numKeys];
        esFinDePalabra = false;
    }

    public Node<T>[] getHijos() {
        return hijos;
    }

    public boolean isEsFinDePalabra() {
        return esFinDePalabra;
    }

    public void setEsFinDePalabra(boolean esFinDePalabra) {
        this.esFinDePalabra = esFinDePalabra;
    }

    public Node<T> getHijo(T key) {
        int index = convertKeyToIndex(key);
        return hijos[index];
    }

    public void setHijo(T key, Node<T> nodo) {
        int index = convertKeyToIndex(key);
        hijos[index] = nodo;
    }

    private int convertKeyToIndex(T key) {
        // Implementacion de la lógica para convertir la clave en un índice.
        return ((Character) key) - 'a';
    }
}
