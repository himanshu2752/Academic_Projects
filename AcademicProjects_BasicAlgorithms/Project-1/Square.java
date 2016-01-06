/**
 * @(#)Square.java
 *
 *
 * @author 
 * @version 1.00 2015/9/5
 */


public class Square 
	{

int sideLength ;

    public Square() 
    	{
    		sideLength = 1;
    	}
    
    public Square(int x)
    	{
    	sideLength = x;
    	}
    
    public int getArea()
    	{
    	int area = sideLength*sideLength ;
    	return area ;
    	}
	}
 