package sunhao.threadpooldemo;

public class TaskPrint implements Task,Runnable{
	
	private boolean isEnd = true;
	
	public TaskPrint(){
		isEnd = true;
	}

	@Override
	public void run() {
		System.out.println("=====>");
	}

	@Override
	public void setEnd(boolean flag) {
		this.isEnd = flag;
	}

	@Override
	public void startTask() throws Exception {
		run();
	}

	@Override
	public void endTask() throws Exception {
		
	}

	@Override
	public boolean isEnd() {
		return isEnd;
	}

}
