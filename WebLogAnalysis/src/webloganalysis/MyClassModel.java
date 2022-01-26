package webloganalysis;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tijo
 */

   public class MyClassModel extends AbstractTableModel 
{
    public String nam1,nam2,nam3;
    String IPL,IPH;
    Vector data = new Vector();
    Vector columns = new Vector();
     
    public MyClassModel(String fileName,String col1,String col2,String col3) {
            String line;
    
            try {
                
                    FileInputStream fis = new FileInputStream(fileName.trim());
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    nam1=col1;
                    nam2=col2;
                    nam3=col3;
                    String[] arr={nam1,nam2,nam3};
                    employee=arr;
                    StringTokenizer st1 = new StringTokenizer(br.readLine(),"\t");
                    while (st1.hasMoreTokens())
                           columns.addElement(st1.nextToken());
                    while ((line = br.readLine()) != null) {
                            StringTokenizer st2 = new StringTokenizer(line, "\t");
                            while (st2.hasMoreTokens())
                                    data.addElement(st2.nextToken());
                                 
                    }
        /*            System.out.println("Size : "+data.size());
                   int j=1,low=9999999,high=0,temp,AVL=0,AVH=0;
                    
                    for(int i=0;i<data.size()&&j<data.size();i++)
                    {
                        temp=Integer.parseInt(data.get(j).toString());
                        if(low>temp)
                                {
                                    IPL=data.get(j-1).toString();
                                    low=temp;
                                    AVL=Integer.parseInt(data.get(j+1).toString());
                                }
                        if(high<temp)
                        {
                            IPH=data.get(j-1).toString();
                            high=temp;
                            AVH=Integer.parseInt(data.get(j+1).toString());
                        }
                        j=j+3;
                    }
                    data.addElement("LARGEST");
                    data.addElement("");
                    data.addElement("");
                    data.addElement(IPH);
                    data.addElement(high);
                    data.addElement(AVH);
                    
                    data.addElement("LOWEST");
                    data.addElement("");data.addElement("");
                    data.addElement(IPL);
                    data.addElement(low);
                    data.addElement(AVL);
                    
                    System.out.println("LOW :"+low+" HIGH :"+high);
                    */
                    
                    /* String temp,val1i,val3i,val1j,val3j;
                    int i=0,j=0,val2i=0,val2j=0,temp1=0;
                    
                    for(i=1;i<data.size();i=i+3)
                    {
                        for(j=i+3;j<data.size();j=j+3)
                        {
                            val1i=data.get(i-1).toString();
                            val2i=Integer.parseInt(data.get(i).toString());
                            val3i=data.get(i+1).toString();
                            
                            val1j=data.get(j-1).toString();
                            val2j=Integer.parseInt(data.get(j).toString());
                            val3j=data.get(j+1).toString();
                            
                            if (val2i>val2j)
                            {
                                temp1=val2i;
                                data.set(i, val2j);
                                data.set(j, temp1);
                                
                                temp=val1i;
                                data.set(i-1, val1j);
                                data.set(j-1, temp);
                                
                                temp=val3i;
                                data.set(i+1, val3j);
                                data.set(j+1, temp);
                                System.out.println("IP :"+val1i+" SIZE :"+val2i+" Arbitary :"+val3i);
                                System.out.println("IP :"+val1j+" SIZE :"+val2j+" Arbitary :"+val3j);
                            }
                        }
                    }
                    */
                    System.out.println("NAME1 :"+col1+" NAME2 :"+col2+" NAME3 :"+col3);
                    System.out.println("NAME1 :"+nam1+" NAME2 :"+nam2+" NAME3 :"+nam3);
                    br.close();
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
    

    public int getRowCount() {
            return data.size() / getColumnCount();
    }
    
    String[] employee = {nam1,nam2,nam3};
            @Override 
            public int getColumnCount() { 
                return employee.length; 
            } 

            @Override 
            public String getColumnName(int index) { 
                return employee[index]; 
            }
    

    public Object getValueAt(int rowIndex, int columnIndex) {
            return (String) data.elementAt((rowIndex * getColumnCount())
                            + columnIndex);
    }
   // public static void main(String srgs[])
    //{
      //  MyClassModel model=new MyClassModel();
    //}
}