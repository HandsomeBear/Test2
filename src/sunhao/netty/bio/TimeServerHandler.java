package sunhao.netty.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeServerHandler implements Runnable{

	private static final Logger logger  = LoggerFactory.getLogger(TimeServerHandler.class);
	
	private Socket socket;
	
	public TimeServerHandler(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {
		
		BufferedReader in = null;
		PrintWriter out = null;
		try{
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(),true);
			String currentTime = null;
			String body = null;
			while(true){
				body = in.readLine();
				if(body == null){
					break;
				}
				logger.info("The time server receive order:"+body);
				
				currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
				
				out.print(currentTime);
			}
		}catch(Exception e){
			if(in != null){
				try{
					in.close();
				}catch(Exception e1){
					logger.error("Close Stream Error",e1);
				}
			}
			if(out != null){
				out.close();
				out = null;
			}
			if(socket != null){
				try{
					socket.close();
				}catch(Exception e2){
					logger.error("Close Socket Error",e2);
				}
				socket = null;
			}
		}
	}
	
	
}
