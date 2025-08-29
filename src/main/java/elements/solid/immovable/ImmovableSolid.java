package elements.solid.immovable;

import com.raylib.Raylib.Vector3;

import matrix.CellularMatrix;
import elements.Element;
import elements.solid.Solid;

public abstract class ImmovableSolid extends Solid {

    public ImmovableSolid(int x, int y) {
        super(x, y);
        isFreeFalling = false;
    }

//    @Override
//    public void draw(ShapeRenderer sr) {
//        sr.setColor(color);
//        sr.rect(pixelX, pixelY, CellularAutomaton.pixelSizeModifier, CellularAutomaton.pixelSizeModifier);
//    }

    @Override
    public void step(CellularMatrix matrix) {
        applyHeatToNeighborsIfIgnited(matrix);
        takeEffectsDamage(matrix);
        spawnSparkIfIgnited(matrix);
        modifyColor();
        customElementFunctions(matrix);
    }

    @Override
    protected boolean actOnNeighboringElement(Element neighbor, int modifiedMatrixX, int modifiedMatrixY, CellularMatrix matrix, boolean isFinal, boolean isFirst, Vector3 lastValidLocation, int depth) {
        return true;
    }
}