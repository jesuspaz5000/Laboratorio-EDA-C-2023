public class LinkedList<T extends Comparable<T>> {
    private Node<T> head;

    // Agrega un elemento a la lista
    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) { // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
            head = newNode;
        } else {
            Node<T> current = head;

            // Recorre la lista hasta llegar al último nodo
            while (current.getNext() != null) {
                current = current.getNext();
            }

            // Inserta el nuevo nodo al final de la lista
            current.setNext(newNode);
        }
    }

    // Elimina un elemento de la lista
    public void remove(T data) {
        if (head == null) { // Si la lista está vacía, no hay nada que eliminar
            return;
        }

        if (head.getData().equals(data)) { // Si el elemento a eliminar es la cabeza, se actualiza la cabeza
            head = head.getNext();
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;

        // Recorre la lista hasta encontrar el nodo a eliminar
        while (current != null && !current.getData().equals(data)) {
            previous = current;
            current = current.getNext();
        }

        // Si se encuentra el nodo, se elimina enlazando el nodo anterior con el siguiente nodo
        if (current != null) {
            previous.setNext(current.getNext());
        }
    }

    // Ordena la lista mediante el algoritmo de inserción
    public long insertionSort() {
        long startTime = System.nanoTime();
        if (head == null || head.getNext() == null) { // Si la lista está vacía o tiene un solo elemento, no es necesario ordenar
            long endTime = System.nanoTime();
            return endTime - startTime;
        }

        Node<T> sorted = null;
        Node<T> current = head;

        // Recorre la lista original y va insertando los nodos en la lista ordenada
        while (current != null) {
            Node<T> next = current.getNext();
            sorted = sortedInsert(sorted, current);
            current = next;
        }

        head = sorted; // Actualiza la cabeza con la lista ordenada
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Inserta un nodo en orden en una lista ordenada
    private Node<T> sortedInsert(Node<T> head, Node<T> newNode) {
        if (head == null || newNode.getData().compareTo(head.getData()) < 0) {
            newNode.setNext(head); // El nuevo nodo se convierte en la nueva cabeza
            return newNode;
        }

        Node<T> current = head;

        // Recorre la lista ordenada hasta encontrar el lugar adecuado para insertar el nuevo nodo
        while (current.getNext() != null && newNode.getData().compareTo(current.getNext().getData()) > 0) {
            current = current.getNext();
        }

        // Inserta el nuevo nodo en la posición correcta
        newNode.setNext(current.getNext());
        current.setNext(newNode);

        return head;
    }

    // Imprime los elementos de la lista
    public void imprimir() {
        Node<T> current = head;

        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }

        System.out.println();
    }

    // Verifica si la lista está vacía
    public boolean isEmpty() {
        return head == null;
    }

    // Retorna el tamaño de la lista
    public int size() {
        int count = 0;
        Node<T> current = head;

        // Recorre la lista contando los nodos
        while (current != null) {
            count++;
            current = current.getNext();
        }

        return count;
    }
}
