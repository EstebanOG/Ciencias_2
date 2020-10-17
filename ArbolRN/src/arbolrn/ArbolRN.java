package arbolrn;

import java.util.ArrayList;

public class ArbolRN {
    private NodoRN raiz, z;
    private final int NEGRO = 0;
    private final int ROJO = 1;
    private ArrayList<NodoRN> listInorden = new ArrayList<>();
    private ArrayList<NodoRN> listPreorden = new ArrayList<>();
    private ArrayList<Punto> listPunto = new ArrayList<>();
    private Punto matriz[][] = new Punto[40][40];
    
    
    public NodoRN Raiz(){
        return raiz;
    }
    
    public void inicializar(){
        z = new NodoRN(0,NEGRO);
        z.setIzq(z);
        z.setDer(z);
        
        raiz = new NodoRN(0,NEGRO);
        raiz.setIzq(z);
        raiz.setDer(z);
        this.listInorden.clear();
    }
    
    public NodoRN rotar(int v, NodoRN p){
        NodoRN c, gc;
        if(v < p.getLlave()){
            c = p.getIzq();
        }else{
            c = p.getDer();
        }
        
        if(v < c.getLlave()){
            gc = c.getIzq();
            c.setIzq(gc.getDer());
            gc.setDer(c);
        }else{
            gc = c.getDer();
            c.setDer(gc.getIzq());
            gc.setIzq(c);
        }
        
        if(v < p.getLlave()){
            p.setIzq(gc);
        }else{
            p.setDer(gc);
        }
        return gc;
    }
    
    public NodoRN dividir(int v, NodoRN gf, NodoRN g, NodoRN p, NodoRN x){
        x.setColor(ROJO);
        x.getIzq().setColor(NEGRO);
        x.getDer().setColor(NEGRO);
        if (p.getColor()==ROJO){
            g.setColor(ROJO);
            if((v < g.getLlave()) != (v < p.getLlave())){
                System.out.println("sentido contrario-" + g.getLlave());
                p = rotar(v,g);
            }
            System.out.println("mismo sentido-"+ gf.getLlave());
            x = rotar(v, gf);
            x.setColor(NEGRO);
        }
        raiz.getDer().setColor(NEGRO);
        return x;
    }
    
    public NodoRN insertar(int v, String nom){
        NodoRN gf, g, p, x;
        x = raiz;
        p = x;
        g = x;
        do{
            if(v < x.getLlave()){
                gf = g;
                g = p;
                p = x;
                x = x.getIzq();
            }else if(v > x.getLlave()){
                gf = g;
                g = p;
                p = x;
                x = x.getDer();
            }else{
                System.out.println("La llave ya existe.." + v);
                return z;
            }
            
            if(x.getIzq().getColor() == ROJO && x.getDer().getColor() == ROJO){
                x = dividir(v, gf, g, p, x);
            }
        }while(x != z);
        x = new NodoRN(v);//Color sin asignar todavia
        x.setLlave(v);
        x.setNombre(nom);
        x.setIzq(z);
        x.setDer(z);
        if(v < p.getLlave()){
            p.setIzq(x);
        }else{
            p.setDer(x);
        }
        x = dividir(v, gf, g, p, x);
        return x;
    }
    public ArrayList<NodoRN> obtenerInorden(){
        return listInorden;
    }
    public void limpiarArrayInorden(){
        this.listInorden.clear();
    }
    public ArrayList<NodoRN> obtenerPreorden(){
        return listInorden;
    }
    public void limpiarArrayPreorden(){
        this.listPreorden.clear();
    }
    public void inorden(NodoRN p){
        if(p != z){
            inorden(p.getIzq());
            if(p.getColor() == ROJO){
                this.listInorden.add(p);
                System.out.println(" "+ p.getLlave()+""+"R");
                
            }else{
                this.listInorden.add(p);
                System.out.println(" "+p.getLlave()+""+"N");
            }
            inorden(p.getDer());
        }
        
    }
    
    public void preorden(NodoRN p){
        if(p != z){
            if(p.getColor() == ROJO){
                this.listPreorden.add(p);
                System.out.println(" "+p.getLlave()+""+"R");
            }else{
                this.listPreorden.add(p);
                System.out.println(" "+p.getLlave()+""+"N");
            }
            preorden(p.getIzq());
            preorden(p.getDer());
        }
    }
    
    public boolean colorRojo(NodoRN raiz){
        return raiz != z && raiz.getColor() == ROJO;
    }
    
    public NodoRN simpleRotacion(NodoRN raiz, int dir){
        NodoRN temp;
        if(dir == 1){
            System.out.println("Simple Rotacion a la der.");
            temp = raiz.getIzq();
            raiz.setIzq(temp.getDer());
            temp.setDer(raiz);
        }else{
            System.out.println("Simple rotacion a la izq.");
            temp = raiz.getDer();
            raiz.setDer(temp.getIzq());
            temp.setIzq(raiz);
        }
        raiz.setColor(ROJO);
        temp.setColor(NEGRO);
        return temp;
    }
    
    public NodoRN dobleRotacion(NodoRN raiz, int dir){
        if(dir == 1){
            raiz.setIzq(simpleRotacion(raiz.getIzq(),0));
        }else{
            raiz.setDer(simpleRotacion(raiz.getDer(),1));
        }
        return simpleRotacion(raiz, dir);
    }
    
    public int eliminar(NodoRN arbol, int llave){
        if(arbol.getDer() != z){
            NodoRN cabeza = new NodoRN(0);
            cabeza.setColor(NEGRO);
            cabeza.setIzq(z);
            cabeza.setDer(z);
            cabeza.setNombre("AAAA");
            NodoRN temp, q, p, g;
            NodoRN t, f = z;
            int dir = 1;
            
            q = cabeza;
            g = p = z;
            q.setDer(arbol.getDer());
            
            t = q.getDer();
            
            while(t != z){
                int anterior = dir;
                NodoRN w,v;
                
                g = p;
                p = q;
                
                if(anterior == 0){
                    v = p.getDer();
                }else{
                    v = p.getIzq();
                }
                
                if(dir == 0){
                    q = q.getIzq();
                }else{
                    q = q.getDer();
                }
                
                //actualiza dir
                if(q.getLlave() < llave){
                    dir = 1;
                }else{
                    dir = 0;
                }
                
                if(q.getLlave() == llave){
                    f = q;
                }
                
                if (dir == 0){
                    t = q.getIzq();
                    w = q.getDer();
                }else{
                    t = q.getDer();
                    w = q.getIzq();
                }

                if(!colorRojo(q) && !colorRojo(t)){
                    if(colorRojo(w)){
                        temp = simpleRotacion(q,dir);
                        if(anterior == 0){
                            p.setIzq(temp);
                        }else{
                            p.setDer(temp);
                        }
                        p = temp;
                    }else if(!colorRojo(w)){
                        NodoRN s = v;
                        NodoRN r1, v1;
                        if(anterior == 0){
                            r1 = s.getIzq();
                            v1 = s.getDer();
                        }else{
                            r1 = s.getDer();
                            v1 = s.getIzq();
                        }
                        
                        if(s != z){
                            if(!colorRojo(v1) && !colorRojo(r1)){
                                p.setColor(NEGRO);
                                s.setColor(ROJO);
                                q.setColor(ROJO);
                            }else{
                                int dir2;
                                NodoRN r2;
                                if(g.getDer()==p){
                                    dir2 = 1;
                                }else{
                                    dir2 = 0;
                                }
                                
                                if(dir2 == 0){
                                    r2 = g.getIzq();
                                }else{
                                    r2 = g.getDer();
                                }
                                
                                if (colorRojo(r1)){
                                    if(dir2 == 0){
                                        System.out.println("Doble Rotacion a la izq.");
                                        g.setIzq(dobleRotacion(p, anterior));
                                    }else{
                                        System.out.println("Doble Rotacion a la der.");
                                        g.setDer(dobleRotacion(p, anterior));
                                    }
                                }else if(colorRojo(v1)){
                                    if(dir2 == 0){
                                        g.setIzq(simpleRotacion(p, anterior));
                                    }else{
                                        g.setDer(simpleRotacion(p, anterior));
                                    }
                                }
                                
                                if (dir2 == 0){
                                    r2 = g.getIzq();
                                }else{
                                    r2 = g.getDer();
                                }
                                q.setColor(ROJO);
                                r2.setColor(ROJO);
                                r2.getIzq().setColor(NEGRO);
                                r2.getDer().setColor(NEGRO);
                            }
                        }// if (s != z)
                    }// if (!colorRojo (w))
                }// if (!colorRojo(q)&& !colorRojo(t))
            }// while(terminar != z)
            
            if(f != z){
                NodoRN h = null;
                f.setLlave(q.getLlave());
                f.setNombre(q.getNombre());
                if(q.getIzq()==z){
                    h = q.getDer();
                }else{
                    h = q.getIzq();
                }
                if(p.getDer()==q){
                    p.setDer(h);
                }else{
                    p.setIzq(h);
                }
                
                q = null; 
            }else{
                System.out.println("!No existe la llave "+ llave);
            }
            
            arbol.setDer(cabeza.getDer());
            cabeza = null;
            if(arbol.getDer() != z){
                arbol.getDer().setColor(NEGRO);
            }
            
        }
        return 1;
    }
    public void dibujarARN(){
        limpiarArrayPunto();
        inorden(raiz);
        preorden(raiz);
        this.listInorden.remove(0);
        this.listPreorden.remove(0);
        for(int i=0; i<40;i++){
            for(int j=0; j<40;j++){
                Punto punto = new Punto();
                matriz[i][j]=punto;
            }
        }
        //Llenar con datos inorden
        for(int i=0; i<listPreorden.size();i++){
            for(int j=0; j<listInorden.size();j++){
                Punto punto = new Punto();
                punto.setNodoRNX(listInorden.get(j));
                matriz[i][j] = punto;
            }
        }
        //Llenar con datos en preOrden
        for(int i=0; i<listPreorden.size();i++){
            for(int j=0; j<listInorden.size();j++){
                matriz[i][j].setNodoRNY(listPreorden.get(i));
            }
        }
        for(int i=0; i<listPreorden.size();i++){
            for(int j=0; j<listInorden.size();j++){
                if(matriz[i][j].getNodoRNX().getLlave()==matriz[i][j].getNodoRNY().getLlave()){
                    matriz[i][j].setPosX(i);
                    matriz[i][j].setPosY(j);
                    this.listPunto.add(matriz[i][j]);
                }
            }
        }
    }
     public ArrayList<Punto> obtenerPuntos(){
        return listPunto;
    }
    public void limpiarArrayPunto(){
        this.listPunto.clear();
    }
}
