import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class join_ReducerClass extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text business_id, Iterable<Text> values,Reducer<Text, Text, Text, Text>.Context context)
			throws IOException
	{
		String string1 = "";
		String string2 = "";
		
		boolean check = false;
		
		for (Text val : values)
		{
			if (val.toString().contains("code::"))
			{
				check = true;
				string1 = val.toString();				
			} 
			else
			{
				string2 = val.toString();
			}
		}
		if (!check)
		{
			return;
		}
		
		String[]data1 = string1.split("\t");
		String[] data2 = string2.split("\t");		
		String data = data2[0] + "\t" + data2[1]+ "\t" + data1[1] ;
		
		try {
			context.write(business_id, new Text(data));
		} catch (InterruptedException e) {
			System.out.println("E3: Error in writing after join reduce"+e.getMessage());
			
		}
	}
}
