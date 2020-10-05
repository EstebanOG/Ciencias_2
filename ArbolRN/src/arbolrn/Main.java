package arbolrn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    /*public static void main(String[] args) {
        GUI ventana = new GUI();
        ventana.setVisible(true);
        int resul;
        ArbolRN arbol = new ArbolRN();
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);
        
        arbol.inicializar();
        String aux;
        int v;
        System.out.println("Insertar Nodos");
        System.out.println("Digite Numero. 9999 para terminar");
        
        try{
            aux = teclado.readLine();
            v = Integer.parseInt(aux);
            while(v != 9999){
                arbol.insertar(v);
                arbol.inorden(arbol.Raiz());
                System.out.println("");
                arbol.preorden(arbol.Raiz());
                System.out.println("");
                System.out.println("---------------------");
                System.out.println("Digite Numero. 9999 para terminar");
                
                aux = teclado.readLine();
                v = Integer.parseInt(aux);
            }
        } catch (IOException e1) {
            System.out.println("Error al abrir el teclado.");
        }
        
        //Retiros
        System.out.println("RETIRAR NODOS");
        System.out.println("Digite Numero. 9999 para terminar");
        try{
            aux = teclado.readLine();
            v = Integer.parseInt(aux);
            while(v != 9999){
                arbol.eliminar(arbol.Raiz(), v);
                arbol.inorden(arbol.Raiz());
                System.out.println("");
                arbol.preorden(arbol.Raiz());
                System.out.println("");
                System.out.println("------------------------");
                System.out.println("Digite Numero. 9999 para terminar");
                aux = teclado.readLine();
                v = Integer.parseInt(aux);
            }
        }catch(IOException e1){
            System.out.println("Error al abrir el teclado.");
        }
    }*/
}
