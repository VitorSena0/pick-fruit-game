package personagens;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class JogadorTemporario extends ElementosDinamicos{
	
	private Image imagem;
	
	protected int qnqFrutas = 0;
	private String tipoJogador;
	protected Fruta[] mochila;
	private int width;
	private int heigth;
	protected int dimensaoGrid;
	protected int qtdMovimentos;
	
	JogadorTemporario(int posicaoX, int posicaoY, int dimensao,int dimensaoGrid, int mochila, ImageIcon referencia, int tipoJogador) {
		super(posicaoX, posicaoY, dimensao, referencia);
		this.imagem = referencia.getImage();
		this.width = dimensao + 1;
		this.heigth = dimensao + 1;
		this.dimensaoGrid = dimensaoGrid;
		this.mochila = new Fruta[mochila];
		this.tipoJogador = tipoJogador == 1 ? "player1" : "player2";
		
	}

	@Override
	public void load(Graphics g) {
		int tamanho = (int) (this.dimensaoGrid * 0.8); // Ajusta o tamanho para 80% da célula
	    int tamanhoX = tamanho + (int)(tamanho*0.3); 
	    int offsetX = (dimensaoGrid - tamanho) / 2;   // Calcula o offset para centralizar
	    int offsetY = (dimensaoGrid - tamanho) / 2;

        if(this.getQtdMovimentos() > 0){
            g.drawString("Movimentos restantes: " + this.getQtdMovimentos(), 50, 40);
            imagem = (tipoJogador.equals("player1")) ? new ImageIcon("res" + System.getProperty("file.separator") + "player1PixelartAura(2).png").getImage() : new ImageIcon("res" + System.getProperty("file.separator") +  "player2PixelartAura(2).png").getImage();
        }else{
            imagem = (tipoJogador.equals("player1")) ? new ImageIcon("res" + System.getProperty("file.separator") + "player1Pixelart.png").getImage() : new ImageIcon("res" + System.getProperty("file.separator") + "player2Pixelart.png").getImage();
        }

	    g.drawImage(imagem, getPosicaoX() + offsetX + dimensaoGrid, getPosicaoY() + offsetY + dimensaoGrid, tamanhoX, tamanho, null);
	}

	@Override
	public void acao() {
		// vai pegar uma fruta;
		
	}
	
	public int getQnqFrutas() {
		return qnqFrutas;
	}

	public void setQnqFrutas(int qnqFrutas) {
		this.qnqFrutas = qnqFrutas;
	}

	public Fruta[] getMochila() {
		return mochila;
	}

	public void setMochila(Fruta[] mochila) {
		this.mochila = mochila;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public void setQtdMovimentos(int qtdMovimentos){
		this.qtdMovimentos = qtdMovimentos;
	}

	/**
	 * Retorna a quantidade de movimentos realizados pelo jogador.
	 *
	 * @return O número de movimentos realizados.
	 */
	public int getQtdMovimentos(){
		return this.qtdMovimentos;
	}

	public void decrementarMovimento(int qtd){
		this.setQtdMovimentos(this.getQtdMovimentos() - qtd);
	}

	
}
