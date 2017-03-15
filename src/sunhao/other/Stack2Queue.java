package sunhao.other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 孙浩
 * @date 2017年2月9日 下午1:47:41
 * @Description:两个栈实现一个队列
 */
public class Stack2Queue {
	
	private static Deque<String> inQue = new ArrayDeque<String>();
	private static Deque<String> outQue = new ArrayDeque<String>();

	public static void main(String[] args) {
		enQueue("a");
		enQueue("b");
		enQueue("c");
		enQueue("d");
		do{
			deQueue();
		}while(!outQue.isEmpty());
	}
	
	public static void enQueue(String s){
		inQue.push(s);
	}
	
	public static void deQueue(){
		if(outQue.isEmpty()){
			System.out.println("outQue isEmpty,put elements from inQue");
			while(!inQue.isEmpty()){
				String s = inQue.pop();
				if(s != null){
					outQue.push(s);
				}
			}
		}
		String s = outQue.pop();
		System.out.print(s+" ");
	}
}
