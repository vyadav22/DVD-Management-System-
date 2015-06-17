package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Mainsys.MainSystem;
import User.Administrator;


public class AddDVD extends JFrame {

	private JPanel contentPane;
	private JTextField txtPnum;
	private JTextField txtAddress;
	private JTextField textField;
	private JLabel lblPrice;
	private JLabel lblRating;
	private JComboBox CboxRating;
	private JLabel lblGenre;
	private JComboBox CBoxGenre;
	private JLabel lblDate;
	private JTextField textField_1;
	private MainSystem mainSystem;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddDVD frame = new AddDVD();
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
	public AddDVD(MainSystem mainSystem) {
		this.mainSystem=mainSystem;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 331);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblTitle.setBounds(34, 32, 59, 28);
		contentPane.add(lblTitle);
		
		txtPnum = new JTextField();
		txtPnum.setColumns(10);
		txtPnum.setBounds(80, 32, 300, 28);
		contentPane.add(txtPnum);
		
		JLabel lblStudio = new JLabel("Studio");
		lblStudio.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblStudio.setBounds(34, 75, 59, 28);
		contentPane.add(lblStudio);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(90, 75, 290, 28);
		contentPane.add(txtAddress);
		
		JButton btnAddDVD = new JButton("Add Now");
		btnAddDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrator admin=(Administrator)getMainSystem().getUser();
				try{
				double temp= Double.parseDouble(textField.getText());
				admin.insertDVD(txtPnum.getText(), txtAddress.getText(), temp, CboxRating.getSelectedItem().toString(), CBoxGenre.getSelectedItem().toString(), textField_1.getText(), getMainSystem().getDVDDB());
				JOptionPane.showMessageDialog(null, "Add Successfully");
				dispose();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Price type is wrong.\n ex) 2,   3.4,   2.0,   10.4 ");
				}
			}
		});
		btnAddDVD.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnAddDVD.setBounds(283, 236, 97, 27);
		contentPane.add(btnAddDVD);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(90, 156, 109, 28);
		contentPane.add(textField);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblPrice.setBounds(34, 155, 59, 28);
		contentPane.add(lblPrice);
		
		lblRating = new JLabel("Rating");
		lblRating.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblRating.setBounds(213, 156, 59, 28);
		contentPane.add(lblRating);
		
		CboxRating = new JComboBox();
		CboxRating.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		CboxRating.setModel(new DefaultComboBoxModel(new String[] {"[NR]", "c", "G", "GA", "MA13", "MA15", "MA17", "NC-17", "NR", "PG", "PG-13", "R", "R/UR", "UNK", "UR", "UR/R", "VAR", "X"}));
		CboxRating.setBounds(265, 156, 116, 28);
		contentPane.add(CboxRating);
		
		lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblGenre.setBounds(34, 196, 59, 28);
		contentPane.add(lblGenre);
		
		CBoxGenre = new JComboBox();
		CBoxGenre.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		CBoxGenre.setModel(new DefaultComboBoxModel(new String[] {"Action", "Adventure", "Action/Adventure", "Action/Comedy", "Animation", "Anime", "Comedy", "Comedy/Drama", "Dance/Ballet", "Documentary", "Drama", "Exercise", "Family", "Fantasy", "Foreign", "Horror", "Late Night", "Music", "Musical", "Mystery", "Mystery/Suspens", "Opera", "SciFi", "Silent", "Software", "Special Interes", "Sports", "Suspens", "Suspense/Thrill", "Thriller", "TV Classics", "VAR", "War", "Western"}));
		CBoxGenre.setBounds(100, 196, 280, 28);
		contentPane.add(CBoxGenre);
		
		lblDate = new JLabel("Released Date");
		lblDate.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblDate.setBounds(34, 115, 116, 28);
		contentPane.add(lblDate);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(149, 116, 231, 28);
		contentPane.add(textField_1);
	}

	public MainSystem getMainSystem()
	{
		return mainSystem;
	}
}
