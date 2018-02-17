package ReplacingAlgorithms;

import splash.ColorPixel;

public interface ReplacingAlgorithm {
    public ColorPixel[][] replacePixels(ColorPixel[][] pixels, ColorPixel[][] selectedPixels, ColorPixel selectedAreaColor);
}
