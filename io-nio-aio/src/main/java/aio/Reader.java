package aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class Reader implements  CompletionHandler<Integer, AsynchronousSocketChannel> {
	private ByteBuffer byteBuffer;

    public Reader(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public void completed(Integer result, AsynchronousSocketChannel socketChannel) {
        byteBuffer.flip();
        if (result != null && result < 0) {
            try {
                socketChannel.close();
            } catch (IOException e) {
            }
            return;
        }
        try {
            System.out.println(socketChannel.getLocalAddress().toString());
            System.out.println(socketChannel.getRemoteAddress().toString());
            socketChannel.write(byteBuffer, socketChannel, new Writer(byteBuffer));
        } catch (IOException e) {
        }
        ByteBuffer duplicate = byteBuffer.duplicate();
        CharBuffer decode = Charset.defaultCharset().decode(duplicate);
        System.out.println(decode.toString());
    }


    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        throw new RuntimeException(exc);
    }

}
