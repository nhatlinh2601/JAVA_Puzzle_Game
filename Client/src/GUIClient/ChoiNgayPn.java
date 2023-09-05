package GUIClient;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ChoiNgayPn extends JPanel {

	public static JTextField txtUserName;
	public static JTextField txtTen;
	public static JTextArea txtQuestionText;
	public static JButton btnOpt1;
	public static JButton btnOpt2;
	public static JButton btnOpt3;
	public static JButton btnOpt4;
	public static JButton btnSTTCauHoi;
	public static JButton btnTime;
	public static JButton btnScore;
	public static JButton btnEndGame;

	public ChoiNgayPn() {

		setBackground(new Color(245, 255, 250));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 234, 246));
		panel.setBounds(28, 21, 301, 725);
		add(panel);
		panel.setLayout(null);

		JLabel lbTime = new JLabel("THỜI GIAN CÒN LẠI");
		lbTime.setBackground(SystemColor.desktop);
		lbTime.setForeground(Color.DARK_GRAY);
		lbTime.setFont(new Font("Consolas", Font.BOLD, 25));
		lbTime.setBounds(21, 10, 259, 70);
		panel.add(lbTime);

		btnTime = new JButton();
		btnTime.setForeground(SystemColor.textText);
		btnTime.setBackground(new Color(221, 237, 234));
		btnTime.setFont(new Font("Book Antiqua", Font.BOLD, 38));
		btnTime.setBounds(78, 65, 124, 60);
		panel.add(btnTime);

		JLabel lbScore = new JLabel("TỔNG ĐIỂM");
		lbScore.setForeground(Color.DARK_GRAY);
		lbScore.setFont(new Font("Consolas", Font.BOLD, 25));
		lbScore.setBounds(78, 162, 143, 70);
		panel.add(lbScore);

		btnScore = new JButton("0");
		btnScore.setForeground(SystemColor.menuText);
		btnScore.setFont(new Font("Perpetua", Font.BOLD, 48));
		btnScore.setBackground(new Color(221, 237, 234));
		btnScore.setBounds(78, 229, 124, 60);
		panel.add(btnScore);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(218, 234, 246));
		panel_2.setBounds(21, 299, 259, 316);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblThngTinNgi = new JLabel("Thông tin người chơi");
		lblThngTinNgi.setForeground(Color.DARK_GRAY);
		lblThngTinNgi.setFont(new Font("Consolas", Font.BOLD, 20));
		lblThngTinNgi.setBackground(Color.BLACK);
		lblThngTinNgi.setBounds(0, 22, 239, 53);
		panel_2.add(lblThngTinNgi);

		JLabel lblUsername = new JLabel("username");
		lblUsername.setForeground(SystemColor.windowBorder);
		lblUsername.setFont(new Font("Monospaced", Font.BOLD, 18));
		lblUsername.setBackground(Color.BLACK);
		lblUsername.setBounds(0, 71, 105, 53);
		panel_2.add(lblUsername);

		JLabel lblTngim_1 = new JLabel("Điểm tích lũy");
		lblTngim_1.setForeground(SystemColor.windowBorder);
		lblTngim_1.setFont(new Font("Monospaced", Font.BOLD, 18));
		lblTngim_1.setBackground(Color.BLACK);
		lblTngim_1.setBounds(0, 197, 182, 53);
		panel_2.add(lblTngim_1);

		JButton btnTotalScore = new JButton("1000");
		btnTotalScore.setForeground(Color.BLACK);
		btnTotalScore.setFont(new Font("Perpetua", Font.BOLD, 34));
		btnTotalScore.setBackground(SystemColor.text);
		btnTotalScore.setBounds(61, 260, 121, 41);
		panel_2.add(btnTotalScore);

		txtUserName = new JTextField();
		txtUserName.setText(DangNhapDlg.username);
		txtUserName.setEnabled(false);
		txtUserName.setForeground(Color.DARK_GRAY);
		txtUserName.setFont(new Font("Consolas", Font.BOLD, 18));
		txtUserName.setBounds(0, 134, 239, 34);
		panel_2.add(txtUserName);
		txtUserName.setColumns(10);

		btnEndGame = new JButton("KẾT THÚC");
		btnEndGame.setForeground(Color.DARK_GRAY);
		btnEndGame.setFont(new Font("Consolas", Font.BOLD, 25));
		btnEndGame.setBackground(new Color(213, 222, 232));
		btnEndGame.setBounds(51, 654, 188, 52);
		panel.add(btnEndGame);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(221, 237, 234));
		panel_1.setForeground(new Color(221, 237, 234));
		panel_1.setBounds(356, 21, 882, 725);
		add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(232, 242, 240));
		panel_3.setBounds(21, 22, 839, 368);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(232, 242, 240));
		panel_4.setBounds(21, 436, 839, 249);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		btnOpt1 = new JButton("");
		btnOpt1.setForeground(Color.DARK_GRAY);
		btnOpt1.setFont(new Font("Consolas", Font.BOLD, 25));
		btnOpt1.setBackground(new Color(221, 237, 234));
		btnOpt1.setBounds(10, 10, 393, 96);
		panel_4.add(btnOpt1);

		btnOpt3 = new JButton("");
		btnOpt3.setForeground(Color.DARK_GRAY);
		btnOpt3.setFont(new Font("Consolas", Font.BOLD, 25));
		btnOpt3.setBackground(new Color(221, 237, 234));
		btnOpt3.setBounds(10, 143, 393, 96);
		panel_4.add(btnOpt3);

		btnOpt2 = new JButton("");
		btnOpt2.setForeground(Color.DARK_GRAY);
		btnOpt2.setFont(new Font("Consolas", Font.BOLD, 25));
		btnOpt2.setBackground(new Color(221, 237, 234));
		btnOpt2.setBounds(436, 10, 393, 96);
		panel_4.add(btnOpt2);

		btnOpt4 = new JButton("");
		btnOpt4.setForeground(Color.DARK_GRAY);
		btnOpt4.setFont(new Font("Consolas", Font.BOLD, 25));
		btnOpt4.setBackground(new Color(221, 237, 234));
		btnOpt4.setBounds(436, 143, 393, 96);
		panel_4.add(btnOpt4);

		JButton btnSTTA = new JButton();
		btnSTTA.setBackground(new Color(245, 255, 250));
		btnSTTA.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSTTA.setBounds(23, 36, 54, 40);
		panel_4.add(btnSTTA);

		JButton btnSTTB = new JButton();
		btnSTTB.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSTTB.setBounds(450, 36, 54, 40);
		panel_4.add(btnSTTB);

		JButton btnSTTC = new JButton();
		btnSTTC.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSTTC.setBackground(new Color(245, 255, 250));
		btnSTTC.setBounds(23, 168, 54, 40);
		panel_4.add(btnSTTC);

		JButton btnSTTD = new JButton();
		btnSTTD.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSTTD.setBackground(new Color(245, 255, 250));
		btnSTTD.setBounds(450, 168, 54, 40);
		panel_4.add(btnSTTD);

		txtQuestionText = new JTextArea();
		txtQuestionText.setLineWrap(true);
		txtQuestionText.setFont(new Font("Monospaced", Font.BOLD, 34));
		txtQuestionText.setText("");
		txtQuestionText.setBackground(Color.WHITE);
		txtQuestionText.setBounds(25, 74, 793, 258);
		panel_3.add(txtQuestionText);

		JLabel lblCu = new JLabel("CÂU ");
		lblCu.setForeground(Color.DARK_GRAY);
		lblCu.setFont(new Font("Consolas", Font.BOLD, 25));
		lblCu.setBackground(Color.BLACK);
		lblCu.setBounds(15, 10, 54, 50);
		panel_3.add(lblCu);

		btnSTTCauHoi = new JButton();
		btnSTTCauHoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSTTCauHoi.setBounds(75, 10, 86, 40);
		panel_3.add(btnSTTCauHoi);
	}
}
