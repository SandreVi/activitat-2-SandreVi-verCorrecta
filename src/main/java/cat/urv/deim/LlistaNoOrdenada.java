package cat.urv.deim;

public class LlistaNoOrdenada<E extends Comparable<E>> extends LlistaAbstracta<E> {

    public LlistaNoOrdenada() {
        super();
    }

    @Override
    public void inserir(E e) {
        Node<E> nouNode = new Node<>(e);
        nouNode.segNode = elementFantasma.segNode;
        elementFantasma.segNode = nouNode;
        numElements++;
    }

}
