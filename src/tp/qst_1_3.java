package tp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
// Augmentation du contraste d’une image en niveau de gris


public class qst_1_3 {
    public static void main(String[] args) throws Exception {
        // Charger l'image
        BufferedImage image = ImageIO.read(new File("/home/hakim/Desktop/projects/image-processing/images/input/img.jpg"));
        
        // Convertir l'image en niveaux de gris
        BufferedImage grayImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        
        // Calculer l'histogramme
        int[] histogram = new int[256];
        for (int y = 0; y < grayImage.getHeight(); y++) {
            for (int x = 0; x < grayImage.getWidth(); x++) {
                int grayLevel = new Color(grayImage.getRGB(x, y)).getRed();
                histogram[grayLevel]++;
            }
        }
        
        // Normaliser l'histogramme
        int numPixels = grayImage.getWidth() * grayImage.getHeight();
        double[] normalizedHist = new double[histogram.length];
        for (int i = 0; i < histogram.length; i++) {
            normalizedHist[i] = (double) histogram[i] / numPixels;
        }
        
        // Calculer la fonction de transformation
        int[] lookupTable = new int[256];
        double sum = 0;
        for (int i = 0; i < lookupTable.length; i++) {
            sum += normalizedHist[i];
            lookupTable[i] = (int) Math.round(255 * sum);
        }
        
        // Appliquer la fonction de transformation à l'image
        for (int y = 0; y < grayImage.getHeight(); y++) {
            for (int x = 0; x < grayImage.getWidth(); x++) {
                int grayLevel = new Color(grayImage.getRGB(x, y)).getRed();
                int newGrayLevel = lookupTable[grayLevel];
                Color newColor = new Color(newGrayLevel, newGrayLevel, newGrayLevel);
                grayImage.setRGB(x, y, newColor.getRGB());
            }
        }
        
        // Enregistrer l'image égalisée
        File output = new File("/home/hakim/Desktop/projects/image-processing/images/ouput/image_égalisée.jpg");
        ImageIO.write(grayImage, "jpg", output);
    }
}

