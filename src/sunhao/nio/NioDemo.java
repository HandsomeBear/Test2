package sunhao.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioDemo {

	private static final Logger logger = LoggerFactory.getLogger(NioDemo.class);

	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月20日 下午2:19:41
	 * @Description:获取File Channel的3种方式
	 * @param filePath
	 */
	public void getChannel(String filePath) {
		logger.info("the three ways to get file channel:");

		FileInputStream in = null;
		FileOutputStream out = null;
		FileChannel fileChannel = null;
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			logger.info("the first:");
			in = new FileInputStream(filePath);
			fileChannel = in.getChannel();
			int byteReaded = fileChannel.read(buffer);
			while (byteReaded != -1) {
				buffer.flip();
				while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get() + "");
				}
				buffer.clear();
				byteReaded = fileChannel.read(buffer);
			}
			fileChannel.close();

			System.out.println();

			logger.info("the second:");
			out = new FileOutputStream(filePath, true);
			fileChannel = out.getChannel();
			buffer.clear();
			buffer.put("test".getBytes());
			buffer.flip();
			while (buffer.hasRemaining()) {
				fileChannel.write(buffer); // 无法保证一次会写入多少字节
			}
			fileChannel.close();

			System.out.println();

			logger.info("the third:");
			RandomAccessFile raf = new RandomAccessFile(filePath, "rwd");
			fileChannel = raf.getChannel();
			buffer.clear();
			int byteReadeds = fileChannel.read(buffer);
			while (byteReadeds != -1) {
				buffer.flip();
				while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get());
				}
				buffer.clear();
				byteReadeds = fileChannel.read(buffer);
			}
			fileChannel.close();
			raf.close();
		} catch (Exception e) {
			logger.error("getChannel error", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				logger.error("close source error", e);
			}
		}

	}

	/**
	 * @author 孙浩
	 * @date 2017年3月20日 下午5:26:33
	 * @Description:读取中文
	 * @param filePath
	 */
	public void readChin(String filePath) {
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			FileChannel fileChannel = fis.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			CharBuffer charBuffer = CharBuffer.allocate(1024);
			int bytes = fileChannel.read(byteBuffer);
			while (bytes != -1) {
				byteBuffer.flip();
				decoder.decode(byteBuffer, charBuffer, false);
				charBuffer.flip();

				System.out.println(charBuffer);
				charBuffer.clear();
				byteBuffer.clear();
				bytes = fileChannel.read(byteBuffer);
			}
			if (fis != null) {
				fis.close();
			}
			fileChannel.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月20日 下午6:56:58
	 * @Description:Channel之间的通信
	 * @throws Exception
	 */
	public void transferFrom() throws Exception{
		RandomAccessFile fromFile = new RandomAccessFile("g:/nio/from.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("g:/nio/to.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		toChannel.transferFrom(fromChannel,position, count);
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月20日 下午6:57:13
	 * @Description:Channel之间的通信
	 * @throws Exception
	 */
	public void transferTo() throws Exception{
		RandomAccessFile fromFile = new RandomAccessFile("g:/nio/from.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("g:/nio/to.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		fromChannel.transferTo(position, count,toChannel);
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月21日 上午8:37:21
	 * @Description:mark() and reset()
	 * @throws Exception
	 */
	public void mark() throws Exception{
		String text = "aBcDeF";
		CharBuffer charBuffer = CharBuffer.allocate(48);
		charBuffer.put(text);
		charBuffer.flip();
		while(charBuffer.hasRemaining()){
			charBuffer.mark();
			char c1 = charBuffer.get();
			char c2 = charBuffer.get();
			charBuffer.reset();
			charBuffer.put(c2).put(c1);
		}
		
		charBuffer.rewind();
		while(charBuffer.hasRemaining()){
			System.out.print(charBuffer.get());
		}
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月21日 上午8:46:55
	 * @Description:字节读取顺序
	 * @throws Exception
	 */
	public void readOrder() throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.asCharBuffer().put("abcde");
		System.out.println("初始值："+Arrays.toString(buffer.array()));
		
		buffer.rewind();
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.asCharBuffer().put("abcde");
		System.out.println("大端模式："+Arrays.toString(buffer.array()));
		
		buffer.rewind();
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.asCharBuffer().put("abcde");
		System.out.println("小端模式："+Arrays.toString(buffer.array()));
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月21日 上午11:53:01
	 * @Description:创建视图
	 * @throws Exception
	 */
	public void asBuffer() throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put("12345".getBytes());
		System.out.println(Arrays.toString(buffer.array()));
		
		buffer.rewind();
		buffer.asCharBuffer().put("12345");
		System.out.println(Arrays.toString(buffer.array()));
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月21日 上午11:58:33
	 * @Description:只读
	 * @throws Exception
	 */
	public void readOnly() throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put("12345".getBytes());
		ByteBuffer rBuffer = buffer.asReadOnlyBuffer();
		
		buffer.flip();
		while(buffer.hasRemaining()){
			System.out.print((char)buffer.get());
		}
		
		rBuffer.rewind();     //共享pisition,limit,capacity
		while(rBuffer.hasRemaining()){
			System.out.print((char)rBuffer.get());
		}
		
		rBuffer.position(1);
		rBuffer.put("2".getBytes());
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月21日 下午12:49:17
	 * @Description:文件锁
	 * @param filePath
	 */
	public void fileLock(String filePath) {
		RandomAccessFile raf = null;
		FileLock lock = null;
		FileChannel channel = null;
		try{
			raf = new RandomAccessFile(filePath, "rw");
			channel = raf.getChannel();
			lock = channel.tryLock();
			if(lock!=null){
				//services
				TimeUnit.SECONDS.sleep(5);
			}
		}catch(Exception e){
			logger.error("fileLock error",e);
		}finally{
			try {
				if(lock!=null){
					lock.release();
				}
				if(channel!=null){
					channel.close();
				}
				if(raf!=null){
					raf.close();
				}
			} catch (IOException e) {
				logger.error("release error",e);
			}
		}
		
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月21日 下午1:45:41
	 * @Description:文件映射
	 */
	public void MapMemeryBuffer(){
		ByteBuffer byteBuffer = ByteBuffer.allocate(350*1024*1024);
		byte[] bbb = new byte[1024];
		FileInputStream fis = null;
		FileChannel fc = null;
		try{
			fis = new FileInputStream("g:/VMware-workstation-full-11.0.0-2305329.exe");
			fc = fis.getChannel();
			long start = System.currentTimeMillis();
//			fc.read(byteBuffer);//
			
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			
			logger.info("File-Size:"+fc.size()/1024);
			long end = System.currentTimeMillis();
			logger.info("Read time:"+(end-start)+"ms");
			
		}catch(Exception e){
			logger.error("error",e);
		}finally{
			try{
				if(fc!=null){
					fc.close();
				}
				if(fis!=null){
					fis.close();
				}
			}catch(Exception e){}
		}
	}
}
