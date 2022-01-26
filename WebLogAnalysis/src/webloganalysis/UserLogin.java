/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webloganalysis;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.imageio.ImageIO;
/**
 *
 * 
 */
public class UserLogin  extends JFrame implements WindowListener , ActionListener{
   private Container container=getContentPane(); 
   private JLabel lblUser=new JLabel("User ID");
   private JLabel lblPass=new JLabel("Password");
   
   private JTextField txtUser=new JTextField(20);
   private JPasswordField txtPass=new JPasswordField(20);
   
   private JButton btnLogin=new JButton("Login");
   private JButton btnReg=new JButton("Register New User");
  
   public UserLogin(String title)
   {
       super(title);
       
       container.setLayout(null);
       container.setBackground(Color.WHITE);
       this.setTitle("Login");
       this.addWindowListener(this);
       this.setSize(500, 250);
       this.setResizable(false);
       final Toolkit toolkit = Toolkit.getDefaultToolkit();
                final Dimension screenSize = toolkit.getScreenSize();
                final int x = (screenSize.width - this.getWidth()) / 2;
                final int y = (screenSize.height - this.getHeight()) / 2;
                this.setLocation(x, y);
                
               // ImageIcon imgThisImg = new ImageIcon("./images/add_user.png");
                ImageIcon imgThisImg=new ImageIcon();
       try {
           imgThisImg = new ImageIcon(ImageIO.read(getClass().getResource("/images/add_user.png")));
       } catch (IOException ex) {
           Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
       }
		JLabel label = new JLabel(imgThisImg);
                label.setSize(250, 250);
                label.setLocation(0, 0);
                container.add(label);
                
                lblUser.setSize(50, 30);
                lblUser.setLocation(250,30);
                container.add(lblUser);
                
                txtUser.setSize(150, 30);
                txtUser.setLocation(320,30);
                container.add(txtUser);
                
                lblPass.setSize(70, 30);
                lblPass.setLocation(250,70);
                container.add(lblPass);
                
                txtPass.setSize(150, 30);
                txtPass.setLocation(320,70);
                
                container.add(txtPass);
                
                btnLogin.addActionListener(this);
                btnLogin.setSize(150, 30);
                btnLogin.setLocation(320,120);
                container.add(btnLogin);
                
                btnReg.addActionListener(this);
                btnReg.setSize(150, 30);
                btnReg.setLocation(320,180);
                container.add(btnReg);
                
                this.setVisible(true);
   }
 public static void main(String[] args) 
 {
     
 }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnLogin)
       {
          if( Data.doUserLogin(txtUser.getText().trim(), txtPass.getText().trim()))
          {
              UserSettings.setLoginUser(txtUser.getText().trim());
              UserSettings.setLoginPass(txtPass.getText().trim());
              UserSettings.setLoginStatus(true);
              UserArea ua=new UserArea("User Environment");
              dispose();
          }
          else
          {
              JOptionPane.showMessageDialog(this, "Login Failed");
          }
       }
       else if(e.getSource()==btnReg)
       {
           UserRegistrationForm urf=new UserRegistrationForm("Register New User");
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
        System.exit(0);
    }
    public void windowIconified(WindowEvent e)
    {}
    public void windowDeiconified(WindowEvent e)
    {}
}
