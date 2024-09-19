package model;

public class Fruta extends ElementoDinamico{
    private String tipo;
    private Boolean bichada;
    public Fruta(int x, int y, String tipo, Boolean estaBichada){
        super(x, y);
        this.tipo = tipo;
        this.bichada = estaBichada;
    }
    public void mover(int direcao){

    }
    public Boolean getBichada() {
        return bichada;
    }
}