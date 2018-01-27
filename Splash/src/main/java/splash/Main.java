package splash;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("resources", "Demo_1.jpg");
        BufferedImage bi = loadImage(path.toString());
        
        ImageEditor editor = new ImageEditor(convertImgToColorPixelArray(bi));
        
        //createAndShowGUI(bi);

    }

    public static BufferedImage loadImage(String ref) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(ref));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }

    private static void createAndShowGUI(BufferedImage bi) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.add(new JLabel(new ImageIcon(bi)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static ColorPixel[][] convertImgToColorPixelArray(BufferedImage image) {
        int width  = image.getWidth();
        int height = image.getHeight();
        ColorPixel[][] result = new ColorPixel[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = new ColorPixel(image.getRGB(col, row));
            }
        }
        return result;
    }
}
