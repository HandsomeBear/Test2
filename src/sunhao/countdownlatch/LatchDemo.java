package sunhao.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class LatchDemo {

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) throws Exception{
		CountDownLatch latch = new CountDownLatch(2);
		Worker worker1 = new Worker("张三",5000,latch);
		Worker worker2 = new Worker("李四",8000,latch);
		new Thread(worker1).start();
		new Thread(worker2).start();
		latch.await();
		System.out.println("All work done at "+sdf.format(new Date()));
	}
	
	static class Worker implements Runnable{
		private String workerName;
		int workTime;
		CountDownLatch latch;
		public Worker(String workerName,int workTime,CountDownLatch latch){
			this.workerName = workerName;
			this.workTime = workTime;
			this.latch = latch;
		}
		@Override
		public void run(){
			System.out.println("Worker "+workerName+" do wrok begin at "+sdf.format(new Date()));
			doWork();
			System.out.println("Worker "+workerName+" complete at "+sdf.format(new Date()));
			latch.countDown();
		}
		
		public void doWork(){
			try{
				Thread.sleep(workTime);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
