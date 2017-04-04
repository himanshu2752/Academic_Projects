
public class License {

	String input;
	int k ;
	public void setInput(String s){
		
		input=new String();
		input=s;
	}
public void setLength(int k){
		
		
		this.k=k;
	}
public String getInput(){
	
	return input;
}
public int getLength(){
	
	return this.k;
}
public int getInputLength(){
	
	return input.length();
}
	
	public static void main(String args[]){
		License obj=new License();
		int k;
		obj.setInput(args[0]);
		int len=Integer.parseInt(args[1]);
		obj.setLength(len);
		System.out.println(obj.getInput());
		String myInput=new String();
				myInput=obj.getInput();
				myInput=myInput.toUpperCase();
				k=obj.getLength();
		System.out.println("Group Length ="+k);		
		int n=obj.getInputLength();
		//obj.getInput().toUpperCase();
		char temp;
		int count=0;
		int dashCount=0;
		StringBuilder s=new StringBuilder(myInput);
		//s.toUpperCase();
		for(int i=n-1;i>=0;i--){
			if(s.charAt(i)=='-'){
				//temp=s.charAt(i);
				count++;
				dashCount++;
				//temp=s.charAt(i-1);
				//s.setCharAt(i-1,'-');
				 s.deleteCharAt(i);
				 n--;
			}
			else{
				count++;
				
				
				
				
			}
			
		}
	
		//System.out.println(s);
		//System.out.println(dashCount);
		int hop=0;
		int index2=0;
		int index=n-1;
		while(dashCount!=0&&index!=0)
		{ for(;index>0;index--){
			index2++;
			if(s.charAt(index)!='-')
			hop++;
			if(hop==k){                   //forming the groups
				if(dashCount!=0){
					//if(index>n/2){
					index2=index;
					//index2=n-index2-1;
					if(index2!=0)
					s.insert(index2,'-');        //inserting dash 
					
					//}
					//else{
						
						//index2=index-1;
						//if(index!=0)
						//s.insert(index,'-');
					//}
				
				dashCount--;
				hop=0;
				
				}
				
				
			}
			else continue;
			
		}
			
			
		}
		
		System.out.println(s);
		int dCount=0;
		for(int j=0;j<=k && j<s.length();j++){    //For the case where input is not proper
			if(s.charAt(j)=='-') dCount++;
			if((j==k-1)&& dCount==0){
				
				System.out.println("WARNING : Check your input.. Number of dashes");
				return;
			}
			
		}
		if(dashCount!=0 || index!=0){
			System.out.println("WARNING : Check the number of dashes in your input ");
			return;
		}
		
		
	}
	
}
