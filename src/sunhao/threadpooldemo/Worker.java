package sunhao.threadpooldemo;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable{

	private volatile boolean isStop = false;
	private String workerName = "";
	private BlockingQueue<Task> taskQueue;
	
	public Worker(String workerName,BlockingQueue<Task> taskQueue){
		this.workerName = workerName;
		this.taskQueue = taskQueue;
	}
	
	@Override
	public void run(){
		while(!isStop){
			Task task = null;
			synchronized(taskQueue){
				while(taskQueue.isEmpty()){
					try{
						taskQueue.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
						Thread.currentThread().interrupt();
						return;
					}
				}
				task = taskQueue.poll();
			}
			if(task != null){
				try{
					System.out.print(this.workerName+" ");
					task.setEnd(false);
					task.startTask();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			try{
				if(!task.isEnd()){
					task.setEnd(true);
					task.endTask();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void shutdown(){
		this.isStop = true;
		System.out.println(this.workerName+" is shutdown.");
	}
	
	public synchronized void startup(){
		this.isStop = false;
	}
	
	public String getName(){
		return workerName;
	}
	
	public synchronized boolean getStatus(){
		return isStop;
	}
}
