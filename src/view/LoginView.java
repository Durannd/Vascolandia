package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private static final int MARGIN = 30;
    private static final int V_GAP = 20;
    private static final int LABEL_WIDTH = 80;
    private static final int FIELD_WIDTH = 250;
    private static final int FIELD_HEIGHT = 28;

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        init();
        setVisible(true);
    }

    private void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());


        JLabel title = new JLabel("Login");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        panel.add(title);

        JLabel userLabel = new JLabel("Usuário:");
        panel.add(userLabel);

        userField = new JTextField();
        userField.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        panel.add(userField);

        JLabel passLabel = new JLabel("Senha:");
        panel.add(passLabel);

        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        panel.add(passField);

        loginButton = new JButton("Entrar");
        panel.add(loginButton);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.GRAY);
        panel.add(statusLabel);


        layout.putConstraint(SpringLayout.NORTH, title, MARGIN, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, panel);


        layout.putConstraint(SpringLayout.NORTH, userLabel, V_GAP, SpringLayout.SOUTH, title);
        layout.putConstraint(SpringLayout.WEST, userLabel, MARGIN, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, userField, 0, SpringLayout.NORTH, userLabel);
        layout.putConstraint(SpringLayout.WEST, userField, 10, SpringLayout.EAST, userLabel);


        layout.putConstraint(SpringLayout.NORTH, passLabel, V_GAP, SpringLayout.SOUTH, userField);
        layout.putConstraint(SpringLayout.WEST, passLabel, MARGIN, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passField, 0, SpringLayout.NORTH, passLabel);
        layout.putConstraint(SpringLayout.WEST, passField, 10, SpringLayout.EAST, passLabel);


        layout.putConstraint(SpringLayout.NORTH, loginButton, V_GAP, SpringLayout.SOUTH, passField);
        layout.putConstraint(SpringLayout.WEST, loginButton, MARGIN, SpringLayout.WEST, panel);


        layout.putConstraint(SpringLayout.NORTH, statusLabel, V_GAP / 2, SpringLayout.SOUTH, loginButton);
        layout.putConstraint(SpringLayout.WEST, statusLabel, MARGIN, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.EAST, statusLabel, -MARGIN, SpringLayout.EAST, panel);

        loginButton.addActionListener(e -> autenticar());

        setContentPane(panel);
    }

    private void autenticar() {
        String usuario = userField.getText().trim();
        char[] senha = passField.getPassword();

        if (usuario.isEmpty() || senha.length == 0) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Preencha usuário e senha.");
            return;
        }


        if (usuario.equals("admin") && String.valueOf(senha).equals("123")) {
            statusLabel.setForeground(new Color(0,128,0));
            statusLabel.setText("Login realizado.");

        } else {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Credenciais inválidas.");
        }



    }


}