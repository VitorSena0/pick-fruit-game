package model;

public class ElementoEstatico extends ElementoCoordenadas{
    private final String tipo;
    ElementoEstatico(String tipo, int x, int y){
        super(x, y);
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
}