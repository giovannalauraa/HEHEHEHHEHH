package fonte;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Thread1 extends Thread {
    
    private int larguraInicial;
    private int larguraFinal;
    private int alturaInicial;
    private int alturaFinal;
    BufferedImage imagem;
    
    public Thread1(BufferedImage imagem){
        
        this.imagem = imagem;
        
        int altura = this.imagem.getHeight();
        int largura = this.imagem.getWidth();

        this.larguraInicial = 0;
        this.larguraFinal = largura/2 ;
        this.alturaInicial = 0;
        this.alturaFinal = altura/4;
        
    }
    
    
    public void run(){
        for (int x = larguraInicial; x < this.larguraFinal; x++){
            for (int y = this.alturaInicial; y < this.alturaFinal; y++){
                Color pixel = new Color(this.imagem.getRGB(x, y));
                int corPixel = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                this.imagem.setRGB(x, y, new Color(corPixel, corPixel, corPixel).getRGB());
            }
        }
        
        try {
            ImageIO.write(this.imagem, "JPG", new File("imgGrayscale1.jpg"));
            System.out.println("Escrevendo T1");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
    }
}