package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Mainsys.MainSystem;
import User.Administrator;



public class AddCustomer extends JFrame {

   private JPanel contentPane;
   private JTextField txtName;
   private JTextField txtPnum;
   private JTextField txtAddress;
   private JPasswordField pwdPassword;
   private JPasswordField pwdcheck;
   private MainSystem mainSystem;

   public AddCustomer(MainSystem mainSystem) {
	   this.mainSystem=mainSystem;
      setResizable(false);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 438, 316);
      contentPane = new JPanel();
      contentPane.setForeground(Color.DARK_GRAY);
      contentPane.setBackground(SystemColor.inactiveCaptionBorder);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblName = new JLabel("Name");
      lblName.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lblName.setBounds(34, 158, 54, 28);
      contentPane.add(lblName);
      
      txtName = new JTextField();
      txtName.setBounds(88, 158, 306, 28);
      contentPane.add(txtName);
      txtName.setColumns(10);
      
      JLabel lblPhoneNumber = new JLabel("Phone number (ID)");
      lblPhoneNumber.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lblPhoneNumber.setBounds(34, 52, 141, 28);
      contentPane.add(lblPhoneNumber);
      
      txtPnum = new JTextField();
      txtPnum.setColumns(10);
      txtPnum.setBounds(179, 52, 215, 28);
      contentPane.add(txtPnum);
      
      JLabel lblAddress = new JLabel("Address");
      lblAddress.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lblAddress.setBounds(34, 196, 59, 28);
      contentPane.add(lblAddress);
      
      txtAddress = new JTextField();
      txtAddress.setColumns(10);
      txtAddress.setBounds(107, 196, 287, 28);
      contentPane.add(txtAddress);
      
      
      
      JLabel lblPassword = new JLabel("Password");
      lblPassword.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lblPassword.setBounds(34, 92, 78, 18);
      contentPane.add(lblPassword);
      
      pwdPassword = new JPasswordField();
      pwdPassword.setBounds(116, 86, 278, 28);
      contentPane.add(pwdPassword);
      
      
      pwdcheck = new JPasswordField();
      pwdcheck.setBounds(116, 122, 278, 28);
      contentPane.add(pwdcheck);
      
      JLabel lblAddCustomer = new JLabel("Add Customer");
      lblAddCustomer.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
      lblAddCustomer.setBounds(24, 22, 157, 18);
      contentPane.add(lblAddCustomer);
      
      JButton btnAddCustomer = new JButton("Add Now");
      btnAddCustomer.setFont(new Font("Calibri Light", Font.PLAIN, 15));
      btnAddCustomer.setBounds(184, 236, 97, 27);
      contentPane.add(btnAddCustomer);
      btnAddCustomer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 Administrator admin=(Administrator)getMainSystem().getUser();
        	 String tempNum=txtPnum.getText();
        	 try {
				if(admin.getCustomerSearch(mainSystem.getCustomerDB(),"phoneNum", tempNum, 1)==null)
					{
					 if(pwdPassword.getText().equals(pwdcheck.getText()))
				
					{
						admin.insertCusotmer(tempNum, pwdPassword.getText(), txtName.getText(), txtAddress.getText(), getMainSystem().getCustomerDB());
						JOptionPane.showMessageDialog(null, "Add Successfully");
						dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "password is incorrect ");
					}
				 else
					 JOptionPane.showMessageDialog(null, "the user who has this phone number already signed up");
			} catch (HeadlessException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         }
      });
      
      JButton btnCancel = new JButton("Cancel");
      btnCancel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            dispose();
         }
      });
      btnCancel.setFont(new Font("Calibri Light", Font.PLAIN, 15));
      btnCancel.setBounds(297, 236, 97, 27);
      contentPane.add(btnCancel);

      
      JLabel lblConfirm = new JLabel("Confirm");
      lblConfirm.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lblConfirm.setBounds(34, 128, 78, 18);
      contentPane.add(lblConfirm);
   }
   
	public MainSystem getMainSystem()
	{
		return mainSystem;
	}
}