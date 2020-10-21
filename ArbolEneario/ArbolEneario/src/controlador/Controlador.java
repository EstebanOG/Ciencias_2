/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import vista.Vista;

/**
 *
 * @author Usuario
 */
public class Controlador implements ActionListener{
    private Vista view;
    private Modelo model;
    
    public Controlador(Vista view, Modelo model){
        this.view = view;
        this.model = model;
        this.view.btnIniciar.addActionListener(this);
        this.view.btnInsertar.addActionListener(this);
        this.view.btnRecorridos.addActionListener(this);
        this.view.btnDiccionario.addActionListener(this);
        this.view.btnConsultar.addActionListener(this);
    }
    
    public void iniciar(){
        view.setTitle("Árbol Eneario");
        view.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.btnIniciar){
            model.inicializarArbol();
            view.dibujo.setListaPalabras(model.getListaPalabras());
        }
        if(e.getSource() == view.btnInsertar){
            model.insetar();
        }
        if(e.getSource() == view.btnRecorridos){
            
        }
        if(e.getSource() == view.btnDiccionario){
            
        }
        if(e.getSource() == view.btnConsultar){
            
        }
    }
}
