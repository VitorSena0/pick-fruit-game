package controle_view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * A classe {@code TelaInicial} representa a tela inicial da aplicação "Cata Fruta".
 * Esta tela exibe um título e uma instrução para o usuário, além de um painel gráfico
 * que representa uma fruta (maracujá).
 * 
 * <p>A tela reage a cliques do mouse, permitindo que o usuário inicie o jogo. Quando o
 * usuário clica na tela, o estado muda para a tela de seleção.</p>
 */
public class TelaInicial extends EstadoView {
	/**
     * Construtor da classe {@code TelaInicial}. Inicializa a interface gráfica
     * da tela inicial, incluindo o título, a instrução para o usuário e o painel
     * de desenho que exibe a fruta.
     */
	TelaInicial() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(0, 0, 986, 732);
		setLayout(null);
		
		// Dimensões da janela
		int larguraJanela = 986;
		
		// Centralizando os elementos
		int xCentral = larguraJanela / 2;
		
		JLabel lblNewLabel = new JLabel("Cata Fruta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Source Serif Pro", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setBounds(xCentral - 119, 10, 239, 67); // 239 / 2 = 119
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clique na tela para iniciar");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Light", Font.ITALIC, 28));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(xCentral - 426, 524, 852, 49); // 852 / 2 = 426
		add(lblNewLabel_1);

		PainelDesenho maracuja = new PainelDesenho();
		maracuja.setAtributos(xCentral - 126, 179, 253, 189, "res/Maracuja.png"); // 253 / 2 = 126
		add(maracuja);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudarEstado = true;
			}
		});
	}

	/**
     * Este método determina o próximo estado da aplicação ao verificar se o usuário
     * clicou na tela. Se {@code mudarEstado} for verdadeiro, o método retorna uma nova
     * instância da classe {@link TelaSelecao}. Caso contrário, retorna a tela atual.
     * 
     * @return Uma instância de {@link EstadoView} que representa o próximo estado da aplicação.
     */
	@Override
	public EstadoView proximoEstado() {
		if (mudarEstado) {
			return new TelaSelecao();
		}
		else {
			return this;
		}
	}
}
