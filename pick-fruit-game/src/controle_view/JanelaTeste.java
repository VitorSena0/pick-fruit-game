package controle_view;
import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class JanelaTeste extends JFrame implements ActionListener {
	private Timer relogioEstado;
	private EstadoView estadoAtual;
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
	public void actionPerformed(ActionEvent event) {
		if (estadoAtual.getMudarEstado()) {
			estadoAtual = estadoAtual.proximoEstado();
			setContentPane(estadoAtual);
			repaint();
		}
	}
}
