



/**文件注释：SingletonEHan.java<br>
 *作者：孙浩<br>
 *时间：2016年11月12日-下午3:12:36<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package com.sh.singleton;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月12日 下午3:12:36</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class SingletonEHan {
	
	private static SingletonEHan instance = new SingletonEHan();
	
	private SingletonEHan(){
		
	}
	
	public static SingletonEHan getInstance(){
		return instance;
	}

	//变种
//	private SingletonEHan instance = null;
//	private SingletonEHan(){
//		
//	}
//	static{
//		instance = new SingletonEHan();
//	}
//	public static SingletonEHan getInstance(){
//		return this.instance;
//	}
}
