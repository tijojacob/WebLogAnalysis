/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class Phm_IP {
  static int tit_flag=0;
  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private  static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException
   {
       Configuration conf = context.getConfiguration();
        String param = conf.get("startVal");
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
        
        if(ip.equals(param.toString()))
        {
            
        if(count==9)
        {
             size=itr.nextToken();
             word.set(ip);
             if (size.contains("1"))
             {
                 size="Page Hit";
		 s=1;
		 one=new IntWritable(s);
             }
             else
             {
                 size="Page Mis";
		 s=0;
		 one=new IntWritable(s);
             }
             word.set(ip+'\t'+size);
                  context.write(word, one);       
             
        	}
        } //
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
                       Reducer.Context context
                       ) throws IOException, InterruptedException {
        
      int sum = 0;
      result.set(sum);
      context.write(key, result);
    }
  }

}
