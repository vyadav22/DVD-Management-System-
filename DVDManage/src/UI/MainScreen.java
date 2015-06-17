package UI;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Mainsys.DVD;
import Mainsys.MainSystem;
import User.Administrator;
import User.Customer;

public class MainScreen {

	public JFrame frame;
	private JTextField txtSearch;

	private JButton btnNewButton_1;

	private JLabel lblDate;
	private JTable table;
	private DefaultTableModel mainModel;

	private MainSystem mainSystem;
	private JRadioButton rdbtnTitle;
	private JRadioButton rdbtnYear;
	private JLabel lblLast_num;
	private int numberOfDVD; //show maximum-page
	private int mode; // 0 - all , 1- title,year search, 2-gerne search
	private String type;
	private String keyword;
	private JTextField txtP_num;
	//test
	private String adminID = null;
	private String adminPW = null;
	private int checkbtnstatue = 0;

	private JButton btnMyPage;
	private JLabel lblWelcomeId;
	private JButton btnRent;
	private JButton btnlogin;
	private JButton btnLogout;
	private JTextField txtId;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//check date


	/*
	 * Create the application.
	 */

	public MainScreen(MainSystem tempMain) {
		if(tempMain==null)
			mainSystem = new MainSystem();
		else
		{

			mainSystem=tempMain;
		}
		initialize();

		//		calender();
	}

	//	public void setMainsystem(MainSystem tempMain)
	//	{
	//		mainSystem=null;
	//		mainSystem=tempMain;
	//		System.out.println("user id"+mainSystem.getUser().getId());
	//	}
	/*
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		mode=0;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel img_logo = new JLabel("");
		img_logo.setBackground(Color.MAGENTA);
		//		img_logo.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\Logo.png"));
		Image imgLogo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
		img_logo.setIcon(new ImageIcon(imgLogo));
		img_logo.setBounds(14, 12, 130, 105);
		frame.getContentPane().add(img_logo);

		txtSearch = new JTextField();
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearch.setText("");
			}
		});
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setFont(new Font("Adobe Arabic", Font.PLAIN, 15));
		txtSearch.setForeground(Color.DARK_GRAY);
		txtSearch.setText("Search");
		txtSearch.setBounds(298, 151, 258, 24);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);		

		int userID=mainSystem.getUser().getId();
		//Id
		if(userID==0)
		{
			getLoginWindow();

		}
		else if(userID==1)
		{
			Adminlogin();
			logout();
		}
		else
		{

			Customerlogin();
			logout();

		}


		//When people search sth, screen changes.
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorderPainted(false);

		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(570, 151, 24, 24);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainModel.setRowCount(0);
				txtP_num.setText("1");
				mode=1;

				if(rdbtnTitle.isSelected()==true)
					try {

						type="title";
						keyword=txtSearch.getText();
						numberOfDVD=mainSystem.getDVDDB().countSearchDVD(type, ""+keyword);
						numberOfDVD=(numberOfDVD/100)+1;

						lblLast_num.setText(""+numberOfDVD);

						ShowDVDListByType();

					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(rdbtnYear.isSelected()==true)
					try {
						type="release_date";
						keyword=txtSearch.getText();
						numberOfDVD=mainSystem.getDVDDB().countSearchDVD(type, ""+keyword);
						numberOfDVD=(numberOfDVD/100)+1;

						lblLast_num.setText(""+numberOfDVD);
						ShowDVDListByType();

					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				//rdbtnYear.setSelected(false);
				//ShowDVDListByType();

			}
		});

		frame.getContentPane().add(btnNewButton_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(36, 138, 222, 497);
		frame.getContentPane().add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Genre", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 217, 469);
		panel.add(scrollPane);

		//List of Genre
		JTree tree = new JTree();
		tree.setBorder(null);
		scrollPane.setViewportView(tree);
		tree.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		DefaultTreeModel treeModel = new DefaultTreeModel(
				new DefaultMutableTreeNode("ALL") {
					{
						//					add(new DefaultMutableTreeNode(new CheckBoxNode("Accessibility", true)));
						add(new DefaultMutableTreeNode("Action"));
						add(new DefaultMutableTreeNode("Adventure"));
						//add(new DefaultMutableTreeNode("Action/Adventure"));
					//	add(new DefaultMutableTreeNode("Action/Comedy"));
						add(new DefaultMutableTreeNode("Animation"));
						add(new DefaultMutableTreeNode("Anime"));
						add(new DefaultMutableTreeNode("Comedy"));
						//add(new DefaultMutableTreeNode("Comedy/Drama"));
						//add(new DefaultMutableTreeNode("Dance/Ballet"));
						add(new DefaultMutableTreeNode("Documentary"));
						add(new DefaultMutableTreeNode("Drama"));
						add(new DefaultMutableTreeNode("Exercise"));
						add(new DefaultMutableTreeNode("Family"));
						add(new DefaultMutableTreeNode("Fantasy"));
						add(new DefaultMutableTreeNode("Foreign"));
						add(new DefaultMutableTreeNode("Horror"));
						add(new DefaultMutableTreeNode("Late Night"));
						add(new DefaultMutableTreeNode("Music"));
						add(new DefaultMutableTreeNode("Musical"));
						add(new DefaultMutableTreeNode("Mystery"));
						//add(new DefaultMutableTreeNode("Mystery/Suspens"));
						add(new DefaultMutableTreeNode("Opera"));
						add(new DefaultMutableTreeNode("SciFi"));
						add(new DefaultMutableTreeNode("Silent"));
						add(new DefaultMutableTreeNode("Software"));
						add(new DefaultMutableTreeNode("Special Interes"));
						add(new DefaultMutableTreeNode("Sports"));
						add(new DefaultMutableTreeNode("Suspense"));
					//	add(new DefaultMutableTreeNode("Suspense/Thrill"));
						add(new DefaultMutableTreeNode("Thriller"));
						add(new DefaultMutableTreeNode("TV Classics"));
						add(new DefaultMutableTreeNode("VAR"));
						add(new DefaultMutableTreeNode("War"));
						add(new DefaultMutableTreeNode("Western"));
					}
				});
		tree.setModel(treeModel);
		MouseListener treeListener = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = tree.getRowForLocation(e.getX(), e.getY());

				mainModel.setRowCount(0);

				txtP_num.setText("1");

				if(selRow==0)
				{
					mode=0;
					ShowAllDVDList();
					numberOfDVD=mainSystem.getDVDDB().countAllDVD();

				}
				else
				{
					mode=2;
					System.out.println("gd"+treeModel.getChild(treeModel.getRoot(), selRow-1) );
					keyword=treeModel.getChild(treeModel.getRoot(), selRow-1).toString();
					ShowDVDListByGenre();

					try {
						numberOfDVD=mainSystem.getDVDDB().countGenreDVD(keyword);
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				numberOfDVD=(numberOfDVD/100)+1;
				lblLast_num.setText(""+numberOfDVD);

			}
		};
		tree.addMouseListener(treeListener);

		//tree.
		//Calendar
		JButton btnPre = new JButton("");
		btnPre.setBackground(Color.WHITE);
		Image imgpre = new ImageIcon(this.getClass().getResource("/pre.png")).getImage();
		btnPre.setIcon(new ImageIcon(imgpre));
		btnPre.setBorderPainted(false);
		btnPre.setBounds(653, 623, 53, 40);
		frame.getContentPane().add(btnPre);



		txtP_num = new JTextField();
		txtP_num.setText("1");
		txtP_num.setBounds(715, 632, 39, 24);
		frame.getContentPane().add(txtP_num);
		txtP_num.setColumns(10);

		numberOfDVD = mainSystem.getDVDDB().countAllDVD();
		numberOfDVD=(numberOfDVD/100)+1;
		lblLast_num = new JLabel(""+numberOfDVD);
		lblLast_num.setBounds(784, 632, 31, 18);
		frame.getContentPane().add(lblLast_num);

		JButton btnLast = new JButton("");
		btnLast.setBackground(Color.WHITE);
		Image imglater = new ImageIcon(this.getClass().getResource("/later.png")).getImage();
		btnLast.setIcon(new ImageIcon(imglater));
		btnLast.setBorderPainted(false);
		btnLast.setBounds(814, 623, 53, 40);
		frame.getContentPane().add(btnLast);		

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int numTemp=Integer.parseInt(txtP_num.getText());
				if((numTemp<=numberOfDVD)&&(numTemp>0))
				{
					txtP_num.setText(""+numTemp);
					mainModel.setRowCount(0);
					if(mode==0)
						ShowAllDVDList();
					else if(mode==1)
					{
						try {
							ShowDVDListByType();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						ShowDVDListByGenre();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "It's over the boundary");

			}
		});
		button.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
		button.setForeground(Color.WHITE);
		button.setBorderPainted(false);
		button.setBackground(Color.WHITE);
		button.setBounds(755, 630, 24, 24);
		frame.getContentPane().add(button);

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
						ShowAllDVDList();
					else if(mode==1)
					{
						try {
							ShowDVDListByType();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						ShowDVDListByGenre();
					}
				}
			}
		});

		btnLast.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int numTemp=Integer.parseInt(txtP_num.getText());
				if(numTemp<3005)
				{
					numTemp=numTemp+1;
					txtP_num.setText(""+numTemp);

					mainModel.setRowCount(0);
					if(mode==0)
						ShowAllDVDList();
					else if(mode==1)
					{
						try {
							ShowDVDListByType();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						ShowDVDListByGenre();
					}
				}
			}
		});


		lblDate = new JLabel(myCalendar.getDate());
		lblDate.setFont(new Font("Calibri Light", Font.BOLD, 16));
		lblDate.setForeground(Color.DARK_GRAY);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(964, 152, 173, 24);
		frame.getContentPane().add(lblDate);

		JScrollPane DVDListTable = new JScrollPane();
		DVDListTable.setBounds(297, 189, 923, 422);
		frame.getContentPane().add(DVDListTable);



		//		String [] col3 = {"No.","title","studio","price","rating","genre","release_date"};
		//		mainModel = new DefaultTableModel(col3,0);
		//		//table.
		//		table = new JTable(mainModel);
		//		table.addMouseListener(new MouseAdapter() {
		//			@Override
		//			public void mouseClicked(MouseEvent arg0) {
		//				checkbtnstatue = 1;
		//			}
		//		});
		//
		//		ShowAllDVDList();
		//
		//
		//
		//
		//
		//		DVDListTable.setViewportView(table);

		String[] col3 = { "No.", "Title", "Studio", "Price", "Rating", "Genre",
		"Released date" };
		mainModel = new DefaultTableModel(col3, 0);
		// table.
		table = new JTable(mainModel);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkbtnstatue = 1;
			}
		});

		ShowAllDVDList();

		DVDListTable.setViewportView(table);

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getTableHeader().setDefaultRenderer(renderer);

		table.getColumn("No.").setPreferredWidth(5);
		table.getColumn("No.").setCellRenderer(celAlignCenter);
		table.getColumn("Title").setPreferredWidth(200);
		table.getColumn("Studio").setPreferredWidth(80);
		table.getColumn("Price").setPreferredWidth(10);
		table.getColumn("Price").setCellRenderer(celAlignCenter);
		table.getColumn("Rating").setPreferredWidth(10);
		table.getColumn("Rating").setCellRenderer(celAlignCenter);
		table.getColumn("Genre").setPreferredWidth(15);
		table.getColumn("Genre").setCellRenderer(celAlignCenter);
		table.getColumn("Released date").setPreferredWidth(20);
		table.getColumn("Released date").setCellRenderer(celAlignCenter);

		JButton btntitle = new JButton("");
		btntitle.setBackground(Color.WHITE);
		//				btntitle.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\title.png"));
		Image imgTitle = new ImageIcon(this.getClass().getResource("/title.png")).getImage();
		btntitle.setIcon(new ImageIcon(imgTitle));
		btntitle.setBorderPainted(false);
		btntitle.setBounds(133, 48, 480, 43);
		frame.getContentPane().add(btntitle);

		JLabel lbltitle = new JLabel("");
		//				lbltitle.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\titlecc.png"));
		Image imgTitlecc = new ImageIcon(this.getClass().getResource("/titlecc.png")).getImage();
		lbltitle.setIcon(new ImageIcon(imgTitlecc));
		lbltitle.setBounds(627, 63, 240, 28);
		frame.getContentPane().add(lbltitle);

		rdbtnTitle = new JRadioButton("Title");
		rdbtnTitle.setSelected(true);
		rdbtnTitle.setBackground(Color.WHITE);
		rdbtnTitle.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		rdbtnTitle.setBounds(604, 150, 66, 27);
		frame.getContentPane().add(rdbtnTitle);

		rdbtnYear = new JRadioButton("Released Year");
		rdbtnYear.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		rdbtnYear.setBackground(Color.WHITE);
		rdbtnYear.setBounds(676, 150, 119, 27);
		frame.getContentPane().add(rdbtnYear);

		//User can choose one of rdbtn
		rdbtnTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnTitle.setSelected(true);
				rdbtnYear.setSelected(false);
			}
		});
		rdbtnYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnYear.setSelected(true);
				rdbtnTitle.setSelected(false);
			}
		});



		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(469, 151, 1, 1);
		frame.getContentPane().add(layeredPane);


	}

	public void ShowAllDVDList()
	{

		ArrayList<DVD> dvdList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		try {
			dvdList = mainSystem.getUser().getAllDVDList(mainSystem.getDVDDB(),temp);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int i=0;
		while(i<dvdList.size())
		{
			String[] a=new String[7];
			a[0]=""+dvdList.get(i).getId();
			a[1]=dvdList.get(i).getTitle();
			a[2]=dvdList.get(i).getStudio();
			a[3]=""+dvdList.get(i).getPrice();
			a[4]=dvdList.get(i).getRating();
			a[5]=dvdList.get(i).getGenre();
			a[6]=dvdList.get(i).getDate();

			mainModel.addRow(a);
			i++;
		}
	}

	public void ShowDVDListByType() throws UnsupportedEncodingException //1-released_date ,  3-title.
	{

		ArrayList<DVD> dvdList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		dvdList = mainSystem.getUser().getDVDSearch(type, keyword, mainSystem.getDVDDB(),temp);

		if(dvdList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<dvdList.size())
			{
				String[] a=new String[7];
				a[0]=""+dvdList.get(i).getId();
				a[1]=dvdList.get(i).getTitle();
				a[2]=dvdList.get(i).getStudio();
				a[3]=""+dvdList.get(i).getPrice();
				a[4]=dvdList.get(i).getRating();
				a[5]=dvdList.get(i).getGenre();
				a[6]=dvdList.get(i).getDate();

				mainModel.addRow(a);
				i++;
			}	
		}
	}

	 public void Adminlogin()
	   {
	      btnMyPage = new JButton("");
	      btnMyPage.setBorderPainted(false);
	      btnMyPage.setBackground(Color.WHITE);
	      Image btnInfo = new ImageIcon(this.getClass()
	            .getResource("/adminpage.png")).getImage();
	      btnMyPage.setIcon(new ImageIcon(btnInfo));
	      btnMyPage.setBounds(1147, 22, 93, 38);
	      frame.getContentPane().add(btnMyPage);   

	      btnMyPage.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseClicked(MouseEvent e) {
	            AdminPage admin = new AdminPage(
	                  mainSystem);
	            admin.setVisible(true);
	            frame.dispose();
	         }
	      });
		Administrator admin=(Administrator)mainSystem.getUser();

		lblWelcomeId = new JLabel("Welcome," + "admin"
				+ ".");
		lblWelcomeId.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblWelcomeId.setForeground(Color.DARK_GRAY);
		lblWelcomeId
		.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWelcomeId.setBounds(897, 42, 240, 18);
		frame.getContentPane().add(lblWelcomeId);

		btnRent = new JButton("");
		btnRent.setBorderPainted(false);
		Image btnrent = new ImageIcon(this.getClass()
				.getResource("/rentadd.png")).getImage();
		btnRent.setIcon(new ImageIcon(btnrent));
		btnRent.setForeground(Color.WHITE);
		btnRent.setBackground(Color.WHITE);
		btnRent.setBounds(1150, 140, 73, 39);
		frame.getContentPane().add(btnRent);

		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkbtnstatue == 1) {
					String tempID=mainModel.getValueAt(table.getSelectedRow(), 0).toString();
					int d_id=Integer.parseInt(tempID);
					//	int isOverdue=mainSystem.getRentDB().isOverdueDVD(d_id);
					try {
						if(mainSystem.getRentDB().isRentDVD(d_id)==null)
						{
							System.out.println("dd");
							String tempPrice=mainModel.getValueAt(table.getSelectedRow(), 3).toString();
							double price=Double.parseDouble(tempPrice);
							RentAdmin rentpopup = new RentAdmin(mainSystem,d_id,price);
							rentpopup.setVisible(true);
							checkbtnstatue = 0;
						}
						else
						{
							JOptionPane
							.showMessageDialog(null,
									"This DVD is out of stock");
						}
					} catch (NumberFormatException
							| HeadlessException
							| UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane
					.showMessageDialog(null,
							"Please click first a DVD which you want to rent.");
				}
			}
		});
	}

	public void Customerlogin()
	{
		btnMyPage = new JButton("");
		btnMyPage.setBorderPainted(false);
		btnMyPage.setBackground(Color.WHITE);
		Image btnInfo = new ImageIcon(this.getClass()
				.getResource("/mypage.png")).getImage();
		btnMyPage.setIcon(new ImageIcon(btnInfo));
		btnMyPage.setBounds(1147, 22, 73, 38);
		frame.getContentPane().add(btnMyPage);   

		btnMyPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OrdiCustom myrentlist = new OrdiCustom(
						mainSystem);
				myrentlist.setVisible(true);
				frame.dispose();
			}
		});

		Customer customer = (Customer)mainSystem.getUser();
		lblWelcomeId = new JLabel("Welcome," + customer.getName()
				+ ".");
		lblWelcomeId.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblWelcomeId.setForeground(Color.DARK_GRAY);
		lblWelcomeId
		.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWelcomeId.setBounds(897, 42, 240, 18);
		frame.getContentPane().add(lblWelcomeId);

		btnRent = new JButton("");
		btnRent.setBorderPainted(false);
		Image btnrent = new ImageIcon(this.getClass()
				.getResource("/rentadd.png")).getImage();
		btnRent.setIcon(new ImageIcon(btnrent));
		btnRent.setForeground(Color.WHITE);
		btnRent.setBackground(Color.WHITE);
		btnRent.setBounds(1150, 140, 73, 39);
		frame.getContentPane().add(btnRent);

		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkbtnstatue == 1) {

					String tempID=mainModel.getValueAt(table.getSelectedRow(), 0).toString();
					int d_id=Integer.parseInt(tempID);
					//	int isOverdue=mainSystem.getRentDB().isOverdueDVD(d_id);
					try {
						if(mainSystem.getRentDB().isValidToRent(mainSystem.getUser().getId())==null)
							JOptionPane.showMessageDialog(null,"you can't rent.\n You have overdue DVD \n or already rent maxium number(5) of DVDs");
						else
						{
							if(mainSystem.getRentDB().isRentDVD(d_id)==null) //DVD in stock
							{
								System.out.println("dd");
								String tempPrice=mainModel.getValueAt(table.getSelectedRow(), 3).toString();
								double price=Double.parseDouble(tempPrice);
								//RentAdmin rentpopup = new RentAdmin(mainSystem,d_id,price);
								//rentpopup.setVisible(true);

								RentCustomer rentpopup = new RentCustomer(mainSystem,d_id,price, (Customer)mainSystem.getUser());
								rentpopup.setVisible(true);
								checkbtnstatue = 0;
							}
							else
							{
								JOptionPane
								.showMessageDialog(null,
										"This DVD is out of stock");
							}
						}
					} catch (NumberFormatException
							| HeadlessException
							| UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane
					.showMessageDialog(null,
							"Please click first a DVD which you want to rent.");
				}
			}
		});
	}

	public void logout()
	{
		btnLogout = new JButton("");
		Image imglogout = new ImageIcon(this.getClass().getResource(
				"/logout.jpg")).getImage();
		btnLogout.setIcon(new ImageIcon(imglogout));
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setBorderPainted(false);
		btnLogout.setBounds(1165, 63, 55, 27);

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainSystem.logout();
				//					txtId.setVisible(true);
				//					pwdPassword.setVisible(true);
				//					btnlogin.setVisible(true);
				//					txtId.setText("ID");
				//					pwdPassword.setText("77777");

				btnLogout.setVisible(false);
				btnRent.setVisible(false);
				lblWelcomeId.setVisible(false);
				btnMyPage.setVisible(false);
				getLoginWindow();

			}
		});

		frame.getContentPane().add(btnLogout);
	}
	
	public void login()
	{
		adminID = txtId.getText();
		adminPW = pwdPassword.getText();

		int checkNum=0;
		try {
			checkNum = mainSystem.login(adminID, adminPW);
		} catch (NumberFormatException
				| UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (checkNum==0)
		{
			JOptionPane.showMessageDialog(null, "Your ID or Password is incorrect.\nPlease, check again.");
		}
		else if (checkNum==1)//////////////////////////////////////////////////////////////////Manage+rent
		{
			//						frame.dispose();
			//						mainSystem.beAdmin();
			//						AdminPage adminpage = new AdminPage(mainSystem);
			//						adminpage.setVisible(true);
			// frame.dispose();
			mainSystem.beAdmin();

			txtId.setVisible(false);
			pwdPassword.setVisible(false);
			btnlogin.setVisible(false);
			Adminlogin();	
			logout();

		}
		else///////////////////////////////Manage+rent
		{

			try {
				mainSystem.beCustomer(checkNum);
				Customer tempUser=(Customer)mainSystem.getUser();
				if(tempUser.isOverdue()==1)
					JOptionPane.showMessageDialog(null, "Your Rent is Overdue!!");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			txtId.setVisible(false);
			pwdPassword.setVisible(false);
			btnlogin.setVisible(false);
			Customerlogin();
			logout();

			//					btnLogout = new JButton("");
			//				      Image imglogout = new ImageIcon(this.getClass().getResource(
			//				            "/logout.jpg")).getImage();
			//				      btnLogout.setIcon(new ImageIcon(imglogout));
			//				      btnLogout.setBackground(Color.WHITE);
			//				      btnLogout.setBorderPainted(false);
			//				      btnLogout.setBounds(1165, 63, 55, 27);
			//				      frame.getContentPane().add(btnLogout);
			///////////////////////////////////////////////////////////if-else end
		}
	}

	public void getLoginWindow()
	{

		txtId = new JTextField(15);		
		txtId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtId.setText("");
			}
		});

		txtId.setForeground(Color.DARK_GRAY);
		txtId.setHorizontalAlignment(SwingConstants.LEFT);
		txtId.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		txtId.setText("ID");
		txtId.setColumns(10);
		txtId.setBounds(943, 27, 110, 24);
		frame.getContentPane().add(txtId);

		pwdPassword = new JPasswordField(15);
		pwdPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pwdPassword.setText("");
			}
		});
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdPassword.setText("");
			}
		});
		
		
		pwdPassword.setForeground(Color.DARK_GRAY);
		pwdPassword.setText("Password");
		pwdPassword.setBounds(1060, 27, 110, 24);
		pwdPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				login();
			}
		});
		frame.getContentPane().add(pwdPassword);

		btnlogin = new JButton("Login");

		//test_login
		//if (adminID == "admin" && adminPW == "1234")
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});


		btnlogin.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		btnlogin.setIcon(null);
		btnlogin.setBounds(1177, 27, 66, 24);
		frame.getContentPane().add(btnlogin);
	}

	public void ShowDVDListByGenre() 
	{

		ArrayList<DVD> dvdList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		try {
			dvdList = mainSystem.getUser().getDVDByGenre(keyword, mainSystem.getDVDDB(), temp);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(dvdList==null)
			JOptionPane.showMessageDialog(null, "can't find anything");
		else
		{
			int i=0;
			while(i<dvdList.size())
			{
				String[] a=new String[7];
				a[0]=""+dvdList.get(i).getId();
				a[1]=dvdList.get(i).getTitle();
				a[2]=dvdList.get(i).getStudio();
				a[3]=""+dvdList.get(i).getPrice();
				a[4]=dvdList.get(i).getRating();
				a[5]=dvdList.get(i).getGenre();
				a[6]=dvdList.get(i).getDate();

				mainModel.addRow(a);
				i++;
			}	
		}
	}
}
