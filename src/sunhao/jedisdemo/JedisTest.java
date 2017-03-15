package sunhao.jedisdemo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JedisTest {

	JedisDemo jedisDemo = null;
	
	@BeforeClass
	public void beforeClass(){
		jedisDemo = new JedisDemo();
	}
	
	@Test
	public void helloTest(){
		jedisDemo.hello();
	}
	
	@AfterClass
	public void afterClass(){
		jedisDemo = null;
	}
}
