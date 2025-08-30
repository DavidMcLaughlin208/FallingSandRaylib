package matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


import com.raylib.Raylib.Color;

import elements.ElementType;
import utils.ColorConstants;

public class PixelBuffer {
    private final int width, height;
    private final ByteBuffer pixelBuffer;
    private final boolean[] dirtyPixels;
    private final Color EMPTY_COLOR;
    
    public PixelBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixelBuffer = ByteBuffer.allocateDirect(width * height * 4);
        this.pixelBuffer.order(ByteOrder.nativeOrder());

        this.dirtyPixels = new boolean[width * height];
        
        // Pre-compute empty color (black)
        this.EMPTY_COLOR = ColorConstants.getColorForElementType(ElementType.EMPTYCELL);
        
        // Initialize buffer to empty/black
        for (int i = 0; i < width * height; i++) {
            pixelBuffer.put(i * 4, (byte) EMPTY_COLOR.r());
            pixelBuffer.put(i * 4 + 1, (byte) EMPTY_COLOR.g());
            pixelBuffer.put(i * 4 + 2, (byte) EMPTY_COLOR.b());
            pixelBuffer.put(i * 4 + 3, (byte) EMPTY_COLOR.a());
        }
        pixelBuffer.rewind();

    }

    public ByteBuffer getBuffer() {
        return pixelBuffer;
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
    public void setPixel(int x, int y, Color color) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            int index = y * width + x;
            pixelBuffer.put((index) * 4, (byte) color.r());
            pixelBuffer.put((index) * 4 + 1, (byte) color.g());
            pixelBuffer.put((index) * 4 + 2, (byte) color.b());
            pixelBuffer.put((index) * 4 + 3, (byte) color.a());
            // dirtyPixels[index] = true;
        }
    }
    
    // Get pixel buffer for processing
    public ByteBuffer getPixelBuffer() { return pixelBuffer; }
    public boolean[] getDirtyPixels() { return dirtyPixels; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}