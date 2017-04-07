package sunhao.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClientHandle implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(TimeClientHandle.class);
	
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	
	public TimeClientHandle(String host,int port) {
		this.host = host == null?"127.0.0.1":host;
		this.port = port;
		try{
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		}catch(Exception e){
			logger.error("Start Error",e);
			System.exit(1);
		}
	}
	
	@Override
	public void run(){
		try{
			doConnect();
		}catch(IOException e){
			logger.error("Connect Error",e);
			System.exit(1);
		}
	}
	
	public void doConnect() throws IOException{
		
		if(socketChannel.connect(new InetSocketAddress(host, port))){
			socketChannel.register(selector, SelectionKey.OP_READ);
			doWrite(socketChannel);
		}else{
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}
	
	public void doWrite(SocketChannel sc) throws IOException{
		 byte[] req = "QUERY TIME ORDER".getBytes();
		 ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		 writeBuffer.put(req);
		 writeBuffer.flip();
		 if(!writeBuffer.hasRemaining()){
			 logger.info("Send order 2 server succeed");
		 }
	}
}
