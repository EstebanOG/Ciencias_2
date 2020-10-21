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
public class Nodo {
    private String info="";
    private Nodo ramIzq = null;
    private Nodo t = null;
    private ArrayList<Nodo> ramDer = new ArrayList<>();
    private ArrayList<Nodo> nodosIzqDer = new ArrayList<>();
    public Nodo(String info){
        this.info= info;
        this.ramIzq = null;
        if(ramDer.isEmpty()){
        }else{
            this.ramDer.clear();
        }
        
    }
    public Nodo(){
        
    }
    public String getInfo() {
        return info;
    }

    public Nodo getRamIzq() {
        return ramIzq;
    }

    public ArrayList<Nodo> getRamDer() {
        return ramDer;
    }

    public Nodo getRamN(int n){
        if(n==0){
            return getRamIzq();
        }
        return getRamDer().get(n-1);
    }
    
    public int getN(){
        if(getRamIzq()!=null){
            return (getRamDer().size() + 1);
        }else{
            return 0;
        }
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setRamIzq(Nodo ramIzq) {
        this.ramIzq = ramIzq;
    }

    public void setRamDer(ArrayList<Nodo> ramDer) {
        this.ramDer = ramDer;
    }
    
    public void setRamN(int n, Nodo direcc){
        if(n==0){
            this.ramIzq = direcc;
        }else{
            this.ramDer.set(n-1,direcc);
        }
    }
    public Nodo getRam(String info){
        boolean justInd = false;
        int j = 0;
        nodosIzqDer.clear();
        nodosIzqDer.add(getRamIzq());
        for(int i = 0;i<getRamDer().size();i++){
            nodosIzqDer.add(getRamDer().get(i));
        }
        
        for(int k = 0; k <nodosIzqDer.size();k++){
            if( nodosIzqDer.get(k) == null){
                return null;
            }else if(nodosIzqDer.get(k).info == info){
                if(justInd){
                    return null;
                }else{
                    return nodosIzqDer.get(k);
                }
            }else{
                j+=1;
            }
        }
        return null;//Revisar función
    }
    
    public void addRam(Nodo direcc){
        System.out.println(direcc.getInfo());
        if(ramIzq == null){
            System.out.println("Rama izq none");
            this.ramIzq = direcc;
            //System.out.println("Se agrega "+ direcc.getInfo()+" a la ramma izquierda");
        }else if(ramIzq.getInfo().compareTo(direcc.getInfo()) < 0 ){
            System.out.println("Clave rama izq < info");
            //System.out.println(ramDer.size() + "Antes de agregar null");
            this.ramDer.add(null);
            //System.out.println(ramDer.size() + "Despues de agregar null");
            for(int i = 0;i<ramDer.size();i++){
                if(ramDer.get(i)==null){
                    //System.out.println("La rama derecha es null");
                    this.ramDer.set(i, direcc);
                    //System.out.println("Se agrega "+direcc.getInfo()+ "En la pos derecha:"+ i);
                    break;
                }else if(ramDer.get(i).getInfo().compareTo(direcc.getInfo()) > 0){
                    this.ramDer.add(i, direcc);
                   // System.out.println("Se agrega "+direcc.getInfo()+ "En la pos derecha:"+ i);
                    this.ramDer.remove(ramDer.size()-1);
                    break;
                }
            }
        }else if(ramIzq.getInfo().compareTo(direcc.getInfo()) > 0){
            System.out.println("Clave rama izq > info");
            this.t = ramIzq;
            this.setRamIzq(direcc);
            this.addRam(t);
        }
        else{
            System.out.println("Se repite");
            System.out.println(ramIzq.getInfo());
            System.out.println(direcc.getInfo());
            System.out.println("Llave repetida.");
            //JOptionPane.showMessageDialog(null, "Llave repetida.");
        }
    }
}
