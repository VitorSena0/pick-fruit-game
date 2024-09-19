package model;

import java.util.Scanner;
public class Terreno {
    private ElementoEstatico[][] floresta;
    private Fruta[] frutas;
    private int frutasOuroParaNascer;
    private int capacidadeMochila;
    private int probabilidadeFrutaBichada;
    public Terreno(String configuracoes) {

    }
    public Terreno(Scanner arquivo) {

    }
    public ElementoEstatico getElementoFloresta(int posX, int posY) {
        return floresta[posX][posY];
    }
    public int getDimensao() {
        return floresta.length;
    }
    public int getCapacidadeMochila() {
        return capacidadeMochila;
    }
    public void adicionaFruta(Fruta fruta) {

    }
    public void removerFruta(Fruta fruta) {

    }
    public void gerarFrutaOuro() {

    }
    public Fruta getFruta(int posX, int posY) {
        return null;
    }
}
