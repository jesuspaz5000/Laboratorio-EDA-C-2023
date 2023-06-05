import java.util.NoSuchElementException;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>(5); // Crear una cola de capacidad 5

        System.out.println("isEmpty(): " + queue.isEmpty()); // La cola está vacía

        queue.add(1); // Agregar elementos a la cola
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("isEmpty(): " + queue.isEmpty()); // La cola ya no está vacía
        System.out.println("getSize(): " + queue.getSize()); // Tamaño actual de la cola

        try {
            queue.add(6); // Intentar agregar un elemento cuando la cola está llena, debería lanzar una excepción
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("peek(): " + queue.peek()); // Elemento del frente de la cola sin eliminarlo
        System.out.println("poll(): " + queue.poll()); // Eliminar y devolver el elemento del frente de la cola

        System.out.println("getSize(): " + queue.getSize()); // Tamaño actual de la cola después de eliminar un elemento

        System.out.println("element(): " + queue.element()); // Elemento del frente de la cola sin eliminarlo

        queue.offer(6); // Agregar un elemento a la cola

        System.out.println("poll(): " + queue.poll()); // Eliminar y devolver el elemento del frente de la cola

        while (!queue.isEmpty()) {
            System.out.println("remove(): " + queue.remove()); // Vaciar la cola eliminando todos los elementos
        }

        try {
            System.out.println("peek(): " + queue.peek()); // Intentar obtener el elemento del frente de una cola vacía, debería lanzar una excepción
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("getSize(): " + queue.getSize()); // Tamaño actual de la cola vacía

        try {
            System.out.println("remove(): " + queue.remove()); // Intentar eliminar un elemento de una cola vacía, debería lanzar una excepción
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
