



/**文件注释：SingletonStaticInner.java<br>
 *作者：孙浩<br>
 *时间：2016年11月12日-下午2:49:56<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package com.sh.singleton;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月12日 下午2:49:56</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class SingletonStaticInner {
	
	private SingletonStaticInner(){
		
	}
	
	private static class SingletonHolder{
		private static final SingletonStaticInner instance = new SingletonStaticInner();
	}

	public static final SingletonStaticInner getInstance(){
		return SingletonHolder.instance;
	}
}
