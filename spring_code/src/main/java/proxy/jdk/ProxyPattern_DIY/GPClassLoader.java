package proxy.jdk.ProxyPattern_DIY;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// 代码生成、编译重新动态加载到jvm
public class GPClassLoader extends ClassLoader{
    private File baseDir;
    public GPClassLoader(){
        String path = GPClassLoader.class.getResource("").getPath();
        this.baseDir = new File(path);
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = GPClassLoader.class.getPackage().getName() + "." + name;
        if (baseDir != null) {
            File classFile = new File(baseDir, name.replaceAll("\\.", "/") + ".class");
            if (classFile.exists()) {
                FileInputStream fis = null;
                ByteArrayOutputStream out = null;
                try {
                    fis = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len=fis.read(bytes))!=-1){
                        out.write(bytes,0,len);
                    }
                    return defineClass(className,out.toByteArray(),0, out.size());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                    if (null != fis) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (null!=out){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    classFile.delete();
                }
            }
        }
        return null;
    }
}
