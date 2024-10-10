package controle_view;
import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A classe JanelaTeste representa uma janela que gerencia a transição de estados
 * entre diferentes telas de uma aplicação através de um timer. Cada estado é representado por
 * uma classe que herda de {@link EstadoView}.
 * 
 * A janela atualiza seu conteúdo com base no estado atual e muda o estado conforme necessário.
 */
public class JanelaTeste extends JFrame implements ActionListener {
	  /** Timer responsável por verificar a mudança de estado a cada 100ms. */
	private Timer relogioEstado;
	/** O estado atual da aplicação, que será uma instância de {@link EstadoView}. */
	private EstadoView estadoAtual;
	 /**
     * Construtor da classe JanelaTeste.
     * 
     * Inicializa a janela com o estado inicial e inicia o timer que verifica a transição de estados.
     */
	public JanelaTeste() {
		super();
		relogioEstado = new Timer(100, this);
		estadoAtual = new TelaInicial();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 632);
		setContentPane(estadoAtual);
		setVisible(true);
		relogioEstado.start();
	}
	 /**
     * Método chamado pelo {@link Timer} a cada 100ms.
     * 
     * Verifica se o estado atual solicitou uma mudança de estado e, se sim, altera para o próximo estado.
     * Atualiza o conteúdo da janela com o novo estado e repinta a tela.
     * 
     * @param event O evento de ação disparado pelo timer.
     */
	public void actionPerformed(ActionEvent event) {
		if (estadoAtual.getMudarEstado()) {
			estadoAtual = estadoAtual.proximoEstado();
			setContentPane(estadoAtual);
			repaint();
		}
	}
}
