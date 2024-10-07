package controle_view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;
import modelo_jogo.*;
public class TelaTerreno extends EstadoView {
	private Terreno terreno;
	private PainelDesenho[][] imagensTerreno;
	private int larguraImagens;
	private int alturaImagens;
	
	TelaTerreno(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas) {
		setBounds(0, 0, 886, 632);
		setLayout(null);
		larguraImagens = getWidth()/dimensao;
		alturaImagens = getHeight()/dimensao;
		terreno = new Terreno(dimensao, pedras, maracujas, maracujas_chao, laranjeiras, laranjas, abacateiros, abacates, coqueiros, cocos, pesDeAcerola, acerolas, amoeiras, amoras, goiabeiras, goiabas, probabidade_bichadas);
		imagensTerreno = new PainelDesenho[terreno.getDimensao()][terreno.getDimensao()];
		for (int i = 0; i < terreno.getDimensao(); i++) {
			for (int j = 0; j < terreno.getDimensao(); j++) {
				if(terreno.getElementoFloresta(i, j) instanceof Arvore) {
					imagensTerreno[i][j] = new PainelDesenho(i*larguraImagens, j*alturaImagens, larguraImagens, alturaImagens, "res/arvorePixelart.png");
					imagensTerreno[i][j].setBackground(new Color(0x8ec546));
					add(imagensTerreno[i][j]);
				}
				else if(terreno.getElementoFloresta(i, j) instanceof Pedra) {
					imagensTerreno[i][j] = new PainelDesenho(i*larguraImagens, j*alturaImagens, larguraImagens, alturaImagens, "res/rochaPixelArt.png");
					imagensTerreno[i][j].setBackground(new Color(0x8ec546));
					add(imagensTerreno[i][j]);
				}
				else {
					imagensTerreno[i][j] = new PainelDesenho(i*larguraImagens, j*alturaImagens, larguraImagens, alturaImagens, "res/gramaPixelart(1).png");
					add(imagensTerreno[i][j]);
				}
			}
		}
		repaint();
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
					frame.setContentPane(new TelaTerreno(5, 5, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 10));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
