package main;

public class Vertice {
  public float x;
  public float y;
  public float z;
  
  public Vertice () {
  	this.x = 0;
  	this.y = 0;
  	this.z = 0;
  }
  
  public Vertice (float x, float y) {
    this.x = x;
    this.y = y;
  }
  
  public Vertice (float x, float y, float z) {
  	this.x = x;
  	this.y = y;
  	this.z = z;
  }
  
  // retorna as coordenadas R2 do vértice
  public float[] getXY () {
  	float[] coordenadas = {this.x, this.y};
  	return coordenadas;
  }
  
  // retorna as coordenadas R3 do vértice
  public float[] getXYZ () {
  	float[] coordenadas = {this.x, this.y, this.z};
  	return coordenadas;
  }
  
}
