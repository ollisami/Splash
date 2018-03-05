package splash;

import Helpers.ColorPixel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

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
        
        //String imageName = "Demo_simple.jpg";
        //String imageName = "Demo_1.jpg";
        //String imageName = "Demo_2.jpg";
        //String imageName = "Demo_3.jpg";
        //String imageName = "Demo_4.jpg";
        String imageName = "Demo_5.jpg";
        //String imageName = "Demo_6.jpg";
        
        //Performance testing
        //String imageName = "performance_test_400.jpg";
        //String imageName = "performance_test_800.jpg";
        //String imageName = "performance_test_1000.jpg";
        
        
        //Luetaan kuva tiedosto
        //InputStream is = getClass().getClassLoader().getResourceAsStream("images/munkuva.jpg");
        ImageReader imgReader = new ImageReader();
        BufferedImage bi = imgReader.readImageByame(imageName);
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
