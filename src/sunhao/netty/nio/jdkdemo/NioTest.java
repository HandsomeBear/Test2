package sunhao.netty.nio.jdkdemo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class NioTest {

	static NioDemo nioDemo = null;
	
	@BeforeClass
	public static void beforeClass(){
		nioDemo = new NioDemo();
	}
	
	//获取Channel的三种方式，且简单操作
	@Test
	public void testGetChannel() throws Exception{
		String filePath = "g:/nio/test.txt";
		nioDemo.getChannel(filePath);
	}
	
	//读取中文
	@Test
	public void testReadChin() throws Exception{
		String filePath = "g:/nio/test.txt";
		nioDemo.readChin(filePath);
	}
	
	//Channel之间的数据传输
	@Test
	public void testTransferFrom() throws Exception{
		nioDemo.transferFrom();
	}
	
	//Channel之间的数据传输
	@Test
	public void testTransferTo() throws Exception{
		nioDemo.transferTo();
	}
	
	//标记/复位测试
	@Test
	public void testMark() throws Exception{
		nioDemo.mark();
	}
	
	//字节读取顺序
	@Test
	public void testReadOrder() throws Exception{
		nioDemo.readOrder();
	}
	
	//创建视图
	@Test
	public void testAsBuffer() throws Exception{
		nioDemo.asBuffer();
	}
	
	//只读缓冲区
	@Test
	public void testReadOnly() throws Exception{
		nioDemo.readOnly();
	}
	
	//文件锁
	@Test
	public void testFileLock() throws Exception{
		String filePath = "g:/nio/test.txt";
		nioDemo.fileLock(filePath);
	}
	
	//虚拟内存映射，释放问题
	@Test
	public void testMapMemeryBuffer() throws Exception{
		nioDemo.MapMemeryBuffer();
	}
	
	@AfterClass
	public static void afterClass(){
		nioDemo = null;
	}
}
