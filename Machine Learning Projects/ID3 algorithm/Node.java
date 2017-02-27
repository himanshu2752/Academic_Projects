import java.util.ArrayList;

/**
 * Node Class and its properties defined in this class
 * @author himanshu & Vivek
 *
 */
public class Node 
{
	ArrayList<rows> rowsArr;
	ArrayList<String> nextAttributesList;
	boolean attributeValue;
	boolean sameClassNodes;
	double entropy;
	 Node left; 
	 Node right;	 
	 String attrbuteName;	 
	 String stampValue=" ";
	 
	 /**
	  * Constructor for Node Class
	  * @param Name of the attribute
	  * @param Value of the Attribute
	  */
	 public Node(String attributeName, boolean attributeValue)
	 {
		 this.left=null;
		 this.right=null;		
		 this.entropy = 0;
		 this.sameClassNodes = true;
		 this.attrbuteName = attributeName;
		 this.attributeValue = attributeValue;
	 } 
} 
