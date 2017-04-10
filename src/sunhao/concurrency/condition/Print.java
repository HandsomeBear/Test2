package sunhao.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Print {

	static class NumberWrapper{
		public int value;
	}
	
	public static void main(String[] args) {
		final ReentrantLock lock = new ReentrantLock();
		final Condition reach3 = lock.newCondition();
		final Condition reach6 = lock.newCondition();
		final NumberWrapper num = new NumberWrapper();
		
		new Thread(new Runnable(){
			@Override
			public void run(){
				lock.lock();
				try{
					System.out.println("ThreadA start write");
					while(num.value<=3){
						System.out.println(num.value);
						num.value++;
					}
					reach3.signal();
				}finally{
					lock.unlock();
				}
				lock.lock();
				try{
					reach6.await();
					System.out.println("ThreadA start write");
					while(num.value<=9){
						System.out.println(num.value);
						num.value++;
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run(){
				lock.lock();
				try{
					while(num.value<=3){
						reach3.await();
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
				lock.lock();
				System.out.println("ThreadB start write");
				try{
					while(num.value<=6){
						System.out.println(num.value);
						num.value++;
					}
					reach6.signal();
				}finally{
					lock.unlock();
				}
			}
		}).start();
	}
}
