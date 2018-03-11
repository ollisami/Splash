
package splash;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageReader {
    public BufferedImage readImageByame (String name) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(name);
        return loadImage(is);
    }
    
    /**
    Lukee tiedoston resources kansiosta
    @param ref path to image
    @return the image as buffered image
    */
    public static BufferedImage loadImage(InputStream is) {
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }
}
