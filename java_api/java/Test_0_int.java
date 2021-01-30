import java.util.ArrayList;

public class Test_0_int {
    public static void main(String[] args) {
        // 下面是java中八大基本类型 及 对应包装类
        //        基本类型　	包装器类型
        //        boolean	Boolean       布尔值
        //        char	    Character     两个字节    从0到65535，存Unicode
        //        int	    Integer       整数       32位，约-21亿到21亿
        //        byte    	Byte          一个字节    从-128到127，从0到127存ascii码
        //        short  	Short         短整数      约-3万到3万
        //        long	    Long          长整数      64位，约-90亿亿到90亿亿
        //        float  	Float         单精度浮点   32位（4个字节）
        //        double	Double        双精度浮点   64位（8个字节）

        // 基本类型 仅能存数字，他们的包装类型封装了很多方法：
        // 输出最大、最小值： MIN_VALUE、MAX_VALUE
        int i1 = Byte.MAX_VALUE;
        double i2 = Double.MIN_VALUE;

        // 装箱、拆箱（基本类型和包装类型的转换）,我们常用的是自动拆箱、装箱的方式
        Integer i3 = 2;                       // 自动装箱
        Integer i4 = new Integer(2);    // 装箱 方式1
        Integer i5 = Integer.valueOf(2);      // 装箱 方式2
        Integer i6 = Integer.valueOf("2");    // 装箱

        int i7 = i4;                          // 自动拆箱
        int i8 = i4.intValue();               // 拆箱

        // ArrayList 只能存储包装类，它的add、get可以自动装箱、拆箱
        ArrayList<Integer> l = new ArrayList<>();
        l.add(5);                              // 自动装箱
        int i9 = l.get(0);                     // 自动拆箱

        // 基本类型与String之间的转换
        // 1、基本类型转String  （三种方式）
        String s1 = 100 + "";
        String s2 = Double.toString(0.333);
        String s3 = String.valueOf(1.1);
        // 2、String转基本类型    注意：被转换的字符串内容，要符合转出去对应的基本类型，比如“a”不能转为int
        int i01 = Integer.parseInt("11");
        long l01 = Long.parseLong("202016666");
    }
}
