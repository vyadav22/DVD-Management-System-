package UI;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Mainsys.MainSystem;
import User.Customer;
import javax.swing.JRadioButton;

public class CustomMyInfo extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
//	private JLabel lblDays;
	private JButton btnClose;
	private MainSystem mainSystem;

	/**
	 * Launch the application.
	 */
	public  MainSystem getMainSystem()
	{
		return mainSystem;
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CustomMyInfo frame = new CustomMyInfo(getMainSystem());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param mainSystem 
	 */
	
	public CustomMyInfo(MainSystem mainSystem,Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 289);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblName.setBounds(24, 28, 62, 18);
		contentPane.add(lblName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblPhoneNumber.setBounds(24, 63, 113, 18);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblAddress.setBounds(24, 102, 62, 18);
		contentPane.add(lblAddress);
		
		JLabel lblMembershipPoint = new JLabel("Membership Point");
		lblMembershipPoint.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblMembershipPoint.setBounds(24, 138, 148, 18);
		contentPane.add(lblMembershipPoint);
		
		JLabel lblOverdueStatue = new JLabel("Overdue Statue");
		lblOverdueStatue.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblOverdueStatue.setBounds(24, 170, 124, 18);
		contentPane.add(lblOverdueStatue);
		
		txtName = new JTextField(customer.getName());
		txtName.setEditable(false);
		txtName.setBounds(80, 25, 326, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		textField = new JTextField(customer.getPhoneNum());
		textField.setEditable(false);
		textField.setBounds(151, 60, 255, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(customer.getAddress());
		textField_1.setEditable(false);
		textField_1.setBounds(87, 99, 319, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(""+customer.getMemPoint());
		textField_2.setEditable(false);
		textField_2.setBounds(171, 135, 148, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPoints = new JLabel("Points");
		lblPoints.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblPoints.setBounds(332, 138, 62, 18);
		contentPane.add(lblPoints);
		
		if(customer.isOverdue()==1)
		{
			JRadioButton radiobtn = new JRadioButton("");
		radiobtn.setSelected(true);
		radiobtn.setEnabled(false);
		radiobtn.setBackground(SystemColor.inactiveCaptionBorder);
		radiobtn.setBounds(158, 166, 42, 27);
		contentPane.add(radiobtn);
		}
		else
		{
			JRadioButton radiobtn = new JRadioButton("");
		radiobtn.setSelected(false);
		radiobtn.setEnabled(false);
		radiobtn.setBackground(SystemColor.inactiveCaptionBorder);
		radiobtn.setBounds(158, 166, 42, 27);
		contentPane.add(radiobtn);
		}

		
//		lblDays = new JLabel("days");
//		lblDays.setFont(new Font("Calibri Light", Font.PLAIN, 18));
//		lblDays.setBounds(225, 170, 62, 18);
//		contentPane.add(lblDays);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(306, 207, 105, 27);
		contentPane.add(btnClose);
		
		
	}
}
