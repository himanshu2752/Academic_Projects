import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

@SuppressWarnings("deprecation")
public class inMemoryJoin {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		
		Configuration conf=new Configuration();
		String[] otherArgs=new GenericOptionsParser(conf,args).getRemainingArgs();
		
		if(otherArgs.length!=3)
		{
			System.out.println("Check number of Arguments");
			System.exit(2);
		}
		
		Path inputFile1=new Path(otherArgs[0]);
		Path inputFile2 = new Path(otherArgs[1]);
		Path outputFile=new Path(otherArgs[2]);
		
		Job job1 = Job.getInstance(conf, "UserID");
		DistributedCache.addCacheFile(inputFile1.toUri(), job1.getConfiguration());
		job1.setJarByClass(inMemoryJoin.class);			
		
		
		job1.setMapperClass(stanford_mapper.class);
		job1.setReducerClass(stanford_Reducer.class);
		FileInputFormat.addInputPath(job1, inputFile2);		
		
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		
		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(Text.class);
		
		FileInputFormat.setMinInputSplitSize(job1, 500000000);
		FileOutputFormat.setOutputPath(job1, outputFile);
		job1.waitForCompletion(true);
					
	}
	public static class stanford_mapper extends Mapper<Object, Text, Text, Text>
	{
		static BufferedReader bufferRead ;
		static HashMap<String, String> hMap = new HashMap<>();
		
		protected void setup(Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException 
		{		
			@SuppressWarnings("deprecation")
			Path[] cache = context.getLocalCacheFiles();
			if (cache == null)
			{}
			else
			{					
				for (Path path:cache) {
					if(true)
					{							
						hMapLoad(path,context);
					}
				}
				
			}
		}
	
	
	private void hMapLoad(Path path, Mapper<Object, Text, Text, Text>.Context context) throws IOException {
		// TODO Auto-generated method stub
		String line = "";
		String value = "";

		try {
			
			bufferRead = new BufferedReader(new FileReader(path.getName().toString()));
		
			while((line = bufferRead.readLine()) != null )
			{
				String[] line_add = line.split("::");
				if(line_add[1].contains("Stanford"))
					
					hMap.put(line_add[0].trim(),value);
			}
			
		} catch (FileNotFoundException e)
		{			
			System.out.println(e.getMessage());			

		} 
		finally {
			if(bufferRead!=null)
				try {
					bufferRead.close();
				}
			catch (IOException e) 
			{System.out.println(e.getMessage());}
		}
	}
	
	public void map(Object key, Text value, Context context
			) throws IOException, InterruptedException {

		Text output = new Text();
		Text userId = new Text();
		
		String[] ratings = value.toString().trim().split("::");
		String address = ratings[2].trim();
		if (hMap.containsKey(address))
		{
			String user_id = ratings[1].trim();
			String stars = ratings[3].trim();
			userId.set(user_id);
			output.set(stars);
			context.write(userId,output);
		}
	}	
	
}
	public static class stanford_Reducer extends Reducer<Text, Text, Text, Text> 
	{
    
    public void reduce(Text key, Iterable<Text> values, Context context)throws IOException {
        for(Text review : values)
        {
        	try {
				context.write(key, review);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			} 
        }
    }	
	
	}
}