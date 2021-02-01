import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test_16_Collections {
    public static void main(String[] args) {
        //  Collections是集合的工具类

        ArrayList<String> al = new ArrayList<>();
        // 向集合里面添加多个元素
        Collections.addAll(al,"123","abcd","aaa");

        // 打乱集合顺序
        Collections.shuffle(al);

        // 按照升序排序 (只能传List，不能传set)
        // List里面传自定义数据类型，只有实现了comparaTo方法的类型才能够排序（实现这个方法可以继承接口Comparable<自定义类型>）
        // conpareTo方法返回值就是排序规则，按年龄升序例：return this.getAge()-person.getAge()
        Collections.sort(al);

        // 按照升序排序（在比较时重写排序规则）
        Collections.sort(al, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 按照字符串长度排序
                // 这里return的负数就是升序、整数就是降序
                return o1.length()-o2.length();
            }
        });


    }
}
