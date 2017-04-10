package sunhao.netty.nio.jdkdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

	private static final Logger logger  = LoggerFactory.getLogger(Client.class);
	
	private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
	private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
	
	private Charset charset = Charset.forName("UTF-8");
	
	private InetSocketAddress SERVER;
	private static Selector selector;
	private static SocketChannel client;
	private static String receiveText;
	private static String sendText;
	private static int count = 0;
	
	public Client(int port){
		SERVER = new InetSocketAddress("localhost",port);
		init();
	}
	
	private void init(){
		try{
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(SERVER);
			
			while(true){
				selector.select();
				Set<SelectionKey> keySet = selector.selectedKeys();
				for(final SelectionKey key : keySet){
					handler(key);
				}
				keySet.clear();
			}
		}catch(Exception e){
			logger.error("init error",e);
		}
	}
	
	private void handler(SelectionKey selectionKey) throws IOException{
		if(selectionKey.isConnectable()){
			client = (SocketChannel)selectionKey.channel();
			if(client.isConnectionPending()){
				client.finishConnect();
				logger.info("connect success!");
				sBuffer.clear();
				sBuffer.put((new Date().toString()+" connected!").getBytes());
				sBuffer.flip();
				client.write(sBuffer);//发送消息至服务器
				
				new Thread(){
					@Override
					public void run(){
						while(true){
							try{
								sBuffer.clear();
								InputStreamReader input = new InputStreamReader(System.in);
								BufferedReader br = new BufferedReader(input);
								sendText = br.readLine();
								sBuffer.put(sendText.getBytes());
								sBuffer.flip();
								client.write(sBuffer);
							}catch(Exception e){
								logger.info("write error",e);
							}
						}
					}
				}.start();
				client.register(selector, SelectionKey.OP_READ);
			}
		}else if(selectionKey.isReadable()){
			client = (SocketChannel)selectionKey.channel();
			rBuffer.clear();
			count = client.read(rBuffer);
			if(count > 0){
				rBuffer.flip();
				receiveText = String.valueOf(charset.decode(rBuffer).array());
				logger.info(receiveText);
				client = (SocketChannel)selectionKey.channel();
				client.register(selector, SelectionKey.OP_READ);
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Client client = new Client(8081);
	}
	
}
