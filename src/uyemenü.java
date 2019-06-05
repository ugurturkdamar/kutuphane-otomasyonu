import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class uyemenü extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField uyeýd;
	private JTextField uyesifre;
	private JTextField uyead;
	private JTextField uyesoyad;
	private JTextField uyetc;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public void clear() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged(); 	
	}

	public void showTableData(){
	 	clear();
		try{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
		String sql = "SELECT * FROM kisiler";
		pst = (PreparedStatement) con.prepareStatement(sql);
		rs=pst.executeQuery();
		while(rs.next()) {
					Object[] row = {rs.getInt("kisi_tc"),
					rs.getString("kisi_ad"),
					rs.getString("kisi_soyad"),
					rs.getInt("kisi_id"),
					rs.getString("kisi_sifre"),
					
			
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					uyemenü frame = new uyemenü();
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
	public uyemenü() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 653, 320);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 11, 354, 259);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int index=table.getSelectedRow();
				DefaultTableModel model=(DefaultTableModel) table.getModel();
			    uyetc.setText(model.getValueAt(index, 0).toString());
				uyead.setText(model.getValueAt(index, 1).toString());
				uyesoyad.setText(model.getValueAt(index, 2).toString());
				uyeýd.setText(model.getValueAt(index, 3).toString());
				uyesifre.setText(model.getValueAt(index, 4).toString());

				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u00DCye TC", "Ad", "Soyad", "\u00DCye ID", "\u015Eifre"
			}
		));
		scrollPane.setViewportView(table);
		showTableData();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(10, 11, 253, 259);
		contentPane.add(panel);
		
		uyeýd = new JTextField();
		uyeýd.setColumns(10);
		uyeýd.setBounds(70, 120, 86, 20);
		panel.add(uyeýd);
		
		uyesifre = new JTextField();
		uyesifre.setColumns(10);
		uyesifre.setBounds(70, 150, 86, 20);
		panel.add(uyesifre);
		
		uyead = new JTextField();
		uyead.setColumns(10);
		uyead.setBounds(70, 60, 86, 20);
		panel.add(uyead);
		
		uyesoyad = new JTextField();
		uyesoyad.setColumns(10);
		uyesoyad.setBounds(70, 90, 86, 20);
		panel.add(uyesoyad);
		
		JLabel ýdlabel = new JLabel("ID");
		ýdlabel.setBounds(10, 120, 46, 14);
		panel.add(ýdlabel);
		
		JLabel labelsifre = new JLabel("\u015Eifre");
		labelsifre.setBounds(10, 150, 46, 14);
		panel.add(labelsifre);
		
		JLabel adlabel = new JLabel("Ad");
		adlabel.setBounds(10, 60, 46, 14);
		panel.add(adlabel);
		
		JLabel soyadlabel = new JLabel("Soyad");
		soyadlabel.setBounds(10, 90, 46, 14);
		panel.add(soyadlabel);
		
		JButton button = new JButton("Ekle");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ekleme kýsmý
			
				try{
					String sql = "INSERT INTO kisiler"
					+"(kisi_tc,kisi_ad,kisi_soyad,kisi_id,kisi_sifre)"
					+"VALUES (?,?,?,?,?)";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					pst =  (PreparedStatement) con.prepareStatement(sql);
					
					
					pst.setInt(1,Integer.parseInt(uyetc.getText()) );
					pst.setString(2,uyead.getText());
					pst.setString(3,uyesoyad.getText());
					pst.setInt(4,Integer.parseInt(uyeýd.getText()) );
					pst.setString(5,uyesifre.getText());					
					
					pst.executeUpdate();	
					
				
					JOptionPane.showMessageDialog(null, "Baþarýlý");
					 
					}
					catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex); 
					}
				showTableData();
			
			}
		});
		button.setBounds(10, 214, 60, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Sil");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//silme kýsmý

				try{
					String sql= "DELETE from kisiler WHERE kisi_tc =?";		
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					pst = (PreparedStatement) con.prepareStatement(sql);
					pst.setInt(1,Integer.parseInt(uyetc.getText()));
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "delete successfully");
					 
					}
					catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex);
					}
				showTableData();
			
				
				
			}
		});
		button_1.setBounds(85, 214, 60, 23);
		panel.add(button_1);
		
		JButton button_2 = new JButton("G\u00FCncelle");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Güncelleme kýsmý
				
				try{
					 String sql = "UPDATE kisiler SET  kisi_ad=?,kisi_soyad=?,kisi_id=?,kisi_sifre=? WHERE kisi_tc=?";
					 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					 pst = (PreparedStatement) con.prepareStatement(sql);
					 
					
					 pst.setString(1,uyead.getText());
					 pst.setString(2,uyesoyad.getText());
					 pst.setInt(3,Integer.parseInt(uyeýd.getText()));
					 pst.setString(4,uyesifre.getText());
					 pst.setInt(5, Integer.parseInt( uyetc.getText()));
					 pst.executeUpdate();
					 JOptionPane.showMessageDialog(null, "updated successfully");
					  
					 }
					 catch(SQLException | HeadlessException ex){
					 JOptionPane.showMessageDialog(null, ex);
					 }
				showTableData();
			}
		});
		button_2.setBounds(160, 214, 83, 23);
		panel.add(button_2);
		
		JLabel lblyeBilgileri = new JLabel("\u00DCye Bilgileri");
		lblyeBilgileri.setBounds(62, 3, 120, 14);
		panel.add(lblyeBilgileri);
		
		JLabel lblTc = new JLabel("TC");
		lblTc.setBounds(10, 30, 46, 14);
		panel.add(lblTc);
		
		uyetc = new JTextField();
		uyetc.setColumns(10);
		uyetc.setBounds(70, 30, 86, 20);
		panel.add(uyetc);
	}
}
