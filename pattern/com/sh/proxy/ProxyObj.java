



/**文件注释：ProxyObj.java<br>
 *作者：孙浩<br>
 *时间：2016年11月10日-下午7:08:18<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package com.sh.proxy;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月10日 下午7:08:18</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class ProxyObj implements IOperate{

	private IOperate concreteObj;
	
	public ProxyObj(IOperate concreteObj){
		this.concreteObj = concreteObj;
	}
	
	/**<div class="HMETHOD"><p>方法说明</p>
	 *<p>作者：孙浩</p>
	 *<p>时间:2016年11月10日 下午7:08:32</p>
	 *<p>主要用途:</p>
	 *<p>方法描述:</p>
	 *<p></p>
	 *<p>预期目标:无</p>
	 *</div>
	 */
	@Override
	public String operate(String info) {
		return concreteObj.operate(info);
	}

}
