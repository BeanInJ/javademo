package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

// 媒婆
public class MyProxy implements MethodInterceptor {
    public Object getInstance(Class clazz) throws Exception{
        // 通过反射机制实例化
        Enhancer enhancer = new Enhancer();
        // 设置o为父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    // 字节码重组
    // 对于用户来说是无感知的
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("媒婆：开始海选");
        // 这个o是CGlib给我们new出来的
        // CGlib new出来的是被代理对象zhang的子类
        methodProxy.invokeSuper(o,objects);
        System.out.println("配对完成");
        return null;
    }
}
