package sunhao.threadsrc;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo {

	public static void main(String[] args) {
		AtomicInteger clt = new AtomicInteger(3);
		clt.compareAndSet(3, 5);
		System.out.println(clt.get());
	}
}
