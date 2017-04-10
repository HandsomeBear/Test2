



/**文件注释：Enum.java<br>
 *作者：孙浩<br>
 *时间：2016年11月12日-下午2:32:49<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package sunhao.designpattern.singleton;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月12日 下午2:32:49</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public enum SingletonEnum {
	
	instance;
	
	public void doSth(){
		System.out.println("枚举产生的单例模式");
	}

	//下面是扩展
	SingletonEnum(){
		System.out.println("构造前所做的事情");
	}
}
