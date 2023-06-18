
public class DoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Constructor de la lista enlazada
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Retorna el tamaño de la lista
    public int size() {
        return size;
    }

    // Verifica si la lista está vacía
    public boolean isEmpty() {
        return size == 0;
    }

    // Inserta un nuevo elemento al final de la lista
    public void insert(E data) {
        Node<E> newNode = new Node<>(data);

        if (isEmpty()) {
            // Si la lista está vacía, el nuevo nodo se convierte en el head y el tail
            head = newNode;
            tail = newNode;
        } else {
            // Ajusta los enlaces para agregar el nuevo nodo después del tail
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }

    // Ordena la lista mediante el algoritmo de inserción y retorna el tiempo de ejecución en nanosegundos
    public long insertionSort() {
        if (isEmpty() || size == 1) {
            return 0;
        }

        long startTime = System.nanoTime();

        Node<E> current = head.getNext();

        while (current != null) {
            E key = current.getData();
            Node<E> prevNode = current.getPrevious();

            while (prevNode != null && prevNode.getData().toString().compareTo(key.toString()) > 0) {
                // Desplaza el valor del nodo anterior al siguiente nodo
                prevNode.getNext().setData(prevNode.getData());
                prevNode = prevNode.getPrevious();
            }

            if (prevNode == null) {
                // Coloca el valor actual en el head
                head.setData(key);
            } else {
                // Coloca el valor actual después del nodo anterior
                prevNode.getNext().setData(key);
            }

            current = current.getNext();
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        return executionTime;
    }

    // Imprime los elementos de la lista
    public void imprimir() {
        Node<E> current = head;

        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }

        System.out.println();
    }
}
