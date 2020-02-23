package Home20;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main  {
    public static void main(String[] args) {
        MyMap<String,Integer> map = new MyMap<>();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("123","1234");
        System.out.println(hashMap.remove(null));
        map.put("google.com", 123);
        map.put("youtube.com", 173);
        map.put("google.com/3",1);
        map.put("google.com/4",2);
        map.put("google.com/5",3);
        map.put("google.com/6",4);
        map.put("google.com/9",7);
        map.put("google.com/10",9);
        map.put("google.com/11",10);
        map.put("google.com/12",90);
        map.put("google.com/43432",null);
        System.out.println(map.containsKey("google.com/43432"));


    }
}