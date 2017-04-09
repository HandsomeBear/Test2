package sunhao.netty.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyTimeClientHandler extends ChannelInboundHandlerAdapter{

	private static final Logger logger = LoggerFactory.getLogger(NettyTimeClientHandler.class);
	
	private final ByteBuf firstMessage;
	
	public NettyTimeClientHandler(){
		byte[] req = "QUERY TIME ORDER".getBytes();
		firstMessage = Unpooled.buffer(req.length);
		firstMessage.writeBytes(req);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
		ctx.writeAndFlush(firstMessage);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
		ByteBuf buf = (ByteBuf)msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req,"UTF-8");
		
		logger.info("Now is:"+body);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		
		logger.warn("UnExcepted exception from downstream:"+cause.getMessage());
		ctx.close();
	}
}
