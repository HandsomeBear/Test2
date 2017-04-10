



/**文件注释：ConcreteFactory.java<br>
 *作者：孙浩<br>
 *时间：2016年11月14日-下午4:15:00<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package sunhao.designpattern.factoryfactory;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月14日 下午4:15:00</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class ConcreteFactory extends Factory{

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IProduct> T createProduct(Class<T> c) {
		T product = null;
		try{
			product = (T)Class.forName(c.getName()).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return product;
	}

}
