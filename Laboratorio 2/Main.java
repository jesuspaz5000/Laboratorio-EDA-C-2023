import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Crear una lista y agregar elementos
        MyList<String> myList = new MyList<>();
        myList.add("A");
        myList.add("B");
        myList.add("C");

        // Imprimir la lista
        System.out.println("Lista original: " + myList);

        // Agregar elementos desde una colección
        myList.addAll(Arrays.asList("D", "E", "F"));
        System.out.println("Lista después de addAll: " + myList);

        // Insertar elementos en una posición específica
        myList.add(2, "G");
        System.out.println("Lista después de add en posición 2: " + myList);

        // Remover un elemento
        myList.remove("B");
        System.out.println("Lista después de remove 'B': " + myList);

        // Verificar si la lista contiene un elemento
        System.out.println("La lista contiene 'C': " + myList.contains("C"));

        // Verificar si la lista contiene todos los elementos de una colección
        System.out.println("La lista contiene todos los elementos: " + myList.containsAll(Arrays.asList("A", "C")));

        // Obtener un elemento por índice
        System.out.println("Elemento en índice 3: " + myList.get(3));

        // Obtener el índice de un elemento
        System.out.println("Índice de 'C': " + myList.indexOf("C"));

        // Obtener el último índice de un elemento
        System.out.println("Último índice de 'C': " + myList.lastIndexOf("C"));

        // Reemplazar un elemento en una posición específica
        myList.set(1, "H");
        System.out.println("Lista después de set en posición 1: " + myList);

        // Obtener una sublista
        MyList<String> sublist = myList.subList(1, 4);
        System.out.println("Sublista: " + sublist);

        // Eliminar todos los elementos que están en una colección
        myList.removeAll(Arrays.asList("A", "D", "F"));
        System.out.println("Lista después de removeAll: " + myList);

        // Limpiar la lista
        myList.clear();
        System.out.println("Lista después de clear: " + myList);

        // Verificar si la lista está vacía
        System.out.println("La lista está vacía: " + myList.isEmpty());
    }
}
