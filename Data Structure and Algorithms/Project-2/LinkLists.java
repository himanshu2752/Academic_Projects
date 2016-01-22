//package Project2;

import java.util.Scanner;
import java.io.* ;
import java.util.* ;
 
public class LinkLists
{
      private Node head1;
      private Node head2;
      private Node head3;
      int size1 =0 ;
	int size2 =0;
	int size3 =0;
	
	public LinkLists()
	{
	head1 =null ;
	head2 = null;
	head3 = null;
	}

private  class Node 
{

    public int data;
    public Node next;

    public Node(int d) {
        data = d;
    }
}
    
public String toString()
{

	StringBuilder sb1 = new StringBuilder("List1 :[");
	Node k1 = head1;
	while (k1 !=null)
	{
		sb1.append(k1.data + " ");
		k1 = k1.next;
	}
	sb1.append("]");
	sb1.append(System.getProperty("line.separator"));
	sb1.append("List2 : [");
	Node k2 = head2 ;
	while (k2 != null)
	{
		sb1.append(k2.data + " ");
		k2 = k2.next;
	}
	sb1.append("]");
	sb1.append(System.getProperty("line.separator"));
	sb1.append("List3 : [");	
	Node k3 = head3 ;
	while (k3 != null)
	{
		sb1.append(k3.data + " ");
		k3 = k3.next;
	}
	sb1.append("]");
	return new String(sb1);
	
}


public static void main (String args[]) throws IOException
{
	 try (BufferedReader br = new BufferedReader(new FileReader("Input.txt")))
	 {
		 LinkLists l = new LinkLists(); 
	 String sCurrentLine;
	 int n=0;
	 while ((sCurrentLine = br.readLine()) != null) {
	 String[] details=sCurrentLine.split(" ");
	 String operation=details[0];
	 if(operation.equals("A") || operation.equals("C"))
	 {
	 if(details[1]!=null || details[1]!="")
	 {
	 n=Integer.parseInt(details[1]);
	 }
	 }
	
	 if(operation.trim().equals("A"))
	 {
	  l.ADD(n);
	 
	 }
	 else if(operation.trim().equals("P"))
	 {
	 System.out.println(l);
	 }
	 else if(operation.trim().equals("C"))
	 {
	 l.CANCEL(n);
	 
	 }
	 else if(operation.trim().equals("R"))
	 {
	l.Remove();
	
	 }
	 }

	 } catch (IOException e) {
	 e.printStackTrace();
	 } 
	 


    //  Scanner s = new Scanner(System.in);
     // System.out.println("Enter input");
   // 	int n = s.nextInt();
    // lists.ADD(n);
	 
	 
	 
	 /* FOR TESTING */
	 
    /*	LinkLists lists = new LinkLists();
    	lists.ADD(5);
    	lists.ADD(4);
    	lists.ADD(6);
    	lists.ADD(10);
    	lists.ADD(20);
    	lists.ADD(25);
    	lists.ADD(15);
    	
    	lists.ADD(28);
    	lists.ADD(30);
    	lists.ADD(29);
    	System.out.println(lists);
    	lists.CANCEL(15);
    	lists.CANCEL(20);
    	System.out.println(lists);
    	lists.Remove();
    	lists.Remove();
    	lists.Remove();
    	
    	lists.Remove();
    	lists.Remove();
    	lists.Remove();
    	lists.Remove();
    	lists.Remove();
    	lists.Remove();
    	System.out.println(lists);
//	List<String> lines = Files.readAllLines(Paths.get("Input.txt"), StandardCharsets.UTF_8);
*/
}




public void ADD(int x)
{
	if (size1 <= size2 && size1 <= size3 )
	{
   Node a1 = new Node(x);
   Node previous1 = null;
   Node current1 = head1;

   while (current1 != null && x > current1.data) 
   {
       previous1 = current1;
       current1 = current1.next;
   }

   if (previous1 == null) 
   	{
	a1.next = current1;
	head1 = a1;
	System.out.println("Added "  +x+ " to queue L1"  );
	size1 ++ ;
	}
	else 
	{
	a1.next = current1;
	previous1.next = a1;
	System.out.println("Added "  +x+ " to queue L1"  );
	size1 ++;
	}
	}
	
	else if (size2<size1 && size2<=size3  )
	{
	Node a2 = new Node(x);
   Node previous2 = null;
   Node current2 = head2;

   while (current2 != null && x > current2.data) 
   {
       previous2 = current2;
       current2 = current2.next;
   }

   if (previous2 == null) 
  {
	a2.next = current2;
	head2 = a2;
	System.out.println("Added "  +x+ " to queue L2"  );
	size2 ++ ;
	}
	else 
	{
	a2.next = current2;
	previous2.next = a2;
	System.out.println("Added "  +x+ " to queue L2"  );
	size2 ++;
	}	
	}
	
	else if (size3 < size1 && size3 < size2) 
	{
	Node a3 = new Node(x);
   Node previous3 = null;
   Node current3 = head3;

   while (current3 != null && x > current3.data) 
   {
       previous3 = current3;
       current3 = current3.next;
   }

   if (previous3 == null) 
  {
	a3.next = current3;
	head3 = a3;
	System.out.println("Added "  +x+ " to queue L3"  );
	size3 ++ ;
	}
	else 
	{
	a3.next = current3;
	previous3.next = a3;
	System.out.println("Added "  +x+ " to queue L3"  );
	size3 ++;
	}		
	}
}


public void CANCEL(int n)
{
	if (head1 == null && head2 == null && head3 == null)
		throw new RuntimeException("cannot delete");
	if (head1 != null )
	{
	if (head1.data ==n)
	{
		head1=head1.next;
		System.out.println("Deleted "  +n+ " from queue L1"  );
		size1 -- ;
		return ;
	}
	Node h1 = head1;
	Node a1 = null ;
	while (h1 != null && h1.data != n)
	{
		a1 = h1;
		h1 = h1.next;
		
	}
	if (h1 == null)
		System.out.println("Element passed in cancel operation i.e " +n+ ", not found in list1 ");
	else
	{
		a1.next =  h1.next ;
		size1 --;
		System.out.println("Deleted "  +n+ " from queue L1"  );
	}	
	}
	if (head2 != null)
	{
	if (head2.data == n)
	{
		head2 = head2.next;
		System.out.println("Deleted "  +n+ " from queue L2"  );
		size2 -- ;
		return ;
	}	
		Node h2 = head2;
		Node a2 = null	;
		while (h2 != null && h2.data != n)
	{
		a2 = h2;
		h2 = h2.next;
		
	}
	if (h2 == null)
		System.out.println( "Element passed in cancel operation i.e " +n+ ", Not found in list2");
	else
	{
		a2.next =  h2.next ;
		System.out.println("Deleted "  +n+ " from queue L2"  );
		size2 -- ;
	}
	}
	
	else if (head3 != null)
	{
	if (head3.data == n)
	{
		head3 = head3.next;
		System.out.println("Deleted "  +n+ " from queue L3"  );
		size3 -- ;
		return ;
	}	
		Node h3 = head3;
		Node a3 = null	;
		while (h3 != null && h3.data != n)
	{
		a3 = h3;
		h3 = h3.next;
		
	}
	if (h3 == null)
	{
		throw new RuntimeException("cannot delete");
		
	}
	else
	{
		a3.next =  h3.next ;
		System.out.println("Deleted "  +n+ " from queue L3"  );
		size3 -- ;
	}
	}	
}

public void Remove()
{
	if (head1 == null && head2 == null && head3 == null)
		System.out.println("Removed Called on empty queues ");
	else if (head1 == null && head2 == null && head3 != null)
	{
		System.out.println("Removed " +head3.data);
		head3 = head3.next;
		size3 -- ;		
		return ;	
	}
	else if (head1 == null && head3 == null && head2 != null)
	{	
		System.out.println("Removed " +head2.data);
		head2 = head2.next;
		size2 -- ;
		return 	;	
	}
	else if (head2 == null && head3 == null && head1 != null )
	{	
		System.out.println("Removed " +head1.data);
		head1 = head1.next;
		size1 -- ;
		return ;	
	}
		else if (head1 == null && head2.data<= head3.data )
		{
			System.out.println("Removed " +head2.data);
		head2 = head2.next;		
		size2 -- ;
		return ;
		}
		else if (head1 == null && head3.data < head2.data)
		{
			System.out.println("Removed " +head3.data);
			head3 = head3.next;			
			size3 -- ;
			return ;
		}
		else if (head2 == null && head1.data<=head3.data)
		{
			System.out.println("Removed " +head1.data);
			head1 = head1.next;
			size1 -- ;
			return ;
		}
		else if (head2 == null && head3.data < head1.data)
		{
			System.out.println("Removed " +head3.data);
			head3 = head3.next;
			size3 -- ;
			return ;
		}
		else if (head3 == null && head1.data <= head2.data)
		{
			System.out.println("Removed " +head1.data);
			head1 = head1.next		;	
			size1 -- ;
			return ;
		}
		
		
		else if (head3 == null && head2.data < head1.data)
		{
			System.out.println("Removed " +head2.data);
			head2 = head2.next;
			size2 -- ;
			return 	;
		}

		
		else if (head1.data <= head2.data && head1.data<=head3.data)
		{
			System.out.println("Removed " +head1.data);
			head1 = head1.next;			
			size1 -- ;
			return ;
		}
		else if (head2.data < head1.data && head2.data <= head3.data)
		{
			System.out.println("Removed " +head2.data);
			head2 = head2.next;			
			size2 -- ;
			return ;
		}
		else if (head3.data < head1.data && head3.data < head2.data)
		{
			System.out.println("Removed " +head3.data);
			head3 = head3.next;			
			size3 -- ;
			return ;
		}		
	
} 

}