package interfaz;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logica.ArbolEneario;
import logica.Nodo;

public class Interfaz extends JFrame{
	public int flag;
	public int WinWidth = 900;
	public int WinHeight = 700;
	public int LROffset = 170;
	public int DownOffset = 50;
	public int nodeD = 26;
	public int levelOffset = 50;
	public JPanel panel;
	public JPanel lienzo;
	public JLabel Titulo;
	public JButton Ingresar;
        public JButton Consultar;
        public JButton Cerrar;
        public JButton Diccionario;
        public JButton Recorridos;
	public JLabel labelPalabra;
	public JLabel labelTraduccion;
        public JLabel labelBuscar;
	public JTextField IngresarPalabra;
	public JTextField IngresarTraduccion;
        public JTextField IngresarBuscar;
	public Graphics graphics;
	public Graphics2D g;
	public ArbolEneario ArbolE;
        public JTable tableDiccionario;
        public TableRowSorter tr;// = new TableRowSorter<DefaultTableModel>(modelo);
        DefaultTableModel modelo = new DefaultTableModel();
        public JTable tabla = new JTable(modelo);
        JScrollPane scroll1 = new JScrollPane(tabla);
        public ArrayList<String> pre;
        public ArrayList<String> pos;
        
        
        
	
	public Interfaz() {
                //modelo.fireTableDataChanged();
                createTable();
                scroll1.setVisible(false);
                
		this.ArbolE= new ArbolEneario();
		this.setTitle("Arbol-Eneario");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize( WinWidth,WinHeight);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
                
                panel = new JPanel();
		panel.setBounds((this.WinWidth/2)-((int) (this.WinWidth*0.60)/2), 570,(int) (this.WinWidth*0.80) , 90);
		System.out.println((int) (this.WinWidth*0.40));
		panel.setLayout(null);
		this.getContentPane().add(panel);
		
		
		lienzo= new JPanel();
		lienzo.setBounds(20, 90, this.WinWidth-50, (int) (this.WinHeight-240));
		lienzo.setBackground(Color.WHITE);
		lienzo.setBorder(BorderFactory.createLineBorder(Color.black));
		lienzo.setAutoscrolls(true);
		this.getContentPane().add(lienzo);
		
		
		
		labelPalabra = new JLabel("Palabra");
		labelPalabra.setBounds(75, 30, 50, 20);
                
		//labelPalabra.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(labelPalabra);
                
		labelTraduccion= new JLabel("Traduccion");
		labelTraduccion.setBounds(230, 30, 115, 20);
		//labelPalabraTraducida.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(labelTraduccion);
                
                labelBuscar = new JLabel("Buscar");
		labelBuscar.setBounds(115, 10, 50, 20);
                labelBuscar.setVisible(false);
		panel.add(labelBuscar);
                
		IngresarPalabra= new JTextField();
		IngresarPalabra.setBounds(125, 30, 100, 20);
		IngresarPalabra.setVisible(true);
		panel.add(IngresarPalabra);
                
		IngresarTraduccion= new JTextField();
		IngresarTraduccion.setBounds(300, 30, 100, 20);
		panel.add(IngresarTraduccion);
                
                IngresarBuscar= new JTextField();
		IngresarBuscar.setBounds(165, 10, 100, 20);
                IngresarBuscar.setVisible(false);
                IngresarBuscar.addKeyListener(new KeyListener(){
                    @Override
                    public void keyReleased(KeyEvent arg0) {
                        String Palabre = IngresarBuscar.getText();
                        filtrar(Palabre);
                    }
                    
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {   
                    }
                });
		panel.add(IngresarBuscar);
                
                
                Diccionario=new JButton("Diccionario");
		Diccionario.setBounds(325, 60, 100, 20);
                Diccionario.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
                                Recorridos.setVisible(false);
                                Cerrar.setVisible(true);
                                scroll1.setVisible(true);			
			}	
	    });
                panel.add(Diccionario);
                
                Recorridos = new JButton("Recorridos");
                Recorridos.setBounds(475, 60, 100, 20);
                Recorridos.addActionListener(new ActionListener(){
                        
			@Override
			public void actionPerformed(ActionEvent e) {
                            String in="";
                            ArbolE.inorden(ArbolE.getRaiz());
                            for(int i = 0; i<ArbolE.getIn().size();i++){
                                in = in+ArbolE.getIn().get(i);
                            }
                            String pos="";
                            ArbolE.posorden(ArbolE.getRaiz());
                            for(int i = 0; i<ArbolE.getPos().size();i++){
                                pos = pos+ArbolE.getPos().get(i);
                            }
                            String pre="";
                            ArbolE.preorden(ArbolE.getRaiz());
                            for(int i = 0; i<ArbolE.getPre().size();i++){
                                pre = pre+ArbolE.getPre().get(i);
                            }
                               JOptionPane.showMessageDialog(null,"<html>"+"Pos: "+pos+"<br>"+"In: "+in+"<br>"+"Pre:"+pre+"</html>");	
                               ArbolE.limpiarIn();
                               ArbolE.limpiarPos();
                               ArbolE.limpiarPre();
                               repintar();
                               panel.repaint();
                            }
                            
                });
                panel.add(Recorridos);
                Cerrar=new JButton("Cerrar");
		Cerrar.setBounds(475, 60, 78, 20);
                Cerrar.addActionListener(new ActionListener(){       
			@Override
			public void actionPerformed(ActionEvent e) {
                            IngresarPalabra.setVisible(true);
                            labelPalabra.setVisible(true);
                            IngresarTraduccion.setVisible(true);
                            labelTraduccion.setVisible(true);
                            IngresarBuscar.setVisible(false);
                            labelBuscar.setVisible(false);
                            scroll1.setVisible(false);
                            Cerrar.setVisible(false);
                            Recorridos.setVisible(true);
                                
			}			
	    });
                panel.add(Cerrar);
                Cerrar.setVisible(false);
                
                Consultar=new JButton("Consultar");
		Consultar.setBounds(25, 60, 100, 20);
                Consultar.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {  
                            IngresarPalabra.setVisible(false);
                            labelPalabra.setVisible(false);
                            IngresarTraduccion.setVisible(false);
                            labelTraduccion.setVisible(false);
                            IngresarBuscar.setVisible(true);
                            labelBuscar.setVisible(true);
                            Recorridos.setVisible(false);
                            scroll1.setVisible(true);
                            Cerrar.setVisible(true);
                            repintar();
                            panel.repaint();
			}			
	    });
                panel.add(Consultar);
                
                Ingresar=new JButton("Ingresar");
		Ingresar.setBounds(175, 60, 100, 20);
		Ingresar.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
                            boolean palabraExiste = false;
                            for (int i = 0; i < tabla.getRowCount(); i++) {
                                if(tabla.getValueAt(i, 0).toString().equals(IngresarPalabra.getText())){
                                    System.out.println("La palabra ya existe");
                                    palabraExiste = true;
                                }
                            }
                            if(palabraExiste == false){
                                scroll1.setVisible(false);
				String palabra=IngresarPalabra.getText();
				String palabraT= IngresarTraduccion.getText();
                                
				try {
					ArbolE.addPalabra(palabra, palabraT);
                                        String[] fila = {
                                        palabra,
                                        palabraT};
                                        
                                        if ("".equals(palabra)){
                                            
                                        } else {
                                            modelo.addRow(fila);
                                        }
					repintar();
                                        
				}catch(Exception error) {
					System.out.println(error.toString());	
				}
                                
                                
				IngresarPalabra.setText(null);
				IngresarTraduccion.setText(null);
                            }else{
                                
                                JOptionPane.showMessageDialog(null,"La palabra ya existe.");
                            }
                                
			}

			
	    });
		panel.add(Ingresar);
		panel.revalidate();
		panel.repaint();
                
                inicio();

	}
        
        void createTable() {
        modelo.addColumn("Palabra");
        modelo.addColumn("Traduccion");
        
        scroll1.setBounds(20, 90, 685, 200);
        add(scroll1);
        }
        
        void inicio() {
        String palabra = IngresarPalabra.getText();
        String palabraT = IngresarTraduccion.getText();

            try {
                ArbolE.addPalabra(palabra, palabraT);

                repintar();
            } catch (Exception error) {
                System.out.println(error.toString());
            }
        }
        
        void filtrar(String palabre){
            tr = new TableRowSorter(modelo);
            tr.setRowFilter(RowFilter.regexFilter(IngresarBuscar.getText(), 0));
            tabla.setRowSorter(tr);
        }
	
	public void repintar()
	{
		graphics = lienzo.getGraphics();
		lienzo.paint(graphics);
		g = (Graphics2D) graphics;
		paintArbolE(this.ArbolE.raiz,10,20 );
		//lienzo.repaint();
	}
	
	private void paintArbolE(Nodo node, int x, int y)
	{
		
		if(node != null)
		{	
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(1));
			g.drawString(String.valueOf(node.getValor()),x, y);
			g.setColor(Color.BLACK);
			if(node.tieneHijos()) {
				g.drawLine(x+3, y+1, x+3, y+29);
				paintArbolE(node.getHijos().get(0),x,y+41);
			
			}else {
				if(node.esFinal()) {
					g.setColor(Color.BLACK);
					g.drawString(String.valueOf(node.getPalabraTraducida()),x-9, y+12);
					pintarhermanos(node,x,y-41);
				}
			}
		}
	}
	
	public void pintarhermanos(Nodo node, int x, int y) {
		if(node.getPadre().sigHermano()==null) {
			pintarhermanos(node.getPadre(),x,y-41);
			return;
		}
		int ancho=node.getPadre().numNodoFinales();
		ancho=ancho-1;
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		if(ancho==0) {
			g.drawLine(x+10, y-6, x+(30), y-6);
		}else {
			System.out.println(ancho);
			g.drawLine(x-(20*ancho)-(10*ancho), y-6, x+(36), y-6);
		}
		
		paintArbolE(node.getPadre().sigHermano(),x+50,y);
	}

	public static void main(String []Args) {
		new Interfaz();
                
	}
	

}
