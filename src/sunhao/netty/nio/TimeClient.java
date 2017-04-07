package sunhao.netty.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClient {

	private static final Logger logger = LoggerFactory.getLogger(TimeClient.class);
	
	public static void main(String[] args){
		
		int port = 8080;
		if(args != null && args.length > 0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e){
				logger.error("Use Default Port...");
			}
		}
		
		new Thread().start();
	}
}
