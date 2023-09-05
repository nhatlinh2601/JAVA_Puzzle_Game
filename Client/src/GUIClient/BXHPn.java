package GUIClient;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class BXHPn extends JPanel {
	public static DefaultTableModel model = new DefaultTableModel();
	private String[] header = new String[] { "Thứ hạng", "Username", "Tên", "Tổng điểm" };
	private JTable table;

	private JButton btnNo1;
	private JButton btnNo2;
	private JButton btnNo3;

	public void set_top_bxh(int i, String top1, String top2, String top3) {
		if (i == 0) {
			this.btnNo1.setText(top1);
		} else if (i == 1) {
			this.btnNo2.setText(top2);
		} else if (i == 2) {
			this.btnNo3.setText(top3);
		}
	}

	public BXHPn() {

		Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
		setBackground(Color.WHITE);
		setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BXHPn.class.getResource("/Images/rank.jpg")));
		lblNewLabel.setBounds(129, 10, 439, 347);
		panel_1.add(lblNewLabel);

		JButton btnNewButton = new JButton("1");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 41));
		btnNewButton.setBounds(64, 378, 92, 88);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("2");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 41));
		btnNewButton_1.setBounds(64, 517, 92, 88);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("3");
		btnNewButton_2.setBackground(new Color(210, 180, 140));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 41));
		btnNewButton_2.setBounds(64, 650, 92, 88);
		panel_1.add(btnNewButton_2);

		btnNo1 = new JButton("Nhat Linh Tran");
		btnNo1.setFont(new Font("Monospaced", Font.BOLD, 36));
		btnNo1.setBackground(Color.ORANGE);
		btnNo1.setBounds(200, 378, 421, 88);
		panel_1.add(btnNo1);

		btnNo2 = new JButton("Nhat Linh Tran");
		btnNo2.setFont(new Font("Monospaced", Font.BOLD, 36));
		btnNo2.setBackground(Color.LIGHT_GRAY);
		btnNo2.setBounds(200, 517, 421, 88);
		panel_1.add(btnNo2);

		btnNo3 = new JButton("Nhat Linh Tran");
		btnNo3.setFont(new Font("Monospaced", Font.BOLD, 36));
		btnNo3.setBackground(new Color(210, 180, 140));
		btnNo3.setBounds(200, 650, 421, 88);
		panel_1.add(btnNo3);
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(232, 242, 240));
		add(panel2);
		panel2.setLayout(null);

		table = new JTable();
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setBackground(new Color(211, 225, 237));
		table.setFont(fontTbl);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(90, 95, 452, 644);
		panel2.add(scrollPane);
		table.setModel(model);

		JLabel lblBxh = new JLabel("BXH TỔNG HỢP");
		lblBxh.setForeground(Color.DARK_GRAY);
		lblBxh.setFont(new Font("Consolas", Font.BOLD, 25));
		lblBxh.setBackground(Color.BLACK);
		lblBxh.setBounds(232, 15, 189, 70);
		panel2.add(lblBxh);
		model.setColumnIdentifiers(header);

	}

}
