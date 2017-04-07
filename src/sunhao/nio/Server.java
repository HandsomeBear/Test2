package sunhao.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
	
	private static final Logger logger = LoggerFactory.getLogger(Server.class);

	private int port = 8081;
	//解码buffer
	private Charset charset = Charset.forName("UTF-8");
	//接收数据缓冲区
	private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
	//发送数据缓冲区
	private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
	//映射客户端channel
	private Map<String,SocketChannel> clientsMap = new HashMap<String,SocketChannel>();
	private static Selector selector;
	
	public Server(int port){
		this.port = port;
		try{
			init();
		}catch(Exception e){
			logger.error("init error",e);
		}
	}
	
	public void init() throws IOException{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));
		
		/**
		 * 启动服务器，注册accept事件
		 */
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		logger.info("Server start on port : " + port);
	}
	
	private void listen(){
		while(true){
			try{
				selector.select();//返回值为本次触发的事件数
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				for(SelectionKey key : selectionKeys){
					handle(key);
				}
				selectionKeys.clear();
			}catch(Exception e){
				logger.error("listen error",e);
			}
		}
	}
	
	private void handle(SelectionKey selectionKey) throws IOException{
		ServerSocketChannel server = null;
		SocketChannel client = null;
		String receiveText = null;
		int count = 0;
		if(selectionKey.isAcceptable()){
			/**
			 * 客户端请求连接事件
			 * serverSocket为该客户端建立连接socket连接，将此socket注册READ事件，监听客户端输入
			 * READ事件：当客户端发来数据，并已被服务器控制线程正确读取时，触发该事件
			 */
			server = (ServerSocketChannel)selectionKey.channel();
			client = server.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		}else if(selectionKey.isReadable()){
			/**
			 * READ事件，收到客户端发送数据，继续监听
			 */
			client = (SocketChannel)selectionKey.channel();
			rBuffer.clear();
			count = client.read(rBuffer);
			if(count > 0){
				rBuffer.flip();
				receiveText = String.valueOf(charset.decode(rBuffer).array());
				logger.info(client.toString()+":"+receiveText);
				dispatch(client,receiveText);
				client = (SocketChannel)selectionKey.channel();
				client.register(selector, SelectionKey.OP_READ);
			}
		}
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月20日 下午7:25:30
	 * @Description:把当前客户端推送到其他客户端
	 * @param client
	 * @param info
	 * @throws IOException
	 */
	private void dispatch(SocketChannel client,String info) throws IOException{
		Socket s = client.socket();
		String name = "["+s.getInetAddress().toString().substring(1)+":"+Integer.toHexString(client.hashCode())+"]";
		if(!clientsMap.isEmpty()){
			for(Map.Entry<String, SocketChannel> entry : clientsMap.entrySet()){
				SocketChannel temp = entry.getValue();
				if(!client.equals(temp)){
					sBuffer.clear();
					sBuffer.put((name+":"+info).getBytes());
					sBuffer.flip();
					//输出到通道
					temp.write(sBuffer);
				}
			}
		}
		clientsMap.put(name, client);
	}
	
	public static void main(String[] args) {
		Server server = new Server(8081);
		server.listen();
	}
}
