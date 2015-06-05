package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Mainsys.MainSystem;
import Mainsys.Rent;
import User.Customer;


public class OrdiCustom extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;
	private DefaultTableModel mainModel;
	private JFrame frame;
	private MainSystem mainSystem;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OrdiCustom frame = new OrdiCustom();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public MainSystem getMainSystem()
	{
		return mainSystem;
	}
	/**
	 * Create the frame.
	 */
	public OrdiCustom(MainSystem mainSystem) {
		this.mainSystem=mainSystem;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDate = new JLabel(myCalendar.getDate());
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setForeground(Color.DARK_GRAY);
		lblDate.setFont(new Font("Adobe Arabic", Font.BOLD, 19));
		lblDate.setBounds(1089, 48, 173, 30);
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
		
		JScrollPane CustomList = new JScrollPane();
		CustomList.setBounds(297, 198, 881, 402);
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

		CustomList.setViewportView(table);
		try {
			ShowMyRentalList();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Image imgdeco2 = new ImageIcon(this.getClass().getResource("/deco2.png")).getImage();
		
		JLabel lblWelcome = new JLabel("Welcome, admin");
		lblWelcome.setForeground(new Color(51, 153, 204));
		lblWelcome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWelcome.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblWelcome.setBounds(977, 36, 240, 18);
		contentPane.add(lblWelcome);
		
		
		JButton btnreturn = new JButton("");
		btnreturn.setBorderPainted(false);
		btnreturn.setBackground(Color.WHITE);
		btnreturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		btnreturn.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		Image btreturn = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
		btnreturn.setIcon(new ImageIcon(btreturn));
		btnreturn.setBounds(69, 317, 143, 69);
		contentPane.add(btnreturn);
		
		JButton btnMyInfo = new JButton("");
		btnMyInfo.setBorderPainted(false);
		btnMyInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomMyInfo myinfo = new CustomMyInfo(
						mainSystem, (Customer)mainSystem.getUser());
				myinfo.setVisible(true);
			}
		});
		Image btninfo = new ImageIcon(this.getClass().getResource("/myinfo.png")).getImage();
		btnMyInfo.setIcon(new ImageIcon(btninfo));
		btnMyInfo.setBackground(Color.WHITE);
		btnMyInfo.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnMyInfo.setBounds(69, 224, 143, 69);
		contentPane.add(btnMyInfo);
		
		JButton btnHome = new JButton("");
		btnHome.setBorderPainted(false);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainScreen mainpage = new MainScreen(mainSystem);
				mainpage.frame.setVisible(true);
				dispose();
			}
		});
		btnHome.setBackground(Color.WHITE);
		Image btnhome = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
		btnHome.setIcon(new ImageIcon(btnhome));
		btnHome.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnHome.setBounds(69, 411, 143, 69);
		contentPane.add(btnHome);
	}
	
	public void ShowMyRentalList() throws UnsupportedEncodingException //1-released_date ,  3-title.
	{

		ArrayList<Rent> rentList=null;
	//	int temp=Integer.parseInt(txtP_num.getText());

//		Administrator admin=(Administrator)getMainSystem().getUser();
		Customer customer=(Customer)mainSystem.getUser();
		rentList = customer.getMyRentDVDList(mainSystem.getRentDB());
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