package GUIClient;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class ChatPn extends JPanel {

	public static int index_btn = 0;
	public static JTextArea txt_mes;
	public static JTextArea view_mes;
	public static int check_send = 0;
	public static final Object lock = new Object();

	public ChatPn() {
		setBackground(new Color(245, 255, 250));
		setLayout(null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setForeground(new Color(176, 208, 238));
		panel_1_1.setBackground(SystemColor.inactiveCaption);
		panel_1_1.setBounds(-11, 685, 1273, 85);
		add(panel_1_1);

		// nơi nhập tin nhắn
		txt_mes = new JTextArea();
		txt_mes.setForeground(Color.DARK_GRAY);
		txt_mes.setFont(new Font("Monospaced", Font.BOLD, 20));
		txt_mes.setBackground(Color.WHITE);
		txt_mes.setBounds(21, 15, 927, 60);
		panel_1_1.add(txt_mes);

		// khung hiển thị tin nhắn
		view_mes = new JTextArea();
		view_mes.setFont(new Font("Monospaced", Font.PLAIN, 20));
		view_mes.setBackground(new Color(245, 255, 250));
		view_mes.setBounds(33, 0, 1229, 686);
		add(view_mes);

		JButton btnSend = new JButton("GỬI");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (lock) {
					check_send = 1;
					lock.notifyAll();
				}
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSend.setBorder(new RoundedBorder(20, new Color(213, 222, 232)));
		btnSend.setBackground(new Color(245, 255, 250));
		btnSend.setBounds(988, 15, 136, 60);
		panel_1_1.add(btnSend);

	}
}


