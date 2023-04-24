package tp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadImage {

    public static void main(String[] args) {
        try {
            // Load the image
            BufferedImage image = ImageIO.read(new File("images/image.png"));
            BufferedImage blurredImage = ImageIO.read(new File("images/blurredImage.png"));

            // Get the width and height of the image
            int width = image.getWidth();
            int height = image.getHeight();

            int widthBlurredImage = blurredImage.getWidth();
            int heightBlurredImage = blurredImage.getHeight();
            
            int[] histogram = new int[256]; // 256 possible intensity values
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int intensity = (int) (0.299 * ((rgb >> 16) & 0xFF) + 0.587 * ((rgb >> 8) & 0xFF) + 0.114 * (rgb & 0xFF));
                    histogram[intensity]++;
                }
            }

            int[] histogramBlurredImage = new int[256];
            for (int y = 0; y < heightBlurredImage; y++) {
                for (int x = 0; x < widthBlurredImage; x++) {
                    int rgb = blurredImage.getRGB(x, y);
                    int intensity = (int) (0.299 * ((rgb >> 16) & 0xFF) + 0.587 * ((rgb >> 8) & 0xFF) + 0.114 * (rgb & 0xFF));
                    histogramBlurredImage[intensity]++;
                }
            }

            // Display the histogram
            for (int i = 0; i < 256; i++) {
                System.out.println(i + ": " + histogram[i]);
            }

            // Display the histogram
            for (int i = 0; i < 256; i++) {
                System.out.println(i + ": " + histogramBlurredImage[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
