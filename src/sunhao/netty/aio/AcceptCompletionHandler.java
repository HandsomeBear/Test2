package sunhao.netty.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AcceptCompletionHandler implements CompletionHandler{

	private static final Logger logger = LoggerFactory.getLogger(AcceptCompletionHandler.class);
	
	@Override
	public void completed(Object result, Object attachment) {
		((AsyncTimeServerHandler)attachment).asynchronousServerSocketChannel.accept(attachment,this);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		AsynchronousSocketChannel result1 = (AsynchronousSocketChannel)result;
//		result1.read(buffer, buffer, new ReadCompletionHandler(result1));
	}

	@Override
	public void failed(Throwable t, Object attachment) {
		
		logger.error("Fail",t);
		((AsyncTimeServerHandler)attachment).latch.countDown();
	}

}
