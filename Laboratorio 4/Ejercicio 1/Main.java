import java.io.FileNotFoundException;
import java.util.*;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int size;
        System.out.println("Ingrese el tamaño del ArrayList: ");
        size = scan.nextInt();

        ArrayList<LinkedList<Integer>> casos = new ArrayList<LinkedList<Integer>>(); // ArrayList que almacenará las LinkedList
        String archivoInsercion = "insercion.txt"; // Nombre del archivo de salida
        PrintWriter oS = new PrintWriter(archivoInsercion); // Objeto PrintWriter para escribir en el archivo

        for(int i=0; i<size; i++){
            LinkedList<Integer> list = new LinkedList<>(); // Crea una nueva LinkedList para cada caso
            int[] peorCaso = generarPeorCaso(i + 1); // Genera el peor caso para el tamaño actual
            for (int j = 0; j < peorCaso.length; j++) {
                list.add(peorCaso[j]); // Agrega los elementos del peor caso a la LinkedList
            }
            casos.add(list); // Agrega la LinkedList al ArrayList de casos
        }

        Iterator<LinkedList<Integer>> puntero = casos.iterator(); // Iterador para recorrer las LinkedList del ArrayList
        while(puntero.hasNext()){
            LinkedList<Integer> list = puntero.next();
    	    long result = list.insertionSort(); // Aplica el algoritmo de ordenamiento a la LinkedList actual
            oS.println(String.valueOf(result)); // Escribe el resultado en el archivo
        }

        oS.close(); // Cierra el archivo de salida

    }

    public static int[] generarPeorCaso(int t) {
        int[] lista= new int[t];
        for (int i=0; i<t; i++) {
            lista[i] = t-i; // Genera un peor caso ordenando los elementos en orden descendente
        }
        return lista;		
    }
}
