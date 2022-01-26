package webloganalysis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tijo
 */
import java.util.Vector;
import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class TableExample extends JDialog implements WindowListener, ActionListener
{
    private UserArea parent;
    private String fileName;
    private String nam1,nam2,nam3;
    private JButton btnClos=new JButton("Close");
    private Container content=getContentPane();
    public TableExample(UserArea parent,String fileName,String col1,String col2,String col3)
    {
        super(parent,"",true);
        this.parent=parent;
        this.fileName=fileName;
        this.nam1=col1;
        this.nam2=col2;
        this.nam3=col3;
        content.setLayout(new BorderLayout());
        MyClassModel model = new MyClassModel(fileName,nam1,nam2,nam3);
         System.out.println("TableExample NAME1 :"+nam1+" NAME2 :"+nam2+" NAME3 :"+nam3);
        JTable table = new JTable(model);
         
        //add the table to the frame
        content.add(new JScrollPane(table),BorderLayout.CENTER);
         content.add(btnClos,BorderLayout.SOUTH);
         btnClos.addActionListener(this);
        this.setTitle("Table Example");
      //  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.setSize(400,400);
        this.setVisible(true);
        this.addWindowListener(this);
        
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
          //  @Override
            public void run() {
                //new TableExample();
            }
        });
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==btnClos)   
         {
             dispose();
         }
    }
    
    public void windowActivated(WindowEvent e)
    {}
    public void windowDeactivated(WindowEvent e)
    {}
    public void windowOpened(WindowEvent e)
    {}
    public void windowClosed(WindowEvent e)
    {}
    public void windowClosing(WindowEvent e)
    { 
        dispose();
    }
    public void windowIconified(WindowEvent e)
    {}
    public void windowDeiconified(WindowEvent e)
    {}

    
}