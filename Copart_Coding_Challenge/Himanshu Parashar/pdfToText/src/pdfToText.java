import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfToText {

	
		private PDFParser parser;
	   private PDFTextStripper pdfStripper;
	   private PDDocument pdDoc ;
	   private COSDocument cosDoc ;
	   
	   private String Text ;
	   private String filePath;
	   private File file;
	 
	     public pdfToText() {
		
		}
	     /**
	      * Converting pdf to text through pdfbox
	      * @return - converted Text/String 
	      * @throws IOException
	      */
	   public String ToText() throws IOException
	   {
	       this.pdfStripper = null;
	       this.pdDoc = null;
	       this.cosDoc = null;	       
	       
	       pdDoc = PDDocument.load(new File(filePath));
	       pdfStripper = new PDFTextStripper();
	       pdDoc.getNumberOfPages();
	       pdfStripper.setStartPage(1);
	       pdfStripper.setEndPage(10);	      
	       
	       Text = pdfStripper.getText(pdDoc);
	       return Text;
	   }
	 
	    public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }
	    
	    /**
	     * Parsing the information received from pdf
	     * 
	     */
	    public void getData(String details)
	    {
	    	String id = "";
	    	String license="";
	    	String[] split = details.split("\\s+");
	    	
	    	for (int i=0 ; i <split.length ; i++)
	    	{
	    		if (split[i].equals("VehicleIdentification") || split[i].contains("VehicleIdentification") || split[i].contains("VehicleNumber") || split[i].contains("Identification"))
	    		{
	    			id = split[i+1];
	    		}
	    		
	    		if (split[i].equals("License")||split[i].contains("Licence") || split[i].contains("Plate"))
	    		{
	    			license = split[i+1];
	    		}
	    	}
	    	
	    	System.out.println("Vehicle ID: " + id);
	    	System.out.println("Vehicle Plate: "+license);
//	    	for (String s : split)
//	    	{
//	    		if (s.equals("VehicleIdentification") || s.contains("VehicleIdentification") || s.contains("VehicleNumber") || s.contains("Identification"))
//	    		{
//	    		
//	    		}
//	    	}
	    }
	    public static void main(String[] args) throws IOException
	    {
	    	pdfToText obj = new pdfToText();
	    	obj.setFilePath("Resume.pdf");
	    	
	    	String text = obj.ToText();
	    	obj.getData(text);
	    //	System.out.println(text);
	    }
	
}
