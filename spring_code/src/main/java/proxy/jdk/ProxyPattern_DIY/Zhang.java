package proxy.jdk.ProxyPattern_DIY;

// Zhang 被代理对象
public class Zhang implements Person {

    @Override
    public void findLove() {
        System.out.println("我想找个高富帅");
    }
}