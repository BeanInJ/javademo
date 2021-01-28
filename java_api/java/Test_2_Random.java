import java.util.Random;

public class Test_2_Random {
    public static void main(String[] args) {
        Random r = new Random();
        // 获取int类型的随机数，随机范围整个int
        int n1 = r.nextInt();
        // 获取int类型的随机数，随机范围【0,3）
        int n2 = r.nextInt(3);
        // 获取int类型的随机数，随机范围【0,7】 ，适用于0~XX
        int n3 = r.nextInt(7)+1;
        // 除了int类型，还可以是Boolean、Bytes、Double、Float、Long
        double d1 = r.nextDouble();

        System.out.println(n1+" \\ "+n2+" \\ "+n3+" \\ "+d1);
    }
}
