package dados;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import personagens.Jogador;

public class DadosInterface {
    private int valorDado1;
    private int valorDado2;
    private int dimensao;
    private JButton botaoDados;
    private Jogador jogador1, jogador2;
    private boolean primeiraJogada = false;
    private boolean segundaJogada = false;
    private Image imagemDado1, imagemDado2;
    private JPanel painelDesenho; // Painel onde os dados serão desenhados

    /**
     * Construtor da classe {@code DadosInterface}. Inicializa a interface gráfica dos dados.
     * @param painelDesenho O painel onde os dados serão desenhados.
     */
    public DadosInterface(JPanel painelDesenho, int dimensao) {
        this.valorDado1 = 1;
        this.valorDado2 = 1;
        this.painelDesenho = painelDesenho; // Armazena a referência ao painel de desenho
        this.dimensao = dimensao;
        carregarImagens();
        adicionarElementosInterface();
    }

    private void carregarImagens() {
        imagemDado1 = RolarDados.carregarImagem("resources/dice1.png");
        imagemDado2 = RolarDados.carregarImagem("resources/dice2.png");
    }

    public int getValorDado1() {
        return valorDado1;
    }

    public int getValorDado2() {
        return valorDado2;
    }

    /**
     * Adiciona os elementos gráficos da interface: botão de rolar dados.
     */
        /**
     * Adiciona os elementos gráficos da interface: botão de rolar dados.
     */
    private void adicionarElementosInterface() {
        // Botão para rolar os dados
        Random random = new Random();
        botaoDados = new JButton("Rolar dados");
        botaoDados.setBounds(75, 165, 60, 17);
        botaoDados.addActionListener(e -> {
            botaoDados.setEnabled(false);
            long tempoInicial = System.currentTimeMillis();

            Thread rollThread = new Thread(() -> {
                long tempoAtual = System.currentTimeMillis();
                try {
                    while ((tempoAtual - tempoInicial) < 1000) {
                        valorDado1 = random.nextInt(6) + 1;
                        valorDado2 = random.nextInt(6) + 1;

                        // Atualiza as imagens dos dados
                        RolarDados.atualizarImagemDado(imagemDado1, "resources" + System.getProperty("file.separator") + "dice" + valorDado1 + ".png");
                        RolarDados.atualizarImagemDado(imagemDado2, "resources" + System.getProperty("file.separator") + "dice" + valorDado2 + ".png");
                        atualizarImagens();
                        // Repaint para redesenhar as novas imagens dos dados
                        painelDesenho.repaint();

                        Thread.sleep(60);
                        tempoAtual = System.currentTimeMillis();
                    }

                    // Verifica se o botão ainda está desabilitado
                    if (!botaoDados.isEnabled()) {
                        setBotaoEnabled(false);
                        setPrimeiraJogada(true);
                    }

                    // Atribui a quantidade de movimentos ao jogador correspondente
                    if (!segundaJogada) {
                        setSegundaJogada(true);
                        jogador1.setQtdMovimentos(valorDado1 + valorDado2);
                        
                    } else {
                        setSegundaJogada(false);
                        jogador2.setQtdMovimentos(valorDado1 + valorDado2);
                    }
                    
                    painelDesenho.repaint();

                } catch (Exception ex) {
                    System.out.println("Erro ao rolar dados: " + ex);
                }
            });
            rollThread.start();
        });

        painelDesenho.add(botaoDados);
    }

        public boolean isPrimeiraJogada() {
            return this.primeiraJogada;
        }

        private void setPrimeiraJogada(boolean primeiraJogada) {
            this.primeiraJogada = primeiraJogada;
        }

        public boolean isSegundaJogada() {
            return this.segundaJogada;
        }

        public void setSegundaJogada(boolean segundaJogada) {
            this.segundaJogada = segundaJogada;
        }

        // Método para obter o estado do botão
        public boolean isBotaoEnabled() {
            return this.botaoDados.isEnabled();
        }

        public void setBotaoEnabled(boolean enabled) {
            this.botaoDados.setEnabled(enabled);
        }

        public int getQtdMovimentos(){
            return valorDado1 + valorDado2;
        }

        public void setJogadores(Jogador jogador1, Jogador jogador2){
            this.jogador1 = jogador1;
            this.jogador2 = jogador2;
        }

/**
 * Classe responsável por carregar imagens.
 */
private void atualizarImagens() {
    imagemDado1 = RolarDados.carregarImagem("resources/dice" + valorDado1 + ".png");
    imagemDado2 = RolarDados.carregarImagem("resources/dice" + valorDado2 + ".png");
}

public void desenharDados(Graphics g) {
    if (imagemDado1 != null && imagemDado2 != null) {
        double transladarDados = 0;
        if(this.dimensao < 8){
            transladarDados = 0.1;
        }
        g.drawImage(imagemDado1, (int)(680 + (680*transladarDados)) , 260, 60, 84, null);
        g.drawImage(imagemDado2, (int)(680 + (680*transladarDados)), 380, 60, 84, null);
    }

}

// Outros métodos omitidos para clareza...

public static class RolarDados {
    private static final double REDIMENSIONAR_PROPORCAO = 0.3;

    public static Image carregarImagem(String caminho) {
        try {
            InputStream imagemCaminho = RolarDados.class.getResourceAsStream(caminho);
            BufferedImage imagem = ImageIO.read(imagemCaminho);
            return redimensionarImagem(imagem, REDIMENSIONAR_PROPORCAO);
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e);
            return null;
        }
    }

    public static void atualizarImagemDado(Image imagem, String caminho) {
        try {
            BufferedImage novaImagem = ImageIO.read(RolarDados.class.getResourceAsStream(caminho));
            imagem = redimensionarImagem(novaImagem, REDIMENSIONAR_PROPORCAO);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar imagem do dado: " + e);
        }
    }

    private static Image redimensionarImagem(BufferedImage imagem, double proporcao) {
        int novaLargura = (int) (imagem.getWidth() * proporcao);
        int novaAltura = (int) (imagem.getHeight() * proporcao);
        return imagem.getScaledInstance(novaLargura, novaAltura, Image.SCALE_SMOOTH);
    }
}
}