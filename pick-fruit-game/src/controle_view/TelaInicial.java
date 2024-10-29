package controle_view;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * TelaInicial representa a tela inicial do jogo, onde o usuário clica para iniciar
 * e acessar o menu de seleção. Esta tela exibe o título do jogo e uma mensagem de 
 * instrução para o usuário.
 */
public class TelaInicial extends EstadoView {

    /**
     * Construtor da classe TelaInicial que inicializa e configura os componentes gráficos
     * da interface, incluindo o título e a instrução para o usuário clicar na tela para iniciar.
     */
	TelaInicial() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(0, 0, 572, 895);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cata Fruta");
		lblNewLabel.setBounds(313, 10, 239, 67);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Source Serif Pro", Font.BOLD | Font.ITALIC, 48));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clique na tela para iniciar");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Light", Font.ITALIC, 28));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 524, 852, 49);
		add(lblNewLabel_1);

		PainelDesenho maracuja = new PainelDesenho();
		maracuja.setAtributos(313, 179, 253, 189, "res"+ System.getProperty("file.separator") +"Maracuja.png");
		add(maracuja);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudarEstado = true;
			}
		});
	}

	  /**
     * Determina o próximo estado da aplicação após o clique do usuário.
     *
     * @return Uma nova instância de TelaSelecao se mudarEstado for verdadeiro,
     * caso contrário, retorna a própria instância de TelaInicial.
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
