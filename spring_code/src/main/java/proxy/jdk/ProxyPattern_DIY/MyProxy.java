package proxy.jdk.ProxyPattern_DIY;

import java.lang.reflect.Method;

// 使用代理模式，MyProxy 代理相亲这件事
// 代理模式必须实现InvocationHandler这个接口
public class MyProxy implements GPInvocationHandler {
    private Person person;

    // 获取代理对象的信息,返回新的代理接口
    public Object getInstance(Person person) throws Exception{
        this.person = person;
        Class clazz = person.getClass();
//        System.out.println("输出被代理的对象："+clazz);
        return GPPorxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("媒婆：");
        System.out.println("开始进行海选...");
        this.person.findLove();
        return null;
    }
}
