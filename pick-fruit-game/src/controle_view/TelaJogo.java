package controle_view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import modelo_jogo.*;

public class TelaJogo extends EstadoView implements KeyListener {
	private Jogo jogo;
	private Image imagemGrama;
	private Image imagemPedra;
	private Image imagemFruta;
	private Image imagemArvore;
	private Image imagemTerra;
	private Image imagemJogador;
	private int larguraImagens;
	private int alturaImagens;
	
	TelaJogo(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas, int mochila, String[] nomes) {
		mudarEstado = false;
		setBounds(0, 0, 986, 732);
		setLayout(new GridBagLayout());
		Terreno terreno = new Terreno(dimensao, pedras, maracujas, maracujas_chao, laranjeiras, laranjas, abacateiros, abacates, coqueiros, cocos, pesDeAcerola, acerolas, amoeiras, amoras, goiabeiras, goiabas, probabidade_bichadas);
		jogo = new Jogo(terreno, 2 ,mochila, 2, nomes);
		larguraImagens = (int)((getWidth() / jogo.getDimensao())*0.8);
		alturaImagens = (int)((getWidth() / jogo.getDimensao())*0.8);
        setFocusable(true); // Permite que o painel tenha foco para eventos de teclado
        addKeyListener(this); // Adiciona o KeyListener ao painel
        requestFocusInWindow(); // Solicita foco para o JPanel
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
            imagemTerra = null;
        }
		try {
            imagemFruta = new ImageIcon("res" + System.getProperty("file.separator") + "frutaPixelart.png").getImage();
        } catch (Exception e) {
            imagemFruta = null;
        }
		try {
            imagemJogador= new ImageIcon("res" + System.getProperty("file.separator") + "player1Pixelart.png").getImage();
        } catch (Exception e) {
            imagemJogador = null;
        }
		revalidate();  // Atualiza o layout
		repaint();     // Para garantir que a pintura aconteça
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX() / larguraImagens;
				int y = e.getY() / alturaImagens;
				int dX = x - jogo.getJogador(jogo.getJogadorDaVez()).getX();
				int dY = y - jogo.getJogador(jogo.getJogadorDaVez()).getY();
				if (dX == 0 && dY == -1) {
					jogo.movimentarJogador(1);
				}
				else if (dX == -1 && dY == 0) {
					jogo.movimentarJogador(2);
				}
				else if (dX == 1 && dY == 0) {
					jogo.movimentarJogador(3);
				}
				else if (dX == 0 && dY == 1) {
					jogo.movimentarJogador(4);
				}
				//System.out.println("position " + jogo.getJogador(jogo.getJogadorDaVez()).getX() + " " + jogo.getJogador(jogo.getJogadorDaVez()).getY());
				//System.out.println("click " + dX + " " + dY);
				repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    // Calcula dinamicamente o tamanho de cada célula com base no tamanho da janela
	    larguraImagens = (int)((getWidth() / jogo.getDimensao())*0.8);
	    alturaImagens = (int)((getHeight() / jogo.getDimensao())*0.8);
	    
	    int dimensaoTerreno = jogo.getDimensao();  // Quantidade de células no terreno

	    // Preenche o fundo da tela com uma cor base
	    g.setColor(new Color(0x7B3F00));
	    g.fillRect(0, 0, (int)(getWidth()*0.8), (int)(getHeight()*0.8));
	    
	    this.requestFocusInWindow();

	    // Desenho dos elementos no terreno
	    for (int i = 0; i < dimensaoTerreno; i++) {
	        for (int j = 0; j < dimensaoTerreno; j++) {
	            ElementoEstatico elementoCenario = jogo.getElementoTerreno(i, j);

	            if (elementoCenario instanceof Grama) {
	                // Desenha a grama
	                g.drawImage(imagemGrama, elementoCenario.getX() * larguraImagens, elementoCenario.getY() * alturaImagens, larguraImagens, alturaImagens, null);

	                Grama gramaAtual = (Grama) elementoCenario;
	                
	                // Desenha a árvore se existir
	                if (gramaAtual.getArvore() != null) {
	                	String nomearvore = gramaAtual.getArvore().getClass().getName() + ".png";
	                	Image arvoreImage = new ImageIcon("res" + System.getProperty("file.separator") + nomearvore).getImage();
	                    g.drawImage(arvoreImage, elementoCenario.getX() * larguraImagens, elementoCenario.getY() * alturaImagens, larguraImagens, alturaImagens, null);
	                }
	                
	                // Desenha a fruta se existir
	                if (gramaAtual.getFruta() != null) {
	                	String nomefruta = gramaAtual.getFruta().getClass().getName() + ".png";
	                	Image frutaImage = new ImageIcon("res" + System.getProperty("file.separator") + nomefruta).getImage();
	                    g.drawImage(frutaImage, elementoCenario.getX() * larguraImagens + larguraImagens / 4, elementoCenario.getY() * alturaImagens + alturaImagens / 4, larguraImagens / 2, alturaImagens / 2, null);
	                }
	                
	                // Desenha o jogador se existir
	                if (gramaAtual.getJogador() != null) {
	                    g.drawImage(imagemJogador, gramaAtual.getJogador().getX() * larguraImagens + larguraImagens / 4, gramaAtual.getJogador().getY() * alturaImagens + alturaImagens / 4, larguraImagens / 2, alturaImagens / 2, null);
	                }
	            } else if (elementoCenario instanceof Pedra) {
	                // Desenha o terreno com pedra
	                g.drawImage(imagemTerra, elementoCenario.getX() * larguraImagens, elementoCenario.getY() * alturaImagens, larguraImagens, alturaImagens, null);
	                g.drawImage(imagemPedra, elementoCenario.getX() * larguraImagens, elementoCenario.getY() * alturaImagens, larguraImagens, alturaImagens, null);
	            }
	        }
	    }
	    revalidate();  // Atualiza o layout
		repaint();     // Para garantir que a pintura aconteça

	}
	
	@Override
	public EstadoView proximoEstado() {
		return this;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			jogo.movimentarJogador(1);
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			jogo.movimentarJogador(2);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jogo.movimentarJogador(3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			jogo.movimentarJogador(4);
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			jogo.finalizarTurno();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}