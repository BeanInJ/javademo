package socket01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @Author BeanInJ
 * @Date 11:10 2021/2/22
 * 服务端
 * 客户端发送消息过来，服务端获取到客户端对象、消息，通过输入流将消息接收到
 * 先运行服务端，再运行客户端就可以接收到消息：你好，服务器
 **/
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7890);
        Socket socket = server.accept();                      // 获取到客户端对象
        InputStream is = socket.getInputStream();             // 输入流对象
        byte[] bytes = new byte[1024];
        int l = is.read(bytes);                               // 返回字节的实际长度
        System.out.println(new String(bytes,0,l));

        OutputStream os = socket.getOutputStream();
        os.write("收到".getBytes());

        socket.close();
        server.close();
    }
}
