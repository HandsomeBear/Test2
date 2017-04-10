package sunhao.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

public abstract class BaseChecker implements Runnable{

	private CountDownLatch latch;
	private String serviceName;
	private boolean serviceUp;
	
	public BaseChecker(String serviceName,CountDownLatch latch){
		super();
		this.serviceName = serviceName;
		this.latch = latch;
		this.serviceUp = false;
	}
	
	@Override
	public void run(){
		try{
			verifyService();
			serviceUp = true;
		}catch(Exception e){
			e.printStackTrace();
			serviceUp = false;
		}finally{
			if(latch != null){
				latch.countDown();
			}
		}
	}
	
	public String getServiceName(){
		return serviceName;
	}
	
	public boolean isServiceUp(){
		return serviceUp;
	}
	
	public abstract void verifyService();
}
