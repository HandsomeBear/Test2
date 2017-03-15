package sunhao.threadpooldemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

	public static void main(String[] args) throws Exception{
		ThreadPool pool = new ThreadPool(3);
		for(int i = 0;i < 20;i++){
			TaskPrint task = new TaskPrint();
			pool.execute(task);
		}
		Thread.sleep(5000);
		pool.shutdown();
		Thread.sleep(5000);
		
		for(Worker worker : pool.getWorkers()){
			System.out.println(worker.getName()+":"+worker.getStatus());
		}
		
		System.out.println("----->"+pool.getWorkers().get(0).getName()+"接受任务");
		pool.getWorkers().get(0).startup();
		
		for(Worker worker : pool.getWorkers()){
			System.out.println(worker.getName()+":"+worker.getStatus());
		}
		
		for(int i = 0;i < 10;i++){
			TaskPrint task = new TaskPrint();
			pool.execute(task);
		}
	}
	
	public static void test(){
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Runnable(){
			public void run(){
				
			}
		});
	}
}
