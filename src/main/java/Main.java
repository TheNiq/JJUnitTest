import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main  {
    public static void main(String[] args) {
        MyMap<String,Integer> map = new MyMap<>();
        HashMap<String,Integer> hashMap = new HashMap<>();
        map.put("google.com", 123);
        map.put("youtube.com", 173);

        //System.out.println(map.put("google.com/2",783459));

        map.put("google.com/3",1);
        map.put("google.com/4",2);
        map.put("google.com/5",3);
        map.put("google.com/6",4);
        hashMap.entrySet();

        map.put("google.com/9",7);
        map.put("google.com/10",9);
        map.put("google.com/11",10);
        map.put("google.com/12",90);
        map.put("google.com/12",901);


        map.put("google.com/43432",24532432);



        hashMap.put("W", 234);//
        hashMap.put("googleasda.com",2123124);
//        System.out.println(hashMap.keySet());
//        System.out.println(hashMap);
//        System.out.println(map.keySet());


        System.out.println(map);
        System.out.println(map.size());
        System.out.println(map.remove("google.com/9"));
        map.remove("google.com/10");

//
//
//        System.out.println(map.containsKey("google.com/12"));
//        System.out.println(map.containsValue(901));
        System.out.println(map);
        System.out.println(map.size());
        map.putAll(hashMap);
        System.out.println("PutAll  -> " + map);
        Set<Map.Entry<String,Integer>> x = map.entrySet();
//        Set<Map.Entry<String,String>> x = map.entrySet();
        System.out.println("EntrySet\n");
        System.out.println(x);
        System.out.println(map.size());

        System.out.println(map.keySet());
        System.out.println(map.values());

    }
}
