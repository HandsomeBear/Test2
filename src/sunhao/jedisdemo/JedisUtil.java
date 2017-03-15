package sunhao.jedisdemo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {

	private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);
	
	//可用连接实例的格式，默认为8；
	//如果为-1，表示不限制，如果jedisPool已经分配了MAX_ACTIVE个jedis实例，则exhausted;
	private static int MAX_ACTIVE = 1024;
	
	//控制一个jedisPool最多有多少个状态为idle的jedis实例，默认值是8;
	private static int MAX_IDLE = 200;
	
	//等待可用连接的最大时间，单位毫秒，默认值为-1表示永不超时，如果超出等待时间，则直接抛出JedisConnectionException
	private static long MAX_WAIT = 10000;
	
	private static int TIME_OUT = 10000;
	
	private static int RETRY_NUM = 5;
	
	//在borrow一个jedis实例时，是否提前进行validate验证；如果为true，则得到的jedis实例均是可用的
	private static boolean TEST_ON_BORROW = true;
	
	private static boolean TEST_ON_RETURN = true;
	
	private JedisUtil(){
		
	}
	
	private static Map<String,JedisPool> maps = new HashMap<String,JedisPool>();
	
	private static JedisPool getPool(String ip,int port){
		logger.info("Execute getPool()...");
		
		String key = ip + ":" + port;
		JedisPool pool = null;
		if(!maps.containsKey(key)){
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWait(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			config.setTestOnReturn(TEST_ON_RETURN);
			try{
				pool = new JedisPool(config, ip, port, TIME_OUT);
			}catch(Exception e){
				logger.error("getPool() Failed",e);
			}
		}else{
			pool = maps.get(key);
		}
		return pool;
	}
	
	private static class RedisUtilHolder{
		private static JedisUtil instance = new JedisUtil();
	}
	
	public static JedisUtil getInstance(){
		logger.info("Executr getInstance()...");
		
		return RedisUtilHolder.instance;
	}
	
	public Jedis getJedis(String ip,int port){
		logger.info("Execute getJedis()...");
		
		Jedis jedis = null;
		int count = 0;
		do{
			try{
				jedis = getPool(ip,port).getResource();
			}catch(Exception e){
				logger.error("get redis master1 failed! ",e);
				//销毁对象
				getPool(ip,port).returnBrokenResource(jedis);
			}
			count++;
		}while(jedis==null&&count<RETRY_NUM);
		
		return jedis;
	}
	
	public void closeJedis(Jedis jedis,String ip,int port){
		logger.info("Execute closeJedis()...");
		
		if(jedis != null){
			getPool(ip,port).returnResource(jedis);
		}
	}
}
