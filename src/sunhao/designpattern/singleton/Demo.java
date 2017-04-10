



/**文件注释：Demo.java<br>
 *作者：孙浩<br>
 *时间：2016年11月12日-下午2:39:29<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package sunhao.designpattern.singleton;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月12日 下午2:39:29</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class Demo {
	
	public static void main(String[] args) {
		
		System.out.println("<=============枚举===========>");
		
		SingletonEnum enum1 = SingletonEnum.instance;
		SingletonEnum enum2 = SingletonEnum.instance;
		System.out.println("enum1==enum2?===>" + String.valueOf(enum1==enum2));
		enum1.doSth();
		
		System.out.println("<=============静态内部类===========>");
		
		SingletonStaticInner inner1 = SingletonStaticInner.getInstance();
		SingletonStaticInner inner2 = SingletonStaticInner.getInstance();
		System.out.println("静态内部类："+String.valueOf(inner1==inner2));
		
		System.out.println("<==============双重校验锁==========>");
		
		SingletonDoubleLock lock1 = SingletonDoubleLock.getInstance();
		SingletonDoubleLock lock2 = SingletonDoubleLock.getInstance();
		System.out.println("双重校验锁："+String.valueOf(lock1==lock2));
		
		System.out.println("<==============懒汉（线程安全问题）==========>");
		System.out.println("线程不安全--->");
		
		SingletonLazy ins1 = SingletonLazy.getInstanceUnSafe();
		SingletonLazy ins2 = SingletonLazy.getInstanceUnSafe();
		System.out.println("懒汉(线程非安全)："+String.valueOf(ins1==ins2));
		
		System.out.println("线程安全--->");
		
		SingletonLazy ins3 = SingletonLazy.getInstanceSafe();
		SingletonLazy ins4 = SingletonLazy.getInstanceSafe();
		System.out.println("懒汉(线程安全)："+String.valueOf(ins3==ins4));
		
		System.out.println("<==============饿汉（变种）==========>");
		
		SingletonEHan ehan1 = SingletonEHan.getInstance();
		SingletonEHan ehan2 = SingletonEHan.getInstance();
		System.out.println("饿汉："+String.valueOf(ehan1==ehan2));
	}

}
