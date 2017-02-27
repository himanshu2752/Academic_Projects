import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author himanshu & Vivek
 *
 */
public class readClass {
	int testInstances = 0;
public List<rows> readTestFile(List<rows> data, String testDataLocation  ) 
	{
		
		int rowNo=1;
		 File file = new File(testDataLocation);
		 data = new ArrayList<>();
		 Scanner sc;
		try {
		 sc = new Scanner(file);
		 String line;		 
		 while(sc.hasNextLine())
		 {		 
		  if(rowNo==1)
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
			   	   Row.attributeValues.add(false);
			   else
				   Row.attributeValues.add(true);
			   }
		   data.add(Row);
		   testInstances++;
		  }
	     rowNo++;
		 } 
		 return data ;
		}
		
		catch (Exception e)
		{		
			System.out.println("E01: Error reading the test File");			
			e.printStackTrace();
		}
		return data;
		}


}


