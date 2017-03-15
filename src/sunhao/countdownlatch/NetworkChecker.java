package sunhao.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class NetworkChecker extends BaseChecker {

	public NetworkChecker(CountDownLatch latch) {
		super("Network Service", latch);
	}

	@Override
	public void verifyService() {
		System.out.println("Checking..."+this.getServiceName());
		try{
			System.out.println("Operation-network");
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(this.getServiceName()+" is UP");
	}

}
