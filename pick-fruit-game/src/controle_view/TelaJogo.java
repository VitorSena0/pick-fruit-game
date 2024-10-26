package controle_view;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	private int dimensao;
	private JLabel painelFalas;
	private JTextArea areaDialogo;
	private Image scoreJogador1, socreJogador2; // Imagem da grama
	private ScorePlayers scorePlayers; // Score dos jogadores
	
	
	
	TelaJogo(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas, int mochila, String[] nomes) {
		mudarEstado = false;
		this.dimensao = dimensao;
		setBounds(0, 0, 986, 732);
		setLayout(new GridBagLayout());
		Terreno terreno = new Terreno(dimensao, pedras, maracujas, maracujas_chao, laranjeiras, laranjas, abacateiros, abacates, coqueiros, cocos, pesDeAcerola, acerolas, amoeiras, amoras, goiabeiras, goiabas, probabidade_bichadas);
		jogo = new Jogo(terreno, 2 ,mochila, 2, nomes);
		larguraImagens = (int)((getWidth() / jogo.getDimensao())*0.8);
		alturaImagens = (int)((getWidth() / jogo.getDimensao())*0.8);
        setFocusable(true); // Permite que o painel tenha foco para eventos de teclado
        addKeyListener(this); // Adiciona o KeyListener ao painel
        requestFocusInWindow(); // Solicita foco para o JPanel
        
        scorePlayers = new ScorePlayers(this, dimensao, jogo.getJogador(0), jogo.getJogador(1)); // Cria um novo score
        
        painelFalas = new JLabel("");
        painelFalas.setFont(new Font("Arial", Font.BOLD, 14));
        
        areaDialogo = new JTextArea(1,30);
        areaDialogo.setWrapStyleWord(true);
        areaDialogo.setLineWrap(true);
        areaDialogo.setEditable(false);
        areaDialogo.setFont(new Font("Arial", Font.PLAIN, 12));
        
        
        JPanel painel = new JPanel(new BorderLayout());  // Define um painel com BorderLayout
        painel.add(painelFalas, BorderLayout.NORTH);  // Label para nome do personagem na parte superior
        painel.add(new JScrollPane(areaDialogo), BorderLayout.SOUTH);  // Área de diálogo no centro

        // Configurações de layout para posicionar o painel de diálogo na parte inferior
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1; // Define o painel na "linha 1" (abaixo da linha 0)
        gbc.weightx = 1;
        gbc.weighty = 0;  // Deixa weighty como zero para evitar que o painel de diálogo ocupe espaço vertical demais
        gbc.anchor = GridBagConstraints.SOUTH; // Coloca o painel na parte inferior
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(getHeight()-175, 10, 10, 10); // Margens para não ficar grudado nas bordas

        add(painel, gbc); 
        
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
	
	int acao = 0;
	
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
	                    g.drawImage(gramaAtual.getJogador().getImagem(), gramaAtual.getJogador().getX() * larguraImagens + larguraImagens / 4, gramaAtual.getJogador().getY() * alturaImagens + alturaImagens / 4, larguraImagens / 2, alturaImagens / 2, null);
	                }
	            } else if (elementoCenario instanceof Pedra) {
	                // Desenha o terreno com pedra
	                g.drawImage(imagemTerra, elementoCenario.getX() * larguraImagens, elementoCenario.getY() * alturaImagens, larguraImagens, alturaImagens, null);
	                g.drawImage(imagemPedra, elementoCenario.getX() * larguraImagens, elementoCenario.getY() * alturaImagens, larguraImagens, alturaImagens, null);
	            }
	        }
	        scorePlayers.desenharScore(g);
	    }
	    
	    String vezDoJogador = "Vez do Jogador " + (jogo.getJogadorDaVez() + 1); // +1 para exibir como 1, 2, etc.
	    String quantidadeMovimentos = "A quantidade de movimentos é: " + (jogo.getJogador(jogo.getJogadorDaVez()).movimentosRestantes());
	    String vencedor =jogo.acao();
	    
	    painelFalas.setText("<html>" + vezDoJogador + "<br>" + quantidadeMovimentos + "</html>");

	    areaDialogo.append(vencedor+ "\n");
	    areaDialogo.setCaretPosition(areaDialogo.getDocument().getLength()); // Rola automaticamente para a última mensagem
	     
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
			acao = 1;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			jogo.movimentarJogador(2);
			acao = 2;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jogo.movimentarJogador(3);
			acao = 3;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			jogo.movimentarJogador(4);
			acao = 4;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			jogo.finalizarTurno();
		}
		else if (e.getKeyCode() == KeyEvent.VK_1) {
			jogo.jogadorConsumirFruta("Maracuja");
		}
		else if (e.getKeyCode() == KeyEvent.VK_2) {
			jogo.jogadorConsumirFruta("Coco");
		}
		else if (e.getKeyCode() == KeyEvent.VK_3) {
			jogo.jogadorConsumirFruta("Abacate");
		}
		else if (e.getKeyCode() == KeyEvent.VK_4) {
			jogo.jogadorConsumirFruta("Laranja");
		}
		else if (e.getKeyCode() == KeyEvent.VK_5) {
			jogo.jogadorConsumirFruta("Acerola");
		}
		else if (e.getKeyCode() == KeyEvent.VK_6) {
			jogo.jogadorConsumirFruta("Amora");
		}
		else if (e.getKeyCode() == KeyEvent.VK_7) {
			jogo.jogadorConsumirFruta("Goiaba");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
