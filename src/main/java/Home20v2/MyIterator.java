package Home20v2;


import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class MyIterator<T> implements ListIterator<T> {
    private MyLinkedList.Node<T> nodes;
    public MyIterator(MyLinkedList.Node<T> nodes) {
        this.nodes = nodes;
    }


    @Override
    public boolean hasNext() {

        return nodes != null;
    }

    @Override
    public T next() {
        T current;
        if(hasNext()){
            current = nodes.getData();
            nodes = nodes.getNext();
            return current;

        }else {
            throw new NoSuchElementException();
        }


    }

    @Override
    public boolean hasPrevious() {
        if(nodes.getPrev() != null){
            return true;
        }
        return false;
    }

    @Override
    public T previous() {
        if(nodes.getPrev() != null){
            return nodes.getPrev().getData();
        }else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int nextIndex() {
        int count = 0;
        for(MyLinkedList.Node<T> node = nodes; node != null; node = node.getPrev()){
            if(node.getPrev() == null){
                count++;
                return count;
            }
            count++;

        }
        return size();
    }

    @Override
    public int previousIndex() {
        int count = 0;
        for(MyLinkedList.Node<T> node = nodes; node != null; node = node.getPrev()){
            if(node.getPrev() == null){
                count--;
                return count;
            }
            count++;

        }
        return size();
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(T t) {
        nodes.setData(t);
    }

    @Override
    public void add(T t) {

    }

    private int size(){
        int size = 0;
        MyLinkedList.Node<T> buff = null;
        for(MyLinkedList.Node<T> x = nodes; x != null; x = x.getPrev()){
            if(x.getPrev() == null) {
                buff = x;
            }
        }
        for(MyLinkedList.Node<T> x = buff;x != null;x = x.getNext()){
            size++;
        }
        return size;



    }

}


