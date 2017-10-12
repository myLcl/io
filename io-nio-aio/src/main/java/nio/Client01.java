package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client01 {
	static SocketChannel channel;

	public static void main(String[] args) throws Exception {
		//1 打开通道
		channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("127.0.0.1", 12345));
		
		//2 循环连接服务端
		while (!channel.finishConnect()) {
			//连接不上可以做其他事情
		}

		//3 与服务器通信
		write();
		read();
	}
	
	/**
	 * 向服务器发送数据
	 */
	public static void write() throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put("我是客户端".getBytes());
		buffer.flip();
		channel.write(buffer);
		buffer.clear();
		Thread.sleep(2000);
	}
	
	/**
	 * 接受服务端的数据
	 */
	public static void read()  throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		StringBuffer sb = new StringBuffer();
		int read = channel.read(buffer);
		if(read > 0) {
			buffer.flip();
			byte[] array = buffer.array();
			buffer.clear();
			sb.append("客户端收到信息---" + new String(array));
		}
		System.out.println(sb.toString());
			
	}
}
