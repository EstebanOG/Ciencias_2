/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Modelo {
    private boolean iniciaArbol = false;
    private ArrayList<Nodo> listaPalabras = new ArrayList<>();
   // private 
    private Nodo raiz,z;
    private Arbol arbol = new Arbol();
    
    public void inicializarArbol(){
        ArrayList<String> listaP = new ArrayList();
        listaP.add("d");
        listaP.add("o");
        listaP.add("s");
        listaP.add("}Two");
        this.arbol.insertar(listaP);
        listaP.clear();
        listaP.add("t");
        listaP.add("r");
        listaP.add("}");
        this.arbol.insertar(listaP);
        listaP.clear();
        listaP.add("u");
        listaP.add("}You");
        this.arbol.insertar(listaP);
        listaP.clear();
        listaP.add("t");
        listaP.add("r");
        listaP.add("e");
        listaP.add("s");
        listaP.add("}Three");
        this.arbol.insertar(listaP);
        listaP.clear();
        listaP.add("d");
        listaP.add("o");
        listaP.add("}C");
        this.arbol.insertar(listaP);
        listaP.clear();
        listaP.add("u");
        listaP.add("n");
        listaP.add("o");
        listaP.add("}One");
        this.arbol.insertar(listaP);
        listaP.clear();
       // arbol.inorden(arbol.getRaiz());
//        if(iniciaArbol==false){
//            this.iniciaArbol = true;
//            //raiz = new Nodo("?","?");
//            listaPalabras.add(raiz);
//        }else{
//            JOptionPane.showMessageDialog(null, "El árbol ya está inicializado.");
//        }
    }
    public void insetar(){
        
    }
    public ArrayList<Nodo> getListaPalabras() {
        return listaPalabras;
    }
    
    
}
