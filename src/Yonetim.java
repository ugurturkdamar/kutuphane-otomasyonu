import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.util.Random;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Panel;
import javax.swing.JLayeredPane;
import java.awt.TextField;
import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class Yonetim extends JFrame {

	private JPanel contentPane;
	
	int xx,xy;
	String name;
	String sifre;
	String control;
	
	public int a,b;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Yonetim frame = new Yonetim();
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
	public Yonetim() {
		setUndecorated(true);
		setVisible(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Yonetim.class.getResource("/image/weww.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 470);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textInactiveText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel pansagal = new Panel();
		
		pansagal.setBounds(10, 10, 726, 447);
		pansagal.setBackground(new Color(240,240,240));
		contentPane.add(pansagal);
		pansagal.setLayout(null);
		
		JLabel Kutuphane = new JLabel("");
		Kutuphane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				kutuphanemenu km=new kutuphanemenu();
				km.setVisible(true);
			}
		});
		Kutuphane.setVerticalAlignment(SwingConstants.TOP);
		Kutuphane.setBounds(555, 65, 128, 128);
		Kutuphane.setIcon(new ImageIcon(Yonetim.class.getResource("/image/bookshelf-icon.png")));
		pansagal.add(Kutuphane);
		
		JLabel insan = new JLabel("");
		insan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				uyemenü um = new uyemenü();
				um.setVisible(true);
			}
		});
		insan.setBounds(310, 65, 128, 128);
		insan.setIcon(new ImageIcon(Yonetim.class.getResource("/image/profle-icon.png")));
		pansagal.add(insan);
		
		JLabel istatistik = new JLabel("");
		istatistik.setBounds(555, 239, 128, 128);
		istatistik.setIcon(new ImageIcon(Yonetim.class.getResource("/image/jtuf.png")));
		pansagal.add(istatistik);
		
		JLabel ayarlar = new JLabel("");
		ayarlar.setBounds(310, 239, 128, 128);
		ayarlar.setIcon(new ImageIcon(Yonetim.class.getResource("/image/gear-icon.png")));
		pansagal.add(ayarlar);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.setBounds(705, 0, 11, 37);
		pansagal.add(lbl_close);
		lbl_close.setFont(new Font("Arial",Font.PLAIN,35));
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			System.exit(0);
			}
		});
		lbl_close.setFont(new Font("Tahome",Font.PLAIN, 18));
		lbl_close.setForeground(SystemColor.windowBorder);
		lbl_close.setBackground(new Color(255, 255, 255));
		
		JLabel kitaplýk = new JLabel("\u00DCyeler");
		kitaplýk.setFont(new Font("Arial",Font.PLAIN,18));
		kitaplýk.setForeground(SystemColor.windowBorder);
		kitaplýk.setBounds(347, 30, 52, 30);
		pansagal.add(kitaplýk);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(310, 216, 373, 12);
		pansagal.add(separator);
		
		JLabel lblKtphaneciler = new JLabel("K\u00FCt\u00FCphaneciler");
		lblKtphaneciler.setForeground(SystemColor.windowBorder);
		lblKtphaneciler.setFont(new Font("Arial", Font.PLAIN, 18));
		lblKtphaneciler.setBounds(563, 30, 120, 30);
		pansagal.add(lblKtphaneciler);
		
		JLabel lblAyarlar = new JLabel("Ayarlar");
		lblAyarlar.setForeground(SystemColor.windowBorder);
		lblAyarlar.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAyarlar.setBounds(337, 378, 60, 30);
		pansagal.add(lblAyarlar);
		
		JLabel lblIstatiktik = new JLabel("\u0130statiktik");
		lblIstatiktik.setForeground(SystemColor.windowBorder);
		lblIstatiktik.setFont(new Font("Arial", Font.PLAIN, 18));
		lblIstatiktik.setBounds(589, 378, 70, 30);
		pansagal.add(lblIstatiktik);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(6, 59, 248, 89);
		pansagal.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Yonetim.class.getResource("/image/250.png")));
		
		JLabel erciyes = new JLabel("Erciyes Library");
		erciyes.setBounds(46, 159, 173, 36);
		pansagal.add(erciyes);
		erciyes.setForeground(SystemColor.windowBorder);
		erciyes.setFont(new Font("Arial",Font.PLAIN,25));
		
		Label label = new Label("Copyright \u00A9 Erciyes Library");
		label.setBounds(0, 415, 207, 22);
		pansagal.add(label);
		label.setAlignment(Label.CENTER);
		label.setBackground(SystemColor.activeCaption);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(281, 25, 11, 383);
		pansagal.add(separator_1);
		
		JLabel label_1 = new JLabel("-");
		label_1.setForeground(SystemColor.windowBorder);
		label_1.setFont(new Font("Arial", Font.PLAIN, 35));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(685, 5, 13, 23);
		pansagal.add(label_1);
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(23, 216, 217, 176);
		pansagal.add(panel);
		
		JTextPane txtpnSaynAhmetMehmet = new JTextPane();
		txtpnSaynAhmetMehmet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnSaynAhmetMehmet.setForeground(SystemColor.windowBorder);
		txtpnSaynAhmetMehmet.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnSaynAhmetMehmet.setText("Say\u0131n Ahmet Mehmet \r\nKitapl\u0131k Men\u00FCs\u00FCnde\r\nKitaplarla alakal\u0131 i\u015Flemleri, \r\nKitaplarla alakal\u0131 i\u015Flemleri, K\u00FCt\u00FCphaneciler\r\nKitaplarla i\u015Flemleri, K\u00FCt\u00FCphaneciler\r\nKitaplarla alakal\u0131 i\u015Flemleri, K\u00FCt\u00FCphaneciler\r\nKitaplarla i\u015Flemleri, K\u00FCt\u00FCphaneciler");
		panel.add(txtpnSaynAhmetMehmet);
		Random rand=new Random();
		a=rand.nextInt(10)+1;
		b=rand.nextInt(10)+1;
	}
}
