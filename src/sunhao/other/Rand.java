package sunhao.other;

import java.util.Random;

public class Rand {

	public static void main(String[] args) {
		test01();
		System.out.println();
		test02();
	}
	
	private static void test01(){
		Random random = new Random();
		for(int i = 0;i<5;i++){
			System.out.print("-->"+random.nextInt());
		}
	}
	
	private static void test02(){
		Random random = new Random(3);
		for(int i = 0;i<5;i++){
			
			System.out.print("-->"+random.nextInt());
		}
	}
}
