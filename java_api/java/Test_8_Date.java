import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test_8_Date {
    public static void main(String[] args) throws ParseException {
        // java.util.Date 表示特定的瞬间，精确到毫秒

        // 概念：
        // 1、基准时间：1970年1月1日 00:00:00 GMT   为 0 毫秒
        // 2、1000毫秒 = 1 秒

        // 获取当前系统时间毫秒数（从1970到现在的毫秒数）
        // 下面两种方式都可以
        long l1 = System.currentTimeMillis();
        long l2 = new Date().getTime();

        // 获取日期 (输出格式：星期X 月份 日期 XX：XX：xx CST 年份)
        Date date1 = new Date();                // 当前日期
        Date date2 = new Date(0L);              // 1970年1月1日 00:00:00
        Date date3 = new Date(1611884139230L);  // 2020年1月19日

        // 日期格式化   java.text.SimpleDateFormat
        // y 年
        // M 月
        // d 天
        // H 小时（0-23）  k 小时（1-24）
        // m 分钟
        // s 秒   S 毫秒

        // Date转成日期字符串
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy年 MM月 dd日 HH时mm分ss秒");
        String d1 = f1.format(new Date());

        // 日期字符串转成Date    parse需要抛出异常
        SimpleDateFormat f2 = new SimpleDateFormat("yyyy年 MM月 dd日 HH时mm分ss秒");
        Date d2 = f2.parse("2021年 01月 29日 10时12分23秒");
    }
}
