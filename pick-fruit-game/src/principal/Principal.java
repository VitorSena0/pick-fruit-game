package principal;
import controle_view.*;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A classe Principal é o ponto de entrada da aplicação, responsável por inicializar e exibir a janela principal
 * da interface gráfica através da classe {@link JanelaTeste}.
 * 
 * Esta classe utiliza o EventQueue para garantir que a interface gráfica seja manipulada
 * na thread de despacho de eventos do Swing.
 * 
 * @version 1.0
 */
public class Principal {

    /**
     * Método principal que inicia a aplicação.
     * Utiliza {@link EventQueue#invokeLater(Runnable)} para criar e exibir a janela principal
     * da interface gráfica de maneira thread-safe.
     * 
     * @param args argumentos da linha de comando (não utilizados).
     */
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaJogo frame = new JanelaJogo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
