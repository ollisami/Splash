package Tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import splash.ColorPixel;

public class ColorPixelTest {
    
    public ColorPixelTest() {
        
    }

    @Test
    public void testCreatingColorPixel() {
       ColorPixel pixel = new ColorPixel(1000);
       
       int[] values = pixel.getValues();
       assertEquals(values[0], 1000);
       assertEquals(values[1], 0);
       assertEquals(values[2], 0);
       assertEquals(values[3], 3);
       assertEquals(values[4], 232);
       
       assertEquals(pixel.getRGB(), 1000);
       
       pixel.clearValues();    
       values = pixel.getValues();
       assertEquals(values[0], Integer.MIN_VALUE);
       assertEquals(values[1], 0);
       assertEquals(values[2], 0);
       assertEquals(values[3], 0);
       assertEquals(values[4], 0);
       
       pixel = new ColorPixel(0, 0 ,3, 232);
       
       values = pixel.getValues();
       assertEquals(values[0], 1000);
       assertEquals(values[1], 0);
       assertEquals(values[2], 0);
       assertEquals(values[3], 3);
       assertEquals(values[4], 232);
       
       assertEquals(pixel.getRGB(), 1000);
    }
    
        @Test
    public void testComparation() {
    
        ColorPixel a = new ColorPixel(1000);
        ColorPixel b = new ColorPixel(1000);
        ColorPixel c = new ColorPixel(10);
        
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
        
        assertEquals(a.difference(b), 0);
        assertEquals(a.difference(c),222);
    
    }
}
