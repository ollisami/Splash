package SelectingAlgorithms;

import java.awt.Point;
import splash.ColorPixel;

public class DummyLoopSelection implements SelectionAlgorithm {
    /**
    Loops through all the pixel in the image and selects the ones with matching colour
    Time: 0(n²), Space: O(n²).
    @param pixels Current pixels matrix
    @param selectedAreaColor Color of the selected pixels
    @param startPos coordinates for the start pixel
    @param range the accuracy of selection
    @param expandAmount the amount how much selection should be expanded
    @return ColorPixel[][] selectedPixels
    */
    @Override
    public ColorPixel[][] SelectPixels(ColorPixel[][] pixels, ColorPixel selectedAreaColor, Point startPos ,int range, int expandAmount) {
        
        ColorPixel[][] selectedPixels = new ColorPixel[pixels.length][pixels[0].length];
        ColorPixel targetColor = pixels[startPos.x][startPos.y];
        
        for (int x = 0; x < pixels.length; x++) {
            for (int y =  0; y < pixels[x].length; y++) {
                
                if(pixels[x][y].difference(targetColor) <= range) {
                    
                    selectedPixels[x][y] = selectedAreaColor;
                    
                    for (int i = 1; i < expandAmount; i++) {
                        if(x + i < pixels.length)    selectedPixels[x + i][y] = selectedAreaColor;
                        if(x - i >= 0)               selectedPixels[x - i][y] = selectedAreaColor;
                        if(y + i < pixels[x].length) selectedPixels[x][y + i] = selectedAreaColor;
                        if(y - i >= 0)               selectedPixels[x][y - i] = selectedAreaColor;
                    }
                    
                }
            }
        }
        return selectedPixels;
    }
}
