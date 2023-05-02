package tp.partie2;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GrayScale {

    public static void main(String[] args) {
    	//objet File qui pointe vers le répertoire contenant les images d'entrée
        File inputDir = new File("C:\\Users\\ASUS\\OneDrive\\Bureau\\input");
        //objet File qui pointe vers le répertoire où les images de sortie seront écrites
        File outputDir = new File("C:\\Users\\ASUS\\OneDrive\\Bureau\\output");
       //On utilise la méthode listFiles() pour récupérer la liste des fichiers dans le répertoire d'entrée
        
        File[] inputFiles = inputDir.listFiles();
        
        // boucle for pour parcourir tous les fichiers du répertoire d'entrée.
        for (File inputFile : inputFiles) {
            // Vérification que le fichier est bien un fichier et non un répertoire
        	if (inputFile.isFile()) {
                try {
                	// Lecture de l'image d'entrée
                    BufferedImage inputImage = ImageIO.read(inputFile);

                    // pour convertir l'image en niveaux de gris
                    BufferedImage outputImage = convertToGrayscale(inputImage);
                    
                    // Définition du nom de fichier de sortie
                    String outputFilename = inputFile.getName().replaceAll("\\.(?=[^\\.]+$)", "_grayscale.");

                    // Définition du fichier de sortie 
                    File outputFile = new File(outputDir, outputFilename);
                    
                    // Écriture de l'image de sortie
                    ImageIO.write(outputImage, "jpg", outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // Conversion de l'image en niveaux de gris
    private static BufferedImage convertToGrayscale(BufferedImage inputImage) {
    	ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        return op.filter(inputImage, null);
    }
}
