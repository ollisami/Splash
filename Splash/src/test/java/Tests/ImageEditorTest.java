package Tests;

import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;
import splash.ColorPixel;
import splash.ImageEditor;


public class ImageEditorTest {
    
    public ImageEditor imgEditor;
    public ColorPixel[][] pixels;
    
    public ImageEditorTest() {
        this.pixels = new ColorPixel[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if(x < 5 && y < 5)
                    pixels[x][y] = new ColorPixel(255, 255, 0, 0);
                else
                    pixels[x][y] = new ColorPixel(255, 0, 255, 0);
            }
        }
        
        this.imgEditor = new ImageEditor(pixels);
    }
    
    @Test
    public void TestGetPixelByCordinates() {
        assertTrue(this.imgEditor.getPixelByCordinates(-1, -1) == null);
        assertTrue(this.imgEditor.getPixelByCordinates(this.pixels.length, this.pixels.length) == null);
    }
    
    @Test
    public void TestSelectingPixels() {
        this.imgEditor.selectPixelsByCordinates(3, 3);
        assertFalse(this.imgEditor.getSelectedPixels()[3][3] == null);
    }
    
    @Test
    public void TestCreatingImageEditor() {
        assertTrue(this.imgEditor.getPixelByCordinates(5, 5).equals(this.pixels[5][5]));
        assertTrue(this.imgEditor.getPixelByCordinates(0, 0).equals(this.pixels[0][0]));
        assertTrue(this.imgEditor.getPixelByCordinates(9, 9).equals(this.pixels[9][9]));
    }
    
    @Test
    public void TestGettingImage() {
        BufferedImage bi = this.imgEditor.GetImage();
        assertEquals(bi.getHeight(), 10);
        assertEquals(bi.getWidth(), 10);
        assertEquals(bi.getRGB(0, 0), new ColorPixel(255, 255, 0, 0).getRGB());
        assertEquals(bi.getRGB(5, 5), new ColorPixel(255, 0, 255, 0).getRGB());
        assertEquals(bi.getRGB(9, 9), new ColorPixel(255, 0, 255, 0).getRGB());
    }
    
}
