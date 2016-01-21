

public class BucketSort 
{
    private static void Bucket(Integer[] a,Integer tmpArray[] )
    {
        int l =0 ;
        
        for (int i=0 ; i<a.length ; i++)
        {
            if (tmpArray[a[i]] == null)
                tmpArray[a[i]] = 1 ;
            else
            tmpArray[a[i]] ++ ;
        }
        for(int j=0 ; j<tmpArray.length ; j++)
        {
            if (tmpArray[j] != null)
                for(int k=0;k<tmpArray[j];k++)
                {
           //         System.out.println(j); 
                    a[l] = j;
                    l++ ;
                }                 
        }
         
    }
    
    public static void BucketSort(Integer[] a)
    {
        int max = a[0];
        for (int i= 0 ; i <a.length ; i++)
        {
            if (a[i] > max)
                max = a[i];
        }
        Integer [] tmpArray = new Integer[max+1] ;
        Bucket(a , tmpArray);
    }
    
    
  /*  public static void main( String [ ] args )
    {
         int [ ] a = new int[12];
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
         
         BucketSort.BucketSort(a);
    }*/
    

}
