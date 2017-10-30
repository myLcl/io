package cs01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 */
public class Server01 {
    private static ServerSocket serverSocket = null;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(9999);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress() + ":" + socket.getPort());
            BufferedReader read = getRead(socket);
            PrintWriter writer = getWriter(socket);

            //与客户端交换数据
            String msg = null;
            while ((msg = read.readLine()) != null) {
                System.out.println(msg);
                //输出到客户端
                writer.println("来自服务端的消息");
                break;
            }
            socket.close();
        }
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
