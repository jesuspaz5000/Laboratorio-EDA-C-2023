
public class Stack<T> {
    private Node<T> top;

    public void push(T data) {
        Node<T> newNode = new Node<T>(data);
        if (top != null) {
            newNode.setNext(top);
        }
        top = newNode;
    }

    public T pop() {
        if (top == null) {
            return null;
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
