package Home20v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        Collection<Integer> collection = new ArrayList<>();




        collection.add(1230);
        collection.add(12);
        collection.add(45);


        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
//        myLinkedList.add(34);
//        myLinkedList.add(10);
//
//        myLinkedList.add(79);
//        myLinkedList.add(90);
        //myLinkedList.removeAll(collection);
        //linkedList.removeAll(collection);



        System.out.println("mylinkedlist " + myLinkedList);
        System.out.println("collection " + collection);
        myLinkedList.add(3,56);
        //myLinkedList.add(4,67);
        myLinkedList.add(2,78);
        myLinkedList.add(3,55);
        System.out.println(myLinkedList);
        //System.out.println(myLinkedList.remove(1));
        myLinkedList.listIterator();
        System.out.println(myLinkedList);
        for(Object o : myLinkedList){
            System.out.println(o);;
        }
        System.out.println(myLinkedList);
        myLinkedList.add(2,67);
        System.out.println(myLinkedList);



    }


}
