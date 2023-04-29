package  tp.partie1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Histo extends JPanel {

private int[] redHistogram;
private int[] greenHistogram;
private int[] blueHistogram;

public Histo(int[] r, int[] g, int[] b) {

redHistogram = r;
greenHistogram = g;
blueHistogram = b;

}

public void paintComponent(Graphics g) {
super.paintComponent(g);
// Dessiner l'histogramme rouge
g.setColor(Color.RED);
for (int i = 0; i < 256; i++) {
g.drawLine(i, 200, i, 200 - (redHistogram[i] / 100));
}

// Dessiner l'histogramme vert
g.setColor(Color.GREEN);
for (int i = 0; i < 256; i++) {
g.drawLine(i, 400, i, 400 - (greenHistogram[i] / 100));
}
// Dessiner l'histogramme bleu
g.setColor(Color.BLUE);
for (int i = 0; i < 256; i++) {
g.drawLine(i, 600, i, 600 - (blueHistogram[i] / 100));
}
}

public static void main(String[] args) {
BufferedImage img = null;
try {
img = ImageIO.read(new File("images/input/DSC09092.JPG"));
} catch (IOException e) {
e.printStackTrace();
}

int width = img.getWidth();
int height = img.getHeight();
int[] redHistogram = new int[256];
int[] greenHistogram = new int[256];
int[] blueHistogram = new int[256];

// parcourir chaque pixel de l'image et incrémenter les histogrammes

for (int y = 0; y < height; y++) {
for (int x = 0; x < width; x++) {
Color c = new Color(img.getRGB(x, y));
redHistogram[c.getRed()]++;
greenHistogram[c.getGreen()]++;
blueHistogram[c.getBlue()]++;
}

}

// afficher les histogrammes

for (int i = 0; i < 256; i++) {
System.out.println("Composante rouge " + i + ": " + redHistogram[i]);
System.out.println("Composante verte " + i + ": " + greenHistogram[i]);
System.out.println("Composante bleue " + i + ": " + blueHistogram[i]);
}

// créer et afficher la JFrame

JFrame f = new JFrame();
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(280, 820);
f.setLocationRelativeTo(null);
Histo h = new Histo(redHistogram, greenHistogram, blueHistogram);
f.add(h, BorderLayout.CENTER);
f.setVisible(true);
}

}
