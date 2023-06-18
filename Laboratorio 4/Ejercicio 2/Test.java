
import com.panayotis.gnuplot.JavaPlot;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
    	Scanner scan = new Scanner(System.in);
    	
    	int n; // Número de elementos en la lista
    	System.out.println("Ingrese el tamaño del ArrayList: ");
        n = scan.nextInt();

        ArrayList<DoublyLinkedList<Integer>> casos = new ArrayList<DoublyLinkedList<Integer>>();
        String archivoInsercion = "insercion2.txt";
        PrintWriter oS = new PrintWriter(archivoInsercion);
        
        for(int i=0; i<n; i++) {
        	DoublyLinkedList<Integer> lista = new DoublyLinkedList<Integer>();
        	lista = peoresCasos(i+1);
        	casos.add(lista);
        }

        // Ordenar la lista y obtener el tiempo de ejecución
        for(int i=0; i<casos.size(); i++) {
        	long result = casos.get(i).insertionSort();
        	oS.println(String.valueOf(result));
        }
        
        oS.close();
        JavaPlot p = new JavaPlot();
        p.addPlot("\"E:/Estructuras de datos/EDAT Eclipse/LAB 4 EDA 2023/insercion2.txt\" with lines");
    	p.plot();
        
    }

    public static DoublyLinkedList<Integer> peoresCasos(int n) {
        DoublyLinkedList<Integer> lista = new DoublyLinkedList<>();

        for (int i = n; i >= 1; i--) {
            lista.insert(i);
        }

        return lista;
    }
}
