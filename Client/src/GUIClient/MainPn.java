package GUIClient;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainPn extends JPanel {

    public MainPn() {
        setBackground(new Color(245, 255, 250));
        setLayout(null);

        JLabel lbTitle = new JLabel("PUZZE GAME");
        lbTitle.setForeground(new Color(0, 0, 139));
        lbTitle.setFont(new Font("Consolas", Font.BOLD, 50));
        lbTitle.setBounds(461, 40, 369, 66);
        add(lbTitle);

        JLabel lbImage = new JLabel("");
        lbImage.setBounds(134, 174, 1000, 661);
        add(lbImage);
        lbImage.setIcon(new ImageIcon(MainPn.class.getResource("/Images/game.jpg")));

        JLabel lbSlogan = new JLabel("Chinh phục trí tuệ, khám phá vô tận");
        lbSlogan.setForeground(new Color(43, 105, 181));
        lbSlogan.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 30));
        lbSlogan.setBounds(315, 137, 686, 34);
        add(lbSlogan);

    }
}
