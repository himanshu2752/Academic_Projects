import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class top10_mapperClass extends Mapper<LongWritable, Text, Text, Text> {
	
	@Override
	protected void map(LongWritable key, Text line,	Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException
	{		
		if(!line.toString().isEmpty())
		{
			String[] fields=line.toString().split("\t");	
			String value= "code::\t"+fields[1];
			try 
			{
				context.write(new Text(fields[0].trim()), new Text(value));
			} catch (InterruptedException e)
			{			
				System.out.println("E1: Error in writing top 10 reviews mapper class"+e.getMessage());
			}
		}
	}	

}
