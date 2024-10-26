package controle_view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

import modelo_jogo.Fruta;
import modelo_jogo.Jogador;

import java.awt.Graphics;


public class ScorePlayers {
    private Image scoreJogador1, socreJogador2, frutaJogador1, frutaJogador2;
    private Jogador jogador1, jogador2;
    private int dimensao;
    private JPanel painelDesenho;
    /**
     * Construtor da classe {@code ScorePlayers}. Inicializa a interface gráfica dos dados.
     * @param painelDesenho
     * @param dimensao
     * @param jogador1
     * @param jogador2
     * 
     * 
    */
    ScorePlayers(JPanel painelDesenho, int dimensao, Jogador jogador1, Jogador jogador2) {
        ImageIcon scoreJogador1Icon = new ImageIcon("res" + System.getProperty("file.separator") + "player1Capacete.png");
        ImageIcon scoreJogador2Icon = new ImageIcon("res" + System.getProperty("file.separator") + "player2Capacete.png");
        this.scoreJogador1 = scoreJogador1Icon.getImage();
        this.socreJogador2 = scoreJogador2Icon.getImage();
        this.painelDesenho = painelDesenho;
        this.dimensao = dimensao;        
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    /**
     * Desenha o score dos jogadores na tela.
     * @param g
     * 
    */
    public void desenharScore(Graphics g){
        g.drawImage(scoreJogador1, (int)(painelDesenho.getWidth()-150), 100, 50, 50, null);
        g.drawImage(socreJogador2, (int)(painelDesenho.getWidth()-150), 180, 50, 50, null);
        g.drawString("Pontuação: " + jogador1.totalDeFrutas(), (int)(painelDesenho.getWidth() - 100), 100);
        g.drawString("Pontuação: " + jogador2.totalDeFrutas(), (int)(painelDesenho.getWidth() - 100), 180);
        if(jogador1.totalDeFrutas() > 0){
            ImageIcon frutaJogador1Icon = new ImageIcon("res" +System.getProperty("file.separator")+ jogador1.getUltimaFruta() + ".png");
            this.frutaJogador1 = frutaJogador1Icon.getImage();
            g.drawImage(frutaJogador1, (int)(painelDesenho.getWidth() - 100), 120, 50, 50, null);
        }
        if(jogador2.totalDeFrutas() > 0){
            ImageIcon frutaJogador2Icon = new ImageIcon("res" + System.getProperty("file.separator") + jogador2.getUltimaFruta()+ ".png");
            this.frutaJogador2 = frutaJogador2Icon.getImage();
            g.drawImage(frutaJogador2, (int)(painelDesenho.getWidth() - 100), 200, 50, 50, null);
        }
        

        painelDesenho.repaint();
    }


}