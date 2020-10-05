/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolrn;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author Usuario
 */
public class GridsCanvas extends Canvas {
    int width, height;
    int rows;
    int cols;

  GridsCanvas(int w, int h, int r, int c) {
    setSize(width = w, height = h);
    rows = r;
    cols = c;
  }

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
  }
}
