package controle_view;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import modelo_jogo.Jogador;

import java.awt.Graphics;


public class ScorePlayers {
    private Image scoreJogador1, socreJogador2, frutaJogador1, frutaJogador2;
    private Jogador jogador1, jogador2;
    private JPanel painelDesenho;
    /**
     * Construtor da classe {@code ScorePlayers}. Inicializa a interface gráfica dos dados.
     * @param painelDesenho
     * @param jogador1
     * @param jogador2
     * 
     * 
    */
    ScorePlayers(JPanel painelDesenho, Jogador jogador1, Jogador jogador2) {
        ImageIcon scoreJogador1Icon = new ImageIcon("res" + System.getProperty("file.separator") + "player1Capacete.png");
        ImageIcon scoreJogador2Icon = new ImageIcon("res" + System.getProperty("file.separator") + "player2Capacete.png");
        this.scoreJogador1 = scoreJogador1Icon.getImage();
        this.socreJogador2 = scoreJogador2Icon.getImage();
        this.painelDesenho = painelDesenho;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    /**
     * Desenha o score dos jogadores na tela.
     * @param g
     * 
    */
    public void desenharScore(Graphics g){
        g.drawString(jogador1.getNome(), (int)(painelDesenho.getWidth()*0.8+10), 75);
        g.drawString(jogador2.getNome(), (int)(painelDesenho.getWidth()*0.8+10), 185);
    	g.drawImage(scoreJogador1, (int)(painelDesenho.getWidth()*0.8+10), 100, 50, 50, null);
        g.drawImage(socreJogador2, (int)(painelDesenho.getWidth()*0.8+10), 205, 50, 50, null);
        g.drawString("Pontuação: " + jogador1.calcularPontosDeVitoria() + " Mochila: " + jogador1.totalDeFrutas() + "/" + jogador1.getCapacidadeMochila(), (int)(painelDesenho.getWidth()*0.8+10), 90);
        if (jogador1.estaDoente()) {
        	g.drawString("Doente", (int)(painelDesenho.getWidth()*0.8+80), 110);
        }
        g.drawString("Pontuação: " + jogador2.calcularPontosDeVitoria() + " Mochila: " + jogador2.totalDeFrutas() + "/" + jogador2.getCapacidadeMochila(), (int)(painelDesenho.getWidth()*0.8+10), 200);
        if (jogador2.estaDoente()) {
        	g.drawString("Doente", (int)(painelDesenho.getWidth()*0.8+80), 215);
        }
        int larguraFrutas = (int) Math.max(10, ((painelDesenho.getWidth() - (painelDesenho.getWidth()*0.8 + 60)) / 8));
        LinkedList<String> frutasJogador1 = jogador1.tiposDeFrutasNaMochila();
        LinkedList<String> frutasJogador2 = jogador2.tiposDeFrutasNaMochila();
        int offset = (int)(painelDesenho.getWidth()*0.8+60);
        while(!frutasJogador1.isEmpty()){
            ImageIcon frutaJogador1Icon = new ImageIcon("res" +System.getProperty("file.separator") + "modelo_jogo." + frutasJogador1.pop() + ".png");
            this.frutaJogador1 = frutaJogador1Icon.getImage();
            g.drawImage(frutaJogador1, offset, 125, larguraFrutas, 25, null);
            offset += larguraFrutas;
        }
        offset = (int)(painelDesenho.getWidth()*0.8+60);
        while(!frutasJogador2.isEmpty()){
            ImageIcon frutaJogador2Icon = new ImageIcon("res" +System.getProperty("file.separator") + "modelo_jogo." + frutasJogador2.pop() + ".png");
            this.frutaJogador2 = frutaJogador2Icon.getImage();
            g.drawImage(frutaJogador2, offset, 230, larguraFrutas, 25, null);
            offset += larguraFrutas;
        }
        

        painelDesenho.repaint();
    }


}