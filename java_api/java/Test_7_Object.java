import java.util.Objects;

public class Test_7_Object {
    public static void main(String[] args) {
        // 每个类都使用 Object 作为超类
        // 也就意味着 Object 类有的方法，所有类都有

        // equals 比较两个类的地址
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = o1;
        System.out.println(o1.equals(o2));
        System.out.println(o1.equals(o3));

        // hashCode() 返回对象内部地址转换成的整数
        System.out.println(o1.hashCode()); // 和o3一样
        System.out.println(o2.hashCode()); // 地址不一样，所以和o1、o3不一样
        System.out.println(o3.hashCode());

        // toString() 返回一个对象的字符串表示，一般没实现序列化的类返回的是类地址

        // Object 中还有一些为垃圾回收器、线程准备的方法
        // finalize() 、getClass() 、notify() 、notifyAll() 、wait()

        // Objects类 (超类工具类) ：jdk1.7后出现.提供静态方法操作对象。重写了equals、解决空指针异常问题
        boolean bs = Objects.equals(o1, o2);  // 允许o1或o2为空
    }
}
