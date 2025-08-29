package elements.solid.immovable;

import com.raylib.Raylib.Vector3;
import matrix.CellularMatrix;

public class Stone extends ImmovableSolid {

    public Stone(int x, int y) {
        super(x, y);
        vel = new Vector3();
        vel.x(0);
        vel.y(0);
        vel.z(0);
        frictionFactor = 0.5f;
        inertialResistance = 1.1f;
        mass = 500;
        explosionResistance = 4;
    }

    @Override
    public boolean receiveHeat(CellularMatrix matrix, int heat) {
        return false;
    }

}