package webloganalysis;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FileSizeJob {
  static int tit_flag=0;
  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private  static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException
   {
       Configuration conf = context.getConfiguration();
        String param = conf.get("startVal");
       int file=0;
        int count=0;
        int s=0;
        String ip=new String();
        String size=new String();
        String strVal=value.toString();
      StringTokenizer itr = new StringTokenizer(strVal,",");
      if(!strVal.contains("ip"))
      {
      if(tit_flag>0)  
      {
      while (itr.hasMoreTokens()) 
      {
        
        count++;
        if(count==1)
        {
            ip=itr.nextToken();
        }
        
        if(count==8)
        {
             size=itr.nextToken();
             
             word.set(ip);
            // word.set(ip+"_"+size);
            if(!size.contains("sid"))
            {
           if( size.contains("."))
           {
               int end_ind=size.indexOf(".");
               size=size.substring(0, end_ind);
               s=Integer.parseInt(size);
             
               one = new IntWritable(0);
               //if(param!=null)
               //{
              file=Integer.parseInt(param.trim());
             // System.out.println("FILE : "+file);
              //file=10000;
               //}
           }
               
                   if(s>file)
                   
                   {
                    System.out.println("S : "+s+"  FILE : "+file);
                    word.set(ip+"\t"+s);
                    
                    context.write(word, one);
                   }
           
            }    
        }
    word.set(itr.nextToken());
      }
      
    }
      tit_flag++;
   }
   }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
        
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

}
