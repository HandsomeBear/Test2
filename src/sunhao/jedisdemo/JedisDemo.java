package sunhao.jedisdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class JedisDemo {
	private static final Logger logger = LoggerFactory.getLogger(JedisDemo.class);
	
	private static final String IP = "127.0.0.1";
	private static final int PORT = 6379;
	JedisUtil jedisUtil = JedisUtil.getInstance();

	public void hello(){
		Jedis jedis = jedisUtil.getJedis(IP, PORT);
		
		try{
			logger.info("set name sunhao...");
			jedis.set("name", "sunhao");
			String name = jedis.get("name");
			logger.info("get name : " + name);
			
			logger.info("<===========>");
			
			logger.info("append name...");
			jedis.append("name", "-test");
			name = jedis.get("name");
			logger.info("get name : " + name);
			
			logger.info("<===========>");
			
			logger.info("delete name...");
			jedis.del("name");
			name = jedis.get("name");
			logger.info("del name : " + name);
		}catch(Exception e){
			logger.error("execute hello ",e);
		}finally{
			jedisUtil.closeJedis(jedis, IP, PORT);
		}
	}
}
