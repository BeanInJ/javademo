import java.util.HashMap;
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

        // Entry 键值对对象，一个entry就是一个键和值
        // entrySet


    }
}
