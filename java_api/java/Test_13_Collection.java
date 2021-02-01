import java.util.ArrayList;
import java.util.Collection;

public class Test_13_Collection {
    public static void main(String[] args) {
        // Collection 是线性集合的一个根接口
        // 它下面包含三个子接口： List(有序集合)、Set(无序集合) 、 Queue(线性列表)
        //        List：有序集合、允许重复、有索引
        //        Set：无序集合、不可重复、没索引

        //  Collection中包含的方法：
        //  | boolean add(Object o)              |   增（单个）                     |
        //  | boolean remove(Object o)           |   删（单个）                     |
        //  | void clear()                       |   删（清空）                     |
        //  | boolean contains(Object o)         |   查（单个）                     |
        //  | boolean containsAll(Collection c)  |   查找集合中是否有集合c中的元素     |
        //  | int size()                         |   返回元素的数量                  |
        //  | boolean isEmpty()                  |   判断集合是否为空                |
        //  | Iterator iterator()                |   返回一个迭代器                  |
        //  | boolean addAll(Collection c)       |   将集合c中所有的元素添加给该集合    |
        //  | void removeAll(Collection c)       |   从集合中删除c集合中也有的元素      |
        //  | void retainAll(Collection c)       |   从集合中删除集合c中不包含的元素    |

        Collection<String> c1 = new ArrayList<>();    // 向上转型
        Collection<String> c2 = new ArrayList<>();
        c2.add("a");                    // 增
        c2.add("b");
        c1.addAll(c2);                  // 增（多个）
        int i = c1.size();              // 返回个数
        c1.remove("a");              // 删
        String s = c1.toString();       // 序列化
        Object[] o = c1.toArray();      // 转为数组
        c1.clear();                     // 清空集合里面的内容

    }
}
