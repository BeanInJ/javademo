package socket01;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/*
 * @Author BeanInJ
 * @Date 11:03 2021/2/22
 * 客户端
 **/
public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",7890);
        OutputStream o = socket.getOutputStream();                     // 获取输出流
        o.write("你好，服务器".getBytes());
        socket.close();
    }
}
