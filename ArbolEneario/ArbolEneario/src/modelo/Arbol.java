/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Arbol {
    private Nodo raiz,n,m,nodoTemp;
    private ArrayList<Nodo> ramDerNull;
    public Arbol(){
        Nodo raiz1 = new Nodo("{");
        this.raiz = raiz1;
        Nodo raiz2 = new Nodo("}\n");
        raiz.addRam(raiz2);
        Nodo nodoTemp1 = new Nodo("");
        this.nodoTemp = nodoTemp1;
        
        //Método resetear respuesta
    }

    public Nodo getRaiz() {
        return raiz;
    }
    
    public void insertar(ArrayList<String> entry){
        //System.out.println("La raiz en insertar es:"+ raiz.getInfo());
        
        this.n = getRaiz();
        while((n instanceof Nodo) && !entry.isEmpty()){
            this.m = n;
            this.n = n.getRam(entry.get(0));
            if((n instanceof Nodo) && n != null){
                entry.remove(0);
            }
        }
        ///System.out.println(entry.size());
        System.out.println("[" + m.getInfo());
        for(int i = 0; i<entry.size();i++){
            System.out.println(entry.get(i));
        }
        System.out.println("]");
        for(int i = 0; i<entry.size();i++){
            //System.out.println(entry.get(i));
            
            this.nodoTemp.setInfo(entry.get(i));
            this.nodoTemp.setRamIzq(null);
            
            if(nodoTemp == null){
                System.out.println("Sí, nodoTemp es null");
            }
            if(m == null){
                System.out.println("Sí, m es null");
            }
            System.out.println("Clave de m:"+m.getInfo());
            if(m.getRamIzq() == null){
                System.out.println("Sí, m izq es null");
            }else{
                System.out.println("Clave izq de m:"+m.getRamIzq().getInfo());
            }
            
            this.m.addRam(nodoTemp);
            //System.out.println("Parametro que se pasa a m:"+entry.get(i));
            this.m = m.getRam(entry.get(i));
        }
        System.out.println("Al final de 1ra iteracion: "+ raiz.getRamIzq().getInfo());
    }
    
    public void inorden(Nodo rai){
        System.out.println("La raiz es:"+rai.getInfo());
        if(rai != null){
            System.out.println("La raiz izquierda es: "+ rai.getRamIzq().getInfo());
            inorden(rai.getRamIzq());
            System.out.println(rai.getInfo());
            for(int i = 0; i<rai.getRamDer().size();i++){
                inorden(rai.getRamDer().get(i));
            }
        }
    }
}
