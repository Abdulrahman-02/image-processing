package tp.partie2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Contraste {
    public static void main(String[] args) throws Exception {
        // emplacement des images
        String inputDirPath = "images/input";
        String outputDirPath = "images/output";

       // 
        File inputDir = new File(inputDirPath);
        File[] inputFiles = inputDir.listFiles();

        // Process each input file
        for (File inputFile : inputFiles) {
            // Load input image
            BufferedImage image = ImageIO.read(inputFile);
            
            // Convert to grayscale
            BufferedImage grayImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics g = grayImage.getGraphics();
            g.drawImage(image, 0, 0, null);

            // Calculate histogram
            int[] histogram = new int[256];
            for (int y = 0; y < grayImage.getHeight(); y++) {
                for (int x = 0; x < grayImage.getWidth(); x++) {
                    int grayLevel = new Color(grayImage.getRGB(x, y)).getRed();
                    histogram[grayLevel]++;
                }
            }

            // Normalize histogram
            int numPixels = grayImage.getWidth() * grayImage.getHeight();
            double[] normalizedHist = new double[histogram.length];
            for (int i = 0; i < histogram.length; i++) {
                normalizedHist[i] = (double) histogram[i] / numPixels;
            }

            // Calculate transformation function
            int[] lookupTable = new int[256];
            double sum = 0;
            for (int i = 0; i < lookupTable.length; i++) {
                sum += normalizedHist[i];
                lookupTable[i] = (int) Math.round(255 * sum);
            }

            // Apply transformation to image
            for (int y = 0; y < grayImage.getHeight(); y++) {
                for (int x = 0; x < grayImage.getWidth(); x++) {
                    int grayLevel = new Color(grayImage.getRGB(x, y)).getRed();
                    int newGrayLevel = lookupTable[grayLevel];
                    Color newColor = new Color(newGrayLevel, newGrayLevel, newGrayLevel);
                    grayImage.setRGB(x, y, newColor.getRGB());
                }
            }

            // Save output image
            String outputFilePath = outputDirPath + "/" + inputFile.getName( )+"contraste.jpg";
            File outputFile = new File(outputFilePath);
            ImageIO.write(grayImage, "jpg", outputFile);
        }
    }
}
