import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class MySet {
    // set继承自Collection，元素不可重复，没有索引,不是同步的
    public static void main(String[] args) {
        Set<Integer> hs = new HashSet<>();
        hs.add(1);
        hs.add(2);
        hs.add(3);

        // while遍历
        Iterator<Integer> i = hs.iterator();
        while (i.hasNext()){
            System.out.println(i.next());

        }
        // for遍历
        for(Integer i1:hs){
            System.out.println(i1);
        }


    }
}

class MyHashSet{
    public static void main(String[] args) {
        Set<Object> hs = new HashSet<>();

    }
}