package main;

public class Vertice {
  public float x;
  public float y;
  
  public Vertice() {
  	this.x = 0;
  	this.y = 0;
  }
  
  public Vertice(float x, float y) {
    this.x = x;
    this.y = y;
  }
  
  // retorna as coordenadas do v�rtice
  public float[] getXY() {
  	float[] coordenadas = {this.x, this.y};
  	return coordenadas;
  }
  
}
