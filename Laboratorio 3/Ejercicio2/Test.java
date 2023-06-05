public class Test {
    public static void main(String[] args) {
        // Crear una instancia de Stack
        Stack<Integer> stack = new Stack<>();

        // Verificar si la pila está vacía
        System.out.println("¿La pila está vacía?: " + stack.isEmpty());

        // Agregar elementos a la pila
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Obtener el tamaño de la pila
        System.out.println("Tamaño de la pila: " + stack.getSize());

        // Ver el elemento superior de la pila sin eliminarlo
        System.out.println("Elemento superior de la pila: " + stack.peek());

        // Buscar un elemento en la pila
        int elementToSearch = 20;
        int position = stack.search(elementToSearch);
        if (position != -1) {
            System.out.println("El elemento " + elementToSearch + " se encuentra en la posición " + position);
        } else {
            System.out.println("El elemento " + elementToSearch + " no se encuentra en la pila");
        }

        // Eliminar elementos de la pila
        int poppedElement = stack.pop();
        System.out.println("Elemento eliminado de la pila: " + poppedElement);

        // Verificar el tamaño de la pila después de la eliminación
        System.out.println("Tamaño de la pila después de eliminar un elemento: " + stack.getSize());
    }
}
