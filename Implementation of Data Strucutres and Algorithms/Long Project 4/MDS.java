import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author Himanshu Parashar
 * 			Satwant Singh
 * Class implementing multidimensional search
 */

public class MDS {
	public int smeSmeCount=0;
		class idAndprice {
		long id ;
		double price ;
		
		public idAndprice(long i, double p) {
			this.id = i ;
			this.price = p ;
		}
	}
	
	 class idDescriptionAndPrice{
		long id ;
		double price;
		long[] description ;		
		
	}
	 class priceDesc{
		 double price;
		 long[] descrption ;
		 
		 priceDesc(double prc, long[] desc)
		 {
			 this.price = prc;
			 this.descrption = desc;
		 }
	 }
	 
	 TreeMap<Long, priceDesc> tm1 = new TreeMap<>();
	 
	 
	 TreeMap<Long, List<Double> > tm3 = new TreeMap<>();
	 HashMap<Long, Boolean> chkSmSm = new HashMap<>();
	 List<idAndprice> a = new ArrayList<>();
 
	////////////////////////////////////////////////////////////////
 	int insert(long id, double price, long[] description, int size) {
		priceDesc pD = new priceDesc(price, description);
		
		long sum=0;
		for (int i =0 ; i<size;i++)
		{
			List<Double> IdList = new LinkedList<>();
			sum+=description[i];
			IdList=tm3.get(description[i]);
			if(IdList==null){
				List<Double> newIdList = new LinkedList<>();
				newIdList.add(price);
				tm3.put(description[i], newIdList);
			}
			else{
				IdList.add(price);
				tm3.put(description[i], IdList);
			}
		}
		if (find(id) == 0)
		{
			tm1.put(id, pD);
			if(size>=8){
				
				if(chkSmSm.get(sum)==null){
					chkSmSm.put(sum, false);
				}
				else{
					if(!chkSmSm.get(sum)){
						smeSmeCount++;
						chkSmSm.put(sum, true);
					}
					smeSmeCount+=1;
				}
			}
			return 1 ; 
		}
		else
		{
			if(size>=8){
				long tmpSum=sumOfDesc(tm1.get(id).descrption);
				if(chkSmSm.get(tmpSum)!=null){
					smeSmeCount--;
				}
				if(chkSmSm.get(sum)==null){
					chkSmSm.put(sum, false);
				}
				else{
					if(!chkSmSm.get(sum)){
						smeSmeCount++;
						chkSmSm.put(sum, true);
					}
					smeSmeCount+=1;
				}
				
				
			}
			tm1.put(id, pD);
			return 0;
		}	
	// Description of item is in description[0..size-1].
	// Copy them into your data structure.
	
    }
 	long sumOfDesc(long [] a){
 		long sum=0;
 		for(int i=0;i<a.length;i++){
 			sum+=a[i];
 		}
 		return sum;
 	}
    double find(long id) {
    	double rtrnValue=0;
    	priceDesc pd = tm1.get(id);
    	if (pd == null)
    		rtrnValue=0;
    	else
    		rtrnValue=pd.price;
    	return rtrnValue;
    }

    long delete(long id) {
    	long rtrnValue=0;
    	priceDesc value= tm1.get(id);
    	tm1.remove(id);
    	return rtrnValue;
    }
    public class LongComparator implements Comparator<Double>{

		public int compare(Double o1, Double o2) {
			return (int) (o1-o2);
		}
    	
    }
    double findMinPrice(long des) {
    	List<Double> IdList = new LinkedList<>();
    	IdList=tm3.get(des);
    	Comparator<Double> c = new LongComparator();
    	IdList.sort(c);
    //	priceDesc pd = tm1.get(IdList.get(0));
    //	System.out.println("findMinPrice"+pd.price);
	return IdList.get(0);
    }

    double findMaxPrice(long des) {
    	List<Double> IdList = new LinkedList<>();
    	IdList=tm3.get(des);
    	Comparator<Double> c = new LongComparator();
    	IdList.sort(c);
    	//priceDesc pd = tm1.get(IdList.get(IdList.size()-1));
   // 	System.out.println("findMaxPrice"+pd.price);
	return IdList.get(IdList.size()-1);
    }

    int findPriceRange(long des, double lowPrice, double highPrice) {
    	//List<Long> listOfIds = tm3.get(des);
    	int count=0;
    	/*for(int i=0;i<listOfIds.size();i++){
    		priceDesc pd= tm1.get(listOfIds.get(i));
    		if(pd.price>=lowPrice && pd.price<=highPrice)
    			count++;
    	}*/
    	for(Map.Entry<Long,List<Double>> entry : tm3.entrySet()) {
  		  Long key = entry.getKey();
  		  List<Double> value = entry.getValue();
  		  for(Double price:value){
  			if(price>=lowPrice && price<=highPrice)
    			count++;
  		  }
  		}
	return count;
    }

    double priceHike(long minid, long maxid, double rate) {
    	double rtrnValue=0;
    	double tmpPrice=0;
    	for(Map.Entry<Long,priceDesc> entry : tm1.entrySet()) {
    		  Long key = entry.getKey();
    		  priceDesc value = entry.getValue();
    		  if(key>=minid && key<=maxid){
    			  tmpPrice = ((rate/100))*value.price;
    			  rtrnValue+=tmpPrice;
    			  value.price+=tmpPrice;
    			  tm1.put(key, value);
    		  }
    		  tmpPrice=0;
    		}
	return rtrnValue;
    }

    int range(double lowPrice, double highPrice) {
    	int count =0 ;
        for (int i=0;i<tm1.size();i++)
        {
             if ( tm1.get(i).price >= lowPrice && tm1.get(i).price <= highPrice)
             {
                  count++ ;
             }
        }
        return count ;
    }

    int samesame() {
    System.out.println("Same Same:"+smeSmeCount);
	return smeSmeCount;
    }
}