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
import Mainsys.Rent;
import User.Administrator;

public class RentPage extends JFrame {

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
	private int mode; // 0 : all , 1: overdue / 2: title / 3:phoneNumber
	private JLabel lblLast_num;

	public MainSystem getMainSystem() {
		return mainSystem;
	}

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { RentPage frame = new RentPage();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	public RentPage(MainSystem mainSystem) {
		setResizable(false);
		this.mainSystem = mainSystem;
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
		// label.setIcon(new
		// ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\Logo.png"));
		Image imgLogo = new ImageIcon(this.getClass().getResource("/Logo.png"))
		.getImage();
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
		// button.addActionListener(new frameDestroy(this));
		// button.setIcon(new
		// ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\title.png"));
		Image imgTitle = new ImageIcon(this.getClass()
				.getResource("/title.png")).getImage();
		button.setIcon(new ImageIcon(imgTitle));
		button.setBorderPainted(false);
		button.setBackground(Color.WHITE);
		button.setBounds(133, 48, 480, 43);
		contentPane.add(button);

		JLabel label_1 = new JLabel("");
		// label_1.setIcon(new
		// ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\titlecc.png"));
		Image imgTitlecc = new ImageIcon(this.getClass().getResource(
				"/titlecc.png")).getImage();
		label_1.setIcon(new ImageIcon(imgTitlecc));
		label_1.setBounds(627, 63, 240, 28);
		contentPane.add(label_1);

		JRadioButton rdbtnTItle = new JRadioButton("Title");
		rdbtnTItle.setBackground(Color.WHITE);
		rdbtnTItle.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		rdbtnTItle.setForeground(Color.DARK_GRAY);
		rdbtnTItle.setBounds(604, 150, 76, 27);
		contentPane.add(rdbtnTItle);

		JRadioButton rdbtnPhoneNumber = new JRadioButton("Phone number");
		rdbtnPhoneNumber.setForeground(Color.DARK_GRAY);
		rdbtnPhoneNumber.setBackground(Color.WHITE);
		rdbtnPhoneNumber.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		rdbtnPhoneNumber.setBounds(678, 150, 139, 27);
		contentPane.add(rdbtnPhoneNumber);
		
		rdbtnTItle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnPhoneNumber.setSelected(false);
			}
		});
		rdbtnPhoneNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnTItle.setSelected(false);
			}
		});
		
		
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

		JButton btnSreach = new JButton("");
		Image imgsearch = new ImageIcon(this.getClass().getResource(
				"/search.png")).getImage();
		btnSreach.setIcon(new ImageIcon(imgsearch));
		btnSreach.setForeground(Color.WHITE);
		btnSreach.setBorderPainted(false);
		btnSreach.setBackground(Color.WHITE);
		btnSreach.setBounds(570, 150, 24, 24);
		
		btnSreach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				mainModel.setRowCount(0);
				txtP_num.setText("1");

				if(rdbtnTItle.isSelected()==true)
					try {
						mode=2;

						keyword=txtSearch.getText();
						numberOfCustomer=mainSystem.getRentDB().countRentByTitle(keyword);
						numberOfCustomer=(numberOfCustomer/100)+1;
						//System.out.println("keyword : "+ keyword);
					//	System.out.println("numberOfCustomer : "+ numberOfCustomer);
						lblLast_num.setText(""+numberOfCustomer);

						showRentByTitle(keyword);
						
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(rdbtnPhoneNumber.isSelected()==true)
					try {
						mode=3;
						keyword=txtSearch.getText();
						numberOfCustomer=mainSystem.getRentDB().countRentByphoneNumber(keyword);
						numberOfCustomer=(numberOfCustomer/100)+1;

						lblLast_num.setText(""+numberOfCustomer);

						showRentByphoneNumber(keyword);
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

		String[] col3 = { "No.", "title", "start date", "due date", "overdue"};
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
		table.getColumn("title").setPreferredWidth(160);
		table.getColumn("start date").setPreferredWidth(80);
		table.getColumn("start date").setCellRenderer(celAlignCenter);
		table.getColumn("due date").setPreferredWidth(80);
		table.getColumn("due date").setCellRenderer(celAlignCenter);
		table.getColumn("overdue").setPreferredWidth(30);
		table.getColumn("overdue").setCellRenderer(celAlignCenter);

		Image imgaddc = new ImageIcon(this.getClass().getResource(
				"/addcustom.png")).getImage();
		Image imgdelc = new ImageIcon(this.getClass().getResource(
				"/delcustom.png")).getImage();
		Image imgeditc = new ImageIcon(this.getClass().getResource(
				"/editcustom.png")).getImage();
		Image imgdeco2 = new ImageIcon(this.getClass()
				.getResource("/deco2.png")).getImage();

		
		
		JButton btnOverdueCustomer = new JButton("");
		btnOverdueCustomer.setBackground(Color.WHITE);
		Image imgoverdue = new ImageIcon(this.getClass().getResource(
				"/overduerent.png")).getImage();
		btnOverdueCustomer.setIcon(new ImageIcon(imgoverdue));
		btnOverdueCustomer.setBorderPainted(false);
		btnOverdueCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mode=1;
				mainModel.setRowCount(0);
				txtP_num.setText("1");
				try {
					numberOfCustomer=mainSystem.getRentDB().countOverdueRent();
					numberOfCustomer=(numberOfCustomer/100)+1;
					lblLast_num.setText(""+numberOfCustomer);
					ShowOverdueRent();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnOverdueCustomer.setForeground(Color.DARK_GRAY);
		btnOverdueCustomer.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnOverdueCustomer.setBounds(75, 361, 143, 68);
		contentPane.add(btnOverdueCustomer);

		JButton btnAdministrator = new JButton("");
		btnAdministrator.setBackground(Color.WHITE);
		Image imgadcustom = new ImageIcon(this.getClass().getResource(
				"/adminrent.png")).getImage();
		btnAdministrator.setIcon(new ImageIcon(imgadcustom));
		btnAdministrator.setBorderPainted(false);
		btnAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPage btnAdmin = new AdminPage(getMainSystem());
				dispose();
				btnAdmin.setVisible(true);
			}
		});
		btnAdministrator.setForeground(Color.DARK_GRAY);
		btnAdministrator.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnAdministrator.setBounds(75, 446, 143, 68);
		contentPane.add(btnAdministrator);

		JLabel lblwelcome = new JLabel(
				"Welcome! This is an administrator page.");
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
		Image imghome = new ImageIcon(this.getClass().getResource("/home.png"))
		.getImage();
		btnHome.setIcon(new ImageIcon(imghome));
		btnHome.setBounds(75, 530, 143, 68);
		contentPane.add(btnHome);

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

		JButton inputButton = new JButton("");
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int numTemp=Integer.parseInt(txtP_num.getText());
				if((numTemp<=numberOfCustomer)&&(numTemp>0))
				{
					txtP_num.setText(""+numTemp);
					mainModel.setRowCount(0);

					if(mode==0)
					{
						try {
							ShowAllRent();
						} catch (UnsupportedEncodingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					else if(mode==1)
					{
						try {
							ShowOverdueRent();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					else if(mode==2)
					{
						try {
							showRentByTitle(keyword);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						try {
							showRentByphoneNumber(keyword);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
					{
						try {
							ShowAllRent();
						} catch (UnsupportedEncodingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					else if(mode==1)
					{
						try {
							ShowOverdueRent();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					else if(mode==2)
					{
						try {
							showRentByTitle(keyword);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						try {
							showRentByphoneNumber(keyword);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
					{
						try {
							ShowAllRent();
						} catch (UnsupportedEncodingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					else if(mode==1)
					{
						try {
							ShowOverdueRent();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					else if(mode==2)
					{
						try {
							showRentByTitle(keyword);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						try {
							showRentByphoneNumber(keyword);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});



		JButton btnreturn = new JButton("");
		Image imgreturn = new ImageIcon(this.getClass().getResource(
				"/return.png")).getImage();
		btnreturn.setIcon(new ImageIcon(imgreturn));

		btnreturn.setForeground(Color.DARK_GRAY);
		btnreturn.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnreturn.setBorderPainted(false);
		btnreturn.setBackground(Color.WHITE);
		btnreturn.setBounds(75, 276, 143, 68);
		
		btnreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getValue=mainModel.getValueAt(table.getSelectedRow(), 0).toString();
				int R_id=Integer.parseInt(getValue);
				//Administrator admin=(Administrator)mainSystem.getUser();
				mainSystem.getUser().returnDVD(mainSystem.getRentDB(), R_id);
				//admin.deleteCustomer(c_id, mainSystem.getCustomerDB());
				mainModel.removeRow(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "return Successfully");
				try {
					mainSystem.getRentDB().updateOverdueAfterRent();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		contentPane.add(btnreturn);

		JButton btnAll = new JButton("");
		Image btnall = new ImageIcon(this.getClass()
				.getResource("/Alllist.png")).getImage();
		btnAll.setIcon(new ImageIcon(btnall));
		btnAll.setForeground(Color.DARK_GRAY);
		btnAll.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnAll.setBorderPainted(false);
		btnAll.setBackground(Color.WHITE);
		btnAll.setBounds(75, 190, 143, 68);
		contentPane.add(btnAll);
		
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mode=0;
				mainModel.setRowCount(0);
				txtP_num.setText("1");
				try {
					numberOfCustomer=mainSystem.getRentDB().countAllRent();
					numberOfCustomer=(numberOfCustomer/100)+1;
					lblLast_num.setText(""+numberOfCustomer);
					ShowAllRent();
		
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void ShowAllRent() throws UnsupportedEncodingException //1-released_date ,  3-title.
	{

		ArrayList<Rent> rentList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		Administrator admin=(Administrator)getMainSystem().getUser();
		rentList = admin.getAllRent(mainSystem.getRentDB(), temp);
		if(rentList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<rentList.size())
			{
				String[] a=new String[6];
				a[0]=""+rentList.get(i).getRid();
				a[1]=""+rentList.get(i).getTitle();
				a[2]=rentList.get(i).getStartDate();
				a[3]=rentList.get(i).getDueDate();
				if(rentList.get(i).getOverdue()==1)
					a[4]=" V";
				else
					a[4]="";
				mainModel.addRow(a);
				i++;
			}	
		}
	}

	public void ShowOverdueRent() throws UnsupportedEncodingException
	{


		ArrayList<Rent> rentList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		Administrator admin=(Administrator)getMainSystem().getUser();
		rentList = admin.getAllOverdueRent(mainSystem.getRentDB(), temp);
		if(rentList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<rentList.size())
			{
				String[] a=new String[6];
				a[0]=""+rentList.get(i).getRid();
				a[1]=""+rentList.get(i).getTitle();
				a[2]=rentList.get(i).getStartDate();
				a[3]=rentList.get(i).getDueDate();
				if(rentList.get(i).getOverdue()==1)
					a[4]=" V";
				else
					a[4]="";
				mainModel.addRow(a);
				i++;
			}	
		}
	}

	public void showRentByTitle(String title) throws UnsupportedEncodingException
	{


		ArrayList<Rent> rentList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		Administrator admin=(Administrator)getMainSystem().getUser();
		rentList = admin.getRentByTitle(mainSystem.getRentDB(), title, temp);
		if(rentList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<rentList.size())
			{
				String[] a=new String[6];
				a[0]=""+rentList.get(i).getRid();
				a[1]=""+rentList.get(i).getTitle();
				a[2]=rentList.get(i).getStartDate();
				a[3]=rentList.get(i).getDueDate();
				if(rentList.get(i).getOverdue()==1)
					a[4]=" V";
				else
					a[4]="";
				mainModel.addRow(a);
				i++;
			}	
		}
	}

	public void showRentByphoneNumber(String phoneNum) throws UnsupportedEncodingException
	{


		ArrayList<Rent> rentList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		Administrator admin=(Administrator)getMainSystem().getUser();
		rentList = admin.getRentByPhoneNumber(mainSystem.getRentDB(), phoneNum, temp);
		if(rentList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<rentList.size())
			{
				String[] a=new String[6];
				a[0]=""+rentList.get(i).getRid();
				a[1]=""+rentList.get(i).getTitle();
				a[2]=rentList.get(i).getStartDate();
				a[3]=rentList.get(i).getDueDate();
				if(rentList.get(i).getOverdue()==1)
					a[4]=" V";
				else
					a[4]="";
				mainModel.addRow(a);
				i++;
			}	
		}
	}
}