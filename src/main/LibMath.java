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
    float result[] = {0, 0, 0, 0};

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

  /* Calcula o produto vetorial entre dois vetores a e b, usando a regra de Laplace
   * utitliza o pre-processamento do método criaMatriz.*/
   
  public float produtoVetorial(float matriz[][]) {
    float determinante = 0;
    

    float[][] aux;
    int i_aux, j_aux, linha, coluna, i;            

    for(i = 0; i < 3; i++) {

      if(matriz[0][i] != 0) {
        aux = new float[2][2];
        i_aux = 0;
        j_aux = 0;

        for(linha = 1; linha < 3; linha++){
          for(coluna = 0; coluna < 3; coluna++){
            if(coluna != i){
              aux[i_aux][j_aux] = matriz[linha][coluna];
              j_aux++;
            }
          }

          i_aux++;
          j_aux = 0;
        }

        determinante += Math.pow(-1, i)*matriz[0][i]*produtoVetorial(aux);
      }

    }
  
  return determinante;
} 

public void imprimeVetor(float vetor[]) {
  System.out.println("[" + vetor[0] + "," + vetor[1] + "," + vetor[2] + "]");
}

/*cria matriz 3x3 a partir de dois vetores 3D. Usado como pre-processamento
 * do método do produtoVetorial.
 */
public float[][] criaMatriz(float a[], float b[]) {
  float matriz[][] = {{0,0,0},{0,0,0},{0,0,0}};
  
  matriz[0][0] = a[0];
  matriz[0][1] = a[1];
  matriz[0][2] = a[2];
  matriz[1][0] = b[0];
  matriz[1][1] = b[1];
  matriz[1][2] = b[2]; 
  
  return matriz;
    
} 

}

