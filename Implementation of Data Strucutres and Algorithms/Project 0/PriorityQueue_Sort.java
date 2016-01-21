import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityQueue_Sort<T> {
	 public static <T> void priorityQ(T arr[])
	 {
		 PriorityQueue<T> pq= new PriorityQueue<>();
		 for (int i =0;i<arr.length;i++)
		 {
			 pq.add(arr[i]);			 
		 }
		 
		 for (int j=0 ; j<arr.length ; j++)
		 {
			   arr[j] = pq.poll();
		 }
	 }
	 
	 
	    public static void main(String[] args) throws IOException{
	    	Scanner sc = new Scanner(System.in);
	        int n = Integer.parseInt(sc.nextLine());
	        Integer[] A = new Integer[n];
	//        Integer[] tmp1 = new Integer[n];
	 
	    for(int i=0; i<n; i++) {
	        A[i] = new Integer(n-i);
	    }

	    firstTen(A);	    
	
	// Calculating Running time for priority queue   	   
	    long starttime2 = System.currentTimeMillis();
	    priorityQ(A);
	    long stoptime2 = System.currentTimeMillis();
	    firstTen(A);
	    long time_elapsed2 = stoptime2 - starttime2 ;
	    System.out.println("total time for priority queue: " +time_elapsed2+ " milli seconds");
	    
	    }
	    
	    static<T> void firstTen(T[] A) {
	        int n = Math.min(A.length, 10);
	        for(int i=0; i<n; i++) {
	            System.out.print(A[i] + " ");
	        }
	        System.out.println();
	        }


}
