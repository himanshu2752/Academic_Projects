import java.util.Comparator;
import java.util.Map;

public class myComparator implements Comparator<Object> {
	 
	Map<String, Float> mymap;

	public myComparator(Map<String, Float> map) {
		this.mymap = map;
	}
	
	/**
	 * Override compare function
	 */
	public int compare(Object key1, Object key2) {
		
		Float value1= (Float) mymap.get(key1);
		Float value2= (Float) mymap.get(key2);
		
		if(value2.compareTo(value1) == 0)		
		{
			return 1;
		}
		return 0;
	}
}