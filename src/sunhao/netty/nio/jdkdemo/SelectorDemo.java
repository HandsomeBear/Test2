package sunhao.netty.nio.jdkdemo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectorDemo {
	
	private static final int BUF_SIZE = 256;
	private static final int TIMEOUT = 3000;

	public static void main(String[] args) throws Exception{
		
		//打开服务端Socket
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		
		//打开Selector
		Selector selector = Selector.open();
		
		//服务端监听8080端口，并配置为非阻塞模式
		serverSocketChannel.socket().bind(new InetSocketAddress(8080));
		serverSocketChannel.configureBlocking(false);
		
		/**
		 * 将channel注册到selector中，
		 * 通常我们都是先注册一个OP_ACCEPT事件，然后accept事件到来时，再将这个channel的OP_READ
		 * 事件注册到selector中
		 */
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true){
			//通过调用select()方法，阻塞的等待channel I/O操作
			if(selector.select(TIMEOUT) == 0){
				System.out.print(".");
				continue;
			}
			
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
			
			while(keyIterator.hasNext()){
				SelectionKey key = keyIterator.next();
				
				//当获取一个SelectionKey后，就要将它删除，表示我们已经对这个IO事件进行了处理
				if(key.isAcceptable()){
					//当OP_ACCEPT事件到来时，我们就有从ServerSocketChannel中获取一个SocketChannel，
					//代表客户端的连接
					//注意：在OP_ACCEPT中，从key.channel()返回的channel是ServerSocketChannel
					//而在OP_WRITE和OP_READ中，从key.channel()返回的是SocketChannel
					SocketChannel clientChannel = ((ServerSocketChannel)key.channel()).accept();
					clientChannel.configureBlocking(false);
					
					//在OP_ACCEPT到来时，再将这个Channel的OP_READ注册到Selector中
					//注意，这里我们如果没有设置OP_READ的话，即interest set仍然是OP_CONNECT的话，那么select()方法一直直接返回
					clientChannel.register(selector, SelectionKey.OP_READ,ByteBuffer.allocate(BUF_SIZE));
				}
				
				if(key.isReadable()){
					SocketChannel clientChannel = (SocketChannel)key.channel();
					ByteBuffer buf = (ByteBuffer)key.attachment();
					long bytesRead = clientChannel.read(buf);
					if(bytesRead == -1){
						clientChannel.close();
					}else if(bytesRead > 0){
						key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
						System.out.println("Get Data Length :"+bytesRead);
					}
				}
				
				if(key.isValid()&&key.isWritable()){
					ByteBuffer buf = (ByteBuffer)key.attachment();
					buf.flip();
					SocketChannel clientChannel = (SocketChannel)key.channel();
					clientChannel.write(buf);
					if(!buf.hasRemaining()){
						key.interestOps(SelectionKey.OP_READ);
					}
					buf.compact();
				}
				
				keyIterator.remove();
			}
		}
	}
}
