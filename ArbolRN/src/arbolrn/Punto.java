package arbolrn;

public class Punto {
    private NodoRN nodoRNX, nodoRNY;
    private int posX, posY;
    Punto(){
        this.posX = 0;
        this.posY = 0;
        this.nodoRNX = null;
        this.nodoRNY = null;
    }

    public NodoRN getNodoRNX() {
        return nodoRNX;
    }

    public void setNodoRNX(NodoRN nodoRNX) {
        this.nodoRNX = nodoRNX;
    }

    public NodoRN getNodoRNY() {
        return nodoRNY;
    }

    public void setNodoRNY(NodoRN nodoRNY) {
        this.nodoRNY = nodoRNY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
}
