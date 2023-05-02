package tp.partie2;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import javax.imageio.ImageIO;

public class Filtre {
    public static void main(String[] args) {
        try {
            // Répertoire contenant les images à flouter
            File inputDir = new File("images/input");
            File[] inputFiles = inputDir.listFiles();
            
            // Répertoire de sortie pour les images floutées
            File outputDir = new File("images/output");
            outputDir.mkdir();
            
            // Appliquer trois filtres gaussiens de tailles différentes à chaque image du répertoire
            float[] sigmaValues = {1.0f, 1.5f, 2.5f};
            int[] kernelSizes = {3, 5, 9};
            for (File inputFile : inputFiles) {
                // Charger l'image
                BufferedImage image = ImageIO.read(inputFile);
                
                // Appliquer les trois filtres gaussiens à l'image
                for (int i = 0; i < sigmaValues.length; i++) {
                    float sigma = sigmaValues[i];
                    int size = kernelSizes[i];
                    
                    // Créer un filtre gaussien de taille n x n et de sigma donné
                    float[] matrix = new float[size*size];
                    float sigma2 = 2 * sigma * sigma;
                    int center = size / 2;
                    float sum = 0.0f;
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
                            int x = j - center;
                            int y = k - center;
                            float value = (float) (Math.exp(-(x*x+y*y)/sigma2) / (Math.PI * sigma2));
                            matrix[j*size+k] = value;
                            sum += value;
                        }
                    }
                    for (int j = 0; j < matrix.length; j++) {
                        matrix[j] /= sum;
                    }
                    Kernel kernel = new Kernel(size, size, matrix);
                    
                    // Appliquer le filtre gaussien à l'image
                    BufferedImage blurredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = blurredImage.createGraphics();
                    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    g.drawImage(image, 0, 0, null);
                    g.dispose();
                    ConvolveOp op = new ConvolveOp(kernel);
                    blurredImage = op.filter(blurredImage, null);
                    
                    // Enregistrer l'image floutée dans un nouveau fichier avec un nom différent pour chaque filtre
                    String outputFileName = String.format("%s_flou_%dx%d__%.1f.png", inputFile.getName(), size, size, sigma);
                    File output = new File(outputDir, outputFileName);
                    ImageIO.write(blurredImage, "png", output);
                    
                    System.out.println("L'image floutée avec le filtre " + outputFileName + " a été enregistrée avec succès !");
                }
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }
    }
