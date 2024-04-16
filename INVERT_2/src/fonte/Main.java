package fonte;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        exibirMenu();
    }

    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int escolha;
        
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar e multiplicar matrizes");
            System.out.println("2. Transformar imagens coloridas em PB utilizando a estratégia de divisão de quatro quadrantes");
            System.out.println("3. Transformar imagens coloridas em PB utilizando a estratégia de divisão de oito quadrantes");
            System.out.println("4. Transformar imagens coloridas em PB utilizando a estratégia de divisão de tiras verticais");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            escolha = scanner.nextInt();
            
            switch (escolha) {
                case 1:
                    criarEMultiplicarMatrizes matriz = new criarEMultiplicarMatrizes();
                    criarEMultiplicarMatrizes();
                    break;
                case 2:
                    transformarImagensPB(4);
                    break;
                case 3:
                    transformarImagensPB(8);
                    break;
                case 4:
                    transformarImagensPBTirasVerticais();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (escolha != 0);
        
        scanner.close();
    }

    public static void transformarImagensPB(int quadrantes) {
        BufferedImage original = null;
        try {
            original = ImageIO.read(new File("percevejo.jpg"));
        } catch (IOException e) {
            System.out.println("Não leu a imagem");
        }

        Thread[] threads = new Thread[quadrantes];
        int altura = original.getHeight();
        int largura = original.getWidth();
        BufferedImage[] imagensProcessadas = new BufferedImage[quadrantes];

        for (int i = 0; i < quadrantes; i++) {
            int larguraInicial = (i % 2 == 0) ? 0 : largura / 2;
            int larguraFinal = (i % 2 == 0) ? largura / 2 : largura;
            int alturaInicial = (i < quadrantes / 2) ? 0 : altura / 2;
            int alturaFinal = (i < quadrantes / 2) ? altura / 2 : altura;

            imagensProcessadas[i] = new BufferedImage(largura / 2, altura / 2, BufferedImage.TYPE_INT_RGB);
            BufferedImage finalOriginal = original;
            int finalAlturaInicial = alturaInicial;
            int finalAlturaFinal = alturaFinal;
            int finalLarguraInicial = larguraInicial;
            int finalLarguraFinal = larguraFinal;
            threads[i] = new Thread(() -> {
                for (int x = finalLarguraInicial; x < finalLarguraFinal; x++) {
                    for (int y = finalAlturaInicial; y < finalAlturaFinal; y++) {
                        Color pixel = new Color(finalOriginal.getRGB(x, y));
                        int corPixel = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                        imagensProcessadas[i].setRGB(x, y, new Color(corPixel, corPixel, corPixel).getRGB());
                    }
                }
                try {
                    ImageIO.write(imagensProcessadas[i], "JPG", new File("imgGrayscale" + (i + 1) + ".jpg"));
                    System.out.println("Escrevendo T" + (i + 1));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void transformarImagensPBTirasVerticais() {
        BufferedImage original = null;
        try {
            original = ImageIO.read(new File("percevejo.jpg"));
        } catch (IOException e) {
            System.out.println("Não leu a imagem");
        }

        int altura = original.getHeight();
        int largura = original.getWidth();
        BufferedImage imagemProcessada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < largura / 2; x++) {
            for (int y = 0; y < altura; y++) {
                Color pixel = new Color(original.getRGB(x, y));
                int corPixel = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                imagemProcessada.setRGB(x, y, new Color(corPixel, corPixel, corPixel).getRGB());
            }
        }

        for (int x = largura / 2; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                Color pixel = new Color(original.getRGB(x, y));
                int corPixel = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                imagemProcessada.setRGB(x, y, new Color(corPixel, corPixel, corPixel).getRGB());
            }
        }

        try {
            ImageIO.write(imagemProcessada, "JPG", new File("imgGrayscaleVerticalStripes.jpg"));
            System.out.println("Escrevendo imagem com tiras verticais");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
