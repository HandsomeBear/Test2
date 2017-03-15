package sunhao.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CacheChecker extends BaseChecker {

	public CacheChecker(CountDownLatch latch) {
		super("Cache Service", latch);
	}

	@Override
	public void verifyService() {
		System.out.println("Checking..."+this.getServiceName());
		try{
			System.out.println("Operation-cache");
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(this.getServiceName()+" is UP");
	}
	
}
