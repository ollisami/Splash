package splash;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ImageEditor {

    private ColorPixel[][] pixels;
    private ColorPixel[][] selectedPixels;
    
    //This is for debugging
    private ColorPixel selectedAreaColor = new ColorPixel(255, 0, 255, 0);

    public ImageEditor(ColorPixel[][] pixels) {
        this.pixels = pixels;
        this.selectedPixels = new ColorPixel[pixels.length][pixels[0].length];
    }

    /**
    Recreates a image file from the pixel data
    @return bufferedImage
    */
    public BufferedImage GetImage() {
        BufferedImage bufferedImage = new BufferedImage(
                pixels.length,
                pixels[0].length,
                BufferedImage.TYPE_INT_RGB
        );

        for (int x = pixels.length - 1; x >= 0; x--) {
            for (int y = pixels[x].length - 1; y >= 0; y--) {
                int argb = selectedPixels[x][y] != null ? selectedPixels[x][y].getRGB() : pixels[x][y].getRGB();
                bufferedImage.setRGB(x, y, argb);
            }
        }

        return bufferedImage;
    }

    /**
    Selects the pixels that are same colour as the selected pixel
    @param x pixel cordinate x
    @param y pixel cordinate y
    */
    public void selectPixelsByCordinates(int x, int y) {
        selectedPixels = new ColorPixel[pixels.length][pixels[0].length];
        int selectionRange = 50;
        Point startPos = new Point(x,y);
        
        //dummyLoop(startPos, selectionRange);
        floodFill(startPos, selectionRange);
    }
    
    
    /**
    Returns a pixel from given cordinates. Null if invalid values.
    @param x pixel cordinate x
    @param y pixel cordinate y
    */
    public ColorPixel getPixelByCordinates(int x, int y) {
        if (x < 0 || y < 0 || x >= pixels.length || y >= pixels[x].length) {
            return null;
        }
        return pixels[x][y];
    }
    
    public ColorPixel[][] getSelectedPixels() {
        return this.selectedPixels;
    }
    
    
//------------------- Different selecting algoritms -----------------------------------
    
    
    /**
    Loops through all the pixel in the image and selects the ones with matching colour.
    @param startPos cordinates for the start pixel
    @param range the accuracy of selection
    */
    public void dummyLoop(Point startPos ,int range) {
        ColorPixel targetColor = getPixelByCordinates(startPos.x, startPos.y);
        for (int x = 0; x < pixels.length; x++) {
            for (int y =  0; y < pixels[x].length; y++) {
                if(pixels[x][y].difference(targetColor) <= range) {
                    selectedPixels[x][y] = this.selectedAreaColor;
                }
            }
        }
    }
    
    
    /**
    Selects the pixels using FloodFill algorithm (https://en.wikipedia.org/wiki/Flood_fill)
    Aika: 0(n), tila: O(n)
    @param startPos cordinates for the start pixel
    @param range the accuracy of selectiony
    */
    public void floodFill(Point startPos, int range) {
        
        ColorPixel targetColor = getPixelByCordinates(startPos.x, startPos.y);
        Queue<Point> queue = new LinkedList();    
        queue.add(startPos);
        
        while(!queue.isEmpty()) {
            
            Point n = queue.remove();
            
            Point w = new Point(n.x, n.y);           
            while (true) {
                if(w.x >= 0 && getPixelByCordinates(w.x , w.y).difference(targetColor) <= range) {
                    w.x--;
                } else {
                    w.x++;
                    break;
                }
            }
            
            Point e = new Point(n.x, n.y);
            while (true) {
                if(e.x < pixels.length && getPixelByCordinates(e.x , e.y).difference(targetColor) <= range) {
                    e.x++;
                } else {
                    e.x--;
                    break;
                }
            }
            
            n = new Point(w.x, w.y);
            while (n.x <= e.x) {
                if(selectedPixels[n.x][n.y] == null) {
                    
                    selectedPixels[n.x][n.y] = this.selectedAreaColor;
                    Point north = new Point(n.x, n.y + 1);
                    Point south = new Point(n.x, n.y - 1);
                    
                    
                    if(north.y < pixels[0].length &&
                       selectedPixels[north.x][north.y] == null &&
                       pixels[north.x][north.y].difference(targetColor) <= range)
                    {
                        queue.add(north);
                    }

                    if(south.y >= 0 &&
                       selectedPixels[south.x][south.y] == null && 
                       pixels[south.x][south.y].difference(targetColor) <= range) 
                    {
                        queue.add(south);
                    }
                }
                n.x++;
            }
        }
    }
}
