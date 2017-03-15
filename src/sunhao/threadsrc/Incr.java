package sunhao.threadsrc;

import java.util.concurrent.atomic.AtomicInteger;

public class Incr {

	public volatile AtomicInteger clt = new AtomicInteger(5);
	
	public static void main(String[] args) throws Exception{
		
		final Incr incr = new Incr();
		
		System.out.println(incr.clt.get());
		
		for(int i = 0;i<10;i++){
			new Thread(){
				@Override
				public void run(){
					incr.clt.incrementAndGet();
				}
			}.start();
		}
		
		Thread.sleep(1000);
		System.out.println(incr.clt.get());
	}
	
}
