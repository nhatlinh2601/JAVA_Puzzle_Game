package GUIServer;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAO.DatabaseHelper;
import DTO.QuestionDTO;
import DTO.TaiKhoan;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class User extends JPanel {
	public static JTextField txtUsername;
	public static JTextField txtFullname;
	public static JTextField txtPassword;
	public static JTextField txtEmail;
	public static JTextField txtScore;
	private PreparedStatement pstmt;

	public static JButton btnUpdate;
	public static JTextField txtNo;
	public static JTextField txtId;

	/**
	 * Create the panel.
	 */
	public User() {
		setBackground(SystemColor.text);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(22, 20, 1122, 723);
		add(panel);
		panel.setLayout(null);
		
		 btnUpdate = new JButton();
		 
		
		
		btnUpdate.setText("CẬP NHẬT");
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Consolas", Font.BOLD, 29));
		btnUpdate.setBackground(new Color(245, 255, 250));
		btnUpdate.setBounds(845, 291, 210, 61);
		panel.add(btnUpdate);
		
		JLabel lblpn = new JLabel("USERNAME");
		lblpn.setBounds(25, 266, 112, 61);
		panel.add(lblpn);
		lblpn.setForeground(Color.DARK_GRAY);
		lblpn.setFont(new Font("Consolas", Font.BOLD, 25));
		lblpn.setBackground(Color.BLACK);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(280, 266, 449, 61);
		panel.add(txtUsername);
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtUsername.setColumns(10);
		
		txtFullname = new JTextField();
		txtFullname.setBounds(280, 355, 449, 61);
		panel.add(txtFullname);
		txtFullname.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtFullname.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(280, 448, 449, 61);
		panel.add(txtPassword);
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(280, 539, 449, 61);
		panel.add(txtEmail);
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		
		txtScore = new JTextField();
		txtScore.setBounds(280, 625, 449, 61);
		panel.add(txtScore);
		txtScore.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtScore.setColumns(10);
		
		JLabel lblLaChnA = new JLabel("FULLNAME");
		lblLaChnA.setBounds(25, 360, 217, 61);
		panel.add(lblLaChnA);
		lblLaChnA.setForeground(Color.DARK_GRAY);
		lblLaChnA.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLaChnA.setBackground(Color.BLACK);
		
		JLabel lblLaChnB = new JLabel("PASSWORD");
		lblLaChnB.setBounds(25, 453, 217, 61);
		panel.add(lblLaChnB);
		lblLaChnB.setForeground(Color.DARK_GRAY);
		lblLaChnB.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLaChnB.setBackground(Color.BLACK);
		
		JLabel lblLaChnB_1 = new JLabel("EMAIL");
		lblLaChnB_1.setBounds(25, 544, 217, 61);
		panel.add(lblLaChnB_1);
		lblLaChnB_1.setForeground(Color.DARK_GRAY);
		lblLaChnB_1.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLaChnB_1.setBackground(Color.BLACK);
		
		JLabel lblLaChnB_2 = new JLabel("TỔNG ĐIỂM");
		lblLaChnB_2.setBounds(25, 630, 217, 61);
		panel.add(lblLaChnB_2);
		lblLaChnB_2.setForeground(Color.DARK_GRAY);
		lblLaChnB_2.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLaChnB_2.setBackground(Color.BLACK);
		
		JButton btnComeback = new JButton();
		btnComeback.setBounds(845, 419, 210, 61);
		panel.add(btnComeback);
		btnComeback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserManage userManage=new UserManage();
		    	AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
		        AdminFr.tabbedPane.addTab("Quản lý người dùng", null, userManage);
		        AdminFr.tabbedPane.setSelectedComponent(userManage);

			}
		});
		btnComeback.setText("QUAY LẠI");
		btnComeback.setForeground(Color.BLACK);
		btnComeback.setFont(new Font("Consolas", Font.BOLD, 29));
		btnComeback.setBackground(new Color(245, 255, 250));
		 
		 JButton btnDelete = new JButton();
		 btnDelete.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		try {
					xuLyXoaUser();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 		
		 	}
		 });
		 btnDelete.setText("XÓA");
		 btnDelete.setForeground(Color.BLACK);
		 btnDelete.setFont(new Font("Consolas", Font.BOLD, 29));
		 btnDelete.setBackground(new Color(245, 255, 250));
		 btnDelete.setBounds(845, 168, 210, 61);
		 panel.add(btnDelete);
		 
		 JLabel lblThngTinNgi = new JLabel("THÔNG TIN NGƯỜI CHƠI");
		 lblThngTinNgi.setForeground(Color.DARK_GRAY);
		 lblThngTinNgi.setFont(new Font("Consolas", Font.BOLD, 25));
		 lblThngTinNgi.setBackground(Color.BLACK);
		 lblThngTinNgi.setBounds(464, 22, 381, 61);
		 panel.add(lblThngTinNgi);
		 
		 txtNo = new JTextField();
		 txtNo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		 txtNo.setColumns(10);
		 txtNo.setBounds(280, 79, 449, 61);
		 panel.add(txtNo);
		 
		 JLabel lblThHng = new JLabel("THỨ HẠNG");
		 lblThHng.setForeground(Color.DARK_GRAY);
		 lblThHng.setFont(new Font("Consolas", Font.BOLD, 25));
		 lblThHng.setBackground(Color.BLACK);
		 lblThHng.setBounds(25, 84, 139, 61);
		 panel.add(lblThHng);
		 
		 txtId = new JTextField();
		 txtId.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		 txtId.setColumns(10);
		 txtId.setBounds(280, 168, 449, 61);
		 panel.add(txtId);
		 
		 JLabel lblId = new JLabel("ID");
		 lblId.setForeground(Color.DARK_GRAY);
		 lblId.setFont(new Font("Consolas", Font.BOLD, 25));
		 lblId.setBackground(Color.BLACK);
		 lblId.setBounds(25, 169, 139, 61);
		 panel.add(lblId);
		 
		 btnUpdate.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		int id=Integer.parseInt(txtId.getText());
			 		String username=txtUsername.getText().trim();
			 		String fullname=txtFullname.getText().trim();
			 		String password=txtPassword.getText().trim();
			 		String email=txtEmail.getText().trim();
			 		int score=Integer.parseInt(txtScore.getText());
			 		TaiKhoan taiKhoan =new TaiKhoan(username, password, email, fullname, score, id);
			 		try {
						xuLyUpdateUser(taiKhoan);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 		
			 	}
			 });

	}
	
	
	
	public boolean updateUser( TaiKhoan taiKhoan) throws SQLException {
        Connection conn = null;
        try {

            conn = DatabaseHelper.getConnection();
            String sql = "update ta_aut_user set t_username= ?, t_fullname =?,t_password =? ,t_email =? ,i_score=? where i_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, taiKhoan.getUsername());
            pstmt.setString(2, taiKhoan.getFullname());
            pstmt.setString(3, taiKhoan.getPassword());
            pstmt.setString(4, taiKhoan.getEmail());
            pstmt.setInt(5, taiKhoan.getScore());
            pstmt.setInt(6,taiKhoan.getId());
 
           

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate() > 0;
    }
	
	public void xuLyUpdateUser(TaiKhoan taiKhoan) throws SQLException {
        try {
           
            StringBuilder errorss = new StringBuilder();
            if (taiKhoan.getUsername().equalsIgnoreCase("")) {
                errorss.append("username không được để trống\n");
            } 
            if (taiKhoan.getFullname().equalsIgnoreCase("")) {
                errorss.append("fullanme không được để trống\n");
            }
            if (taiKhoan.getPassword().equalsIgnoreCase("")) {
                errorss.append("password không được để trống\n");
            }
            if (taiKhoan.getEmail().equalsIgnoreCase("")) {
                errorss.append("email không được để trống\n");
            } 
            if (String.valueOf(taiKhoan.getScore()).equalsIgnoreCase("")) {
                errorss.append("Score không được để trống\n");
            }
            

            if (errorss.isEmpty()) {
                
                    if (updateUser(taiKhoan)) {
                    	
                        JOptionPane.showMessageDialog(null, "Update thành công");
                        UserManage userManage=new UserManage();
        		    	AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
        		        AdminFr.tabbedPane.addTab("Quản lý người dùng", null, userManage);
        		        AdminFr.tabbedPane.setSelectedComponent(userManage);
        		        userManage.loadDataUserTable();
						
                    } else JOptionPane.showMessageDialog(null, "Update thất bại");
             
            } else JOptionPane.showMessageDialog(null, errorss.toString());
        } catch (Exception e) {
			e.printStackTrace();
		}
	
    }
	
	
	  public boolean DeleteUser(int id) throws SQLException {
	        Connection conn = null;
	        try {
	            conn = DatabaseHelper.getConnection();
	            String sql = "delete from ta_aut_user where i_id=?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, id);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return pstmt.executeUpdate() > 0;
	    }
	  
	  

	  
	  public void xuLyXoaUser() throws NumberFormatException, SQLException {
		  if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?")==JOptionPane.YES_OPTION) {
			  if (DeleteUser(Integer.parseInt(txtId.getText()))) {
				JOptionPane.showMessageDialog(null, "Xóa thành công");
				UserManage userManage=new UserManage();
		    	AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
		        AdminFr.tabbedPane.addTab("Quản lý người dùng", null, userManage);
		        AdminFr.tabbedPane.setSelectedComponent(userManage);
		        userManage.loadDataUserTable();
				
			} else JOptionPane.showMessageDialog(null, "Xóa thất bại");
		  }
	}
	  
	 
}

	

