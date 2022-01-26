/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webloganalysis;

import java.sql.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
/**
 *
 * 
 */
public class Data {
     public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
       // try
       // {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://master:3306/webloganalysis", "root", "");
        
        return connect;
        //}
       /* catch(Exception ex)
        {
            System.out.println(ex.getMessage().trim());
        }*/

    }
 public static String getInputFolder()
 {
     String fileName=new String();
     try
     {
         BufferedReader in=new BufferedReader(new FileReader(new File("sel_name")));
         fileName=in.readLine().trim();
     }
     catch(Exception ex)
     {
         System.out.println(ex.getMessage());
     }
     return fileName;
 }
 
  public static String getInputSize()
 {
     String fileName=new String();
     try
     {
         BufferedReader in=new BufferedReader(new FileReader(new File("fil_Size")));
         fileName=in.readLine().trim();
     }
     catch(Exception ex)
     {
         System.out.println(ex.getMessage());
         System.out.println("Error at Data.java file");
     }
     return fileName;
 }
  

public static String getInputIP()
 {
     String IP=new String();
     try
     {
         BufferedReader in=new BufferedReader(new FileReader(new File("fil_IP")));
         IP=in.readLine().trim();
     }
     catch(Exception ex)
     {
         System.out.println(ex.getMessage());
         System.out.println("Error at Data.java file");
     }
     return IP;
 }  

    
   
    
  
     public static void saveUserInfo(String fullName,String userName,String userPass)
     {
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="INSERT INTO USERLIST(FULLNAME,USERNAME,USERPASS) VALUES('"+fullName.trim()+"','"+userName.trim()+"','"+userPass.trim()+"')";
             st.executeUpdate(sql);
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
     }
     public static boolean isUserExist(String user)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT USERNAME FROM USERLIST WHERE TRIM(USERNAME)='"+user.trim()+"'";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         
         return flag;
     }
     public static void approveNewUser(String newUser)
     {
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="UPDATE  USERLIST SET STATUS=1 WHERE TRIM(USERNAME)='"+newUser.trim()+"'"; 
             st.executeUpdate(sql);
             st.close();
             cn.close(); 
         }catch(Exception ex)
         {
             System.err.println(ex.getMessage());
         }
     }
     public static ArrayList getNewUsers()
     {
         ArrayList list=new ArrayList();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT USERNAME FROM USERLIST WHERE ROLE=0 AND STATUS=0";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
                 list.add(rs.getString("USERNAME"));
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return list;
     }
     public static ArrayList getApprovedUsers()
     {
         ArrayList list=new ArrayList();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT USERNAME FROM USERLIST WHERE ROLE=0 AND STATUS=1";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
                 list.add(rs.getString("USERNAME"));
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return list;
     }
     public static ArrayList getRevokedUsers()
     {
         ArrayList list=new ArrayList();
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT USERNAME FROM USERLIST WHERE ROLE=0 AND STATUS=-1";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
                 list.add(rs.getString("USERNAME"));
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return list;
     }
     public static boolean doAdminLogin(String userName,String userpass)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT * FROM USERLIST WHERE TRIM(USERNAME)='"+userName.trim()+"' AND TRIM(USERPASS)='"+userpass.trim()+"' AND ROLE=1 AND STATUS=1";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return flag;
     }
     public static boolean doChangeAdminPassword(String u,String op,String np)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="UPDATE USERLIST SET USERPASS='"+np.trim()+"' WHERE TRIM(USERNAME)='"+u.trim()+"' AND TRIM(USERPASS)='"+op.trim()+"'";
            
             if(st.executeUpdate(sql)>0)
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.err.println(ex.getMessage());
         }
         
         return flag;
     }
     public static boolean doUserLogin(String userName,String userpass)
     {
         boolean flag=false;
         try
         {
             Connection cn=getConnection();
             Statement st=cn.createStatement();
             String sql="SELECT * FROM USERLIST WHERE TRIM(USERNAME)='"+userName.trim()+"' AND TRIM(USERPASS)='"+userpass.trim()+"'";
             ResultSet rs=st.executeQuery(sql);
             if(rs.next())
             {
                 flag=true;
             }
             st.close();
             cn.close();
         }catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }
         return flag;
     }

public static boolean saveResult(String inputFileName)
{
    boolean saved=false;
    try
    {
        Connection cn=getConnection();
        
        BufferedReader in=new BufferedReader(new FileReader(new File(inputFileName)));
        String line=in.readLine();
        while(line!=null)
        {
            String[] parts=line.split("\t");
            
            String sql="INSERT INTO temp_table(ip,size,arvalue) VALUES ('"+parts[0]+"',"+parts[1]+","+parts[2]+")";
            Statement st=cn.createStatement();
            st.executeUpdate(sql);
            line=in.readLine();
            st.close();
        }
        
        cn.close();
        
    }catch(Exception ex)
    {
        System.out.println(ex.getMessage().trim());
    }
    
    return saved;

}
}