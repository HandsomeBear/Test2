package sunhao.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStartUpUtil {
	
	private static List<BaseChecker> services;
	private static CountDownLatch latch;
	private ApplicationStartUpUtil(){
		
	}
	
	private final static ApplicationStartUpUtil INSTANCE = new ApplicationStartUpUtil();
	public static ApplicationStartUpUtil getInstance(){
		return INSTANCE;
	}
	
	public boolean checkExternalServices() throws Exception {
		latch = new CountDownLatch(3);
		services = new ArrayList<BaseChecker>();
		services.add(new NetworkChecker(latch));
		services.add(new DatabaseChecker(latch));
		services.add(new CacheChecker(latch));
		
		Executor executor = Executors.newFixedThreadPool(services.size());
		for(final BaseChecker v : services){
			executor.execute(v);
		}
		latch.await();
		for(final BaseChecker v: services){
			if(!v.isServiceUp()){
				return false;
			}
		}
		return true;
	}
}
