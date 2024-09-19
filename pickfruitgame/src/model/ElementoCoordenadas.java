package model;

public class ElementoCoordenadas {
    protected int coordenadaX;
    protected int coordenadaY;
    ElementoCoordenadas(int x, int y) {
        this.coordenadaX = x;
        this.coordenadaY = y;
    }
    public int getX() {
        return coordenadaX;
    }
    public int getY() {
        return coordenadaY;
    }
}
