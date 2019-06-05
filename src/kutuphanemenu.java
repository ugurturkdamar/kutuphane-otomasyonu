import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class kutuphanemenu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField kutýd;
	private JTextField kutsifre;
	private JTextField kutad;
	private JTextField kutsoyad;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	/**
	 * Launch the application.
	 */
	
	public void clear() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged(); 	
	}

	public void showTableData(){
	 	clear();
		try{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
		String sql = "SELECT * FROM kutuphaneciler";
		pst = (PreparedStatement) con.prepareStatement(sql);
		rs=pst.executeQuery();
		while(rs.next()) {
					Object[] row = {rs.getInt("kutid"),
					rs.getString("kutsifre"),
					rs.getString("kutad"),
					rs.getString("kutsoyad"),
			
			};
			DefaultTableModel model=(DefaultTableModel) table.getModel();	
			model.addRow(row);	
		}
		System.out.println(rs);
		}
		catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex); 
		}	 
		}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kutuphanemenu frame = new kutuphanemenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public kutuphanemenu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 694, 275);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(263, 11, 405, 219);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index=table.getSelectedRow();
				DefaultTableModel model=(DefaultTableModel) table.getModel();
			    kutýd.setText(model.getValueAt(index, 0).toString());
				kutsifre.setText(model.getValueAt(index, 1).toString());
				kutad.setText(model.getValueAt(index, 2).toString());
				kutsoyad.setText(model.getValueAt(index, 3).toString());
			
				//týkladý
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Sifre", "Ad", "Soyad"
			}
		));
		scrollPane.setViewportView(table);
		
		showTableData();
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(10, 11, 243, 219);
		contentPane.add(panel);
		panel.setLayout(null);
		kutýd = new JTextField();
		kutýd.setBounds(70, 28, 86, 20);
		panel.add(kutýd);
		kutýd.setColumns(10);
		
		kutsifre = new JTextField();
		kutsifre.setBounds(70, 58, 86, 20);
		panel.add(kutsifre);
		kutsifre.setColumns(10);
		
		kutad = new JTextField();
		kutad.setBounds(70, 88, 86, 20);
		panel.add(kutad);
		kutad.setColumns(10);
		
		kutsoyad = new JTextField();
		kutsoyad.setBounds(70, 118, 86, 20);
		panel.add(kutsoyad);
		kutsoyad.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 28, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblifre = new JLabel("\u015Eifre");
		lblifre.setBounds(10, 58, 46, 14);
		panel.add(lblifre);
		
		JLabel lblAd = new JLabel("Ad");
		lblAd.setBounds(10, 88, 46, 14);
		panel.add(lblAd);
		
		JLabel lblSoyad = new JLabel("Soyad");
		lblSoyad.setBounds(10, 118, 46, 14);
		panel.add(lblSoyad);
		
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.setBounds(10, 172, 65, 23);
		panel.add(btnNewButton);
		
		JButton btnSil = new JButton("Sil");
		btnSil.setBounds(78, 172, 65, 23);
		panel.add(btnSil);
		
		JButton btnGncelle = new JButton("G\u00FCncelle");
		btnGncelle.setBounds(150, 171, 83, 23);
		panel.add(btnGncelle);
		
		JLabel lblKtphaneciBilgileri = new JLabel("K\u00FCt\u00FCphaneci Bilgileri");
		lblKtphaneciBilgileri.setBounds(62, 3, 120, 14);
		panel.add(lblKtphaneciBilgileri);
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try{
					 String sql = "UPDATE kutuphaneciler SET  kutsifre=?,kutad=?,kutsoyad=? WHERE kutid=?";
					 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					 pst = (PreparedStatement) con.prepareStatement(sql);
					 
					
					 pst.setString(1,kutsifre.getText());
					 pst.setString(2,kutad.getText());
					 pst.setString(3,kutsoyad.getText());
					 pst.setInt(4,Integer.parseInt(kutýd.getText()));
					 pst.executeUpdate();
					 JOptionPane.showMessageDialog(null, "updated successfully");
					  
					 }
					 catch(SQLException | HeadlessException ex){
					 JOptionPane.showMessageDialog(null, ex);
					 }
				showTableData();
				
			}
		});
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try{
					String sql= "DELETE from kutuphaneciler WHERE kutid =?";		
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					pst = (PreparedStatement) con.prepareStatement(sql);
					pst.setInt(1,Integer.parseInt(kutýd.getText()));
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "delete successfully");
					 
					}
					catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex);
					}
				showTableData();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try{
					String sql = "INSERT INTO kutuphaneciler"
					+"(kutid,kutsifre,kutad,kutsoyad)"
					+"VALUES (?,?,?,?)";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					pst =  (PreparedStatement) con.prepareStatement(sql);
					
					
					pst.setInt(1,Integer.parseInt( kutýd.getText()) );
					pst.setString(2,kutsifre.getText());
					pst.setString(3,kutad.getText());
					pst.setString(4,kutsoyad.getText());					
					
					pst.executeUpdate();	
					
				
					JOptionPane.showMessageDialog(null, "Baþarýlý");
					 
					}
					catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex); 
					}
				showTableData();
			}
				
				
			
		});
	}
}
