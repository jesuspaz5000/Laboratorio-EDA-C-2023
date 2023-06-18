
public class Node<E> {

    private E data;
    private Node<E> nextNode;
    private Node<E> previousNode;

    public Node() {
        this.data = null;
        this.nextNode = null;
        this.previousNode = null;
    }

    public Node(E data) {
        this.data = data;
        this.nextNode = null;
        this.previousNode = null;
    }

    public Node(E data, Node<E> nextNode) {
        this.data = data;
        this.nextNode = nextNode;
        this.previousNode = null;
    }

    public Node(E data, Node<E> nextNode, Node<E> previousNode) {
        this.data = data;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }

    /* Getters */
    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return nextNode;
    }

    public Node<E> getPrevious() {
        return previousNode;
    }

    /* Setters */
    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Node<E> nextNode) {
        this.nextNode = nextNode;
    }

    public void setPrevious(Node<E> previousNode) {
        this.previousNode = previousNode;
    }
}
