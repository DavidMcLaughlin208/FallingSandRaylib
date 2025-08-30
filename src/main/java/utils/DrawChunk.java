package utils;

import com.raylib.Raylib;
import com.raylib.Raylib.Image;

import matrix.CellularMatrix;

public class DrawChunk implements Runnable {

    public CellularMatrix matrix;
    public int colIndex;
    public Image image;

    public DrawChunk(CellularMatrix matrix, int colIndex, Image image) {
        this.matrix = matrix;
        this.colIndex = colIndex;
        this.image = image;
    }

    @Override
    public void run() {
        matrix.drawProvidedChunk(colIndex, image);
    }
}