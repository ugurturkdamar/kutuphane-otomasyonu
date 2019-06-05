import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.Choice;
import java.awt.Component;

import javax.swing.JMenu;
import java.awt.Button;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Checkbox;
import java.awt.List;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxEditor;
import javax.swing.ListSelectionModel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Panel;
import javax.swing.JCheckBox;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

public class kitap extends JFrame {

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ResultSet rs2=null;
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtAd;
	private JTextField txtAciklama;
	private JTextField txtBasimYili;
	private JTextField kitapid;
	private JTextArea yazaralan;
	private JTextArea katarea;
	private JComboBox cbrafta;
	private JComboBox cbyazar;
	private JComboBox cbkat;
	private JTextField kisiid;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					kitap frame = new kitap();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 * @return 
	 * @return 
	 */
	
	public void yazaridbul(int kitapid) {

		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			String sql = "SELECT * FROM kitapveyazarid";
			pst = (PreparedStatement) con.prepareStatement(sql);
			rs=pst.executeQuery();
			
			
			while(rs.next()) {
				if(rs.getInt("kitapid")==kitapid)
				{ 
					yazaralan.setText(rs.getInt("yazarid")+	" " +yazarbilgicek(rs.getInt("yazarid")));
					
				    
			//	YazarBilgi=	YazarBilgi.setText(rs.getInt("yazarid")+ yazarbilgi(rs.getInt("yazarid"))+rs.getString("yazar_bio"));		
				}		
			}
			
		}
			catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex);	 
			}	
	}
	
	
	public String yazarbilgicek(int yazarid)
	{
		String yazarad=null;
		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			String sql = "SELECT * FROM yazarlar";
			pst = (PreparedStatement) con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
			int flag=rs.getInt("yazar_id");
				if(flag==yazarid)
				{   
							
					yazarad =rs.getString("yazar_ad")+" \n"+rs.getString("yazar_bio");	
					cbyazar.setSelectedItem(rs.getInt("yazar_id")+" "+rs.getString("yazar_ad"));
				}
			}}
			catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			}
		return yazarad;
	}
	
	public void katidbul(int kitapid) {
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			String sql = "SELECT * FROM kitapvekatid";
			pst = (PreparedStatement) con.prepareStatement(sql);
			rs=pst.executeQuery();
			
			
			while(rs.next()) {
				if(rs.getInt("kitap_id")==kitapid)
				{ 
					katarea.setText( rs.getInt("kat_id")+" "+katbilgicek(rs.getInt("kat_id")));
					//yazaralan.setText(rs.getInt("yazarid")+	" " +yazarbilgicek(rs.getInt("yazarid")));
					
				    
			//	YazarBilgi=	YazarBilgi.setText(rs.getInt("yazarid")+ yazarbilgi(rs.getInt("yazarid"))+rs.getString("yazar_bio"));		
				}		
			}
			
		}
			catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex);	 
			}	
		
	}

	public String katbilgicek(int katid) {
		String katad=null;
		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			String sql = "SELECT * FROM kategoriler";
			pst = (PreparedStatement) con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
			
				if(rs.getInt("kat_id")==katid)
				{   
					katad =rs.getString("kat_ad")+" \n"+rs.getString("kat_genelaciklama");	
					cbkat.setSelectedItem(rs.getInt("kat_id")+" "+rs.getString("kat_ad"));
					
					
				}
			}}
			catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			}
		return katad;
		
	}
	

	
	public void doldur(JComboBox names) {
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			String sql = "SELECT * FROM yazarlar";
			pst = (PreparedStatement) con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
			
				names.addItem( rs.getInt("yazar_id")+" "+rs.getString("yazar_ad"));
				//names.addItem(rs.getString("yazar_ad"));
			};
			}
			catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			}
	} 
	
	public void doldurkat(JComboBox namess) {
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			String sql = "SELECT * FROM kategoriler";
			pst = (PreparedStatement) con.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
			
				namess.addItem(rs.getInt("kat_id")+" "+rs.getString("kat_ad"));
			};
			}
			catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			}
	} 
	
	public void clear() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged(); 	
	}

	public void showTableData(){
	 	clear();
		try{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
		String sql = "SELECT * FROM kitap";
		pst = (PreparedStatement) con.prepareStatement(sql);
		rs=pst.executeQuery();
		String yazar = null;
		String kat=null;
		while(rs.next()) {
					Object[] row = {rs.getInt("kitap_id"),
					rs.getString("kitap_ad"),
					rs.getString("kitap_aciklama"),
					rs.getInt("kitap_basimyili"),
					rs.getString("kitap_rafta"),
			
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
	
	
	public void yazaridgir(int kitapid,int yazarid) {
		try{
			String sql = "INSERT INTO kitapveyazarid"
			+"(kitapid,yazarid)"
			+"VALUES (?,?)";
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			pst = (PreparedStatement) con.prepareStatement(sql);
				
			pst.setInt(1, kitapid);
			pst.setInt(2,yazarid);
			pst.executeUpdate();	
			
		
			JOptionPane.showMessageDialog(null, "Yazar Eklendi");
			 
			}
			catch(SQLException | HeadlessException ex){
			JOptionPane.showMessageDialog(null, ex); 
			}
		
	}
	
	
	public void kategoriidgir(int kitapid,int kategoriid) {
		try{
			String sql = "INSERT INTO kitapvekatid"
			+"(kitap_id,kat_id)"
			+"VALUES (?,?)";
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			pst = (PreparedStatement) con.prepareStatement(sql);
				
			pst.setInt(1, kitapid);
			pst.setInt(2,kategoriid);
			pst.executeUpdate();	
			
		
			JOptionPane.showMessageDialog(null, "Kategori Eklendi");
			 
			}
			catch(SQLException | HeadlessException ex){
			JOptionPane.showMessageDialog(null, ex); 
			}
		
		
	}
	
	public void yazarguncelle(int kitapid,int yazarid) {
		try{
			 String sql = "UPDATE kitapveyazarid SET  yazarid=? WHERE kitapid=?";

			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			pst = (PreparedStatement) con.prepareStatement(sql);
				
			pst.setInt(1, yazarid);
			pst.setInt(2,kitapid);
			pst.executeUpdate();	
			
		
			JOptionPane.showMessageDialog(null, "Yazar bilgisi güncellendi.");
			 
			}
			catch(SQLException | HeadlessException ex){
			JOptionPane.showMessageDialog(null, ex); 
			}
	
	}
	
	public void kategorigüncelle(int kitapid,int katid) {
		try{
			 String sql = "UPDATE kitapvekatid SET  kat_id=? WHERE kitap_id=?";

			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
			pst = (PreparedStatement) con.prepareStatement(sql);
				
			pst.setInt(1, katid);
			pst.setInt(2,kitapid);
			pst.executeUpdate();	
			
		
			JOptionPane.showMessageDialog(null, "Kategori bilgisi güncellendi.");
			 
			}
			catch(SQLException | HeadlessException ex){
			JOptionPane.showMessageDialog(null, ex); 
			}
	
	}
	
	
	
	public kitap() {
		setUndecorated(true);
		


		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 480);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlDkShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setBackground(new Color(240,240,240));
		tabbedPane.setBounds(325, 11, 393, 458);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			
		
		
			
		});
		tabbedPane.addTab("Kitaplar", null, scrollPane, null);
		
		table = new JTable();
		table.setSelectionBackground(new Color(135, 206, 250));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ýndex kaçýncý satýrý seçtiðimizi anlamamýz için parametre olacak
				int index=table.getSelectedRow();
				DefaultTableModel model=(DefaultTableModel) table.getModel();
			    kitapid.setText(model.getValueAt(index, 0).toString());
				txtAd.setText(model.getValueAt(index, 1).toString());
				txtAciklama.setText(model.getValueAt(index, 2).toString());
				txtBasimYili.setText(model.getValueAt(index, 3).toString());
			//	txtRafta.setText(model.getValueAt(index, 4).toString());
				yazaridbul(Integer.parseInt( kitapid.getText()));
				katidbul(Integer.parseInt(kitapid.getText()));
				cbrafta.setSelectedItem(model.getValueAt(index, 4).toString());
				
			}
			
		});
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Ad", "A\u00E7\u0131klama", "Bas\u0131m Y\u0131l\u0131", "Rafta"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		showTableData();
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBox.background"));
		panel.setBounds(10, 24, 299, 445);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Rafta");
		lblNewLabel_4.setBounds(new Rectangle(25, 0, 0, 0));
		lblNewLabel_4.setBounds(11, 148, 73, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Bas\u0131m Y\u0131l\u0131");
		lblNewLabel_3.setBounds(new Rectangle(25, 0, 0, 0));
		lblNewLabel_3.setBounds(11, 118, 73, 14);
		panel.add(lblNewLabel_3);
		

		cbyazar = new JComboBox();
		cbyazar.setBounds(96, 178, 150, 22);
		panel.add(cbyazar);
		doldur(cbyazar);
		
		 cbrafta = new JComboBox();
		cbrafta.setModel(new DefaultComboBoxModel(new String[] {"E", "H"}));
		cbrafta.setBounds(96, 148, 85, 22);
		panel.add(cbrafta);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEkle.setBounds(11, 255, 55, 25);
		panel.add(btnEkle);
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try{
					String sql = "INSERT INTO kitap"
					+"(kitap_id,kitap_ad, kitap_aciklama, kitap_basimyili,kitap_rafta)"
					+"VALUES (?,?,?,?,?)";
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					pst = (PreparedStatement) con.prepareStatement(sql);
						
					pst.setInt(1, Integer.parseInt( kitapid.getText()));
					pst.setString(2,txtAd.getText());
					pst.setString(3,txtAciklama.getText());
					pst.setInt(4,Integer.parseInt(txtBasimYili.getText()));
					pst.setString(5,(String) cbrafta.getSelectedItem());
					pst.executeUpdate();	
					String str=(String) cbyazar.getSelectedItem();
					String[] splited=str.split("\\s+");
					System.out.println(splited[0]);
					yazaridgir(Integer.parseInt( kitapid.getText()), Integer.parseInt(splited[0]));
					
					String strr=(String) cbkat.getSelectedItem();
					String[] splitedd=strr.split("\\s+");
					System.out.println(splitedd[0]);
					kategoriidgir(Integer.parseInt( kitapid.getText()) , Integer.parseInt(splitedd[0]));
					
				
					JOptionPane.showMessageDialog(null, "Kitap eklendi.");
					 
					}
					catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex); 
					}
				showTableData();
			}
				
		});
		
		JButton btnSil = new JButton("Sil");
		btnSil.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSil.setBounds(76, 255, 55, 25);
		panel.add(btnSil);
		
		JLabel lblNewLabel_1 = new JLabel("Kitap Ad\u0131");
		lblNewLabel_1.setBounds(new Rectangle(25, 0, 0, 0));
		lblNewLabel_1.setBounds(11, 58, 73, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Kitap ID");
		lblNewLabel.setBounds(11, 28, 73, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("A\u00E7\u0131klama");
		lblNewLabel_2.setBounds(new Rectangle(25, 0, 0, 0));
		lblNewLabel_2.setBounds(11, 88, 75, 14);
		panel.add(lblNewLabel_2);
		
		txtAciklama = new JTextField();
		txtAciklama.setBounds(96, 88, 85, 20);
		panel.add(txtAciklama);
		txtAciklama.setColumns(10);
		
		txtBasimYili = new JTextField();
		txtBasimYili.setBounds(96, 118, 85, 20);
		panel.add(txtBasimYili);
		txtBasimYili.setColumns(10);
		
		JButton btnGncelle = new JButton("G\u00FCncelle");
		btnGncelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGncelle.setBounds(141, 255, 74, 25);
		panel.add(btnGncelle);
		
		txtAd = new JTextField();
		txtAd.setBounds(96, 57, 85, 20);
		panel.add(txtAd);
		txtAd.setColumns(10);
		
		kitapid = new JTextField();
		kitapid.setBounds(96, 28, 85, 20);
		panel.add(kitapid);
		kitapid.setColumns(10);
		
		cbkat = new JComboBox();
		cbkat.setBounds(96, 208, 150, 22);
		panel.add(cbkat);
		doldurkat(cbkat);
		
		JLabel lblYazar = new JLabel("Yazar");
		lblYazar.setBounds(new Rectangle(25, 0, 0, 0));
		lblYazar.setBounds(11, 178, 73, 14);
		panel.add(lblYazar);
		
		JLabel lblKategori = new JLabel("Kategori");
		lblKategori.setBounds(new Rectangle(25, 0, 0, 0));
		lblKategori.setBounds(11, 208, 73, 14);
		panel.add(lblKategori);
		
		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				kitapid.setText("");
				txtAciklama.setText("");
				txtAd.setText("");
				txtBasimYili.setText("");
				cbrafta.setSelectedIndex(0);
				cbkat.setSelectedIndex(0);
				cbyazar.setSelectedIndex(0);
			}
		});
		btnTemizle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTemizle.setBounds(225, 256, 69, 25);
		panel.add(btnTemizle);
		
		JLabel yazararti = new JLabel("");
		yazararti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				yazarekle ye=new yazarekle();
				ye.setVisible(true);
				
			}
		});
		yazararti.setIcon(new ImageIcon(kitap.class.getResource("/image/icons8-plus-16.png")));
		yazararti.setBounds(256, 181, 16, 16);
		panel.add(yazararti);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				katEkle ke=new katEkle();
			
			}
		});
		label.setIcon(new ImageIcon(kitap.class.getResource("/image/icons8-plus-16.png")));
		label.setBounds(256, 211, 16, 16);
		panel.add(label);
		
		JLabel kitapodunc = new JLabel("");
		kitapodunc.setIcon(new ImageIcon(kitap.class.getResource("/image/packages.png")));
		kitapodunc.setBounds(25, 315, 64, 64);
		panel.add(kitapodunc);
		
		kisiid = new JTextField();
		kisiid.setColumns(10);
		kisiid.setBounds(162, 320, 98, 20);
		panel.add(kisiid);
		
		JLabel kisiidt = new JLabel("Ki\u015Fi ID");
		kisiidt.setBounds(new Rectangle(25, 0, 0, 0));
		kisiidt.setBounds(121, 320, 73, 14);
		panel.add(kisiidt);
		
		JDateChooser tarih = new JDateChooser();
		tarih.setDateFormatString("yyyy-MM-dd");
		tarih.setBounds(162, 355, 98, 20);
		panel.add(tarih);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.DARK_GRAY);
		separator_2.setBounds(11, 301, 278, 3);
		panel.add(separator_2);
		
		JLabel lblTarih = new JLabel("Tarih");
		lblTarih.setBounds(new Rectangle(25, 0, 0, 0));
		lblTarih.setBounds(121, 355, 73, 14);
		panel.add(lblTarih);
		
		JButton btnNewButton = new JButton("\u00D6d\u00FCn\u00E7");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbrafta.getSelectedItem()=="H")
				{
					 final JPanel panel = new JPanel();

					    JOptionPane.showMessageDialog(panel, "Rafta kitap mevcut deðil.", "Error", JOptionPane.ERROR_MESSAGE);

				}
						
				else {
					
					
					try{
					String sql = "INSERT INTO islem"
						+"(kisi_id,kitap_id,alinma_tarih)"
						+"VALUES (?,?,?)";
						
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
						pst =  (PreparedStatement) con.prepareStatement(sql);
						
						
						pst.setInt(1,Integer.parseInt(kisiid.getText()));
						pst.setInt(2,Integer.parseInt(kitapid.getText()));
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						
						pst.setDate(3,java.sql.Date.valueOf(sdf.format(tarih.getDate())) );
						pst.executeUpdate();	
						
					
						JOptionPane.showMessageDialog(null, "Baþarýlý! Kitap artýk rafta deðil. Lütfen güncelleyin.");
						
						showTableData();
						}
						catch(SQLException | HeadlessException ex){
						JOptionPane.showMessageDialog(null, ex); 
						}
					
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(111, 394, 70, 40);
		panel.add(btnNewButton);
		
		JButton btnVer = new JButton("\u0130ade");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbrafta.getSelectedItem()=="E")
				{
					 final JPanel panel = new JPanel();

					    JOptionPane.showMessageDialog(panel, "Kitap zaten rafta.", "Error", JOptionPane.ERROR_MESSAGE);

				}
				
else {
					
					
					try{
						 String sql = "UPDATE islem SET  iadeTarih=? WHERE kitap_id=? and kisi_id=?";
						 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
						 pst = (PreparedStatement) con.prepareStatement(sql);
						     
						
							
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							
							pst.setDate(1,java.sql.Date.valueOf(sdf.format(tarih.getDate())) );
							pst.setInt(2,Integer.parseInt(kitapid.getText()));
							pst.setInt(3,Integer.parseInt(kisiid.getText()));
							pst.executeUpdate();	
					
					
						JOptionPane.showMessageDialog(null, "Baþarýlý! Kitap artýk rafta. Lütfen güncelleyin.");
						 
						}
						catch(SQLException | HeadlessException ex){
						JOptionPane.showMessageDialog(null, ex); 
						}
					
				}
				
				
			}
		});
		btnVer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVer.setBounds(202, 394, 70, 40);
		panel.add(btnVer);
		
		JButton btnCezalar = new JButton("Cezalar");
		btnCezalar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kitapodunc ko=new kitapodunc();
				ko.setVisible(true);
			}
		});
		btnCezalar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCezalar.setBounds(14, 394, 70, 40);
		panel.add(btnCezalar);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(733, 37, 158, 432);
		panel_1.setBackground(SystemColor.controlHighlight);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		Label Baslik = new Label("Kitap Bilgileri");
		Baslik.setBounds(42, 10, 83, 22);
		panel_1.add(Baslik);
		
		yazaralan = new JTextArea();
		yazaralan.setBackground(SystemColor.textHighlightText);
		yazaralan.setLineWrap(true);
		yazaralan.setBounds(10, 81, 138, 132);
		panel_1.add(yazaralan);
		
		katarea = new JTextArea();
		katarea.setBackground(SystemColor.textHighlightText);
		katarea.setLineWrap(true);
		katarea.setBounds(10, 270, 138, 135);
		panel_1.add(katarea);
		
		Label label_1 = new Label("Yazar Bilgileri");
		label_1.setBounds(10, 53, 83, 22);
		panel_1.add(label_1);
		
		Label label_2 = new Label("Kategori");
		label_2.setBounds(10, 243, 83, 22);
		panel_1.add(label_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.DARK_GRAY);
		separator_3.setBounds(0, 38, 137, 3);
		panel_1.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(Color.DARK_GRAY);
		separator_4.setBounds(20, 230, 137, 3);
		panel_1.add(separator_4);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBounds(317, 11, 2, 458);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(724, 11, 2, 458);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_5 = new JLabel("X");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(875, 7, 12, 22);
		contentPane.add(lblNewLabel_5);
		
		
				
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					 String sql = "UPDATE kitap SET  kitap_ad=?,kitap_aciklama=?,kitap_basimyili=?,kitap_rafta=? WHERE kitap_id=?";
					 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					 pst = (PreparedStatement) con.prepareStatement(sql);
					 pst.setInt(5, Integer.parseInt(kitapid.getText()) );
					 pst.setString(1,txtAd.getText());
					 pst.setString(2,txtAciklama.getText());
					 pst.setInt(3,Integer.parseInt(txtBasimYili.getText()));
					 pst.setString(4,(String) cbrafta.getSelectedItem());
					 pst.executeUpdate();
					 
						String str=(String) cbyazar.getSelectedItem();
						String[] splited=str.split("\\s+");
						
						
					 
					 yazarguncelle(Integer.parseInt(kitapid.getText()), Integer.parseInt(splited[0]));
					 
					    String str2=(String) cbyazar.getSelectedItem();
						String[] splitedd=str2.split("\\s+");
						
					kategorigüncelle(Integer.parseInt(kitapid.getText()), Integer.parseInt(splitedd[0]));
						
					 
					 JOptionPane.showMessageDialog(null, "Güncelleme Baþarýlý");
					  
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
					String sql= "DELETE from kitap WHERE kitap_id =?";		
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					pst = (PreparedStatement) con.prepareStatement(sql);
					pst.setInt(1,Integer.parseInt(kitapid.getText()));
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Silme baþarýlý. Yazar ve kategoriler foreign key olduðu için otomatik silindi.");
					 
					}
					catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex);
					}
				
				
				
				showTableData();
			}
		});
	    
		
	}
	}
    

