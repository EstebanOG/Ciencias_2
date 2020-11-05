package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Huffman;
import logica.HuffmanTable;

public class Interfaz extends JFrame {
	public int flag;
	public int WinWidth = 700;
	public int WinHeight = 600;
	public int LROffset = 135;
	public int DownOffset = 50;
	public int nodeD = 26;
	public int levelOffset = 50;
	public JPanel control;
	public JPanel lienzo;
	public JLabel Titulo;
	public JButton Ingresar;
	public JLabel labelPalabra;
	public JTextField IngresarPalabra;
	public Graphics graphics;
	public Graphics2D g;
	public Huffman h;
	public HuffmanTable hTable;
    // Declaración Objetos Gráficos
    private JPanel pEncabezado, pLienzo;
    private JLabel lTitulo, lMensajeIngresar;
    private JTextField tFrase;
    private JButton bEnviarMensaje;

    public Interfaz() {
        super("Códigos de Huffman");

        pEncabezado = new JPanel();
        pEncabezado.setSize(1200, 100);
        pEncabezado.setLocation(0, 0);
        pEncabezado.setLayout(null);
        this.add(pEncabezado);

        pLienzo = new JPanel();
        pLienzo.setBounds(100, 110, 1000, 400);
        pLienzo.setBackground(Color.WHITE);
        pLienzo.setBorder(BorderFactory.createLineBorder(Color.black));
        pLienzo.setAutoscrolls(true);
        this.add(pLienzo);

        lTitulo = new JLabel("Códigos de Huffman");
        lTitulo.setBounds(10, 0, 220, 30);
        pEncabezado.add(lTitulo);

        lMensajeIngresar = new JLabel("Digite el mensaje. Solo se aceptan caracteres de la 'a' a la 'z'.");
        lMensajeIngresar.setBounds(10, 30, 500, 30);
        pEncabezado.add(lMensajeIngresar);

        tFrase = new JTextField();
        tFrase.setSize(500, 30);
        tFrase.setLocation(10, 65);
        tFrase.setBackground(Color.WHITE);
        pEncabezado.add(tFrase);

        bEnviarMensaje = new JButton("Enviar Mensaje");
        bEnviarMensaje.setSize(200, 30);
        bEnviarMensaje.setLocation(540, 65);
        bEnviarMensaje.setFocusable(false);
        bEnviarMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tFrase.getText();
                try {
                    h.setMensaje(text);
                    repintar();
                } catch (Exception error) {
                    System.out.println(error.toString());
                }
                //tFrase.setText("");

            }

        });
        pEncabezado.add(bEnviarMensaje);
        pEncabezado.revalidate();
        pEncabezado.repaint();

        this.h = new Huffman();
        setLayout(null);
        setSize(1200, 700);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

	public void repintar() {
		graphics = pLienzo.getGraphics();
		pLienzo.paint(graphics);
		g = (Graphics2D) graphics;
		this.hTable = this.h.getTabla();
		paintArbolH(this.h.ultimoI(), (pLienzo.getWidth() / 2) - (this.nodeD / 2), 5, 0, "");
		// lienzo.repaint();
	}

	private void paintArbolH(int i, int x, int y, int level, String codigo) {
		int nodeR = nodeD / 2;
                int nextRNodeX = 0;
                int nextLNodeX = 0;
                if(level<3){
                     nextLNodeX = x - LROffset + level * levelOffset;
                     nextRNodeX = x + LROffset - level * levelOffset;
                }else{
                     nextLNodeX = x + LROffset - level * levelOffset;
                     nextRNodeX = x - LROffset + level * levelOffset;
                }		
		int nextNodeY = y + DownOffset;
		int hizq = this.hTable.getIzq()[i];
		int hder = this.hTable.getDer()[i];
                int frec = this.hTable.getFrecuencia()[i];
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		if (this.hTable.getSimbolo()[i] == null) {
			g.drawLine(nextLNodeX + nodeR, nextNodeY + nodeR, x + nodeR, y + nodeR);
			g.drawLine(nextRNodeX + nodeR, nextNodeY + nodeR, x + nodeR, y + nodeR);
		}
		g.fillOval(x, y, nodeD - level, nodeD - level);
		g.setColor(Color.WHITE);
		if (this.hTable.getSimbolo()[i] == null) {
			//g.drawString(String.valueOf(i), x + nodeR - 10, y + nodeR + 4);
                        g.drawString(String.valueOf(frec), x + nodeR - 10, y + nodeR + 4);
		} else {
                        g.drawString(String.valueOf(this.hTable.getSimbolo()[i]), x + nodeR - 10, y + nodeR + 4);
		}
		if (this.hTable.getSimbolo()[i] == null) {
			paintArbolH(hizq, nextLNodeX, nextNodeY, level + 1,codigo+"0");
			paintArbolH(hder, nextRNodeX, nextNodeY, level + 1,codigo+"1");
//                        paintArbolH(this.hTable.getIzq()[i], nextLNodeX, nextNodeY, level + 1,codigo+"0");
//			paintArbolH(this.hTable.getDer()[i], nextRNodeX, nextNodeY, level + 1,codigo+"1");
		}else {
			g.setColor(Color.BLACK);
			g.drawString(codigo, x-nodeR , y + nodeD+5);
		}
	}

	public static void main(String[] Args) {
		new Interfaz();
	}

}