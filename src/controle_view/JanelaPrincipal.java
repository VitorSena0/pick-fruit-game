package controle_view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cata Fruta");
		lblNewLabel.setBounds(313, 10, 239, 67);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Source Serif Pro", Font.BOLD | Font.ITALIC, 48));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clique na tela para iniciar");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Light", Font.ITALIC, 28));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 524, 852, 49);
		contentPane.add(lblNewLabel_1);

		PainelDesenho maracuja = new PainelDesenho();
		maracuja.setAtributos(313, 179, 253, 189, "imagens/Maracuja.png");
		contentPane.add(maracuja);
		
		JPanel panel = new JPanel();
		panel.setBounds(313, 104, 239, 119);
		contentPane.add(panel);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Carregar arquivo de terreno");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNewRadioButton_1.setBounds(14, 39, 219, 21);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Inserir configurações de terreno");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNewRadioButton.setBounds(14, 62, 219, 21);
		panel.setLayout(null);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);
		
		panel.add(rdbtnNewRadioButton_1);
		panel.add(rdbtnNewRadioButton);
		
		JButton btnNewButton = new JButton("Próximo");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(130, 89, 99, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Escolha uma opção");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 10, 219, 23);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(10, 89, 99, 21);
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(313, 272, 239, 100);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Digite o caminho do arquivo");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 5, 219, 23);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(10, 38, 219, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Próximo");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(130, 69, 99, 21);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2_1 = new JButton("Voltar");
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2_1.setBounds(10, 69, 99, 21);
		panel_1.add(btnNewButton_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(255, 10, 348, 595);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Defina as configurações do terreno");
		lblNewLabel_2_1.setBounds(10, 5, 328, 28);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Probablidade de fruta vir bichada");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(10, 43, 203, 23);
		panel_2.add(lblNewLabel_3_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(223, 46, 115, 19);
		panel_2.add(textField_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Dimensão");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1_1.setBounds(10, 76, 64, 23);
		panel_2.add(lblNewLabel_3_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(84, 76, 83, 19);
		panel_2.add(textField_2);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Pedras");
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1_1_1.setBounds(177, 76, 64, 23);
		panel_2.add(lblNewLabel_3_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(251, 76, 83, 19);
		panel_2.add(textField_3);
		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_1.setText("o jogo ainda não está pronto");
				lblNewLabel.setVisible(false);
			}
		});
		
	}
}
