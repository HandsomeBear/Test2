package sunhao.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 线程1：数据库查到数据放到缓存
 * 线程2：将缓存数据插到数据库
 * @author leagsoft
 *
 */
public class ExchangerDemo {

	public static class ExchangerRunnable implements Runnable{
		Exchanger<Object> exchanger = null;
		Object object = null;
		public ExchangerRunnable(Exchanger<Object> exchanger,Object object){
			this.exchanger = exchanger;
			this.object = object;
		}
		
		public void run(){
			try{
				Object previous = object;
				
				object = this.exchanger.exchange(object);
				
				System.out.println(Thread.currentThread().getName()+" exchange "+previous+" for "+object);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Exchanger<Object> exchanger = new Exchanger<Object>();
		
		ExchangerRunnable exchangerRunnable1 =  
		        new ExchangerRunnable(exchanger, "A"); 
		
		ExchangerRunnable exchangerRunnable2 =  
		        new ExchangerRunnable(exchanger, "B");
		
		new Thread(exchangerRunnable1).start();  
		new Thread(exchangerRunnable2).start();  
	}
}
