package ReplacingAlgorithms;

import java.awt.Point;
import splash.ColorPixel;

public class DummyPixelReplace implements ReplacingAlgorithm {

    @Override
    public ColorPixel[][] replacePixels(ColorPixel[][] pixels, ColorPixel[][] selectedPixels, ColorPixel selectedAreaColor) {
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {

                if (selectedPixels[x][y] != selectedAreaColor) {
                    continue;
                }

                Point w = new Point(x, y);
                while (w.x > 0 && selectedPixels[w.x][w.y] != null) {
                    w.x--;
                }

                Point e = new Point(x, y);
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
