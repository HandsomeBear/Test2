



/**文件注释：ProxyObj2.java<br>
 *作者：孙浩<br>
 *时间：2016年11月10日-下午7:21:19<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package com.sh.proxy;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月10日 下午7:21:19</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class ProxyObj2 implements IOperate{

	private IOperate concreteObj = null;
	
	/**<div class="HMETHOD"><p>方法说明</p>
	 *<p>作者：孙浩</p>
	 *<p>时间:2016年11月10日 下午7:21:53</p>
	 *<p>主要用途:</p>
	 *<p>方法描述:</p>
	 *<p></p>
	 *<p>预期目标:无</p>
	 *</div>
	 */
	@Override
	public String operate(String info) {
		if(concreteObj == null){
			concreteObj = new ConcreteObj();
		}
		return concreteObj.operate(info);
	}

}
