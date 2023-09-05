package DTO;


public class TaiKhoan {

    private String username, password, email,fullname;
    private int score;
    private int id;
    


	public TaiKhoan(String username, String password, String email, String fullname, int score, int id) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.score = score;
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "TaiKhoan [username=" + username + ", password=" + password + ", email=" + email + ", fullname="
				+ fullname + ", score=" + score + "]";
	}


	public TaiKhoan(String username, String password, String email, String fullname, int score) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.score = score;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public TaiKhoan(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



}
