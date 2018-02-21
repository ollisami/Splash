package ReplacingAlgorithms;

import Helpers.ColorPixel;

public interface ReplacingAlgorithm {
    public ColorPixel[][] replacePixels(ColorPixel[][] pixels, ColorPixel[][] selectedPixels, ColorPixel selectedAreaColor);
}
