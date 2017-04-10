



/**文件注释：ConcreteObj.java<br>
 *作者：孙浩<br>
 *时间：2016年11月10日-下午7:06:07<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package sunhao.designpattern.proxy;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月10日 下午7:06:07</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class ConcreteObj implements IOperate{

	
	
	
	
	/**<div class="HMETHOD"><p>方法说明</p>
	 *<p>作者：孙浩</p>
	 *<p>时间:2016年11月10日 下午7:07:14</p>
	 *<p>主要用途:</p>
	 *<p>方法描述:</p>
	 *<p></p>
	 *<p>预期目标:无</p>
	 *</div>
	 */
	@Override
	public String operate(String info) {
		return "ConcreteObj is working..." + info;
	}

}
