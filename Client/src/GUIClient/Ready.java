package GUIClient;

import CountDown.countDown;
import add_ip.add_ip;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.Socket;
import java.sql.Time;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.transform.Source;

public class Ready extends JPanel {

	private MainPn mainPn = new MainPn();
	private countDown game = new countDown();
	private ChoiNgayPn choiNgayPn = new ChoiNgayPn();

	static Socket socket;
	static DataInputStream dis;
	static DataOutputStream dos;
	public static String[][] question_tmp;
	public static int cnt_of_que;
	public static int index = 0;
	public static int point = 0;

	public static int check_reset = 0;

	public static int DES_TIME;

	public static String[] Ans_Question = { "cho", "2", "S", "sai", "cai chan", "mo mat" };

	public Ready() {
		try {
			socket = DangNhapDlg.socket;
		} catch (Exception e) {
			e.printStackTrace();
		}

		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Ready.class.getResource("/Images/ready.jpg")));
		lblNewLabel.setBounds(39, 30, 761, 395);
		add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(733, 61, 445, 337);
		add(panel);
		panel.setLayout(null);

		JLabel lblBn = new JLabel("BẠN ĐÃ SẴN SÀNG THAM GIA ");
		lblBn.setForeground(Color.DARK_GRAY);
		lblBn.setFont(new Font("Consolas", Font.BOLD, 25));
		lblBn.setBackground(Color.BLACK);
		lblBn.setBounds(55, 30, 476, 70);
		panel.add(lblBn);

		JLabel lblCngChngTi = new JLabel("CÙNG CHÚNG TÔI?");
		lblCngChngTi.setForeground(Color.DARK_GRAY);
		lblCngChngTi.setFont(new Font("Consolas", Font.BOLD, 25));
		lblCngChngTi.setBackground(Color.BLACK);
		lblCngChngTi.setBounds(98, 85, 227, 70);
		panel.add(lblCngChngTi);

		JButton btnStart = new JButton("BẮT ĐẦU");

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");

				check_reset++;
				DES_TIME = 180;
				try {
					if (check_reset == 1) {
						game.startGame(DES_TIME);
						dos = new DataOutputStream(socket.getOutputStream());
						dis = new DataInputStream(socket.getInputStream());

						dos.writeUTF("BAT DAU");

						// so luong cau hoi
						cnt_of_que = dis.readInt();

						question_tmp = new String[cnt_of_que][5];

						for (int i = 0; i < cnt_of_que; i++) {
							for (int j = 0; j < 5; j++) {
								question_tmp[i][j] = dis.readUTF();
							}
						}

						resetQuestion();

					} else {
						game.timer.cancel();
						game.startGame(DES_TIME);
					}

				} catch (Exception ex) {
					java.util.logging.Logger.getLogger(Ready.class.getName()).log(Level.SEVERE, null, ex);
				}

				resetQuestion();

				HomeFr.tabbedPane.remove(HomeFr.tabbedPane.getSelectedComponent());
				HomeFr.tabbedPane.addTab("Chơi ngay", null, choiNgayPn);
				HomeFr.tabbedPane.setSelectedComponent(choiNgayPn);

			}

		});
		btnStart.setBackground(Color.WHITE);
		btnStart.setFont(new Font("Monospaced", Font.BOLD, 24));
		btnStart.setBounds(37, 186, 157, 70);
		panel.add(btnStart);

		choiNgayPn.btnOpt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");
				int bool_check = 0;
				for (String x : Ans_Question) {
					if (x.equals(ChoiNgayPn.btnOpt1.getText())) {
						bool_check++;
						JOptionPane.showMessageDialog(null, "Bạn đã trả lời đúng");
					}
				}
				if (bool_check == 0) {
					JOptionPane.showMessageDialog(null, "Câu trả lời sai :((");
				}
				setQuestion();
				setPoint(bool_check);
			}
		});

		choiNgayPn.btnOpt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");
				int bool_check = 0;
				for (String x : Ans_Question) {
					if (x.equals(ChoiNgayPn.btnOpt2.getText())) {
						bool_check++;
						JOptionPane.showMessageDialog(null, "Bạn đã trả lời đúng");
					}
				}
				if (bool_check == 0) {
					JOptionPane.showMessageDialog(null, "Câu trả lời sai :((");
				}
				setQuestion();
				setPoint(bool_check);
			}
		});

		choiNgayPn.btnOpt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");
				int bool_check = 0;
				for (String x : Ans_Question) {
					if (x.equals(ChoiNgayPn.btnOpt3.getText())) {
						bool_check++;
						JOptionPane.showMessageDialog(null, "Bạn đã trả lời đúng");
					}
				}
				if (bool_check == 0) {
					JOptionPane.showMessageDialog(null, "Câu trả lời sai :((");
				}
				setQuestion();
				setPoint(bool_check);
			}
		});

		choiNgayPn.btnOpt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");
				int bool_check = 0;
				for (String x : Ans_Question) {
					if (x.equals(ChoiNgayPn.btnOpt4.getText())) {
						bool_check++;
						JOptionPane.showMessageDialog(null, "Bạn đã trả lời đúng (^ > ^)");
					}
				}
				if (bool_check == 0) {
					JOptionPane.showMessageDialog(null, "Câu trả lời sai :((");
				}
				setQuestion();
				setPoint(bool_check);
			}
		});

		ChoiNgayPn.btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(ChoiNgayPn.btnSTTCauHoi.getText()) < 15) {
					if (JOptionPane.showConfirmDialog(null,
							"Bạn chưa hoàn thành hết câu hỏi. Chắc chắn muốn thoát khỏi trò chơi ?") == JOptionPane.YES_OPTION) {
						gameOver();
					}
				}
				else {
					gameOver();
				}
			}
		});

		JButton btnComeback = new JButton("QUAY LẠI");
		btnComeback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Sound.sound.playSoundOneClick("src//Sound//click-btn.wav");

				HomeFr.tabbedPane.remove(HomeFr.tabbedPane.getSelectedComponent());
				HomeFr.tabbedPane.addTab("Giới Thiệu", null, mainPn);
				HomeFr.tabbedPane.setSelectedComponent(mainPn);

			}
		});
		btnComeback.setFont(new Font("Monospaced", Font.BOLD, 24));
		btnComeback.setBackground(Color.WHITE);
		btnComeback.setBounds(247, 186, 157, 70);
		panel.add(btnComeback);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(78, 479, 1100, 282);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLutChi = new JLabel("LUẬT CHƠI");
		lblLutChi.setForeground(Color.DARK_GRAY);
		lblLutChi.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLutChi.setBackground(Color.BLACK);
		lblLutChi.setBounds(454, 10, 146, 70);
		panel_1.add(lblLutChi);

		JLabel lblNewLabel_1 = new JLabel("Với mỗi lượt chơi Puzze Game, bạn cần vượt qua 15 câu hỏi.");
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel_1.setBounds(67, 76, 725, 49);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Bạn có tổng cộng 60s suy nghĩ. ");
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(67, 119, 725, 49);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel(
				"Mỗi câu trả lời đúng cộng vào bảng điểm tích lũy 10đ, mỗi câu trả lời sai trừ đi 5đ.");
		lblNewLabel_1_2.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(67, 161, 987, 49);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Hãy chinh phục giải thưởng lớn cùng Puzzle Game nhé!");
		lblNewLabel_1_3.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(67, 208, 725, 49);
		panel_1.add(lblNewLabel_1_3);

	}

	public void resetQuestion() {
		index = 0;
		point = 0;
		ChoiNgayPn.btnScore.setText("0");
		choiNgayPn.btnSTTCauHoi.setText("1");
		choiNgayPn.txtQuestionText.setText(question_tmp[0][0]);
		choiNgayPn.btnOpt1.setText(question_tmp[0][1]);
		choiNgayPn.btnOpt2.setText(question_tmp[0][2]);
		choiNgayPn.btnOpt3.setText(question_tmp[0][3]);
		choiNgayPn.btnOpt4.setText(question_tmp[0][4]);
	}

	public void setQuestion() {
		index++;
		if (index == 15) {
			gameOver();
		} else if (index <= 14) {
			choiNgayPn.btnSTTCauHoi.setText("" + (index + 1));
			choiNgayPn.txtQuestionText.setText(question_tmp[index][0]);
			choiNgayPn.btnOpt1.setText(question_tmp[index][1]);
			choiNgayPn.btnOpt2.setText(question_tmp[index][2]);
			choiNgayPn.btnOpt3.setText(question_tmp[index][3]);
			choiNgayPn.btnOpt4.setText(question_tmp[index][4]);
		}
	}

	public void setPoint(int bool_check) {
		if (bool_check == 0) {
			if (point != 0) {
				point -= 5;
			}
		} else {
			point += 10;
		}
		ChoiNgayPn.btnScore.setText(String.valueOf(point));
	}

	public void gameOver() {

		DangNhapDlg.sound.closeSound();

		DangNhapDlg.sound.playSoundOneClick("src//Sound//click-endgame.wav");

		int time = 0;
		
		if (!ChoiNgayPn.btnTime.getText().isEmpty()) {
			
			time = Integer.parseInt(ChoiNgayPn.btnTime.getText());
			
		}
		GameOver gameOver = new GameOver();
		GameOver.btnScore.setText(Integer.toString(point));
		GameOver.btnTime.setText(Integer.toString(60 - time) + "s");

		HomeFr.tabbedPane.remove(HomeFr.tabbedPane.getSelectedComponent());
		HomeFr.tabbedPane.addTab("Game over", null, gameOver);
		HomeFr.tabbedPane.setSelectedComponent(gameOver);
	}
}
