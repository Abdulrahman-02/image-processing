package tp.partie2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Gamma {

    public static void main(String[] args) {
// répertoires d'entrée et de sortie
        String inputDir = "images/input";
        String outputDir = "images/output";

// Récupérer la liste des fichiers du répertoire d'entrée 
        File inputDirectory = new File(inputDir);

        // On utilise la méthode listFiles() pour récupérer la liste des fichiers dans le répertoire d'entrée
        File[] imageFiles = inputDirectory.listFiles();

        // Parcourir la liste des fichiers et pour chaque fichier, s'il s'agit bien d'un fichier 
        for (File imageFile : imageFiles) {
            if (imageFile.isFile() ) {
                try {
                    BufferedImage image = ImageIO.read(imageFile);
                    // Parcourir chaque pixel de l'image et calculer sa valeur de gris 
                    for (int y = 0; y < image.getHeight(); y++) {
                        for (int x = 0; x < image.getWidth(); x++) {
                            Color c = new Color(image.getRGB(x, y));
                            int gray = (int)(0.299*c.getRed()+0.587*c.getGreen()+0.114*c.getBlue());
                            Color grayColor = new Color(gray, gray, gray);
                            image.setRGB(x, y, grayColor.getRGB());
                        }
                    }
                    // nom et l'emplacement du fichier de sortie
                   String outputFileName = imageFile.getName().substring(0, imageFile.getName().lastIndexOf(".")) + "_gray.jpg";
                    File outputFile = new File(outputDir + "\\" + outputFileName);

                    // Écrire l'image convertie en niveaux de gris dans le fichier de sortie
                    ImageIO.write(image, "jpg", outputFile);

                } catch (Exception e) {
                    System.out.println("Erreur : " + e.getMessage());
                }
            }
        }
    }
}
