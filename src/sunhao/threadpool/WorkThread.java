package sunhao.threadpool;

import java.util.Vector;

public class WorkThread extends Thread {

	public int threadNum;
	private boolean flag;

	private Vector<Task> taskVector;
	private Task task;

	public WorkThread(Vector<Task> vector, int i) {
		flag = true;
		threadNum = i;
		taskVector = vector;
		// hide entry here
		super.start();
	}

	public void run() {
		while (flag && taskVector != null) {
			synchronized (taskVector) {
				while (taskVector.isEmpty() && flag) {
					try {
						taskVector.wait();
					} catch (Exception exception) {
					}
				}
				try {
					task = (Task) taskVector.remove(0);
				} catch (Exception ex) {
					task = null;
				}
				if (task == null){
					continue;
				}
			}
			try {
				task.setEnd(false);
				task.startTask();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (!task.isEnd()) {
					task.setEnd(true);
					task.endTask();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} // end of while
	}

	public void closeThread() {
		flag = false;
		try {
			if (task != null){
				task.endTask();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (taskVector) {
			taskVector.notifyAll();
		}
	}
}
