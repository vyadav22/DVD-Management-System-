package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Mainsys.MainSystem;
import User.Administrator;
import User.Customer;


public class EditCustomer extends JFrame {

   private JPanel contentPane;
   private MainSystem mainSystem;
   private JLabel lblEditCustomer;
   private JTextField txtName;
   private JPasswordField pwdPassword;
   private JTextField txtPhone;
   private JTextField txtaddress;
   private JTextField txtPoint;

   private Customer originCustomer;
   private DefaultTableModel mainModel;
   private JTable table;
   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               EditCustomer frame = new EditCustomer();
//               frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the frame.
    */
   public EditCustomer(MainSystem mainSystem,DefaultTableModel mainModel,JTable table) {
      setResizable(false);
      this.mainSystem=mainSystem;
      this.mainModel=mainModel;
      this.table=table;
      
      String getID=mainModel.getValueAt(table.getSelectedRow(), 0).toString();
      int c_id=Integer.parseInt(getID);
      try {
         originCustomer=mainSystem.getCustomerDB().getCustomerByID(c_id);
      } catch (UnsupportedEncodingException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      
      
//      String getPoints=mainModel.getValueAt(table.getSelectedRow(), 4).toString();
//      double points=Double.parseDouble(getPoints);
//      
//      String tempOverdue=mainModel.getValueAt(table.getSelectedRow(), 5).toString();
//      if(tempOverdue.equals("V"))
//         originCustomer=new Customer(c_id, mainModel.getValueAt(table.getSelectedRow(), 2).toString(),mainModel.getValueAt(table.getSelectedRow(), 1).toString(), mainModel.getValueAt(table.getSelectedRow(), 3).toString(), points,1);
//      else
//         originCustomer=new Customer(c_id, mainModel.getValueAt(table.getSelectedRow(), 2).toString(),mainModel.getValueAt(table.getSelectedRow(), 1).toString(), mainModel.getValueAt(table.getSelectedRow(), 3).toString(), points, 0);

            
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 435, 319);
      contentPane = new JPanel();
      contentPane.setForeground(Color.DARK_GRAY);
      contentPane.setBackground(SystemColor.inactiveCaptionBorder);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      
      
      lblEditCustomer = new JLabel("Edit Customer");
      lblEditCustomer.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
      lblEditCustomer.setBounds(27, 26, 157, 18);
      contentPane.add(lblEditCustomer);
      
      JLabel lblName = new JLabel("Name");
      lblName.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lblName.setBounds(37, 57, 54, 28);
      contentPane.add(lblName);
      
      txtName = new JTextField();
      txtName.setColumns(10);
      txtName.setBounds(91, 57, 306, 28);
      txtName.setText(originCustomer.getName());
      contentPane.add(txtName);
      
      JLabel lblPassword = new JLabel("Confirm Password");
      lblPassword.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lblPassword.setBounds(37, 183, 135, 18);
      contentPane.add(lblPassword);
      
      pwdPassword = new JPasswordField();
      pwdPassword.setBounds(180, 177, 217, 28);
      contentPane.add(pwdPassword);
      
      JLabel lblPhone = new JLabel("Phone number");
      lblPhone.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lblPhone.setBounds(37, 97, 116, 28);
      contentPane.add(lblPhone);
      
      txtPhone = new JTextField();
      txtPhone.setColumns(10);
      txtPhone.setBounds(152, 97, 245, 28);
      contentPane.add(txtPhone);
      
      txtaddress = new JTextField();
      txtaddress.setColumns(10);
      txtaddress.setBounds(110, 137, 287, 28);
      contentPane.add(txtaddress);
      
	txtPhone.setText(originCustomer.getPhoneNum());
		txtaddress.setText(originCustomer.getAddress());
      JLabel lbladdress = new JLabel("Address");
      lbladdress.setFont(new Font("Calibri Light", Font.PLAIN, 18));
      lbladdress.setBounds(37, 137, 59, 28);
      contentPane.add(lbladdress);
      
      JButton btnEditCustomer = new JButton("Edit");
      btnEditCustomer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            Administrator admin=(Administrator)mainSystem.getUser(); //1-release_date,2-genre,3-title,4-price,5-rating,6-studio
            
            
            try {
               if(mainSystem.login(originCustomer.getPhoneNum(), pwdPassword.getText())==0)
                  JOptionPane.showMessageDialog(null, "Password is wrong.\n You should confirm password to modify data");
               else
               {
                  boolean isChanged=false;
                  
                  if(!(originCustomer.getName().equals(txtName.getText()))) //
                  {
                     try {
                        admin.modifyCustomer(mainSystem.getCustomerDB(), originCustomer.getId(), 2, txtName.getText());
                     } catch (UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                     }
                     
                     mainModel.setValueAt(txtName.getText(), table.getSelectedRow(), 1);
                     
                     isChanged=true;
                  }
                  if(!(originCustomer.getPhoneNum().equals(txtPhone.getText()))) //
                  {
                     try {
                        admin.modifyCustomer(mainSystem.getCustomerDB(), originCustomer.getId(), 3, txtPhone.getText());
                     } catch (UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                     }
                     
                     mainModel.setValueAt(txtPhone.getText(), table.getSelectedRow(), 2);
                     
                     isChanged=true;
                  }   
                  if(!(originCustomer.getAddress().equals(txtaddress.getText()))) //
                  {
                     try {
                        admin.modifyCustomer(mainSystem.getCustomerDB(), originCustomer.getId(), 4, txtaddress.getText());
                     } catch (UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                     }
      
                     mainModel.setValueAt(txtaddress.getText(), table.getSelectedRow(), 3);
                     
                     isChanged=true;
                  }   
                  
                  if(isChanged)
                     JOptionPane.showMessageDialog(null, "Edit Successfully");
                  dispose();
               }
            } catch (NumberFormatException | HeadlessException
                  | UnsupportedEncodingException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }

         }
      });
      btnEditCustomer.setFont(new Font("Calibri Light", Font.PLAIN, 15));
      btnEditCustomer.setBounds(197, 223, 97, 27);
      contentPane.add(btnEditCustomer);
      
      JButton btnClose = new JButton("Close");
      btnClose.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });
      btnClose.setFont(new Font("Calibri Light", Font.PLAIN, 15));
      btnClose.setBounds(300, 223, 97, 27);
      contentPane.add(btnClose);
   }
   public MainSystem getMainSystem()
   {
      return mainSystem;
   }
}