package jdk;

public class Demo01 {

	//assert需要在run as的run configuration配置vm arguments -ea或-enableassertions
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		System.out.println("before assert!");
		assert (a == b);
		System.out.println("after assert!");
	}
}
