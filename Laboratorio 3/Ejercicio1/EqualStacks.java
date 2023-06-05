
public class EqualStacks {
    public static int equalStacks(int[] h1, int[] h2, int[] h3) {
        // Calcula la suma inicial de cada pila
        int sum1 = getSum(h1);
        int sum2 = getSum(h2);
        int sum3 = getSum(h3);
        
        // Inicializa las posiciones superiores de cada pila
        int top1 = 0;
        int top2 = 0;
        int top3 = 0;
        
        // Itera hasta que las pilas tengan la misma altura
        while (!areStacksEqual(sum1, sum2, sum3)) {
            // Reduce la altura de la pila con la suma más grande
            if (sum1 > sum2 || sum1 > sum3) {
                sum1 -= h1[top1++];
            } else if (sum2 > sum1 || sum2 > sum3) {
                sum2 -= h2[top2++];
            } else {
                sum3 -= h3[top3++];
            }
        }

        // Devuelve la altura igual resultante
        return sum1;
    }

    private static int getSum(int[] heights) {
        // Calcula la suma de los elementos en una pila
        int sum = 0;
        for (int height : heights) {
            sum += height;
        }
        return sum;
    }

    private static boolean areStacksEqual(int sum1, int sum2, int sum3) {
        // Verifica si las sumas de las pilas son iguales
        return sum1 == sum2 && sum1 == sum3;
    }

    public static void main(String[] args) {
        // Pilas de ejemplo
        int[] h1 = {1, 1, 1, 2, 3};
        int[] h2 = {2, 3, 4};
        int[] h3 = {1, 4, 1, 1};

        // Calcula la altura igual y la imprime
        int equalHeight = equalStacks(h1, h2, h3);
        System.out.println("Equal height: " + equalHeight);
    }
}
