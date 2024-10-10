package principal;

import controle_view.*;
import java.awt.EventQueue;

/**
 * A classe {@code Principal} é o ponto de entrada da aplicação.
 * Ela inicializa a interface gráfica do jogo "Cata Fruta" utilizando o Swing.
 */
public class Principal {

    /**
     * O método principal (main) da aplicação. Ele cria uma instância de {@code JanelaTeste}
     * dentro da thread de despacho de eventos do Swing (Event Dispatch Thread - EDT).
     *
     * @param args Argumentos passados pela linha de comando (não usados aqui).
     */
    public static void main(String[] args) {
        // A chamada para EventQueue.invokeLater garante que a GUI seja construída
        // e atualizada dentro da thread de despacho de eventos do Swing.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Cria a janela principal do jogo, representada por JanelaTeste
                    JanelaTeste frame = new JanelaTeste();
                } catch (Exception e) {
                    // Caso ocorra alguma exceção, ela será impressa no console
                    e.printStackTrace();
                }
            }
        });
    }
}
