package sunhao.designpattern.strategy;

public class Context {

	private IStrategy strategy;
	
	public Context(IStrategy strategy){
		this.strategy = strategy;
	}
	
	public void operateWapper(String info){
		System.out.println(strategy.operate(info));
	}
}
