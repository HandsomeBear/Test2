



/**文件注释：SingletonLazy.java<br>
 *作者：孙浩<br>
 *时间：2016年11月12日-下午3:04:26<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package sunhao.designpattern.singleton;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月12日 下午3:04:26</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class SingletonLazy {
	
	private static SingletonLazy instance;
	
	private SingletonLazy(){
		
	}
	
	//线程不安全
	public static SingletonLazy getInstanceUnSafe(){
		if(instance==null){
			instance = new SingletonLazy();
		}
		return instance;
	}

	//线程安全
	public static synchronized SingletonLazy getInstanceSafe(){
		if(instance==null){
			instance = new SingletonLazy();
		}
		return instance;
	}
}
