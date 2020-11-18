package scheduler;

/**
 * Created by: Michael Janks
 */

public class MyJobWorkable implements JobWorkable {
	int taskNo = 0;
	
	public void doWork(String name) {
		System.out.println("Job " + name + " is performing task #" + taskNo);
		taskNo++;
	}
}
