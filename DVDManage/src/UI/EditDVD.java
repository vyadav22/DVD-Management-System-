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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Mainsys.DVD;
import Mainsys.MainSystem;
import User.Administrator;


public class EditDVD extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
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
	private DVD originDVD;
	private DefaultTableModel mainModel;
	private JTable table;
	
	
	public MainSystem getMainSystem()
	{
		
		return mainSystem;
	}

	/**
	 * Create the frame.
	 */
	public EditDVD(MainSystem mainSystem,DefaultTableModel mainModel,JTable table) 
	{
		this.mainSystem=mainSystem;
		this.mainModel=mainModel;
		this.table=table;
		
		String getID=mainModel.getValueAt(table.getSelectedRow(), 0).toString();
		int d_id=Integer.parseInt(getID);
		String getPrice=mainModel.getValueAt(table.getSelectedRow(), 3).toString();
		double tempPrice=Double.parseDouble(getPrice);
		
		
		originDVD=new DVD(d_id,mainModel.getValueAt(table.getSelectedRow(), 1).toString(),
				mainModel.getValueAt(table.getSelectedRow(), 2).toString(),tempPrice,mainModel.getValueAt(table.getSelectedRow(), 4).toString(),
				mainModel.getValueAt(table.getSelectedRow(), 5).toString(),mainModel.getValueAt(table.getSelectedRow(), 6).toString());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 381);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDVDnum = new JLabel("DVD Number :");
		lblDVDnum.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblDVDnum.setBounds(38, 33, 116, 28);
		contentPane.add(lblDVDnum);
		
		JLabel dvdID= new JLabel(""+originDVD.getId());
		dvdID.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		dvdID.setBounds(153, 33, 231, 28);
		contentPane.add(dvdID);
		
//		txtName = new JTextField();
//		txtName.setFont(new Font("Calibri Light", Font.PLAIN, 15));
//		txtName.setForeground(SystemColor.textInactiveText);
//		txtName.setText("AAA00001");
//		// ��ȣ �ڵ� ���� �ǵ��� ���߿� ������ �ٲ��ָ� �ɵ�!
//		txtName.setBounds(153, 33, 231, 28);
//		contentPane.add(txtName);
//		txtName.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblTitle.setBounds(38, 76, 59, 28);
		contentPane.add(lblTitle);
		
		txtPnum = new JTextField(originDVD.getTitle());
		txtPnum.setColumns(10);
		txtPnum.setBounds(84, 76, 300, 28);
		contentPane.add(txtPnum);
		
		JLabel lblStudio = new JLabel("Studio");
		lblStudio.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblStudio.setBounds(38, 119, 59, 28);
		contentPane.add(lblStudio);
		
		txtAddress = new JTextField(originDVD.getStudio());
		txtAddress.setColumns(10);
		txtAddress.setBounds(94, 119, 290, 28);
		contentPane.add(txtAddress);
		
		
		
		
		JButton btnEditDVD = new JButton("Edit");
		btnEditDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Administrator admin=(Administrator)mainSystem.getUser(); //1-release_date,2-genre,3-title,4-price,5-rating,6-studio
				String getPrice=textField.getText();
				
				double tempPrice=0;
				boolean isChanged=false;
				try{
					tempPrice=Double.parseDouble(getPrice);
					if(originDVD.getPrice()!=tempPrice) //price
					{
						admin.modifyDVD(originDVD.getId(), 4, ""+tempPrice, mainSystem.getDVDDB());
						mainModel.setValueAt(getPrice, table.getSelectedRow(), 3);
						
						isChanged=true;
					}
						
					if(!(originDVD.getTitle().equals(txtPnum.getText()))) //title
					{
						admin.modifyDVD(originDVD.getId(), 3, txtPnum.getText(), mainSystem.getDVDDB());
						//System.out.println("info "+originDVD.getId()+" "+originDVD.getTitle());
						isChanged=true;
						mainModel.setValueAt(txtPnum.getText(), table.getSelectedRow(), 1);
					}
						
					if(!(originDVD.getStudio().equals(txtAddress.getText()))) //studio
					{
						admin.modifyDVD(originDVD.getId(), 6, txtAddress.getText(), mainSystem.getDVDDB());
						isChanged=true;
						mainModel.setValueAt(txtAddress.getText(), table.getSelectedRow(), 2);
					}
						
					if(!(originDVD.getRating().equals(CboxRating.getSelectedItem().toString()))) //rating
					{
						admin.modifyDVD(originDVD.getId(), 5, CboxRating.getSelectedItem().toString(), mainSystem.getDVDDB());
						isChanged=true;
						mainModel.setValueAt(CboxRating.getSelectedItem().toString(), table.getSelectedRow(), 4);
					}
						
					if(!(originDVD.getGenre().equals(CBoxGenre.getSelectedItem().toString()))) //genre
					{
						admin.modifyDVD(originDVD.getId(), 2, CBoxGenre.getSelectedItem().toString(), mainSystem.getDVDDB());
						isChanged=true;
						mainModel.setValueAt(CBoxGenre.getSelectedItem().toString(), table.getSelectedRow(), 5);
					}
						
					if(!(originDVD.getDate().equals(textField_1.getText()))) //release_date
					{
						admin.modifyDVD(originDVD.getId(), 1, textField_1.getText(), mainSystem.getDVDDB());
						isChanged=true;
						mainModel.setValueAt(textField_1.getText(), table.getSelectedRow(), 6);
					}
					if(isChanged)
						JOptionPane.showMessageDialog(null, "Edit Successfully");
					dispose();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Price type is wrong.\n ex) 2,   3.4,   2.0,   10.4 ");
				}
				
				
			}
		});
		
		
		
		
		btnEditDVD.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnEditDVD.setBounds(287, 280, 97, 27);
		contentPane.add(btnEditDVD);
		//System.out.println("price:"+originDVD.getPrice());
		textField = new JTextField(""+originDVD.getPrice());
		textField.setColumns(10);
		textField.setBounds(94, 200, 109, 28);
		contentPane.add(textField);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblPrice.setBounds(38, 199, 59, 28);
		contentPane.add(lblPrice);
		
		lblRating = new JLabel("Rating");
		lblRating.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblRating.setBounds(217, 200, 59, 28);
		contentPane.add(lblRating);
		
		CboxRating = new JComboBox();
		CboxRating.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		CboxRating.setModel(new DefaultComboBoxModel(new String[] {"[NR]", "c", "G", "GA", "MA13", "MA15", "MA17", "NC-17", "NR", "PG", "PG-13", "R", "R/UR", "UNK", "UR", "UR/R", "VAR", "X"}));
		CboxRating.setBounds(269, 200, 116, 28);
		CboxRating.setSelectedItem(originDVD.getRating());
		contentPane.add(CboxRating);
		
		lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblGenre.setBounds(38, 240, 59, 28);
		contentPane.add(lblGenre);
		
		CBoxGenre = new JComboBox();
		CBoxGenre.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		CBoxGenre.setModel(new DefaultComboBoxModel(new String[] {"Action", "Adventure", "Action/Adventure", "Action/Comedy", "Animation", "Anime", "Comedy", "Comedy/Drama", "Dance/Ballet", "Documentary", "Drama", "Exercise", "Family", "Fantasy", "Foreign", "Horror", "Late Night", "Music", "Musical", "Mystery", "Mystery/Suspense", "Opera", "SciFi", "Silent", "Software", "Special Interes", "Sports", "Suspense", "Suspense/Thrill", "Thriller", "TV Classics", "VAR", "War", "Western"}));
		CBoxGenre.setBounds(104, 240, 280, 28);
		CBoxGenre.setSelectedItem(originDVD.getGenre());
		contentPane.add(CBoxGenre);
		
		lblDate = new JLabel("Released Date");
		lblDate.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		lblDate.setBounds(38, 159, 116, 28);
		contentPane.add(lblDate);
		
		textField_1 = new JTextField(originDVD.getDate());
		textField_1.setColumns(10);
		textField_1.setBounds(153, 160, 231, 28);
		contentPane.add(textField_1);
	}

}
