package project4;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import project4.MyHashTable;

public class DupHT 
{
	
	 public static void main(String args[])
	   {
		 DupHT d = new DupHT();
		 MyHashTable ht = new MyHashTable();
		 long startTime = System.currentTimeMillis( );
	      try 
	      {
	         Scanner sc = new Scanner(new File("ph.txt"));
	         ArrayList<String> list = new ArrayList<>();
	         
		 while (sc.hasNext())
		 {
		    String ph = sc.nextLine();
		    list.add(ph);
		
	         }

		 for (int i=0; i<list.size(); i++)
		 {
			 String s = list.get(i);
			ht.add(s);
	      
		 }
		    
	      }
	      catch (Exception e)
	      {
	          System.out.println("Exception " + e);
		  System.exit(1);
	      }
	    
	      ht.print();
	      System.out.println("non duplicate Phone numbers :- "+ht.count);
	      
	        
	        
	        int duptimes= 0;
			 for (int i =0 ;i<ht.countDuplicate.length ; i++)
			 {
				 if (ht.countDuplicate[i] != 0)
					 duptimes++ ;
			 }
			 System.out.println("number of duplicates " +duptimes);
	 
			 long endTime = System.currentTimeMillis( );
			 System.out.println( "Elapsed time: " + (endTime - startTime) );
	   }
	 		public void Duplicate(String d)
	 		{
		
	 			System.out.println(d+ " is duplicate phone number ,it already exists ");//+n+" number of times" );
	 		}

}
