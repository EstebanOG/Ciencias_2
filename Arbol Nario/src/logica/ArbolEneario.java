package logica;

import java.util.ArrayList;

public class ArbolEneario {

    public Nodo raiz;
    public ArrayList<String> in = new ArrayList<String>();
    public ArrayList<String> pre = new ArrayList<String>();
    public ArrayList<String> pos = new ArrayList<String>();
    public ArbolEneario() {
        this.raiz = new Nodo('?');
    }

    public void addPalabra(String palabra, String palabraT) {
        Nodo sig = this.raiz;
        for (int i = 0; i < palabra.length(); i++) {

            sig = sig.addHijo(palabra.charAt(i));
        }
        sig.addFinal(palabraT);
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void inorden(Nodo rai) {
        if (rai != null) {
            if (rai.getHijos().size() > 0) {
                inorden(rai.getHijos().get(0));
            }
            System.out.println(Character.toString(rai.getValor()));
            this.in.add(Character.toString(rai.getValor()));
            for (int i = 1; i < rai.getHijos().size(); i++) {

                inorden(rai.getHijos().get(i));
            }
        }

    }
    
    public void preorden(Nodo rai){
        if (rai != null) {
            System.out.println(Character.toString(rai.getValor()));
            this.pre.add(Character.toString(rai.getValor()));
            if (rai.getHijos().size() > 0) {
                preorden(rai.getHijos().get(0));
            }
            for (int i = 1; i < rai.getHijos().size(); i++) {

                preorden(rai.getHijos().get(i));
            }
        }
    }
    
    public void posorden(Nodo rai){
        if (rai != null) {
            if (rai.getHijos().size() > 0) {
                posorden(rai.getHijos().get(0));
            }
            for (int i = 1; i < rai.getHijos().size(); i++) {

                posorden(rai.getHijos().get(i));
            }
            this.pos.add(Character.toString(rai.getValor()));
            System.out.println(Character.toString(rai.getValor()));
        }
    }

    public ArrayList<String> getIn() {
        return in;
    }

    public ArrayList<String> getPre() {
        return pre;
    }

    public ArrayList<String> getPos() {
        return pos;
    }

    public void limpiarIn() {
        this.in.clear();
    }

    public void limpiarPre() {
        this.pre.clear();
    }
    
    public void limpiarPos() {
        this.pos.clear();
    }

}
