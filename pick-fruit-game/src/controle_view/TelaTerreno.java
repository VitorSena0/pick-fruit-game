package controle_view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.*;
import modelo_jogo.*;
public class TelaTerreno extends EstadoView {
	private Terreno terreno;
	private Jogo jogo;
	private Image imagemGrama;
	private Image imagemPedra;
	private Image imagemFruta;
	private Image imagemArvore;
	private Image imagemTerra;
	private Image imagemJogador;
	private int larguraImagens;
	private int alturaImagens;
	private String[] nomes = {"Jogador 1", "Jogador 2"};
	
	TelaTerreno(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas, int mochila) {
		setBounds(0, 0, 886, 632);
		setLayout(null);
		larguraImagens = getWidth()/dimensao;
		alturaImagens = getHeight()/dimensao;
		terreno = new Terreno(dimensao, pedras, maracujas, maracujas_chao, laranjeiras, laranjas, abacateiros, abacates, coqueiros, cocos, pesDeAcerola, acerolas, amoeiras, amoras, goiabeiras, goiabas, probabidade_bichadas);
		jogo = new Jogo(terreno, 2 ,mochila, 2, nomes);
		try {
			imagemGrama = new ImageIcon("res" + System.getProperty("file.separator") + "gramaPixelart(1).png").getImage();
        } catch (Exception e) {
            imagemGrama = null;
        }
		try {
            imagemPedra = new ImageIcon("res" + System.getProperty("file.separator") + "rochaPixelArt.png").getImage();
        } catch (Exception e) {
            imagemPedra = null;
        }
		try {
            imagemArvore = new ImageIcon("res" + System.getProperty("file.separator") + "arvorePixelart.png").getImage();
        } catch (Exception e) {
            imagemArvore = null;
        }
		try {
            imagemTerra = new ImageIcon("res" + System.getProperty("file.separator") + "terraPixelArt.png").getImage();
        } catch (Exception e) {
            imagemFruta = null;
        }
		try {
            imagemFruta = new ImageIcon("res" + System.getProperty("file.separator") + "frutaPixelart.png").getImage();
        } catch (Exception e) {
            imagemFruta = null;
        }
		try {
            imagemJogador= new ImageIcon("res" + System.getProperty("file.separator") + "player1Pixelart.png").getImage();
        } catch (Exception e) {
            imagemFruta = null;
        }
		repaint();     // Para garantir que a pintura aconteça

	}
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		int dimensaoTerreno = terreno.getDimensao();
		requestFocusInWindow();
		g.setColor(new Color(0x7B3F00));
		g.fillRect(0, 0, larguraImagens*dimensaoTerreno, alturaImagens*dimensaoTerreno);
		for (int i = 0; i < dimensaoTerreno; i++)
		{
			for (int j = 0; j < dimensaoTerreno; j++)
			{
				ElementoEstatico elementoCenario = terreno.getElementoFloresta(i, j);
				if (elementoCenario instanceof Grama) {
					g.drawImage(imagemGrama, i*larguraImagens, j*alturaImagens, larguraImagens, alturaImagens, null);
					Grama gramaAtual = (Grama)elementoCenario;
					if (gramaAtual.getArvore() != null) {
						g.drawImage(imagemArvore, i*larguraImagens, j*alturaImagens, larguraImagens, alturaImagens, null);
					}
					if (gramaAtual.getFruta() != null) {
						g.drawImage(imagemFruta, i*larguraImagens, j*alturaImagens, larguraImagens, alturaImagens, null);
					}
					if(gramaAtual.getJogador() != null) {
						g.drawImage(imagemJogador, i* larguraImagens+larguraImagens/4, j*alturaImagens+alturaImagens/4, larguraImagens/2, alturaImagens/2, null);
					}
				}
				else if (elementoCenario instanceof Pedra) {
					g.drawImage(imagemTerra, i*larguraImagens, j*alturaImagens, larguraImagens, alturaImagens, null);
					g.drawImage(imagemPedra, i*larguraImagens, j*alturaImagens, larguraImagens, alturaImagens, null);
				}
			}
		}
    }
	
	@Override
	public EstadoView proximoEstado() {
		return this;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setBounds(100, 100, 886, 632);
					frame.setContentPane(new TelaTerreno(5, 5, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10,10));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
