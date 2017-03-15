package sunhao.classloader;

/**
 * @author 孙浩
 * @date 2017年3月14日 上午11:21:56
 * @Description:
 * 类的生命周期：加载->验证->准备->解析->初始化->使用->卸载
 * 只有在准备和初始化阶段才会涉及类变量的初始化和赋值，
 * 类的准备阶段需要做的就是为类变量分配内存并设置默认值，因此类变量st为null,b为0（注意：如果类变量是final的，则在加载阶段已经完成初始化）
 * 类的初始化阶段需要做的是执行类构造器（类构造器是编译器收集所有静态语句块和类变量的赋值语句按语句在源码中的出现顺序合并生成类的构造器，
 * 		对象的构造方法是init，类的构造方法是cinit），因此先执行第一条静态变量st = new StaticTest()，此时会进行对象的初始化，对
 * 		象的初始化是先初始化成员变量再执行构造方法，因此a=110,b还未完成赋值，等对象的初始化完成后继续执行之前的类构造器的语句
 */
public class StaticTest {

	/**
	 * 
	 * @author 孙浩
	 * @date 2017年3月14日 上午11:46:06
	 * @Description:
	 * 我的理解：
	 * 		在准备阶段完成类变量的默认值赋值过程
	 * 		初始化时：new StaticTest()会先执行构造代码块输出2；然后执行成员变量的赋值a=110；在执行构造函数
	 * 			在这里类变量的赋值是根据顺序来的，所以c=12;b=0
	 */
	public static void main(String[] args) {
		staticFunction();
	}
	
	static int c = 12;
	
	static StaticTest st = new StaticTest();
	
	static{
		System.out.println("1");
	}
	
	{
		System.out.println("2");
	}
	
	public StaticTest(){
		System.out.println("3");
		System.out.println("a="+a+",b="+b+",c="+c);
	}
	
	public static void staticFunction(){
		System.out.println("4");
	}
	
	int a = 110;
	static int b = 12;
}
