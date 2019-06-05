import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class kitapodunc extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
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
		String sql = "SELECT * FROM islem";
		pst = (PreparedStatement) con.prepareStatement(sql);
		rs=pst.executeQuery();
		Calendar C;
		
		while(rs.next()) {
			// Alýnma tarihine +10 ekleyip son verilmesi gereken tarihi belirliyoruz.
			
			Date atarih=rs.getDate("alinma_tarih");
			Date starih = atarih;
			Calendar c = Calendar.getInstance(); 
			c.setTime(starih); 
			c.add(Calendar.DATE, 10);
			starih = c.getTime();
			
			// Son verilmesi gereken tarih ile iade edilen tarihi arasýndaki farký alýyoruz.
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String son = formatter.format(starih);
			String ilk=formatter.format(atarih);
			
			double ceza=0;
			Date iadeTarih = null;
			float days = 0;
			
			if(rs.getDate("iadeTarih")!=null)
			{iadeTarih=rs.getDate("iadeTarih");
			long diff=iadeTarih.getTime()-starih.getTime();
			 days = (diff / (1000*60*60*24));
			
			if(days<0) ceza=0;
			else ceza=days*0.10;
			
			}
			
			
					Object[] row = {rs.getInt("islem_id"),
							rs.getInt("kisi_id"),
							rs.getInt("kitap_id"),
							rs.getDate("alinma_tarih"),
							son,
							iadeTarih,
							days,
							ceza,
							
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
					kitapodunc frame = new kitapodunc();
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
	public kitapodunc() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 845, 390);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 809, 329);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u0130\u015Flem ID", "Ki\u015Fi ID", "Kitap ID", "Al\u0131nma Tarih", "Son Tarih", "\u0130ade Tarihi", "Gecikme G\u00FCn", "Ceza"
			}
		));
		scrollPane.setViewportView(table);
		showTableData();
	}
}
