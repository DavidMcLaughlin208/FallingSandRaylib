package matrix;

import elements.ElementType;
import utils.ColorConstants;

public class PixelBuffer {
    private final int width, height;
    private final int[] pixelBuffer;
    private final boolean[] dirtyPixels;
    private final int EMPTY_COLOR;
    
    public PixelBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixelBuffer = new int[width * height];
        this.dirtyPixels = new boolean[width * height];
        
        // Pre-compute empty color (black)
        this.EMPTY_COLOR = ColorConstants.getColorForElementType(ElementType.EMPTYCELL);
        
        // Initialize buffer to empty/black
        for (int i = 0; i < pixelBuffer.length; i++) {
            pixelBuffer[i] = EMPTY_COLOR;
        }
    }
    
    // Pack RGBA into a single int for faster processing
    private int packColor(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
    
    // Mark a pixel as needing update
    public void markDirty(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            int index = y * width + x;
            dirtyPixels[index] = true;
        }
    }
    
    // Set a pixel color
    public void setPixel(int x, int y, int packedColor) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            int index = y * width + x;
            pixelBuffer[index] = packedColor;
            dirtyPixels[index] = true;
        }
    }
    
    // Get pixel buffer for processing
    public int[] getPixelBuffer() { return pixelBuffer; }
    public boolean[] getDirtyPixels() { return dirtyPixels; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}