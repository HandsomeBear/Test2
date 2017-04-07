package sunhao.threadpool;

public interface Task {
	
	public void setEnd(boolean flag);

	public abstract void startTask() throws Exception;

	public abstract void endTask() throws Exception;

	public boolean isEnd();

}
