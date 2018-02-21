package ReplacingAlgorithms;

import Helpers.ColorPixel;

public class MixedRepeatPixelReplace implements ReplacingAlgorithm {
    /**
    Loops through all the pixels and replaces the color by 4-way copying the texture from nort, south, west and east of the selected area.
    At the end we calculate a median color for each pixel and use it.
    Time: 0(n²), Space: O(n²).
    @param pixels Current pixels matrix
    @param selectedPixels the currently selected area
    @param selectedAreaColor Color of the selected pixels
    @return ColorPixel[][] selectedPixels with replaced colors
    */
    @Override
    public ColorPixel[][] replacePixels(ColorPixel[][] pixels, ColorPixel[][] selectedPixels, ColorPixel selectedAreaColor) {

        int w_max = Integer.MAX_VALUE;
        int n_max = Integer.MIN_VALUE;
        int e_max = Integer.MIN_VALUE;
        int s_max = Integer.MAX_VALUE;
        
        // Search for the edges of our selected area
        for (int x = 0; x < selectedPixels.length; x++) {
            for (int y = 0; y < selectedPixels[x].length; y++) {
                if (selectedPixels[x][y] != selectedAreaColor) {
                    continue;
                }
                if (y < s_max) s_max = y;
                if (x < w_max) w_max = x;
                if (y > n_max) n_max = y;
                if (x > e_max) e_max = x;
            }
        }
        
        // make the selected area a square based on edges
        int areaSize_x = e_max - w_max + 1;
        int areaSize_y = n_max - s_max + 1;
        
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
                }
            }
        } else {
            System.out.println("ERR! The selected area is too big for this algorithm!");
        }
        return selectedPixels;
    }

}
