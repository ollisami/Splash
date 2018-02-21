package splash;

import Helpers.ColorPixel;
import Helpers.PixelPoint;
import MultiFunctionAlgorithms.FloodFillWithMixedRepeat;
import ReplacingAlgorithms.DummyPixelReplace;
import ReplacingAlgorithms.MixedRepeatPixelReplace;
import ReplacingAlgorithms.ReplacingAlgorithm;
import ReplacingAlgorithms.VerticalRepeatPixelReplace;
import SelectingAlgorithms.DummyLoopSelection;
import SelectingAlgorithms.FloodFillSelection;
import SelectingAlgorithms.OnePassCCL;
import SelectingAlgorithms.SelectionAlgorithm;
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
                int argb = selectedPixels != null && selectedPixels[x][y] != null ? selectedPixels[x][y].getRGB() : pixels[x][y].getRGB();
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
        int selectionRange = 100000;
        int expandAmount = 10;
        PixelPoint startPos = new PixelPoint(x, y);
        /*
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
        this.selectedPixels = replacingAlgorithm.replacePixels(pixels, selectedPixels, selectedAreaColor);
        */
        
        //Do a loop that finds best values here!
        FloodFillWithMixedRepeat ffwmr = new FloodFillWithMixedRepeat();
        this.selectedPixels = null;
        while (this.selectedPixels == null) {
            this.selectedPixels = ffwmr.SelectAndReplacePixels(pixels, selectedAreaColor, startPos, selectionRange, expandAmount);
            selectionRange -= 2000;
            if(selectionRange < 0) break;
        }
        
        ColorPixel[][]temp = this.selectedPixels;
        while (true) {
            selectionRange += 100;
            temp = ffwmr.SelectAndReplacePixels(pixels, selectedAreaColor, startPos, selectionRange, expandAmount);
            if(temp != null) this.selectedPixels = temp;
            else break;
        }
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
