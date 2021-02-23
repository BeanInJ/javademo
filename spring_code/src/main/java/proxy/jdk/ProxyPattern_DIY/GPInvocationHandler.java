package proxy.jdk.ProxyPattern_DIY;

import java.lang.reflect.Method;

// 自己写一个代理
public interface GPInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
