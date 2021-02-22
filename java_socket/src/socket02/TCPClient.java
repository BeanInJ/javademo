package socket02;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// 客户端
public class TCPClient {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("d:\\1.png");
        Socket socket = new Socket("127.0.0.1",8888);
        OutputStream os = socket.getOutputStream();

        // 读取本地文件
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes))!=-1){
            os.write(bytes,0,len);
        }

        // fis.read(bytes)是读取本地文件，结束标记是读取到-1
        // socket.shutdownOutput(); 就是结束标记
        socket.shutdownOutput();


        // 读取服务器回写数据
        InputStream is = socket.getInputStream();
        while ((len = is.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }

        fis.close();
        socket.close();
    }
}
