package SelectingAlgorithms;

import java.awt.Point;
import splash.ColorPixel;


public interface SelectionAlgorithm {
    public ColorPixel[][] SelectPixels(
            ColorPixel[][] pixels,
            ColorPixel selectedAreaColor,
            Point startPos,
            int range,
            int expandAmount
    );
}
