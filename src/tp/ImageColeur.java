package tp;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageColeur {

	public static void main(String[] args) {

		try {
			// chargement de l'image
			BufferedImage image = ImageIO.read(new File("images/input/tiger.jpg"));

			// Modifier la taille de l'image
			int newWidth = 800;
			int newHeight = 600;
			BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = newImage.createGraphics();
			g.drawImage(image, 0, 0, newWidth, newHeight, null);
			g.dispose();

			// Écrire la nouvelle image dans un fichier
			ImageIO.write(newImage, "jpg", new File("images/output/tigerR.jpg"));

			// Appliquer un filtre sur l'image (Flou Gaussien)
			float[] matrix = {
					0.0625f, 0.125f, 0.0625f,
					0.125f, 0.25f, 0.125f,
					0.0625f, 0.125f, 0.0625f
			};

			// Appliquer le filtre
			ConvolveOp op = new ConvolveOp(new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null);
			BufferedImage blurredImage = op.filter(image, null);

			// Écrire la nouvelle image dans un fichier


			ImageIO.write(blurredImage, "jpg", new File("images/output/tigerB.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
