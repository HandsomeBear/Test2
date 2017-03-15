package sunhao.aqs;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class CLHLock {

	private static Unsafe unsafe = null;
	private static final long valueOffset;
	private volatile CLHNode tail;
	private class CLHNode{
		private boolean isLocked = true;
	}
	
	static{
		try{
			unsafe = getUnsafeInstance();
			valueOffset = unsafe.objectFieldOffset(CLHLock.class.getDeclaredField("tail"));
		}catch(Exception e){
			throw new Error(e);
		}
	}
	
	private static Unsafe getUnsafeInstance() throws Exception{
		Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafeInstance.setAccessible(true);
		return (Unsafe)theUnsafeInstance.get(Unsafe.class);
	}
	
	/**
	 * 入队时,看tail是否为null:
	 * 是,则while检测前驱节点是否解锁，解锁了则执行自己的逻辑
	 * 否,继续检测
	 * @return {[type]} [description]
	 */
	public void lock(CLHNode currentThreadNode){
		CLHNode preNode = null;
		for(;;){
			preNode = tail;
			if(unsafe.compareAndSwapObject(this, valueOffset, tail, currentThreadNode)){
				break;
			}
			if(preNode!=null){
				while(preNode.isLocked){
					
				}
			}
		}
	}
	
	/**
	 * 判断当前节点是否为尾节点：是，则直接将尾节点置为空；否则置为解锁状态
	 * @return {[type]} [description]
	 */
	public void unlock(CLHNode currentThreadNode){
		if(!unsafe.compareAndSwapObject(this, valueOffset, currentThreadNode,null)){
			currentThreadNode.isLocked = false; 
		}
	}
}
