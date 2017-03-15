



/**文件注释：Demo.java<br>
 *作者：孙浩<br>
 *时间：2016年11月14日-下午4:18:15<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package com.sh.factorymethod;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月14日 下午4:18:15</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class Demo {

	public static void main(String[] args) {
		Factory factory = new ConcreteFactory();
		IProduct productA = factory.createProduct(ProductA.class);
		IProduct productB = factory.createProduct(ProductB.class);
		
		productA.method();
		productB.method();
	}
}
