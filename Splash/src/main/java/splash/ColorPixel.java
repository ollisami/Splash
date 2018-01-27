package splash;

public class ColorPixel {

    private int argb;
    private int r;
    private int g;
    private int b;
    private int a;

    public ColorPixel(int argb) {
        
        this.argb = argb;
        
        this.a = (argb >> 24) & 0xFF;
        this.r = (argb >> 16) & 0xFF;
        this.g = (argb >> 8)  & 0xFF;
        this.b = (argb >> 0)  & 0xFF;
    }

    public int getRGB() {
        return this.argb;
    }

    @Override
    public String toString() {
        return "a: " + a
                + ", r: " + r
                + ", g: " + g
                + ", b: " + b;
    }
}
