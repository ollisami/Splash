package splash;

import java.awt.image.BufferedImage;

public class ImageEditor {

    private ColorPixel[][] pixels;

    public ImageEditor(ColorPixel[][] pixels) {
        this.pixels = pixels;
    }

    //Testaa tää
    public BufferedImage GetImage() {
        BufferedImage bufferedImage = new BufferedImage(
                pixels.length,
                pixels[0].length,
                BufferedImage.TYPE_INT_RGB
        );

        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {
                bufferedImage.setRGB(x, y, pixels[x][y].getRGB());
            }
        }

        return bufferedImage;
    }
}
