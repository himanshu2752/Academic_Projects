import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class sideJoin
	{	

	public static class review_mapper extends Mapper<LongWritable, Text, Text, FloatWritable> 
	{
		static String record = "";
		
		protected void map(LongWritable baseAddress, Text line, Context context)
				throws IOException {

			Text business_id = new Text();
			FloatWritable stars = new FloatWritable(1);

			record = record.concat(line.toString());
			String[] fields = record.split("::");
			if (fields.length == 4) 
			{				
					business_id.set(fields[2].trim());
					stars.set(Float.parseFloat(fields[3].trim()));
					try {
						context.write(business_id, stars);
					} catch (InterruptedException e) {
						System.out.println("B1: Error in mapper:"+e.getMessage());
					}				
				record = "";
			}
		}
	}	

	public static class review_reducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

		HashMap<String, Float> Hmap = new HashMap<String, Float>();

		@Override
		protected void reduce(Text business_id, Iterable<FloatWritable> stars,	Context context) throws IOException 
		{
			float total = 0;
			int count = 0;
			FloatWritable average = new FloatWritable(0);
			
			for (FloatWritable star : stars)
			{
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
					break;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ClassNotFoundException	{

		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();

		if (otherArgs.length != 3)
		{
			System.err.println("Incompatible Number Of Arguments");
			System.exit(2);
		}
		
		Job job1 = new Job(conf, " Details of top 10 Businesses");

		job1.setJarByClass(sideJoin.class);

		Path inputFile1 = new Path(otherArgs[0]);
		Path inputFile2 = new Path(otherArgs[1]);
		Path outputFile = new Path(otherArgs[2]);
		Path intermidiateFile = new Path("/hxp151330/intermediate_data");

		FileInputFormat.addInputPath(job1, inputFile1);
		FileOutputFormat.setOutputPath(job1, intermidiateFile);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(FloatWritable.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(FloatWritable.class);

		job1.setMapperClass(review_mapper.class);
		job1.setReducerClass(review_reducer.class);

		FileInputFormat.setMinInputSplitSize(job1, 500000000);

		try {
			job1.waitForCompletion(true);
		} catch (InterruptedException e) {
			System.out.println("A1: Error in Job1:"+e.getMessage());
		}

				
		Job job2 = new Job(conf, "Joiner");
		job2.setJarByClass(sideJoin.class);

		job2.setReducerClass(join_ReducerClass.class);

		MultipleInputs.addInputPath(job2, intermidiateFile,
				TextInputFormat.class, top10_mapperClass.class);
		MultipleInputs.addInputPath(job2, inputFile2, TextInputFormat.class,
				allDetails_mapperClass.class);

		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);

		FileOutputFormat.setOutputPath(job2, outputFile);
		FileInputFormat.setMinInputSplitSize(job2, 500000000);

		try {
			job2.waitForCompletion(true);
		} catch (InterruptedException e) {
			System.out.println("A2: Error in job2:"+e.getMessage());
		}
		
		// you can comment the below two lines if you want to check the intermediate file
		org.apache.hadoop.fs.FileSystem fs = org.apache.hadoop.fs.FileSystem.get(conf);
		fs.delete(intermidiateFile, true);
	}

}
