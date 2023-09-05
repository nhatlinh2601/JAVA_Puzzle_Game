package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.DatabaseHelper;

public class BxhDAO {

	private PreparedStatement pstmt;
	private ArrayList<TaiKhoan> userList;
	private Connection conn;
	private int cnt_arr;

	public int getCnt_arr() {
		return cnt_arr;
	}

	public void setCnt_arr(int cnt_arr) {
		this.cnt_arr = cnt_arr;
	}

	public ArrayList<TaiKhoan> getUserList() {
		userList = new ArrayList<>();
		int cnt = 0;
		try {
			conn = DatabaseHelper.getConnection();
			String sql = "SELECT * FROM ta_aut_user ORDER BY ta_aut_user.i_score DESC";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cnt++;
				String username = rs.getString("t_username");
				String fullname = rs.getString("t_fullname");
				String password = rs.getString("t_password");
				String email = rs.getString("t_email");
				int score = rs.getInt("i_score");
				TaiKhoan taiKhoan = new TaiKhoan(username, password, email, fullname, score);
				userList.add(taiKhoan);
			}
			setCnt_arr(cnt);
			return userList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
