package tp.partie1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Histogramme extends JPanel {

    // attributs
    private int[] histogram;
    private Color color;

    // constructeur
    public Histogramme(int[] h, Color c) {
        histogram = h;
        color = c;
    }
    // redimensionner l'image
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        for (int i = 0; i < 256; i++) {
            g.drawLine(i, 200, i, 200 - (histogram[i] / 100));
        }
    }

    public static void main(String[] args) {
        // charger l'image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images/output/tigerB.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // récupérer la largeur et la hauteur de l'image
        int width = img.getWidth();
        int height = img.getHeight();

        // créer les tableaux des histogrammes
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
        JFrame redFrame = new JFrame("Histogramme rouge");
        redFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        redFrame.setSize(280, 240);
        redFrame.setLocationRelativeTo(null);
        Histogramme redHisto = new Histogramme(redHistogram, Color.RED);
        redFrame.add(redHisto, BorderLayout.CENTER);
        redFrame.setVisible(true);

        JFrame greenFrame = new JFrame("Histogramme vert");
        greenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        greenFrame.setSize(280, 240);
        greenFrame.setLocationRelativeTo(null);
        Histogramme greenHisto = new Histogramme(greenHistogram, Color.GREEN);
        greenFrame.add(greenHisto, BorderLayout.CENTER);
        greenFrame.setVisible(true);

        JFrame blueFrame = new JFrame("Histogramme bleu");
        blueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        blueFrame.setSize(280, 240);
        blueFrame.setLocationRelativeTo(null);
        Histogramme blueHisto = new Histogramme(blueHistogram, Color.BLUE);
        blueFrame.add(blueHisto, BorderLayout.CENTER);
        blueFrame.setVisible(true);

    }

}
