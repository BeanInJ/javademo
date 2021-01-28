import java.util.Scanner;

public class Test_1_Scanner {
    public static void main(String[] args) {
        // 创建 Scanner 对象
        Scanner sc = new Scanner(System.in);
        // 获取输入的字符串对象
        String s = sc.next();
        // 获取输入的int对象
        int i = sc.nextInt();

        System.out.println(s+""+i);
    }
}
