public class Test_11_StringBuilder {
    public static void main(String[] args) {
        // 所有字符串都是 String 类的，它是被final修饰的不可变类型
        // 如下abc三个字符串，都是一个独立的空间。下面的相加过程总共会占用5个空间：a b c ab abc
        String s = "a"+"b"+"c";
        // 字符串转换大小写
        String s1 = "aBc123".toUpperCase();  // 转大写
        String s2 = "aBc123".toLowerCase();  // 转小写

        // 为了解决 String 浪费空间现象，我们利用StringBuilder（字符串缓冲区）提高效率
        // StringBuilder内存中是一个数组，会根据字符串长度自动扩容（初始容量为16个字符）
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder("aaa");
        StringBuilder sb3 = sb1.append("aaa");          // 向sb1添加内容，并赋值给sb3
        // 比较地址
        System.out.println(sb1 == sb2);
        System.out.println(sb1 == sb3);
        System.out.println(sb1.equals(sb2));
        // 添加多个值
        sb2.append("123").append("abc").append("---");
        // StringBuilder 和 String  的相互转换
        String s3 = sb2.toString();                    // StringBuilder 转 String
        StringBuilder sb4 = new StringBuilder(s2);     // String 转 StringBuilder

    }
}
