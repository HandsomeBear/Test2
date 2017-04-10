



/**文件注释：SingletonDoubleLock.java<br>
 *作者：孙浩<br>
 *时间：2016年11月12日-下午2:55:58<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */
package sunhao.designpattern.singleton;





/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月12日 下午2:55:58</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class SingletonDoubleLock {
	
	private static SingletonDoubleLock instance;
	
	private SingletonDoubleLock(){
		
	}

	public static SingletonDoubleLock getInstance(){
		if(instance==null){
			synchronized(SingletonDoubleLock.class){
				if(instance==null){
					instance = new SingletonDoubleLock();
				}
			}
		}
		return instance;
	}
}
