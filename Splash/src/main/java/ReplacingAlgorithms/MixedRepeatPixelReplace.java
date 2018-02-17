package ReplacingAlgorithms;

import java.util.ArrayList;
import splash.ColorPixel;

public class MixedRepeatPixelReplace implements ReplacingAlgorithm {

    @Override
    public ColorPixel[][] replacePixels(ColorPixel[][] pixels, ColorPixel[][] selectedPixels, ColorPixel selectedAreaColor) {

        int w_max = Integer.MAX_VALUE;
        int n_max = Integer.MIN_VALUE;
        int e_max = Integer.MIN_VALUE;
        int s_max = Integer.MAX_VALUE;

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
        
        int areaSize_x = e_max - w_max + 1;
        int areaSize_y = n_max - s_max + 1;
        
        ColorPixel[][] w_fill = new ColorPixel[areaSize_x][areaSize_y];
        ColorPixel[][] n_fill = new ColorPixel[areaSize_x][areaSize_y];
        ColorPixel[][] e_fill = new ColorPixel[areaSize_x][areaSize_y];
        ColorPixel[][] s_fill = new ColorPixel[areaSize_x][areaSize_y];
        
        ArrayList<ColorPixel[][]> filledArrays = new ArrayList<>();
        
        if(w_max + -areaSize_x >= 0) {
            for (int x = 0; x < areaSize_x; x++) {
                for (int y = 0; y < areaSize_y; y++) {
                    w_fill[x][y] = pixels[w_max - x][s_max + y];
                }
            }
            filledArrays.add(w_fill);
        }
        
        if(n_max + areaSize_y < pixels[0].length) {
            for (int x = 0; x < areaSize_x; x++) {
                for (int y = 0; y < areaSize_y; y++) {
                    n_fill[x][y] = pixels[w_max + x][n_max + areaSize_y - y];
                }
            }
            filledArrays.add(n_fill);
        }
        
        if(e_max + areaSize_x < pixels.length) {
            for (int x = 0; x < areaSize_x; x++) {
                for (int y = 0; y < areaSize_y; y++) {
                    e_fill[x][y] = pixels[e_max + areaSize_x - x][s_max + y];
                }
            }
            filledArrays.add(e_fill);
        }
        
        if(s_max - areaSize_y >= 0) {
            for (int x = 0; x < areaSize_x; x++) {
                for (int y = 0; y < areaSize_y; y++) {
                    s_fill[x][y] = pixels[w_max + x][s_max - y];
                }
            }
            filledArrays.add(e_fill);
        }
        
        if(!filledArrays.isEmpty()) {
            for (int x = 0; x < selectedPixels.length; x++) {
                for (int y = 0; y < selectedPixels[x].length; y++) {
                    if(x < w_max || x >= e_max || y < s_max || y >= n_max) continue;

                    int normalized_x = x - w_max;
                    int normalized_y = y - s_max;
                    int median_a = 0;
                    int median_r = 0;
                    int median_g = 0;
                    int median_b = 0;

                    for (ColorPixel[][] arr : filledArrays) {
                        if(arr[normalized_x][normalized_y] == null) continue;
                        int[] values = arr[normalized_x][normalized_y].getValues();
                        median_a += values[1];
                        median_r += values[2];
                        median_g += values[3];
                        median_b += values[4];
                    }
                    
                    selectedPixels[x][y] = new ColorPixel(
                            median_a / filledArrays.size(),
                            median_r / filledArrays.size(),
                            median_g / filledArrays.size(),
                            median_b / filledArrays.size()
                    );
                }
            }
        }
        return selectedPixels;
    }

}
