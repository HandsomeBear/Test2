



/**文件注释：Demo.java<br>
 *作者：孙浩<br>
 *时间：2016年11月10日-下午7:10:12<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package sunhao.designpattern.proxy;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月10日 下午7:10:12</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class Demo {

	public static void main(String[] args) {
		ProxyObj proxy = new ProxyObj(new ConcreteObj());
		String result = proxy.operate("This is just a demo...");
		System.out.println(result);
		
		//延迟加载
		ProxyObj2 proxy2 = new ProxyObj2();
		String result2 = proxy2.operate("This is just d demo.");
		System.out.println(result2);
	}
}
