package sunhao.jedisdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {

	private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);
	
	public static void main(String[] args) {
		InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream("jedis.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			for(Object o : p.keySet()){
				String key = (String)o;
				logger.info(key + ":" + p.getProperty(key));
			}
		} catch (IOException e) {
			logger.error("load properties failed",e);
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
