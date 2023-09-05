package CountDown;

import java.util.Timer;
import java.util.TimerTask;

import GUIClient.BXHPn;
import GUIClient.ChoiNgayPn;
import GUIClient.Ready;

import javax.swing.JOptionPane;

public class countDown {

	private int COUNTDOWN_SECONDS;
	private int secondsLeft;
	public static Timer timer;
	private boolean isGameOver;

	public void startGame(int COUNTDOWN_SECONDS) {
		// Thiết lập giá trị ban đầu
		secondsLeft = COUNTDOWN_SECONDS;
		isGameOver = false;

		ChoiNgayPn.btnTime.setText(String.valueOf(secondsLeft));

		// Tạo một TimerTask để giảm số giây còn lại sau mỗi giây
		TimerTask countdownTask = new TimerTask() {
			@Override
			public void run() {
				ChoiNgayPn.btnTime.setText(String.valueOf(secondsLeft));
				secondsLeft--;

				if (secondsLeft == -1) {
					// Xử lý khi đếm ngược kết thúc
					gameOver();
				}
			}
		};

		// Tạo một Timer và lập lịch thực hiện TimerTask mỗi giây
		timer = new Timer();
		timer.scheduleAtFixedRate(countdownTask, 1000, 1000);
	}

	private void gameOver() {
		// Xử lý khi đếm ngược kết thúc (ví dụ: hiển thị thông báo, kết thúc game, vv.)
		isGameOver = true;

		Ready ready = new Ready();
		ready.gameOver();
		System.out.println("Game over!");
	}
}
