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

import Mainsys.DVD;
import Mainsys.MainSystem;
import User.Administrator;


public class DVDListEdit extends JFrame {

	private JPanel btnAdminpage;
	private JTextField txtSearch;

	private JFrame frame;
	private MainSystem mainSystem;
	private JTable table;
	private DefaultTableModel mainModel;


	private int numberOfDVD; //show maximum-page
	private String type;
	private String keyword;
	private JTextField txtP_num;
	private JLabel lblLast_num;
	private int mode; // 0 : search by title and year / 1: available DVDs // 2: all DVDs
	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					DVDListEdit frame = new DVDListEdit();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	//JScrollPane CustomList = new JScrollPane();

	public DVDListEdit(MainSystem mainSystem) {
		this.mainSystem=mainSystem;
		mode=2;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		btnAdminpage = new JPanel();
		btnAdminpage.setBackground(Color.WHITE);
		btnAdminpage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(btnAdminpage);
		btnAdminpage.setLayout(null);

		JLabel lblDate = new JLabel(myCalendar.getDate());
	      lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
	      lblDate.setForeground(Color.DARK_GRAY);
	      lblDate.setFont(new Font("Calibri", Font.PLAIN, 16));
	      lblDate.setBounds(1047, 165, 173, 30);
	      btnAdminpage.add(lblDate);

		JLabel label = new JLabel("");
		//		label.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\Logo.png"));
		Image imgLogo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
		label.setIcon(new ImageIcon(imgLogo));
		label.setBackground(Color.MAGENTA);
		label.setBounds(14, 12, 130, 105);
		btnAdminpage.add(label);

		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainScreen mainpage = new MainScreen(mainSystem);
				mainpage.frame.setVisible(true);
				dispose();
			}
		});
		//		but
		//		button.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\title.png"));
		Image imgTitle = new ImageIcon(this.getClass().getResource("/title.png")).getImage();
		button.setIcon(new ImageIcon(imgTitle));
		button.setBorderPainted(false);
		button.setBackground(Color.WHITE);
		button.setBounds(133, 48, 480, 43);
		btnAdminpage.add(button);

		JLabel label_1 = new JLabel("");
		//		label_1.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\titlecc.png"));
		Image imgTitlecc = new ImageIcon(this.getClass().getResource("/titlecc.png")).getImage();
		label_1.setIcon(new ImageIcon(imgTitlecc));
		label_1.setBounds(627, 63, 240, 28);
		btnAdminpage.add(label_1);

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
		btnAdminpage.add(txtSearch);
		txtSearch.setColumns(10);

		JRadioButton rdbtnTitle = new JRadioButton("Title");
		rdbtnTitle.setSelected(true);
		rdbtnTitle.setBackground(Color.WHITE);
		rdbtnTitle.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		rdbtnTitle.setForeground(Color.DARK_GRAY);
		rdbtnTitle.setBounds(604, 150, 76, 27);
		btnAdminpage.add(rdbtnTitle);

		JRadioButton rdbtnYear = new JRadioButton("Released Year");
		rdbtnYear.setForeground(Color.DARK_GRAY);
		rdbtnYear.setBackground(Color.WHITE);
		rdbtnYear.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		rdbtnYear.setBounds(678, 150, 139, 27);
		btnAdminpage.add(rdbtnYear);

		//User can choose one of rdbtn
		rdbtnTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnYear.setSelected(false);
			}
		});
		rdbtnYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnTitle.setSelected(false);
			}
		});

		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
		button_1.setForeground(Color.WHITE);
		button_1.setBorderPainted(false);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(570, 150, 24, 24);

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mode=0;
				mainModel.setRowCount(0);
				txtP_num.setText("1");

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

		btnAdminpage.add(button_1);

		JScrollPane CustomList = new JScrollPane();
		CustomList.setBounds(297, 198, 923, 422);
		btnAdminpage.add(CustomList);

		   String[] col3 = { "No.", "Title", "Studio", "Price", "Rating", "Genre",
		      "Released date" };
		      mainModel = new DefaultTableModel(col3,0);
		      //table.
		      table = new JTable(mainModel);


		      CustomList.setViewportView(table);
		      
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

		     

		JButton btnAddDVD = new JButton("");
		btnAddDVD.setBackground(Color.WHITE);
		Image btnadddvd = new ImageIcon(this.getClass().getResource("/adddvd.png")).getImage();
		btnAddDVD.setIcon(new ImageIcon(btnadddvd));
		btnAddDVD.setBorderPainted(false);
		btnAddDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDVD btnAdd = new AddDVD(getMainSystem());
				btnAdd.setVisible(true);
			}
		});
		   btnAddDVD.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		      btnAddDVD.setForeground(Color.DARK_GRAY);
		      btnAddDVD.setBounds(63, 290, 155, 57);
		      btnAdminpage.add(btnAddDVD);

		JButton btnDeleteDVD = new JButton("");
		btnDeleteDVD.setBackground(Color.WHITE);
		Image btndeletedvd = new ImageIcon(this.getClass().getResource("/deletedvd.png")).getImage();
		btnDeleteDVD.setIcon(new ImageIcon(btndeletedvd));
		btnDeleteDVD.setBorderPainted(false);
		btnDeleteDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DeleteCustomer btnDel = new DeleteCustomer(getMainSystem());
				//btnDel.setVisible(true);
				//System.out.println(table.getSelectedRow()+"value: "+);
				String getValue=mainModel.getValueAt(table.getSelectedRow(), 0).toString();
				int d_id=Integer.parseInt(getValue);
				Administrator admin=(Administrator)mainSystem.getUser();
				admin.deleteDVD(d_id, mainSystem.getDVDDB());
				mainModel.removeRow(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Delete Successfully");
			}
		});
		btnDeleteDVD.setForeground(Color.DARK_GRAY);
	      btnDeleteDVD.setFont(new Font("Calibri Light", Font.PLAIN, 15));
	      btnDeleteDVD.setBounds(63, 360, 155, 57);
	      btnAdminpage.add(btnDeleteDVD);

		JButton btnEditDVD = new JButton("");
		btnEditDVD.setBackground(Color.WHITE);
		Image btneditdvd = new ImageIcon(this.getClass().getResource("/editdetail.png")).getImage();
		btnEditDVD.setIcon(new ImageIcon(btneditdvd));
		btnEditDVD.setBorderPainted(false);
		btnEditDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		EditDVD btnEdit = new EditDVD(getMainSystem(),mainModel,table);
				btnEdit.setVisible(true);
			}
		});
		 btnEditDVD.setForeground(Color.DARK_GRAY);
	      btnEditDVD.setFont(new Font("Calibri Light", Font.PLAIN, 15));
	      btnEditDVD.setBounds(63, 430, 155, 57);
	      btnAdminpage.add(btnEditDVD);

		JButton btnHome = new JButton("");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainScreen mainpage = new MainScreen(mainSystem);
				mainpage.frame.setVisible(true);
				dispose();
			}
		});
		btnHome.setBackground(Color.WHITE);
	      Image btnhome = new ImageIcon(this.getClass().getResource("/adhome.png")).getImage();
	      btnHome.setIcon(new ImageIcon(btnhome));
	      btnHome.setBorderPainted(false);
	      btnHome.setForeground(Color.DARK_GRAY);
	      btnHome.setFont(new Font("Calibri Light", Font.PLAIN, 15));
	      btnHome.setBounds(63, 569, 155, 57);
	      btnAdminpage.add(btnHome);
		
		JButton btnadmin = new JButton("");
		btnadmin.setBackground(Color.WHITE);
		Image btnadm = new ImageIcon(this.getClass().getResource("/adminback.png")).getImage();
		btnadmin.setIcon(new ImageIcon(btnadm));
		btnadmin.setBorderPainted(false);
		btnadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPage btnAdmin = new AdminPage(getMainSystem());
				dispose();
				btnAdmin.setVisible(true);
				}
		});
		 btnadmin.setForeground(Color.DARK_GRAY);
	      btnadmin.setFont(new Font("Calibri Light", Font.PLAIN, 15));
	      btnadmin.setBounds(63, 499, 155, 57);
	      btnAdminpage.add(btnadmin);

		///////////////////////////////////////////////////////////////////////move to Rent manage window
		
/////////////////////////////////////////////////////////////////////////////////////////////////////

	      JButton btnServiceable = new JButton("");
	      Image imgserv = new ImageIcon(this.getClass().getResource("/serviceable.png")).getImage();
	      btnServiceable.setIcon(new ImageIcon(imgserv));
	      btnServiceable.setForeground(Color.DARK_GRAY);
	      btnServiceable.setFont(new Font("Calibri Light", Font.PLAIN, 15));
	      btnServiceable.setBorderPainted(false);
	      btnServiceable.setBackground(Color.WHITE);
	      btnServiceable.setBounds(63, 220, 155, 57);
	      btnAdminpage.add(btnServiceable);
		
		
		btnServiceable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainModel.setRowCount(0);
				txtP_num.setText("1");
				mode=1;

				numberOfDVD=getMainSystem().getDVDDB().countDVDInStock();
				numberOfDVD=(numberOfDVD/100)+1;
				lblLast_num.setText(""+numberOfDVD);
				ShowDVDInStock();

				//rdbtnYear.setSelected(false);
				//ShowDVDListByType();

			}
		});
		
		JButton btnAll = new JButton("");
	      Image imgall = new ImageIcon(this.getClass().getResource("/dvdalllist.png")).getImage();
	      btnAll.setIcon(new ImageIcon(imgall));
	      btnAll.setForeground(Color.DARK_GRAY);
	      btnAll.setFont(new Font("Calibri Light", Font.PLAIN, 15));
	      btnAll.setBorderPainted(false);
	      btnAll.setBackground(Color.WHITE);
	      btnAll.setBounds(63, 151, 155, 57);
	      btnAdminpage.add(btnAll);
	      btnAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					mainModel.setRowCount(0);
					txtP_num.setText("1");
					mode=2;

					numberOfDVD=getMainSystem().getDVDDB().countAllDVD();
					numberOfDVD=(numberOfDVD/100)+1;
					lblLast_num.setText(""+numberOfDVD);
					ShowAllDVDList();

					//rdbtnYear.setSelected(false);
					//ShowDVDListByType();

				}
			});
		

		JLabel label_3 = new JLabel("Welcome! This is an administrator page.");
		label_3.setForeground(new Color(100, 149, 237));
		label_3.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		label_3.setBounds(977, 34, 246, 18);
		btnAdminpage.add(label_3);

		JButton btnPre = new JButton("");
		btnPre.setBackground(Color.WHITE);
		Image imgpre = new ImageIcon(this.getClass().getResource("/pre.png")).getImage();
		btnPre.setIcon(new ImageIcon(imgpre));
		btnPre.setBorderPainted(false);
		btnPre.setBounds(653, 623, 53, 40);
		btnAdminpage.add(btnPre);

		txtP_num = new JTextField();
		txtP_num.setText("1");
		txtP_num.setBounds(715, 632, 39, 24);
		btnAdminpage.add(txtP_num);
		txtP_num.setColumns(10);



		lblLast_num = new JLabel("1");
		lblLast_num.setBounds(784, 632, 31, 18);
		btnAdminpage.add(lblLast_num);

		JButton btnLast = new JButton("");
		btnLast.setBackground(Color.WHITE);
		Image imglater = new ImageIcon(this.getClass().getResource("/later.png")).getImage();
		btnLast.setIcon(new ImageIcon(imglater));
		btnLast.setBorderPainted(false);
		btnLast.setBounds(814, 623, 53, 40);
		btnAdminpage.add(btnLast);		

		JButton inputButton = new JButton("");
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int numTemp=Integer.parseInt(txtP_num.getText());
				if((numTemp<=numberOfDVD)&&(numTemp>0))
				{
					txtP_num.setText(""+numTemp);
					mainModel.setRowCount(0);

					if(mode==0)
						try {
							ShowDVDListByType();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else if(mode==1)
					{
						ShowDVDInStock();
					}
					else
						ShowAllDVDList();
	
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
		btnAdminpage.add(inputButton);

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
							ShowDVDListByType();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else if(mode==1)
					{
						ShowDVDInStock();
					}
					else
						ShowAllDVDList();
				}
			}
		});

		btnLast.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int numTemp=Integer.parseInt(txtP_num.getText());
				if(numTemp<numberOfDVD)
				{
					numTemp=numTemp+1;
					txtP_num.setText(""+numTemp);

					mainModel.setRowCount(0);

					if(mode==0)
						try {
							ShowDVDListByType();
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else if(mode==1)
					{
						ShowDVDInStock();
					}
					else
						ShowAllDVDList();
				}
			}
		});
		numberOfDVD = mainSystem.getDVDDB().countAllDVD();
		numberOfDVD=(numberOfDVD/100)+1;
		lblLast_num.setText(""+numberOfDVD);
		 ShowAllDVDList();
	}
	public MainSystem getMainSystem()
	{
		return mainSystem;
	}

	public void ShowDVDListByType() throws UnsupportedEncodingException //1-released_date ,  3-title.
	{

		ArrayList<DVD> dvdList=null;
		int temp=Integer.parseInt(txtP_num.getText());

			dvdList = getMainSystem().getUser().getDVDSearch(type, keyword, getMainSystem().getDVDDB(),temp);

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
	
	public void ShowDVDInStock() 
	{

		ArrayList<DVD> dvdList=null;
		int temp=Integer.parseInt(txtP_num.getText());

		Administrator admin=(Administrator)getMainSystem().getUser();
		try {
			dvdList =admin.getDVDListInStock(getMainSystem().getDVDDB(), temp);
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
}
