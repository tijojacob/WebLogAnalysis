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
public class OutputViewer extends JDialog implements ActionListener, WindowListener
{
    private Container container=getContentPane(); 
    private JTextArea txt=new JTextArea();
    private JButton btnClos=new JButton("Close");
    private JScrollPane pane=new JScrollPane(txt);
    private UserArea parent;
    
    public OutputViewer(UserArea parent,String title)
        {
            super(parent,title,true);
            this.parent=parent;
            container.setLayout(null);
            setSize(500,500);
            this.setResizable(true);
            final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
            
            pane.setSize(this.getWidth(), 400);
            pane.setLocation(0, 0);
            container.add(pane);
            txt.setEditable(false);
            btnClos.setSize(100, 30);
            btnClos.setLocation(200, 410);
            container.add(btnClos);
            btnClos.addActionListener(this);
            
            this.addWindowListener(this);
            try
            {
                File f=new File(title);
                if(f.exists())
                {
                    BufferedReader in=new BufferedReader(new FileReader(f));
                    String line=in.readLine().trim();
                    while(line!=null)
                    {
                    txt.append(line+'\n');
                    line=in.readLine().trim();
                    }
                   in.close();
                }
                        
                
            }catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            setVisible(true);
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