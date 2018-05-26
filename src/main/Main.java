/*
 * UFRPE - Universidade Federal Rural de Pernambuco
DC - Departamento de Computação
Disiciplina: Computação Gráfica
Professor: João Paulo
Aluno: José Bartolomeu Alheiros Dias Neto

*/

package main;

public class Main {
 
  public static void main(String[] args) {  
    LibMath lib = new LibMath();
    
    float a[][] = { {-7.0f, -3.0f}, 
                    {4.0f, -8.0f}, 
                    {-1.0f, -6.0f} };
    
    float b[][] = { {2.0f, -5.0f, 3.0f}, 
                    {1.0f, 4.0f, 9.0f} };
    
    System.out.println(a.length);
    
    float result[][] = lib.calculaProduto(a, b);
    
    lib.imprimeMatriz(result);
    
    float v1[] = {1f, 2f, 3f};
    float v2[] = {2f, 0f, -1f};
    
    System.out.println();
    
    lib.imprimeVetor(lib.subtraiVetor(v1, v2));
    
    System.out.println();
    
    System.out.println(lib.produtoEscalar(v1, v2));
    
    v1[0] = 1f; v1[1] = 2f; v1[2] = 1f;
    v2[0] = 1f; v2[1] = 0f; v2[2] = -1f;
    
    float resultV[] = lib.produtoVetorial(v1, v2);
    lib.imprimeVetor(resultV);
    
    float n = lib.norma(resultV); //norma
    
    System.out.println(n);
    
    v1[0] = 1f; v1[1] = -2f; v1[2] = 2f;
    n = lib.norma(v1);
    System.out.println("Norma de v1: " + n);
    
    lib.imprimeVetor(lib.normaliza(v1, n));
    
    float p[] = {-0.25f,0.75f};
    float pontoA[] = {-1f,1f};
    float pontoB[] = {0f,-1f};
    float pontoC[] = {1f,0f};
    
    lib.imprimeVetor(lib.coordBaricentrica(p, pontoA, pontoB, pontoC));
    
    lib.imprimeVetor(lib.baric2Cartes(lib.coordBaricentrica(p, pontoA, pontoB, pontoC), pontoA, pontoB, pontoC));
  }
  
}
