package sunhao.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeServer {

	private static final Logger logger  = LoggerFactory.getLogger(TimeServer.class);
	
	public static void main(String[] args) throws IOException{
		
		int port = 8080;
		if(args != null && args.length > 0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e){
				logger.error("Use Default Port...");
			}
		}
		
		ServerSocket server = null;
		try{
			server = new ServerSocket(port);
			
			logger.info("The time server is started in port:"+port);
			
			Socket socket = null;
			while(true){
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		}finally{
			if(server != null){
				logger.info("The time server close");
				server.close();
				server = null;
			}
		}
	}
}
