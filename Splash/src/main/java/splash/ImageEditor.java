package splash;

import ReplacingAlgorithms.MixedRepeatPixelReplace;
import ReplacingAlgorithms.ReplacingAlgorithm;
import ReplacingAlgorithms.VerticalRepeatPixelReplace;
import SelectingAlgorithms.FloodFillSelection;
import SelectingAlgorithms.SelectionAlgorithm;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ImageEditor {

    private ColorPixel[][] pixels;
    private ColorPixel[][] selectedPixels;

    //This is for debugging
    private ColorPixel selectedAreaColor = new ColorPixel(255, 0, 255, 0);

    public ImageEditor(ColorPixel[][] pixels) {
        this.pixels = pixels;
        this.selectedPixels = new ColorPixel[pixels.length][pixels[0].length];
    }

    /**
     * Recreates a image file from the pixel data
     *
     * @return bufferedImage
     */
    public BufferedImage GetImage() {
        BufferedImage bufferedImage = new BufferedImage(
                pixels.length,
                pixels[0].length,
                BufferedImage.TYPE_INT_RGB
        );

        for (int x = pixels.length - 1; x >= 0; x--) {
            for (int y = pixels[x].length - 1; y >= 0; y--) {
                int argb = selectedPixels[x][y] != null ? selectedPixels[x][y].getRGB() : pixels[x][y].getRGB();
                bufferedImage.setRGB(x, y, argb);
            }
        }

        return bufferedImage;
    }

    /**
     * Selects the pixels that are same colour as the selected pixel
     *
     * @param x pixel cordinate x
     * @param y pixel cordinate y
     */
    public void selectPixelsByCordinates(int x, int y) {
        int selectionRange = 3500;
        int expandAmount = 10;
        Point startPos = new Point(x, y);

        // Here we select the method used to make the selection.
        //SelectionAlgorithm selectionAlgorithm = new DummyLoopSelection();
        SelectionAlgorithm selectionAlgorithm = new FloodFillSelection();
        //SelectionAlgorithm selectionAlgorithm = new OnePassCCL();

        selectedPixels = selectionAlgorithm.SelectPixels(
                this.pixels,
                this.selectedAreaColor,
                startPos,
                selectionRange,
                expandAmount
        );
        
        //ReplacingAlgorithm replacingAlgorithm = new DummyPixelReplace();
        //ReplacingAlgorithm replacingAlgorithm = new VerticalRepeatPixelReplace();
        ReplacingAlgorithm replacingAlgorithm = new MixedRepeatPixelReplace();
        replacingAlgorithm.replacePixels(pixels, selectedPixels, selectedAreaColor);
    }

    /**
     * Returns a pixel from given cordinates. Null if invalid values.
     *
     * @param x pixel cordinate x
     * @param y pixel cordinate y
     */
    public ColorPixel getPixelByCordinates(int x, int y) {
        if (x < 0 || y < 0 || x >= pixels.length || y >= pixels[x].length) {
            return null;
        }
        return pixels[x][y];
    }

    public ColorPixel[][] getSelectedPixels() {
        return this.selectedPixels;
    }
}
