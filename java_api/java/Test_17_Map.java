import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MyMap {
    public static void main(String[] args) {
        // Map是双列集合，一列存K，一列存V（键值对）。K不能重复
        // Map的常用实现类：HashMap、LinkedHashMap（有序的Map）

        // 常用方法
        Map<String,String> m = new HashMap<>();
        // 增, 返回插入的V
        String v1 = m.put("医生", "张三");
        // 改，返回修改之前的V
        String v2 = m.put("医生", "李四");   // v2的值是“张三”
        // 删，返回被删除的V
        String v3 = m.remove("医生");  // v3的值是“李四”，如果填入的K没有，返回null
        // 获取
        String v4 = m.get("医生");         // 没有返回null
        // 判断有否有某个K
        boolean b1 = m.containsKey("医生");

        // 遍历：通过键找值的方式
        Map<Integer,String> m1 = new HashMap<>();
        m1.put(1,"张三");
        m1.put(2,"李四");
        m1.put(3,"王五");
        for (Integer mm:m1.keySet()){              // m1.keySet()返回的是一个set类型
            System.out.println(m1.get(mm));
        }

        // 遍历：通过Entry对象遍历  （Entry：键值对对象，一个entry就是一个键和值）
        // Entry 是Map的内部类
        // Set<Map.Entry<Integer, String>> sme = m1.entrySet();
        for(Map.Entry<Integer,String> e : m1.entrySet()){
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        }

    }
}

class MyHashMap{
    public static void main(String[] args) {
        // HashMap，，K和V可以为null
        // 常用方法,详细用法见”Map“里面的
        HashMap<String,String> m = new HashMap<>();
        String v1 = m.put("医生", "张三");

        // jdk 9对集合进行了优化
        // List、Set、Map接口里面添加了一个静态方法of，可以给集合一次性添加多个元素
        // 使用前提：集合中元素个数已经确定，不再改变
        // 注意：1、of只适用于List、Set、Map接口，不适用于其实现类
        //      2、of的返回值是一个不可改变的集合，不能再使用add、put等方法进行添加
        //      3、在使用of时，不能有重复元素。

            // List<String> l1 = List.of("a","b","c");
            // Set<String> s1 = Set.of("a","b","c");
            // Map<Integer,String> m1 = Map.of(1,"a",2,"b",3,"c");
    }
}

class MyLinkedHashMap{
    // LinkedHashMap 有顺序的hashmap，它继承自HashMap
    // 使用方式和HashMap一样
}

class MyHashTable{
    // 特点：同步的、速度慢、K和V不可以为null
    // 在jdk 1.2 之后被HashMap取代了
    // 但是他的子类Properties依然在使用
}

class MyProperties{
    // Properties是唯一个和IO流结合的集合
}

