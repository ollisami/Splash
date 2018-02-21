package ReplacingAlgorithms;

import Helpers.ColorPixel;
import Helpers.PixelPoint;

public class DummyPixelReplace implements ReplacingAlgorithm {
    /**
    Loops through all the pixels and replaces the color by copying the color thats on the west or east side of the selected area 
    (depending on the difference between the colors).
    Time: 0(n²), Space: O(n²).
    @param pixels Current pixels matrix
    @param selectedPixels the currently selected area
    @param selectedAreaColor Color of the selected pixels
    @return ColorPixel[][] selectedPixels with replaced colors
    */
    @Override
    public ColorPixel[][] replacePixels(ColorPixel[][] pixels, ColorPixel[][] selectedPixels, ColorPixel selectedAreaColor) {
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {

                if (selectedPixels[x][y] != selectedAreaColor) {
                    continue;
                }

                PixelPoint w = new PixelPoint(x, y);
                while (w.x > 0 && selectedPixels[w.x][w.y] != null) {
                    w.x--;
                }

                PixelPoint e = new PixelPoint(x, y);
                while (e.x < pixels.length - 1 && selectedPixels[e.x][e.y] != null) {
                    e.x++;
                }

                ColorPixel replacement = pixels[w.x][w.y];

                replacement = replacement.difference(pixels[x][y]) < pixels[e.x][e.y].difference(pixels[x][y]) ? replacement : pixels[e.x][e.y];

                selectedPixels[x][y] = replacement;
            }
        }
        return selectedPixels;
    }

}
