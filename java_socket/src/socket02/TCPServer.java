package socket02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

// 服务器
public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while (true){
            Socket socket = server.accept();
            // 有一个客户端上传文件，就开启一个线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取输入流
                        InputStream is = socket.getInputStream();

                        // 判断文件夹是否存在
                        File file = new File("d:\\upload");
                        if(!file.exists()){
                            file.mkdirs();
                        }

                        // 自定义一个命名规则，防止同名文件覆盖
                        String fileName = "itcast"+System.currentTimeMillis()+new Random().nextInt(999999)+".jpg";

                        // 将文件数据保存到磁盘上
                        FileOutputStream fos = new FileOutputStream(file+"\\"+fileName);
                        int len = 0;
                        byte[] bytes = new byte[1024];
                        while ((len = is.read(bytes))!=-1){
                            fos.write(bytes,0,len);
                        }
                        socket.getOutputStream().write("上传成功".getBytes());
                        fos.close();
                        socket.close();
                    }catch (IOException e){
                        System.out.println(e);
                    }
                }
            }).start();


        }
    }
}
