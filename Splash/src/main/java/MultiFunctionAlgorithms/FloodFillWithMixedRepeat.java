package MultiFunctionAlgorithms;

import Helpers.ColorPixel;
import Helpers.PPLinkedList;
import Helpers.PixelPoint;

public class FloodFillWithMixedRepeat {
    
        public ColorPixel[][] SelectAndReplacePixels(ColorPixel[][] pixels, ColorPixel selectedAreaColor, PixelPoint startPos, int range, int expandAmount) {
        
        int w_max = Integer.MAX_VALUE;
        int n_max = Integer.MIN_VALUE;
        int e_max = Integer.MIN_VALUE;
        int s_max = Integer.MAX_VALUE;
        ColorPixel[][] selectedPixels = new ColorPixel[pixels.length][pixels[0].length];
        ColorPixel targetColor        = pixels[startPos.x][startPos.y];
        PPLinkedList queue            = new PPLinkedList();    
        queue.add(startPos);
        
        while(!queue.isEmpty()) {
            if ((w_max == 0 && e_max == pixels.length - 1) || s_max == 0 && n_max == pixels[0].length - 1) {
                return null;
            }
            PixelPoint temp = queue.remove();
            if(selectedPixels[temp.x][temp.y] != null) continue;
            
            PixelPoint w = new PixelPoint(temp.x, temp.y);           
            while (true) {               
                if(w.x >= 0 && pixels[w.x][w.y].difference(targetColor) <= range) {
                    w.x--;
                } else {
                    w.x = Math.max(0, w.x - expandAmount);
                    break;
                }
            }
            w_max = Math.min(w_max, w.x);
            
            PixelPoint e = new PixelPoint(temp.x, temp.y);
            while (true) {
                if(e.x < pixels.length && pixels[e.x][e.y].difference(targetColor) <= range) {
                    e.x++;
                } else {
                    e.x = Math.min(pixels.length - 1, e.x + expandAmount);
                    break;
                }
            }
            e_max = Math.max(e_max, e.x);
                        
            temp = new PixelPoint(w.x, w.y);          
            while (temp.x <= e.x) {
                if(selectedPixels[temp.x][temp.y] == null) {
                                  
                    selectedPixels[temp.x][temp.y] = selectedAreaColor;
                    PixelPoint north = new PixelPoint(temp.x, temp.y + 1);
                    PixelPoint south = new PixelPoint(temp.x, temp.y - 1);
                    
                    if(north.y < pixels[0].length &&
                       selectedPixels[north.x][north.y] == null &&
                       pixels[north.x][north.y].difference(targetColor) <= range)
                    {
                        for (int i = 0; i < expandAmount; i++) {
                            if(north.y + i >= pixels[0].length ) break;
                            queue.add(new PixelPoint (north.x, north.y + i));
                            n_max = Math.max(n_max, north.y + i);
                        }
                    }

                    if(south.y >= 0 &&
                       selectedPixels[south.x][south.y] == null && 
                       pixels[south.x][south.y].difference(targetColor) <= range) 
                    {
                        for (int i = 0; i < expandAmount; i++) {
                            if(south.y - i < 0 ) break;
                            queue.add(new PixelPoint (south.x, south.y - i));
                            s_max = Math.min(s_max, south.y - i);
                        }
                    }
                }
                temp.x++;
            }
        }
        return replacePixels(pixels, selectedPixels, selectedAreaColor, w_max, n_max, e_max, s_max, startPos, range);
    }
    
    
    /**
    Loops through all the pixels and replaces the color by 4-way copying the texture from nort, south, west and east of the selected area.
    At the end we calculate a median color for each pixel and use it.
    Time: 0(n²), Space: O(n²).
    @param pixels Current pixels matrix
    @param selectedPixels the currently selected area
    @param selectedAreaColor Color of the selected pixels
    @return ColorPixel[][] selectedPixels with replaced colors
    */
    public ColorPixel[][] replacePixels(
            ColorPixel[][] pixels,
            ColorPixel[][] selectedPixels,
            ColorPixel selectedAreaColor,
            int w_max,
            int n_max,
            int e_max,
            int s_max,
            PixelPoint startPos,
            int range
    ) {
        // make the selected area a square based on edges
        int areaSize_x = e_max - w_max + 1;
        int areaSize_y = n_max - s_max + 1;
        
        if(areaSize_x > 500 || areaSize_x <= 0 || areaSize_y > 500 || areaSize_y <= 0 ) {
            System.out.println("ERR! The selected area is too big or small for this algorithm!");
            return selectedPixels;
        }
        
        // Create new arrays for all four direction
        ColorPixel[][][] filledArrays = new ColorPixel[4][areaSize_x][areaSize_y];
        ColorPixel[][] w_fill = filledArrays[0];
        ColorPixel[][] n_fill = filledArrays[1];
        ColorPixel[][] e_fill = filledArrays[2];
        ColorPixel[][] s_fill = filledArrays[3];
        
        int filledArraysCount = 0;
        
        if(w_max + -areaSize_x >= 0) {
            for (int x = 0; x < areaSize_x; x++) {
                for (int y = 0; y < areaSize_y; y++) {
                    w_fill[x][y] = pixels[w_max - x][s_max + y];
                }
            }
            filledArraysCount++;
        }
        
        if(n_max + areaSize_y < pixels[0].length) {
            for (int x = 0; x < areaSize_x; x++) {
                for (int y = 0; y < areaSize_y; y++) {
                    n_fill[x][y] = pixels[w_max + x][n_max + areaSize_y - y];
                }
            }
            filledArraysCount++;
        }
        
        if(e_max + areaSize_x < pixels.length) {
            for (int x = 0; x < areaSize_x; x++) {
                for (int y = 0; y < areaSize_y; y++) {
                    e_fill[x][y] = pixels[e_max + areaSize_x - x][s_max + y];
                }
            }
            filledArraysCount++;
        }
        
        if(s_max - areaSize_y >= 0) {
            for (int x = 0; x < areaSize_x; x++) {
                for (int y = 0; y < areaSize_y; y++) {
                    s_fill[x][y] = pixels[w_max + x][s_max - y];
                }
            }
            filledArraysCount++;
        }
        if(filledArraysCount > 0) {
            for (int x = 0; x < selectedPixels.length; x++) {
                for (int y = 0; y < selectedPixels[x].length; y++) {
                    if(x < w_max || x >= e_max || y < s_max || y >= n_max) continue;

                    int normalized_x = x - w_max;
                    int normalized_y = y - s_max;
                    int median_a = 0;
                    int median_r = 0;
                    int median_g = 0;
                    int median_b = 0;

                    for (int i = 0; i < 4; i++) {
                        ColorPixel[][] arr = filledArrays[i];
                        if(arr[normalized_x][normalized_y] == null) continue;
                        int[] values = arr[normalized_x][normalized_y].getValues();
                        median_a += values[1];
                        median_r += values[2];
                        median_g += values[3];
                        median_b += values[4];
                    }
                    
                    selectedPixels[x][y] = new ColorPixel(
                            median_a / filledArraysCount,
                            median_r / filledArraysCount,
                            median_g / filledArraysCount,
                            median_b / filledArraysCount
                    );
                    
                    if(pixels[x][y].difference(selectedPixels[x][y]) < 200) {
                        int centerX = (e_max - w_max) / 2 + w_max;
                        int centerY = (n_max - s_max) / 2 + s_max;
                        
                        double distancePercentFromCenterX = x > centerX ? 
                                (double)(x - centerX) / (double)(e_max - centerX) : 
                                (double)(centerX - x) / (double)(centerX - w_max);
                        
                        double distancePercentFromCenterY = y > centerY ?                           
                                (double)(y - centerY) / (double)(n_max - centerY) :
                                (double)(centerY - y) / (double)(centerY - s_max);
                        
                        double distancePercentFromCenter = Math.max(distancePercentFromCenterX, distancePercentFromCenterY);
                        selectedPixels[x][y] = selectedPixels[x][y].blendPixelByPercent(pixels[x][y], distancePercentFromCenter);
                    }
                }
            }
        } else {
            System.out.println("ERR! The selected area is too big for this algorithm!");
        }
        return selectedPixels;
    }

}