
public class Stack<T> {
   private Node<T> top;  // Nodo superior de la pila
   private int size;     // Tamaño de la pila

   public Stack() {
       top = null;   // Inicializa el nodo superior como nulo
       size = 0;     // Inicializa el tamaño de la pila como cero
   }

   // Verifica si la pila está vacía
   public boolean isEmpty() {
       return size == 0;  // Devuelve true si el tamaño es cero, indicando que la pila está vacía
   }

   // Devuelve el tamaño actual de la pila
   public int getSize() {
       return size;
   }

   // Agrega un elemento a la pila
   public void push(T data) {
       Node<T> newNode = new Node<>(data);  // Crea un nuevo nodo con los datos proporcionados
       newNode.setNext(top);                // Establece el siguiente del nuevo nodo como el nodo superior actual
       top = newNode;                       // Actualiza el nodo superior con el nuevo nodo
       size++;                              // Incrementa el tamaño de la pila
   }

   // Elimina y devuelve el elemento superior de la pila
   public T pop() {
       if (isEmpty()) {
           throw new IllegalStateException("Stack is empty");  // Lanza una excepción si la pila está vacía y no se puede eliminar ningún elemento
       }
       T data = top.getData();   // Obtiene los datos del nodo superior
       top = top.getNext();      // Actualiza el nodo superior al siguiente nodo en la pila
       size--;                   // Reduce el tamaño de la pila
       return data;              // Devuelve los datos del nodo superior eliminado
   }

   // Devuelve el elemento superior de la pila sin eliminarlo
   public T peek() {
       if (isEmpty()) {
           throw new IllegalStateException("Stack is empty");  // Lanza una excepción si la pila está vacía y no se puede obtener ningún elemento
       }
       return top.getData();     // Devuelve los datos del nodo superior sin eliminarlo
   }

   // Busca un elemento en la pila y devuelve su posición (1-based)
   public int search(T element) {
      Node<T> currentNode = top;  // Inicia el nodo actual en el nodo superior
      int position = 1;           // Inicia la posición en 1

      while (currentNode != null) {
          if (currentNode.getData().equals(element)) {
              return position;    // Devuelve la posición si encuentra el elemento en el nodo actual
          }
          currentNode = currentNode.getNext();  // Avanza al siguiente nodo en la pila
          position++;                           // Incrementa la posición
      }

      return -1;  // Devuelve -1 si el elemento no se encuentra en la pila
  }
}
