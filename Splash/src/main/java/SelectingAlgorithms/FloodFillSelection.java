package SelectingAlgorithms;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import splash.ColorPixel;

public class FloodFillSelection implements SelectionAlgorithm {
   /**
    Selects the pixels using FloodFill algorithm (https://en.wikipedia.org/wiki/Flood_fill)
    Time: 0(n), Space: O(n).
    @param pixels Current pixels matrix
    @param selectedAreaColor Color of the selected pixels
    @param startPos coordinates for the start pixel
    @param range the accuracy of selection
    @param expandAmount the amount how much selection should be expanded
    @return ColorPixel[][] selectedPixels
    */
    @Override
    public ColorPixel[][] SelectPixels(ColorPixel[][] pixels, ColorPixel selectedAreaColor, Point startPos, int range, int expandAmount) {
        
        ColorPixel[][] selectedPixels = new ColorPixel[pixels.length][pixels[0].length];
        ColorPixel targetColor        = pixels[startPos.x][startPos.y];
        Queue<Point> queue            = new LinkedList();    
        queue.add(startPos);
        
        while(!queue.isEmpty()) {
            
            Point n = queue.remove();
            if(selectedPixels[n.x][n.y] != null) continue;
            
            Point w = new Point(n.x, n.y);           
            while (true) {               
                if(w.x >= 0 && pixels[w.x][w.y].difference(targetColor) <= range) {
                    w.x--;
                } else {
                    w.x = Math.max(0, w.x - expandAmount);
                    break;
                }
            }
            
            Point e = new Point(n.x, n.y);
            while (true) {
                if(e.x < pixels.length && pixels[e.x][e.y].difference(targetColor) <= range) {
                    e.x++;
                } else {
                    e.x = Math.min(pixels.length - 1, e.x + expandAmount);
                    break;
                }
            }
                        
            n = new Point(w.x, w.y);          
            while (n.x <= e.x) {
                if(selectedPixels[n.x][n.y] == null) {
                                  
                    selectedPixels[n.x][n.y] = selectedAreaColor;
                    Point north = new Point(n.x, n.y + 1);
                    Point south = new Point(n.x, n.y - 1);
                    
                    
                    if(north.y < pixels[0].length &&
                       selectedPixels[north.x][north.y] == null &&
                       pixels[north.x][north.y].difference(targetColor) <= range)
                    {
                        for (int i = 0; i < expandAmount; i++) {
                            if(north.y + i >= pixels[0].length ) break;
                            queue.add(new Point (north.x, north.y + i));
                        }
                    }

                    if(south.y >= 0 &&
                       selectedPixels[south.x][south.y] == null && 
                       pixels[south.x][south.y].difference(targetColor) <= range) 
                    {
                        for (int i = 0; i < expandAmount; i++) {
                            if(south.y - i < 0 ) break;
                            queue.add(new Point (south.x, south.y - i));
                        }
                    }
                }
                n.x++;
            }
        }
        return selectedPixels;
    }
      
}
