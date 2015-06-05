package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Mainsys.MainSystem;
import User.Administrator;
import User.Customer;


public class RentAdmin extends JFrame {

	private JPanel contentPane;
	private MainSystem mainSystem;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtMpoint;
	private JTextField textField;
	private Customer customer;
	/**
	 * Launch the application.
	 */
	//   public static void main(String[] args) {
	//      EventQueue.invokeLater(new Runnable() {
	//         public void run() {
	//            try {
	//               RentAdmin frame = new RentAdmin();
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
	public RentAdmin(MainSystem mainSystem,int d_id, double price) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.mainSystem=mainSystem;
		setBounds(100, 100, 450, 311);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);



		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnCancel.setBounds(310, 215, 93, 27);
		contentPane.add(btnCancel);

		JLabel lbl_Price = new JLabel("Price");
		lbl_Price.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lbl_Price.setBounds(27, 141, 59, 28);
		contentPane.add(lbl_Price);

		JLabel lblCustomerInformation = new JLabel("Customer Information");
		lblCustomerInformation.setForeground(Color.DARK_GRAY);
		lblCustomerInformation.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblCustomerInformation.setBounds(27, 28, 237, 27);
		contentPane.add(lblCustomerInformation);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblName.setBounds(27, 101, 54, 28);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(81, 101, 322, 28);
		contentPane.add(txtName);

		JLabel lblPhone = new JLabel("Phone number");
		lblPhone.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblPhone.setBounds(27, 61, 116, 28);
		contentPane.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(142, 61, 228, 28);
		contentPane.add(txtPhone);

		JLabel lblMpoint = new JLabel("Membership Point");
		lblMpoint.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblMpoint.setBounds(25, 175, 139, 28);
		contentPane.add(lblMpoint);

		txtMpoint = new JTextField();
		txtMpoint.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMpoint.setEditable(false);
		txtMpoint.setColumns(10);
		txtMpoint.setBounds(276, 175, 84, 28);
		contentPane.add(txtMpoint);

		JLabel lblPoints = new JLabel("/");
		lblPoints.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblPoints.setBounds(261, 173, 15, 28);
		contentPane.add(lblPoints);

		JButton btnsearch = new JButton("");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Phonesearch rentpopup = new Phonesearch(
						mainSystem,GetCustomerList(txtPhone.getText()));
				rentpopup.setVisible(true);
			}
		});
		Image btnSearch = new ImageIcon(this.getClass().getResource(
				"/search.png")).getImage();
		btnsearch.setIcon(new ImageIcon(btnSearch));
		btnsearch.setForeground(Color.WHITE);
		btnsearch.setBorderPainted(false);
		btnsearch.setBackground(SystemColor.inactiveCaptionBorder);
		btnsearch.setBounds(379, 61, 24, 24);
		contentPane.add(btnsearch);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0");
		textField.setColumns(10);
		textField.setBounds(171, 175, 84, 28);
		contentPane.add(textField);

		JLabel lblPrice = new JLabel(""+price);
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setForeground(new Color(220, 20, 60));
		lblPrice.setBounds(84, 141, 85, 23);
		contentPane.add(lblPrice);

		JLabel label = new JLabel("$");
		label.setBounds(179, 146, 62, 18);
		contentPane.add(label);

		JLabel lblPoints_1 = new JLabel("Points");
		lblPoints_1.setBounds(366, 180, 48, 18);
		contentPane.add(lblPoints_1);

		JButton btnYes = new JButton("Rent");
		btnYes.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempMaxPoint=txtMpoint.getText();
				try{
					double points=Double.parseDouble(textField.getText());
					double originPrice=price;
				
					double maxPoint=Double.parseDouble(tempMaxPoint);
					if((points<=maxPoint)&&(points<=originPrice))
					{
						double finPrice=originPrice-points;
						double storePoints=finPrice*0.1;
						
						customer.rentDVD(mainSystem.getRentDB(), d_id);
						customer.useMembership(points, mainSystem.getCustomerDB());
						customer.upadateMembership(price, mainSystem.getCustomerDB());
						JOptionPane.showMessageDialog(null, "origin price : "+originPrice + "\n use points : "+points +"\n final price : " +finPrice+"\n Thanks for using. "+storePoints+"points stored");
//						mainSystem.getRentDB().RentDVD(customer.getId(), d_id);
//						mainSystem.getCustomerDB().useMembership(customer.getId(), points);
//						mainSystem.getCustomerDB().updateMembership(customer.getId(), storePoints);
					//	mainSystem.beCustomer(customer.getId());
						dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "available points should be lower than your membership-points and DVD's price");
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "please insert between 0 to "+tempMaxPoint);
				}
			}
		});
		btnYes.setBounds(206, 215, 93, 27);
		contentPane.add(btnYes);
	}

	public ArrayList<Customer> GetCustomerList(String phoneNumber)
	{

		ArrayList<Customer> customerList=null;
		//int temp=Integer.parseInt(txtP_num.getText());

		//		try {
		//			dvdList = mainSystem.getUser().getAllDVDList(mainSystem.getDVDDB(),temp);
		//		} catch (UnsupportedEncodingException e1) {
		//			// TODO Auto-generated catch block
		//			e1.printStackTrace();
		//		}
		Administrator admin=(Administrator)mainSystem.getUser();
		try {
			customerList=admin.getCustomerSearch(mainSystem.getCustomerDB(), "phoneNum", phoneNumber, 1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		//		int i=0;
		//		while(i<customerList.size())
		//		{
		//			System.out.println("cc "+customerList.get(i).getName()+"vv "+customerList.get(i).getPhoneNum());
		//			String[] a=new String[2];
		//			a[0]=""+customerList.get(i).getName();
		//			a[1]=customerList.get(i).getPhoneNum();
		//
		//			mainModel.addRow(a);
		//			i++;
		//		}
		return customerList;

	}

	public class Phonesearch extends JFrame {

		private JPanel contentPane;
		private MainSystem mainSystem;
		private JTable table;
		private DefaultTableModel mainModel;
		private String getPhoneNum;
		private ArrayList<Customer> customerList;

		/**
		 * Launch the application.
		 */
		/*	public static void main(String[] args) {
   		EventQueue.invokeLater(new Runnable() {
   			public void run() {
   				try {
   					Phonesearch frame = new Phonesearch();
   					frame.setVisible(true);
   				} catch (Exception e) {
   					e.printStackTrace();
   				}
   			}
   		});
   	}
		 */

		public MainSystem getMainSystem()
		{
			return mainSystem;
		}

		/**
		 * Create the frame.
		 * @param mainSystem 
		 */
		public Phonesearch(MainSystem mainSystem,ArrayList<Customer> customerList) {
			setResizable(false);
			this.mainSystem=mainSystem;
			//getPhoneNum=phoneNumber;
			this.customerList=customerList;
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			setBounds(100, 100, 450, 378);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.inactiveCaptionBorder);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel lblSearchCustomers = new JLabel("Customers search");
			lblSearchCustomers.setForeground(Color.DARK_GRAY);
			lblSearchCustomers.setFont(new Font("Arial Black", Font.PLAIN, 17));
			lblSearchCustomers.setBounds(26, 12, 200, 27);
			contentPane.add(lblSearchCustomers);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 51, 389, 219);
			contentPane.add(scrollPane);



			String [] col3 = {"Name","PhoneNumber"};
			mainModel = new DefaultTableModel(col3,0);
			//table.
			table = new JTable(mainModel);
			//contentPane.add(table);
			scrollPane.setViewportView(table);
			ShowCustomerList();

			JButton btnSubmit = new JButton("Okay");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {


					try {
						if(mainSystem.getRentDB().isValidToRent(customerList.get(table.getSelectedRow()).getId())==null)
							JOptionPane.showMessageDialog(null,"This Customer can't rent.\n This customer has overdue DVD \n or already rent maxium number(5) of DVDs");
						else
						{
							txtName.setText(mainModel.getValueAt(table.getSelectedRow(), 0).toString());
							txtPhone.setText(mainModel.getValueAt(table.getSelectedRow(), 1).toString());	 
							txtMpoint.setText(""+customerList.get(table.getSelectedRow()).getMemPoint());
							customer=customerList.get(table.getSelectedRow());
							dispose();
						}
					} catch (HeadlessException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnSubmit.setBounds(214, 300, 97, 27);
			contentPane.add(btnSubmit);

			JButton btnclose = new JButton("Close");
			btnclose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnclose.setFont(new Font("Calibri Light", Font.PLAIN, 15));
			btnclose.setBounds(317, 300, 97, 27);
			contentPane.add(btnclose);
		}

		public void ShowCustomerList()
		{

			int i=0;
			while(i<customerList.size())
			{
				System.out.println("cc "+customerList.get(i).getName()+"vv "+customerList.get(i).getPhoneNum());
				String[] a=new String[2];
				a[0]=""+customerList.get(i).getName();
				a[1]=customerList.get(i).getPhoneNum();

				mainModel.addRow(a);
				i++;
			}
		}


	}
}