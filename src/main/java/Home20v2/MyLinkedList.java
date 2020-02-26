package Home20v2;

import java.util.*;

public class MyLinkedList<T> implements List<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(Node<T> x = first;x != null;x =x.getNext()){
            if(x.getData() == o){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(first);
    }

    @Override
    public Object[] toArray() {
        Object [] objects = new Object[size];
        int count = 0;
        for(Node<T> x = first;x != null ;x= x.getNext()){
            objects[count] = x.getData();
            count++;
        }

        return objects;
    }

    @Override
    public boolean add(T o) {
        Node<T> node = new Node<>(o,null,null);
        if (size == 0) {
            first = node;
            size++;
            return true;
        }
        else if(size == 1) {
            Node<T> current = node;
            first.setNext(current);
            current.setPrev(first);
            last = current;
            size++;
            return true;
        }
        else if(size > 1){
            Node<T> current = first;
            while (current.getNext() != null){
                current = current.getNext();
            }
            last = node;
            current.setNext(last);
            last.setPrev(current);
            size++;


        }
        return true;

    }

    @Override
    public boolean remove(Object o) {
        if(o == null){
            for(Node<T> x = first; x != null; x = x.getNext()){
                if(x.getData() == null){
                    unlink(x);
                    return true;
                }
            }
        }else {
            for(Node<T> x = first;x != null; x = x.getNext()){
                if(o.equals(x.getData())){
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if(c.isEmpty()){
            throw new NullPointerException();
        }
        for(T o: c){
            add(o);
            size++;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.isEmpty()){
            throw new NullPointerException();
        }
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }else {
            for (T element : c) {
                add(index, element);
            }
            return true;
        }
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }else {
            int count = 0;
            for(Node<T> x = first; x != null ; x = x.getNext()){
                if(count == index){
                    return x.getData();
                }
                count++;
            }
        }
        return null;
    }

    @Override
    public T set(int index, T element) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }else {
            T oldVal = null;
            int count =0;
            for(Node<T> x = first;x != null; x = x.getNext()){
                if(count == index){
                    oldVal = x.getData();
                    remove(index);
                    add(index,element);
                }
                count++;
            }
            return oldVal;
        }
    }

    @Override
    public void add(int index, T element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = new Node<>(element,null,null);
        int count = 0;
        if(index == 0){
            node.setNext(first);
            first.setPrev(node);
            first = node;
            size++;
        }

        else if(index == size){
            node.setPrev(last);
            last.setNext(node);
            last = node;
            size++;
        }
        else if(index < size && index > 0){
            for(Node<T> x = first;x != null; x = x.getNext()){
                if(count ==  index){
                    Node<T> prev;
                    prev = x.getPrev();
                    prev.setNext(node);
                    node.setPrev(prev);
                    node.setNext(x);
                    x.setPrev(node);
                    size++;
                    return;
                }
                count++;
            }

        }



    }

    @Override
    public T remove(int index) {
        if(index <0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        T removeItem = null;
        if(index <= size){
            int count =0;
            for(Node<T> x = first;x != null ;x = x.getNext()){
                if(count == index){
                    removeItem = x.getData();
                    remove(x.getData());
                }
                count++;

            }
        }
        return removeItem;
    }

    @Override
    public int indexOf(Object o) {
        int count = 0;
        for(Node<T> x = first; x != null;x = x.getNext()){
            if(o.equals(x.getData())){
                return count;
            }
            count++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        int count = 0;
        for(Node<T> x = first; x != null;x = x.getNext()){
            if(o.equals(x.getData())){
                lastIndex= count;
            }
            count++;
        }
        return lastIndex;
    }

    @Override
    public ListIterator<T> listIterator() {

        return new MyIterator<>(first);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
        int count =0;
        for(Node<T> x = first;x != null;x = x.getNext()){
            if(count == index){
                return new MyIterator<>(x);
            }
            count++;


        }
        return new MyIterator<>(first);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex){
            throw new IndexOutOfBoundsException();
        }else {
            List<T> sublist = new MyLinkedList();
            int count = 0;
            for(Node<T> x = first ; x != null ; x = x.getNext()){
                if(count <= toIndex && count >= fromIndex){
                    sublist.add(x.getData());
                }
                count++;
            }
            return sublist;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if(c.isEmpty()){
            throw new NullPointerException();
        }
        for(Node<T> x = first;x != null;x = x.getNext()){
            if(!c.contains(x.getData())){
                Node<T> next = x.getNext();
                remove(x.getData());
                x = next;
            }

        }
        if(!c.contains(last)){
            remove(last.getData());
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if(c.isEmpty()){
            throw new NullPointerException();
        }
        for(Object o : c){
            for(Node<T> x = first;x != null;x = x.getNext()){
                if(o.equals(x.getData())){
                    remove(x.getData());

                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if(c.isEmpty()){
            throw new NullPointerException();
        }
        for(Node<T> x = first;x != null;x = x.getNext()){
            if(!c.contains(x.getData())){
                return false;
            }
        }
        return true;
    }

    @Override
    public T[] toArray(Object[] a) {
        return null;
    }

    T unlink (Node<T> x ){
        T data = x.getData();
        Node<T> next = x.getNext();
        Node<T> prev = x.getPrev();
        if (prev == null){
            first = next;
        }else {
            prev.setNext(next);
            x.setPrev(null);
        }

        if(next == null){
            last = prev;
        }else {
            next.setPrev(prev);
            x.setNext(null);
        }

        x.setData(null);
        size--;
        return data;
    }

    @Override
    public String toString() {
        String result = "[";
        for(Node<T> x = first; x != null;x = x.getNext()){
            result += x.getData() + ", ";
        }
        return result +  "]";
    }
    public T getLast(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return last.data;
    }
    public T getFirst(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public static class Node <T>  {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node ["  + data  + "]    ";
        }
    }





















}