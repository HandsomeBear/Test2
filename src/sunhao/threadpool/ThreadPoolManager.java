package sunhao.threadpool;

import java.util.Vector;

@SuppressWarnings("unused")
public class ThreadPoolManager {

	/**
	 * the number of threads in pool
	 */
	private int threadNum;
	/**
	 * the default number of threads in pool
	 */
	private int defaultThreadNum;
	/**
	 * the vector of threads in pool
	 */
	private Vector<Thread> workThreadVector;
	/**
	 * the vector of tasks
	 */
	private Vector<Task> taskVector;

	/**
	 * @param i
	 */
	public ThreadPoolManager(int i) {
		taskVector = new Vector<Task>(10, 10);
		// initial thread number
		defaultThreadNum = 10;
		if (i > 0)
			defaultThreadNum = i;
		// call thread
		CreateThreadPool(i);
	}

	public ThreadPoolManager() {
		this(10);
	}

	/**
	 *
	 * @return
	 */
	public boolean isAllTaskFinish() {
		return taskVector.isEmpty();
	}

	/**
	 * @return int
	 */
	public int getThreadNum() {
		return threadNum;
	}

	/**
	 * create thread pool
	 * 
	 * @param i
	 */
	private void CreateThreadPool(int i) {
		if (workThreadVector == null)
			workThreadVector = new Vector<Thread>(i);
		// create threads
		synchronized (workThreadVector) {
			for (int j = 0; j < i; j++) {
				threadNum++;
				WorkThread workThread = new WorkThread(taskVector, threadNum);
				workThreadVector.addElement(workThread);
			}

		}
	}

	/**
	 * add task to task vector and notify work Threads in pool to do it
	 * 
	 * @param taskObj
	 */
	public void addTask(Task taskObj) {
		if (taskObj == null)
			return;
		synchronized (taskVector) {
			taskVector.addElement(taskObj);
			taskVector.notifyAll();
		}
	}

	/**
	 * destroy threads in pool
	 */
	public void closeThread() {

		while (!workThreadVector.isEmpty()) {

			try {
				WorkThread workThread = (WorkThread) workThreadVector.remove(0);
				workThread.closeThread();
				continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
}
