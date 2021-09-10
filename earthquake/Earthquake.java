import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.io.PrintWriter;

class EarthquakeMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
	private PrintWriter out=null;

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		out = new PrintWriter("./logoutput.txt");
		String[] line = value.toString().split(",",12);

		if (line.length != 12) {
            return;
        }

        String outputKey = line[11];
        double outputValue = Double.parseDouble(line[8]);
      
        context.write(new Text(outputKey), new DoubleWritable(outputValue));
    }
}

class EarthquakeReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    @Override
    public void reduce(Text key, Iterable<DoubleWritable> values, 
            Context context) throws IOException, InterruptedException {
        
        // Standard algorithm for finding the max value
        double maxMagnitude = Double.MIN_VALUE;
        for (DoubleWritable value : values) {
            maxMagnitude = Math.max(maxMagnitude, value.get());
        }
        
        context.write(key, new DoubleWritable(maxMagnitude));
    }
}


public class Earthquake {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: hadoopex <input path> <output path>");
			System.exit(-1); 
		}

		Job job = Job.getInstance();
		job.setJobName("Earthquake Measurment");
		job.setJarByClass(Earthquake.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // FileInputFormat.setMaxInputSplitSize(job, 100000);
		// FileInputFormat.setMaxInputSplitSize(job, 500000);
		job.setMapperClass(EarthquakeMapper.class);
		job.setReducerClass(EarthquakeReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
