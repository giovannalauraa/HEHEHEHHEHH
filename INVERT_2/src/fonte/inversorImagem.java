package fonte;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import javax.imageio.ImageIO;

public class inversorImagem {

    public static void main(String[] args) throws Throwable {
        BufferedImage original = null;
        try {
            original = ImageIO.read(new File("percevejo.jpg"));
        } catch (IOException e) {
            System.out.println("Não leu a imagem");
        }

        // constroi threads
        Thread1 thread1 = new Thread1(original);
        Thread2 thread2 = new Thread2(original);
        Thread3 thread3 = new Thread3(original);
        Thread4 thread4 = new Thread4(original);
        Thread5 thread5 = new Thread5(original);
        Thread6 thread6 = new Thread6(original);
        Thread7 thread7 = new Thread7(original);
        Thread8 thread8 = new Thread8(original);

        // executa as threads
        System.out.println("Inicio = " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute()
                + ":" + LocalTime.now().getSecond());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fim = " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute()
                + ":" + LocalTime.now().getSecond());
    }

}
