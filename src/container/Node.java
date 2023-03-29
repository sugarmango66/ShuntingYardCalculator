package container;

public class Node<T> {
    public T elem;
    public Node next;

    public Node(T elem) {
        this.elem = elem;
        this.next = null;
    }
}
