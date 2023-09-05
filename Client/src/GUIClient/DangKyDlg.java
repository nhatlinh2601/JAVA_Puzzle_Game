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
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DangKyDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtTen;
	private JTextField txtEmail;
	static DataOutputStream dos;
	static DataInputStream dis;
	static Socket socket;

	public void init() throws Exception {
		socket = DangNhapDlg.socket;

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 450, 367);
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
			buttonPane.setBounds(0, 283, 436, 48);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnDangNhap = new JButton("Đăng nhập");
				btnDangNhap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
//                        new DangNhapDlg();
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
			btnDangKy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						dos = new DataOutputStream(socket.getOutputStream());
						dis = new DataInputStream(socket.getInputStream());

						dos.writeUTF("DANG KY");
					} catch (IOException ex) {
						Logger.getLogger(DangKyDlg.class.getName()).log(Level.SEVERE, null, ex);
					}

					StringBuilder error = new StringBuilder();

					String username = txtUsername.getText().trim();
					char[] pass = txtPassword.getPassword();
					String password = new String(pass);
					String pass_md5 = sha_256.revertPass.encodePassword(password);
					String email = txtEmail.getText().trim();
					String fullName = txtTen.getText().trim();

					if (username.equalsIgnoreCase("")) {
						error.append("Tên đăng nhập không được để trống \n");
					}
					if (pass_md5.trim().equalsIgnoreCase("")) {
						error.append("Mật khẩu không được để trống \n");
					}
					if (fullName.trim().equalsIgnoreCase("")) {
						error.append("Tên không được để trống \n");
					}
					if (email.trim().equalsIgnoreCase("")) {
						error.append("Email không được để trống \n");
					} else {
//                        if (!checkEmail.validateEmail(email)) {
//                            error.append("Email không hợp lệ");
//                        }
					}

					if (error.length() == 0) {

						try {
							dos.writeUTF(username);
							dos.writeUTF(fullName);
							dos.writeUTF(pass_md5);
							dos.writeUTF(email);

							if (dis.readUTF().equals("DANG KY THANH CONG")) {
								JOptionPane.showMessageDialog(null, "Đăng ký thành công");
								setVisible(false);
								new DangNhapDlg();
							}

						} catch (IOException ex) {
							Logger.getLogger(DangKyDlg.class.getName()).log(Level.SEVERE, null, ex);
						}

					} else {

						JOptionPane.showMessageDialog(btnDangKy, error.toString());
					}
				}
			});
			btnDangKy.setBackground(SystemColor.inactiveCaption);
			btnDangKy.setForeground(Color.DARK_GRAY);
			btnDangKy.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnDangKy.setActionCommand("OK");
			btnDangKy.setBounds(229, 5, 155, 38);
			buttonPane.add(btnDangKy);
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(new Color(245, 255, 250));
			panel.setBounds(21, 138, 405, 46);
			contentPanel.add(panel);
			{
				JLabel lbTen = new JLabel("Tên");
				lbTen.setForeground(Color.DARK_GRAY);
				lbTen.setFont(new Font("Tahoma", Font.BOLD, 20));
				lbTen.setBounds(10, 10, 133, 25);
				panel.add(lbTen);
			}
			{
				txtTen = new JTextField();
				txtTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtTen.setColumns(10);
				txtTen.setBounds(140, 0, 229, 45);
				panel.add(txtTen);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(new Color(245, 255, 250));
			panel.setBounds(21, 194, 405, 46);
			contentPanel.add(panel);
			{
				JLabel lbEmail = new JLabel("Email");
				lbEmail.setForeground(Color.DARK_GRAY);
				lbEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
				lbEmail.setBounds(10, 10, 133, 25);
				panel.add(lbEmail);
			}
			{
				txtEmail = new JTextField();
				txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtEmail.setColumns(10);
				txtEmail.setBounds(140, 0, 229, 45);
				panel.add(txtEmail);
			}
		}

		setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public DangKyDlg() throws Exception {

		init();
		setLocationRelativeTo(null);

	}
}
