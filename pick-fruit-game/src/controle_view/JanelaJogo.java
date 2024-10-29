package controle_view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * A classe JanelaJogo representa a janela principal do jogo, que gerencia
 * a interface gráfica e o estado atual do jogo.
 * Ela é responsável por iniciar a janela e atualizar o estado do jogo
 * em intervalos regulares usando um Timer.
 */

public class JanelaJogo extends JFrame implements ActionListener {
	private Timer relogioEstado;
	public EstadoView estadoAtual;

	/**
     * Construtor padrão da classe JanelaJogo.
     * Inicializa a janela, define o estado inicial e inicia o timer.
     */
	public JanelaJogo() {
		super();
		relogioEstado = new Timer(100, this);
		estadoAtual = new TelaInicial();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 732);
		setVisible(true);
		setContentPane(estadoAtual);
		relogioEstado.start();
	}

	 /**
     * Método chamado a cada vez que o timer é acionado.
     * Atualiza o estado da janela se necessário.
     *
     * @param event O evento de ação que foi disparado pelo timer.
     */
	public void actionPerformed(ActionEvent event) {
		if (estadoAtual.getMudarEstado()) {
			remove(estadoAtual);
			estadoAtual = estadoAtual.proximoEstado();
			setContentPane(estadoAtual);
			repaint();
		}
	}
}
