package sunhao.other;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@SuppressWarnings("unused")
public class LockS {

	public static void main(String[] args) throws Exception{
		test04();
	}
	
	/**
	 * @author 孙浩
	 * @date 2017年3月14日 上午9:50:14
	 * @Description:许可默认是被占用的，调用park()进入阻塞状态
	 */
	private static void test01(){
		System.out.println("test01-before");
		LockSupport.park();
		System.out.println("test01-after");
	}
	
	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月14日 上午9:53:42
	 * @Description:多次unpark()，只有一次park()也是正常的
	 */
	private static void test02(){
		Thread thread = Thread.currentThread();
		LockSupport.unpark(thread);
		System.out.println("test02-before");
		LockSupport.unpark(thread);
		System.out.println("test02-middle");
		LockSupport.park();
		System.out.println("test02-after");
	}
	
	/**
	 * @author 孙浩
	 * @date 2017年3月14日 上午9:56:40
	 * @Description:LockSupport是不可重入的，如果连续调用park()，线程会一直阻塞下去
	 */
	private static void test03(){
		Thread thread = Thread.currentThread();
		
		LockSupport.unpark(thread);
		
		System.out.println("test03-before");
		LockSupport.park();
		System.out.println("test03-middle");
		LockSupport.park();
		System.out.println("test03-after");
	}
	
	private static void test04() throws Exception{
		Thread t = new Thread(new Runnable(){
			private int count;
			public void run(){
				long start = System.currentTimeMillis();
				long end = 0;
				while((end-start) <= 1000){
					count++;
					end = System.currentTimeMillis();
				}
				System.out.println("After 1 second,count = "+count);
				LockSupport.park();
				System.out.println("Thread.over."+Thread.currentThread().isInterrupted());
			}
		});
		t.start();
		
		TimeUnit.SECONDS.sleep(2);
		
//		LockSupport.unpark(t);
		t.interrupt();
		
		System.out.println("Over");
	}
}
