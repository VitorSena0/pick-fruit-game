package dados;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import personagens.Jogador;


/**
 * A classe {@code DadosInterface} representa a interface gráfica para rolar dados de 6 faces.
 * Ela exibe dois dados e um botão para rolar os dados e obter um número aleatório entre 1 e 6.
 */
public class DadosInterface extends JPanel {
    private int valorDado1;
    private int valorDado2;
    private JButton botaoDados;
    private Jogador jogador1, jogador2;
    private boolean primeiraJogada = false;
    private boolean segundaJogada = false;

    /**
     * Construtor da classe {@code DadosInterface}. Inicializa a interface gráfica dos dados.
     */
    public DadosInterface() {
        setPreferredSize(new Dimension(210, 210)); // 30% de 700x700
        setLayout(null);
        adicionarElementosInterface();
    }


    public int getValorDado1() {
        return valorDado1;
    }

    public int getValorDado2() {
        return valorDado2;
    }

    /**
     * Adiciona os elementos gráficos da interface: banner, dados e botão.
    */
    private void adicionarElementosInterface() {
        // Painel principal para organizar os componentes
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 210, 210); // 30% de 700x700

        // Banner
        JLabel bannerIMG = RolarDados.carregarImagens("resources" + System.getProperty("file.separator") + "banner.png");
        bannerIMG.setBounds(13, 7, 180, 30); // 30% de 45, 25, 600, 100
        panel.add(bannerIMG); // Adiciona o banner ao painel

        // Dados
        JLabel dado1 = RolarDados.carregarImagens("resources" + System.getProperty("file.separator") + "dice1.png");
        dado1.setBounds(30, 60, 60, 84); // 30% de 100, 200, 200, 280
        panel.add(dado1); // Adiciona o dado 1 ao painel

        JLabel dado2 = RolarDados.carregarImagens("resources" + System.getProperty("file.separator") + "dice2.png");
        dado2.setBounds(120, 60, 60, 84); // 30% de 400, 200, 200, 280
        panel.add(dado2); // Adiciona o dado 2 ao painel

        // Botão
        Random random = new Random();
        JButton botao = new JButton("Rolar dados");
        this.botaoDados = botao;
        botao.setBounds(75, 165, 60, 17); // 30% de 250, 550, 200, 58
        botao.addActionListener(new ActionListener() {
            
            /**
             * Método chamado quando o botão é clicado. Ele gera um número aleatório entre 1 e 6 para cada dado durante 1 segundo.
             * Faça a chamada para o método {@link RolarDados#atualizarImagemDado(JLabel, String)} para atualizar a imagem de cada dado.
             * Realiza a atribuição das movimentações dos jogadores.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                botao.setEnabled(false); // Desabilita o botão para evitar cliques múltiplos
                
                // Gera um número aleatório entre 1 e 6 para cada dado durante 1 segundo
                long tempoInicial = System.currentTimeMillis();
                Thread rollThread = new Thread(() -> { // Cria uma nova thread para rolar os dados, pois o método actionPerformed é executado na thread de despacho de eventos
                    long tempoAtual = System.currentTimeMillis(); // Tempo atual em milissegundos
                    try {
                        while ((tempoAtual - tempoInicial) < 1000) { // Rola os dados por 1 segundo
                            valorDado1 = random.nextInt(6) + 1;
                            valorDado2 = random.nextInt(6) + 1;

                            // Atualiza as imagens dos dados
                            RolarDados.atualizarImagemDado(dado1, "resources" + System.getProperty("file.separator") + "dice" + valorDado1 + ".png");
                            RolarDados.atualizarImagemDado(dado2, "resources" + System.getProperty("file.separator") + "dice" + valorDado2 + ".png");

                            repaint();
                            revalidate();

                            Thread.sleep(60); // Aguarda 60 milissegundos

                            tempoAtual = System.currentTimeMillis();
                        }

                        if(!botao.isEnabled()){ // Verifica se o botão ainda está desabilitado
                            DadosInterface.this.setBotaoEnabled(false);
                            DadosInterface.this.setPrimeiraJogada(true);
                        }
                        if(!segundaJogada){ // Verifica se é a primeira jogada
                            DadosInterface.this.setSegundaJogada(true);
                            jogador1.setQtdMovimentos(valorDado1 + valorDado2); // Atribui a quantidade de movimentos do jogador 1
                        }else if(segundaJogada){
                            DadosInterface.this.setSegundaJogada(false);
                            jogador2.setQtdMovimentos(valorDado1 + valorDado2); // Atribui a quantidade de movimentos do jogador 2
                        }
                    } catch (Exception ex) {
                        System.out.println("Erro ao rolar dados: " + ex);
                    }
                });
                rollThread.start(); // Inicia a thread para rolar os dados
             }
        });
        panel.add(botao); // Adiciona o botão ao painel

        this.add(panel); // Adiciona o painel ao JPanel principal
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
}
