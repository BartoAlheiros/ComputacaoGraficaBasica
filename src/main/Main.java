/*
 * UFRPE - Universidade Federal Rural de Pernambuco
DC - Departamento de Computação
Disiciplina: Computação Gráfica
Professor: João Paulo
Aluno: José Bartolomeu Alheiros Dias Neto

*/

package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	
	static FileReader arq;
	static BufferedReader lerArq;
	static String linha; 
	static String[] str;
 
  public static void main(String[] args) {  
    /*(a)*/
    LibMath lib = new LibMath();
    
    float a[][] = { {1.5f, 2.5f, 3.5f}, 
                    {4.5f, 5.5f, 6.5f} };
    
    float b[][] = { {7.5f, 8.5f}, 
                    {9.5f, 10.5f},
                    {11.5f,12.5f} };
    
  
    float result[][] = lib.calculaProduto(a, b);
    System.out.println("Letra A: ");
    lib.imprimeMatriz(result);
    System.out.println();
    
    /*(b)*/
    float v1[] = {3.5f, 1.5f, 2.0f};
    float v2[] = {1.0f, 2.0f, 1.5f};
    
    System.out.println("Letra B: ");
    lib.imprimeVetor(lib.subtraiVetor(v1, v2));
    System.out.println();
    
    /*(c)*/
    System.out.println("Letra C: ");
    System.out.println(lib.produtoEscalar(v1, v2));
    System.out.println();
    
    /*(d)*/
    float resultV[] = lib.produtoVetorial(v1, v2);
    System.out.println("Letra D: ");
    lib.imprimeVetor(resultV);
    System.out.println();
    
    /*(e)*/
    float n = lib.norma(v1);
    System.out.println("Letra E: ");
    System.out.println(n);
    System.out.println();
    
    /*(f)*/
    System.out.println("Letra F: ");lib.imprimeVetor(lib.normaliza(v1, n));
    System.out.println();
    
    /*(g)*/
    float p[] = {-0.25f,0.75f};
    float pontoA[] = {-1f,1f};
    float pontoB[] = {0f,-1f};
    float pontoC[] = {1f,1f};
    
    System.out.println("Letra G: ");
    System.out.println(Arrays.toString(lib.coordBaricentrica(p, pontoA, pontoB, pontoC)));
    System.out.println();
    
    /*(h)*/
    float coordBaric[] = {0.5f,0.25f,0.25f};
    
    System.out.println("Letra H: ");
    System.out.println(Arrays.toString(lib.baric2Cartes(coordBaric, pontoA, pontoB, pontoC)));
    
    
    /*2*/
    FileReader arq;
    try {
      arq = new FileReader("./objetos/maca2.byu");
      BufferedReader lerArq = new BufferedReader(arq);
      
      String linha = lerArq.readLine(); //lê a primeira linha e armazena na String linha
      String[] strA = linha.split(" "); //corta a String linha e armazena o no de Vertices em strA
      ArrayList<Vertice> vertices = new ArrayList<Vertice>();
   
      for (int i = 2; i <= Integer.parseInt(strA[0]) + 1; i++) {
        linha = lerArq.readLine(); 
        
        String[] strB = linha.split(" ");
        
        Vertice v = new Vertice(Float.parseFloat(strB[0]), Float.parseFloat(strB[1]));
        vertices.add(v); 
      }

      DesenhoView window = new DesenhoView(vertices);
      window.vertices = normaliza(500, 500, vertices);
      window.frame.setVisible(true);
      
      arq.close();
    } catch (IOException e) {
      System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
    }
    
    carregaCamera();
    
  }
  
  public static ArrayList<Vertice> normaliza(int width, int height, ArrayList<Vertice> vertices) {
    ArrayList<Vertice> vertices2 = new ArrayList<Vertice>();
    
    float xMin = achaXmin(vertices);
    float xMax = achaXmax(vertices);
    float yMin = achaYmin(vertices);
    float yMax = achaYmax(vertices);
    
    for (Vertice vertice: vertices) {
      vertice.x = ( ( vertice.x - xMin )/( xMax - xMin ) ) * (width - 1);
      vertice.y = ( ( vertice.y - yMin )/( yMax - yMin ) ) * (height - 1);
      vertices2.add(vertice);
    }
    
    return vertices2;
  }
  
  public static float achaXmin(ArrayList<Vertice> vertice) {
    float xMin = 0f;
    
    for(Vertice vertices: vertice) {
      if(vertices.x < xMin)
        xMin = vertices.x;
    }
    
    return xMin;
  }
  
  public static float achaXmax(ArrayList<Vertice> vertice) {
    float xMax = 0f;
    
    for(Vertice vertices: vertice) {
      if(vertices.x > xMax)
        xMax = vertices.x;
    }
    
    return xMax;
  }
  
  public static float achaYmin(ArrayList<Vertice> vertice) {
    float yMin = 0f;
    
    for(Vertice vertices: vertice) {
      if(vertices.y < yMin)
        yMin = vertices.y;
    }
    
    return yMin;
  }
  
  public static float achaYmax(ArrayList<Vertice> vertice) {
    float yMax = 0f;
    
    for(Vertice vertices: vertice) {
      if(vertices.y > yMax)
        yMax = vertices.y;
    }
    
    return yMax;
  }
  
  public static void carregaCamera() {
  	
  	try {
  		arq = new FileReader("./parametrosCamera/parametrosCam.cam");
  		lerArq = new BufferedReader(arq);
  		
  		int[] n = new int[3];
  		int[] v = new int[3];
  		int[] u = new int[3]; 
  		int[] c = new int[3];
  		
  		cortaLinha();
  		n[0] = Integer.parseInt(str[2]);
  		n[1] = Integer.parseInt(str[3]);
  		n[2] = Integer.parseInt(str[4]);
  		
  		for (int i = 0; i < n.length; i++) {
  			System.out.print(n[i]);
  		}
  		System.out.println();
  		
  		cortaLinha();
  		v[0] = Integer.parseInt(str[2]);
  		v[1] = Integer.parseInt(str[3]);
  		v[2] = Integer.parseInt(str[4]);
  		
   		for (int i = 0; i < v.length; i++) {
  			System.out.print(v[i]);
  		}
   		System.out.println();
   		
  		cortaLinha();
  		u[0] = Integer.parseInt(str[2]);
  		u[1] = Integer.parseInt(str[3]);
  		u[2] = Integer.parseInt(str[4]);
  		
   		for (int i = 0; i < u.length; i++) {
  			System.out.print(u[i]);
  		}
   		System.out.println();
   		
   		cortaLinha();
  		int d = Integer.parseInt(str[2]);
  		System.out.println(d);
  		
  		cortaLinha();
  		int hx = Integer.parseInt(str[2]);
  		System.out.println(hx);
  		
  		cortaLinha();
  		int hy = Integer.parseInt(str[2]);
  		System.out.println(hy);
  	
  		cortaLinha();
  		c[0] = Integer.parseInt(str[2]);
  		c[1] = Integer.parseInt(str[3]);
  		c[2] = Integer.parseInt(str[4]);
  		
   		for (int i = 0; i < c.length; i++) {
  			System.out.print(c[i]);
  		}
  		
		} catch (FileNotFoundException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		} catch (IOException e) {
			System.err.printf("Erro na leitura do arquivo: %s.\n", e.getMessage());
		}
  }
  
  public static void cortaLinha() throws IOException {
  	linha = lerArq.readLine(); 
		str = linha.split(" ");
	}
}
