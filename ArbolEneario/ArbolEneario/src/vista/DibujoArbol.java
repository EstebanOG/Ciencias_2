/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import modelo.Nodo;

/**
 *
 * @author Usuario
 */
public class DibujoArbol extends Canvas{
    private ArrayList<Nodo> listaPalabras;
    DibujoArbol(){
        setLocation(150, 0);
        setSize(900, 600);
        setBackground(Color.GRAY);
        setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        if(listaPalabras == null||listaPalabras.isEmpty()){
            
        }else{
           g.setFont( new Font( "Tahoma", Font.BOLD, 16 ) );
          // g.drawString(listaPalabras.get(0).getClave(), 10, 20); 
        }
    }

    public void setListaPalabras(ArrayList<Nodo> listaPalabras) {
        this.listaPalabras = listaPalabras;
        repaint();
    }
    
}
