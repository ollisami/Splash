package SelectingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Helpers.ColorPixel;
import Helpers.PixelPoint;

public class OnePassCCL implements SelectionAlgorithm {

    private HashMap<Integer, ArrayList<PixelPoint>> pixelLabelsMap;
    private int[][] labels;

    public OnePassCCL() {
        this.pixelLabelsMap = new HashMap();
    }

    /**
     ** HUOM: Algoritmi käyttää javan valmiita tietorakenteita, sillä sitä ei varsinaisesti
     * käytetä lopullisessa ratkaisussa, vaan se on tehty vertailua varten!
     * 
     * Selects the pixels using One-pass Connected-component labeling algorithm
     * (https://en.wikipedia.org/wiki/Connected-component_labeling) 
     * Time: 0(n), Space: O(n).
     * 
     *
     * @param pixels Current pixels matrix
     * @param selectedAreaColor Color of the selected pixels
     * @param startPos coordinates for the start pixel
     * @param range the accuracy of selection
     * @param expandAmount the amount how much selection should be expanded
     * @return ColorPixel[][] selectedPixels
     */
    @Override
    public ColorPixel[][] SelectPixels(ColorPixel[][] pixels, ColorPixel selectedAreaColor, PixelPoint startPos, int range, int expandAmount) {

        if (this.pixelLabelsMap.isEmpty()) {
            SetImageLabels(pixels, range);
        }
        
        ColorPixel[][] selectedPixels = new ColorPixel[pixels.length][pixels[0].length];
        if (!this.pixelLabelsMap.containsKey(this.labels[startPos.x][startPos.y])) return selectedPixels;
        
        ArrayList<PixelPoint> selectedArea = this.pixelLabelsMap.get(this.labels[startPos.x][startPos.y]);
        for (PixelPoint p : selectedArea) {
            selectedPixels[p.x][p.y] = selectedAreaColor;
        }
        
        return selectedPixels;
    }

    private void SetImageLabels(ColorPixel[][] pixels, int range) {
        
        this.labels = new int[pixels.length][pixels[0].length];
        for (int x = 0; x < labels.length; x++) {
            for (int y = 0; y < labels[x].length; y++) {
                labels[x][y] = Integer.MAX_VALUE;
            }
        }
        int currentLabelCount = 0;
        
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {
                if(labels[x][y] == Integer.MAX_VALUE) {
                    currentLabelCount++;
                    List<PixelPoint> neighbours = getNeigbours(x,y, pixels.length, pixels[x].length, 2);                
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if(i == 0 && j == 0) continue;
                            PixelPoint p = new PixelPoint(x + i, y + j);
                            if(p.x >= 0 && p.x < pixels.length && p.y >= 0 && p.y < pixels[x].length)
                                neighbours.add(p);
                        }
                    }
                    int smallestLabelInNeighbours = currentLabelCount;
                    for (PixelPoint p : neighbours) {
                        if(labels[p.x][p.y] < smallestLabelInNeighbours && pixels[x][y].difference(pixels[p.x][p.y]) < range) {
                            smallestLabelInNeighbours = labels[p.x][p.y];
                        }
                    }
                    labels[x][y] = smallestLabelInNeighbours;
                }
            }
        }
        
        for (int x = 0; x < labels.length; x++) {
            for (int y = 0; y < labels[x].length; y++) {
                int key = labels[x][y];
                if(this.pixelLabelsMap.containsKey(key)) {
                    this.pixelLabelsMap.get(key).add(new PixelPoint(x,y));
                } else {
                    this.pixelLabelsMap.put(key, new ArrayList<>());
                }
            }
        }
    }

    private List<PixelPoint> getNeigbours (int x, int y, int max_x, int max_y, int range) {
        List<PixelPoint> neighbours = new ArrayList<>();                
        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                if(i == 0 && j == 0) continue;
                PixelPoint p = new PixelPoint(x + i, y + j);
                if(p.x >= 0 && p.x < max_x && p.y >= 0 && p.y < max_y)
                    neighbours.add(p);
            }
        }
        return neighbours;
    }
}
