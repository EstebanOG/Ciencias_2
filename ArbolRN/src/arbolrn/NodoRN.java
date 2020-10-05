package arbolrn;

public class NodoRN {
    private  int llave;
    private String nombre;
    private int color;
    private NodoRN izq;
    private NodoRN der;
    
    public NodoRN(int llave,int color){
        this.llave = llave;
        //this.nombre = nombre;
        this.color=color;
        //this.izq=this.der=z;
    }
    public NodoRN(int llave){
        this.llave = llave;
    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public NodoRN getIzq() {
        return izq;
    }

    public void setIzq(NodoRN izq) {
        this.izq = izq;
    }

    public NodoRN getDer() {
        return der;
    }

    public void setDer(NodoRN der) {
        this.der = der;
    }
    
}
