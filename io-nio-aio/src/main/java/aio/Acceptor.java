package aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
   
//这里的参数受实际调用它的函数决定。本例是服务端socket.accetp调用决定  
public class Acceptor implements CompletionHandler <AsynchronousSocketChannel, AsynchronousServerSocketChannel>{   
    
    //注意第一个是客户端socket，第二个是服户端socket
    public void completed(AsynchronousSocketChannel socket, AsynchronousServerSocketChannel attachment){  
        try {   
            System.out.println("AioAcceptHandler.completed called");  
            attachment.accept(attachment, this);   
            System.out.println("有客户端连接:" + socket.getRemoteAddress().toString());   
            
            ByteBuffer clientBuffer = ByteBuffer.allocate(1024); 
            socket.read(clientBuffer, socket, new Reader(clientBuffer));
//            startRead(socket);      
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }   
   
    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment)   
    {   
        exc.printStackTrace();   
    }   
      
//    //不是CompletionHandler的方法  
//    public void startRead(AsynchronousSocketChannel socket) {   
//        ByteBuffer clientBuffer = ByteBuffer.allocate(1024);   
//        //read的原型是  
//        //read(ByteBuffer dst, A attachment,  
//        //    CompletionHandler<Integer,? super A> handler)   
//        //即它的操作处理器，的A型，是实际调用read的第二个参数，即clientBuffer。  
//        // V型是存有read的连接情况的参数  
//        
////        AioReadHandler rd=new AioReadHandler(socket);  
////        socket.read(clientBuffer, clientBuffer, rd);   
//        try {               
//        } catch (Exception e) {   
//            e.printStackTrace();   
//        }   
//    }   
   
}  
