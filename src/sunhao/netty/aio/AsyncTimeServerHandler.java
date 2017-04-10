package sunhao.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncTimeServerHandler implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(AsyncTimeServerHandler.class);
	
	@SuppressWarnings("unused")
	private int port;
	CountDownLatch latch;
	AsynchronousServerSocketChannel asynchronousServerSocketChannel;
	
	public AsyncTimeServerHandler(int port){
		
		this.port = port;
		try{
			asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
			logger.info("The time sevrer is started in port:"+port);
		}catch(IOException e){
			logger.error("Start Error",e);
		}
	}
	
	@Override
	public void run(){
		
		latch = new CountDownLatch(1);
		doAccept();
		try{
			latch.await();
		}catch(InterruptedException e){
			logger.error("Interrupted",e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void doAccept() {
		asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
	}
}
