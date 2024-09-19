package model;

public class Arvore extends ElementoEstatico{
    public String tipoFruta;
    public int cronometroFruta;
    public Arvore(int x, int y, String tipoDeFruta){
        super("Arvore", x, y);
        this.tipoFruta = tipoDeFruta;
        cronometroFruta = 0;
    }
    public void atualizarCronometro() {

    }
    public Fruta gerarFruta(Boolean frutaBichada) {
        return null;
    }
}
