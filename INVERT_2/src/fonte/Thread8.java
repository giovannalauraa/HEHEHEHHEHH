package fonte;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Thread8 extends Thread {
    
    private int larguraInicial;
    private int larguraFinal;
    private int alturaInicial;
    private int alturaFinal;
    BufferedImage imagem;
    
    public Thread8(BufferedImage imagem){
        
        this.imagem = imagem;
        
        int altura = this.imagem.getHeight();
        int largura = this.imagem.getWidth();

        this.larguraInicial = largura / 2;
        this.larguraFinal = largura;
        this.alturaInicial = altura / 2;
        this.alturaFinal = altura;
        
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
            ImageIO.write(this.imagem, "JPG", new File("imgGrayscale8.jpg"));
            System.out.println("Escrevendo T8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
    }
}