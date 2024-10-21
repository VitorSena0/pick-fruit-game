package modelo_jogo;

import java.util.LinkedList;
import java.util.Random;

public class Jogo {
	private Terreno terreno;
	private Jogador[] jogadores;
	private int rodada;
	private boolean houveEmpurraoRodada;
	private Integer[] dados;
	
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
	public boolean houveEmpurrao() {
		return houveEmpurraoRodada;
	}
	
	Jogo(Terreno terreno, int numeroJogadores, int capacidadeMochila, int quantidadeDeDados, String[] nomes) {
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
		dados = new Integer[quantidadeDeDados];
		Random gerador = new Random();
		for (int i = 0; i < numeroJogadores; i++) {
			Grama gramaOcupada = gramas.remove(gerador.nextInt(gramas.size()));
			jogadores[i] = new Jogador(nomes[i], gramaOcupada.getX(), gramaOcupada.getY(), capacidadeMochila);
			gramaOcupada.setJogador(jogadores[i]);
		}
		for (Integer dado : dados) {
			dado = 1;
		}
		houveEmpurraoRodada = false;
		rodada = 0;
	}
}
