package GUIServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFr extends JFrame {

    private JPanel contentPane;
    private GioiThieuPN gioiThieuPN;
    private QuestionManage questionManage;
    private UserManage userManage;
    public static JTabbedPane tabbedPane;

    /**
     * Launch the application.
     */
    public void init() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1552, 830);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(0, 0, 369, 803);
        contentPane.add(panel);
        panel.setLayout(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(367, 0, 1171, 793);
        contentPane.add(tabbedPane);

        JButton btnNewButton = new JButton("QUẢN LÝ CÂU HỎI");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                questionManage = new QuestionManage();
                tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.addTab("Quản lý câu hỏi", null, questionManage);
                tabbedPane.setSelectedComponent(questionManage);

            }
        });
        btnNewButton.setFont(new Font("Consolas", Font.BOLD, 26));
        btnNewButton.setBackground(new Color(245, 255, 250));
        btnNewButton.setBounds(21, 79, 321, 71);
        panel.add(btnNewButton);

        JButton btnQunLNgi = new JButton("QUẢN LÝ NGƯỜI CHƠI");
        btnQunLNgi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		userManage=new UserManage();
        		tabbedPane.remove(tabbedPane.getSelectedComponent());
                tabbedPane.addTab("Quản lý người chơi", null, userManage);
                tabbedPane.setSelectedComponent(userManage);
        		
        	}
        });
        btnQunLNgi.setFont(new Font("Consolas", Font.BOLD, 26));
        btnQunLNgi.setBackground(new Color(245, 255, 250));
        btnQunLNgi.setBounds(21, 228, 321, 71);
        panel.add(btnQunLNgi);

        JButton btnThngK = new JButton("THỐNG KÊ");
        btnThngK.setFont(new Font("Consolas", Font.BOLD, 26));
        btnThngK.setBackground(new Color(245, 255, 250));
        btnThngK.setBounds(21, 389, 321, 71);
        panel.add(btnThngK);

        gioiThieuPN = new GioiThieuPN();

        tabbedPane.addTab("Giới Thiệu", null, gioiThieuPN);
        tabbedPane.setSelectedComponent(gioiThieuPN);

    }

    /**
     * Create the frame.
     */
    public AdminFr() {
        init();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
