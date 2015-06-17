
package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Mainsys.MainSystem;
import User.Administrator;
import User.Customer;


public class CustomerEdit extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;

	private MainSystem mainSystem;
	
	private JFrame frame;

	private JTable table;
	private JTextField txtP_num;
	private DefaultTableModel mainModel;
	private int numberOfCustomer; //show maximum-page
	private String type;
	private String keyword;
	private int mode; // 0 : all , 1: search by name or phoneNum / 2: overdue customers
	private JLabel lblLast_num;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CustomerEdit(MainSystem mainSystem) {
		this.mainSystem=mainSystem;
		mode=0;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDate = new JLabel(myCalendar.getDate());
	      lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
	      lblDate.setForeground(Color.DARK_GRAY);
	      lblDate.setFont(new Font("Calibri", Font.PLAIN, 16));
	      lblDate.setBounds(1047, 165, 173, 30);
	      contentPane.add(lblDate);
		
		JLabel label = new JLabel("");
//		label.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\Logo.png"));
		Image imgLogo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
		label.setIcon(new ImageIcon(imgLogo));
		label.setBackground(Color.MAGENTA);
		label.setBounds(14, 12, 130, 105);
		contentPane.add(label);
		
		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainScreen mainpage = new MainScreen(mainSystem);
				mainpage.frame.setVisible(true);
				dispose();
			}
		});
//		button.addActionListener(new frameDestroy(this));
//		button.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\title.png"));
		Image imgTitle = new ImageIcon(this.getClass().getResource("/title.png")).getImage();
		button.setIcon(new ImageIcon(imgTitle));
		button.setBorderPainted(false);
		button.setBackground(Color.WHITE);
		button.setBounds(133, 48, 480, 43);
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("");
//		label_1.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\titlecc.png"));
		Image imgTitlecc = new ImageIcon(this.getClass().getResource("/titlecc.png")).getImage();
		label_1.setIcon(new ImageIcon(imgTitlecc));
		label_1.setBounds(627, 63, 240, 28);
		contentPane.add(label_1);
		
		txtSearch = new JTextField();
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearch.setText("");
			}
		});
	
		txtSearch.setForeground(Color.DARK_GRAY);
		txtSearch.setFont(new Font("Adobe Arabic", Font.PLAIN, 15));
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setBounds(298, 151, 258, 24);
		txtSearch.setText("Search");
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JRadioButton rdbtnName = new JRadioButton("Name");
		rdbtnName.setBackground(Color.WHITE);
		rdbtnName.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		rdbtnName.setForeground(Color.DARK_GRAY);
		rdbtnName.setBounds(604, 150, 76, 27);
		contentPane.add(rdbtnName);
		
		JRadioButton rdbtnPhoneNumber = new JRadioButton("Phone number");
		rdbtnPhoneNumber.setForeground(Color.DARK_GRAY);
		rdbtnPhoneNumber.setBackground(Color.WHITE);
		rdbtnPhoneNumber.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		rdbtnPhoneNumber.setBounds(678, 150, 139, 27);
		contentPane.add(rdbtnPhoneNumber);
		
		rdbtnName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnPhoneNumber.setSelected(false);
			}
		});
		rdbtnPhoneNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnName.setSelected(false);
			}
		});
		
		JButton btnSreach = new JButton("");
		Image imgsearch = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		btnSreach.setIcon(new ImageIcon(imgsearch));
		btnSreach.setForeground(Color.WHITE);
		btnSreach.setBorderPainted(false);
		btnSreach.setBackground(Color.WHITE);
		btnSreach.setBounds(570, 150, 24, 24);
		
		btnSreach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mode=1;
				mainModel.setRowCount(0);
				txtP_num.setText("1");

				if(rdbtnName.isSelected()==true)
					try {

						type="name";
						keyword=txtSearch.getText();
						numberOfCustomer=mainSystem.getCustomerDB().countCustomerBySearch(type, keyword);
						numberOfCustomer=(numberOfCustomer/100)+1;

						lblLast_num.setText(""+numberOfCustomer);

						showCustomerBySearch();

					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(rdbtnPhoneNumber.isSelected()==true)
					try {
						type="phoneNum";
						keyword=txtSearch.getText();
						numberOfCustomer=mainSystem.getCustomerDB().countCustomerBySearch(type, keyword);
						numberOfCustomer=(numberOfCustomer/100)+1;

						lblLast_num.setText(""+numberOfCustomer);

						showCustomerBySearch();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				//rdbtnYear.setSelected(false);
				//ShowDVDListByType();

			}
		});
		
		contentPane.add(btnSreach);
		
		JScrollPane CustomList = new JScrollPane();
		CustomList.setBounds(298, 190, 923, 422);
		contentPane.add(CustomList);
		
		   String[] col3 = { "No.", "Name", "PhoneNumber", "Address", "MemPoint", "Overdue"};
		      mainModel = new DefaultTableModel(col3,0);
		      //table.
		      table = new JTable(mainModel);


		      CustomList.setViewportView(table);
		      
		      DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		      celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		      DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		      renderer.setHorizontalAlignment(SwingConstants.CENTER);
		      table.getTableHeader().setDefaultRenderer(renderer);

		      table.getColumn("No.").setPreferredWidth(20);
		      table.getColumn("No.").setCellRenderer(celAlignCenter);
		      table.getColumn("Name").setPreferredWidth(160);
		      table.getColumn("PhoneNumber").setPreferredWidth(150);
		      table.getColumn("PhoneNumber").setCellRenderer(celAlignCenter);
		      table.getColumn("Address").setPreferredWidth(380);
		      table.getColumn("MemPoint").setPreferredWidth(50);
		      table.getColumn("MemPoint").setCellRenderer(celAlignCenter);
		      table.getColumn("Overdue").setPreferredWidth(30);
		      table.getColumn("Overdue").setCellRenderer(celAlignCenter);

		

		
		JButton btnAddCustomer = new JButton("");
		Image imgaddc = new ImageIcon(this.getClass().getResource("/addcustom.png")).getImage();
		btnAddCustomer.setIcon(new ImageIcon(imgaddc));
		btnAddCustomer.setBackground(Color.WHITE);
		btnAddCustomer.setBorderPainted(false);
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustomer btnAdd = new AddCustomer(mainSystem);
				btnAdd.setVisible(true);
			}
		});
		btnAddCustomer.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnAddCustomer.setForeground(Color.DARK_GRAY);
		btnAddCustomer.setBounds(61, 223, 167, 57);
		contentPane.add(btnAddCustomer);
		
		JButton btnDeleteCustomer = new JButton("");
		btnDeleteCustomer.setBackground(Color.WHITE);
		Image imgdelc = new ImageIcon(this.getClass().getResource("/delcustom.png")).getImage();
		btnDeleteCustomer.setIcon(new ImageIcon(imgdelc));
		btnDeleteCustomer.setBorderPainted(false);
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getValue=mainModel.getValueAt(table.getSelectedRow(), 0).toString();
				int c_id=Integer.parseInt(getValue);
				Administrator admin=(Administrator)mainSystem.getUser();
				admin.deleteCustomer(c_id, mainSystem.getCustomerDB());
				mainModel.removeRow(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Delete Successfully");
			}
		});
		btnDeleteCustomer.setForeground(Color.DARK_GRAY);
		btnDeleteCustomer.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnDeleteCustomer.setBounds(61, 293, 167, 57);
		contentPane.add(btnDeleteCustomer);
		
		JButton btnEditCustomer = new JButton("");
		btnEditCustomer.setBackground(Color.WHITE);
		Image imgeditc = new ImageIcon(this.getClass().getResource("/editcustom.png")).getImage();
		btnEditCustomer.setIcon(new ImageIcon(imgeditc));
		btnEditCustomer.setBorderPainted(false);
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCustomer btnEdit = new EditCustomer(getMainSystem(),mainModel,table);
				btnEdit.setVisible(true);
			}
		});
		btnEditCustomer.setForeground(Color.DARK_GRAY);
		btnEditCustomer.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnEditCustomer.setBounds(61, 363, 167, 57);
		contentPane.add(btnEditCustomer);
		Image imgdeco2 = new ImageIcon(this.getClass().getResource("/deco2.png")).getImage();
		

		JButton btnOverdueCustomer = new JButton("");
		btnOverdueCustomer.setBackground(Color.WHITE);
		Image imgoverduec = new ImageIcon(this.getClass().getResource("/overdue.png")).getImage();
		btnOverdueCustomer.setIcon(new ImageIcon(imgoverduec));
		btnOverdueCustomer.setBorderPainted(false);
		btnOverdueCustomer.setForeground(Color.DARK_GRAY);
		btnOverdueCustomer.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnOverdueCustomer.setBounds(61, 433, 167, 57);
		contentPane.add(btnOverdueCustomer);
		
		btnOverdueCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mode=2;
				mainModel.setRowCount(0);
				txtP_num.setText("1");
					numberOfCustomer=mainSystem.getCustomerDB().countOverdueCustomer();
					numberOfCustomer=(numberOfCustomer/100)+1;
					lblLast_num.setText(""+numberOfCustomer);
					ShowOverdueCustomer();
			}
		});
		
		JButton btnAdministrator = new JButton("");
		btnAdministrator.setBackground(Color.WHITE);
		Image imgadcustom = new ImageIcon(this.getClass().getResource("/admincustom.png")).getImage();
		btnAdministrator.setIcon(new ImageIcon(imgadcustom));
		btnAdministrator.setBorderPainted(false);
		btnAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPage btnAdmin = new AdminPage(getMainSystem());
				dispose();
				btnAdmin.setVisible(true);			}
		});
		btnAdministrator.setForeground(Color.DARK_GRAY);
		btnAdministrator.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnAdministrator.setBounds(61, 502, 167, 57);
		contentPane.add(btnAdministrator);
		
		JLabel lblwelcome = new JLabel("Welcome! This is an administrator page.");
		lblwelcome.setForeground(new Color(100, 149, 237));
		lblwelcome.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblwelcome.setBounds(977, 34, 246, 18);
		contentPane.add(lblwelcome);
		
		JButton btnHome = new JButton("");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				MainScreen mainpage = new MainScreen(mainSystem);
				mainpage.frame.setVisible(true);
				dispose();
			}
		});
		btnHome.setBorderPainted(false);
		btnHome.setBackground(Color.WHITE);
		Image imghome = new ImageIcon(this.getClass().getResource("/homecustom.png")).getImage();
		btnHome.setIcon(new ImageIcon(imghome));
		btnHome.setBounds(61, 572, 167, 57);
		contentPane.add(btnHome);
		
//		JButton btnPre = new JButton("");
//		Image imgprebtn = new ImageIcon(this.getClass().getResource("/pre.png")).getImage();
//		btnPre.setIcon(new ImageIcon(imgprebtn));
//		btnPre.setBorderPainted(false);
//		btnPre.setBackground(Color.WHITE);
//		btnPre.setBounds(659, 626, 53, 40);
//		contentPane.add(btnPre);
//		
//		txtPnum = new JTextField();
//		txtPnum.setText("1");
//		txtPnum.setColumns(10);
//		txtPnum.setBounds(721, 635, 39, 24);
//		contentPane.add(txtPnum);
//		
//		JButton btnPsearch = new JButton("");
//		Image imgpsearch = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
//		btnPsearch.setIcon(new ImageIcon(imgpsearch));
//		btnPsearch.setForeground(Color.WHITE);
//		btnPsearch.setBorderPainted(false);
//		btnPsearch.setBackground(Color.WHITE);
//		btnPsearch.setBounds(761, 633, 24, 24);
//		contentPane.add(btnPsearch);
//		
//		JLabel lblLpnum = new JLabel("1");
//		lblLpnum.setBounds(790, 635, 31, 18);
//		contentPane.add(lblLpnum);
//		
//		JButton btnLast = new JButton("");
//		Image imglast = new ImageIcon(this.getClass().getResource("/later.png")).getImage();
//		btnLast.setIcon(new ImageIcon(imglast));
//		btnLast.setBorderPainted(false);
//		btnLast.setBackground(Color.WHITE);
//		btnLast.setBounds(820, 626, 53, 40);
//		contentPane.add(btnLast);
		JButton btnPre = new JButton("");
		btnPre.setBackground(Color.WHITE);
		Image imgpre = new ImageIcon(this.getClass().getResource("/pre.png")).getImage();
		btnPre.setIcon(new ImageIcon(imgpre));
		btnPre.setBorderPainted(false);
		btnPre.setBounds(653, 623, 53, 40);
		contentPane.add(btnPre);

		txtP_num = new JTextField();
		txtP_num.setText("1");
		txtP_num.setBounds(715, 632, 39, 24);
		contentPane.add(txtP_num);
		txtP_num.setColumns(10);

		lblLast_num = new JLabel("1");
		lblLast_num.setBounds(784, 632, 31, 18);
		contentPane.add(lblLast_num);

		JButton btnLast = new JButton("");
		btnLast.setBackground(Color.WHITE);
		Image imglater = new ImageIcon(this.getClass().getResource("/later.png")).getImage();
		btnLast.setIcon(new ImageIcon(imglater));
		btnLast.setBorderPainted(false);
		btnLast.setBounds(814, 623, 53, 40);
		contentPane.add(btnLast);		
		
		JButton btnAll = new JButton("");
		Image imgall = new ImageIcon(this.getClass().getResource("/all.png")).getImage();
		btnAll.setIcon(new ImageIcon(imgall));
		btnAll.setForeground(Color.DARK_GRAY);
		btnAll.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnAll.setBorderPainted(false);
		btnAll.setBackground(Color.WHITE);
		btnAll.setBounds(61, 151, 167, 57);
		
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mode=0;
				mainModel.setRowCount(0);
				txtP_num.setText("1");
				try {
					numberOfCustomer=mainSystem.getCustomerDB().countAllCustomer();
					numberOfCustomer=(numberOfCustomer/100)+1;
					lblLast_num.setText(""+numberOfCustomer);
					ShowAllCustomer();
					mainModel.removeRow(0);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		contentPane.add(btnAll);

		JButton inputButton = new JButton("");
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int numTemp=Integer.parseInt(txtP_num.getText());
				if((numTemp<=numberOfCustomer)&&(numTemp>0))
				{
					txtP_num.setText(""+numTemp);
					mainModel.setRowCount(0);

					if(mode==0)
						try {
							ShowAllCustomer();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else if(mode==1)
					{
						try {
							showCustomerBySearch();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						ShowOverdueCustomer();
					}
	
				}
				else
					JOptionPane.showMessageDialog(null, "It's over the boundary");

			}
		});
		inputButton.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
		inputButton.setForeground(Color.WHITE);
		inputButton.setBorderPainted(false);
		inputButton.setBackground(Color.WHITE);
		inputButton.setBounds(755, 630, 24, 24);
		contentPane.add(inputButton);

		btnPre.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int numTemp=Integer.parseInt(txtP_num.getText());
				if(numTemp>1)
				{
					numTemp=numTemp-1;
					txtP_num.setText(""+numTemp);

					mainModel.setRowCount(0);

					if(mode==0)
						try {
							ShowAllCustomer();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else if(mode==1)
					{
						try {
							showCustomerBySearch();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						ShowOverdueCustomer();
					}
				}
			}
		});

		btnLast.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int numTemp=Integer.parseInt(txtP_num.getText());
				if(numTemp<numberOfCustomer)
				{
					numTemp=numTemp+1;
					txtP_num.setText(""+numTemp);

					mainModel.setRowCount(0);

					if(mode==0)
						try {
							ShowAllCustomer();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else if(mode==1)
					{
						try {
							showCustomerBySearch();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						ShowOverdueCustomer();
					}
				}
			}
		});
		numberOfCustomer=mainSystem.getCustomerDB().countAllCustomer();
		numberOfCustomer=(numberOfCustomer/100)+1;
		lblLast_num.setText(""+numberOfCustomer);
		try {
			ShowAllCustomer();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainModel.removeRow(0);
		
	}
	
	public void ShowAllCustomer() throws UnsupportedEncodingException //1-released_date ,  3-title.
	{

		ArrayList<Customer> customerList=null;
		int temp=Integer.parseInt(txtP_num.getText());

//		switch(type)
//		{
//		case 1:
//			customerList = getMainSystem().getUser().getDVDSearch("release_date", keyword, getMainSystem().getDVDDB(),temp);
//			break;
//		case 3:
//			customerList = getMainSystem().getUser().getDVDSearch("title", keyword, getMainSystem().getDVDDB(),temp);
//			break;
//		}
		Administrator admin=(Administrator)getMainSystem().getUser();
		customerList = admin.getAllCustomerList(mainSystem.getCustomerDB(), temp);
		if(customerList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<customerList.size())
			{
				String[] a=new String[6];
				a[0]=""+customerList.get(i).getId();
				a[1]=""+customerList.get(i).getName();
				a[2]=customerList.get(i).getPhoneNum();
				a[3]=customerList.get(i).getAddress();
				a[4]=""+customerList.get(i).getMemPoint();
				if(customerList.get(i).isOverdue()==1)
					a[5]=" V";
				else
					a[5]="";
				mainModel.addRow(a);
				i++;
			}	
		}
	}
	
	public void ShowOverdueCustomer() 
	{

		ArrayList<Customer> customerList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		Administrator admin=(Administrator)getMainSystem().getUser();
		try {
			customerList = admin.getAllOverdueCustomer(mainSystem.getCustomerDB(), temp);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(customerList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<customerList.size())
			{
				String[] a=new String[6];
				a[0]=""+customerList.get(i).getId();
				a[1]=""+customerList.get(i).getName();
				a[2]=customerList.get(i).getPhoneNum();
				a[3]=customerList.get(i).getAddress();
				a[4]=""+customerList.get(i).getMemPoint();
				if(customerList.get(i).isOverdue()==1)
					a[5]=" V";
				else
					a[5]="";
				mainModel.addRow(a);
				i++;
			}	
		}
	}
	
	public void showCustomerBySearch() throws UnsupportedEncodingException
	{
		ArrayList<Customer> customerList=null;
		int temp=Integer.parseInt(txtP_num.getText());
		
		Administrator admin=(Administrator)getMainSystem().getUser();

		customerList = admin.getCustomerSearch(mainSystem.getCustomerDB(), type, keyword, temp);

		if(customerList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<customerList.size())
			{
				String[] a=new String[6];
				a[0]=""+customerList.get(i).getId();
				a[1]=""+customerList.get(i).getName();
				a[2]=customerList.get(i).getPhoneNum();
				a[3]=customerList.get(i).getAddress();
				a[4]=""+customerList.get(i).getMemPoint();
				if(customerList.get(i).isOverdue()==1)
					a[5]=" V";
				else
					a[5]="";
				mainModel.addRow(a);
				i++;
			}	
		}
	}
	
	public MainSystem getMainSystem()
	{
		return mainSystem;
	}
}
