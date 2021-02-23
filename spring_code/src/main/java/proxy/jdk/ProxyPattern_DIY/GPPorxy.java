package proxy.jdk.ProxyPattern_DIY;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// 生成代理对象的代码
public class GPPorxy {
    private static String ln = "\r\n";
    public static Object newProxyInstance(GPClassLoader loader, Class<?>[] interfaces, GPInvocationHandler h) throws Exception{
        // 1、生成源代码
        String proxySrc = generateSrc(interfaces[0]);
        // 2、将生成的源代码输出到磁盘，保存到.java文件
        String path = GPPorxy.class.getResource("").getPath();
        File f = new File(path+"$Proxy0.java");
        FileWriter fileWriter = new FileWriter(f);
        fileWriter.write(proxySrc);
        fileWriter.flush();
        fileWriter.close();
        // 3、编译源代码，生成class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
        Iterable iterable = manager.getJavaFileObjects(f);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        manager.close();

        // 4、将class文件中的内容，动态加载到jvm中

        // 5、返回被代理后的代理对象
        Class proxyClass = loader.findClass("$Proxy0");
        Constructor constructor = proxyClass.getConstructor(GPInvocationHandler.class);
        f.delete();
        return constructor.newInstance(h);
    }
    private static String generateSrc(Class<?> interfaces){
        StringBuffer sb = new StringBuffer();
        sb.append("package myProxyPattern;").append(ln)
                .append("import java.lang.reflect.Method;").append(ln)
                .append("public class $Proxy0 implements ").append(interfaces.getName()).append("{").append(ln)
                .append("GPInvocationHandler h;").append(ln)
                .append("public $Proxy0(GPInvocationHandler h){").append(ln)
                .append("this.h = h;").append(ln)
                .append("}");
        for (Method m : interfaces.getMethods()){
            sb.append("public "+m.getReturnType().getName()+" "+m.getName()+"(){"+ln);
            sb.append("try{"+ln);
            sb.append("Method m = "+interfaces.getName()+".class.getMethod(\""+m.getName()+"\",new Class[]{});"+ln);
            sb.append("this.h.invoke(this,m,null);"+ln);
            sb.append("}catch(Throwable e){e.printStackTrace();}"+ln);
            sb.append("}"+ln);
        }
        sb.append("}");

        return sb.toString();
    }
}
