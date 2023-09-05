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

public class Question extends JPanel {
	public static JTextField txtCorrectAns;
	public static JTextField txtOpt1;
	public static JTextField txtOpt2;
	public static JTextField txtOpt3;
	public static JTextField txtOpt4;
	public static JTextArea txtTextQue;
	private PreparedStatement pstmt;
	private final Action action = new SwingAction();
	public static JButton btnSave;
	public static JButton btnUpdate;

	/**
	 * Create the panel.
	 */
	public Question() {
		setBackground(SystemColor.text);
		setLayout(null);
		
		JLabel lblNiDungCu = new JLabel("NỘI DUNG CÂU HỎI");
		lblNiDungCu.setForeground(Color.DARK_GRAY);
		lblNiDungCu.setFont(new Font("Consolas", Font.BOLD, 25));
		lblNiDungCu.setBackground(Color.BLACK);
		lblNiDungCu.setBounds(41, 75, 259, 70);
		add(lblNiDungCu);
		
		 txtTextQue = new JTextArea();
		txtTextQue.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtTextQue.setBounds(310, 31, 734, 143);
		add(txtTextQue);
		
		JLabel lblpn = new JLabel("ĐÁP ÁN");
		lblpn.setForeground(Color.DARK_GRAY);
		lblpn.setFont(new Font("Consolas", Font.BOLD, 25));
		lblpn.setBackground(Color.BLACK);
		lblpn.setBounds(43, 231, 112, 61);
		add(lblpn);
		
		txtCorrectAns = new JTextField();
		txtCorrectAns.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtCorrectAns.setColumns(10);
		txtCorrectAns.setBounds(310, 226, 449, 61);
		add(txtCorrectAns);
		
		JLabel lblLaChnA = new JLabel("LỰA CHỌN A");
		lblLaChnA.setForeground(Color.DARK_GRAY);
		lblLaChnA.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLaChnA.setBackground(Color.BLACK);
		lblLaChnA.setBounds(41, 337, 217, 61);
		add(lblLaChnA);
		
		JLabel lblLaChnB = new JLabel("LỰA CHỌN B");
		lblLaChnB.setForeground(Color.DARK_GRAY);
		lblLaChnB.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLaChnB.setBackground(Color.BLACK);
		lblLaChnB.setBounds(41, 439, 217, 61);
		add(lblLaChnB);
		
		JLabel lblLaChnB_1 = new JLabel("LỰA CHỌN C");
		lblLaChnB_1.setForeground(Color.DARK_GRAY);
		lblLaChnB_1.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLaChnB_1.setBackground(Color.BLACK);
		lblLaChnB_1.setBounds(41, 543, 217, 61);
		add(lblLaChnB_1);
		
		JLabel lblLaChnB_2 = new JLabel("LỰA CHỌN D");
		lblLaChnB_2.setForeground(Color.DARK_GRAY);
		lblLaChnB_2.setFont(new Font("Consolas", Font.BOLD, 25));
		lblLaChnB_2.setBackground(Color.BLACK);
		lblLaChnB_2.setBounds(41, 644, 217, 61);
		add(lblLaChnB_2);
		
		JButton btnComeback = new JButton();
		btnComeback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				QuestionManage questionManage = new QuestionManage();
		    	AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
		        AdminFr.tabbedPane.addTab("Quản lý câu hỏi", null, questionManage);
		        AdminFr.tabbedPane.setSelectedComponent(questionManage);

			}
		});
		btnComeback.setText("QUAY LẠI");
		btnComeback.setForeground(Color.BLACK);
		btnComeback.setFont(new Font("Consolas", Font.BOLD, 29));
		btnComeback.setBackground(new Color(245, 255, 250));
		btnComeback.setBounds(873, 542, 210, 61);
		add(btnComeback);
		
		 btnSave = new JButton();
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text=txtTextQue.getText().trim();
				String correct_ans=txtCorrectAns.getText().trim();
				String opt1=txtOpt1.getText().trim();
				String opt2=txtOpt2.getText().trim();
				String opt3=txtOpt3.getText().trim();
				String opt4=txtOpt4.getText().trim();
				QuestionDTO ques =new QuestionDTO(text, correct_ans, opt1, opt2, opt3, opt4);
				
				
				try {
					xuLyThemQue(ques);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSave.setText("LƯU");
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Consolas", Font.BOLD, 30));
		btnSave.setBackground(new Color(245, 255, 250));
		btnSave.setBounds(873, 645, 210, 56);
		add(btnSave);
		
		txtOpt1 = new JTextField();
		txtOpt1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtOpt1.setColumns(10);
		txtOpt1.setBounds(310, 332, 449, 61);
		add(txtOpt1);
		
		txtOpt2 = new JTextField();
		txtOpt2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtOpt2.setColumns(10);
		txtOpt2.setBounds(310, 434, 449, 61);
		add(txtOpt2);
		
		txtOpt3 = new JTextField();
		txtOpt3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtOpt3.setColumns(10);
		txtOpt3.setBounds(310, 538, 449, 61);
		add(txtOpt3);
		
		txtOpt4 = new JTextField();
		txtOpt4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtOpt4.setColumns(10);
		txtOpt4.setBounds(310, 639, 449, 61);
		add(txtOpt4);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(22, 20, 1122, 723);
		add(panel);
		panel.setLayout(null);
		
		 btnUpdate = new JButton();
		btnUpdate.setAction(action);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text=txtTextQue.getText().trim();
				String correct_ans=txtCorrectAns.getText().trim();
				String opt1=txtOpt1.getText().trim();
				String opt2=txtOpt2.getText().trim();
				String opt3=txtOpt3.getText().trim();
				String opt4=txtOpt4.getText().trim();
				int id=loadId(text);
				QuestionDTO ques =new QuestionDTO(text, correct_ans, opt1, opt2, opt3, opt4, id);
			
				
				try {
					xuLyUpdateQue(ques);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setText("CẬP NHẬT");
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Consolas", Font.BOLD, 29));
		btnUpdate.setBackground(new Color(245, 255, 250));
		btnUpdate.setBounds(852, 419, 210, 61);
		panel.add(btnUpdate);

	}
	
	public boolean themQue( QuestionDTO question) throws SQLException {
        Connection conn = null;
        try {

            conn = DatabaseHelper.getConnection();
            String sql = "insert into ta_que_question(t_text,t_correct_answer,t_opt1,t_opt2,t_opt3,t_opt4) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, question.getText());
            pstmt.setString(2, question.getCorrect_ans());
            pstmt.setString(3, question.getOpt1());
            pstmt.setString(4, question.getOpt2());
            pstmt.setString(5, question.getOpt3());
            pstmt.setString(6,question.getOpt4());
           

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate() > 0;
    }
	
	public void xuLyThemQue(QuestionDTO question) throws SQLException {
        try {
           
            StringBuilder errorss = new StringBuilder();
            if (question.getText().equalsIgnoreCase("")) {
                errorss.append("Câu hỏi không được để trống\n");
            } 
            if (question.getCorrect_ans().equalsIgnoreCase("")) {
                errorss.append("Đáp án không được để trống\n");
            }
            if (question.getOpt1().equalsIgnoreCase("")) {
                errorss.append("Lựa chọn 1 không được để trống\n");
            }
            if (question.getOpt2().equalsIgnoreCase("")) {
                errorss.append("Lựa chọn 2 không được để trống\n");
            } 
            if (question.getOpt3().equalsIgnoreCase("")) {
                errorss.append("Lựa chọn 3 không được để trống\n");
            }
            if (question.getOpt4().equalsIgnoreCase("")) {
                errorss.append("Lựa chọn 4 không được để trống\n");
            }
            System.out.println(question.getText() + "hahhaaaa");

            if (errorss.isEmpty()) {
            	
            	/*if (!checkContainQue(question.getText())) {*/
                          if (themQue(question)) {
                    	
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                        if (JOptionPane.showConfirmDialog(null, "Bạn muốn thêm tiếp câu hỏi ?")==JOptionPane.NO_OPTION) {
                        	QuestionManage questionManage = new QuestionManage();
            		    	AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
            		        AdminFr.tabbedPane.addTab("Quản lý câu hỏi", null, questionManage);
            		        AdminFr.tabbedPane.setSelectedComponent(questionManage);
						} else {
							txtTextQue.setText("");
							txtCorrectAns.setText("");
							txtOpt1.setText("");
							txtOpt2.setText("");
							txtOpt3.setText("");							
							txtOpt4.setText("");
						}
                    } else JOptionPane.showMessageDialog(null, "Thêm thất bại");
					/*
					 * } else { JOptionPane.showMessageDialog(btnSave, "Câu hỏi đã tồn tại"); }
					 */
                
                    
             
            } else JOptionPane.showMessageDialog(null, errorss.toString());
        } catch (Exception e) {
			e.printStackTrace();
		}
	
    }
	
	public boolean updateQue( QuestionDTO question) throws SQLException {
        Connection conn = null;
        try {

            conn = DatabaseHelper.getConnection();
            String sql = "update ta_que_question set t_text= ?, t_correct_answer =?,t_opt1 =? ,t_opt2 =? ,t_opt3=?,t_opt4=? where i_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, question.getText());
            pstmt.setString(2, question.getCorrect_ans());
            pstmt.setString(3, question.getOpt1());
            pstmt.setString(4, question.getOpt2());
            pstmt.setString(5, question.getOpt3());
            pstmt.setString(6,question.getOpt4());
            pstmt.setInt(7, question.getId());
           

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pstmt.executeUpdate() > 0;
    }
	
	public void xuLyUpdateQue(QuestionDTO question) throws SQLException {
        try {
           
            StringBuilder errorss = new StringBuilder();
            if (question.getText().equalsIgnoreCase("")) {
                errorss.append("Câu hỏi không được để trống\n");
            } 
            if (question.getCorrect_ans().equalsIgnoreCase("")) {
                errorss.append("Đáp án không được để trống\n");
            }
            if (question.getOpt1().equalsIgnoreCase("")) {
                errorss.append("Lựa chọn 1 không được để trống\n");
            }
            if (question.getOpt2().equalsIgnoreCase("")) {
                errorss.append("Lựa chọn 2 không được để trống\n");
            } 
            if (question.getOpt3().equalsIgnoreCase("")) {
                errorss.append("Lựa chọn 3 không được để trống\n");
            }
            if (question.getOpt4().equalsIgnoreCase("")) {
                errorss.append("Lựa chọn 4 không được để trống\n");
            }

            if (errorss.isEmpty()) {
                
                    if (updateQue(question)) {
                    	
                        JOptionPane.showMessageDialog(null, "Update thành công");
                        	QuestionManage questionManage = new QuestionManage();
            		    	AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
            		        AdminFr.tabbedPane.addTab("Quản lý câu hỏi", null, questionManage);
            		        AdminFr.tabbedPane.setSelectedComponent(questionManage);
						
                    } else JOptionPane.showMessageDialog(null, "Update thất bại");
             
            } else JOptionPane.showMessageDialog(null, errorss.toString());
        } catch (Exception e) {
			e.printStackTrace();
		}
	
    }
	
	public int loadId(String text) {
		 Connection conn = null;
		 int id = 0;
	        try {
	        	
	            conn = DatabaseHelper.getConnection();
	            String sql = "select i_id from ta_que_question where t_text=?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, text);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                id= rs.getInt("i_id");
	            }

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
			return id;
		
		
	}
	
	  public ArrayList<QuestionDTO> getTextQue() throws Exception {
	        ArrayList<QuestionDTO> queList = new ArrayList<>();
	        Connection conn = null;
	        try {
	            conn = DatabaseHelper.getConnection();
	            String sql = "select t_text from ta_que_question";
	            pstmt= conn.prepareStatement(sql);
	            ResultSet rs = pstmt.executeQuery(sql);
	            while (rs.next()) {
	                QuestionDTO question=new QuestionDTO();
	                question.setText(rs.getString("t_text"));
	                queList.add(question);
	            }

	            return queList;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
		/*
		 * public boolean checkContainQue(String text) throws Exception { boolean flag =
		 * false; for (QuestionDTO que : getTextQue()) {
		 * System.out.println(que.getText()); if (que.getText().equalsIgnoreCase(text))
		 * { flag = true; } }
		 * 
		 * return false;
		 * 
		 * }
		 */
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	
}
