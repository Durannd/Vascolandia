package view;

import javax.swing.*;

import model.dto.FuncionarioDTO;
import model.entities.Funcionario;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	

    public LoginView() {
    	setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 244);
        setLocationRelativeTo(null);
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);
        
        JFormattedTextField txtFieldCpf = new JFormattedTextField();
        springLayout.putConstraint(SpringLayout.WEST, txtFieldCpf, 38, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, txtFieldCpf, -96, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, txtFieldCpf, -89, SpringLayout.EAST, getContentPane());
        txtFieldCpf.setFocusLostBehavior(JFormattedTextField.REVERT);
        txtFieldCpf.setToolTipText("");
        getContentPane().add(txtFieldCpf);
        
        JLabel lblTitle = new JLabel("Faça seu login");
        springLayout.putConstraint(SpringLayout.NORTH, lblTitle, 24, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblTitle, 98, SpringLayout.WEST, getContentPane());
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        getContentPane().add(lblTitle);
        
        JLabel lblCpf = new JLabel("Digite seu cpf:");
        springLayout.putConstraint(SpringLayout.NORTH, txtFieldCpf, 6, SpringLayout.SOUTH, lblCpf);
        springLayout.putConstraint(SpringLayout.WEST, lblCpf, 38, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, lblCpf, 19, SpringLayout.SOUTH, lblTitle);
        getContentPane().add(lblCpf);
        
        JButton btnLogin = new JButton("Login");
        springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 6, SpringLayout.SOUTH, txtFieldCpf);
        springLayout.putConstraint(SpringLayout.WEST, btnLogin, 125, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnLogin, -57, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnLogin, 215, SpringLayout.WEST, getContentPane());
        getContentPane().add(btnLogin);
        
        txtFieldCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume(); 
                }
            }
        });
        
        btnLogin.addActionListener(e -> {
        	FuncionarioDTO f = new FuncionarioDTO(1L, "Operador de Caixa", 1500.98F, "Ricael Durand", "46856663803", "ricaelmenezes@gmail.com"
        			, "11952719041", 15L );
        	
        	dispose();
        	InitialView i = new InitialView(f);
        });
        
        setVisible(true);
    }

   
}