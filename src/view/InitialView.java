package view;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import model.dto.FuncionarioDTO;
import model.entities.Funcionario;

import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JLayeredPane;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import javax.swing.DebugGraphics;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class InitialView extends JFrame {
	private JMenu mnNewMenu;

	public InitialView(FuncionarioDTO f) {
		getContentPane().setSize(new Dimension(0, 35));
		getContentPane().setPreferredSize(new Dimension(0, 35));
		setResizable(false);
		setTitle("Tela Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		// inicio menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(0, 35));
		menuBar.setBackground(Color.WHITE);
		getContentPane().add(menuBar, BorderLayout.NORTH);

		mnNewMenu = new JMenu("Novo Pedido");
		mnNewMenu.setBackground(Color.GRAY);
		mnNewMenu.setForeground(new Color(0, 0, 0));
		mnNewMenu.setMinimumSize(new Dimension(100, 21));
		mnNewMenu.setPreferredSize(new Dimension(100, 21));
		mnNewMenu.setMaximumSize(new Dimension(100, 100));
		menuBar.add(mnNewMenu);

		JMenuItem menuItemEntrega = new JMenuItem("Entrega");
		menuItemEntrega.setPreferredSize(new Dimension(100, 21));
		mnNewMenu.add(menuItemEntrega);

		JMenuItem menuItemRestaurante = new JMenuItem("Restaurante");
		menuItemRestaurante.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UNDEFINED, 0));
		menuItemRestaurante.setPreferredSize(new Dimension(100, 21));
		mnNewMenu.add(menuItemRestaurante);

		JMenu menuFornecedores = new JMenu("Fornecedores");
		menuBar.add(menuFornecedores);

		JMenuItem menuItemNovoFornecedor = new JMenuItem("Novo Fornecedor");
		menuFornecedores.add(menuItemNovoFornecedor);

		JMenuItem menuItemListarFornecedores = new JMenuItem("Listar Fornecedores");
		menuFornecedores.add(menuItemListarFornecedores);

		setVisible(true);
	}

}
