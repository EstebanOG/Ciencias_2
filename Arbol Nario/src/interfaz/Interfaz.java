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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	public JLabel labelPalabra;
	public JLabel labelTraduccion;
	public JTextField IngresarPalabra;
	public JTextField IngresarTraduccion;
	public Graphics graphics;
	public Graphics2D g;
	public ArbolEneario ArbolE;
        public JTable tableDiccionario;
        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        JScrollPane scroll1 = new JScrollPane(tabla);
        
        
	
	public Interfaz() {
                
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
                
		IngresarPalabra= new JTextField();
		IngresarPalabra.setBounds(125, 30, 100, 20);
		IngresarPalabra.setVisible(true);
		panel.add(IngresarPalabra);
                
		IngresarTraduccion= new JTextField();
		IngresarTraduccion.setBounds(300, 30, 100, 20);
		panel.add(IngresarTraduccion);
                		
                Diccionario=new JButton("Diccionario");
		Diccionario.setBounds(325, 60, 100, 20);
                Diccionario.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
                                Cerrar.setVisible(true);
                                scroll1.setVisible(true);			
			}	
	    });
                panel.add(Diccionario);
                
                Cerrar=new JButton("Cerrar");
		Cerrar.setBounds(475, 60, 78, 20);
                Cerrar.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
                                scroll1.setVisible(false);
                                Cerrar.setVisible(false);
                                
			}			
	    });
                panel.add(Cerrar);
                Cerrar.setVisible(false);
                
                Consultar=new JButton("Consultar");
		Consultar.setBounds(25, 60, 100, 20);
                Consultar.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {  
                            String kr = IngresarPalabra.getText();
                            filtrar(kr);
                            scroll1.setVisible(true);
                            Cerrar.setVisible(true);
			}			
	    });
                panel.add(Consultar);
                
                Ingresar=new JButton("Ingresar");
		Ingresar.setBounds(175, 60, 100, 20);
		Ingresar.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
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
            TableRowSorter tr = new TableRowSorter(modelo);
            tr.setRowFilter(RowFilter.regexFilter(IngresarPalabra.getText(), 0));
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
		
		paintArbolE(node.getPadre().sigHermano(),x+36,y);
	}
        private void IngresarPalabraKeyReleased(java.awt.event.KeyEvent evt){
            String Palabre = IngresarPalabra.getText();
            filtrar(Palabre);
        }
	public static void main(String []Args) {
		new Interfaz();
	}
	

}
