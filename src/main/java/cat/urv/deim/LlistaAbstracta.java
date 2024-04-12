package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

public class LlistaAbstracta<E extends Comparable<E>> implements ILlistaGenerica<E> {

    public Node<E> elementFantasma;
    public int numElements;

    public LlistaAbstracta() {
        this.elementFantasma = new Node<>(null);
        this.numElements = 0;
    }

    @Override
    public void inserir(E e) {
        //S'implementarà específicament a LlistaOrdenada i LlistaNoOrdenada
    }

    @Override
    public void esborrar(E e) throws ElementNoTrobat {
        Node<E> nodeAnterior = buscarNodeAnterior(e);
        if (nodeAnterior == null || nodeAnterior.segNode == null || !nodeAnterior.segNode.element.equals(e)) {
            throw new ElementNoTrobat();
        }
        Node<E> nodeAEliminar = nodeAnterior.segNode;
        nodeAnterior.segNode = nodeAEliminar.segNode;
        nodeAEliminar = null;
        numElements--;
    }

    @Override
    public E consultar(int pos) throws PosicioForaRang {
        if (pos < 0 || pos >= numElements) {
            throw new PosicioForaRang();
        }
        Node<E> actual = elementFantasma.segNode;
        for (int i = 0; i < pos; i++) {
            actual = actual.segNode;
        }
        return actual.element;
    }

    @Override
    public int buscar(E e) throws ElementNoTrobat {
        Node<E> actual = elementFantasma.segNode;
        int pos = 0;
        while (actual != null) {
            if (actual.element.equals(e)) {
                return pos;
            }
            actual = actual.segNode;
            pos++;
        }
        throw new ElementNoTrobat();
    }

    @Override
    public boolean existeix(E e) {
        try {
            buscar(e);
            return true;
        } catch (ElementNoTrobat eNoTrobat) {
            return false;
        }
    }

    @Override
    public boolean esBuida() {
        return numElements == 0;
    }

    @Override
    public int numElements() {
        return numElements;
    }

    //Mètode auxiliar per buscar el node anterior al passat per paràmetre
    private Node<E> buscarNodeAnterior(E e) {
        Node<E> actual = elementFantasma;
        while (actual.segNode != null) {
            if (actual.segNode.element.equals(e)) {
                return actual;
            }
            actual = actual.segNode;
        }
        return null;
    }

    @Override
    public Object[] elements() {
        Object[] array = new Object[numElements];
        Node<E> actual = elementFantasma.segNode;
        for (int i = 0; i < numElements; i++) {
            array[i] = actual.element;
            actual = actual.segNode;
        }
        return array;
    }
}
