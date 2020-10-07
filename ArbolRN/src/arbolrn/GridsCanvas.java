package arbolrn;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class GridsCanvas extends Canvas {
    int width, height;
    int rows;
    int cols;
    private ArrayList<Punto> puntos;
    private ArrayList<Punto> puntosLinea;
    private Punto raizP, izqP, derP, actP;
    private int clavePadre;

  GridsCanvas(int w, int h, int r, int c) {
    setSize(width = 1300, height = 550);
    rows = 40;
    cols = 40;
    this.izqP = null;
    this.derP = null;
  }

    @Override
    public void paint(Graphics g) {
        int i;

        width = getSize().width;
        height = getSize().height;

//        // draw the rows
//        int rowHt = height / (rows);
//        for (i = 0; i < rows; i++) {
//            g.drawLine(0, i * rowHt, width, i * rowHt);
//            g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
//        }
//
//        // draw the columns
//        int rowWid = width / (cols);
//        for (i = 0; i < cols; i++) {
//            g.drawLine(i * rowWid, 0, i * rowWid, height);
//            g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
//        }

        if (puntos == null || puntos.isEmpty()) {

        } else {
            System.out.println("Tamaño dibuja:"+puntos.size());
            for (int j = 0; j < puntos.size(); j++) {
                if (puntos.get(j).getNodoRNX().getColor() == 1) {
                    g.setColor(Color.red);
                    g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
                } else {
                    g.setColor(Color.BLACK);
                    g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
                }
                g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
                g.drawString(Integer.toString(puntos.get(j).getNodoRNX().getLlave()), (puntos.get(j).getPosY() * 80) + 40, (puntos.get(j).getPosX() * 40) + 20);
                g.drawString(puntos.get(j).getNodoRNX().getNombre(), (puntos.get(j).getPosY() * 80) + 30, (puntos.get(j).getPosX() * 40) + 50);
            }
            //Buscamos la raiz
            
            for (int k = 0; k < puntos.size(); k++) {
                System.out.println("xd");
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
                        System.out.println("Se cumple menor :" + puntos.get(k).getNodoRNX().getLlave() + "<" + actP.getNodoRNX().getLlave());
                        if (izqP == null || (puntos.get(k).getPosX() < izqP.getPosX())) {
                            this.izqP = puntos.get(k);
                        }
                    } else {
                        if (derP == null) {
                            System.out.println("Entra en no null");
                            if((actP.getNodoRNX().getLlave()<raizP.getNodoRNX().getLlave())&&(puntos.get(k).getNodoRNX().getLlave()<raizP.getNodoRNX().getLlave())){
                                if((puntos.get(k).getNodoRNX().getLlave()<=clavePadre)||clavePadre<actP.getNodoRNX().getLlave())
                                    this.derP = puntos.get(k);
                                System.out.println("If 1");
                            }  
                                
                            if((actP.getNodoRNX().getLlave()>=raizP.getNodoRNX().getLlave())){
                                if(clavePadre==raizP.getNodoRNX().getLlave()){
                                    this.derP = puntos.get(k);
                                }else{
                                    if(puntos.get(k).getNodoRNX().getLlave()<=clavePadre || clavePadre<actP.getNodoRNX().getLlave())
                                        this.derP = puntos.get(k);
                                }
                                
                                System.out.println("If 2");
                            }
                                
                        }else{
                            if(puntos.get(k).getPosX() < derP.getPosX())
                                this.derP = puntos.get(k);
                        }
                    }
                }
                g.setColor(Color.BLACK);
                if (izqP != null) {
                    g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
                    g.drawLine((actP.getPosY() * 80) + 40, (actP.getPosX() * 40) + 30, (izqP.getPosY() * 80) + 40, (izqP.getPosX() * 40) + 30);
                }
                if (derP != null) {
                    g.setFont( new Font( "Tahoma", Font.BOLD, 20 ) );
                    g.drawLine((actP.getPosY() * 80) + 40, (actP.getPosX() * 40) + 30, (derP.getPosY() * 80) + 40, (derP.getPosX() * 40) + 30);
                }
                
                this.actP = null;
                this.izqP = null;
                this.derP = null;
                int indiceEliminar = -1;
                for (int k = 0; k < puntos.size(); k++) {
                    System.out.println("xd");
                    if (actP==null||puntos.get(k).getPosX() < actP.getPosX()){
                        this.actP = puntos.get(k);
                        
                        for(int a = 0; a < puntosLinea.size();a++){
                            if((puntosLinea.get(a).getNodoRNX().getDer().getLlave()==actP.getNodoRNX().getLlave())||(puntosLinea.get(a).getNodoRNX().getIzq().getLlave()==actP.getNodoRNX().getLlave())){
                                this.clavePadre = puntosLinea.get(a).getNodoRNX().getLlave();
                            }
                        }
                        System.out.println("Actual: "+ actP.getNodoRNX().getLlave()+" Padre:"+clavePadre);
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
