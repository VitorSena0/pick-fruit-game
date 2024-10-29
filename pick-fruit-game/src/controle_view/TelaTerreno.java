package controle_view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;

import modelo_jogo.*;

/**
 * TelaTerreno é uma interface gráfica que representa o terreno do jogo.
 * Esta classe é responsável por renderizar o terreno, que consiste em elementos como grama, árvores, frutas e pedras.
 * Além disso, fornece um botão para voltar à tela anterior e atualiza a visualização dos elementos conforme necessário.
 */

public class TelaTerreno extends EstadoView {
	private Terreno terreno;
	private Jogo jogo;
	private JButton botaoVoltar;
	private Image imagemGrama;
	private Image imagemPedra;
	private Image imagemFruta;
	private Image imagemArvore;
	private Image imagemTerra;
	private Image imagemJogador;
	private int larguraImagens;
	private int alturaImagens;
	private int dimensao; private int pedras; private int maracujas; private int maracujas_chao; private int laranjeiras; private int laranjas; private int abacateiros; private int abacates; private int coqueiros; private int cocos; private int pesDeAcerola; private int acerolas; private int amoeiras; private int amoras; private int goiabeiras; private int goiabas; private int probabidade_bichadas; private int mochila;
	GridBagConstraints gbc;
	private String[] nomes = {"Jogador 1", "Jogador 2"};

	 /**
     * Construtor que inicializa a tela do terreno com as dimensões e elementos configurados.
     *
     * @param dimensao Dimensão do terreno.
     * @param pedras Quantidade de pedras no terreno.
     * @param maracujas Quantidade de maracujas no terreno.
     * @param maracujas_chao Quantidade de maracujas no chão.
     * @param laranjeiras Quantidade de laranjeiras no terreno.
     * @param laranjas Quantidade de laranjas no terreno.
     * @param abacateiros Quantidade de abacateiros no terreno.
     * @param abacates Quantidade de abacates no terreno.
     * @param coqueiros Quantidade de coqueiros no terreno.
     * @param cocos Quantidade de cocos no terreno.
     * @param pesDeAcerola Quantidade de pés de acerola no terreno.
     * @param acerolas Quantidade de acerolas no terreno.
     * @param amoeiras Quantidade de amoeiras no terreno.
     * @param amoras Quantidade de amoras no terreno.
     * @param goiabeiras Quantidade de goiabeiras no terreno.
     * @param goiabas Quantidade de goiabas no terreno.
     * @param probabidade_bichadas Probabilidade de frutas bichadas no terreno.
     * @param mochila Capacidade da mochila.
     */
	
	TelaTerreno(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas, int mochila) {
		this.dimensao = dimensao;
		this.pedras = pedras;
		this.maracujas = maracujas;
		this.maracujas_chao = maracujas_chao;
		this.laranjeiras = laranjeiras;
		this.laranjas = laranjas;
		this.abacates = abacates;
		this.abacateiros = abacateiros;
		this.coqueiros = coqueiros;
		this.cocos = cocos;
		this.pesDeAcerola = pesDeAcerola;
		this.acerolas = acerolas;
		this.amoeiras = amoeiras;
		this.amoras = amoras;
		this.goiabeiras = goiabeiras;
		this.goiabas = goiabas;
		this.probabidade_bichadas = probabidade_bichadas;
		this.mochila = mochila;
		mudarEstado = false;
		setBounds(0, 0, 986, 732);
		setLayout(new GridBagLayout());
		larguraImagens = getWidth()/dimensao;
		alturaImagens = getHeight()/dimensao;
		terreno = new Terreno(dimensao, pedras, maracujas, maracujas_chao, laranjeiras, laranjas, abacateiros, abacates, coqueiros, cocos, pesDeAcerola, acerolas, amoeiras, amoras, goiabeiras, goiabas, probabidade_bichadas);
		jogo = new Jogo(terreno, 2 ,mochila, 2, nomes);
		gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
	     
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
	
	        // Adicionando o botão "Voltar" ao lado direito
	        botaoVoltar = new JButton("Voltar");
	        botaoVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));

	        // Usando weightx para empurrar o botão para a direita
	        gbc.gridx = 1; // Definir coluna (direita)
	        gbc.gridy = 0; // Primeira linha
	        gbc.weightx = 1.0; // Usar o espaço disponível
	        gbc.anchor = GridBagConstraints.NORTHEAST; // Posicionar no canto superior direito
	        
	        botaoVoltar.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                mudarEstado = true;
	            }
	        });

	        add(botaoVoltar, gbc);
		
		revalidate();  // Atualiza o layout
		repaint();     // Para garantir que a pintura aconteça

	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    // Calcula dinamicamente o tamanho de cada célula com base no tamanho da janela
	    larguraImagens = (int)((getWidth() / terreno.getDimensao())*0.8);
	    alturaImagens = (int)((getHeight() / terreno.getDimensao())*0.8);
	    
	    int dimensaoTerreno = terreno.getDimensao();  // Quantidade de células no terreno

	    // Preenche o fundo da tela com uma cor base
	    g.setColor(new Color(0x7B3F00));
	    g.fillRect(0, 0, (int)(getWidth()*0.8), (int)(getHeight()*0.8));

	    // Desenho dos elementos no terreno
	    for (int i = 0; i < dimensaoTerreno; i++) {
	        for (int j = 0; j < dimensaoTerreno; j++) {
	            ElementoEstatico elementoCenario = terreno.getElementoFloresta(i, j);

	            if (elementoCenario instanceof Grama) {
	                // Desenha a grama
	                g.drawImage(imagemGrama, i * larguraImagens, j * alturaImagens, larguraImagens, alturaImagens, null);

	                Grama gramaAtual = (Grama) elementoCenario;
	                
	                // Desenha a árvore se existir
	                if (gramaAtual.getArvore() != null) {
	                	String nomearvore = gramaAtual.getArvore().getClass().getName() + ".png";
	                	Image arvoreImage = new ImageIcon("res" + System.getProperty("file.separator") + nomearvore).getImage();
	                    g.drawImage(arvoreImage, i * larguraImagens, j * alturaImagens, larguraImagens, alturaImagens, null);
	                }
	                
	                // Desenha a fruta se existir
	                if (gramaAtual.getFruta() != null) {
	                	String nomefruta = gramaAtual.getFruta().getClass().getName() + ".png";
	                	Image frutaImage = new ImageIcon("res" + System.getProperty("file.separator") + nomefruta).getImage();
	                    g.drawImage(frutaImage, i * larguraImagens + larguraImagens / 4, j * alturaImagens + alturaImagens / 4, larguraImagens / 2, alturaImagens / 2, null);
	                }
	                
	                // Desenha o jogador se existir
	                if (gramaAtual.getJogador() != null) {
	                    g.drawImage(imagemJogador, i * larguraImagens + larguraImagens / 4, j * alturaImagens + alturaImagens / 4, larguraImagens / 2, alturaImagens / 2, null);
	                }
	            } else if (elementoCenario instanceof Pedra) {
	                // Desenha o terreno com pedra
	                g.drawImage(imagemTerra, i * larguraImagens, j * alturaImagens, larguraImagens, alturaImagens, null);
	                g.drawImage(imagemPedra, i * larguraImagens, j * alturaImagens, larguraImagens, alturaImagens, null);
	            }
	        }
	    }
	    revalidate();  // Atualiza o layout
		repaint();     // Para garantir que a pintura aconteça

	}
	
	@Override
	public EstadoView proximoEstado() {
		if(mudarEstado) {
			 return new TelaConfiguracoes( dimensao,  pedras,  maracujas,  maracujas_chao,  laranjeiras,  laranjas,  abacateiros,  abacates,  coqueiros,  cocos,  pesDeAcerola,  acerolas,  amoeiras,  amoras,  goiabeiras,  goiabas,  probabidade_bichadas,  mochila);
		}
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
