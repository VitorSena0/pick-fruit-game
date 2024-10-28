package modelo_jogo;

import java.awt.Image;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;

public class Jogador extends ElementoDinamico {
	private String nome;
	private Hashtable<String, LinkedList<Fruta>> mochila;
	private int capacidadeMochila;
	private int pontosDeMovimento;
	private boolean doente;
	private boolean forte;
	private String ultimaFruta = "";
	public String frutaComida = "";
	int acao = 0;
	
	@Override
	public void mover(int direcao) {
		if (pontosDeMovimento <= 0) {
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
		coordenadaX += dX;
		coordenadaY += dY;
		Set<String> keySet = mochila.keySet();
		for (String tipo : keySet) {
			LinkedList<Fruta> lista = mochila.get(tipo);
			for (Fruta fruta : lista) {
				fruta.mover(direcao);
			}
		}
		pontosDeMovimento--;
	}
	public int calcularPontosDeVitoria() {
		LinkedList<Fruta> maracujas = mochila.get("Maracuja");
		if (maracujas == null) {
			return 0;
		}
		else {
			return maracujas.size();
		}
	}
	public int totalDeFrutas() {
		Set<String> keySet = mochila.keySet();
		int soma = 0;
		for (String tipo: keySet) {
			soma += mochila.get(tipo).size();
		}
		return soma;
	}
	public int calcularForca() {
		int forcaBase = totalDeFrutas();
		if (forte) {
			return 2*forcaBase;
		}
		else {
			return forcaBase;
		}
	}
	public int getCapacidadeMochila() {
		return capacidadeMochila;
	}
	public LinkedList<String> tiposDeFrutasNaMochila() {
		LinkedList<String> tiposFrutas = new LinkedList<String>();
		Set<String> keySet = mochila.keySet();
		for (String tipo: keySet) {
			if (mochila.get(tipo).size() > 0) {
				tiposFrutas.add(tipo);
			}
		}
		return tiposFrutas;
	}
	public boolean catarFruta(Fruta fruta) {
		if (totalDeFrutas() >= capacidadeMochila) {
			//System.out.println(nome + " não conseguiu catar fruta pois a mochila está cheia");
			return false;
		}
		if (fruta.temBicho()) {
			doente = true;
		}
		String tipo = fruta.getClass().getSimpleName();
		LinkedList<Fruta> lista = mochila.get(tipo);
		if (lista == null) {
			LinkedList<Fruta> novaLista = new LinkedList<Fruta>();
			novaLista.add(fruta);
			mochila.put(tipo, novaLista);
		}
		else {
			mochila.get(tipo).add(fruta);
		}
		acao = fruta.temBicho() ? 10 : 11;
		frutaComida = fruta.getClass().getName().replaceFirst("modelo_jogo.", "");
		ultimaFruta = fruta.getClass().getName();
		return true;
	}
	public String getUltimaFruta() {
		return ultimaFruta;
	}
	public boolean comerFruta(String tipo) {
		LinkedList<Fruta> frutasDoTipo = mochila.get(tipo);
		if (frutasDoTipo == null) {
			//System.out.println(nome + " não conseguiu comer " + tipo + " pois não possui na mochila");
			frutaComida = tipo;
			acao = 12; 
			return false;
		}
		if (frutasDoTipo.size() == 0) {
			//System.out.println(nome + " não conseguiu comer " + tipo + " pois não possui na mochila");
			frutaComida = tipo;
			acao = 12;
			return false;
		}
		if (pontosDeMovimento <= 0) {
			//System.out.println(nome + " não conseguiu comer " + tipo + " pois não tem pontos de movimento");
			frutaComida = tipo;
			acao = 13;
			return false;
		}
		Fruta fruta = frutasDoTipo.pop();
		boolean consumida = fruta.serConsumida(this);
		if (consumida) {
			//System.out.println(nome + " comeu " + tipo);
			frutaComida = tipo;
			acao = 14;
			pontosDeMovimento--;
			return true;
		}
		//System.out.println(nome + " não conseguiu comer " + tipo + " pois não é comestível");
		frutaComida = tipo;
		acao = 15;
		frutasDoTipo.add(fruta);
		return false;
	}
	public void setDoente(boolean doente) {
		this.doente = doente;
	}
	public void setForte(boolean forte) {
		this.forte = forte;
	}
	public void setPontosMovimento(int pontos) {
		if (pontos < 0) {
			return;
		}
		pontosDeMovimento = pontos;
	}
	public String getNome() {
		return nome;
	}
	public boolean estaDoente() {
		return doente;
	}
	public boolean estaForte() {
		return forte;
	}
	public int movimentosRestantes() {
		return pontosDeMovimento;
	}
	public LinkedList<Fruta> serEmpurrado(Jogador empurrador) {
		int forcaDefensor = calcularForca();
		int forcaAtacante = empurrador.calcularForca();
		long empurrao = Math.round(Math.log(forcaAtacante+1)/Math.log(2)) - Math.round(Math.log(forcaDefensor+1)/Math.log(2));
		long qtdTeto = Math.max(0, empurrao);
		int totalFrutas = totalDeFrutas();
		long frutasDerrubadas = Math.min(totalFrutas, qtdTeto);
		LinkedList<Fruta> frutas = new LinkedList<Fruta>();
		if (frutasDerrubadas == 0) {
			return frutas;
		}
		Set<String> keySet = mochila.keySet();
		Object[] keyArray = keySet.toArray();
		String[] tiposFrutas = Arrays.copyOf(keyArray, keyArray.length, String[].class);
		Random gerador = new Random();
		int i = gerador.nextInt(tiposFrutas.length);
		int j = 0; 
		while (j < frutasDerrubadas) {
			LinkedList<Fruta> lista = mochila.get(tiposFrutas[i]);
			if (lista.size() > 0) {
				Fruta fruta = lista.pop();
				frutas.add(fruta);
				j++;
			}
			if (lista.size() <= 0) {
				//System.out.println(getNome() + " tem uma lista de frutas do tamanho de: " + lista.size());
			}
			i = (i + 1) % tiposFrutas.length;
		}
		return frutas; 
	}
	public int getAcao() {
		return acao;
	}
	public void zerarAcao() {
		acao = 0;
	}
	Jogador(String nome, int x, int y, int capacidadeMochila) {
		super(x, y);
		this.nome = nome;
		this.capacidadeMochila = capacidadeMochila;
		mochila = new Hashtable<String, LinkedList<Fruta>>();
		pontosDeMovimento = 0;
		doente = false;
		forte = false;
	}
}
