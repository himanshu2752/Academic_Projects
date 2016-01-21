
import java.util.Random;

public class SortMain 
{
    static Random r = new Random() ;
    private static  int NUM_ITEMS = 1000;   ////This will be the input to all sorting algorithms --change this to change input //
     public static  Integer[] Random(int l)
     {
         Integer[] a = new Integer[l];
         for(int i= 0 ;i<a.length ;i++)
         {
             a[i] = i;
         }
         for(int i=0 ; i<l ; i++)
         {
             int rand = i + r.nextInt(l-i);
             int tmp = a[i];
             a[i] = a[rand];
             a[rand]= tmp ;
         }
         return a ;
     }
    public static void main( String [ ] args )
    {
        Sort s = new Sort();
    
         
        Integer[] a1 = Random(NUM_ITEMS);
        long startTime1 = System.nanoTime();
        Sort.insertionSort(a1);
      //  Sort.checkSort(a1);
        long endTime1 = System.nanoTime();
         System.out.println( "Elapsed time(time in millisecond*10) for insertion sort: " + (endTime1 - startTime1)/100000 ); /*   It will calculate time in (millisecond*10)   */
        
         
        Integer[] a2 = Random(NUM_ITEMS);
        long startTime2 = System.nanoTime();
        Sort.quicksort(a2);
      //  Sort.checkSort(a2);
        long endTime2 = System.nanoTime();
         System.out.println( "Elapsed time(time in millisecond*10) for quicksort: " + (endTime2 - startTime2)/100000 );
        
        
        Integer[] a3 = Random(NUM_ITEMS);
        long startTime3 = System.nanoTime();
        BucketSort.BucketSort(a3);
       // Sort.checkSort(a3);        
        long endTime3 = System.nanoTime();
         System.out.println( "Elapsed time(time in millisecond*10) for Bucketsort: " + (endTime3 - startTime3)/100000 );
     
         /*Integer [ ] a = new Integer[12];
         a[0]= 10;
         a[1]= 20;
         a[2] = 5;
         a[3]= 50;
         a[4] = 30;
         a[5] = 35;
         a[6] = 23;
         a[7] = 13;
         a[8] = 4;
         a[9] = 20;
         a[10] = 5;
         a[11] = 13;
         long startTime4 = System.currentTimeMillis();
         BucketSort.BucketSort(a);
            System.out.println(a);
         long endTime4 = System.currentTimeMillis( );
         System.out.println( "Elapsed time for new Bucketsort: " + (endTime4 - startTime4) );
         Sort.checkSort(a);
         
         long startTime5 = System.currentTimeMillis( );
         Sort.insertionSort(a);
         long endTime5 = System.currentTimeMillis( );
         System.out.println( "Elapsed time for new Insertionsort: " + (endTime5 - startTime5) );
         Sort.checkSort(a);
*/         
    }
}
