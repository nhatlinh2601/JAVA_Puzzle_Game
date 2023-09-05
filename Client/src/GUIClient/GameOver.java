package GUIClient;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;

public class GameOver extends JPanel {

	public static JButton btnScore;
	public static JButton btnTime;
	public static JButton btnTotalScore;

	public GameOver() {
		setBackground(new Color(245, 255, 250));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GameOver.class.getResource("/Images/gameover.jpg")));
		lblNewLabel.setBounds(413, -49, 373, 503);
		add(lblNewLabel);

		JLabel lbScore = new JLabel("TỔNG ĐIỂM");
		lbScore.setForeground(Color.DARK_GRAY);
		lbScore.setFont(new Font("Consolas", Font.BOLD, 30));
		lbScore.setBounds(390, 427, 208, 70);
		add(lbScore);

		btnScore = new JButton("0");
		btnScore.setForeground(Color.BLACK);

		btnScore.setFont(new Font("Perpetua", Font.BOLD, 50));
		btnScore.setBackground(new Color(221, 237, 234));
		btnScore.setBounds(681, 419, 169, 72);
		add(btnScore);

		JLabel lblThiGian = new JLabel("THỜI GIAN");
		lblThiGian.setForeground(Color.DARK_GRAY);
		lblThiGian.setFont(new Font("Consolas", Font.BOLD, 30));
		lblThiGian.setBounds(390, 528, 208, 70);
		add(lblThiGian);

		btnTime = new JButton();
		btnTime.setForeground(Color.BLACK);
		btnTime.setFont(new Font("Book Antiqua", Font.BOLD, 38));
		btnTime.setBackground(new Color(221, 237, 234));
		btnTime.setBounds(681, 528, 169, 70);
		add(btnTime);

		JLabel lblimTchLy = new JLabel("ĐIỂM TÍCH LŨY");
		lblimTchLy.setForeground(Color.DARK_GRAY);
		lblimTchLy.setFont(new Font("Consolas", Font.BOLD, 30));
		lblimTchLy.setBounds(390, 633, 245, 70);
		add(lblimTchLy);

		btnTotalScore = new JButton();
		btnTotalScore.setForeground(Color.BLACK);
		btnTotalScore.setFont(new Font("Book Antiqua", Font.BOLD, 38));
		btnTotalScore.setBackground(new Color(221, 237, 234));
		btnTotalScore.setBounds(681, 633, 169, 70);
		add(btnTotalScore);

	}
}
