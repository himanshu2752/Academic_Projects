/**
 * @(#)ScoresTest.java
 *
 *
 * @author 
 * @version 1.00 2015/9/5
 */

import java.util.* ;
public class ScoresTest {

    public static void main(String[] args) {
       List<String> names = new ArrayList<String>();
        Scanner s = new Scanner(System.in);
        System.out.println("enter name");
			names.add(s.next());
       
        	do
        	{        
		
			int k = names.size();
		
			if( k<10)
			{
				System.out.println("enter another name");
				names.add(s.next());
			}
			else
			{
				break;
			}
		
			}
        while(true);
        
         String[] arrNames = names.toArray(new String[0]);
         System.out.println("List of names is " + names);
         
        System.out.println("Enter marks for the above list of names :-");
  	
 		int[][] marks = new int[10][5];
 		
 		
		for (int i = 0; i < 10; i++)
		{
 		   for(int j = 0; j < 5; j++)
 		   	 {
      
      				System.out.println("for "+arrNames[i]+" ,marks in quiz#["+(j+1)+"] :");
   				 marks[i][j] = s.nextInt(); 
		   	}

		}

			for (int a=0;a<10;a++)
			{
			 	for(int b=0;b<5;b++)
			 	{
			 	
 		System.out.println("quiz marks for " +arrNames[a]+ " are : " +marks[a][b]);
			 	}	
			}
			for(int k=0;k<10;k++)
			{
			double avgScore =  average(k,marks) ;	
				
         System.out.println("average marks for: "+arrNames[k]+ " is :" +avgScore);
				
			}
    }
    
    public static double   average(int n ,int[][] a)   
    {
    	double numerator = (a[n][0]+a[n][1]+a[n][2]+a[n][3]+a[n][4]);
    	double denom = ((a[n][0]/a[n][0])+(a[n][1]/a[n][1])+(a[n][2]/a[n][2])+(a[n][3]/a[n][3])+(a[n][4]/a[n][4])) ;
    	
    	double   k = numerator/denom ;
    	
    	return k;
    }
    
}