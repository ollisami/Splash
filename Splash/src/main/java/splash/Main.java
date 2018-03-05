package splash;

import Helpers.ColorPixel;
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

    static JLabel imgLabel;
    
    
    /*
        Tämä luokka hoitaa kuvan latamisen ja muuttamisen pikseli dataksi.
        Kuvan lataamista ja muuntamista pikselidataksi ei ole laskettu osaksi
        varsinaista algoritmiä, sillä ne ovat laitteistosta ja käyttötarkoituksesta
        riippuvia.
     */
    public static void main(String[] args) {
        
        
        //Aluksi valitaan käytettävä kuvatiedosto
        
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_simple.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_1.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_2.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_3.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_4.jpg");
        Path path = FileSystems.getDefault().getPath("resources", "Demo_5.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "Demo_6.jpg");
        
        //Performance testing
        //Path path = FileSystems.getDefault().getPath("resources", "performance_test_400.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "performance_test_800.jpg");
        //Path path = FileSystems.getDefault().getPath("resources", "performance_test_1000.jpg");
        
        
        //Luetaan kuva tiedosto
        BufferedImage bi = loadImage(path.toString());
        // Muunnetaan kuva pikselidataksi
        ImageEditor editor = new ImageEditor(convertImgToColorPixelArray(bi));
        // Piirretään kuva näytölle
        createAndShowGUI(editor);
        
        //Performance testing
        /*
        long aikaAlussa = System.currentTimeMillis(); 
        editor.selectPixelsByCordinates(500, 500);
        ImageIcon newImgIcon = new ImageIcon(editor.GetImage());
        imgLabel.setIcon(newImgIcon);
        long aikaLopussa = System.currentTimeMillis(); 
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms."); 
        */
    }

    /**
    Lukee tiedoston resources kansiosta
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
    Piirtää kuvan JFrame:en
    @param editor imageEditor that hold the image data
    */
    private static void createAndShowGUI(ImageEditor editor) {

        BufferedImage bi = editor.GetImage();

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(bi.getWidth(), bi.getHeight());
        ImageIcon imgIcon = new ImageIcon(bi);
        imgLabel = new JLabel(imgIcon);

        imgLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Pixel:" + e.getX() + "," + e.getY());
                System.out.println(editor.getPixelByCordinates(e.getX(), e.getY()));
                editor.selectAndReplacePixelsByCordinates(e.getX(), e.getY());
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
    Muuttaa kuvan ColorPixel matriisiksi.
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
