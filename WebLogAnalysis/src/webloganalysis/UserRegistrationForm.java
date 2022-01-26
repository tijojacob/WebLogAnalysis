/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webloganalysis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 *
 */
public class UserRegistrationForm extends JFrame implements WindowListener , ActionListener{
    private Container container=getContentPane(); 
    
    private JLabel lbName=new JLabel("Full Name");
    private JLabel lblUserID=new JLabel("User ID");
    private JLabel lblPassword=new JLabel("Password");
    
    private JTextField txtName=new JTextField(20);
    private JTextField txtUserID=new JTextField(20);
    private JPasswordField txtPass=new JPasswordField(20);
    
    private JButton btnRegister=new JButton("Register User");
    
    public UserRegistrationForm(String title)
    {
       super(title);
       
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       
       this.addWindowListener(this);
       this.setSize(380, 200);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
                ImageIcon imgThisImg = new ImageIcon("images/accept_user.png");
		JLabel label = new JLabel(imgThisImg);
                label.setSize(128, 128);
                label.setLocation(10, 10);
                container.add(label);
                
                lbName.setSize(80, 30);
                lbName.setLocation(150,10);
                container.add(lbName);
                
                txtName.setSize(100, 30);
                txtName.setLocation(240,10);
                container.add(txtName);
                
                lblUserID.setSize(100, 30);
                lblUserID.setLocation(150,50);
                container.add(lblUserID);
                
                txtUserID.setSize(100, 30);
                txtUserID.setLocation(240,50);
                container.add(txtUserID);
                
                lblPassword.setSize(150, 30);
                lblPassword.setLocation(150,90);
                container.add(lblPassword);
                
                txtPass.setSize(100, 30);
                txtPass.setLocation(240,90);
                container.add(txtPass);
                
                btnRegister.addActionListener(this);
                btnRegister.setSize(140, 30);
                btnRegister.setLocation(200,130);
                container.add(btnRegister);
                
                this.setVisible(true);
    }
    public static void main(String[] args)
    {
        UserRegistrationForm urf=new UserRegistrationForm("Register New User");
    }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnRegister)
       {
           if(txtName.getText().trim().equals("") || txtUserID.getText().trim().equals("") || txtPass.getText().trim().equals(""))
           {
               JOptionPane.showMessageDialog(this, "Incomplete User Details");
           }
           else
           {
           String selectedUser=txtUserID.getText().trim();
           if(Data.isUserExist(selectedUser))
           {
               JOptionPane.showMessageDialog(this, "User Already Exists");
           }
           else
           {
               Data.saveUserInfo(txtName.getText().trim(), selectedUser, txtPass.getText().trim());
               JOptionPane.showMessageDialog(this, "User Added Successfully");
               dispose();
           }
           }
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
    { dispose(); }
    public void windowIconified(WindowEvent e)
    {}
    public void windowDeiconified(WindowEvent e)
    {}
}
