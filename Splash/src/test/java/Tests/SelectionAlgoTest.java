package Tests;

import SelectingAlgorithms.DummyLoopSelection;
import SelectingAlgorithms.FloodFillSelection;
import SelectingAlgorithms.OnePassCCL;
import SelectingAlgorithms.SelectionAlgorithm;
import java.awt.Point;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import Helpers.ColorPixel;
import Helpers.PixelPoint;
import splash.ImageEditor;

public class SelectionAlgoTest {

    public ImageEditor imgEditor;
    public ColorPixel[][] pixels;
    public ColorPixel selectedAreaColor;

    public SelectionAlgoTest() {
        this.pixels = new ColorPixel[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (x < 5 && y < 5) {
                    pixels[x][y] = new ColorPixel(255, 255, 0, 0);
                } else {
                    pixels[x][y] = new ColorPixel(255, 0, 255, 0);
                }
            }
        }
        this.imgEditor = new ImageEditor(pixels);
        selectedAreaColor = new ColorPixel(255, 0, 255, 0);
    }

    @Test
    public void testDummySelection() {
        SelectionAlgorithm algo = new DummyLoopSelection();
        assertFalse(algo.SelectPixels(this.pixels, selectedAreaColor, new PixelPoint(3,3), 1000, 1) == null); 
    }
    
    @Test
    public void testFloodFillSelection() {
        SelectionAlgorithm algo = new FloodFillSelection();
        assertFalse(algo.SelectPixels(this.pixels, selectedAreaColor, new PixelPoint(3,3), 1000, 1) == null); 
    }
    
    @Test
    public void testOnePassCCLselection() {
        SelectionAlgorithm algo = new OnePassCCL();
        assertFalse(algo.SelectPixels(this.pixels, selectedAreaColor, new PixelPoint(3,3), 1000, 1) == null); 
    }
}
