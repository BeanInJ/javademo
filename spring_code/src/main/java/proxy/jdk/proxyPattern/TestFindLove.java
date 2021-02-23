package proxy.jdk.proxyPattern;

// 使用代理
// zhang 想找个高富帅
public class TestFindLove {
    public static void main(String[] args) throws Exception {
        Person zhang = (Person) new MyProxy().getInstance(new Zhang());
        System.out.println("输出代理后的对象："+zhang.getClass());
        zhang.findLove();

        // 代理对象实现原理：
        // 1、拿到被代理对象的引用，然后获取它的接口
        // 2、JDK代理会重新生成一个类，同时实现代理对象实现的接口
        // 3、把被代理对象的引用拿到
        // 4、重新动态生成一个class字节码，重新编译


        // 生成代理对象的字节码文件
//        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
//        FileOutputStream fos = new FileOutputStream("d://$Proxy0.class");
//        fos.write(data);
//        fos.close();

    }
}
