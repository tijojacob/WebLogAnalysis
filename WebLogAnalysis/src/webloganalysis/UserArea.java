/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webloganalysis;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import weblog.*;

//import webloganalysis.MyClassModel;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.*;
/**
 *
 * 
 */
public class UserArea extends JFrame implements WindowListener,ActionListener{
    private JMenu mnuApp= new JMenu("Application");
    private JMenu mnuJobs= new JMenu("Jobs");
    private JMenu mnuResult= new JMenu("Results");
    private JMenu mnuInSel = new JMenu("Input");
    
    private JMenuItem mnuQuit = new JMenuItem("Quit");
    private JMenu mnuSizeJob = new JMenu("File Size Job");
    private JMenuItem mnuReqCont = new JMenuItem("Requested Content");
    private JMenu mnuPageHit = new JMenu("Page Hit/Mis");
    //private JMenuItem mnuOutput = new JMenuItem("Output Viewer");
    private JMenu mnuFSJ = new JMenu("File Size Job");
    private JMenuItem mnuRC = new JMenuItem("Requested Content");
    private JMenu mnuPH = new JMenu("Page Hit");
    private JMenuItem mnuPHIP = new JMenuItem("Page Hit/Mis with IP");
    private JMenuItem mnuPHN = new JMenuItem("Page Hit/Mis");
    
    private JMenuItem mnuInput = new JMenuItem("Input Folder");
    private JMenuItem mnuIP = new JMenuItem("IP");
    private JMenuItem mnuSize = new JMenuItem("Size");
    private JMenuItem mnuPageHit_Miss = new JMenuItem("Page Hit/Mis");
    private JMenuItem mnuPhmIP = new JMenuItem("Page Hit/Mis with IP");
    private JMenuBar bar = new JMenuBar();
    
    
    private JMenuItem mnuOutIP = new JMenuItem("IP");
    private JMenuItem mnuOutSize = new JMenuItem("Size");
    
    public UserArea(String title)
    {
        super(title);
        
                setSize(800,600);
                final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
               this.setLocation(x, y);
               // setSize(screenSize.width,screenSize.height);
               
                Container con=getContentPane();
                con.setBackground(Color.BLACK);
                ImageIcon imgThisImg=new ImageIcon();
       try {
           imgThisImg = new ImageIcon(ImageIO.read(getClass().getResource("/images/web_log.png")));
       } catch (IOException ex) {
           Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
       }
                
                //ImageIcon imgThisImg = new ImageIcon("images/web_log.png");
                JLabel l=new JLabel(imgThisImg);
    
                con.setLayout(new BorderLayout());
                con.add(l,BorderLayout.CENTER);
    
        addWindowListener(this);
        this.setResizable(false);
        
               
        mnuQuit.setMnemonic('Q');
        mnuQuit.addActionListener(this);
        mnuSizeJob.addActionListener(this);
        mnuReqCont.addActionListener(this);
        mnuPageHit.addActionListener(this);
        //mnuOutput.addActionListener(this);
        mnuInput.addActionListener(this);
        mnuFSJ.addActionListener(this);
        mnuRC.addActionListener(this);        
        mnuPHIP.addActionListener(this);
        mnuPHN.addActionListener(this);
        
        mnuJobs.add(mnuSizeJob);
        mnuApp.add(mnuQuit);
        mnuJobs.add(mnuReqCont);
        mnuJobs.add(mnuPageHit);
        //mnuResult.add(mnuOutput);
        mnuInSel.add(mnuInput);
        mnuResult.add(mnuFSJ);      
        mnuResult.add(mnuRC);
        mnuResult.add(mnuPH);
        
        mnuSizeJob.add(mnuIP);
        mnuSizeJob.add(mnuSize);
        
        mnuPageHit.add(mnuPageHit_Miss);
        mnuPageHit.add(mnuPhmIP);
        mnuFSJ.add(mnuOutIP);
        mnuFSJ.add(mnuOutSize);
        mnuPH.add(mnuPHN);
        mnuPH.add(mnuPHIP);
        
        
        mnuPageHit_Miss.addActionListener(this);
        mnuPhmIP.addActionListener(this);
        mnuIP.addActionListener(this);
        mnuSize.addActionListener(this);
        mnuOutIP.addActionListener(this);
        mnuOutSize.addActionListener(this);
                
        bar.add(mnuApp);
        bar.add(mnuJobs);
        bar.add(mnuInSel);
        bar.add(mnuResult);
        setJMenuBar(bar);
        
        setVisible(true);
    }
    public static void main(String[] args) {
        // TODO code application logic here
       
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==mnuQuit)   
         {
             System.exit(0);
         }
        
        else if(e.getSource()==mnuIP)   
         {
             try
             {
                 IPSelection F1=new IPSelection(this,"IP Selection");
                 if(F1.getContinueJob())
                 {
         
             Configuration conf = new Configuration();
    conf.set("startVal", Data.getInputIP()); //Data.getInputIP().toString()
    
    JOptionPane.showMessageDialog(this, "Starting Job");//gettexxt
    long sTime=System.currentTimeMillis();
    //Job job = Job.getInstance(conf, "File Size Job");
    Job job = new Job(conf);
    job.setJarByClass(FileSizeJobIP.class);
    job.setMapperClass(FileSizeJobIP.TokenizerMapper.class);
    job.setCombinerClass(FileSizeJobIP.IntSumReducer.class);
    job.setReducerClass(FileSizeJobIP.IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    
    job.setOutputValueClass(IntWritable.class);
   // FileInputFormat.addInputPath(job, new Path(args[0]));
   // FileOutputFormat.setOutputPath(job, new Path(args[1]));
   File f=new File("/home/tijo/output(FileSizeJob)IP");
   if(f.exists())
   {
       FileUtils.deleteDirectory(f);
   }
   
   FileInputFormat.addInputPath(job, new Path(Data.getInputFolder()));
    FileOutputFormat.setOutputPath(job, new Path("/home/tijo/output(FileSizeJob)IP"));
   // System.exit(job.waitForCompletion(true) ? 0 : 1);
   job.waitForCompletion(true);
   long eTime=System.currentTimeMillis();
   long timeEllapsed=(eTime-sTime)/1000;
   JOptionPane.showMessageDialog(this, "Job Completed in "+timeEllapsed+" sec");
                 }
                 else
             {
                   JOptionPane.showMessageDialog(this, "Job Cancelled");  
             }
             }
             catch(Exception ex)
             {
                 System.out.println(ex.getMessage());
             }
             
             
             }
        
        
        else if(e.getSource()==mnuPhmIP)   
         {
             try
             {
                 IPSelection F1=new IPSelection(this,"IP Selection");
                 if(F1.getContinueJob())
                 {
         
             Configuration conf = new Configuration();
    conf.set("startVal", Data.getInputIP()); //Data.getInputIP().toString()
    
    JOptionPane.showMessageDialog(this, "Starting Job");//gettexxt
    long sTime=System.currentTimeMillis();
    //Job job = Job.getInstance(conf, "File Size Job");
    Job job = new Job(conf);
    job.setJarByClass(Phm_IP.class);
    job.setMapperClass(Phm_IP.TokenizerMapper.class);
    job.setCombinerClass(Phm_IP.IntSumReducer.class);
    job.setReducerClass(Phm_IP.IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    
    job.setOutputValueClass(IntWritable.class);
   // FileInputFormat.addInputPath(job, new Path(args[0]));
   // FileOutputFormat.setOutputPath(job, new Path(args[1]));
   File f=new File("/home/tijo/OUT(PageH-M)IP");
   if(f.exists())
   {
       FileUtils.deleteDirectory(f);
   }
   
   FileInputFormat.addInputPath(job, new Path(Data.getInputFolder()));
    FileOutputFormat.setOutputPath(job, new Path("/home/tijo/OUT(PageH-M)IP"));
   // System.exit(job.waitForCompletion(true) ? 0 : 1);
   job.waitForCompletion(true);
   long eTime=System.currentTimeMillis();
   long timeEllapsed=(eTime-sTime)/1000;
   JOptionPane.showMessageDialog(this, "Job Completed in "+timeEllapsed+" sec");
                 }
                 else
             {
                   JOptionPane.showMessageDialog(this, "Job Cancelled");  
             }
             }
             catch(Exception ex)
             {
                 System.out.println(ex.getMessage());
             }
             
             
             }
        
        else if(e.getSource()==mnuSize)   
         {
             try
             {
                 FileSizeSelection F1=new FileSizeSelection(this,"File Size");
                 if(F1.getContinueJob())
                 {
             Configuration conf = new Configuration();
    conf.set("startVal", Data.getInputSize());
    JOptionPane.showMessageDialog(this, "Starting Job");//gettexxt
    long sTime=System.currentTimeMillis();
    
    
    //Job job = Job.getInstance(conf, "File Size Job");
    Job job = new Job(conf);
    job.setJarByClass(FileSizeJob.class);
    job.setMapperClass(FileSizeJob.TokenizerMapper.class);
    job.setCombinerClass(FileSizeJob.IntSumReducer.class);
    job.setReducerClass(FileSizeJob.IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
   // FileInputFormat.addInputPath(job, new Path(args[0]));
   // FileOutputFormat.setOutputPath(job, new Path(args[1]));
   File f=new File("/home/tijo/output(FileSizeJob)");
   if(f.exists())
   {
       FileUtils.deleteDirectory(f);
   }
   FileInputFormat.addInputPath(job, new Path(Data.getInputFolder()));
    FileOutputFormat.setOutputPath(job, new Path("/home/tijo/output(FileSizeJob)"));
   // System.exit(job.waitForCompletion(true) ? 0 : 1);
   job.waitForCompletion(true);
   long eTime=System.currentTimeMillis();
   long timeEllapsed=(eTime-sTime)/1000;
   JOptionPane.showMessageDialog(this, "Job Completed in "+timeEllapsed+" sec");
                 }
                 else
             {
                   JOptionPane.showMessageDialog(this, "Job Cancelled");  
             }
             }
             catch(Exception ex)
             {
                 System.out.println(ex.getMessage());
             }
             
             
             }
             
         
        else if(e.getSource()==mnuReqCont)   
         {
             try
             {
                 IPSelection F1=new IPSelection(this,"IP Selection");
                 if(F1.getContinueJob())
                 {
         
             Configuration conf = new Configuration();
    conf.set("startVal", Data.getInputIP()); //Data.getInputIP().toString()
    
    JOptionPane.showMessageDialog(this, "Starting Job");//gettexxt
    long sTime=System.currentTimeMillis();
    //Job job = Job.getInstance(conf, "File Size Job");
    Job job = new Job(conf);
    job.setJarByClass(Requested_Content.class);
    job.setMapperClass(Requested_Content.TokenizerMapper.class);
    job.setCombinerClass(Requested_Content.IntSumReducer.class);
    job.setReducerClass(Requested_Content.IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    
    job.setOutputValueClass(IntWritable.class);
   // FileInputFormat.addInputPath(job, new Path(args[0]));
   // FileOutputFormat.setOutputPath(job, new Path(args[1]));
   File f=new File("/home/tijo/OUT(Req_Cont)IP");
   if(f.exists())
   {
       FileUtils.deleteDirectory(f);
   }
   
   FileInputFormat.addInputPath(job, new Path(Data.getInputFolder()));
    FileOutputFormat.setOutputPath(job, new Path("/home/tijo/OUT(Req_Cont)IP"));
   // System.exit(job.waitForCompletion(true) ? 0 : 1);
   job.waitForCompletion(true);
   long eTime=System.currentTimeMillis();
   long timeEllapsed=(eTime-sTime)/1000;
   JOptionPane.showMessageDialog(this, "Job Completed in "+timeEllapsed+" sec");
                 }
                 else
             {
                   JOptionPane.showMessageDialog(this, "Job Cancelled");  
             }
             }
             catch(Exception ex)
             {
                 System.out.println(ex.getMessage());
             }
             
             
             }
        
        
        else if(e.getSource()==mnuPageHit_Miss)   
         {
             try
             {
                  
                 JOptionPane.showMessageDialog(this, "Starting Job");
                 long sTime=System.currentTimeMillis();
             Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Page Hit/Mis");
    job.setJarByClass(PageHit_Miss.class);
    job.setMapperClass(PageHit_Miss.TokenizerMapper.class);
    job.setCombinerClass(PageHit_Miss.IntSumReducer.class);
    job.setReducerClass(PageHit_Miss.IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
   // FileInputFormat.addInputPath(job, new Path(args[0]));
   // FileOutputFormat.setOutputPath(job, new Path(args[1]));
   File f=new File("/home/tijo/OUT(PageH-MS)");
   if(f.exists())
   {
       FileUtils.deleteDirectory(f);
   }
   
   FileInputFormat.addInputPath(job, new Path(Data.getInputFolder()));
    FileOutputFormat.setOutputPath(job, new Path("/home/tijo/OUT(PageH-MS)"));
   // System.exit(job.waitForCompletion(true) ? 0 : 1);
   job.waitForCompletion(true);
   long eTime=System.currentTimeMillis();
   long timeEllapsed=(eTime-sTime)/1000;
   JOptionPane.showMessageDialog(this, "Job Completed in "+timeEllapsed+" sec");
                 
             }
             catch(Exception ex)
             {
                 System.out.println(ex.getMessage());
             }
         }
        else if(e.getSource()==mnuOutIP)
        {
             //OutputViewer out=new OutputViewer(this,"Output");
             
            // OutputViewer out=new OutputViewer(this,"/home/tijo/output(FileSizeJob)/part-r-00000");
            //Data.saveResult("/home/tijo/output(FileSizeJob)/part-r-00000");
            TableExample te=new TableExample(this,"/home/tijo/output(FileSizeJob)IP/part-r-00000","IP","Size","Arbitary Value");
            
        }
        else if(e.getSource()==mnuOutSize)
        {
            TableExample te=new TableExample(this,"/home/tijo/output(FileSizeJob)/part-r-00000","IP","Size","Arbitary Value");
        }
        else if(e.getSource()==mnuRC)
            {
             //OutputViewer out=new OutputViewer(this,"/home/tijo/output(ReqestedContent)/part-r-00000");
                TableExample te=new TableExample(this,"/home/tijo/OUT(Req_Cont)IP/part-r-00000","IP","Content Name","Arbitary Value");
            }
        else if(e.getSource()==mnuPHN)
            {
             //OutputViewer out=new OutputViewer(this,"/home/tijo/output(PageHit)/part-r-00000");
                TableExample te=new TableExample(this,"/home/tijo/OUT(PageH-MS)/part-r-00000","IP","Hit/Mis","idx");
            }
        else if(e.getSource()==mnuPHIP)
            {
             //OutputViewer out=new OutputViewer(this,"/home/tijo/output(PageHit)/part-r-00000");
                TableExample te=new TableExample(this,"/home/tijo/OUT(PageH-M)IP/part-r-00000","IP","Hit/Mis","idx");
            }
        
        else if(e.getSource()==mnuInput)
        {
            InputSettings s=new InputSettings(this,"Input Settings");
        }
        
        
    }
    
    public void windowClosing(WindowEvent e)
    {
    System.exit(0);
    }
    public void windowClosed(WindowEvent e)
    {}
    public void windowOpened(WindowEvent e)
    {}
    public void windowActivated(WindowEvent e)
    {}
    public void windowDeactivated(WindowEvent e)
    {}
    public void windowIconified(WindowEvent e)
    {}
    public void windowDeiconified(WindowEvent e)
    {}
     
}
