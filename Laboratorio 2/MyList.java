
import java.util.Collection;

public class MyList<T> {

    private Node<T> root;

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (root == null) {
            root = newNode;
        } else {
            Node<T> currentNode = root;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
    }

    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Indice no valido");
        }

        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNextNode(root);
            root = newNode;
        } else {
            Node<T> currentNode = root;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
        }
    }

    public void addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
    }

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

    public void remove(T element) {
        if (root == null) {
            return;
        }

        if (root.getData().equals(element)) {
            root = root.getNextNode();
            return;
        }

        Node<T> currentNode = root;
        while (currentNode.getNextNode() != null) {
            if (currentNode.getNextNode().getData().equals(element)) {
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                return;
            }
            currentNode = currentNode.getNextNode();
        }
    }

    public boolean removeObject(Object o) {
        if (root == null) {
            return false;
        }
    
        if (root.getData().equals(o)) {
            root = root.getNextNode();
            return true;
        }
    
        Node<T> currentNode = root;
        while (currentNode.getNextNode() != null) {
            if (currentNode.getNextNode().getData().equals(o)) {
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
    
        return false;
    }

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

    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains((T) element)) {
                return false;
            }
        }
        return true;
    }

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
    

    public void clear() {
        root = null;
    }

    public int size() {
        int count = 0;
        Node<T> currentNode = root;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.getNextNode();
        }
        return count;
    }

    public boolean isEmpty() {
        return root == null;
    }

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
                    root = currentNode.getNextNode();
                } else {
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


}
