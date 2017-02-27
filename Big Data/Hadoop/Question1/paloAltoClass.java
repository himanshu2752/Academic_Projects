import java.io.IOException;
import java.util.Hashtable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class paloAltoClass {
	public static class q1PaloAlto extends
			Mapper<LongWritable, Text, Text, NullWritable> 
			{
		public static Hashtable<Integer, String> hashTabl = new Hashtable<>();
		public static String records = "";
	//	public final static IntWritable intwr = new IntWritable();

		
		public void map(LongWritable baseAddress, Text value, Context context)
				throws IOException, InterruptedException {

			Text categories = new Text();
			records = records.concat(value.toString());
			String[] array_fields = records.split("::");
			if (array_fields.length == 3) 
			{
				if (array_fields[1].contains("Palo Alto") || array_fields[1].contains("palo alto")) 
				{
					
					String s = array_fields[2].trim() ;
					s = s.replaceAll("\\p{P}","");
					s = s.substring(4, s.length());
					if (!hashTabl.containsValue(s))
					{
					categories.set(s);
					hashTabl.put(hashTabl.size()+1, s);
					NullWritable nullOb = NullWritable.get();
					context.write(categories, nullOb);
					}
				}
				records = "";
			}
		}
	}
	
	/**
	*	Main function for part1
	*
	*/
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InterruptedException {

		Configuration config = new Configuration();
		
		String[] otherArgs = new GenericOptionsParser(config, args)
				.getRemainingArgs();

				
		if (otherArgs.length != 2) 
		{
			System.err.println("Error - check word count, Incompatible Number Of Arguments");
			System.exit(2);
		}

		@SuppressWarnings("deprecation")
		Job jobObj = new Job(config, "Count of Palo Alto");

		jobObj.setJarByClass(paloAltoClass.class);

		Path inputFile = new Path(otherArgs[0]);
		Path outputFile = new Path(otherArgs[1]);

		FileInputFormat.addInputPath(jobObj, inputFile);
		FileOutputFormat.setOutputPath(jobObj, outputFile);

		jobObj.setOutputKeyClass(Text.class);
		jobObj.setOutputValueClass(NullWritable.class);

		jobObj.setMapperClass(q1PaloAlto.class);
		jobObj.setNumReduceTasks(0);
		FileInputFormat.setMinInputSplitSize(jobObj, 500000000);

		System.exit(jobObj.waitForCompletion(true) ? 1 : 0);
	}

}