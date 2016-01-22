/**
 * @(#)TestSquare.java
 *
 *
 * @author 
 * @version 1.00 2015/9/5
 */
//import Square;

public class TestSquare
 {

    public static void main (String[] args) {
					   
 Square s1 = new Square();
 int a1 = s1.getArea();
 System.out.println("Area of square1 is " +a1);
 
 Square s2 = new Square(4);
 int a2 = s2.getArea(); 
 System.out.println("Area of square2 is " +a2); 
	}
 }

    
