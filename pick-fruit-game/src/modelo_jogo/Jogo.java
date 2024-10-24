package modelo_jogo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Jogo {
	private Terreno terreno;
	private Jogador[] jogadores;
	private int rodada;
	private int jogadorDaVez;
	private Integer jogadorVencedor;
	private Arvore arvoreMaracujaAnterior;
	private boolean houveEmpurraoTurno;
	private int[] dados;
	
	public ElementoEstatico getElementoTerreno(int x, int y) {
		return terreno.getElementoFloresta(x, y);
	}
	public Jogador getJogador(int i) {
		return jogadores[i];
	}
	public int getRodada() {
		return rodada;
	}
	public int getDado(int i) {
		return dados[i];
	}
	public int getJogadorDaVez() {
		return jogadorDaVez;
	}
	public boolean houveEmpurrao() {
		return houveEmpurraoTurno;
	}
	public void gerarFrutaOuro() {
		if (terreno.getFrutasOuroParaNascer() <= 0) {
			return;
		}
		int dimensao = terreno.getDimensao();
		int fim = dimensao - 1;
		Random gerador = new Random();
		HashMap<Arvore,Grama> vizinhancasLivresSemFruta = new HashMap<Arvore,Grama>();
		HashMap<Arvore,Grama> vizinhancasLivresComFruta = new HashMap<Arvore,Grama>();
		for (int n = 0; n < dimensao; n++)
		{
			for (int m = 0; m < dimensao; m++)
			{
				ElementoEstatico elemento = terreno.getElementoFloresta(n, m);
				if (elemento instanceof Grama) {
					Grama grama = (Grama) elemento;
					Arvore arvore = grama.getArvore();
					if (arvore == null || arvore == arvoreMaracujaAnterior) {
						continue;
					}
					int arvoreX = arvore.getX();
					int arvoreY = arvore.getY();
					int indiceI  = gerador.nextInt(3);
					int indiceJ = indiceI;
					int[] deslocamentos = {-1, 0, 1};
					for (int k = -1; k < 2; k++)
					{
						int i = deslocamentos[indiceI];
						boolean encontrado = false;
						for (int l = -1; l < 2; l++)
						{
							int j = deslocamentos[indiceJ];
							if (arvoreX + i < 0 || arvoreX + i > fim || arvoreY + j < 0 || arvoreY + j > fim || (i == 0 && j == 0)) {
								continue;
							}
							ElementoEstatico elementoVizinho = terreno.getElementoFloresta(arvoreX + i, arvoreY + j);
							if (elementoVizinho instanceof Grama) {
								Grama gramaVizinha = (Grama) elementoVizinho;
								if (gramaVizinha.getArvore() == null) {
									if (gramaVizinha.getFruta() == null) {
										vizinhancasLivresSemFruta.put(arvore, gramaVizinha);
										encontrado = true;
										break;
									}
									else if (!(gramaVizinha.getFruta() instanceof Maracuja)) {
										vizinhancasLivresComFruta.put(arvore, gramaVizinha);
										encontrado = true;
										break;
									}
								}
								}
							indiceJ = (indiceJ + 1) % 3;
						}
						if (encontrado) {
							break;
						}
						indiceI = (indiceI + 1) % 3;
					}
				}
			}
		}
		if (vizinhancasLivresComFruta.isEmpty() && vizinhancasLivresSemFruta.isEmpty() && arvoreMaracujaAnterior != null) {
			int arvoreX = arvoreMaracujaAnterior.getX();
			int arvoreY = arvoreMaracujaAnterior.getY();
			int indiceI  = gerador.nextInt(3);
			int indiceJ = indiceI;
			int[] deslocamentos = {-1, 0, 1};
			for (int k = -1; k < 2; k++)
			{
				int i = deslocamentos[indiceI];
				boolean encontrado = false;
				for (int l = -1; l < 2; l++)
				{
					int j = deslocamentos[indiceJ];
					if (arvoreX + i < 0 || arvoreX + i > fim || arvoreY + j < 0 || arvoreY + j > fim || (i == 0 && j == 0)) {
						continue;
					}
					ElementoEstatico elementoVizinho = terreno.getElementoFloresta(arvoreX + i, arvoreY + j);
					if (elementoVizinho instanceof Grama) {
						Grama gramaVizinha = (Grama) elementoVizinho;
						if (gramaVizinha.getArvore() == null) {
							if (gramaVizinha.getFruta() == null) {
								vizinhancasLivresSemFruta.put(arvoreMaracujaAnterior, gramaVizinha);
								encontrado = true;
								break;
							}
							else if (!(gramaVizinha.getFruta() instanceof Maracuja)) {
								vizinhancasLivresComFruta.put(arvoreMaracujaAnterior, gramaVizinha);
								encontrado = true;
								break;
							}
						}
						}
					indiceJ = (indiceJ + 1) % 3;
				}
				if (encontrado) {
					break;
				}
				indiceI = (indiceI + 1) % 3;
			}
		}
		if (!vizinhancasLivresSemFruta.isEmpty()) {
			int numero = gerador.nextInt(100);
			boolean bichada = numero < terreno.probabilidadeBichada();
			int i = gerador.nextInt(vizinhancasLivresSemFruta.keySet().size());
			Arvore arvore = (Arvore) vizinhancasLivresSemFruta.keySet().toArray()[i];
			Grama grama = vizinhancasLivresSemFruta.get(arvore);
			grama.setFruta(new Maracuja(grama.getX(), grama.getX(), bichada));
			arvoreMaracujaAnterior = arvore;
			terreno.nasceuFrutaOuro();
			return;
		}
		if (!vizinhancasLivresComFruta.isEmpty()) {
			int numero = gerador.nextInt(100);
			boolean bichada = numero < terreno.probabilidadeBichada();
			int i = gerador.nextInt(vizinhancasLivresComFruta.keySet().size());
			Arvore arvore = (Arvore) vizinhancasLivresComFruta.keySet().toArray()[i];
			Grama grama = vizinhancasLivresComFruta.get(arvore);
			grama.setFruta(new Maracuja(grama.getX(), grama.getX(), bichada));
			arvoreMaracujaAnterior = arvore;
			terreno.nasceuFrutaOuro();
			return;
		}
	}
	public void rolarDados() {
		Random gerador = new Random();
		for (int i = 0; i < dados.length; i++) {
			dados[i] = gerador.nextInt(1, 7);
		}
	}
	public void proximaRodada() {
		if (jogadorVencedor != null) {
			return;
		}
		rodada++;
		jogadorDaVez = 0;
		rolarDados();
		for (int i = 0; i < jogadores.length; i++) {
			if (jogadores[i].calcularPontosDeVitoria() >= Math.floor(terreno.getTotalFrutasOuro()/2) + 1) {
				jogadorVencedor = i;
				return;
			}
		}
		int somaDados = Arrays.stream(dados).sum();
		for (int i = 0; i < jogadores.length; i++) {
			jogadores[i].setPontosMovimento(somaDados);
		}
		for (int i = 0; i < terreno.getDimensao(); i++)
		{
			for (int j = 0; j < terreno.getDimensao(); j++)
			{
				ElementoEstatico elementoFloresta = terreno.getElementoFloresta(i, j);
				if (elementoFloresta instanceof Grama) {
					Grama gramaFloresta = (Grama) elementoFloresta;
					if (gramaFloresta.getArvore() != null) {
						gramaFloresta.getArvore().atualizarCronometro();
					}
				}
			}
		}
		arvoreMaracujaAnterior = null;
		if (rodada % 2 == 0) {
			gerarFrutaOuro();
		}
	}
	public void finalizarTurno() {
		if (jogadorVencedor != null) {
			return;
		}
		ElementoEstatico elementoPosicaoJogador = terreno.getElementoFloresta(jogadores[jogadorDaVez].getX(), jogadores[jogadorDaVez].getY());
		if (elementoPosicaoJogador instanceof Grama) {
			Grama gramaPosicaoJogador = (Grama) elementoPosicaoJogador;
			if (gramaPosicaoJogador.getArvore() != null)
			{
				Random gerador = new Random();
				int numeroSorteado = gerador.nextInt(100);
				boolean bichada = numeroSorteado < terreno.probabilidadeBichada();
				Fruta fruto = gramaPosicaoJogador.getArvore().gerarFruta(bichada, false);
				if (fruto != null) {
					jogadores[jogadorDaVez].catarFruta(fruto);
				}
			}
		}
		jogadorDaVez++;
		houveEmpurraoTurno = false;
		if (jogadorDaVez > jogadores.length) {
			proximaRodada();
		}
		else if (rodada % 2 == 0) {
			gerarFrutaOuro();
		}
	}
	public void movimentarJogador(int direcao) {
		if (jogadorVencedor != null) {
			return;
		}
		if (direcao < 1 || direcao > 4) {
			return;
		}
		if (jogadores[jogadorDaVez].movimentosRestantes() <= 0) {
			return;
		}
		int x = jogadores[jogadorDaVez].getX();
		int y = jogadores[jogadorDaVez].getY();
		int fim = terreno.getDimensao() - 1;
		if ((direcao == 1 && y == 0) || (direcao == 2 && x == 0) || (direcao == 3 && x == fim) || (direcao == 4 && y == fim)) {
			return;
		}
		int dX = 0;
		int dY = 0;
		if (direcao == 1) {
			dY -= 1;
		}
		else if (direcao == 2) {
			dX -= 1;
		}
		else if (direcao == 3) {
			dX += 1;
		}
		else if (direcao == 4) {
			dY += 1;
		}
		ElementoEstatico elementoProximaPosicao = terreno.getElementoFloresta(x + dX, y + dY);
		if (elementoProximaPosicao instanceof Grama) {
			Grama grama = (Grama) elementoProximaPosicao;
			if (grama.getJogador() == null) {
				jogadores[jogadorDaVez].mover(direcao);
				if (grama.getFruta() != null) {
					boolean catouFruta = jogadores[jogadorDaVez].catarFruta(grama.getFruta());
					if (catouFruta) {
						grama.setFruta(null);
					}
				}
			}
			else {
				if (houveEmpurraoTurno) {
					return;
				}
				Jogador jogadorEmpurrado = grama.getJogador();
				LinkedList<Fruta> frutasDerrubadas = jogadorEmpurrado.serEmpurrado(jogadores[jogadorDaVez]);
				houveEmpurraoTurno = true;
				int empurradoX = jogadorEmpurrado.getX();
				int empurradoY = jogadorEmpurrado.getY();
				int[] direcoesLivres = new int[8];
				int totalDirecoesLivres = 0;
				for (int i = -1; i < 2; i++)
				{
					for (int j = -1; j < 2; j++)
					{
						if (empurradoX + i < 0 || empurradoX + i > fim || empurradoY + j < 0 || empurradoY + j > fim || (i == 0 && j == 0)) {
							continue;
						}
						ElementoEstatico elementoVizinho = terreno.getElementoFloresta(empurradoX + i, empurradoY + j);
						if (elementoVizinho instanceof Grama) {
							Grama gramaVizinha = (Grama) elementoVizinho;
							if (gramaVizinha.getArvore() == null && gramaVizinha.getFruta() == null)
							{
								if (i == -1 && j == -1)
								{
									direcoesLivres[totalDirecoesLivres] = 5;
								}
								else if (i == 0 && j == -1)
								{
									direcoesLivres[totalDirecoesLivres] = 1;
								}
								else if (i == 1 && j == -1)
								{
									direcoesLivres[totalDirecoesLivres] = 6;
								}
								else if (i == -1 && j == 0)
								{
									direcoesLivres[totalDirecoesLivres] = 2;
								}
								else if (i == 1 && j == 0)
								{
									direcoesLivres[totalDirecoesLivres] = 3;
								}
								else if (i == -1 && j == 1)
								{
									direcoesLivres[totalDirecoesLivres] = 7;
								}
								else if (i == 0 && j == 1)
								{
									direcoesLivres[totalDirecoesLivres] = 4;
								}
								else if (i == 1 && j == 1)
								{
									direcoesLivres[totalDirecoesLivres] = 8;
								}
								totalDirecoesLivres++;
							}
						}
					}
				}
				int i = 0;
				while (i < totalDirecoesLivres && !frutasDerrubadas.isEmpty()) {
					Fruta frutaDerrubada = frutasDerrubadas.pop();
					frutaDerrubada.mover(direcoesLivres[i]);
					i++;
				}
				boolean doente = jogadorEmpurrado.estaDoente();
				while (!frutasDerrubadas.isEmpty()) {
					Fruta frutaDerrubada = frutasDerrubadas.pop();
					jogadorEmpurrado.catarFruta(frutaDerrubada);
				}
				if (!doente) {
					jogadorEmpurrado.setDoente(false);
				}
			}
		}
		else if (elementoProximaPosicao instanceof Pedra) {
			int xFinal = x + 2*dX;
			int yFinal = y + 2*dY;
			if (xFinal < 0 || xFinal > fim || yFinal < 0 || yFinal > fim) {
				return;
			}
			if (jogadores[jogadorDaVez].movimentosRestantes() < 3) {
				return;
			}
			ElementoEstatico elementoPosicaoFinal = terreno.getElementoFloresta(xFinal, yFinal);
			if (elementoPosicaoFinal instanceof Pedra) {
				return;
			}
			else if (elementoPosicaoFinal instanceof Grama) {
				Grama grama = (Grama) elementoPosicaoFinal;
				if (grama.getJogador() != null) {
					return;
				}
				jogadores[jogadorDaVez].mover(direcao);
				jogadores[jogadorDaVez].setPontosMovimento(jogadores[jogadorDaVez].movimentosRestantes() - 1);
				jogadores[jogadorDaVez].mover(direcao);
				if (grama.getFruta() != null) {
					boolean catouFruta = jogadores[jogadorDaVez].catarFruta(grama.getFruta());
					if (catouFruta) {
						grama.setFruta(null);
					}
				}
			}
		}
		if (jogadores[jogadorDaVez].movimentosRestantes() <= 0) {
			finalizarTurno();
		}
	}
	
	public Jogo(Terreno terreno, int numeroJogadores, int capacidadeMochila, int quantidadeDeDados, String[] nomes) {
		this.terreno = terreno;
		LinkedList<Grama> gramas = new LinkedList<Grama>();
		int dimensao = terreno.getDimensao();
		for (int n = 0; n < dimensao; n++)
		{
			for (int m = 0; m < dimensao; m++)
			{
				if (terreno.getElementoFloresta(n, m) instanceof Grama) {
					gramas.add((Grama) terreno.getElementoFloresta(n, m));
					
				}
			}
		}
		jogadores = new Jogador[numeroJogadores];
		dados = new int[quantidadeDeDados];
		Random gerador = new Random();
		for (int i = 0; i < numeroJogadores; i++) {
			Grama gramaOcupada = gramas.remove(gerador.nextInt(gramas.size()));
			jogadores[i] = new Jogador(nomes[i], gramaOcupada.getX(), gramaOcupada.getY(), capacidadeMochila);
			gramaOcupada.setJogador(jogadores[i]);
		}
		for (int i = 0; i < dados.length; i++) {
			dados[i] = 1;
		}
		houveEmpurraoTurno = false;
		rodada = 0;
		jogadorDaVez = 0;
		jogadorVencedor = null;
		arvoreMaracujaAnterior = null;
	}
}
