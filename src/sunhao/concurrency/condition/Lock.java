package sunhao.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock {
	
	Object data;
	private volatile boolean cacheValid;
	final ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock();
	
	public void processCacheData(){
		lock1.readLock().lock();
		if(!cacheValid){
			lock1.readLock().unlock();
			lock1.writeLock().lock();
			try{
				if(!cacheValid){
					data = null;//读取数据操作
					cacheValid = true;
				}
				lock1.readLock().lock();
			}finally{
				lock1.writeLock().unlock();
			}
		}
		
		try{
			//使用缓存的数据
		}finally{
			lock1.readLock().unlock();
		}
	}
	
	//Condition的用法
	final ReentrantLock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	
	final Object[] items = new Object[20];
	int putPtr,takePtr,count;
	
	public void put(Object o) throws InterruptedException{
//		System.out.println("put->wait lock");
		lock.lock();
//		System.out.println("put->get lock");
		
		try{
			while(count==items.length){
				System.out.println("buffer full,please wait");
				notFull.await();
			}
			items[putPtr] = o;
			System.out.println("存放数据->:"+o);
			if(++putPtr==items.length){
				putPtr = 0;
			}
			++count;
			notEmpty.signal();
		}finally{
			lock.unlock();
		}
	}
	
	public Object take() throws InterruptedException{
//		System.out.println("take->wait lock");
		lock.lock();
//		System.out.println("take->get lock");
		
		try{
			while(count==0){
				System.out.println("no elements,please wait");
				notEmpty.await();
			}
			Object o = items[takePtr];
			System.out.println("读取数据:"+o);
			if(++takePtr==items.length){
				takePtr=0;
			}
			--count;
			notFull.signal();
			return o;
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final Lock buffer = new Lock();
		
		Thread thread1 = new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i = 0;i<10;i++){
					try{
						System.out.println("t1 run");
//						TimeUnit.SECONDS.sleep(1);
						buffer.put(Integer.valueOf(i));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i = 0;i<10;i++){
					try{
						Object val = buffer.take();
						System.out.println(val.toString());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		});
		
		thread1.start();
		thread2.start();
	}
}
