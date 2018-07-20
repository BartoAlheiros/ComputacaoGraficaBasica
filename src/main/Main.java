/*
 * UFRPE - Universidade Federal Rural de Pernambuco
DC - Departamento de Computação
Disiciplina: Computação Gráfica
Professor: João Paulo
Aluno: José Bartolomeu Alheiros Dias Neto

 */

package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {

	static FileReader arq;
	static BufferedReader lerArq;
	static String linha; 
	static String[] str;
	static LibMath lib = new LibMath();
	static float[] vLinha = new float[3];
	static float[] u = new float[3];
	static float[] v = new float[3];
	static float[] n = new float[3];
	static float[] c = new float[3];
	static float[] pLinha;
	static int d, hx, hy;
	static double i, j; // coordenadas de tela

	public static void main(String[] args) {  
		carregaCamera();
		carregaArquivo();
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));        
		String in = null;

		/* espera sempre uma entrada do teclado. Se ela for 'r', recarrega as
		 * configura��es da camera  o arquivo.*/
		while (true) {  
			//obtem dado do teclado
			try {
				in = inFromUser.readLine();
				System.out.println("Digite p para fornecer o ponto P ou r para recarregar os parametros de camera"); 
				if (in.equals("r")) {
					carregaArquivo();
					carregaCamera();
				} else if (in.equals("p")) {
					carregaArquivo();
					carregaCamera();

					float[] P = new float[3];
					in = inFromUser.readLine();
					P[0] = Float.parseFloat(in);
					in = inFromUser.readLine();
					P[1] = Float.parseFloat(in);
					in = inFromUser.readLine();
					P[2] = Float.parseFloat(in);

					/* Convertendo do sistema de coordenadas mundial para o sistema de vista */
					Gram_Schmidt();
					pLinha = Mundial_to_Vista(P);

					System.out.println(Arrays.toString(pLinha));

				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
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

	public static float achaXmin(ArrayList<Vertice> vertices) {
		float xMin = 0f;

		for(Vertice vertice: vertices) {
			if(vertice.x < xMin)
				xMin = vertice.x;
		}

		return xMin;
	}

	public static float achaXmax(ArrayList<Vertice> vertices) {
		float xMax = 0f;

		for(Vertice vertice: vertices) {
			if(vertice.x > xMax)
				xMax = vertice.x;
		}

		return xMax;
	}

	public static float achaYmin(ArrayList<Vertice> vertices) {
		float yMin = 0f;

		for(Vertice vertice: vertices) {
			if(vertice.y < yMin)
				yMin = vertice.y;
		}

		return yMin;
	}

	public static float achaYmax(ArrayList<Vertice> vertices) {
		float yMax = 0f;

		for(Vertice vertice: vertices) {
			if(vertice.y > yMax)
				yMax = vertice.y;
		}

		return yMax;
	}

	// testa a primeira parte do projeto
	public static void testesParte1() {
		/*(a)*/
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
		float normaV1 = lib.norma(v1);
		System.out.println("Letra E: ");
		System.out.println(normaV1);
		System.out.println();

		/*(f)*/
		System.out.println("Letra F: ");lib.imprimeVetor(lib.normaliza(v1, normaV1));
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
	}

	public static void carregaArquivo() {
		/*2*/
		FileReader arq;
		try {
			arq = new FileReader("./objetos/maca2.byu");
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine(); //l� a primeira linha e armazena na String linha
			String[] strA = linha.split(" "); //corta a primera linha e armazena no array strA
			ArrayList<Vertice> vertices = new ArrayList<>();
			ArrayList<Triangulo> triangulos = new ArrayList<>();
			int i;

			// carregando os vértices do polígono em R3
			for (i = 2; i <= Integer.parseInt(strA[0]) + 1; i++) {
				linha = lerArq.readLine(); 

				String[] strB = linha.split(" ");

				Vertice v = new Vertice(Float.parseFloat(strB[0]), Float.parseFloat(strB[1]), Float.parseFloat(strB[2]));
				vertices.add(v); 
			}
			
			// passando os vértices do sistema mundial para o sistema de Vista
			for (Vertice v: vertices) {
				Vertice v2 = new Vertice();
				Mundial_to_Vista(v.getXYZ());
			}

			/* Para cada linha de triangulo no arquivo, adiciona um triangulo no ArrayList 
			 * - juntamente com seus índices lidos do arquivo(carrega os triangulos) */
			for (int j = i; j <= Integer.parseInt(strA[1]); j++) {
				linha = lerArq.readLine(); 

				String[] strB = linha.split(" ");

				Triangulo t = new Triangulo(Integer.parseInt(strB[0]), Integer.parseInt(strB[1]), Integer.parseInt(strB[2]));
				triangulos.add(t);
			}

			/* Para cada triangulo, adiciona seus vértices - que pertencem ao polígono - , baseado nos índices já salvos anteriormente */
			for (Triangulo t: triangulos) {
				ArrayList<Vertice> verticesTriangulo = new ArrayList<>();

				verticesTriangulo.add(vertices.get(t.getI1())); 
				verticesTriangulo.add(vertices.get(t.getI2())); 
				verticesTriangulo.add(vertices.get(t.getI3()));

				t.setVertices(verticesTriangulo);
			}

			// passando as coordenadas de cada vertice do polígono do sistema mundial para o sistema de vista
			for (Vertice vertice: vertices) {
				float[] p = vertice.getXY();
				float[] pLinha = Mundial_to_Vista(p);
				vertice.x = pLinha[0]; vertice.y = pLinha[1];
			}

			// instancia uma janela e repassa os v�rtices para que sejam pintados nela
			DesenhoView window = new DesenhoView(vertices);
			window.vertices = normaliza(500, 500, vertices);
			window.frame.setVisible(true);

			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
	}

	public static void carregaCamera() {
		try {
			arq = new FileReader("./parametrosCamera/parametrosCam.cam");
			lerArq = new BufferedReader(arq);

			v = new float[3];
			n = new float[3];
			u = new float[3];
			c = new float[3];

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

			// inicializa u
			cortaLinha();
			u[0] = Integer.parseInt(str[2]);
			u[1] = Integer.parseInt(str[3]);
			u[2] = Integer.parseInt(str[4]);

			for (int i = 0; i < u.length; i++) {
				System.out.print(u[i]);
			}
			System.out.println();

			cortaLinha();
			d = Integer.parseInt(str[2]);
			System.out.println(d);

			cortaLinha();
			hx = Integer.parseInt(str[2]);
			System.out.println(hx);

			cortaLinha();
			hy = Integer.parseInt(str[2]);
			System.out.println(hy);

			cortaLinha();
			c[0] = Integer.parseInt(str[2]);
			c[1] = Integer.parseInt(str[3]);
			c[2] = Integer.parseInt(str[4]);

			for (int i = 0; i < c.length; i++) {
				System.out.print(c[i]);
			}

			for (int i = 0; i < u.length; i++) {
				System.out.print(u[i]);
			}

		} catch (FileNotFoundException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		} catch (IOException e) {
			System.err.printf("Erro na leitura do arquivo: %s.\n", e.getMessage());
		}
	}

	/*Recebe um ponto p do polígono no sistema de coordenadas mundial, converte para o sistema de coordenadas de vista
	 * e devolve p'(ponto no sistema de vista).*/
	public static float[] Mundial_to_Vista(float[] p) {
		float[][] pSubCCol;
		float[] pSubC;

		// obtendo as normas de U, V, N
		float normaU = lib.norma(u);
		float normaVLinha = lib.norma(vLinha);
		float normaN = lib.norma(n);
		System.out.println("Norma de vLinha: " + normaVLinha); //ok
		// obtendo a base ortonormal do sistema de vista alpha
		float nBarra[] = {1/normaN * n[0], 1/normaN * n[1], 1/normaN * n[2]};
		float vLinhaBarra[] = {(1/normaVLinha) * vLinha[0], (1/normaVLinha) * vLinha[1], (1/normaVLinha) * vLinha[2]};
		float uBarra[] = {1/normaU * u[0], 1/normaU * u[1], 1/normaU * u[2]};

		System.out.println("vLinhaBarra: ");

		for (int i = 0; i < vLinhaBarra.length; i++) {
			System.out.print(vLinhaBarra[i]);
		}

		// matriz de convers�o de bases I
		float[][] I = { { uBarra[0], uBarra[1], uBarra[2] }, 
				{ vLinhaBarra[0], vLinhaBarra[1], vLinhaBarra[2] }, 
				{ nBarra[0], nBarra[1], nBarra[2] } };

		// (P - C)
		pSubC = lib.subtraiVetor(p, c);
		// colocando (P - C) em uma Matriz Coluna
		pSubCCol = new float[3][1];
		pSubCCol[0][0] = pSubC[0]; 
		pSubCCol[1][0] = pSubC[1];
		pSubCCol[2][0] = pSubC[2];

		// calculando e devolvendo pLinha
		float[][] pLinha = lib.calculaProduto(I, pSubCCol);

		// convertendo a matriz acima em um vetor para facilitar os cálculos
		float[] pLinhaVet = {pLinha[0][0], pLinha[1][0], pLinha[2][0]};

		return pLinhaVet;
	}

	/* processo de Gram-Schmidt para ortogonalizar V e calcular U */
	public static void Gram_Schmidt() {
		/* produto escalar entre V e N = <V,N> */
		float Vn = lib.produtoEscalar(v, n);
		/* produto escalar entre N e N = <N,N> */
		float Nn = lib.produtoEscalar(n, n);

		/* salvando o Inverso do produto escalar entre N e N em uma vari�vel extra */
		float invNn = 1/Nn;

		// Ortogonalizando V:
		/* Calculando V' */
		float e = Vn * invNn; // escalar resultado dos produtos escalares entre <V,N> e inv<N,N> tal que: < <V,N>, inv<N,N> > = <V,N>/<N,N>
		/* multiplicando o escalar(e) obtido pelo vetor N e salvando no vetor  
		 * resultInt(resultadoIntermediario) */
		float[] resultInt = {n[0]*e, n[1]*e, n[2]*e};
		// Último passo para obter vLinha:
		vLinha = lib.subtraiVetor(v, resultInt);

		/* Calculando e devolvendo U */
		u = lib.produtoVetorial(n, vLinha);		
	}

	public static void projecaoEmPerspectiva() {
		/* Realizando a projeção em perspectiva. */
		float xv = pLinha[0];
		float yv = pLinha[1];
		float zv = pLinha[2];
		float xs = d * (xv/zv);
		float ys = d * (yv/zv);
		/* Convertendo as coordenadas xs e ys para coordenadas normalizadas. */
		float xsBarra = xs/hx;
		float ysBarra = ys/hy;

		/* Convertendo as coordenadas normalizadas(xsBarra, ysBarra) para Coordenadas de Tela. */
		// Pegando o x e o y da tela
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		double resX = d.getWidth();
		double resY = d.getHeight();

		i = Math.floor((xsBarra+1)/2 * resX + 0.5);
		j = Math.floor(resY - ( (ysBarra + 1)/2 + 0.5) );
	}

	public static void scanLine() {

	}

	public static void cortaLinha() throws IOException {
		linha = lerArq.readLine(); 
		str = linha.split(" ");
	}

	public static void capturaTecla(java.awt.event.KeyEvent e) {
		char tecla = e.getKeyChar();
		if (tecla == 'r') {
			carregaCamera();
		}
	}
}
