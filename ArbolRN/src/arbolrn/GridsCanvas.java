package arbolrn;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;

public class GridsCanvas extends Canvas {
    int width, height;
    int rows;
    int cols;
    private ArrayList<Punto> puntos;
    private ArrayList<Punto> puntosLinea;
    private Punto raizP, izqP, derP, actP;
    private int clavePadre, claveAbuelo;

  GridsCanvas(int w, int h, int r, int c) {
    setSize(width = 1400, height = 600);
    rows = 80;
    cols = 80;
    this.izqP = null;
    this.derP = null;
  }

    @Override
    public void paint(Graphics g) {
        int i;
        width = getSize().width;
        height = getSize().height;

        if (puntos == null || puntos.isEmpty()) {

        } else {
            for (int j = 0; j < puntos.size(); j++) {
                if (puntos.get(j).getNodoRNX().getColor() == 1) {
                    g.setColor(Color.red);
                    g.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
                } else {
                    g.setColor(Color.BLACK);
                    g.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
                }
                g.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
                g.drawString(Integer.toString(puntos.get(j).getNodoRNX().getLlave()), (puntos.get(j).getPosY() * 65) + 40, (puntos.get(j).getPosX() * 25) + 20);
                g.drawString(puntos.get(j).getNodoRNX().getNombre(), (puntos.get(j).getPosY() * 65) + 40, (puntos.get(j).getPosX() * 25) + 35);
            }
            //Buscamos la raiz
            
            for (int k = 0; k < puntos.size(); k++) {
                if (puntos.get(k).getPosX() == 0) {
                    this.raizP = puntos.get(k);
                    this.actP = puntos.get(k);
                    this.clavePadre = puntos.get(k).getNodoRNX().getLlave();
                    puntos.remove(k);
                }
            }
            while(puntos.size()>0){
                for (int k = 0; k < puntos.size(); k++) {
                    if (puntos.get(k).getNodoRNX().getLlave() < actP.getNodoRNX().getLlave()) {
                        if (izqP == null || (puntos.get(k).getPosX() < izqP.getPosX())) {
                            this.izqP = puntos.get(k);
                        }
                    } else {
                        if (derP == null) {
                            if((actP.getNodoRNX().getLlave()<raizP.getNodoRNX().getLlave())&&(puntos.get(k).getNodoRNX().getLlave()<raizP.getNodoRNX().getLlave())){
                                if((puntos.get(k).getNodoRNX().getLlave()<=clavePadre)||(clavePadre<actP.getNodoRNX().getLlave()&&puntos.get(k).getNodoRNX().getLlave()<claveAbuelo))
                                    this.derP = puntos.get(k);
                            }  
                                
                            if((actP.getNodoRNX().getLlave()>=raizP.getNodoRNX().getLlave())){
                                if(clavePadre==raizP.getNodoRNX().getLlave()){
                                    this.derP = puntos.get(k);
                                }else{
                                    if(puntos.get(k).getNodoRNX().getLlave()<=clavePadre || (clavePadre<actP.getNodoRNX().getLlave()&&puntos.get(k).getNodoRNX().getLlave()<claveAbuelo)){
                                        this.derP = puntos.get(k);
                                    }else{
                                        if((actP.getNodoRNX().getLlave()>clavePadre&&actP.getNodoRNX().getLlave()>claveAbuelo) && puntos.get(k).getNodoRNX().getLlave()>clavePadre &&puntos.get(k).getNodoRNX().getLlave()>claveAbuelo)
                                            this.derP = puntos.get(k); 
                                    }
                                }
                            }
                                
                        }else{
                            if(puntos.get(k).getPosX() < derP.getPosX())
                                this.derP = puntos.get(k);
                        }
                    }
                }
                g.setColor(Color.BLACK);
                if (izqP != null) {
                    g.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
                    g.drawLine((actP.getPosY() * 65) + 40, (actP.getPosX() * 25) + 30, (izqP.getPosY() * 65) + 40, (izqP.getPosX() * 25) + 30);
                }
                if (derP != null) {
                    g.setFont( new Font( "Tahoma", Font.BOLD, 13 ) );
                    g.drawLine((actP.getPosY() * 65) + 40, (actP.getPosX() * 25) + 30, (derP.getPosY() * 65) + 40, (derP.getPosX() * 25) + 30);
                }
                
                this.actP = null;
                this.izqP = null;
                this.derP = null;
                int indiceEliminar = -1;
                for (int k = 0; k < puntos.size(); k++) {
                    if (actP==null||puntos.get(k).getPosX() < actP.getPosX()){
                        this.actP = puntos.get(k);
                        //Se busca padre y abuelo del clave actual
                        for(int a = 0; a < puntosLinea.size();a++){
                            if((puntosLinea.get(a).getNodoRNX().getDer().getLlave()==actP.getNodoRNX().getLlave())||(puntosLinea.get(a).getNodoRNX().getIzq().getLlave()==actP.getNodoRNX().getLlave())){
                                this.clavePadre = puntosLinea.get(a).getNodoRNX().getLlave();
                                for(int b = 0; b < puntosLinea.size();b++){
                                    if((puntosLinea.get(b).getNodoRNX().getDer().getLlave()==puntosLinea.get(a).getNodoRNX().getLlave())||(puntosLinea.get(b).getNodoRNX().getIzq().getLlave()==puntosLinea.get(a).getNodoRNX().getLlave())){
                                        this.claveAbuelo = puntosLinea.get(b).getNodoRNX().getLlave();
                                    }
                                }    
                            }
                        }
                        indiceEliminar = k;
                    }
                }
                if(indiceEliminar != -1)
                    puntos.remove(indiceEliminar);
            }
        }
        limpiarArrayPuntos();
    }

    public void setPuntos(ArrayList<Punto> puntos) {
        this.puntos = puntos;
        ArrayList<Punto> copia = new ArrayList<>(puntos);
        this.puntosLinea = copia;
    }

    public void limpiarArrayPuntos() {
        if (puntos == null || puntos.isEmpty()) {

        } else {
            this.puntos.clear();
        }

    }
}
