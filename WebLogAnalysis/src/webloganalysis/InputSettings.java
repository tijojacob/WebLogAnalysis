/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webloganalysis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 *
 * @author tijo
 */
   
public class InputSettings extends JDialog implements ActionListener, WindowListener
{
    private Container container=getContentPane(); 
    private JLabel lblInFol=new JLabel("Input Folder");
    private JTextField txtFolnam=new JTextField(20);
    private JButton btnSel=new JButton("Browse");
    private JButton btnDone=new JButton("Save");
    private JButton btnCan=new JButton("Cancel");
    private UserArea parent;
    
        public InputSettings(UserArea parent,String title)
        {
            super(parent,title,true);
            this.parent=parent;
            container.setLayout(null);
            setSize(500,150);
            this.setResizable(true);
            final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
            lblInFol.setSize(100, 30);
            lblInFol.setLocation(10, 10);
            container.add(lblInFol);
            
            txtFolnam.setSize(250, 30);
            txtFolnam.setLocation(110, 10);
            container.add(txtFolnam);
            
            btnSel.setSize(100, 30);
            btnSel.setLocation(380, 10);
            container.add(btnSel);
            btnSel.addActionListener(this);
            
            btnDone.setSize(100, 30);
            btnDone.setLocation(380, 50);
            container.add(btnDone);
            btnDone.addActionListener(this);
            
            
            btnCan.setSize(100, 30);
            btnCan.setLocation(260, 50);
            container.add(btnCan);
            btnCan.addActionListener(this);
            
            this.addWindowListener(this);
            setVisible(true);
            
            
        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==btnSel)
            {
                JFileChooser jfc= new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               if( jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
               {
                   txtFolnam.setText(jfc.getSelectedFile().getAbsolutePath());
               }
                
            }
            else if(e.getSource()==btnDone)
            {
                if(txtFolnam.getText().trim().equals(""))
                {
                    JOptionPane.showMessageDialog(this, "You must select a folder");
                }
                else
                {
                try
                {
                PrintWriter out=new PrintWriter(new FileWriter(new File("sel_name")));
                out.println(txtFolnam.getText().trim());
                out.close();
                dispose();
                }
                catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
                }
                
            }
            else if(e.getSource()==btnCan)
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
