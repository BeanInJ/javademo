package proxy.cglib;

public class TestMyProxy {
    public static void main(String[] args) throws Exception {
        // CGlib的动态代理是通过生成一个被代理对象的子类，然后重写父类方法
        // 生成以后的对象，可以强制转换为被代理对象（子类引用，赋值给父类）
        Zhang zhang =(Zhang) new MyProxy().getInstance(Zhang.class);
        zhang.findLove();

    }
}
