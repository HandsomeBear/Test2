package sunhao.netty.bio;

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
		
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try{
			socket = new Socket("127.0.0.1",port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("QUERY TIME ORDER");
			
			logger.info("Send order 2 to server succeed.");
			
			String resp = in.readLine();
			
			logger.info("Now is:"+resp);
		}catch(Exception e){
			//no need to deal
		}finally{
			if(out != null){
				out.close();
				out = null;
			}
			try{
				if(in != null){
					in.close();
				}
				in = null;
				if(socket != null){
					socket.close();
				}
				socket = null;
			}catch(Exception e1){
				logger.error("Close Resource Error",e1);
			}
		}
	}
}
