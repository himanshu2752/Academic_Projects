import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Class having functions/methods to implement ID3 algorithm
 * to choose the best attribute for making the Decision Tree
 * @author himanshu & Vivek
 *
 */
public class algorithm_ID3 {
	int trainInstances = 0, testInstances = 0 ,noOfNodes = 0 ,noOfLeafNodes = 0;	
	 double trainAccuracy = 0,testAccuracy = 0;	 
	 ArrayList<String> attributeNames;
	 ArrayList<rows> data;	 
	 Node root;	 
	 int prePruneNodes = 0,postPruneNodes = 0;
	 String trainPath = "",testPath="";

	 readClass rd = new readClass();	 
	 
public void readTestFile()
	{
		int rows=1;
		 File file = new File(testPath);
		 data = new ArrayList<>();
		 Scanner sc;
		try
		{
		 sc = new Scanner(file);
		 String line;		 
		 while(sc.hasNextLine())
		 {		 
		  if(rows==1)
		     line=sc.nextLine();		 		 
		  else
		  {
		   line= sc.nextLine();
		   String[] Values= line.split("\t");
		   rows Row = new rows();
		   Row.attributeValues = new ArrayList<>();
		   int count=0;
		   for(String elementValue:Values)
		   {
			   count++;
			   if(count==(Values.length))
			   {
				   if(elementValue.equals("0"))
				  	   Row.classValue=false;				  
				   else
					   Row.classValue=true;				   
			   }
		       else if(elementValue.equals("0"))
			   {
				   Row.attributeValues.add(false);
			   }
			   else
			   	   Row.attributeValues.add(true);			  
		   }
		   data.add(Row);
		   testInstances++;
		  }
	     rows++;
		 } 
		} 
		catch (Exception e)
		{		
			System.out.println("E01: Error reading the test File");			
			e.printStackTrace();
		}
		}
	
	/**
	 * Calling id3 algorithm which calls the recursive method to compute ID3
	 */
	public void id3()
	{
		root.nextAttributesList = new ArrayList<String>();	 
	
	for(String attr: attributeNames)
	 {
		 root.nextAttributesList.add(attr);
	 }	 
	 RecursiveID3(root);
	}
	/**
	 * Calling Randomid3 which recursiely calls Random recursive method for DT
	 */
	public void Randomid3()
	{
		root.nextAttributesList = new ArrayList<String>();	 
	
	for(String attr: attributeNames)
	 {
		 root.nextAttributesList.add(attr);
	 }	 
	randomRecursiveID3(root);
	}
	
	/**
	  * To test the accuracy of Test Data
	  * @param root
	  * @param rows
	  * @return accuracy
	  */
	 public double AccuracyFunctionTest(Node root, ArrayList<rows> rows)
	 {
		 	double correct_count = 0;
			double wrong_count=0;
			double accuracy = 0;
		 String AN = root.right.attrbuteName;
		 boolean AV = root.right.attributeValue;
		 int attrInd= attributeNames.indexOf(AN);
		 
		 for(rows row: rows)
		 {
		  if(row.attributeValues.get(attrInd)==AV)
			  testDT(root.right,row);
		  else
			  testDT(root.left,row);
		 }	 
		 for(rows row: rows)
		 {
			 if(row.treeValue==row.classValue)
			 	 correct_count++;
			 else
				 wrong_count++;
		 }
		 
		 accuracy = correct_count/(correct_count+wrong_count) * 100;
		 
		 return accuracy;	
	 }
	 
	/**
	 * Initializing the values
	 */
	public void initialize()
	
	 {
		 int rows=1;
		 File file = new File(trainPath);
		 data = new ArrayList<>();
		 Scanner sc;	 
		
		 try 
		{
		 sc = new Scanner(file);
		 String line;	 
		 root = new Node("All Data",true);
		 root.rowsArr = new ArrayList<>();
		 
		 while(sc.hasNextLine())
		 {
		 
		  if(rows==1)
		  { 
		   line=sc.nextLine();		   
		   String[] Names = line.split("\t");
		   attributeNames= new ArrayList<>(Arrays.asList(Names));
		   attributeNames.remove("class");		    
		  }
		 
		  else
		  {
		   line= sc.nextLine();
		   String[] Values= line.split("\t");
		   rows Row = new rows();
		   Row.attributeValues = new ArrayList<>();
		   int count=0;
		   for(String elementValue:Values)
		   {
			   count++;
			   if(count==(Values.length))
			   {
				   if(elementValue.equals("0"))
				   	   Row.classValue=false;
				   else
				      Row.classValue=true;				   
			   }
		       else if(elementValue.equals("0"))
		    	      Row.attributeValues.add(false);			   
			   else
				    Row.attributeValues.add(true);			  
		   }
		   data.add(Row);
		   trainInstances++;
		   root.rowsArr.add(Row);
		  }
		  rows++;
		 } 		
		 int positiveRowCount=0;
		 for(rows testRow: root.rowsArr)
		   {
			   if(testRow.classValue)
			   {
				   positiveRowCount++;
			   }
		   }
		 //Calculate Entropy of Root
		 double positiveFraction=(double)positiveRowCount/root.rowsArr.size();
		 double negativeFraction = 1-positiveFraction;
		 root.entropy= (Double) (( positiveFraction * (Math.log(1/positiveFraction)/Math.log(2))) + ( negativeFraction * (Math.log(1/negativeFraction)/Math.log(2))));
		 
		} catch (Exception e) {
			System.out.println("E02 : Error in initialization");
			e.printStackTrace();
		} 		
		}
	
	/**
	  * to print the summary of the ID3 algorithm
	  */
	 public void printSummary()
	 {
		 
		 System.out.println("Number of training instances = "+trainInstances);
		 System.out.println("Number of training attributes = "+attributeNames.size());
		 System.out.println("Total number of nodes in the tree = "+noOfNodes);
		 System.out.println("Number of leaf nodes in the tree = "+noOfLeafNodes);
		 System.out.println("Accuracy of the model on the training dataset = "+trainAccuracy);	 
		 System.out.println(" ");
		 System.out.println("Number of testing instances = "+testInstances);
		 System.out.println("Number of testing attributes = "+attributeNames.size());
		 System.out.println("Accuracy of the model on the testing dataset ="+testAccuracy);
	 }
	 
	 /**
	  * Pruning Function
	  * @param Node - takes root as Node
	  */
	 // NOT REQUIRED FOR THIS ASSIGNMENT. SInce we are comparing ID3 with Random
	/* public void pruneFunction(Node Node)
	 {
	 	int positives=0;
	 	int negatives=0;	
	 	if(Node!=null && Node.left!=null && Node.right!=null)
	 	{
	 	  if(postPruneNodes>=prePruneNodes)	 
	 	    return;		
	 	 
	 	 if(!Node.left.stampValue.equals(" ") && !Node.right.stampValue.equals(" "))
	 	 {		
	 		ArrayList<rows> rows = new ArrayList<>();
	 		for(rows row : Node.left.rowsArr)
	 		{
	 			rows.add(row);
	 			if(row.classValue)
	 				positives++;
	 			else
	 				negatives++;
	 		}
	 		for(rows row: Node.right.rowsArr)
	 		{
	 			rows.add(row);
	 			if(row.classValue)
	 				positives++;
	 			else
	 				negatives++;
	 		}
	 		if(positives>=negatives)
	 			Node.stampValue="1";
	 		else
	 			Node.stampValue="0";
	 		
	 		Node.left=null;
	 		Node.right=null;
	 		postPruneNodes+=2;
	 	 }
	 	 // Recursively call Prune Function
	 	 else
	 	 {
	 		 pruneFunction(Node.left);
	 	  pruneFunction(Node.right);
	 	 }
	    }
	 }*/

	 
	/**
	 * Implementing the Formula for Entropy in this function
	 * @param node which will be root.
	 */
	private void RecursiveID3(Node node)
	{
	double totalEntropy=0;
	double Lognegtv=0.0f; 
	double positiveLog=0.0f;
	double InformationGain = 0;
	double maxGain=-1;
	String maxGainAttr="";
	TreeMap<String,Double> attrWithIG= new TreeMap<>();	
	  
	for(int i=1; i < node.rowsArr.size(); i++)
	  {
		 if(node.rowsArr.get(i).classValue != node.rowsArr.get(i-1).classValue) 	   
			 node.sameClassNodes = false;	  
	  } 
	  
	  if(node.sameClassNodes || node.nextAttributesList.isEmpty())
	  {
		  if(node.sameClassNodes && !node.rowsArr.isEmpty())
		  {
			  if(node.rowsArr.get(0).classValue)
				 node.stampValue="1";
			  else
				  node.stampValue="0"; 	  
		  }
		  else 
		  {
			  int positives=0;
			  int negatives=0;			  
			  for(rows individualRow: node.rowsArr)
			  {
				  if(individualRow.classValue)				  
					  positives++;				
				  else
					  negatives++;
			  }			  
			  if(positives>=negatives) 
				  	node.stampValue="1";
			  else
			  	  	node.stampValue="0";			  
		  }		  
		return;
	  }
	  
	  else
	  {	 
	   for(String testAttr: node.nextAttributesList)
	   {
		    double IndexPositveClassPositive=0.0f;
	        double IndexPositveClassNegative=0.0f;
	        double IndexNegativeClassPositive=0.0f;
	        double IndexNegativeClassNegative=0.0f;

		int attrIndex = attributeNames.indexOf(testAttr);
	        for(rows currentRow: node.rowsArr)
		{
		 if(currentRow.attributeValues.get(attrIndex) == true)
		 {
		   if(currentRow.classValue)		 
		    IndexPositveClassPositive++;
		   else		   
		    IndexPositveClassNegative++;
		  }
		 else
		 {
		  if(currentRow.classValue)
		    IndexNegativeClassPositive++;
		  else
		    IndexNegativeClassNegative++;
		  }		
		}	   
	   
	   if(IndexPositveClassPositive == 0.0 || IndexPositveClassNegative == 0.0)
	   	    positiveLog =0;
	   else
	   {
	    double indxPositiveProb = IndexPositveClassPositive / (IndexPositveClassPositive + IndexPositveClassNegative);
	    double indexNegativeProb = IndexPositveClassNegative / (IndexPositveClassPositive + IndexPositveClassNegative);
	    positiveLog = (double) (( indxPositiveProb * (Math.log(1/indxPositiveProb)/Math.log(2))) + ( indexNegativeProb * (Math.log(1/indexNegativeProb)/Math.log(2))));
	   }
	    
	   if(IndexNegativeClassPositive == 0.0 || IndexNegativeClassNegative == 0.0)
	  		   Lognegtv =0;
	   else
	   {
		double positiveFraction = IndexNegativeClassPositive / (IndexNegativeClassPositive + IndexNegativeClassNegative);
	    double negativeFraction = IndexNegativeClassNegative / (IndexNegativeClassPositive + IndexNegativeClassNegative);
	    Lognegtv = (double) (( positiveFraction * (Math.log(1/positiveFraction)/Math.log(2))) + ( negativeFraction * (Math.log(1/negativeFraction)/Math.log(2))));
	   }
	   totalEntropy = ((IndexPositveClassPositive + IndexPositveClassNegative)/(IndexNegativeClassPositive + IndexNegativeClassNegative+IndexPositveClassPositive + IndexPositveClassNegative))*positiveLog;
	   totalEntropy = totalEntropy + ((IndexNegativeClassPositive + IndexNegativeClassNegative)/(IndexNegativeClassPositive + IndexNegativeClassNegative+IndexPositveClassPositive + IndexPositveClassNegative))*Lognegtv;
	   InformationGain = node.entropy - totalEntropy;
	   
	   attrWithIG.put(testAttr,InformationGain);
	   }	   
		    for (Map.Entry<String, Double> entry : attrWithIG.entrySet()) 
		    {		        
		       if(maxGain<entry.getValue())
		       {
		    	   maxGainAttr= entry.getKey();
		    	   maxGain=entry.getValue();
		       }
		    }	
		    
		node.right= new Node(maxGainAttr,true);
		node.left= new Node(maxGainAttr,false);
		node.right.entropy = 1;
		node.left.entropy = 1;
			   
		
	   
	   node.right.rowsArr = new ArrayList<>();
	   node.left.rowsArr = new ArrayList<>();
	   node.right.nextAttributesList= new ArrayList<>();
	   node.left.nextAttributesList= new ArrayList<>();
	   
	   for(String attribute:node.nextAttributesList)
	   {
		   if (attribute.compareTo(maxGainAttr) != 0)
		   {
			   node.right.nextAttributesList.add(attribute);
			   node.left.nextAttributesList.add(attribute);			   
		   }
	   }
	   
       for(rows newRow:node.rowsArr) 
		{    	   
    	   int attrIndex = attributeNames.indexOf(maxGainAttr);
    	   
    	   if(newRow.attributeValues.get(attrIndex))    	  
    		   node.right.rowsArr.add(newRow);
    	   else    	   
    		node.left.rowsArr.add(newRow); 
    	   
		}
      //Call ID recursively again to go traverse whole Decision Tree 
  RecursiveID3(node.left);
  RecursiveID3(node.right);  
	 }
 }
/**
 * Function for Random Splitting without using ID3
 * @param node
 */
	private void randomRecursiveID3(Node node)
	{	
	String Attr="";
	ArrayList<String> randmGen = new ArrayList<>();	
	  
	for(int i=1; i < node.rowsArr.size(); i++)
	  {
		 if(node.rowsArr.get(i).classValue != node.rowsArr.get(i-1).classValue) 	   
			 node.sameClassNodes = false;	  
	  } 
	  
	  if(node.sameClassNodes || node.nextAttributesList.isEmpty())
	  {		 
		return;
	  }
	  
	  else
	  {
		  ArrayList<String> copyArry = new ArrayList<>();
	   for(String testAttr: node.nextAttributesList)
	   {    
	   randmGen.add(testAttr);
	   }	
		String tmp = "";
		Random rnd = new Random();
		    while (!randmGen.isEmpty()) 
		    {
			int randomIndex = rnd.nextInt(randmGen.size());
			tmp = randmGen.get(randomIndex);
		       if(!copyArry.contains(tmp))
		       {
		    	   Attr= tmp;
		    	   randmGen.remove(tmp);
		       }
		       copyArry.add(tmp);
		    }	
		    
		node.right= new Node(Attr,true);
		node.left= new Node(Attr,false);		
	   
	   node.right.rowsArr = new ArrayList<>();
	   node.left.rowsArr = new ArrayList<>();
	   node.right.nextAttributesList= new ArrayList<>();
	   node.left.nextAttributesList= new ArrayList<>();
	   
	   for(String attribute:node.nextAttributesList)
	   {
		   if (attribute.compareTo(Attr) != 0)
		   {
			   node.right.nextAttributesList.add(attribute);
			   node.left.nextAttributesList.add(attribute);			   
		   }
	   }
	   
       for(rows newRow:node.rowsArr) 
		{    	   
    	   int attrIndex = attributeNames.indexOf(Attr);
    	   
    	   if(newRow.attributeValues.get(attrIndex))    	  
    		   node.right.rowsArr.add(newRow);
    	   else    	   
    		node.left.rowsArr.add(newRow); 
    	   
		}
      //Call ID recursively again to go traverse whole Decision Tree 
  RecursiveID3(node.left);
  RecursiveID3(node.right);  
	 }
 }
/**
 * To print the attributes and Values as per the format given in the project description	
 * @param node
 * @param appendString
 */
 private void printTree(Node node, String appendString)
 {
	 if(node!=null)
	 {
		 String attributeVal="";
		 
	   if(node.attributeValue)
		   attributeVal="1";
	   else	 
		   attributeVal="0";
	 
	  System.out.println(appendString+node.attrbuteName+" : "+attributeVal+ " : "+node.stampValue);
	  noOfNodes++;
	  if(!node.stampValue.equals(" "))
			  noOfLeafNodes++;
	  
	  appendString=appendString+"|";
	  System.out.println(" ");
	  printTree(node.left,appendString);
	  printTree(node.right,appendString);
	 }
 }

 
/**
 * Testing the Decision Tree 
 * @param node - Root 
 * @param row - row in the File
 */
private void testDT(Node node, rows row)
 {
	 if(node!=null)
	 {
	  if(!node.stampValue.equals(" "))
	  {		 
		 if(node.stampValue.equals("0"))
		 {
			 row.treeValue=false;
		 }
		 else
		 {
			 row.treeValue = true;
		 }
		 return;
	  }
	  else
	  {
		 String AN = node.right.attrbuteName;
		 boolean AV =node.right.attributeValue;
		 int attrInd= attributeNames.indexOf(AN);
		 if(row.attributeValues.get(attrInd)==AV)
			 testDT(node.right,row);
		 else
			 testDT(node.left,row);
	  }
	 }
 }
 



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Drive Function to Run the Program 
 * 
 * @param args - it Takes command line arguments as Files names ( test and train ) and pruning factor
 * args[0] - "training set"
 * args[1] - "testing set"
 * args[2] - Pruning Factor
 */
public static void main(String[] args)
{
	algorithm_ID3 id3Obj = new algorithm_ID3();
//	readClass readObj = new readClass();
	
  if(args.length!=2)
	  System.out.println("Please input both file names i.e test and train data and pruning factor");	
  else
  {
  id3Obj.trainPath = args[0];
  id3Obj.testPath = args[1];
 // double pruningFactor = Double.parseDouble(args[2]); / Pruning is not required for Random
  
  id3Obj.initialize();
  id3Obj.id3();
  id3Obj.printTree(id3Obj.root,"");
  
  Node testRootNode = new Node("AllTestData",true);
  testRootNode.rowsArr = new ArrayList<>();
  id3Obj.trainAccuracy= id3Obj.AccuracyFunctionTest(id3Obj.root,id3Obj.root.rowsArr);
  id3Obj.readTestFile();
  id3Obj.testAccuracy= id3Obj.AccuracyFunctionTest(id3Obj.root,id3Obj.data);
  id3Obj.printSummary();
  
  id3Obj.noOfNodes=0;
  id3Obj.noOfLeafNodes=0;
  id3Obj.testInstances=0;
  
  System.out.println("After running Random");
  id3Obj.initialize();
  id3Obj.Randomid3();
  id3Obj.printTree(id3Obj.root,""); 
  
  
  testRootNode.rowsArr = new ArrayList<>();
  id3Obj.trainAccuracy= id3Obj.AccuracyFunctionTest(id3Obj.root,id3Obj.root.rowsArr);
  id3Obj.readTestFile();
  id3Obj.testAccuracy= id3Obj.AccuracyFunctionTest(id3Obj.root,id3Obj.data);
  System.out.println("---------BELOW IS THE SUMMARY WHEN WE SPLIT BY RANDOM");
  System.out.println(" ");
  id3Obj.printSummary();
 
  /*id3Obj.prePruneNodes = (int) (pruningFactor * id3Obj.noOfNodes);
  
  if(id3Obj.prePruneNodes%2 !=0)
  {
	  id3Obj.prePruneNodes++;
  }
  
  id3Obj.noOfNodes=0;
  id3Obj.noOfLeafNodes=0;
  id3Obj.postPruneNodes=0;
  id3Obj.testInstances=0;
 // id3Obj.testInstances = readObj.testInstances;
  id3Obj.pruneFunction(id3Obj.root);
  System.out.println(" ");
  System.out.println("Post Prune ");
  System.out.println(" ");
  id3Obj.printTree(id3Obj.root,"");
 // testRootNode.rowsArr = new ArrayList<>();
  id3Obj.trainAccuracy=id3Obj.AccuracyFunctionTest(id3Obj.root,id3Obj.root.rowsArr);
  
  readObj.readTestFile(id3Obj.data, id3Obj.testPath);
  id3Obj.readTestFile();
  
  id3Obj.testAccuracy=id3Obj.AccuracyFunctionTest(id3Obj.root,id3Obj.data);
  id3Obj.printSummary();*/
  }
}
}