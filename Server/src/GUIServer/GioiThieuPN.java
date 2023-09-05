package GUIServer;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GioiThieuPN extends JPanel {

    private QuestionManage questionManage = new QuestionManage();

    /**
     * Create the panel.
     */
    public GioiThieuPN() {
        setBackground(new Color(245, 255, 250));
        setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(GioiThieuPN.class.getResource("/Images/admin.jpg")));
        lblNewLabel.setBounds(442, 335, 349, 354);
        add(lblNewLabel);

        JLabel lbTitle = new JLabel("PUZZE GAME");
        lbTitle.setForeground(new Color(0, 0, 139));
        lbTitle.setFont(new Font("Consolas", Font.BOLD, 50));
        lbTitle.setBounds(424, 88, 369, 66);
        add(lbTitle);

        JLabel lbSlogan = new JLabel("Chinh phục trí tuệ, khám phá vô tận");
        lbSlogan.setForeground(new Color(43, 105, 181));
        lbSlogan.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 30));
        lbSlogan.setBounds(255, 199, 686, 34);
        add(lbSlogan);

    }
}
