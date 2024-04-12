package cat.urv.deim;

public class Node<E> {
    public E element;
    public Node<E> segNode;

    public Node(E element) {
        this.element = element;
        this.segNode = null;
    }
}