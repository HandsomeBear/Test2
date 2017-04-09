package sunhao.netty.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeServer {

	private static final Logger logger = LoggerFactory.getLogger(TimeServer.class);
	
	public static void main(String[] args){
		
		int port = 8080;
		if(args != null && args.length > 0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e){
				logger.error("Use Default Port...");
			}
		}
		
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		
		new Thread(timeServer).start();
	}
}
