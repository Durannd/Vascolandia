package view;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;

public class LoginView extends JFrame {
    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setResizable(false);

        init();
        setVisible(true);
    }
    public void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(10, 10, 100, 30);
        loginLabel.setAlignmentX(CENTER_ALIGNMENT);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setOpaque(true);
        loginLabel.setBackground(Color.lightGray);

        panel.add(loginLabel);
        panel.setBackground(Color.white);
        add(panel);



    }
}
