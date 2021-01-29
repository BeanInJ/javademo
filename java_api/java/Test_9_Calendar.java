import java.util.Calendar;
import java.util.Date;

public class Test_9_Calendar {
    public static void main(String[] args) {
        // Calendar 日历类

        Calendar c = Calendar.getInstance();
        // get（）
        // 获取年份
        int i = c.get(Calendar.YEAR);
        // 获取月份    西方的月份0-11   东方的月份1-12
        int i1 = c.get(Calendar.MONTH);
        // 日期
        int i2 = c.get(Calendar.DAY_OF_MONTH);
        int i3 = c.get(Calendar.DATE);
        // 时分秒 同理

        // set（）   set设置的，可以用get获取
        c.set(Calendar.YEAR,2099);  // 设置年
        c.set(Calendar.MONTH,6);    // 设置月
        c.set(Calendar.DATE,18);    // 设置日
        // 同时设置年月日
        c.set(2099,6,18);

        // 增加年、月、日、时...
        c.add(Calendar.YEAR,2);    // 增加2年
        c.add(Calendar.DATE,-3);   // 减少3月

        // 日历转为日期
        Date date = c.getTime();

    }
}
