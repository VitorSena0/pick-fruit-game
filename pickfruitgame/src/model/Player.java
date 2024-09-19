package model;

public class Player extends ElementoDinamico{
    private Fruta[] mochila;
    private int totalFrutas;
    private int pontosMovimento;
    private Boolean efeitoForca;
    private Boolean efeitoBichada;
    Player(int x, int y, int capacidadeMochila) {
        super(x, y);
        mochila = new Fruta[capacidadeMochila];
        totalFrutas = 0;
        pontosMovimento = 0;
        efeitoForca = false;
        efeitoBichada = false;
    }
    public void mover(int direcao) {

    }
    public int calcularPontosDeVitoria() {
        return 0;
    }
    public int calcularForca() {
        return 0;
    }
    public void pegarFruta(Fruta novaFruta) {

    }
    public Fruta[] derrubarFrutas(int quantidadeFrutas) {
        return null;
    }
    public Fruta consumirFruta(String tipoFruta) {
        return null;
    }
    public void removerEfeitoForca() {
        efeitoForca = false;
    }
    public void removerEfeitoBichada() {
        efeitoBichada = false;
    }
}
