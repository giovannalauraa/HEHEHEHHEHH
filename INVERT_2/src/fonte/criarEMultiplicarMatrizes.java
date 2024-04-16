import java.util.Random;
import java.util.Scanner;

public static void criarEMultiplicarMatrizes() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite o número de linhas para a Matriz 1:");
    int linhasMatriz1 = scanner.nextInt();
    System.out.println("Digite o número de colunas para a Matriz 1:");
    int colunasMatriz1 = scanner.nextInt();
    System.out.println("Digite o número de linhas para a Matriz 2:");
    int linhasMatriz2 = scanner.nextInt();
    System.out.println("Digite o número de colunas para a Matriz 2:");
    int colunasMatriz2 = scanner.nextInt();

    int[][] matriz1 = new int[linhasMatriz1][colunasMatriz1];
    int[][] matriz2 = new int[linhasMatriz2][colunasMatriz2];

    if (colunasMatriz1 != linhasMatriz2) {
        System.out.println("Dimensões de matriz inválidas para multiplicação.");
        return;
    }

    int[][] matrizResultado = new int[linhasMatriz1][colunasMatriz2];
    Random rand = new Random();

    System.out.println("Matriz 1:");
    preencherMatriz(matriz1, rand);
    imprimirMatriz(matriz1);

    System.out.println("Matriz 2:");
    preencherMatriz(matriz2, rand);
    imprimirMatriz(matriz2);

    ThreadMatriz[] threads = new ThreadMatriz[linhasMatriz1];
    for (int i = 0; i < linhasMatriz1; i++) {
        threads[i] = new ThreadMatriz(matriz1, matriz2, matrizResultado, i);
        threads[i].start();
    }

    for (ThreadMatriz thread : threads) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    System.out.println("Matriz Resultado:");
    imprimirMatriz(matrizResultado);
    
    scanner.close();
}

public static void preencherMatriz(int[][] matriz, Random rand) {
    int linhas = matriz.length;
    int colunas = matriz[0].length;
    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            matriz[i][j] = rand.nextInt(10);
        }
    }
}

public static void imprimirMatriz(int[][] matriz) {
    int linhas = matriz.length;
    int colunas = matriz[0].length;
    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            System.out.print(matriz[i][j] + " ");
        }
        System.out.println();
    }
}