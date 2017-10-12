package nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务器
 */
public class Service {
	static Selector selector;
	public static void main(String[] args) throws Exception {
		//1  创建连接通道 
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ServerSocket socket = ssc.socket();
		socket.bind(new InetSocketAddress(12345));
		//该方法继承者父类,并且只有非阻塞模式的通道才能注册到选择器
		ssc.configureBlocking(false); 
		
		//2 创建选择器,注册到想要监控的信道上,在注册过程中指出该信道可以进行“accept”操作
		 selector = Selector.open();
		//一个通道可以注册到多个选择器中
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		
		//获得可用信道 和 信道中注册的事件
		while(true){
			// >0 代表有就绪信道
			if(selector.select(3000) == 0) continue;
			
			Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
			while(iter.hasNext()) {
				SelectionKey key = iter.next();
				
				if(key.isAcceptable()) accept(key);
				if(key.isValid() && key.isReadable()) read(key);
				if(key.isValid() && key.isWritable()) write(key);
				
				iter.remove();
			}
		}
	}
	
	/**
	 * 连接事件：接受客户端的连接
	 */
	public static void accept(SelectionKey key) throws Exception{
		/*
		 * channel()方法返回注册时用来创建的Channel，该Channel是一个ServerSocketChannel，
		 * 因为这是我们注册的唯一一种支持accept操作的信道
		 * accept()方法为传入的连接返回一个SocketChannel实例。
		 */
		ServerSocketChannel serverChannel =(ServerSocketChannel)key.channel();
		SocketChannel channel = serverChannel.accept();
		channel.configureBlocking(false);
		
		//注册读事件和添加缓冲区
		channel.register(selector, SelectionKey.OP_READ);
	}
	
	/**
	 * 读事件: 读客户端的数据
	 */
	public static void read(SelectionKey key)  throws Exception{
		//1 根据其支持数据读取操作可知，这是一个SocketChannel
		SocketChannel channel =(SocketChannel)key.channel();
		
		try {
			//2 把通道中的数据读取到缓冲区
			 ByteBuffer buffer = ByteBuffer.allocate(1024);
			 int read = channel.read(buffer);
			
			/*如果read()方法返回-1，则表示底层连接已经关闭，此时需要关闭信道。
			 * 关闭信道时，将从选择器的各种集合中移除与该信道关联的键。
			 */
			if (read == -1) channel.close();
			
			StringBuilder sb = new StringBuilder();
			if(read > 0) {
				buffer.flip();
				byte[] data = buffer.array();
				buffer.clear();
				sb.append("服务端收到信息--" +new String(data));
			}
			System.out.println(sb.toString());
			
			//注册写事件
			key.interestOps(SelectionKey.OP_WRITE);
			
		} catch (Exception e) {
			key.cancel();
			channel.socket().close();
			channel.close();
			return;
		}
	}
	
	/**
	 * 写事件: 向客户端写信息
	 */
	public static void write(SelectionKey key)  throws Exception{
		SocketChannel channel =(SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//向缓冲区写数据
		buffer.put("我是服务端".getBytes());
		buffer.flip();
		channel.write(buffer);
		buffer.clear();
	}
	
}
