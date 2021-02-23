package proxy.jdk.proxyPattern;

// Zhang 被代理对象
public class Zhang implements Person{

    private String sex = "女";

    @Override
    public void findLove() {
        System.out.println("我是"+sex+"的，我想找个高富帅");
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

}