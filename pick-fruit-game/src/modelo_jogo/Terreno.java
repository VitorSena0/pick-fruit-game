package modelo_jogo;
import java.util.Random;


/**
 * A classe Terreno representa um terreno em um jogo, onde elementos estáticos como árvores, pedras e frutas são dispostos em uma grade.
 * O terreno é inicializado com uma dimensão especificada e uma quantidade determinada de elementos que serão distribuídos aleatoriamente.
 */
public class Terreno {
	private ElementoEstatico[][] floresta;
	//private Fruta[] frutas;
	private int totalFrutasOuro;
	private int frutasOuroParaNascer;
	private int probabilidadeFrutaBichada;
	
	 /**
     * Retorna o elemento estático presente na posição especificada do terreno.
     *
     * @param x A coordenada x do terreno.
     * @param y A coordenada y do terreno.
     * @return O elemento estático na posição (x, y).
     */
	public ElementoEstatico getElementoFloresta(int x, int y) {
		return floresta[x][y];
	}

	/**
     * Retorna a dimensão do terreno (tamanho da grade).
     *
     * @return A dimensão do terreno.
     */
	public int getDimensao() {
		return floresta.length;
	}

	 /**
     * Retorna o total de frutas de ouro presentes no terreno.
     *
     * @return O total de frutas de ouro.
     */
	public int getTotalFrutasOuro() {
		return totalFrutasOuro;
	}

	/**
     * Retorna o número de frutas de ouro que ainda devem nascer.
     *
     * @return O número de frutas de ouro que ainda devem nascer.
     */
	public int getFrutasOuroParaNascer() {
		return frutasOuroParaNascer;
	}
	 /**
     * Retorna a probabilidade de uma fruta nascer bichada.
     *
     * @return A probabilidade de uma fruta bichada.
     */
	public int probabilidadeBichada() {
		return probabilidadeFrutaBichada;
	}

	/**
     * Decrementa o número de frutas de ouro que ainda devem nascer, indicando que uma fruta de ouro nasceu.
     */
	public void nasceuFrutaOuro() {
		if (frutasOuroParaNascer > 0) {
			frutasOuroParaNascer--;
		}
	}
	

    /**
     * Construtor que inicializa o terreno com a dimensão e os elementos especificados.
     *
     * @param dimensao A dimensão do terreno (número de células em cada lado).
     * @param pedras Número de pedras a serem colocadas no terreno.
     * @param maracujas Número total de maracujás a serem colocados no terreno.
     * @param maracujas_chao Número de maracujás que já estão no chão.
     * @param laranjeiras Número de laranjeiras a serem colocadas.
     * @param laranjas Número de laranjas a serem colocadas.
     * @param abacateiros Número de abacateiros a serem colocados.
     * @param abacates Número de abacates a serem colocados.
     * @param coqueiros Número de coqueiros a serem colocados.
     * @param cocos Número de cocos a serem colocados.
     * @param pesDeAcerola Número de pés de acerola a serem colocados.
     * @param acerolas Número de acerolas a serem colocadas.
     * @param amoeiras Número de amoreiras a serem colocadas.
     * @param amoras Número de amoras a serem colocadas.
     * @param goiabeiras Número de goiabeiras a serem colocadas.
     * @param goiabas Número de goiabas a serem colocadas.
     * @param probabidade_bichadas Probabilidade de uma fruta ser bichada.
     */
	public Terreno(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas) {
		floresta = new ElementoEstatico[dimensao][dimensao];
		totalFrutasOuro = maracujas;
		probabilidadeFrutaBichada = probabidade_bichadas;
		frutasOuroParaNascer = maracujas - maracujas_chao;
		//numero de posições no mapa
		int n = dimensao*dimensao;
		//cria um array com uma sequencia numérica de 0 até n
		int[] sequenciaPosicoes = new int[n];
		for (int i = 0; i < n; i++) {
			sequenciaPosicoes[i] = i;
		}
		//embaralha o array
		Random gerador = new Random();
		for (int i = 0; i < n; i++) {
			int j = gerador.nextInt(n);
			int temp = sequenciaPosicoes[i];
			sequenciaPosicoes[i] = sequenciaPosicoes[j];
			sequenciaPosicoes[j] = temp;
		}
		//coloca os elementos em posições aleatórias
		int fim = n - 1;
		//Árvores
		for (int i = 0; i < laranjeiras; i++) {
			int posicao = sequenciaPosicoes[fim];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			floresta[x][y] = new Grama(x, y, new Laranjeira(x, y));
			fim--;
		}
		for (int i = 0; i < abacateiros; i++) {
			int posicao = sequenciaPosicoes[fim];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			floresta[x][y] = new Grama(x, y, new Abacateiro(x, y));
			fim--;
		}
		for (int i = 0; i < pesDeAcerola; i++) {
			int posicao = sequenciaPosicoes[fim];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			floresta[x][y] = new Grama(x, y, new Pe_de_Acerola(x, y));
			fim--;
		}
		for (int i = 0; i < coqueiros; i++) {
			int posicao = sequenciaPosicoes[fim];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			floresta[x][y] = new Grama(x, y, new Coqueiro(x, y));
			fim--;
		}
		for (int i = 0; i < amoeiras; i++) {
			int posicao = sequenciaPosicoes[fim];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			floresta[x][y] = new Grama(x, y, new Amoreira(x, y));
			fim--;
		}
		for (int i = 0; i < goiabeiras; i++) {
			int posicao = sequenciaPosicoes[fim];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			floresta[x][y] = new Grama(x, y, new Goiabeira(x, y));
			fim--;
		}
		//Pedras
		for (int i = 0; i < pedras; i++) {
			int posicao = sequenciaPosicoes[fim];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			floresta[x][y] = new Pedra(x, y);
			fim--;
		}
		//Frutas
		Fruta[] frutas = new Fruta[laranjas + abacates + acerolas + cocos + amoras + goiabas + maracujas_chao];
		frutasOuroParaNascer = maracujas - maracujas_chao;
		int indice_posicao_fruta = 0;
		int indice_fruta = 0;
		for (int i = 0; i < maracujas_chao; i++) {
			int posicao = sequenciaPosicoes[indice_posicao_fruta];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
			frutas[indice_fruta] = new Maracuja(x, y, bichada);
			System.out.println("Maracujá nasceu em " + frutas[indice_fruta].getX() + " " + frutas[indice_fruta].getY());
			indice_fruta++;
			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
		}
		for (int i = 0; i < laranjas; i++) {
			int posicao = sequenciaPosicoes[indice_posicao_fruta];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
			frutas[indice_fruta] = new Laranja(x, y, bichada);
			indice_fruta++;
			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
		}
		for (int i = 0; i < abacates; i++) {
			int posicao = sequenciaPosicoes[indice_posicao_fruta];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
			frutas[indice_fruta] = new Abacate(x, y, bichada);
			indice_fruta++;
			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
		}
		for (int i = 0; i < cocos; i++) {
			int posicao = sequenciaPosicoes[indice_posicao_fruta];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
			frutas[indice_fruta] = new Coco(x, y, bichada);
			indice_fruta++;
			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
		}
		for (int i = 0; i < acerolas; i++) {
			int posicao = sequenciaPosicoes[indice_posicao_fruta];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
			frutas[indice_fruta] = new Acerola(x, y, bichada);
			indice_fruta++;
			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
		}
		for (int i = 0; i < amoras; i++) {
			int posicao = sequenciaPosicoes[indice_posicao_fruta];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
			frutas[indice_fruta] = new Amora(x, y, bichada);
			indice_fruta++;
			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
		}
		for (int i = 0; i < goiabas; i++) {
			int posicao = sequenciaPosicoes[indice_posicao_fruta];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
			frutas[indice_fruta] = new Goiaba(x, y, bichada);
			indice_fruta++;
			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
		}
		//Grama
		for (int i = 0; i <= fim; i++) {
			int posicao = sequenciaPosicoes[i];
			int x = posicao % dimensao;
			int y = posicao / dimensao;
			Grama grama =  new Grama(x, y);
			if (i < frutas.length) {
				grama.setFruta(frutas[i]);
			}
			floresta[x][y] = grama;
		}
	}
}