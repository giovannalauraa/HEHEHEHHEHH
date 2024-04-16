package fonte;

public class ThreadMatriz extends Thread {
    private int[][] matriz1;
    private int[][] matriz2;
    private int[][] matrizResultado;
    private int linha;

    public ThreadMatriz(int[][] matriz1, int[][] matriz2, int[][] matrizResultado, int linha) {
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.matrizResultado = matrizResultado;
        this.linha = linha;
    }

    public void run() {
        int colunasMatriz2 = matriz2[0].length;
        int colunasMatriz1 = matriz1[0].length;

        for (int j = 0; j < colunasMatriz2; j++) {
            int temp = 0;
            for (int k = 0; k < colunasMatriz1; k++) {
                temp += matriz1[linha][k] * matriz2[k][j];
            }
            synchronized (matrizResultado[linha]) {
                matrizResultado[linha][j] = temp;
            }
        }
    }
}