package sunhao.classloader;

public class Initialization {

	public static void main(String[] args) {
		System.out.println(SubClass.value);
	}
}

class SuperClass{
	static{
		System.out.println("---Super Class");
	}
	public static int value = 123;
}

class SubClass extends SuperClass{
	static{
		System.out.println("---Sub Class");
	}
}
