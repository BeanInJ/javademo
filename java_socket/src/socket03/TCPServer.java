package socket03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// BS版本的TCP服务器
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Socket socket = server.accept();
                        // 获取输入流
                        InputStream                        is = socket.getInputStream();

                        // 网络输入流转为字符串缓冲流
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        // 读取客户端请求信息的第一行
                        String line = br.readLine();
                        // 利用空格切割出地址
                        String[] arr = line.split(" ");
                        // 把路径最前面的"/"去掉
                        String path = arr[1].substring(1);
                        System.out.println(path);
                        // 读取对应路径下的文件
                        FileInputStream fileInputStream = new FileInputStream(path);
                        // 写入客户端
                        OutputStream os = socket.getOutputStream();
                        // 写入http相应头(固定写法)
                        os.write("HTTP/1.1 200 OK\r\n".getBytes());
                        os.write("Content-Type:text/html\r\n".getBytes());
                        os.write("\r\n".getBytes());

                        int len = 0;
                        byte[] bytes = new byte[1024];
                        while ((len = fileInputStream.read(bytes))!=-1){
                            os.write(bytes,0,len);
                        }

                        fileInputStream.close();
                        socket.close();
                    }catch (Exception e){
                        System.out.println(e);
                    };
                }
            });
        }

    }
}
