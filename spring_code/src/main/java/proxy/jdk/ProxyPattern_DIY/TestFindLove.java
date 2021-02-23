package proxy.jdk.ProxyPattern_DIY;

// 使用代理
// zhang 想找个高富帅
public class TestFindLove {
    public static void main(String[] args) throws Exception {
        Person zhang = (Person) new MyProxy().getInstance(new Zhang());
        zhang.findLove();

    }
}
