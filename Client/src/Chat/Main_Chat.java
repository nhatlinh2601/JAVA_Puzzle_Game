package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import GUIClient.ChatPn;
import add_ip.add_ip;

public class Main_Chat {

	private String nameUser;

	public static StringBuilder messageBuilder;

	public Main_Chat() {

	}

	public Main_Chat(String nameUser) {

		this.nameUser = nameUser;

		runMain runmain = new runMain(nameUser);
		runmain.start();

		System.out.println("LOADING ... ====> " + nameUser);
	}

}

class runMain extends Thread {
	static DataOutputStream dos;
	static DataInputStream dis;
	static Socket socket;
	private String nameUser;
	private String mes;
	private Main_Chat conan = new Main_Chat();
	public static int check_nhan = 0;

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public runMain(String nameUser) {
		this.nameUser = nameUser;
	}

	public runMain() {
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	@Override
	public void run() {
		try {
			Scanner sc = new Scanner(System.in);
			socket = new Socket("localhost", 1111);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());

			// Tạo và bắt đầu thread đọc tin nhắn từ server
			doRead doread = new doRead(dis, this);
			doread.start();

			dos.writeUTF("tiN-nhaN/" + getNameUser() + "/chay--nen");

			while (true) {
				// nhap tin nhan
				synchronized (ChatPn.lock) {
					while (ChatPn.check_send == 0) {
						ChatPn.lock.wait();
					}
					mes = ChatPn.txt_mes.getText();
					dos.writeUTF("tiN-nhaN/" + getNameUser() + "/" + mes);
					dos.flush();
					if (conan.messageBuilder.length() == 0) {
						conan.messageBuilder.append(mes);

					} else {
						conan.messageBuilder.append("\n").append(mes);
					}
					ChatPn.view_mes.setText(conan.messageBuilder.toString());
					ChatPn.txt_mes.setText("");
					ChatPn.check_send = 0;
					ChatPn.lock.notifyAll();
				}
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			try {
				dis.close();
				dos.close();
				socket.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}
}

class doRead extends Thread {

	private DataInputStream dis;
	private runMain r;
	private Main_Chat conan = new Main_Chat();

	public doRead(DataInputStream dis, runMain r) {
		this.dis = dis;
		this.r = r;
	}

	@Override
	public void run() {
		try {
			conan.messageBuilder = new StringBuilder();

			while (true) {
				String mes = dis.readUTF();
				synchronized (ChatPn.lock) {
					if (conan.messageBuilder.length() == 0) {
						conan.messageBuilder.append(mes);
					} else {
						conan.messageBuilder.append("\n").append(mes);
					}
					r.setMes(conan.messageBuilder.toString());
					ChatPn.view_mes.setText(r.getMes());
					ChatPn.check_send = 0;
					ChatPn.lock.notifyAll();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
