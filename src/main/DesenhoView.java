package main;

import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class DesenhoView {

  public JFrame frame;
  ArrayList<Vertice> vertices;

  /**
   * Create the application.
   */
  public DesenhoView(ArrayList<Vertice> vertices) {
    this.vertices = vertices;
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 600, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JDesenho panel = new JDesenho(this.vertices);
    frame.getContentPane().add(panel, BorderLayout.CENTER);
  }

}
