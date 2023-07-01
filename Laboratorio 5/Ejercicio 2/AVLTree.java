package AVL;
import javax.swing.SwingUtilities;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
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
    
    // Método para buscar un valor
    public boolean search(T key) {
        return search(root, key) != null;
    }

    // Método auxiliar para la búsqueda
    private Node<T> search(Node<T> node, T key) {
        // El nodo base es nulo o la clave está presente en la raíz
        if (node == null || node.getKey().equals(key))
            return node;

        // La clave es mayor que la clave de la raíz
        if (node.getKey().compareTo(key) < 0)
            return search(node.getRight(), key);

        // La clave es menor que la clave de la raíz
        return search(node.getLeft(), key);
    }

    // Método para obtener el valor mínimo del árbol
    public T getMin() {
        return getMin(root).getKey();
    }

    // Método auxiliar para obtener el valor mínimo
    private Node<T> getMin(Node<T> node) {
        // Si el nodo es nulo, se retorna null
        if (node == null) {
            return null;
        // Si el nodo no tiene hijo izquierdo, se retorna el nodo
        } else if (node.getLeft() == null) {
            return node;
        // Si el nodo tiene hijo izquierdo, se llama recursivamente a getMin con el hijo izquierdo
        } else {
            return getMin(node.getLeft());
        }
    }

    // Método para obtener el valor máximo del árbol
    public T getMax() {
        return getMax(root).getKey();
    }

    // Método auxiliar para obtener el valor máximo
    private Node<T> getMax(Node<T> node) {
        // Si el nodo es nulo, se retorna null
        if (node == null) {
            return null;
        // Si el nodo no tiene hijo derecho, se retorna el nodo
        } else if (node.getRight() == null) {
            return node;
        // Si el nodo tiene hijo derecho, se llama recursivamente a getMax con el hijo derecho
        } else {
            return getMax(node.getRight());
        }
    }

    // Método para obtener el predecesor de un valor dado
    public T getPredecessor(T key) {
        // Busca el nodo con el valor dado
        Node<T> node = search(root, key);
        // Si no encuentra el nodo, retorna null
        if (node == null) {
            return null; // Retorna null si el valor no está presente en el árbol
        }

        // Si el nodo tiene un hijo izquierdo, el máximo valor en el subárbol izquierdo es el predecesor
        if (node.getLeft() != null) {
            return getMax(node.getLeft()).getKey(); // Caso 1
        }

        // Si el nodo no tiene hijo izquierdo, se busca el primer ancestro que sea mayor que el nodo. Ese será el predecesor
        Node<T> predecessor = null;
        Node<T> ancestor = root;
        while (ancestor != node) { // Caso 2
            if (node.getKey().compareTo(ancestor.getKey()) > 0) {
                predecessor = ancestor;
                ancestor = ancestor.getRight();
            } else {
                ancestor = ancestor.getLeft();
            }
        }
        return (predecessor == null) ? null : predecessor.getKey();
    }

    // Método para obtener el sucesor de un valor dado
    public T getSuccessor(T key) {
        // Busca el nodo con el valor dado
        Node<T> node = search(root, key);
        // Si no encuentra el nodo, retorna null
        if (node == null) {
            return null; // Retorna null si el valor no está presente en el árbol
        }

        // Si el nodo tiene un hijo derecho, el mínimo valor en el subárbol derecho es el sucesor
        if (node.getRight() != null) {
            return getMin(node.getRight()).getKey(); // Caso 1
        }

        // Si el nodo no tiene hijo derecho, se busca el primer ancestro que sea menor que el nodo. Ese será el sucesor
        Node<T> successor = null;
        Node<T> ancestor = root;
        while (ancestor != node) { // Caso 2
            if (node.getKey().compareTo(ancestor.getKey()) < 0) {
                successor = ancestor;
                ancestor = ancestor.getLeft();
            } else {
                ancestor = ancestor.getRight();
            }
        }
        return (successor == null) ? null : successor.getKey();
    }

    public void outputConsola() {
        printNodeBalanceFactors(root);
        System.out.println();
    }

    // Método para imprimir los factores de equilibrio de todos los nodos del árbol
    private void printNodeBalanceFactors(Node<T> node) {
        if (node != null) {
            printNodeBalanceFactors(node.getLeft()); // Recorre el subárbol izquierdo
            System.out.print(node.getKey() + "(" + (-1)*getBalance(node) + "), ");
            printNodeBalanceFactors(node.getRight()); // Recorre el subárbol derecho
        }
    }

    // Método para mostrar el árbol
    public void output() {
        Graph graph = new SingleGraph("AVL Tree");
        outputGraphHelper(root, graph);

        // Se utiliza SwingUtilities.invokeLater para asegurar que el código se ejecute en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                graph.display();
            }
        });
    }

    // Método auxiliar para añadir los nodos y las aristas al grafo
    private void outputGraphHelper(Node<T> node, Graph graph) {
        // Verifica si el nodo es nulo
        if (node != null) {
            // Obtiene la llave del nodo y la convierte en una cadena de caracteres
            String nodeId = node.getKey().toString();
            // Si el nodo no existe en el grafo, lo añade
            if (graph.getNode(nodeId) == null) {
                graph.addNode(nodeId);
            }
            // Si el nodo tiene un hijo izquierdo
            if (node.getLeft() != null) {
                // Obtiene la llave del hijo izquierdo y la convierte en una cadena de caracteres
                String leftChildId = node.getLeft().getKey().toString();
                // Si el hijo izquierdo no existe en el grafo, lo añade
                if (graph.getNode(leftChildId) == null) {
                    graph.addNode(leftChildId);
                }
                // Crea un identificador para la arista entre el nodo y su hijo izquierdo
                String edgeId = nodeId + "-" + leftChildId;
                // Si la arista no existe en el grafo, la añade
                if (graph.getEdge(edgeId) == null) {
                    graph.addEdge(edgeId, nodeId, leftChildId);
                }
                // Llama recursivamente a la función para el hijo izquierdo
                outputGraphHelper(node.getLeft(), graph);
            }
            // Si el nodo tiene un hijo derecho
            if (node.getRight() != null) {
                // Obtiene la llave del hijo derecho y la convierte en una cadena de caracteres
                String rightChildId = node.getRight().getKey().toString();
                // Si el hijo derecho no existe en el grafo, lo añade
                if (graph.getNode(rightChildId) == null) {
                    graph.addNode(rightChildId);
                }
                // Crea un identificador para la arista entre el nodo y su hijo derecho
                String edgeId = nodeId + "-" + rightChildId;
                // Si la arista no existe en el grafo, la añade
                if (graph.getEdge(edgeId) == null) {
                    graph.addEdge(edgeId, nodeId, rightChildId);
                }
                // Llama recursivamente a la función para el hijo derecho
                outputGraphHelper(node.getRight(), graph);
            }
        }
    }



}
