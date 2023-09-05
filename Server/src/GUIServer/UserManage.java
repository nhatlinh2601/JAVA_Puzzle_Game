package GUIServer;

import javax.swing.JPanel;
import java.awt.Color;



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

public class UserManage extends JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    private String[] header = new String[]{"Thứ hạng","ID","Username", "Fullname", "Password", "Email", "Tổng điểm"};
    private JTable table;
    private GioiThieuPN gioiThieuPN;
	private PreparedStatement pstmt;

    /**
     * Create the panel.
     */
    public UserManage() {

        Font fontTbl = new Font("SF Mono", Font.PLAIN, 16);
        setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 274, 806);
        panel_1.setBackground(Color.WHITE);
        add(panel_1);
        panel_1.setLayout(null);

        JButton btnXem = new JButton("XEM THÔNG TIN");
        btnXem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int row = table.getSelectedRow();
                if (row != -1) {
                	
                	Object no=table.getValueAt(row, 0) ;
                	Object stt=table.getValueAt(row, 1);
                    Object username=table.getValueAt(row, 2) ;
                    Object fullname=table.getValueAt(row, 3) ;
                    Object password=table.getValueAt(row, 4) ;
                    Object email=table.getValueAt(row, 5) ;
                    Object score=table.getValueAt(row, 6) ;
                    
                    User user=new User();
            		AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
                    AdminFr.tabbedPane.addTab("Xem thông tin", null, user);
                    AdminFr.tabbedPane.setSelectedComponent(user);
                    
                    User.txtNo.setText(String.valueOf(no));
                    User.txtUsername.setText(String.valueOf(username));
                    User.txtFullname.setText(String.valueOf(fullname));
                    User.txtId.setText(String.valueOf(stt));
                    User.txtPassword.setText(String.valueOf(password));
                    User.txtEmail.setText(String.valueOf(email));
                    User.txtScore.setText(String.valueOf(score));
                   
                }else JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống. Vui lòng chọn dòng để xem thong tin !!");
        		
        	}
        });
        btnXem.setFont(new Font("Consolas", Font.BOLD, 20));
        btnXem.setBackground(SystemColor.inactiveCaption);
        btnXem.setBounds(21, 347, 231, 71);
        panel_1.add(btnXem);
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

        JLabel lblNewLabel = new JLabel("DANH SÁCH NGƯỜI DÙNG");
        lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
        lblNewLabel.setBounds(353, 38, 415, 34);
        panel2.add(lblNewLabel);
        model.setColumnIdentifiers(header);
        loadDataUserTable();

    }
    
    public void quesManage() {
    	
    	Question question=new Question();
    	
    	AdminFr.tabbedPane.remove(AdminFr.tabbedPane.getSelectedComponent());
        AdminFr.tabbedPane.addTab("Chỉnh sửa câu hỏi", null, question);
        AdminFr.tabbedPane.setSelectedComponent(question);

    }
    
    public void loadDataUserTable() {
        try {
            int cnt=1;
            Connection conn = DatabaseHelper.getConnection();
            String sql = "select * from ta_aut_user";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                		String.valueOf(cnt++),
                		String.valueOf(rs.getInt("i_id")),
                		rs.getString("t_username"),
                        rs.getString("t_fullname"),
                        rs.getString("t_password"),
                        rs.getString("t_email"),
                        String.valueOf(rs.getInt("i_score"))
                        
                        };
                        
                model.addRow(row);
            }
            model.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
    
   
    public void loadAuser() {
    	int row = table.getSelectedRow();
        if (row != -1) {
        	Object stt=table.getValueAt(row, 1);
            Object no=table.getValueAt(row, 0) ;
            Object username=table.getValueAt(row, 2) ;
            Object fullname=table.getValueAt(row, 3) ;
            Object password=table.getValueAt(row, 4) ;
            Object email=table.getValueAt(row, 5) ;
            Object score=table.getValueAt(row, 6) ;
            
            User.txtNo.setText(String.valueOf(no));
            User.txtUsername.setText(String.valueOf(username));
            User.txtFullname.setText(String.valueOf(fullname));
            User.txtId.setText(String.valueOf(stt));
            User.txtPassword.setText(String.valueOf(password));
            User.txtEmail.setText(String.valueOf(email));
            User.txtScore.setText(String.valueOf(score));
       
            
        }else JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống. Vui lòng chọn dòng để xem thong tin !!");
	}
    

}
    

