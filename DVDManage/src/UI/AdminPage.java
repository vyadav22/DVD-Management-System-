//package UI;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//
//import Mainsys.MainSystem;
//
//public class AdminPage extends JFrame {
//
//	private JPanel contentPane;
//	private MainSystem mainSystem;
//	/**
//	 * Launch the application.
//	 */
//
//
//	/**
//	 * Create the frame.
//	 */
//
//	public AdminPage(MainSystem mainSystem) {
//		this.mainSystem=mainSystem;
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 1280, 720);
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(255, 255, 255));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JLabel label = new JLabel(myCalendar.getDate());
//		label.setHorizontalAlignment(SwingConstants.LEFT);
//		label.setForeground(Color.DARK_GRAY);
//		label.setFont(new Font("Adobe Arabic", Font.BOLD, 19));
//		label.setBounds(1075, 49, 173, 30);
//		contentPane.add(label);
//		
//		JLabel label_1 = new JLabel("");
////		label_1.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\Logo.png"));
//		Image imgLogo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
//		label_1.setIcon(new ImageIcon(imgLogo));
//		label_1.setBackground(Color.MAGENTA);
//		label_1.setBounds(14, 12, 130, 105);
//		contentPane.add(label_1);
//		
//		JButton button = new JButton("");
//		button.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
////				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				MainScreen adminpage = new MainScreen(getMainSystem());
//				adminpage.frame.setVisible(true);
//				//setVisible(false);
//				dispose();
//			}
//		});
////		button.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\title.png"));
//		Image imgTitle = new ImageIcon(this.getClass().getResource("/title.png")).getImage();
//		button.setIcon(new ImageIcon(imgTitle));
//		button.setBorderPainted(false);
//		button.setBackground(Color.WHITE);
//		button.setBounds(133, 48, 480, 43);
//		contentPane.add(button);
//		
//		JLabel label_2 = new JLabel("");
////		label_2.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\titlecc.png"));
//		Image imgTitlecc = new ImageIcon(this.getClass().getResource("/titlecc.png")).getImage();
//		label_2.setIcon(new ImageIcon(imgTitlecc));
//		label_2.setBounds(627, 63, 240, 28);
//		contentPane.add(label_2);
//		
//		JLabel lbladminname = new JLabel("Welcome! This is an administrator page.");
//		lbladminname.setForeground(new Color(100, 149, 237));
//		lbladminname.setFont(new Font("Calibri Light", Font.PLAIN, 15));
//		lbladminname.setBounds(977, 34, 246, 18);
//		contentPane.add(lbladminname);
//		
//		JLabel lbldeco = new JLabel("");
////		lbldeco.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\deco.png"));
//		Image imgdeco = new ImageIcon(this.getClass().getResource("/deco.png")).getImage();
//		lbldeco.setIcon(new ImageIcon(imgdeco));
//		lbldeco.setBounds(79, 145, 124, 142);
//		contentPane.add(lbldeco);
////		lbldvdlist.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\DVDlist.png"));
//		Image imgdvdborder = new ImageIcon(this.getClass().getResource("/DVDlist.png")).getImage();
////		lblcustom.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\customer.png"));
//		Image imgcustomborder = new ImageIcon(this.getClass().getResource("/customer.png")).getImage();
////		label_3.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\deco2.png"));
//		Image imgdeco2 = new ImageIcon(this.getClass().getResource("/deco2.png")).getImage();
//		
//		JLabel lblManagement = new JLabel("Management");
//		lblManagement.setForeground(Color.DARK_GRAY);
//		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
//		lblManagement.setFont(new Font("Arial Black", Font.PLAIN, 18));
//		lblManagement.setBounds(47, 281, 149, 38);
//		contentPane.add(lblManagement);
//		
//		JButton DVDbtn = new JButton("");
//		DVDbtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				DVDListEdit dvdpage = new DVDListEdit(getMainSystem());
//				dispose();
//				dvdpage.setVisible(true);
//			}
//		});
//		DVDbtn.setBorderPainted(false);
//		Image imgdvdbtn = new ImageIcon(this.getClass().getResource("/dvdbtn.png")).getImage();
//		DVDbtn.setIcon(new ImageIcon(imgdvdbtn));
//		DVDbtn.setBackground(Color.WHITE);
//		DVDbtn.setBounds(343, 297, 280, 76);
//		contentPane.add(DVDbtn);
//		
//		JButton customerbtn = new JButton("");
//		customerbtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				CustomerEdit customerpage = new CustomerEdit(getMainSystem());
//				dispose();
//				customerpage.setVisible(true);
//			}
//		});
//		Image imgcustomerbtn = new ImageIcon(this.getClass().getResource("/customerbtn.png")).getImage();
//		customerbtn.setIcon(new ImageIcon(imgcustomerbtn));
//		customerbtn.setBorderPainted(false);
//		customerbtn.setBackground(Color.WHITE);
//		customerbtn.setBounds(730, 297, 280, 76);
//		contentPane.add(customerbtn);
//		
//	}
//	
//	
//	public MainSystem getMainSystem()
//	{
//		return mainSystem;
//	}
//}
package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Mainsys.MainSystem;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private MainSystem mainSystem;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	
	public MainSystem getMainSystem()
	{
		return mainSystem;
	}
	public AdminPage(MainSystem mainSystem) {
		this.mainSystem=mainSystem;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel(myCalendar.getDate());
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Adobe Arabic", Font.BOLD, 19));
		label.setBounds(1075, 49, 173, 30);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
//		label_1.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\Logo.png"));
		Image imgLogo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
		label_1.setIcon(new ImageIcon(imgLogo));
		label_1.setBackground(Color.MAGENTA);
		label_1.setBounds(14, 12, 130, 105);
		contentPane.add(label_1);
		
		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MainScreen mainpage = new MainScreen(mainSystem);
				mainpage.frame.setVisible(true);
				dispose();
			}
		});
//		button.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\title.png"));
		Image imgTitle = new ImageIcon(this.getClass().getResource("/title.png")).getImage();
		button.setIcon(new ImageIcon(imgTitle));
		button.setBorderPainted(false);
		button.setBackground(Color.WHITE);
		button.setBounds(133, 48, 480, 43);
		contentPane.add(button);
		
		JLabel label_2 = new JLabel("");
//		label_2.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\img\\titlecc.png"));
		Image imgTitlecc = new ImageIcon(this.getClass().getResource("/titlecc.png")).getImage();
		label_2.setIcon(new ImageIcon(imgTitlecc));
		label_2.setBounds(627, 63, 240, 28);
		contentPane.add(label_2);
		
		JLabel lbladminname = new JLabel("Welcome! This is an administrator page.");
		lbladminname.setForeground(new Color(100, 149, 237));
		lbladminname.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lbladminname.setBounds(977, 34, 246, 18);
		contentPane.add(lbladminname);
		
		JLabel lbldeco = new JLabel("");
//		lbldeco.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\deco.png"));
		Image imgdeco = new ImageIcon(this.getClass().getResource("/deco.png")).getImage();
		lbldeco.setIcon(new ImageIcon(imgdeco));
		lbldeco.setBounds(185, 129, 124, 142);
		contentPane.add(lbldeco);
//		lbldvdlist.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\DVDlist.png"));
		Image imgdvdborder = new ImageIcon(this.getClass().getResource("/DVDlist.png")).getImage();
//		lblcustom.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\customer.png"));
		Image imgcustomborder = new ImageIcon(this.getClass().getResource("/customer.png")).getImage();
//		label_3.setIcon(new ImageIcon("C:\\Users\\\uC784\uD55C\uC194\\Desktop\\GC\\deco2.png"));
		Image imgdeco2 = new ImageIcon(this.getClass().getResource("/deco2.png")).getImage();
		
		JButton DVDbtn = new JButton("");
		DVDbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DVDListEdit dvdpage = new DVDListEdit(mainSystem);
				dispose();
				dvdpage.setVisible(true);
			}
		});
		DVDbtn.setBorderPainted(false);
		Image imgdvdbtn = new ImageIcon(this.getClass().getResource("/dvdmgm.png")).getImage();
		DVDbtn.setIcon(new ImageIcon(imgdvdbtn));
		DVDbtn.setBackground(Color.WHITE);
		DVDbtn.setBounds(154, 320, 289, 87);
		contentPane.add(DVDbtn);
		
		JButton customerbtn = new JButton("");
		customerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerEdit customerpage = new CustomerEdit(getMainSystem());
				dispose();
				customerpage.setVisible(true);
			}
		});
		Image imgcustomerbtn = new ImageIcon(this.getClass().getResource("/custommgm.png")).getImage();
		customerbtn.setIcon(new ImageIcon(imgcustomerbtn));
		customerbtn.setBorderPainted(false);
		customerbtn.setBackground(Color.WHITE);
		customerbtn.setBounds(834, 320, 289, 87);
		contentPane.add(customerbtn);
		
		JButton btnRent = new JButton("");
		btnRent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RentPage rentpage = new RentPage(getMainSystem());
				dispose();
				rentpage.setVisible(true);
			}
		});
		Image imgrent = new ImageIcon(this.getClass().getResource("/rentmgm.png")).getImage();
		btnRent.setIcon(new ImageIcon(imgrent));
		btnRent.setBorderPainted(false);
		btnRent.setBackground(Color.WHITE);
		btnRent.setBounds(494, 320, 289, 87);
		contentPane.add(btnRent);
		
		JLabel lblBorder = new JLabel("");
		Image imgborder = new ImageIcon(this.getClass().getResource("/border.png")).getImage();
		lblBorder.setIcon(new ImageIcon(imgborder));
		lblBorder.setBounds(109, 227, 1058, 285);
		contentPane.add(lblBorder);
		
		JLabel deco = new JLabel("");
		Image imgaddeco = new ImageIcon(this.getClass().getResource("/addeco.png")).getImage();
		deco.setIcon(new ImageIcon(imgaddeco));
		deco.setBounds(889, 540, 334, 129);
		contentPane.add(deco);
		
		JButton btnHomebtn = new JButton("");
		btnHomebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainScreen mainpage = new MainScreen(mainSystem);
				mainpage.frame.setVisible(true);
				dispose();
			}
		});
		Image imghome = new ImageIcon(this.getClass().getResource("/home2.png")).getImage();
		btnHomebtn.setIcon(new ImageIcon(imghome));
		btnHomebtn.setBorderPainted(false);
		btnHomebtn.setBackground(Color.WHITE);
		btnHomebtn.setBounds(24, 524, 100, 137);
		contentPane.add(btnHomebtn);
		
	}
}
