
import java.util.*;

public class TestAvl {
    public static void main(String[] args) {
    	
    	System.setProperty("org.graphstream.ui", "swing");
        
    	Scanner scan = new Scanner(System.in);
    	
    	String letras;
        
        AVLTree<Character> tree = new AVLTree<Character>();

        System.out.println("Input: ");
        letras = scan.next();
        
        for(int i=0; i<letras.length(); i++) {
        	tree.insert(letras.charAt(i));
        }
        tree.outputConsola();
        tree.output();
        
        System.out.println("Raiz: " + tree.getRoot() + "\n");
        
        System.out.println("Minimo: " + tree.getMin());
        System.out.println("Máximo: " + tree.getMax());
        
        System.out.println("Predecesor de: " + tree.getRoot() + " es " + tree.getPredecessor(tree.getRoot()));
        System.out.println("Sucesor de: " + tree.getRoot() + " es " + tree.getSuccessor(tree.getRoot()));
        
        System.out.println("Elemento a eliminar: ");
        char eliminar = scan.next().charAt(0);
        
        tree.delete(eliminar);
        
        tree.outputConsola();
        tree.output();
        
    }
}