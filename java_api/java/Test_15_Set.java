import java.util.*;

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
class Test{
    // idea 里面按住alt+insert选择equals() and hashcode()可以重写这两个方法
    private int num;

    public Test(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return num == test.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public String toString() {
        return "Test{" +
                "num=" + num +
                '}';
    }
}
class MyHashSet{
    public static void main(String[] args) {
        // 哈希值
        // Object 超类中有一个方法hashCode(),可以获取一个对象的哈希值
        Object t = new Test(1);
        System.out.println(t.hashCode());

        // String 字符串重写了这个方法
        String s = new String("abc");
        System.out.println(s.hashCode());                           // 96354  字符串的内容相同，哈希值相同
        System.out.println("abc".hashCode());                       // 96354
        System.out.println("重地".hashCode() == "通话".hashCode());  // 返回true，他两的哈希值相同，这是个巧合

        // hash表：查询速度快
        // jdk 1.8 之前： 哈希表=数组+链表
        // jdk 1.8 之后： 哈希表=数组+链表   或    数组+红黑树    （当链表长度超过8位时，由链表转为红黑树）

        // HashSet存储数据的过程：1、计算hashcode，判断这个哈希值是否存在。 2、用equals判断内容是否相等。
        // 什么时候会把值存进去：  ①当1为false，就把值存进去。    ②若1为true，2为false也存进去
        String s1 = new String("abc");
        String s2 = new String("abc");
        HashSet<String> s3 = new HashSet<>();
        s3.add(s1);
        s3.add(s2);
        s3.add("重地");
        s3.add("通话");
        System.out.println(s3);     // 输出结果：[重地, 通话, abc]

        // 自定义类重写hashCode()
        // 重写前，两个自定义类对象，即使里面的属性值相同，两个对象都可以加入到HashSet中
        // 重写后，两个自定义类对象，里面的属性值相同，两个对象加入到HashSet中，只保存一个
        HashSet<Test> t1 = new HashSet<>();
        Test t2 = new Test(11);
        Test t3 = new Test(11);
        t1.add(t2);
        t1.add(t3);
        System.out.println(t1);     // 只存了一个 [Test{num=11}]
    }
}

class MyLinkedHashSet{
    public static void main(String[] args) {
        // LinkedHashSet相当于有序的HashSet （特点：有序、不重复）
        // LinkedHashSet的底层是一个hash表+链表 （这条链表用来记录元素存储顺序）

        LinkedHashSet<String> s = new LinkedHashSet<>();
        s.add("q1");
        s.add("q2");
        s.add("q3");
        System.out.println(s);
    }
}