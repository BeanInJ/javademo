package proxy.jdk.proxyPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 使用代理模式，MyProxy 代理相亲这件事
// 代理模式必须实现InvocationHandler这个接口
public class MyProxy implements InvocationHandler {
    private Person person;
    // 获取代理对象的信息,返回新的代理接口
    public Object getInstance(Person person) throws Exception{
        this.person = person;
        Class clazz = person.getClass();
        System.out.println("输出被代理的对象："+clazz);
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("媒婆：你的性别是"+this.person.getSex());
        System.out.println("开始进行海选...");
        this.person.findLove();
        return null;
    }
}
