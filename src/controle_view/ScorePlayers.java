package controle_view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import personagens.Jogador;
import java.awt.Graphics;

import personagens.Jogador;

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
        g.drawImage(scoreJogador1, (int)(615 + (615/this.dimensao)), 100, 50, 50, null);
        g.drawImage(socreJogador2, (int)(615 + (615/this.dimensao)), 180, 50, 50, null);
        g.drawString("Pontuação: " + jogador1.getQtdFrutasColetadas(), (int)(635 + (635/this.dimensao) + 50), 100);
        g.drawString("Pontuação: " + jogador2.getQtdFrutasColetadas(), (int)(635 + (635/this.dimensao) + 50), 180);
        if(jogador1.getQtdFrutasColetadas() > 0){
            ImageIcon frutaJogador1Icon = new ImageIcon("res" + System.getProperty("file.separator") + jogador1.getMochilaFrutas()[jogador1.getQtdFrutasColetadas()-1].getNomeFruta() + ".png");
            this.frutaJogador1 = frutaJogador1Icon.getImage();
            g.drawImage(frutaJogador1, (int)(645 + (645/this.dimensao)), 110, 50, 50, null);
        }
        if(jogador2.getQtdFrutasColetadas() > 0){
            ImageIcon frutaJogador2Icon = new ImageIcon("res" + System.getProperty("file.separator") + jogador2.getMochilaFrutas()[jogador2.getQtdFrutasColetadas()-1].getNomeFruta() + ".png");
            this.frutaJogador2 = frutaJogador2Icon.getImage();
            g.drawImage(frutaJogador2, (int)(645 + (645/this.dimensao)), 190, 50, 50, null);
        }
        

        painelDesenho.repaint();
    }
}
