package scheduler;

//import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;

/**
 * Modified by: Michael Janks
 * 
 * <p>Title: FCFSScheduler</p>
 * <p>Description: Component of the simulate operating system that encapsulates FCFS job scheduling.</p>
 * <p>Copyright: Copyright (c) 2015, 2004</p>
 * <p>Company: </p>
 * @author Matt Evett
 * @version 2.0
 */

public class FCFSScheduler extends Scheduler {

  /*
   * TO_DO: your data structure to support a FCFS scheduler
   * and the abstract methods of Scheduler
   */
	
	ArrayList<Job> readyQ = new ArrayList<Job>(); // SHARED OBJECT, BUFFER
	
	public synchronized void add(Job J) {
		
		if(readyQ.isEmpty()) {
			readyQ.add(J);
			System.out.println(Thread.currentThread() + " notifying the readyQ");
			notify();
		}
	}

	public void remove(Job J) {
		readyQ.remove(J);
	}

	public boolean hasJobsQueued() {
		if(readyQ.isEmpty())
			return false;
		else
			return true;
	}
    
  /**
   * If the ready queue is empty, return false.
   * Otherwise, start the next job in the queue, returning true.  If the queue is empty
   * return false.
   * Make the next job in the ready queue run. You should probably
   * invoke Thread.start() on it.
   */
  public boolean makeRun()
  {
	  //System.out.println("TO_DO: makeRun not yet implemented");
	  /*
	   * Place code here that gets the next Job from the ready queue and
	   * invokes start() on it
	   *
	   */
	  
	  if(readyQ.isEmpty()) {
		  System.out.println("******************** readyQ is empty! ********************");
		  return false;
	  }
	  currentlyRunningJob = readyQ.get(0); 
	  remove(readyQ.get(0));
	  currentlyRunningJob.start();
	  
	  return true; // TO_DO ***SHOULDN'T ALWAYS RETURN TRUE***
  }
  
  /**
   * blockTilThereIsAJob()  Invoked by OS simulator when it wants to get a new Job to
   * run.  Will block if the ready queue is empty until a Job is added to the queue.
   */
  public synchronized void  blockTilThereIsAJob() { 
	  if (hasRunningJob()) 
		  return;
	  //System.out.println("TO_DO: blockTilThereIsAJob not yet implemented");
	  /*
	   * Place code here that will cause the calling thread to block until the ready queue
	   * contains a Job
	   */
	  
	  while(readyQ.isEmpty())
		try {
			System.out.println(Thread.currentThread() + " is blocking until there is a job");
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  //System.out.println("evidently there is now a job on readyQ");
  }
}
  