package SelectingAlgorithms;

import Helpers.ColorPixel;
import Helpers.PixelPoint;


public interface SelectionAlgorithm {
    public ColorPixel[][] SelectPixels(
            ColorPixel[][] pixels,
            ColorPixel selectedAreaColor,
            PixelPoint startPos,
            int range,
            int expandAmount
    );
}
