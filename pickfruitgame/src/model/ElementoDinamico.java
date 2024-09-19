package model;

abstract public class ElementoDinamico extends ElementoCoordenadas{
    abstract public void mover(int direcao);
    ElementoDinamico(int x, int y) {
        super(x, y);
    }
}
