import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class allDetails_mapperClass extends Mapper<LongWritable, Text, Text, Text> {

	static String records = "";

	@Override
	protected void map(LongWritable baseAddress, Text line, Context context) throws IOException	{
		Text business_id = new Text();
		Text details = new Text();

		records = records.concat(line.toString());
		String[] fields = records.split("::");
		if (fields.length == 3) {
			 {
				String address=fields[1].trim();
				String categories=fields[2].trim();
				String value=address+"\t"+categories;				
				business_id.set(fields[0].trim());
				details.set(value);
				try {
					context.write(business_id, details);
				} catch (InterruptedException e) {
					System.out.println("E2: Error in write operation details mapping class"+e.getMessage());
					
				}
			}
			 records="";
		}
	}
}
