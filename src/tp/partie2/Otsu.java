package tp.partie2;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.io.File;
import java.io.FilenameFilter;
import javax.imageio.ImageIO;

public class Otsu {
    public static void main(String[] args) {
        // Chemin du répertoire contenant les images
        String inputDirectoryPath = "images/input";

        // Liste tous les fichiers  dans le répertoire
        File inputDirectory = new File(inputDirectoryPath);
        File[] inputFiles = inputDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".jpg")||
                name.toLowerCase().endsWith(".jpeg") ||
                name.toLowerCase().endsWith(".png");
            }
        });

        // Créer un répertoire de sortie pour les images binarisées
        String outputDirectoryPath = "images/output";
        File outputDirectory = new File(outputDirectoryPath);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }

        // Initialiser OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Appliquer la méthode d'Otsu à chaque image et enregistrer les images binarisées en sortie
        for (File inputFile : inputFiles) {
            // Charger l'image en niveaux de gris
            Mat grayImage = Imgcodecs.imread(inputFile.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);

            // Appliquer la méthode d'Otsu pour trouver le seuil optimal
            Mat binaryImage = new Mat();
            Imgproc.threshold(grayImage, binaryImage, 0, 255, Imgproc.THRESH_BINARY+Imgproc.THRESH_OTSU);

            // Enregistrer l'image binaire résultante dans le répertoire de sortie
            String outputFilePath = outputDirectoryPath + "/" + inputFile.getName()+ "otsu.jpg";
            Imgcodecs.imwrite(outputFilePath, binaryImage);   
            
        }
    }
}
