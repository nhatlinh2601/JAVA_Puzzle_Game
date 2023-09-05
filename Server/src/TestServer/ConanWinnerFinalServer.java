package TestServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.midi.Soundbank;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.*;
import DTO.*;

import GUIServer.*;

public class ConanWinnerFinalServer {

	static ServerSocket ss;
	static HashMap<String, Socket> clients = new HashMap<>();
	static int s1, s2;

	public static void main(String[] args) throws Exception {
		ss = new ServerSocket(1111);

		AdminFr ad = new AdminFr();
		ad.setVisible(true);

		System.out.println("Server is running...");
		while (true) {
			try {
				Socket socket = ss.accept();

				Thread thread = new ClientHandler(socket);
				thread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class ClientHandler extends Thread {

	private final Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private TaiKhoan taiKhoan;
	private BxhDAO bxhDAO = new BxhDAO();
	private ArrayList<TaiKhoan> userList;
	public static String username;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {

			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());

			while (true) {

				String request = dis.readUTF();

				if (request.equalsIgnoreCase("DANG NHAP")) {
					username = dis.readUTF();
					String password = dis.readUTF();
					CheckTaiKhoan checkTaiKhoan = new CheckTaiKhoan();
					taiKhoan = checkTaiKhoan.loadTaiKhoan(username, password);

					if (taiKhoan != null) {

						dos.writeUTF("KIEM TRA THANH CONG");

					} else {

						dos.writeUTF("KIEM TRA KHONG THANH CONG");

						System.out.println("KHONG DUNG BAN OI ===============>");
					}
				} else if (request.equalsIgnoreCase("DANG KY")) {

					Connection connection = DatabaseHelper.getConnection();
					PreparedStatement pstm = null;

					String sql = "INSERT INTO ta_aut_user (t_username,t_fullname,t_password,t_email) VALUES (?,?,?,?)";
					pstm = connection.prepareStatement(sql);

					pstm.setString(1, dis.readUTF());
					pstm.setString(2, dis.readUTF());
					pstm.setString(3, dis.readUTF());
					pstm.setString(4, dis.readUTF());

					pstm.executeUpdate();

					String ok = "DANG KY THANH CONG";
					System.out.println("-----> " + ok);
					dos.writeUTF(ok);

				} else if (request.equalsIgnoreCase("BAT DAU")) {

					System.out.println("ban da san sang roi chu =======>>>>> ready");
					Connection con = DatabaseHelper.getConnection();
					PreparedStatement stmt = null;
					String sql = "SELECT * FROM ta_que_question";
					stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();

					dos.writeInt(15);

					while (rs.next()) {
						String t_text = rs.getString("t_text");
						String t_opt1 = rs.getString("t_opt1");
						String t_opt2 = rs.getString("t_opt2");
						String t_opt3 = rs.getString("t_opt3");
						String t_opt4 = rs.getString("t_opt4");

						dos.writeUTF(t_text);
						dos.writeUTF(t_opt1);
						dos.writeUTF(t_opt2);
						dos.writeUTF(t_opt3);
						dos.writeUTF(t_opt4);

					}

				}

				else if (request.contains("tiN-nhaN/")) {
					String tin_nhan[] = request.split("/");

					ConanWinnerFinalServer.clients.put(tin_nhan[1], socket);

					if (!tin_nhan[2].equals("chay--nen")) {
						for (String x : ConanWinnerFinalServer.clients.keySet()) {
							Socket toClientSocket = ConanWinnerFinalServer.clients.get(x);

							if (toClientSocket != null && !x.equals(tin_nhan[1])) {
								DataOutputStream toClientDos = new DataOutputStream(toClientSocket.getOutputStream());
								toClientDos.writeUTF(tin_nhan[1] + ": "+ tin_nhan[2]);
								toClientDos.flush();
							} else {
								dos.flush();
							}
						}

					}
				}

				else if (request.equals("CAN-XEM-BXH")) {
					userList = bxhDAO.getUserList();

					// so luong nguoi co trong bxh
					dos.writeInt(bxhDAO.getCnt_arr());

					for (TaiKhoan user : userList) {
						String username = user.getUsername();
						String fullname = user.getFullname();
						int score = user.getScore();

						dos.writeUTF(username);
						dos.writeUTF(fullname);
						dos.writeInt(score);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				dis.close();
				dos.close();
				socket.close();
			} catch (Exception e) {
			}
		}

	}
}
