package GUIClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Chat.Main_Chat;
import add_ip.add_ip;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeFr extends JFrame {

	private JPanel contentPane;
	private MainPn mainPn;
	private ChoiNgayPn choiNgayPn;
	private ChatPn chatPn;
	private BXHPn bxhPn;
	private DangNhapDlg dangnhap;
	private Ready ready;

	public static JTabbedPane tabbedPane;
	public static JButton btnChoiNgay;
	public static JButton btnBXH;

	static DataOutputStream dos;
	static DataInputStream dis;
	static Socket socket;

	public static int cnt = 0;
	public static int load_BXH = 0;
	
	public HashMap<String, Integer> check = new HashMap<>();

	public void init() {
		DangNhapDlg.sound.playSoundMain("src//Sound//sanhcho.wav");
		try {
			socket = DangNhapDlg.socket;
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1551, 830);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 265, 806);
		contentPane.add(panel);
		panel.setLayout(null);

		btnBXH = new JButton("BXH");
		btnBXH.setFont(new Font("Monospaced", Font.BOLD, 24));
		btnBXH.setBackground(new Color(232, 242, 240));
		btnBXH.setBounds(40, 492, 180, 79);
		panel.add(btnBXH);

		JButton btnChat = new JButton("CHAT");
		btnChat.setFont(new Font("Monospaced", Font.BOLD, 24));
		btnChat.setBackground(new Color(232, 242, 240));
		btnChat.setBounds(40, 341, 180, 79);
		panel.add(btnChat);

		chatPn = new ChatPn();

		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");

				cnt++;
				tabbedPane.remove(tabbedPane.getSelectedComponent());
				tabbedPane.addTab("Chat", null, chatPn);
				tabbedPane.setSelectedComponent(chatPn);
				if (cnt == 1) {
					Main_Chat conan_client = new Main_Chat(dangnhap.username);
				}
			}
		});

		btnChoiNgay = new JButton("Chơi ngay");
		btnChoiNgay.setBackground(new Color(232, 242, 240));

		btnChoiNgay.setFont(new Font("Monospaced", Font.BOLD, 24));
		btnChoiNgay.setBounds(40, 189, 180, 79);
		panel.add(btnChoiNgay);

		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");
					new DangNhapDlg();
				} catch (IOException ex) {
					Logger.getLogger(HomeFr.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		btnDangXuat.setFont(new Font("Monospaced", Font.BOLD, 24));
		btnDangXuat.setBackground(new Color(232, 242, 240));
		btnDangXuat.setBounds(40, 642, 180, 79);
		panel.add(btnDangXuat);

		JButton btnTrangChu = new JButton("Trang chủ");
		btnTrangChu.setBounds(40, 47, 180, 79);
		panel.add(btnTrangChu);
		btnTrangChu.setFont(new Font("Monospaced", Font.BOLD, 24));
		btnTrangChu.setBackground(new Color(232, 242, 240));

		btnTrangChu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");
				tabbedPane.remove(tabbedPane.getSelectedComponent());
				tabbedPane.addTab("Giới Thiệu", null, mainPn);
				tabbedPane.setSelectedComponent(mainPn);
			}
		});

		mainPn = new MainPn();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		tabbedPane.setBounds(264, 0, 1288, 806);
		tabbedPane.setBackground(new Color(112, 128, 144));
		tabbedPane.addTab("Giới Thiệu", null, mainPn);
		tabbedPane.setSelectedComponent(mainPn);

		choiNgayPn = new ChoiNgayPn();
		ready = new Ready();

		btnChoiNgay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");
				tabbedPane.remove(tabbedPane.getSelectedComponent());
				tabbedPane.addTab("Chơi ngay", null, ready);
				tabbedPane.setSelectedComponent(ready);
			}
		});

		bxhPn = new BXHPn();
		btnBXH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load_BXH++;

				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");

				try {

					if (load_BXH == 1) {
						dos.writeUTF("CAN-XEM-BXH");
						loadDataUserTable();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				tabbedPane.remove(tabbedPane.getSelectedComponent());
				tabbedPane.addTab("BXH", null, bxhPn);
				tabbedPane.setSelectedComponent(bxhPn);
			}
		});

		setVisible(true);

	}

	public void loadDataUserTable() {
		// mang chua bxh top3
		String top_bxh[] = new String[3];

		int cnt = 1;
		try {

			// so luong nguoi trong bxh
			int cnt_bxh = dis.readInt();

			for (int i = 0; i < cnt_bxh; i++) {
				String username = dis.readUTF();
				String fullname = dis.readUTF();
				int score = dis.readInt();

				bxhPn.set_top_bxh(i, username, username, username);

				String[] row = new String[] { Integer.toString(cnt++), username, fullname, Integer.toString(score) };
				String row_check = username + fullname + score;

				if (check.get(row_check) == null) {
					check.put(row_check, 1);
					bxhPn.model.addRow(row);
					bxhPn.model.fireTableDataChanged();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public HomeFr() {
		init();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
