package Logica;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juana
 */
public class Main {
    	public static void main(String[] args) {
                XYSeries series = new XYSeries("Retiros");
                XYSeries series1 = new XYSeries("Logaritmo");
                int y =0;
                for( int j=1000;j>0;j--){
                    int x = j;
                    if(j==0){
                        j=1;
                    }
                    series.add(Math.log(j)+2,y);
                    j=x;
                    y++;
                }
                
                
                ColasPrioridad c = new ColasPrioridad();
                for(int i=0;i<1000;i++){
                    c.creaP((int) (Math.random()*100+1));
                }
                for(int i=0;i<1000;i++){
                    c.retirarP();
                    System.out.println(c.getContador()+"54616");
                    series1.add(c.getContador(), i);
                    
                }
                
                XYSeriesCollection dataset = new XYSeriesCollection();
                XYSeriesCollection datasett = new XYSeriesCollection();
                
                dataset.addSeries(series);
                dataset.addSeries(series1);
                
                JFreeChart chart = ChartFactory.createScatterPlot("O(ln x)", "Operaciones", "Elementos", dataset, PlotOrientation.HORIZONTAL, true, false, false);
                
                ChartFrame frame = new ChartFrame("Ejemplo", chart);
                frame.pack();
                frame.setVisible(true);
                
        }
		
}