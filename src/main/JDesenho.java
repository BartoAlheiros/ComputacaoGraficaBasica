package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JDesenho extends JPanel{
  ArrayList<Vertice> vertices; //array com os vértices normalizados para o JPanel
 
  public JDesenho(ArrayList<Vertice> vertices) {
    this.vertices = vertices;
  }

  @Override
  protected void paintComponent(Graphics g) {
    // TODO Auto-generated method stub
    
    g.setColor(Color.black);
    g.fillRect(0, 0, 500, 500);
    
    for(Vertice vertice: vertices) { 
      g.setColor(Color.white);
      g.fillRect( (int) vertice.x, (int) vertice.y, 1, 1 );
    }
    
  }
}
