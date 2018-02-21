package Helpers;

public class ColorPixel {

    private int argb;
    private int r;
    private int g;
    private int b;
    private int a;

    public ColorPixel(int argb) {
        initValues(argb);
    }
    
    public ColorPixel(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.b = b;
        this.g = g;
        
        this.argb = (a << 24) | (r << 16 ) | (g <<8 ) | b;
    }

    /**
    Initialises the values
    @param argb argb value of the colour
    */
    private void initValues(int argb) {

        if (argb == Integer.MIN_VALUE) {
            clearValues();
            return;
        }

        this.argb = argb;

        this.a = (argb >> 24) & 0xFF;
        this.r = (argb >> 16) & 0xFF;
        this.g = (argb >> 8) & 0xFF;
        this.b = (argb >> 0) & 0xFF;
    }

    /**
    Sets the pixel to black
    */
    public void clearValues() {
        this.argb = Integer.MIN_VALUE;
        this.a = 0;
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    /**
    Return the argb value of the colour
    @return argb value
    */
    public int getRGB() {
        return this.argb;
    }
    
     /**
    Returns all color values in pixel
    @return values
    */   
    public int[] getValues() {
        int[] val = new int[5];
        
        val[0] = this.argb;
        val[1] = this.a;
        val[2] = this.r;
        val[3] = this.g;
        val[4] = this.b;
        
        return val;
    }

    @Override
    public String toString() {
        return "a: " + a
                + ", r: " + r
                + ", g: " + g
                + ", b: " + b;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (!(other instanceof ColorPixel)) return false;
        ColorPixel otherPixel = (ColorPixel) other;

        return this.argb == otherPixel.argb;
    }
    
    /**
    Compares two colours and calculates the difference
    @param other colour to be compared to this colour
    @return the difference
    */
    public double difference(ColorPixel c) {
        return (c.r - this.r)*(c.r - this.r) + (c.g - this.g)*(c.g - this.g) + (c.b - this.b)*(c.b - this.b);
    }
    
    /**
    Return a blended pixel lerped by percent value. 0 returns this color, 1 returns the other pixel.
    @param other colour to be compared to this colour
    @param percent the percent value of blending
    @return the difference
    */
    public ColorPixel blendPixelByPercent (ColorPixel other, double percent) {
        percent = Math.max(0, Math.min(percent, 1));
        int[] otherValues = other.getValues();
        return new ColorPixel(
            (int) (this.a + (otherValues[1] - this.a) * percent),
            (int) (this.r + (otherValues[2] - this.r) * percent),
            (int) (this.g + (otherValues[3] - this.g) * percent),
            (int) (this.b + (otherValues[4] - this.b) * percent)
        );
    }
}
