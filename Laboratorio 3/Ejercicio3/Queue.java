import java.util.NoSuchElementException;

public class Queue<T> {
    private Node<T> front; // Referencia al frente de la cola
    private Node<T> rear; // Referencia al final de la cola
    private int size; // Tamaño actual de la cola
    private int capacity; // Capacidad máxima de la cola

    public Queue(int capacity) {
        front = null; // Inicializa el frente como null
        rear = null; // Inicializa el final como null
        size = 0; // Inicializa el tamaño en 0
        this.capacity = capacity; // Asigna la capacidad proporcionada
    }

    // Verifica si la cola está vacía
    public boolean isEmpty() {
        return size == 0; // Devuelve true si el tamaño es 0, indicando que la cola está vacía
    }

    // Devuelve el tamaño actual de la cola
    public int getSize() {
        return size;
    }

    // Verifica si la cola está llena
    public boolean isFull() {
        return size == capacity; // Devuelve true si el tamaño es igual a la capacidad, indicando que la cola está llena
    }

    // Agrega un elemento a la cola
    public void enqueue(T data) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full"); // Lanza una excepción si la cola está llena y no se puede agregar más elementos
        }

        Node<T> newNode = new Node<>(data); // Crea un nuevo nodo con el dato proporcionado
        if (isEmpty()) {
            front = newNode; // Si la cola está vacía, el nuevo nodo es tanto el frente como el final
        } else {
            rear.setNext(newNode); // Si la cola no está vacía, establece el siguiente del final como el nuevo nodo
        }
        rear = newNode; // El nuevo nodo se convierte en el nuevo final de la cola
        size++; // Incrementa el tamaño de la cola
    }

    // Elimina y devuelve el elemento del frente de la cola
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty"); // Lanza una excepción si la cola está vacía y no se puede eliminar ningún elemento
        }
        T data = front.getData(); // Obtiene el dato del frente de la cola
        front = front.getNext(); // El siguiente del frente se convierte en el nuevo frente
        if (front == null) {
            rear = null; // Si el frente es null, la cola está vacía, por lo que el final también debe ser null
        }
        size--; // Reduce el tamaño de la cola
        return data; // Devuelve el dato eliminado
    }

    // Devuelve el elemento del frente de la cola sin eliminarlo
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty"); // Lanza una excepción si la cola está vacía y no se puede obtener ningún elemento
        }
        return front.getData(); // Devuelve el dato del frente de la cola sin eliminarlo
    }

    // Agrega un elemento a la cola. Si la cola está llena, lanza una excepción.
    public boolean add(T element) {
        if (isFull()) {
            throw new IllegalStateException("No space available. Queue is full."); // Lanza una excepción si la cola está llena y no se puede agregar más elementos
        }
    
        enqueue(element); // Agrega el elemento a la cola utilizando el método enqueue
        return true; // Devuelve true para indicar que el elemento se agregó correctamente
    }

    // Devuelve el elemento del frente de la cola sin eliminarlo. Si la cola está vacía, lanza una excepción.
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty"); // Lanza una excepción si la cola está vacía y no se puede obtener ningún elemento
        }
        return front.getData(); // Devuelve el dato del frente de la cola sin eliminarlo
    }
    
    // Agrega un elemento a la cola. Si la cola está llena, devuelve false.
    public boolean offer(T data) {
        Node<T> newNode = new Node<>(data); // Crea un nuevo nodo con el dato proporcionado
        if (isEmpty()) {
            front = newNode; // Si la cola está vacía, el nuevo nodo es tanto el frente como el final
        } else {
            rear.setNext(newNode); // Si la cola no está vacía, establece el siguiente del final como el nuevo nodo
        }
        rear = newNode; // El nuevo nodo se convierte en el nuevo final de la cola
        size++; // Incrementa el tamaño de la cola
        return true; // Devuelve true para indicar que el elemento se agregó correctamente
    }
    
    // Elimina y devuelve el elemento del frente de la cola. Si la cola está vacía, devuelve null.
    public T poll() {
        if (isEmpty()) {
            return null; // Devuelve null si la cola está vacía y no se puede eliminar ningún elemento
        }
        T data = front.getData(); // Obtiene el dato del frente de la cola
        front = front.getNext(); // El siguiente del frente se convierte en el nuevo frente
        if (front == null) {
            rear = null; // Si el frente es null, la cola está vacía, por lo que el final también debe ser null
        }
        size--; // Reduce el tamaño de la cola
        return data; // Devuelve el dato eliminado
    }

    // Elimina y devuelve el elemento del frente de la cola. Si la cola está vacía, lanza una excepción.
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty"); // Lanza una excepción si la cola está vacía y no se puede eliminar ningún elemento
        }
        T data = front.getData(); // Obtiene el dato del frente de la cola
        front = front.getNext(); // El siguiente del frente se convierte en el nuevo frente
        if (front == null) {
            rear = null; // Si el frente es null, la cola está vacía, por lo que el final también debe ser null
        }
        size--; // Reduce el tamaño de la cola
        return data; // Devuelve el dato eliminado
    }    
}
