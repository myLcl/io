package cs01;

import java.io.*;
import java.net.Socket;

/**
 */
public class Client01 {
    public static Socket socket;
    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost",9999);
        BufferedReader read = getRead(socket);
        PrintWriter writer = getWriter(socket);

        //与服务端交换数据
        writer.println("来自客户端的消息");
        System.out.println(read.readLine());

        socket.close();

    }

    //获得输出流
    public static PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        return  new PrintWriter(outputStream,true);
    }

    //获得输入流
    public static BufferedReader getRead(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}

