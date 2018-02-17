package splash;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_simple.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_1.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_2.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_3.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_4.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_5.jpg");
        Path path = FileSystems.getDefault().getPath("resources", "Demo_6.jpg");
        
        BufferedImage bi = loadImage(path.toString());
        ImageEditor editor = new ImageEditor(convertImgToColorPixelArray(bi));
        createAndShowGUI(editor);

    }

    /**
    The Description of the method to explain what the method does
    @param ref path to image
    @return the image as buffered image
    */
    public static BufferedImage loadImage(String ref) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(ref));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }

    /**
    Draws the image file to a JFrame
    @param editor imageEditor that hold the image data
    */
    private static void createAndShowGUI(ImageEditor editor) {

        BufferedImage bi = editor.GetImage();

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(bi.getWidth(), bi.getHeight());
        ImageIcon imgIcon = new ImageIcon(bi);
        JLabel imgLabel = new JLabel(imgIcon);

        imgLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Pixel:" + e.getX() + "," + e.getY());
                System.out.println(editor.getPixelByCordinates(e.getX(), e.getY()));
                editor.selectPixelsByCordinates(e.getX(), e.getY());
                ImageIcon newImgIcon = new ImageIcon(editor.GetImage());
                imgLabel.setIcon(newImgIcon);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        frame.add(imgLabel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    Converts a buffered image to ColorPixel matrix
    @param image image to convert
    @return ColorPixel[][] holding the pixel data
    */
    private static ColorPixel[][] convertImgToColorPixelArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        ColorPixel[][] result = new ColorPixel[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[x][y] = new ColorPixel(image.getRGB(x, y));
            }
        }
        return result;
    }
}
