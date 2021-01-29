import java.util.Arrays;

public class Test_10_System {
    public static void main(String[] args) {
        // System类里面的都是静态方法

        // 返回系统时间毫秒 （多用来统计程序执行效率）
        long l = System.currentTimeMillis();

        // 数组复制   把i1的0-3 复制到i2中
        int[] i1 = {1,2,3,4,5} , i2 = {6,7,8,9,0} ;
        System.arraycopy(i1,0,i2,0,3);
        System.out.println(Arrays.toString(i2));


    }
}
