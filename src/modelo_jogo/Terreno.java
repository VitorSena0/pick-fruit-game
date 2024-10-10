// package modelo_jogo;
// import java.util.Random;
// public class Terreno {
// 	private ElementoEstatico[][] floresta;
// 	private Fruta[] frutas;
// 	private int frutasOuroParaNascer;
// 	private int probabilidadeFrutaBichada;
	
// 	public ElementoEstatico getElementoFloresta(int x, int y) {
// 		return floresta[x][y];
// 	}
// 	public int getDimensao() {
// 		return floresta.length;
// 	}
	
// 	public Terreno(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas) {
// 		floresta = new ElementoEstatico[dimensao][dimensao];
// 		probabilidadeFrutaBichada = probabidade_bichadas;
// 		frutasOuroParaNascer = maracujas - maracujas_chao;
// 		//numero de posições no mapa
// 		int n = dimensao*dimensao;
// 		//cria um array com uma sequencia numérica de 0 até n
// 		int[] sequenciaPosicoes = new int[n];
// 		for (int i = 0; i < n; i++) {
// 			sequenciaPosicoes[i] = i;
// 		}
// 		//embaralha o array
// 		Random gerador = new Random();
// 		for (int i = 0; i < n; i++) {
// 			int j = gerador.nextInt(n);
// 			int temp = sequenciaPosicoes[i];
// 			sequenciaPosicoes[i] = sequenciaPosicoes[j];
// 			sequenciaPosicoes[j] = temp;
// 		}
// 		//coloca os elementos em posições aleatórias
// 		int fim = n - 1;
// 		//Árvores
// 		for (int i = 0; i < laranjeiras; i++) {
// 			int posicao = sequenciaPosicoes[fim];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			floresta[x][y] = new Laranjeira(x, y);
// 			fim--;
// 		}
// 		for (int i = 0; i < abacateiros; i++) {
// 			int posicao = sequenciaPosicoes[fim];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			floresta[x][y] = new Abacateiro(x, y);
// 			fim--;
// 		}
// 		for (int i = 0; i < pesDeAcerola; i++) {
// 			int posicao = sequenciaPosicoes[fim];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			floresta[x][y] = new Pe_de_Acerola(x, y);
// 			fim--;
// 		}
// 		for (int i = 0; i < coqueiros; i++) {
// 			int posicao = sequenciaPosicoes[fim];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			floresta[x][y] = new Coqueiro(x, y);
// 			fim--;
// 		}
// 		for (int i = 0; i < amoeiras; i++) {
// 			int posicao = sequenciaPosicoes[fim];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			floresta[x][y] = new Amoreira(x, y);
// 			fim--;
// 		}
// 		for (int i = 0; i < goiabeiras; i++) {
// 			int posicao = sequenciaPosicoes[fim];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			floresta[x][y] = new Goiabeira(x, y);
// 			fim--;
// 		}
// 		//Pedras
// 		for (int i = 0; i < pedras; i++) {
// 			int posicao = sequenciaPosicoes[fim];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			floresta[x][y] = new Pedra(x, y);
// 			fim--;
// 		}
// 		//Frutas
// 		frutas = new Fruta[laranjas + abacates + acerolas + cocos + amoras + goiabas + maracujas_chao];
// 		frutasOuroParaNascer = maracujas - maracujas_chao;
// 		int indice_posicao_fruta = 0;
// 		int indice_fruta = 0;
// 		for (int i = 0; i < maracujas_chao; i++) {
// 			int posicao = sequenciaPosicoes[indice_posicao_fruta];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
// 			frutas[indice_fruta] = new Maracuja(x, y, bichada);
// 			indice_fruta++;
// 			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
// 		}
// 		for (int i = 0; i < laranjas; i++) {
// 			int posicao = sequenciaPosicoes[indice_posicao_fruta];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
// 			frutas[indice_fruta] = new Laranja(x, y, bichada);
// 			indice_fruta++;
// 			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
// 		}
// 		for (int i = 0; i < abacates; i++) {
// 			int posicao = sequenciaPosicoes[indice_posicao_fruta];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
// 			frutas[indice_fruta] = new Abacate(x, y, bichada);
// 			indice_fruta++;
// 			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
// 		}
// 		for (int i = 0; i < cocos; i++) {
// 			int posicao = sequenciaPosicoes[indice_posicao_fruta];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
// 			frutas[indice_fruta] = new Coco(x, y, bichada);
// 			indice_fruta++;
// 			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
// 		}
// 		for (int i = 0; i < acerolas; i++) {
// 			int posicao = sequenciaPosicoes[indice_posicao_fruta];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
// 			frutas[indice_fruta] = new Acerola(x, y, bichada);
// 			indice_fruta++;
// 			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
// 		}
// 		for (int i = 0; i < amoras; i++) {
// 			int posicao = sequenciaPosicoes[indice_posicao_fruta];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
// 			frutas[indice_fruta] = new Amora(x, y, bichada);
// 			indice_fruta++;
// 			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
// 		}
// 		for (int i = 0; i < goiabas; i++) {
// 			int posicao = sequenciaPosicoes[indice_posicao_fruta];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			boolean bichada = gerador.nextInt(100) < probabilidadeFrutaBichada;
// 			frutas[indice_fruta] = new Goiaba(x, y, bichada);
// 			indice_fruta++;
// 			indice_posicao_fruta = (indice_posicao_fruta + 1) % (fim + 1);
// 		}
// 		//Grama
// 		while (fim >= 0) {
// 			int posicao = sequenciaPosicoes[fim];
// 			int x = posicao % dimensao;
// 			int y = posicao / dimensao;
// 			floresta[x][y] = new Grama(x, y);
// 			fim--;
// 		}
// 	}
// }