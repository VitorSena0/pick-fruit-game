import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A classe Container representa a janela principal do jogo "Cata Fruta".
 * Ela herda de JFrame e configura os elementos básicos da janela, como título,
 * tamanho e comportamento de fechamento. Além disso, adiciona o painel de jogo (Janela).
 * 
 * O jogo é renderizado dentro dessa janela e contém elementos como o jogador, frutas, pedras e árvores.
 * 
 * @author SeuNome
 * @version 1.0
 */
public class Container extends JFrame {
	public int proporcaoTelaJogo = 700;
    /**
     * Construtor da classe Container.
     * Inicializa a janela do jogo com título, tamanho e comportamento de fechamento.
     * Também configura a janela para ser visível e ajustável e adiciona o painel de jogo.
     */
    public Container() {
        this.setTitle("Cata Fruta"); // Define o título da janela
        this.setSize(proporcaoTelaJogo, proporcaoTelaJogo); // Define o tamanho da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar
        this.setResizable(true); // Permite que a janela seja redimensionada
        this.setVisible(true); // Torna a janela visível

        // Adiciona o painel do jogo
        Janela janela = new Janela();
        add(janela);
    }

    /**
     * Método principal do jogo.
     * Responsável por criar e exibir a janela do jogo. 
     * O método usa o SwingUtilities.invokeLater para garantir que a criação da GUI ocorra na Event Dispatch Thread.
     * 
     * @param args Argumentos passados pela linha de comando (não usados aqui).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Container(); // Cria uma nova instância de Container (a janela do jogo)
        });
    }
}

