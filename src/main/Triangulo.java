package main;

import java.util.ArrayList;

public class Triangulo {
	// �ndices dos v�rtices do tri�ngulo no pol�gono
	private int i1, i2, i3;
	// v�rtices do tri�ngulo
	private ArrayList<Vertice> vertices;
	
	public Triangulo() {
		this.vertices = null;
	}
	
	public Triangulo(int i1, int i2, int i3) {
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		this.vertices = null;
	}
	
	/* M�todo que preenche a lista de v�rtices do tri�ngulo, utilizando a lista de
	 * v�rtices do pol�gono, junto com os �ndices do tri�ngulo */
	public ArrayList<Vertice> calculaVertice(ArrayList<Vertice> vertices) {
		this.vertices.get(0).x = vertices.get(this.i1).x;
		this.vertices.get(0).y = vertices.get(this.i1).y;
		this.vertices.get(1).x = vertices.get(this.i2).x;
		this.vertices.get(1).y = vertices.get(this.i2).y;
		this.vertices.get(2).x = vertices.get(this.i3).x;
		this.vertices.get(2).y = vertices.get(this.i3).y;
		
		return this.vertices;
	}
	
	public ArrayList<Vertice> getVertices() {
		return this.vertices;
	}
	
	public void setVertices(ArrayList<Vertice> vertices) {
		this.vertices = vertices;
	}
	
	public int getI1() {
		return i1;
	}

	public int getI2() {
		return i2;
	}

	public int getI3() {
		return i3;
	}
	
	
}
