import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class top10Business {
	
	public static void main(String[] args) throws IOException,ClassNotFoundException 
	{
			Configuration conf = new Configuration();
			String[] otherArgs = new GenericOptionsParser(conf, args)
					.getRemainingArgs();
			
			if (otherArgs.length != 2) 
			{
				System.err.println("check number of arguments");
				System.exit(2);
			}
			
			@SuppressWarnings("deprecation")
			Job job = new Job(conf, "Top 10 Business rating wise");
			
			job.setJarByClass(top10Business.class);
			
			Path inputFile = new Path(otherArgs[0]);
			Path outputFile = new Path(otherArgs[1]);
			
			FileInputFormat.addInputPath(job, inputFile);
			FileOutputFormat.setOutputPath(job, outputFile);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(FloatWritable.class);
			
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(FloatWritable.class);
			
			job.setMapperClass(mapperClass.class);
			job.setReducerClass(reducerClass.class);
			
			FileInputFormat.setMinInputSplitSize(job, 500000000);
			
			try {
				System.exit(job.waitForCompletion(true) ? 1 : 0);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
	
	public static class reducerClass extends Reducer<Text, FloatWritable, Text, FloatWritable> {

		HashMap<String, Float> Hmap = new HashMap<String, Float>();

		@Override
		protected void reduce(Text business_id, Iterable<FloatWritable> stars,
				Context context) throws IOException, InterruptedException {

			FloatWritable average = new FloatWritable(0);
			float total = 0;
			int count = 0;
			for (FloatWritable star : stars) {
				total = total + star.get();
				count++;
			}

			float avg = total / count;
			average.set(avg);
			Hmap.put(business_id.toString(), avg);			
			
		}
		protected void cleanup(Reducer<Text, FloatWritable, Text, FloatWritable>.Context context)
				throws IOException, InterruptedException {

			Map<String, Float> sortedMap = new TreeMap<String, Float>(new myComparator(Hmap));
			sortedMap.putAll(Hmap);
			int i = 0;
			for (Map.Entry<String, Float> entry : sortedMap.entrySet()) 
			{
				context.write(new Text(entry.getKey()),	new FloatWritable(entry.getValue()));
				i++;
				if (i == 10)
				{
					break;
				}
			}
		}
	}
	
	public static class mapperClass extends	Mapper<LongWritable, Text, Text, FloatWritable> {
		static String records = "";
		
		protected void map(LongWritable baseAddress, Text line, Context context)
				throws IOException, InterruptedException {

			Text business_id = new Text();
			FloatWritable stars = new FloatWritable(1);

			records = records.concat(line.toString());
			String[] fields = records.split("::");
			if (fields.length == 4) 
			{		
					String id = fields[2].trim();
					String rating = fields[3].trim();
					business_id.set(id);
					stars.set(Float.parseFloat(rating));
					context.write(business_id, stars);				
					records = "";
			}
		}
	}		
				
	
}