import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Panel;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class katEkle extends JFrame {

	private JPanel contentPane;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField katad;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					katEkle frame = new katEkle();
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
	public katEkle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(10, 10, 284, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		katad = new JTextField();
		katad.setBounds(88, 28, 86, 20);
		panel.add(katad);
		katad.setColumns(10);
		
		JTextArea katozet = new JTextArea();
		katozet.setBounds(88, 59, 186, 93);
		panel.add(katozet);
		
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try{
					String sql = "INSERT INTO kategoriler"
					+"(kat_ad,kat_genelaciklama)"
					+"VALUES (?,?)";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					pst =  (PreparedStatement) con.prepareStatement(sql);
					
					
					pst.setString(1,katad.getText());
					pst.setString(2,katozet.getText());
										
					
					pst.executeUpdate();	
					
				
					JOptionPane.showMessageDialog(null, "Kategori eklendi. Lütfen programý yeniden baþlatýn.");
					setVisible(false);
					
					}
					catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex); 
					}
			}
		});
		btnNewButton.setBounds(45, 182, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblKategoriAd = new JLabel("Kategori Ad");
		lblKategoriAd.setBounds(6, 31, 72, 14);
		panel.add(lblKategoriAd);
		
		JLabel lblGenelzet = new JLabel("Genel \u00D6zet");
		lblGenelzet.setBounds(6, 92, 72, 14);
		panel.add(lblGenelzet);
	}
}
