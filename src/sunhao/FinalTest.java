package sunhao;

import java.util.HashSet;
import java.util.Set;

public class FinalTest {

	private static final Set<String> set = new HashSet<String>();
	
	public FinalTest(){
		set.add("sun");
	}
	
	public static boolean contains(String s){
		return set.contains(s);
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				if(contains("hao")){
					System.out.println("Contains");
				}else{
					System.out.println("N-Contains");
				}
			}
		});
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				set.add("hao");
			}
		});
		t2.start();
		t1.start();
	}
}
