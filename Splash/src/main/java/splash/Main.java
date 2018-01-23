package splash;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {
    private static BufferedImage bi;

    public static void main(String[] args){
        Path path = FileSystems.getDefault().getPath("resources", "Demo_1.jpg");
        bi = loadImage(path.toString());
        createAndShowGUI();
        
    }
    
    public static BufferedImage loadImage(String ref) {  
            BufferedImage bimg = null;  
            try { bimg = ImageIO.read(new File(ref));} 
            catch (Exception e) { e.printStackTrace();}  
            return bimg;  
        }  

    private static void createAndShowGUI(){
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.add(new JLabel(new ImageIcon(bi)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
