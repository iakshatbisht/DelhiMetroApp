package ERROR;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

public class Initial_Page {

	private JFrame frame;
	static public JTextField txtSaasad;
	static public String username;
	
	Connection conn = null;
	OraclePreparedStatement pst = null;	
	OracleResultSet rs = null;
  static JPasswordField passwordField;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Initial_Page window = new Initial_Page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Initial_Page() {
			LookAndFeelInfo lf[]=UIManager.getInstalledLookAndFeels();
			try{			
				UIManager.setLookAndFeel(lf[1].getClassName());
			}catch(Exception e) {
				
			}
				initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aknit\\eclipse-workspace\\DBMS_Project\\USED_IMAGES\\dmrc.png"));
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setContentPane(new JLabel(resize(new ImageIcon("C:/Users/aknit/eclipse-workspace/DBMS_Project/USED_IMAGES/bg.jpg"), frame.getWidth(), frame.getHeight())));
		JLabel lblNewLabel = new JLabel("THE DMRC PROTOTYPE");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Monotyoe Corsiva", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(153, 10, 550, 102);
		frame.getContentPane().add(lblNewLabel);
		
		
		SignIn_Page sip = new SignIn_Page();
		
		
		  JLabel lblNewLabel_4 = new JLabel(""); 
		  lblNewLabel_4.setBounds(480, 170, 356, 342); 
		  lblNewLabel_4.setIcon(resize(new ImageIcon("C:/Users/aknit/eclipse-workspace/DBMS_Project/USED_IMAGES/metro.jpg"), lblNewLabel_4.getWidth(), lblNewLabel_4.getHeight()));
		  frame.getContentPane().add(lblNewLabel_4);
		 
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(37, 123, 396, 267);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(0,0,0,65));
		
		
		JLabel lblNewLabel_2 = new JLabel("UserName :");
		lblNewLabel_2.setBounds(22, 43, 130, 33);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.white);
		
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(22, 117, 121, 33);
		panel.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
		lblPassword.setForeground(Color.white);
		
		txtSaasad = new JTextField();
		txtSaasad.setBounds(176, 45, 186, 32);
		panel.add(txtSaasad);
		txtSaasad.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		txtSaasad.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		
		btnNewButton.setBounds(124, 186, 130, 50);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtSaasad.getText();	
				Second second = new Second();		
				String passkey = new String(passwordField.getPassword());
				try {
					
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
						e1.printStackTrace();
					}
					Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:<port_number>:xe","Username","Password");
					String query;
					String search = "dmrc";
					if(username.toLowerCase().indexOf(search.toLowerCase()) != -1) {
						query = "SELECT * FROM admin where Username=? AND Password=?";
					}
					else {
						query = "SELECT * FROM users_a where Username=? AND Password=?";

					}
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.setString(1, username);
					pst.setString(2, passkey);
					ResultSet rs = (OracleResultSet) pst.executeQuery();
					if(rs.next()) {
						if(second.isVisible()==true) {
							JOptionPane.showMessageDialog(frame, "Already an account is logged in. Log out first!");
							return;
						}
						second.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(frame, "Either your password is wrong or you don't have account. Sign in first!");
					}
					pst.close();
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 117, 186, 32);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(37, 431, 396, 122);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0,0,0,65));
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setBounds(110, 54, 149, 58);
		panel_1.add(btnSignIn);
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(sip.isVisible()==true) {
						JOptionPane.showMessageDialog(frame, "Already an account is logged in.");
						return;
					}
					sip.setVisible(true);
			}
		});
		btnSignIn.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		
		JLabel lblNewLabel_3 = new JLabel("Don't have an account, create here..");
		lblNewLabel_3.setBounds(37, 22, 323, 26);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
	}
	public static ImageIcon resize(ImageIcon im,int w,int h)
	{
	BufferedImage bi=new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	Graphics2D gd=(Graphics2D)bi.createGraphics();
	gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
	gd.drawImage(im.getImage(), 0, 0, w, h, null);
	gd.dispose();
	return new ImageIcon(bi);
	}
	
}
