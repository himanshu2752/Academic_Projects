//package project4;

//import project4.DupHT;

public class MyHashTable
{
	DupHT dup = new DupHT();
	int[] countDuplicate = new int[50051] ;
	private static class Hash
    {
        public String  PhNo;   
        public boolean flag; 
        public Hash( String x, boolean b )
        {
            PhNo  = x;
            flag = b;
        }
    }
	private Hash[] arrayD ;
	public int count ;
 
	private boolean flag( int n)
    {
		return arrayD[n] != null && arrayD[ n ].flag;
    }
 
	private int hashfunction(String a)
	{
	 
	 Number	n = Integer.parseInt(a.replaceAll("[^0-9]", ""));
	 int hashvalue = n.hashCode( );
	 if (arrayD.length <= 5003)
	 {
	  hashvalue = hashvalue%5003 ;
	  if (hashvalue < 0)
			 hashvalue = arrayD.length + hashvalue;
	  return hashvalue ;
	 }
	 if (arrayD.length >= 5003 && arrayD.length <=10007)
	 {
		 hashvalue = hashvalue%10007 ;
	 	 if (hashvalue < 0)
		 hashvalue = arrayD.length + hashvalue;
	 	 return hashvalue ;
	 }
	 else
	 {
		 hashvalue = hashvalue%arrayD.length ;
		 if (hashvalue < 0)
			 hashvalue = arrayD.length + hashvalue;
	 	 return hashvalue ;
	 }
	}

 
	private void rehash()

	{
	 Hash[] prevarrayD = arrayD ;
	 makeNewarrayD(2*prevarrayD.length);
	 count = 0;
	 for (Hash h : prevarrayD)
	 {		 
		 if (h != null && h.flag)
			 add(h.PhNo);
	 }
	}
	
	public void add(String a)
	{
		add1(a);
	}
 
	private void add1(String x)
	{
			 
	 int currentPos = findPos( x );
        if(flag(currentPos))
            return ;

        if( arrayD[currentPos] == null )
            ++count;
        arrayD[currentPos] = new Hash( x, true );
                  
        if( count > arrayD.length / 2 )
            rehash();
        
        return ;
 }

	private int findPos( String x )
    {
	
        int quadtmp = 1;
        int currentPos = hashfunction(x);
        
        if (arrayD[currentPos] != null && arrayD[currentPos].PhNo.compareTo(x) == 0)
        {
        	countDuplicate[currentPos] = countDuplicate[currentPos] + 1 ;
        	
        }
        if (arrayD[currentPos] !=null && arrayD[currentPos].PhNo.compareTo(x) == 0 && countDuplicate[currentPos] ==1)
        	dup.Duplicate(x);
        
        while( arrayD[currentPos] != null && arrayD[currentPos].PhNo.compareTo(x) != 0  )
        {
            currentPos += quadtmp; 
            quadtmp += 2;
          
            if( currentPos >= arrayD.length )
            {
                currentPos = currentPos - arrayD.length; 
            }
         }      
        
        return currentPos;
    	}
	private void makeNewarrayD(int arrlength)
	{
	 for (int i=2 ; i*i <= arrlength ; i+=2)
	 {
		 if (arrlength%i == 0 || arrlength%(i+1) ==0)
		 {
		 arrlength = arrlength + 1 ;

		 }
	 }
	 arrayD = new Hash[arrlength] ;
	}
 

	public MyHashTable()
	{
	 makeNewarrayD(5003);
	}
	public void print()
	{
	 
	 for (int j=0 ; j<arrayD.length ;j++)
	 {
		 if (arrayD[j] != null)
		 System.out.println(arrayD[j].PhNo);
	
	 }
	}
}
