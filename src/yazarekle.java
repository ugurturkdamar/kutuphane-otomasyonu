import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Panel;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class yazarekle extends JFrame {

	private JPanel contentPane;
	private JTextField YazarAd;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yazarekle frame = new yazarekle();
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
	public yazarekle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(10, 10, 295, 241);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea biyografi = new JTextArea();
		biyografi.setBounds(92, 56, 171, 102);
		panel.add(biyografi);
		
		YazarAd = new JTextField();
		YazarAd.setBounds(92, 26, 86, 20);
		panel.add(YazarAd);
		YazarAd.setColumns(10);
		
		JLabel yazar = new JLabel("Yazar AD");
		yazar.setBounds(10, 29, 72, 14);
		panel.add(yazar);
		
		JLabel lblBiyografi = new JLabel("Biyografi");
		lblBiyografi.setBounds(10, 75, 72, 14);
		panel.add(lblBiyografi);
		
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try{
					String sql = "INSERT INTO yazarlar"
					+"(yazar_ad,yazar_bio)"
					+"VALUES (?,?)";
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
					pst =  (PreparedStatement) con.prepareStatement(sql);
					
					
					pst.setString(1,YazarAd.getText());
					pst.setString(2,biyografi.getText());
										
					
					pst.executeUpdate();	
					
				
					JOptionPane.showMessageDialog(null, "Yazar eklendi. Lütfen programý yeniden baþlatýn.");
					
					
					}
					catch(SQLException | HeadlessException ex){
					JOptionPane.showMessageDialog(null, ex); 
					}
				
			
			
				
			}
		});
		btnNewButton.setBounds(92, 192, 89, 23);
		panel.add(btnNewButton);
	}
}
