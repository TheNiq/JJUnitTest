
import java.util.*;

public class MyMap implements Map {
    private Entry [] table = new Entry[capacity];
    private static int capacity = 16;
    private int size = 0;
    private float loadFactor = 0.75f;
    private float  threshold = table.length * loadFactor;


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        int index = indexFor(hash(key.hashCode()),table.length);
        if(table[index] != null){
            if(table[index].key == key || key.equals(table[index].key)){
                return true;
            }
            if(table[index].next != null){
                Entry current = table[index];
                while (current.next != null){
                    current = current.next;
                    if(current.key == key || key.equals(current.key)){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for(int i = 0;i < table.length;i++){
            if(table[i] != null ){
                if(table[i].value.equals(value)){
                    return true;
                }
                if(table[i].next != null){
                    Entry current = table[i];
                    while (current.next != null){
                        current = current.next;
                        if(current.value.equals(value)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Object get(Object key) {
        int index = indexFor(hash(key.hashCode()), table.length);
        if(table[index].next != null){
            Entry current = table[index];
            while (current != null){
                if(current.key.equals(key)){
                    return current.value;
                }
                current = current.next;
            }
        }
        return table[index].value;
    }

    public Object put(Object key, Object value) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash, table.length);
        if(table[index] != null ){
            if(table[index].hash == hash && table[index].key == key || key.equals(table[index].key)){
                addEntry(hash,value,index,key);
                size--;
                if(size >= threshold){
                    resize();
                }
                return table[index].value;

            }
            else {
                addIfHasCollision(hash,value,index,key);
                if(size >= threshold){
                    resize();
                }
                return null;
            }

        }
        addEntry(hash,value,index,key);
        if(size >= threshold){
            resize();
        }
        return null;
    }

    public Object remove(Object key) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash, table.length);
        if(table[index].hash == hash && table[index].key == key || key.equals(table[index].key)){
            if(table[index].next != null){
                Object valueDelete = table[index].value;
                table[index] = table[index].next;
                table[index].setNext(null);
                size--;
                return valueDelete;
            }
            Object valueDelete = table[index].value;
            table[index] = null;
            size--;
            return valueDelete;
        }
        Entry current = table[index];
        while (current.next != null){
            Entry oldEntry = current;
            current = current.next;
            if(current.hash == hash && current.key == key || key.equals(current.key)){
                if(current.next != null){
                    Object valueDelete = current.value;
                    oldEntry.setNext(current.next);
                    size--;
                    return valueDelete;
                }else {
                    Object valueDelete = current.value;
                    oldEntry.setNext(null);
                    size--;
                    return valueDelete;
                }
            }
        }

        return null;
    }

    public void putAll(Map m) {
        Map.Entry  x ;
        for (Object e : m.entrySet()) {
            x = (Map.Entry) e;
            Object key = x.getKey();
            Object value = x.getValue();
            put(key,value);
        }

    }

    public void clear() {
        table = new Entry[capacity];
        capacity = 16;
        size = 0;
        threshold = table.length * loadFactor;
    }

    public Set keySet() {
        Set keys = new HashSet();
        for(int i =0;i < table.length; i++){
            if (table[i] != null){
                keys.add(table[i].key);
                if(table[i].next != null){
                    Entry current = table[i];
                    while (current.next != null){
                        current = current.next;
                        keys.add(current.key);
                    }
                }
            }
        }
        return keys;
    }

    public Collection values() {
        Collection collectionValues = new ArrayList();
        for(int i =0;i < table.length; i++){
            if (table[i] != null){
                collectionValues.add(table[i].value);
                if(table[i].next != null){
                    Entry current = table[i];
                    while (current.next != null){
                        current = current.next;
                        collectionValues.add(current.value);
                    }
                }
            }
        }
        return collectionValues;
    }

    public Set<Entry> entrySet() {
        Set<Entry> setEntry = new HashSet<Entry>();
        for(int i =0;i < table.length; i++){
            if (table[i] != null){
                setEntry.add(table[i]);
                if(table[i].next != null){
                    Entry current = table[i];
                    while (current.next != null){
                        current = current.next;
                        setEntry.add(current);
                    }
                }
            }
        }
        return setEntry;
    }













    private void resize(){
        int newCapacity = capacity * 2;
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        threshold =  newCapacity * loadFactor;
        System.out.println("__------------------------");
    }
    private void transfer(Entry[] newTable){
        size = 0;
        Entry[] oldTable = table;
        table = newTable;
        for(int i =0;i < oldTable.length;i++){
            Entry x =  oldTable[i];
            if( x != null){
                Object key = x.key;
                Object value = x.value;
                int hash = x.hash;
                int index = indexFor(hash, newTable.length);
                addEntry(hash,value,index,key);
                if(x.next != null){
                    while (x.next != null){
                        x = x.next;
                        Object key2 = x.key;
                        Object value2 = x.value;
                        int hash2 = x.hash;
                        int index2 = indexFor(hash2, newTable.length);
                        if(table[index2] != null){
                            addIfHasCollision(hash2,value2,index2,key2);
                        }else {
                            addEntry(hash2,value2,index2,key2);
                        }
                    }

                }
            }

        }
    }
    private void addEntry(int hash, Object value, int index,Object key){
        Entry entry = new Entry(hash,key,value,null);
        table[index] = entry;
        size++;
    }
    private void addIfHasCollision(int hash, Object value, int index,Object key){
        Entry current = table[index];
        while (current.next != null) {
            Entry prev = current;
            current = current.next;
            if(current.hash ==  hash && current.key == key || key.equals(current.key)){
                current = new Entry(hash,key,value,null);
                prev.setNext(current);
                return;
            }
        }
        current.next = new Entry(hash,key,value,null);
        size++;

    }

    static int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    static int indexFor(int h, int length){
        return h & (length-1);

    }






    public static class Entry {
        int hash;
        Object key;
        Object value;
        Entry next;

        public Entry(int hash, Object key, Object value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return hash == entry.hash &&
                    Objects.equals(key, entry.key) &&
                    Objects.equals(value, entry.value) &&
                    Objects.equals(next, entry.next);
        }

        @Override
        public String toString() {
            return "Entry [" +
                    " key=" + key +
                    ", value=" + value + " ]";
        }
    }






    @Override
    public String toString() {
        String result = "MyMAP [ ";
        for(int i = 0;i < table.length;i++){
            if(table[i] != null){
                result += table[i].key + " = " + table[i].value + ", ";
                if(table[i].next != null){
                    Entry current = table[i];
                    while (current.next != null){
                        current = current.next;
                        result += current.key + " = " + current.value+ ", ";
                    }
                }
            }
        }
        return result + " ] ";

    }

}
