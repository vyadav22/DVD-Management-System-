package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Mainsys.MainSystem;
import User.Customer;


public class RentCustomer extends JFrame {

	private JPanel contentPane;
	private MainSystem mainSystem;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	//   public static void main(String[] args) {
	//      EventQueue.invokeLater(new Runnable() {
	//         public void run() {
	//            try {
	//               RentandReturn frame = new RentandReturn();
	//               frame.setVisible(true);
	//            } catch (Exception e) {
	//               e.printStackTrace();
	//            }
	//         }
	//      });
	//   }
	public MainSystem getMainSystem()
	{
		return mainSystem;
	}

	/**
	 * Create the frame.
	 */
	public RentCustomer(MainSystem mainSystem,int d_id, double price,Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.mainSystem=mainSystem;
		setBounds(100, 100, 450, 308);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		

		JButton btnYes = new JButton("Rent");
		btnYes.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double maxPoint=customer.getMemPoint();
				try{ 
					double originPrice=price;
					double points=Double.parseDouble(textField_2.getText());
					System.out.println("points "+ points);
					
					if((points<=maxPoint)&&(points<=originPrice))
					{

						double finPrice=originPrice-points;
						double storePoints=finPrice*0.1;
						customer.rentDVD(mainSystem.getRentDB(), d_id);
						customer.useMembership(points, mainSystem.getCustomerDB());
						customer.upadateMembership(price, mainSystem.getCustomerDB());
						//mainSystem.beCustomer(customer.getId());
						JOptionPane.showMessageDialog(null, "origin price : "+originPrice + "\n use points : "+points +"\n final price : " +finPrice+"\n Thanks for using. "+storePoints+"points stored");
						dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "available points is lower than your membership-points and DVD's price");
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "please insert between 0 to "+maxPoint);
				}
			}
		});
		btnYes.setBounds(209, 221, 93, 27);
		contentPane.add(btnYes);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnCancel.setBounds(313, 221, 93, 27);
		contentPane.add(btnCancel);

		JLabel label = new JLabel("Phone number");
		label.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		label.setBounds(30, 96, 116, 28);
		contentPane.add(label);

		textField = new JTextField(customer.getPhoneNum());
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(145, 96, 261, 28);
		contentPane.add(textField);

		JLabel label_1 = new JLabel("Name");
		label_1.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		label_1.setBounds(30, 56, 54, 28);
		contentPane.add(label_1);

		textField_1 = new JTextField(customer.getName());
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(84, 56, 322, 28);
		contentPane.add(textField_1);

		JLabel label_4 = new JLabel("Customer Information");
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setFont(new Font("Arial Black", Font.PLAIN, 17));
		label_4.setBounds(27, 25, 237, 27);
		contentPane.add(label_4);

		JLabel label_2 = new JLabel("Price");
		label_2.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		label_2.setBounds(32, 137, 59, 28);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel(""+price);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(new Color(220, 20, 60));
		label_3.setBounds(89, 137, 85, 23);
		contentPane.add(label_3);

		JLabel label_5 = new JLabel("$");
		label_5.setBounds(184, 142, 62, 18);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("Membership Point");
		label_6.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		label_6.setBounds(30, 171, 139, 28);
		contentPane.add(label_6);

		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("");
			}
		});
		textField_2.setText("0");
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setColumns(10);
		textField_2.setBounds(176, 171, 84, 28);
		contentPane.add(textField_2);

		textField_3 = new JTextField(""+customer.getMemPoint());
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(281, 171, 84, 28);
		contentPane.add(textField_3);

		JLabel label_7 = new JLabel("/");
		label_7.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		label_7.setBounds(266, 169, 15, 28);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("Points");
		label_8.setBounds(371, 176, 48, 18);
		contentPane.add(label_8);
	}
}