/** @author G32
 *  MerSort implementation without recursion
 */
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class SP1_d {
	
	//Non Recursive method to implement merge sort
	// Using stack of type Integer array
	/**
	 * 
	 * @param A = Integer array to be sorted
	 * @return = returning sorted array
	 */
	private static Integer[] mergeSort(Integer[] A) {
        Stack<Integer[]> stack1 = new Stack<Integer[]>();
        Stack<Integer[]> stack2 = new Stack<Integer[]>();
      int  n = A.length;
        for (int i = 0; i < A.length; i++)
        {
            stack1.push(new Integer[]{A[i]});
        }
        while (stack1.size()>1)
        {
            while (stack1.size()>1)
            {

                Integer[] r = stack1.pop();
                Integer[] l = stack1.pop();
                Integer[] merged=merge(l, r);
                if (merged.length == n)
                	return merged ;
                else
                stack2.push(merged);
            }
            while (stack2.size()>1)
            {

            	Integer[] r = stack2.pop();
            	Integer[] l = stack2.pop();
            	Integer[] merged=merge(l, r);
                if (merged.length == n)
                	return merged ;
                else
                stack1.push(merged);
            }
        }
        if (stack1.isEmpty())
        	return stack2.pop();
        else if (stack2.isEmpty())
        	return stack1.pop();
        // Adding a condition for input equal to multiple of 3
        else 
        	return merge(stack1.pop(),stack2.pop());       
    
    }
 /**
  * 
  * @param l1 = list1 of type Integer array
  * @param l2 = list2 of type Integer array
  * @return = Merged list of l1 and l2 in sorted manner
  */
 		private static Integer[] merge(Integer[] l1, Integer[] l2)
 		{
 			int l = l1.length+l2.length;
	
 			Integer[] tmp = new Integer[l];
 			int pos1 = 0;
 			int pos2 = 0;
 			int tmpPos = 0;
	
 			while (pos1 < l1.length  && pos2 < l2.length)
 			{
 				if (l1[pos1] <= l2[pos2])
 					tmp[tmpPos++] = l1[pos1++];
 				else
 					tmp[tmpPos++] = l2[pos2++];
 			}
 			while (pos1< l1.length)
 			{
 				tmp[tmpPos++]= l1[pos1++];
 			}
 			while (pos2< l2.length)
 			{
 				tmp[tmpPos++] = l2[pos2++];
 			}		
 			return tmp ;		
 		}
 		static<T> void firstTen(T[] A) {
 		       int n = Math.min(A.length, 10);
 			 // int n = A.length;			// If testing for small input and see all the elements of sorted list
 		        for(int i=0; i<n; i++) {
 		            System.out.print(A[i] + " ");
 		        }
 		        System.out.println();
 		     }
 		
 		public static Integer[] MergeSort(Integer[] A)
 		{
 			return mergeSort(A);
 		}
 		
 		 public static void main(String[] args) throws IOException{
 			 //scanner to take input. n can be upto 50 million
 			 System.out.println("Enter the value of n:");
 			 Scanner sc = new Scanner(System.in);
 		        Integer n = Integer.parseInt(sc.nextLine());
 		        Integer[] A = new Integer[n];
 		  		      
 		    for(int i=0; i<n; i++) {
 		        A[i] = new Integer(n-i);
 		    }
 		    firstTen(A);
 		    
 		    //calculating Running time for merge sort
 		    long starttime1 = System.currentTimeMillis();
 		   A =  MergeSort(A);
 		    long stoptime1 = System.currentTimeMillis();
 		    long time_elapsed1 = stoptime1 - starttime1 ;
 		    
 		    firstTen(A);
 		   
 		    System.out.println("");
 		
 		    System.out.println("total time for merge sort: " +time_elapsed1+ " milli seconds");
 		 }

	}