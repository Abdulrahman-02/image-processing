package tp;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class QST_2 {

	public static void main(String[] args) {
		 try {
        	 // Charge l'image à partir du fichier
            BufferedImage blurredImage = ImageIO.read(new File("img.jpg"));
         // Récupérer les dimensions de l'image
            int largeur = blurredImage.getWidth();
            int hauteur = blurredImage.getHeight();
            
          
           
        

            // Lire les valeurs des pixels de l'image modifiée
            System.out.println("Valeurs des pixels de l'image modifiée :");
            for (int y = 0; y < hauteur; y++) {
                for (int x = 0; x < largeur; x++) {
                    Color c = new Color(blurredImage.getRGB(x, y));
                    System.out.print("(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ") ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture de l'image : " + e.getMessage());
        }
    }
}