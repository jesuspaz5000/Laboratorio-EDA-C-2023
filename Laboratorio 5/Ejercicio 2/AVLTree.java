
public class AVLTree<T extends Comparable<T>> {
    private Node<T> root;
    
    public T getRoot() {
    	return root.getKey();
    }

    // Método para obtener la altura del árbol
    private int height(Node<T> N) {
        if (N == null)
            return 0;
        return N.getHeight();
    }

    // Método para obtener el balance del nodo N
    private int getBalance(Node<T> N) {
        if (N == null)
            return 0;
        return height(N.getLeft()) - height(N.getRight());
    }

    // Método para rotar a la derecha
    private Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.getLeft();
        Node<T> T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        return x;
    }

    // Método para rotar a la izquierda
    private Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.getRight();
        Node<T> T2 = y.getLeft();
        y.setLeft(x);
        x.setRight(T2);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
        return y;
    }

    // Método para insertar un valor
    public void insert(T key) {
        root = insert(root, key);
    }

    // Método auxiliar para la inserción
    private Node<T> insert(Node<T> node, T key) {
        // 1. Realiza la inserción normal de BST
        if (node == null) {
            return (new Node<T>(key));
        }

        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(insert(node.getLeft(), key));
        else if (key.compareTo(node.getKey()) > 0)
            node.setRight(insert(node.getRight(), key));
        else // Los datos duplicados no son permitidos en el BST
            return node;

        // 2. Actualiza la altura de este nodo antecesor
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        // 3. Obtén el factor de balance para comprobar si este nodo se volvió no balanceado
        int balance = getBalance(node);

        // Si este nodo se vuelve no balanceado, hay 4 casos a considerar

        // Caso Izquierda Izquierda
        if (balance > 1 && key.compareTo(node.getLeft().getKey()) < 0)
            return rightRotate(node);

        // Caso Derecha Derecha
        if (balance < -1 && key.compareTo(node.getRight().getKey()) > 0)
            return leftRotate(node);

        // Caso Izquierda Derecha
        if (balance > 1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Caso Derecha Izquierda
        if (balance < -1 && key.compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        // Retorna el nodo apuntado
        return node;
    }

    public void delete(T key) {
        root = deleteNode(root, key);
    }

    private Node<T> deleteNode(Node<T> root, T key) {
        if (root == null) {
            return root;
        }

        if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(deleteNode(root.getLeft(), key));
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRight(deleteNode(root.getRight(), key));
        } else {
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                Node<T> temp = null;
                if (temp == root.getLeft()) {
                    temp = root.getRight();
                } else {
                    temp = root.getLeft();
                }

                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node<T> temp = minValueNode(root.getRight());

                root.setKey(temp.getKey());

                root.setRight(deleteNode(root.getRight(), temp.getKey()));
            }
        }

        if (root == null) {
            return root;
        }

        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.getLeft()) >= 0) {
            return rightRotate(root);
        }

        if (balance > 1 && getBalance(root.getLeft()) < 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.getRight()) <= 0) {
            return leftRotate(root);
        }

        if (balance < -1 && getBalance(root.getRight()) > 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root;
    }

    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }
    
    public void printTreeBalanceFactors() {
        printNodeBalanceFactors(root);
        System.out.println();
    }

    private void printNodeBalanceFactors(Node<T> node) {
        if (node != null) {
            printNodeBalanceFactors(node.getLeft()); // Recorre el subárbol izquierdo
            System.out.print(node.getKey() + "(" + (-1)*getBalance(node) + "), ");
            printNodeBalanceFactors(node.getRight()); // Recorre el subárbol derecho
        }
    }
}
