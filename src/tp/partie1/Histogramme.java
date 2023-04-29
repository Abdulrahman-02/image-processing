package tp.partie1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Histogramme {

	public static void main(String[] args) throws IOException {

		// Load the original image
		BufferedImage image = ImageIO.read(new File("images/input/image.png"));
		int width = image.getWidth();
		int height = image.getHeight();

		// Extract the red, green and blue color channels
		int[] reds = new int[256];
		int[] greens = new int[256];
		int[] blues = new int[256];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Color color = new Color(image.getRGB(i, j));
				reds[color.getRed()]++;
				greens[color.getGreen()]++;
				blues[color.getBlue()]++;
			}
		}

		// Create a new blank image for each color channel
		BufferedImage redImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		BufferedImage greenImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		BufferedImage blueImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);

		// Draw the histogram for each color channel on its corresponding blank image
		Graphics g = redImage.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 256, 256);
		g.setColor(Color.RED);
		drawHistogram(g, reds);

		g = greenImage.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 256, 256);
		g.setColor(Color.GREEN);
		drawHistogram(g, greens);

		g = blueImage.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 256, 256);
		g.setColor(Color.BLUE);
		drawHistogram(g, blues);

		// Save each image with a different name
		ImageIO.write(redImage, "png", new File("images/ouput/red_histogram.png"));
		ImageIO.write(greenImage, "png", new File("images/ouput/green_histogram.png"));
		ImageIO.write(blueImage, "png", new File("images/ouput/blue_histogram.png"));
	}

	private static void drawHistogram(Graphics g, int[] data) {
		int max = getMaxValue(data);
		for (int i = 0; i < data.length; i++) {
			int value = data[i];
			int x = i;
			int y = 255 - (int) ((double) value / max * 255.0);
			g.drawLine(x, y, x, 255);
		}
	}

	private static int getMaxValue(int[] data) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}
		return max;
	}
}
