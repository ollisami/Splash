package splash;

import SelectingAlgorithms.DummyLoopSelection;
import SelectingAlgorithms.FloodFillSelection;
import SelectingAlgorithms.SelectionAlgorithm;
import java.awt.Point;
import java.awt.image.BufferedImage;

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
        int selectionRange = 5500;
        int expandAmount = 20;
        Point startPos = new Point(x,y);
            
        // Here we select the method used to make the selection.
        //SelectionAlgorithm selectionAlgorithm = new DummyLoopSelection();
        SelectionAlgorithm selectionAlgorithm = new FloodFillSelection();
        
        selectedPixels = selectionAlgorithm.SelectPixels(
                this.pixels,
                this.selectedAreaColor,
                startPos,
                selectionRange,
                expandAmount
        );
        
       //dummyReplceSelectedPixels();
       VerticalReplceSelectedPixels();
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
    
//------------------- Different color replacing algoritms -----------------------------------
    public void dummyReplceSelectedPixels() {
        for (int x = 0; x < pixels.length; x++) {
            for (int y =  0; y < pixels[x].length; y++) {
                
                if(this.selectedPixels[x][y] != this.selectedAreaColor) continue;
                
                Point w = new Point(x,y);              
                while(w.x > 0 && this.selectedPixels[w.x][w.y] != null) {
                   w.x--;
                }
                
                Point e = new Point(x,y);              
                while(e.x < pixels.length -1 && this.selectedPixels[e.x][e.y] != null) {
                   e.x++;
                }
                
                ColorPixel replacement = this.pixels[w.x][w.y];
                
                replacement = replacement.difference(this.pixels[x][y]) < this.pixels[e.x][e.y].difference(this.pixels[x][y]) ? replacement : this.pixels[e.x][e.y];
                
                this.selectedPixels[x][y] = replacement;        
            }
        }
    }
    
        public void VerticalReplceSelectedPixels() {
            
            int westEdge = Integer.MIN_VALUE;
            int offset = 0;
            boolean increaseOffset = true;
            
            for (int x = 0; x < pixels.length; x++) {

                int verticalStart = Integer.MIN_VALUE , verticalStop = Integer.MIN_VALUE;

                for (int y =  0; y < pixels[x].length; y++) {
                    if(this.selectedPixels[x][y] == this.selectedAreaColor) {
                        if(verticalStart == Integer.MIN_VALUE) {
                            verticalStart = y;
                        }
                        verticalStop = y;  
                    }
                }

                if(verticalStart == Integer.MIN_VALUE || verticalStop == Integer.MIN_VALUE) continue;              
                if(westEdge == Integer.MIN_VALUE) westEdge = x;

                for (int y = verticalStart; y <= verticalStop; y++) {
                    
                    if(westEdge - offset < 0) break;
                    
                    this.selectedPixels[x][y] = this.pixels[westEdge - offset][y];
                    
                    if(offset >= 10) increaseOffset = false;
                    else if (offset < 0) increaseOffset = true;
                    
                    offset += increaseOffset ? 1 : -1;
                }

            }
    }
       
}
