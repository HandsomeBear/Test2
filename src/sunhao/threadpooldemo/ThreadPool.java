package sunhao.threadpooldemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPool {

	private static final int MAX_WORKER_NUMBERS = 10;
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	private static final int MIN_WORKER_NUMBERS = 1;
	
	private BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>();
	private List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
	
	private int workerNum = DEFAULT_WORKER_NUMBERS;
	
	private AtomicLong threadNumber = new AtomicLong();
	
	public ThreadPool(){
		this(DEFAULT_WORKER_NUMBERS);
	}
	
	public ThreadPool(int number){
		this.workerNum = number > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS
                : number < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : number;
        init(workerNum);
	}
	
	private void init(int workNum){
		for(int i = 0;i < workerNum; i++){
			workers.add(new Worker("Worker-"+threadNumber.incrementAndGet(),taskQueue));
		}
		for(Worker worker : workers){
			new Thread(worker).start();
		}
	}
	
	public void execute(Task task){
		if(task != null){
			synchronized(taskQueue){
				taskQueue.add(task);
				taskQueue.notify();
			}
		}
	}
	
	public void shutdown(){
		for(Worker worker : workers){
			worker.shutdown();
		}
	}
	
	public synchronized List<Worker> getWorkers(){
		return workers;
	}
}
