import java.util.ArrayList;

public class Test_4_ArrayList {
    public static void main(String[] args) {
        // 数组的长度不可变，ArrayList的长度是可变的

        // ArrayList<> 可以根据<>里面的泛型定义要传入的数据类型，泛型只能是引用类型，不能是基本类型
        // jdk 1.7开始,右边的泛型可以不写
        ArrayList<String> al = new ArrayList<>();
        al.add("张三");
        al.add("李四");

        // ArrayList默认实现了序列化，直接打印ArrayList打印出来的是字符串内容
        System.out.println(al);
        // 获取其中一个元素
        System.out.println(al.get(1));
        // 删除一个,有返回值，删除的内容是什么就返回什么。
        al.remove(0);
        // 获取长度
        int s = al.size();
        // 遍历集合
        for (String l : al) { System.out.print(l);}

    }
}
