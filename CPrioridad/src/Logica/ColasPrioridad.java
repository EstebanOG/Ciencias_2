package Logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juana
 */
public class ColasPrioridad {
    Arbol m;
    private int contador =0;
    
        ColasPrioridad() {
            m = new Arbol();
            
            }
        public void setContador(int contador) {
            this.contador = contador;
	}
        
        public int getContador() {
            return this.contador;
	}
        
        void creaP(int dato) {
            int i,j;
            m.cont++;
            i = m.cont;
            j = i / 2;
            while (m.a[j] < dato) {
            m.a[i] = m.a[j];
            i = j;
            j = i / 2;
            }
            m.a[i] = dato;
         }
        
        int retirarP() {
            int i,j,temp,elemento;
            contador =0;
            elemento = m.a[1];
            temp = m.a[m.cont];
            m.cont = m.cont - 1;
            i = 1;
            j = 2;
            while (j <= m.cont) {
                if (j < m.cont) {
                    if (m.a[j] < m.a[j+1] ) {
                    j++;
                    }
                }
                if (temp >= m.a[j]) {
                break;
                }
                m.a[i] = m.a[j];
                i = j;
                j = 2 * i;
                contador++;
            }
            m.a[i] = temp;
            System.out.println(contador);
            return elemento;
        }
}

