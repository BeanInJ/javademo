public class Test_3_Array {
    public static void main(String[] args) {
        // 在1.6的API里面我们能找到两个Array
        // 这里用的是java.lang.reflect包下的

        // Array这个类是不能够被实例化的，但是我们可以直接用基本类型加上[],创建对应类型的数组
        // 注意数组一旦创建，长度固定，不能发生改变

        // 下面是一个int类型的数组几种写法
        // 静态赋值
        int a[] = new int[]{10,20,30};
        int[] b = new int[]{10,20,30};
        int c[] = {10,20,30};
        // 动态赋值
        int[] d = new int[3];
        d[0] = 10;
        d[1] = 20;
        d[2] = 30;

        // 下面是数组被动态初始化是，各种类型的默认值：
            //  1).byte,short,int,long类型，数组被赋值成0.
            //　2).float,double类型，数组被赋值成0.0.
            //  3).char类型，数组被赋值成“\u0000”.
            //　4).boolean类型，数组被赋值成false.
            //　5).引用类型(类，接口，数组)，数组被赋值成null.

        // 数组还可以定义成二维的
        int[][] dd = {{1, 2, 3}, {4, 5}, {6, 7, 8, 9}};
        // 也可以自定义对象数组
        NewArray[] ns = new NewArray[3];
        ns[0] = new NewArray("李四");
        ns[1] = new NewArray("张三");
        ns[2] = new NewArray("王五");

        System.out.println(ns[0]);
        System.out.println(dd[2][1]);

        // 数组长度是不可变的，如果想要“可变”，那就用ArrayList集合
    }
}

class NewArray{
    private String name;

    NewArray(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}