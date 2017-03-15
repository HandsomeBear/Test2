class HelloA{
	String a = "父类在调用构造函数过程中被调用的方法...";
	public HelloA(){
		System.out.println("HelloA-before");
		draw();
		System.out.println("HelloA-after");
	}
	public void draw(){
		System.out.println(a);
	}
}

public class HelloB extends HelloA{

	String a = "子类override了draw方法";
    public HelloB(){
    	System.out.println("HelloB-before");
    	System.out.println("HelloB-after");
    }
    public void draw(){
    	System.out.println(a);
    }
    public static void main(String[] args) {
		new HelloB();
	} 

}
