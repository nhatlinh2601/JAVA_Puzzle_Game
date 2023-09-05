package GUIServer;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DatabaseHelper;
import DTO.QuestionDTO;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class QuestionManage extends JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    private String[] header = new String[]{"ID", "Nội dung câu hỏi", "Đáp án chính xác", "Lựa chọn 1", "Lựa chọn 2", "Lựa chọn 3", "Lựa chọn 4"};
    private JTable table;
    private GioiThieuPN gioiThieuPN;
	private PreparedStatement pstmt;

    /**
     * Create the panel.
     */
    public QuestionManage() {

        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 274, 806);
        panel_1.setBackground(Color.WHITE);
        add(panel_1);
        panel_1.setLayout(null);

        JButton btnAddQue = new JButton("THÊM CÂU HỎI");
        btnAddQue.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		quesManage();
        		Question.btnUpdate.setEnabled(false);
        		
        	}
        });
        btnAddQue.setFont(new Font("Consolas", Font.BOLD, 20));
        btnAddQue.setBackground(SystemColor.inactiveCaption);
        btnAddQue.setBounds(23, 103, 231, 71);
        panel_1.add(btnAddQue);

        JButton btnDeleteQue = new JButton("XÓA");
        btnDeleteQue.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					xuLyXoaQue();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        btnDeleteQue.setFont(new Font("Consolas", Font.BOLD, 20));
        btnDeleteQue.setBackground(SystemColor.inactiveCaption);
        btnDeleteQue.setBounds(23, 266, 231, 71);
        panel_1.add(btnDeleteQue);

        JButton btnUpdateQue = new JButton("CHỈNH SỬA CÂU HỎI");
        btnUpdateQue.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int row = table.getSelectedRow();
                if (row != -1) {
                    Object id=table.getValueAt(row, 0) ;
                    Object text=table.getValueAt(row, 1) ;
                    Object correct_ans=table.getValueAt(row, 2) ;
                    Object opt1=table.getValueAt(row, 3) ;
                    Object opt2=table.getValueAt(row, 4) ;
                    Object opt3=table.getValueAt(row, 5) ;
                    Object opt4=table.getValueAt(row, 6) ;
                    
                    quesManage();
                    Question.btnSave.setEnabled(false);
                    Question.txtTextQue.setText(String.valueOf(text));
                    Question.txtCorrectAns.setText(String.valueOf(correct_ans));
                    Question.txtOpt1.setText(String.valueOf(opt1));
                    Question.txtOpt2.setText(String.valueOf(opt2));
                    Question.txtOpt3.setText(String.valueOf(opt3));
                    Question.txtOpt4.setText(String.valueOf(opt4));
                    
                    
                }else JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống. Vui lòng chọn dòng để update!!");
        		
        	}
        });
        btnUpdateQue.setFont(new Font("Consolas", Font.BOLD, 20));
        btnUpdateQue.setBackground(SystemColor.inactiveCaption);
        btnUpdateQue.setBounds(23, 421, 238, 71);
        panel_1.add(btnUpdateQue);
        JPanel panel2 = new JPanel();
        panel2.setBounds(284, 0, 978, 806);
        panel2.setBackground(new Color(232, 242, 240));
        add(panel2);
        panel2.setLayout(null);

        table = new JTable();
        table.setForeground(Color.DARK_GRAY);
        table.setFont(new Font("Tahoma", Font.BOLD, 20));
        table.setBackground(new Color(211, 225, 237));
        table.setFont(fontTbl);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 95, 862, 644);
        panel2.add(scrollPane);
        table.setModel(model);

        JButton btnQuayLi = new JButton("QUAY LẠI");
        btnQuayLi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gioiThieuPN = new GioiThieuPN();
                AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
                AdminFr.tabbedPane.addTab("Quản lý câu hỏi", null, gioiThieuPN);
                AdminFr.tabbedPane.setSelectedComponent(gioiThieuPN);

            }
        });
        btnQuayLi.setFont(new Font("Consolas", Font.BOLD, 20));
        btnQuayLi.setBackground(SystemColor.inactiveCaption);
        btnQuayLi.setBounds(10, 17, 178, 55);
        panel2.add(btnQuayLi);

        JLabel lblNewLabel = new JLabel("DANH SÁCH");
        lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
        lblNewLabel.setBounds(443, 23, 184, 34);
        panel2.add(lblNewLabel);
        model.setColumnIdentifiers(header);
        loadDataQuestionTable();

    }
    
    public void quesManage() {
    	
    	Question question=new Question();
    	
    	AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
        AdminFr.tabbedPane.addTab("Chỉnh sửa câu hỏi", null, question);
        AdminFr.tabbedPane.setSelectedComponent(question);

    }
    
    public void loadDataQuestionTable() {
        try {
            int cnt=1;
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from ta_que_question";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                		String.valueOf(rs.getInt("i_id")),
                		rs.getString("t_text"),
                        rs.getString("t_correct_answer"),
                        rs.getString("t_opt1"),
                        rs.getString("t_opt2"),
                        rs.getString("t_opt3"),
                        rs.getString("t_opt4")
                        
                        };
                        
                model.addRow(row);
            }
            model.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean DeleteQue(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            String sql = "delete from ta_que_question where i_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate() > 0;
    }
    
    public void xuLyXoaQue() throws SQLException {
        int row = table.getSelectedRow();

        if (row != -1) {
        	
            Object id=table.getValueAt(row, 0) ;
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?") == JOptionPane.YES_OPTION) {
                if (DeleteQue(Integer.parseInt(String.valueOf(id)))) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    loadDataQuestionTable();
                } else JOptionPane.showMessageDialog(null, "Xóa thất bại!");
            }
        } else JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống. Vui lòng chọn dòng để xóa!!");
    }
    
   
    public void loadAque() {
    	int row = table.getSelectedRow();
        if (row != -1) {
            Object id=table.getValueAt(row, 0) ;
            Object text=table.getValueAt(row, 1) ;
            Object correct_ans=table.getValueAt(row, 2) ;
            Object opt1=table.getValueAt(row, 3) ;
            Object opt2=table.getValueAt(row, 4) ;
            Object opt3=table.getValueAt(row, 5) ;
            Object opt4=table.getValueAt(row, 6) ;
            
            
            Question.txtTextQue.setText(String.valueOf(text));
            Question.txtCorrectAns.setText(String.valueOf(correct_ans));
            Question.txtOpt1.setText(String.valueOf(opt1));
            Question.txtOpt2.setText(String.valueOf(opt2));
            Question.txtOpt3.setText(String.valueOf(opt3));
            Question.txtOpt4.setText(String.valueOf(opt4));
            
        }else JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống. Vui lòng chọn dòng để update!!");
	}
    
  
}
    

