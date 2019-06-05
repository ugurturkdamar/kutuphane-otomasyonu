import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Label;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import java.awt.Checkbox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AnaSayfa extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTextField userýd;
	private JPasswordField pass;

	int xx,xy;
	String name;
	String sifre;
	String control;
	
	public int a,b;
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JComboBox kutyon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaSayfa frame = new AnaSayfa();
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
	public AnaSayfa() {

		
		setUndecorated(true);
		setVisible(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnaSayfa.class.getResource("/image/weww.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 350, 420);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 xx = e.getX();
				 xy = e.getY();
			}
		});
		lblNewLabel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				int x = arg0.getXOnScreen();
	            int y = arg0.getYOnScreen();
	            AnaSayfa.this.setLocation(x - xx, y - xy);
			}
		});
		
		JLabel lblLibrary = new JLabel("Library");
		lblLibrary.setForeground(new Color(241, 57, 83));
		lblLibrary.setBounds(78, 230, 174, 65);
		lblLibrary.setFont(new Font("Tahome",Font.PLAIN, 58));
		panel.add(lblLibrary);
		lblNewLabel.setBounds(41, 33, 253, 398);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon(AnaSayfa.class.getResource("/image/250.png")));
		panel.add(lblNewLabel);
		
		JLabel lblErciyes = new JLabel("Erciyes");
		lblErciyes.setForeground(SystemColor.desktop);
		lblErciyes.setBounds(68, 145, 200, 74);
		lblErciyes.setFont(new Font("Tahome",Font.PLAIN, 58));
		panel.add(lblErciyes);
		
		Button button = new Button("Giriþ Yap");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				kitap kt=new kitap();	
				Yonetim yt= new Yonetim();
				kt.setVisible(false);
				yt.setVisible(false);
		
				if(kutyon.getSelectedItem()=="Kütüphaneci")
				{	
					String sql3="select * from kutuphaneciler where kutid=? and kutsifre=?";
				
					try{
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
						pst =  (PreparedStatement) con.prepareStatement(sql3);
					pst.setInt(1,Integer.parseInt(userýd.getText()));
					pst.setString(2,pass.getText());
					rs=pst.executeQuery();
					if(rs.next())
					{
					JOptionPane.showMessageDialog(null,"Kütüphaneci Giriþi Baþarýlý.");
					 kt.setVisible(true);
					}
					else
					{
					JOptionPane.showMessageDialog(null, "Kütüphaneci Giriþi Baþarýsýz.");
					}
					}
					catch(SQLException | HeadlessException ex)
					{
					JOptionPane.showMessageDialog(null,ex);
					}
					
			}
				
				else if(kutyon.getSelectedItem()=="Yönetici")
				{
					String sql4="select * from yöneticiler where yonID=? and yonSifre=?";
					
					try{
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphane","root","");
						pst =  (PreparedStatement) con.prepareStatement(sql4);
					pst.setInt(1,Integer.parseInt(userýd.getText()));
					pst.setString(2,pass.getText());
					rs=pst.executeQuery();
					if(rs.next())
					{
					JOptionPane.showMessageDialog(null,"Yönetici Giriþi Baþarýlý.");
					 yt.setVisible(true);
					}
					else
					{
					JOptionPane.showMessageDialog(null, "Yönetici Giriþi Baþarýsýz.");
					}
					}
					catch(SQLException | HeadlessException ex)
					{
					JOptionPane.showMessageDialog(null,ex);
					}
					
				}
				
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(241,57,83));
		button.setBounds(395, 308, 202, 30);
		contentPane.add(button);
		
		Label label = new Label("Kullan\u0131c\u0131 ID");
		label.setBounds(395, 59, 76, 22);
		contentPane.add(label);
		
		userýd = new JTextField();
		userýd.setBounds(395, 92, 245, 30);
		contentPane.add(userýd);
		userýd.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(395, 121, 245, 2);
		contentPane.add(separator);
		
		JLabel lblifre = new JLabel("\u015Eifre");
		lblifre.setBounds(394, 146, 46, 14);
		contentPane.add(lblifre);
		
		pass = new JPasswordField();
		pass.setBounds(395, 170, 245, 30);
		contentPane.add(pass);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.setFont(new Font("Arial",Font.PLAIN,25));
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			System.exit(0);
			}
		});
		lbl_close.setFont(new Font("Tahome",Font.PLAIN, 18));
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setBackground(new Color(255, 255, 255));
		lbl_close.setBounds(705, -2, 11, 30);
		contentPane.add(lbl_close);
		Random rand=new Random();
		a=rand.nextInt(10)+1;
		b=rand.nextInt(10)+1;
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(395, 133, 245, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(395, 217, 245, 2);
		contentPane.add(separator_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(395, 280, 245, 2);
		contentPane.add(separator_1);
		
		kutyon = new JComboBox();
		kutyon.setForeground(Color.WHITE);
		kutyon.setBackground(new Color(241, 57, 83));
		kutyon.setModel(new DefaultComboBoxModel(new String[] {"K\u00FCt\u00FCphaneci", "Y\u00F6netici"}));
		kutyon.setBounds(395, 230, 111, 20);
		contentPane.add(kutyon);
	}
}
