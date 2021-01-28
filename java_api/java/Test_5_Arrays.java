import java.util.Arrays;

public class Test_5_Arrays {
    public static void main(String[] args) {
        // 数组工具类Arrays

        long[] l = {1,3,2};
        // 将数组变成字符串 Arrays.toString
        String s = Arrays.toString(l);
        // 排序 ,Arrays.sort升序、从小到大
        Arrays.sort(l);

        // 字符串排序       String.charAt(index)（返回值为char）    String.toCharArray()（返回值为char[]）
        // 思路：先转为char数组排序，再转为String
        String s1 = "acbed";
        // 转为char[]
        char[] c = s1.toCharArray();
        Arrays.sort(c);
        // 转为String
        System.out.println(String.valueOf(c));
    }
}
