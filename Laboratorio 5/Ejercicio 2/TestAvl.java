
import java.util.*;

public class TestAvl {
    public static void main(String[] args) {
        
    	Scanner scan = new Scanner(System.in);
        
        AVLTree<Integer> tree = new AVLTree<>();

        System.out.println("Ingreso de datos");
        
        tree.insert(100);
        tree.printTreeBalanceFactors();
        tree.insert(200);
        tree.printTreeBalanceFactors();
        tree.insert(300);
        tree.printTreeBalanceFactors();
        tree.insert(400);
        tree.printTreeBalanceFactors();
        tree.insert(500);
        tree.printTreeBalanceFactors();
        tree.insert(50);
        tree.printTreeBalanceFactors();
        tree.insert(25);
        tree.printTreeBalanceFactors();
        tree.insert(350);
        tree.printTreeBalanceFactors();
        tree.insert(375);
        tree.printTreeBalanceFactors();
        tree.insert(360);
        tree.printTreeBalanceFactors();
        tree.insert(355);
        tree.printTreeBalanceFactors();
        tree.insert(150);
        tree.printTreeBalanceFactors();
        tree.insert(175);
        tree.printTreeBalanceFactors();
        tree.insert(120);
        tree.printTreeBalanceFactors();
        tree.insert(190);
        tree.printTreeBalanceFactors();
        
        System.out.println("Raiz: " + tree.getRoot() + "\n");
        
        tree.delete(100);
        tree.printTreeBalanceFactors();
        tree.delete(200);
        tree.printTreeBalanceFactors();
        tree.delete(300);
        tree.printTreeBalanceFactors();
        tree.delete(400);
        tree.printTreeBalanceFactors();
        tree.delete(500);
        tree.printTreeBalanceFactors();
        tree.delete(50);
        tree.printTreeBalanceFactors();
        tree.delete(25);
        tree.printTreeBalanceFactors();
        tree.delete(350);
        tree.printTreeBalanceFactors();
        tree.delete(375);
        tree.printTreeBalanceFactors();
        tree.delete(360);
        tree.printTreeBalanceFactors();
        tree.delete(355);
        tree.printTreeBalanceFactors();
        tree.delete(150);
        tree.printTreeBalanceFactors();
        tree.delete(175);
        tree.printTreeBalanceFactors();
        tree.delete(120);
        tree.printTreeBalanceFactors();
        tree.delete(190);
        tree.printTreeBalanceFactors();
        
    }
}