package GUIClient;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import add_ip.add_ip;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DangNhapDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	static DataOutputStream dos;
	static DataInputStream dis;
	public static Socket socket;
	public static String username;
	public static Sound.sound sound;

	public void init() throws IOException {
		socket = new Socket(add_ip.add_ip, 1111);

		sound = new Sound.sound();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 450, 232);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 255, 250));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(245, 255, 250));
			panel.setBounds(21, 26, 405, 46);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lbUserName = new JLabel("Username");
				lbUserName.setBounds(10, 10, 133, 25);
				panel.add(lbUserName);
				lbUserName.setForeground(Color.DARK_GRAY);
				lbUserName.setFont(new Font("Tahoma", Font.BOLD, 20));
			}
			{
				txtUsername = new JTextField();
				txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtUsername.setBounds(140, 0, 229, 45);
				panel.add(txtUsername);
				txtUsername.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(new Color(245, 255, 250));
			panel.setBounds(21, 82, 405, 46);
			contentPanel.add(panel);
			{
				JLabel lblPassword = new JLabel("Password");
				lblPassword.setForeground(Color.DARK_GRAY);
				lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPassword.setBounds(10, 10, 133, 25);
				panel.add(lblPassword);
			}

			txtPassword = new JPasswordField();
			txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtPassword.setBounds(141, 0, 227, 46);
			panel.add(txtPassword);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(218, 234, 246));
			buttonPane.setBounds(0, 147, 436, 48);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnDangNhap = new JButton("Đăng nhập");
				btnDangNhap.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// am thanh cua game

						StringBuilder error = new StringBuilder();

						// tai khoan
						username = txtUsername.getText().trim();

						// mat khau
						char[] pass = txtPassword.getPassword();
						String password = new String(pass);
						String pass_md5 = sha_256.revertPass.encodePassword(password);
						System.out.println(username + "\n" + pass_md5);

						if (username.equalsIgnoreCase("")) {
							error.append("Tên đăng nhập không được để trống \n");
						}
						if (pass_md5.trim().equalsIgnoreCase("")) {
							error.append("Mật khẩu không được để trống \n");
						}
						if (error.length() == 0) {
							// new HomeFr();
							try {
								dos = new DataOutputStream(socket.getOutputStream());
								dis = new DataInputStream(socket.getInputStream());
								// gui qua server de check
								dos.writeUTF("DANG NHAP");
								dos.writeUTF(username);
								dos.writeUTF(pass_md5);

								String check = dis.readUTF();
								if (check.equals("KIEM TRA THANH CONG")) {
									new HomeFr();
									dispose();
								} else if (check.equals("KIEM TRA KHONG THANH CONG")) {
									Sound.sound.playSoundOneClick("src//Sound//sound-lose.wav");
									JOptionPane.showMessageDialog(btnDangNhap,
											"Tên đăng nhập hoặc mật khẩu không đúng");
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						} else {

							JOptionPane.showMessageDialog(btnDangNhap, error.toString());
						}
					}
				});
				btnDangNhap.setBackground(SystemColor.inactiveCaption);
				btnDangNhap.setForeground(Color.DARK_GRAY);
				btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnDangNhap.setBounds(68, 5, 155, 38);
				btnDangNhap.setActionCommand("OK");
				buttonPane.add(btnDangNhap);
				getRootPane().setDefaultButton(btnDangNhap);
			}

			JButton btnDangKy = new JButton("Đăng ký");

			btnDangKy.setBackground(SystemColor.inactiveCaption);
			btnDangKy.setForeground(Color.DARK_GRAY);
			btnDangKy.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnDangKy.setActionCommand("OK");
			btnDangKy.setBounds(229, 5, 155, 38);
			buttonPane.add(btnDangKy);

			setVisible(true);

			btnDangKy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					try {
						new DangKyDlg();
					} catch (Exception ex) {
						Logger.getLogger(DangNhapDlg.class.getName()).log(Level.SEVERE, null, ex);
					}

				}
			});

		}
	}

	public DangNhapDlg() throws IOException {
		init();
		setLocationRelativeTo(null);
	}
}
