import java.util.Collection;

public class MyList<T> {

    private Node<T> root;

    // Agrega un elemento al final de la lista
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (root == null) {
            // Si la lista está vacía, el nuevo nodo se convierte en el nodo raíz
            root = newNode;
        } else {
            // Si la lista no está vacía, se recorre hasta el último nodo y se agrega el nuevo nodo
            Node<T> currentNode = root;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
    }

    // Agrega un elemento en la posición especificada
    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Indice no valido");
        }

        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            // Si se inserta en la posición 0, el nuevo nodo se convierte en la raíz y apunta al nodo actual
            newNode.setNextNode(root);
            root = newNode;
        } else {
            // Si se inserta en una posición distinta de 0, se recorre hasta el nodo anterior a la posición y se agrega el nuevo nodo
            Node<T> currentNode = root;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
        }
    }

    // Agrega todos los elementos de una colección al final de la lista
    public void addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
    }

    // Agrega todos los elementos de una colección en la posición especificada
    public void addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Indice no valido");
        }

        Node<T> currentNode = root;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.getNextNode();
        }

        for (T element : c) {
            Node<T> newNode = new Node<>(element);
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
            currentNode = newNode;
        }
    }

    // Elimina la primera aparición de un elemento en la lista
    public void remove(T element) {
        if (root == null) {
            // Si la lista está vacía, no se realiza ninguna acción
            return;
        }

        if (root.getData().equals(element)) {
            // Si el elemento a eliminar es el primer nodo, se actualiza la raíz
            root = root.getNextNode();
            return;
        }

        Node<T> currentNode = root;
        while (currentNode.getNextNode() != null) {
            if (currentNode.getNextNode().getData().equals(element)) {
                // Si se encuentra el elemento en el siguiente nodo, se actualizan los enlaces para omitirlo
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                return;
            }
            currentNode = currentNode.getNextNode();
        }
    }

    // Elimina la primera aparición de un objeto en la lista
    public boolean removeObject(Object o) {
        if (root == null) {
            // Si la lista está vacía, no se realiza ninguna acción
            return false;
        }

        if (root.getData().equals(o)) {
            // Si el objeto a eliminar es el primer nodo, se actualiza la raíz
            root = root.getNextNode();
            return true;
        }

        Node<T> currentNode = root;
        while (currentNode.getNextNode() != null) {
            if (currentNode.getNextNode().getData().equals(o)) {
                // Si se encuentra el objeto en el siguiente nodo, se actualizan los enlaces para omitirlo
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                return true;
            }
            currentNode = currentNode.getNextNode();
        }

        return false;
    }

    // Verifica si la lista contiene un elemento específico
    public boolean contains(T element) {
        Node<T> currentNode = root;
        while (currentNode != null) {
            if (currentNode.getData().equals(element)) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    // Verifica si la lista contiene todos los elementos de una colección
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains((T) element)) {
                return false;
            }
        }
        return true;
    }

    // Obtiene el elemento en la posición especificada
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Indice no valido");
        }

        Node<T> currentNode = root;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }

        return currentNode.getData();
    }

    // Obtiene el índice de la primera aparición de un objeto en la lista
    public int indexOf(Object o) {
        int index = 0;
        Node<T> currentNode = root;

        while (currentNode != null) {
            if (currentNode.getData().equals(o)) {
                return index;
            }
            currentNode = currentNode.getNextNode();
            index++;
        }

        return -1;
    }

    // Obtiene el índice de la última aparición de un objeto en la lista
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        int currentIndex = 0;
        Node<T> currentNode = root;

        while (currentNode != null) {
            if (currentNode.getData().equals(o)) {
                lastIndex = currentIndex;
            }
            currentNode = currentNode.getNextNode();
            currentIndex++;
        }

        return lastIndex;
    }

    // Modifica el elemento en la posición especificada
    public void set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Indice no valido");
        }

        Node<T> currentNode = root;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }

        currentNode.setData(element);
    }

    // Obtiene una sublista de la lista original desde el índice de inicio (incluido) hasta el índice de fin (excluido)
    public MyList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > toIndex || toIndex > size()) {
            throw new IndexOutOfBoundsException("Rango de índice no válido");
        }

        MyList<T> sublist = new MyList<>();
        Node<T> currentNode = root;
        int currentIndex = 0;

        while (currentNode != null && currentIndex < toIndex) {
            if (currentIndex >= fromIndex) {
                sublist.add(currentNode.getData());
            }
            currentNode = currentNode.getNextNode();
            currentIndex++;
        }

        return sublist;
    }

    // Elimina todos los elementos de la lista
    public void clear() {
        root = null;
    }

    // Obtiene el tamaño de la lista
    public int size() {
        int count = 0;
        Node<T> currentNode = root;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.getNextNode();
        }
        return count;
    }

    // Verifica si la lista está vacía
    public boolean isEmpty() {
        return root == null;
    }

    // Elimina todos los elementos de la lista que están presentes en una colección
    public boolean removeAll(Collection<?> c) {
        if (root == null || c == null) {
            return false;
        }

        boolean modified = false;
        Node<T> currentNode = root;
        Node<T> prevNode = null;

        while (currentNode != null) {
            if (c.contains(currentNode.getData())) {
                if (prevNode == null) {
                    // Si el primer nodo está en la colección, se actualiza la raíz
                    root = currentNode.getNextNode();
                } else {
                    // Si un nodo intermedio está en la colección, se actualizan los enlaces para omitirlo
                    prevNode.setNextNode(currentNode.getNextNode());
                }
                modified = true;
            } else {
                prevNode = currentNode;
            }
            currentNode = currentNode.getNextNode();
        }

        return modified;
    }

    // Verifica si otra lista es igual a esta lista
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof MyList)) {
            return false;
        }

        MyList<?> otherList = (MyList<?>) o;

        if (size() != otherList.size()) {
            return false;
        }

        Node<T> currentNode = root;
        Node<?> otherCurrentNode = otherList.root;

        while (currentNode != null && otherCurrentNode != null) {
            if (!currentNode.getData().equals(otherCurrentNode.getData())) {
                return false;
            }
            currentNode = currentNode.getNextNode();
            otherCurrentNode = otherCurrentNode.getNextNode();
        }

        return true;
    }

    // Convierte la lista en una cadena de texto
    @Override
    public String toString() {
        String result = "[";
        Node<T> currentNode = root;
        while (currentNode != null) {
            result += currentNode.getData().toString();
            if (currentNode.getNextNode() != null) {
                result += ", ";
            }
            currentNode = currentNode.getNextNode();
        }
        result += "]";
        return result;
    }
}
