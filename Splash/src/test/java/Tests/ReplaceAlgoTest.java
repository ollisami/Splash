
package Tests;

import ReplacingAlgorithms.DummyPixelReplace;
import ReplacingAlgorithms.MixedRepeatPixelReplace;
import ReplacingAlgorithms.ReplacingAlgorithm;
import ReplacingAlgorithms.VerticalRepeatPixelReplace;
import SelectingAlgorithms.FloodFillSelection;
import SelectingAlgorithms.SelectionAlgorithm;
import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.*;
import Helpers.ColorPixel;
import Helpers.PixelPoint;
import splash.ImageEditor;

/**
 *
 * @author ollisami
 */
public class ReplaceAlgoTest {
       public ImageEditor imgEditor;
    public ColorPixel[][] pixels;
    public ColorPixel selectedAreaColor;

    public ReplaceAlgoTest() {
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
    public void testDummyReplace() {
        SelectionAlgorithm selectAlgo = new FloodFillSelection();
        ColorPixel[][] selected = selectAlgo.SelectPixels(this.pixels, selectedAreaColor, new PixelPoint(3,3), 1000, 1); 
        
        ReplacingAlgorithm replaceAlgo = new DummyPixelReplace();
        assertFalse(replaceAlgo.replacePixels(pixels, selected, selectedAreaColor) == null);
    }
    
    @Test
    public void testVericalReplace() {
        SelectionAlgorithm selectAlgo = new FloodFillSelection();
        ColorPixel[][] selected = selectAlgo.SelectPixels(this.pixels, selectedAreaColor, new PixelPoint(3,3), 1000, 1); 
        
        ReplacingAlgorithm replaceAlgo = new VerticalRepeatPixelReplace();
        assertFalse(replaceAlgo.replacePixels(pixels, selected, selectedAreaColor) == null);
    }
    
    @Test
    public void testMixedRepeatReplace() {
        SelectionAlgorithm selectAlgo = new FloodFillSelection();
        ColorPixel[][] selected = selectAlgo.SelectPixels(this.pixels, selectedAreaColor, new PixelPoint(3,3), 1000, 1); 
        
        ReplacingAlgorithm replaceAlgo = new MixedRepeatPixelReplace();
        assertFalse(replaceAlgo.replacePixels(pixels, selected, selectedAreaColor) == null);
    }
}
