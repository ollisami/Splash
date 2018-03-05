package splash;

import Helpers.ColorPixel;
import Helpers.PixelPoint;
import MultiFunctionAlgorithms.FloodFillWithMixedRepeat;
import ReplacingAlgorithms.*;
import SelectingAlgorithms.*;
import java.awt.image.BufferedImage;

public class ImageEditor {

    private ColorPixel[][] pixels;
    private ColorPixel[][] selectedPixels;
    private ColorPixel selectedAreaColor;

    public ImageEditor(ColorPixel[][] pixels) {
        this.pixels = pixels;
        this.selectedPixels = new ColorPixel[pixels.length][pixels[0].length];
        selectedAreaColor = new ColorPixel(255, 0, 255, 0);
    }

    /**
     * Muuttaa pikselidatan takaisin kuvaksi
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
     * Valitsee ja korvaa ne pikselit, jotka täyttävät hakukriteerit
     *
     * @param x pixel cordinate x
     * @param y pixel cordinate y
     */
    public void selectAndReplacePixelsByCordinates(int x, int y) {
        int selectionRange  = 100000; // kuinka paljon pikseleiden välillä saa olla eroa jotta ne tulevat valituksi
        int expandAmount    = 10;     // kuinka monella pikselillä valittua aluetta laajennetaan
        PixelPoint startPos = new PixelPoint(x, y);
        /*
        // Valitaan käytettävä valitsemis algoritmi.
        
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
        
        // Valitaan käytettävä korvaus algoritmi
        //ReplacingAlgorithm replacingAlgorithm = new DummyPixelReplace();
        //ReplacingAlgorithm replacingAlgorithm = new VerticalRepeatPixelReplace();
        ReplacingAlgorithm replacingAlgorithm = new MixedRepeatPixelReplace();
        this.selectedPixels = replacingAlgorithm.replacePixels(pixels, selectedPixels, selectedAreaColor);
        */
        
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
     * Palauttaa pikselin annetuista koordinaateista 
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
