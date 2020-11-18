package scheduler;
import java.io.*;
import java.util.*;

/**
 * Modified by: Michael Janks
 * 
 * <p>Title: RunScheduler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015, 2004 by Matt Evett</p>
 * @author Matt Evett
 * @version 2.0
 * The main driver for the project.
 */

public class RunScheduler {
	
  private final static String INPUT_FILE_NAME = "testing1.txt"; // filename

  public static void main(String[] args) {
	  
	try {
		/*
		 * Found out about a way of redirecting system output from this website:
		 * https://developers.perfectomobile.com/display/TT/Export+IDE+console+log+to+external+file
		 * This code will redirect the output of System.out.print from the console
		 * to a file called output.txt.
		 * Comment out this code to see the output display in the console while
		 * the program is running.
		 */
		System.setOut(new PrintStream(new FileOutputStream("output.txt")));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	  
    // set up for operating system simulator
    Thread thisThread = Thread.currentThread();
    thisThread.setPriority(Thread.MAX_PRIORITY);
    SystemSimulator kernel = new SystemSimulator(new FCFSScheduler());
    ArrayList<String> jobs = new ArrayList<String>();

    getJobsFromFile( jobs );
    
    WorkFactory sinecure = new WorkFactory();
    Submittor sub = new Submittor( jobs, kernel, sinecure);
    kernel.setPriority(8);
    kernel.start(); // Thread executing this instruction has higher priority, so will continue to hold cpu
    sub.setPriority(7); 
    sub.start();
    // As the driver exits, os has the highest priority, so should get the cpu....
  }
  
  /**
   * Copies content of the input file into an array.  Each line of input
   * becomes one element of the array.
   */
  public static void getJobsFromFile( ArrayList<String> listOfLines )
  {
	  Scanner fileIn=null;  //(Initialization keeps compiler happy)
      try { // open file
          fileIn = new Scanner(new FileInputStream(INPUT_FILE_NAME));
      } catch (FileNotFoundException e) {
          System.out.println("Input file "+INPUT_FILE_NAME+" not found. ");
          System.exit(1);
      } 
      while( fileIn.hasNextLine() ) // iterate through each file line
    	  listOfLines.add( fileIn.nextLine() ); // Send line to ArrayList
      fileIn.close(); // close file
  }
}
  
