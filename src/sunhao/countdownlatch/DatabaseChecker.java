package sunhao.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class DatabaseChecker extends BaseChecker {

	public DatabaseChecker(CountDownLatch latch) {
		super("DB Service", latch);
	}

	@Override
	public void verifyService() {
		System.out.println("Checking..."+this.getServiceName());
		try{
			System.out.println("Operation-db");
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(this.getServiceName()+" is UP");
	}
	
}
