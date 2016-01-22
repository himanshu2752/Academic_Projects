/**
 * @(#)MyFour.java
 *
 *
 * @author 
 * @version 1.00 2015/9/5
 */
import java.util.* ;

public class MyFour<T> {
	
	T item1 ;
	T item2 ;
	T item3 ;
	T item4 ;
	

    public MyFour(T w,T x ,T y ,T z)
    {
    	item1 =w;
    	item2 =x;
    	item3 =y;
    	item4 =z;
    }
    
    public boolean allEqual(T a ,T b ,T c ,T d)
    {
    	if (a==b&&b==c&&c==d&&a==d)
    		return true;
    		else
    			return false;
    }
    
    public void shiftLeft(T a ,T b ,T c ,T d)
    {
    	T temp1 ,temp2 ;
    	
    	temp1 = a;
    	temp2 = b;
    	a = temp2;
    	b = c ;
    	c = d ;
    	d = temp1 ;
    	
    	System.out.println(""+a+" "+b+" "+c+" "+d+"");
    }
   	public void toString(T a ,T b ,T c ,T d)
   	{
   		System.out.println(""+a+", " +b+", " +c+", " +d+" ");
   	}
    
    public static void main (String[] args) 
    	
    	{	
    		
    		MyFour<String> obj = new MyFour<String>( "abcd" , "abcd" , "abcd" , "abcd");
    		obj.toString("abcd","abcd","abcd","abcd");
    	boolean a=	obj.allEqual("abcd","abcd","abcd","abcd");
    	System.out.println("Result of allEqual is :" +a);
    		
    		MyFour<Integer> obj2 = new MyFour<Integer> (11,22,33,55);
    		obj2.toString(11,22,33,55);
    		boolean a1=	obj2.allEqual(11,22,33,55);
    	System.out.println("Result of allEqual is :" +a1);
    	obj2.shiftLeft(11,22,33,55);
    	
    		
  		  }
}