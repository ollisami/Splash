package ReplacingAlgorithms;

import splash.ColorPixel;

public class VerticalRepeatPixelReplace implements ReplacingAlgorithm {

    @Override
    public ColorPixel[][] replacePixels(ColorPixel[][] pixels, ColorPixel[][] selectedPixels, ColorPixel selectedAreaColor) {
        int westEdge = Integer.MIN_VALUE;
        int offset = 0;
        boolean increaseOffset = true;

        for (int x = 0; x < pixels.length; x++) {

            int verticalStart = Integer.MIN_VALUE, verticalStop = Integer.MIN_VALUE;

            for (int y = 0; y < pixels[x].length; y++) {
                if (selectedPixels[x][y] == selectedAreaColor) {
                    if (verticalStart == Integer.MIN_VALUE) {
                        verticalStart = y;
                    }
                    verticalStop = y;
                }
            }

            if (verticalStart == Integer.MIN_VALUE || verticalStop == Integer.MIN_VALUE) {
                continue;
            }
            if (westEdge == Integer.MIN_VALUE) {
                westEdge = x;
            }

            for (int y = verticalStart; y <= verticalStop; y++) {

                if (westEdge - offset < 0) {
                    break;
                }

                selectedPixels[x][y] = pixels[westEdge - offset][y];

                if (offset >= 10) {
                    increaseOffset = false;
                } else if (offset < 0) {
                    increaseOffset = true;
                }

                offset += increaseOffset ? 1 : -1;
            }
        }
        return selectedPixels;
    }
}
