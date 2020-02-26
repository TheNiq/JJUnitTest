package Home20v2;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.NoSuchElementException;

public class MyLinkedListTest {
    MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
    @BeforeEach
    void setup(){
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(10);
        myLinkedList.add(20);
    }
    @Nested
    class ExceptionTest{
        @Test
        void getFistElementExceptionTest(){
            myLinkedList.clear();
            Assertions.assertThrows(NoSuchElementException.class ,()->  myLinkedList.getFirst() );

        }
        @Test
        void getLastElementExceptionTest(){
            myLinkedList.clear();
            Assertions.assertThrows(NoSuchElementException.class, () -> myLinkedList.getLast());
        }
        @Test
        void iteratorExceptionTest(){
            Iterator<Integer> iterator = myLinkedList.iterator();
            for(int i =0;i <=4 ;i++){
                iterator.next();
            }
            Assertions.assertThrows(NoSuchElementException.class, iterator::next);
        }
        @Test
        void addAllExceptionTest(){
            ArrayList<Integer> arrayList = new ArrayList<>();
            Assertions.assertThrows(NullPointerException.class ,()-> myLinkedList.addAll(arrayList));
        }
        @Test
        void addAllIndexExceptionTest(){
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(123);
            Assertions.assertThrows(IndexOutOfBoundsException.class ,()-> myLinkedList.addAll(7,arrayList ));
            arrayList.clear();
            Assertions.assertThrows(NullPointerException.class ,()-> myLinkedList.addAll(2,arrayList));
        }
        @Test
        void getExceptionTest(){
            Assertions.assertThrows(IndexOutOfBoundsException.class ,() -> myLinkedList.get(5));
            Assertions.assertThrows(IndexOutOfBoundsException.class ,() -> myLinkedList.get(-1));
        }
        @Test
        void setExceptionTest(){
            Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myLinkedList.set(8,34));
            Assertions.assertThrows(IndexOutOfBoundsException.class,() ->myLinkedList.set(-1,23));
        }
        @Test
        void addExceptionTest(){
            Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myLinkedList.add(8,34));
            Assertions.assertThrows(IndexOutOfBoundsException.class,() ->myLinkedList.add(-1,23));
        }
        @Test
        void removeExceptionTest(){
            Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myLinkedList.remove(8));
            Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myLinkedList.remove(-1));

        }
        @Test
        void listIteratorExceptionTest(){

            Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myLinkedList.listIterator(8));
            Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myLinkedList.listIterator(-1));
        }
        @Test
        void subListExceptionTest(){
            Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myLinkedList.subList(0,7));
            Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> myLinkedList.subList(-1,6));
        }
        @Test
        void retainAllExceptionTest(){
            ArrayList<Integer> arrayList = new ArrayList<>();
            Assertions.assertThrows(NullPointerException.class ,()-> myLinkedList.retainAll(arrayList));
        }
        @Test
        void removeAllExceptionTest(){
            ArrayList<Integer> arrayList = new ArrayList<>();
            Assertions.assertThrows(NullPointerException.class ,()-> myLinkedList.removeAll(arrayList));
        }
        @Test
        void containAllExceptionTest(){
            ArrayList<Integer> arrayList = new ArrayList<>();
            Assertions.assertThrows(NullPointerException.class ,()-> myLinkedList.containsAll(arrayList));
        }


    }

    @Test
    void sizeTest(){
        Assertions.assertEquals(5,myLinkedList.size());
    }

}
