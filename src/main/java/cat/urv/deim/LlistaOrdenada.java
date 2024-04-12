package cat.urv.deim;

public class LlistaOrdenada<E extends Comparable<E>> extends LlistaAbstracta<E> {

    public LlistaOrdenada() {
        super();
    }

    @Override
    public void inserir(E e) {
        Node<E> nouNode = new Node<>(e);

        Node<E> actual = elementFantasma;
        while (actual.segNode != null && actual.segNode.element.compareTo(e) < 0) {
            actual = actual.segNode;
        }

        nouNode.segNode = actual.segNode;
        actual.segNode = nouNode;
        numElements++;
    }
}
