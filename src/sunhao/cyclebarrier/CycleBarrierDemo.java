package sunhao.cyclebarrier;

import java.util.concurrent.CyclicBarrier;

public class CycleBarrierDemo {

	private static final int num = 5;
	public static class Worker implements Runnable{
		CyclicBarrier barrier;
		public Worker(CyclicBarrier barrier){
			this.barrier = barrier;
		}
		@Override
		public void run(){
			try{
				//执行各自的线程逻辑
				System.out.println("Worker "+Thread.currentThread().getId()+" waiting");
				//逻辑执行完，等待
				barrier.await();
				//所有线程均被唤醒
				System.out.println("Thread ID:"+Thread.currentThread().getId()+" working");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(num,new Runnable(){//所有线程到达栅栏出后的回调逻辑
			@Override
			public void run(){
				System.out.println("Inside Barrier");
			}
		});
		
		for(int i = 0;i<num;i++){
			new Thread(new Worker(barrier)).start();
		}
	}
}
