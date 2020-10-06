/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolrn;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class GridsCanvas extends Canvas {
    int width, height;
    int rows;
    int cols;
    private ArrayList<Punto> puntos;

  GridsCanvas(int w, int h, int r, int c) {
    setSize(width = w, height = h);
    rows = r;
    cols = c;
    //puntos.clear();
  }

    @Override
  public void paint(Graphics g) {
    int i;
    width = getSize().width;
    height = getSize().height;

    // draw the rows
    int rowHt = height / (rows);
    for (i = 0; i < rows; i++)
      g.drawLine(0, i * rowHt, width, i * rowHt);

    // draw the columns
    int rowWid = width / (cols);
    for (i = 0; i < cols; i++)
      g.drawLine(i * rowWid, 0, i * rowWid, height);
    
    if(puntos==null || puntos.isEmpty()){
        
    }else{
        for(int j = 0; j<puntos.size();j++){
            if(puntos.get(j).getNodoRNX().getColor()==1){
                g.setColor(Color.red);
            }else{
                g.setColor(Color.BLACK);
            }
            
            g.drawString(Integer.toString(puntos.get(j).getNodoRNX().getLlave()), (puntos.get(j).getPosY()*80)+40, (puntos.get(j).getPosX()*40)+20);
        }
    }
    limpiarArrayPuntos();
  }

    public void setPuntos(ArrayList<Punto> puntos) {
        this.puntos = puntos;
    }
    public void limpiarArrayPuntos(){
        if(puntos==null || puntos.isEmpty()){
        
        }else{
            this.puntos.clear();
        }
        
    }
  
}
