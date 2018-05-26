/*
 * UFRPE - Universidade Federal Rural de Pernambuco
DC - Departamento de Computação
Disiciplina: Computação Gráfica
Professor: João Paulo
Aluno: José Bartolomeu Alheiros Dias Neto

 */

package main;

public class LibMath {

  float[][] result;

  /* Calcula o produto de duas matrizes passadas como argumento. */
  public float[][] calculaProduto(float a[][], float b[][]) {
    if(a[0].length != b.length) throw new RuntimeException("Dimensões inconsistentes. Impossível multiplicar as matrizes");

    result = new float[ a.length ][ b[0].length ];

    for (int i = 0; i < a.length; i++)
      for (int j = 0; j < b[0].length; j++) 
        for (int k = 0; k < a[0].length; k++) 
          result[i][j] += (a[i][k] * b[k][j]);

    return result ;
  }


  /*Imprime matriz resultado da multiplicação de matrizes.*/
  public void imprimeMatriz(float matriz[][]) {

    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[0].length; j++) {
        System.out.print(matriz[i][j] + " ");
      }
      System.out.println();
    }
  }

  /* Recebe um vetor 3D a e um vetor 3D b e retorna o resultado da operação 
   * de subtração entre os dois.
   */
  public float[] subtraiVetor(float a[], float b[]) {
    float result[] = {0, 0, 0};

    if( (a != null && b != null) ) 
    { 
      result[0] = a[0] - b[0];
      result[1] = a[1] - b[1];
      result[2] = a[2] - b[2];
    }

    return result;
  }

  /*Efetua o produto escalar entre dois vetores a e b.*/
  public float produtoEscalar(float a[], float b[]) {
    float result;

    result = a[0] * b[0] + a[1] * b[1] + a[2] * b[2];

    return result;
  }

  public float[] produtoVetorial(float a[], float b[]) {
    float result[] = {0,0,0};
    
    result[0] = (a[1]*b[2])-(b[1]*a[2]);
    result[1] = -((a[0]*b[2])-(b[0]*a[2]));
    result[2] = (a[0]*b[1])-(b[0]*a[1]);
    
    return result;
  }
  
  public float norma(float v[]) {
    float result ;
    
    result = (float) Math.sqrt(Math.pow(Double.parseDouble(Float.toString(v[0])),2)+Math.pow(Double.parseDouble(Float.toString(v[1])), 2)+Math.pow(Double.parseDouble(Float.toString(v[2])), 2));
    
    return result;
  }
  
  /*Retorna um vetor 3D normalizado(unitário), do vetor passado como argumento*/
  public float[] normaliza(float v[], float n) {
    float result[] = {0,0,0};
    
    result[0] = v[0]/n;
    result[1] = v[1]/n;
    result[2] = v[2]/n;
    
    return result;
  }
  
  public float[] coordBaricentrica(float p[], float a[], float b[], float c[]) {
   float tInversa[][] = {{0,0}, {0,0}}; 
   float escInversa = 1/( ( (a[0]-c[0])*(b[1]-c[1]) ) - ( (b[0]-c[0])*(a[1]-c[1]) ) ); //escalar 1/(ad-bc).
   
   //obtendo a inversa de T:
   tInversa[0][0] = escInversa * (b[1]-c[1]); 
   tInversa[0][1] = escInversa * -(b[0]-c[0]);
   tInversa[1][0] = escInversa * -(a[1]-c[1]);
   tInversa[1][1] = escInversa * (a[0]-c[0]);
   
   float coord[][] = {{0}, {0}}; //matriz coluna com as coordenadas baricêntricas(alpha e beta)
   
   coord[0][0] = ( tInversa[0][0] * (p[0] - c[0]) ) + ( tInversa[0][1] * (p[1] - c[1]) ); // alpha
   coord[1][0] = ( tInversa[1][0] * (p[0] - c[0]) ) + ( tInversa[1][1] * (p[1] - c[1]) ); //beta
   
   float gama = ( 1 - ( coord[0][0] + coord[1][0] ) );
   
   float result[] = {coord[0][0], coord[1][0], gama};
   
   return result;
}
  
  public float[] baric2Cartes(float cdBaric[], float a[], float b[], float c[]) {
    a[0] = cdBaric[0] * a[0]; a[1] = cdBaric[0] * a[1]; //alpha * a
    b[0] = cdBaric[1] * b[0]; b[1] = cdBaric[1] * b[1]; //beta * b
    c[0] = cdBaric[2] * a[0]; c[1] = cdBaric[2] * c[1]; //gama * c
    
    float result[] = {( a[0]+b[0]+c[0] ),( a[1]+b[1]+c[1])};
    
    return result;
  }
  
  public void imprimeVetor(float vetor[]) {
    if(vetor.length == 2) 
      System.out.println("[" + vetor[0] + "," + vetor[1] + "]");
    else
      System.out.println("[" + vetor[0] + "," + vetor[1] + "," + vetor[2] + "]");
  }
}  


