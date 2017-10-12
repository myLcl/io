package aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
   
//这里的参数受实际调用它的函数决定。本例是服务端socket.accetp调用决定  
public class Writer implements CompletionHandler<Integer, AsynchronousSocketChannel> {
    private ByteBuffer byteBuffer;

    public Writer(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public void completed(Integer result, AsynchronousSocketChannel socketChannel) {
        byteBuffer.clear();
        socketChannel.read(byteBuffer, socketChannel, new Reader(byteBuffer));
    }

    public void failed(Throwable exc, AsynchronousSocketChannel socketChannel) {
        throw new RuntimeException(exc);
    }
}
