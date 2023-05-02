package tp.partie2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MidPixels {

    public static void main(String[] args) {
        // les chemins d'accès de l'image source et de l'image de sortie
        String inputDir = "images/input";
        String outputDir = "images/output";

        // Récupérer la liste des fichiers du répertoire d'entrée 
        File inputDirectory = new File(inputDir);

        // récupérer la liste des fichiers dans le répertoire d'entrée
        File[] imageFiles = inputDirectory.listFiles();

        for (File imageFile : imageFiles) {
            if (imageFile.isFile() ) {
                try {
                    BufferedImage inputImage = ImageIO.read(imageFile);

                    // Crée une nouvelle image de sortie en niveaux de gris avec la même taille que l'image source
                    BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                    
                    // boucle sur chaque pixel de l'image source et on calcule la valeur de gris correspondante en utilisant la formule grayValue = (R + G + B) / 3
                    for (int y = 0; y < inputImage.getHeight(); y++) {
                        for (int x = 0; x < inputImage.getWidth(); x++) {
                            Color color = new Color(inputImage.getRGB(x, y));
                            int grayValue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                            Color grayColor = new Color(grayValue, grayValue, grayValue);
                            outputImage.setRGB(x, y, grayColor.getRGB());
                        }
                    }
                    
                    String outputFileName = imageFile.getName().substring(0, imageFile.getName().lastIndexOf(".")) + "_grayyy.jpg";
                    File outputFile = new File(outputDir + "\\" + outputFileName);

                    //enregistre l'image de sortie en utilisant la méthode ImageIO.write()
                    ImageIO.write(outputImage, "jpg", outputFile);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
