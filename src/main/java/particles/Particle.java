package particles;

import com.raylib.Raylib;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Vector3;
import matrix.CellularAutomaton;
import matrix.CellularMatrix;
import utils.VectorUtils;
import elements.Element;
import elements.ElementType;
import elements.EmptyCell;
import elements.gas.Gas;
import elements.liquid.Liquid;
import elements.solid.Solid;

public class Particle extends Element {

    public ElementType containedElementType;

    public Particle(int x, int y, Vector3 vel, ElementType elementType, Integer color, boolean isIgnited) {
        super(x, y);
        if (ElementType.PARTICLE.equals(elementType)) {
            throw new IllegalStateException("Containing element cannot be particle");
        }
        this.containedElementType = elementType;
        this.vel = new Vector3();
        Vector3 localVel = vel == null ? new Vector3().x(0).y(-124).z(0) : vel;
        this.vel.x(localVel.x());
        this.vel.y(localVel.y());
        this.color = color;
        this.isIgnited = isIgnited;
        if (isIgnited) {
            this.flammabilityResistance = 0;
        }
    }

    public Particle(int x, int y, Vector3 vel, Element sourceElement) {
        super(x, y);
        if (ElementType.PARTICLE.equals(sourceElement.elementType)) {
            throw new IllegalStateException("Containing element cannot be particle");
        }
        this.containedElementType = sourceElement.elementType;
        this.vel = new Vector3();
        Vector3 localVel = vel == null ? new Vector3().x(0).y(-124).z(0) : vel;
        this.vel.x(localVel.x());
        this.vel.y(localVel.y());
        this.color = sourceElement.color;
        this.isIgnited = sourceElement.isIgnited;
        if (isIgnited) {
            this.flammabilityResistance = 0;
        }
    }

    @Override
    public boolean receiveHeat(CellularMatrix matrix, int heat) {
        return false;
    }

    @Override
    public void dieAndReplace(CellularMatrix matrix, ElementType elementType) {
        particleDeathAndSpawn(matrix);
    }

    private void particleDeathAndSpawn(CellularMatrix matrix) {
        Element currentLocation = matrix.get(this.getMatrixX(), this.getMatrixY());
        if (currentLocation == this || currentLocation instanceof EmptyCell) {
            die(matrix);
            Element newElement = containedElementType.createElementByMatrix(getMatrixX(), getMatrixY());
            newElement.color = this.color;
            newElement.isIgnited = this.isIgnited;
            if (newElement.isIgnited) {
                newElement.flammabilityResistance = 0;
            }
            matrix.setElementAtIndex(getMatrixX(), getMatrixY(), newElement);
            matrix.reportToChunkActive(getMatrixX(), getMatrixY());
        } else {
            int yIndex = 0;
            while (true) {
                Element elementAtNewPos = matrix.get(getMatrixX(), getMatrixY() + yIndex);
                if (elementAtNewPos == null) {
                    break;
                } else if (elementAtNewPos instanceof EmptyCell) {
                    die(matrix);
                    matrix.setElementAtIndex(getMatrixX(), getMatrixY() + yIndex, containedElementType.createElementByMatrix(getMatrixX(), getMatrixY() + yIndex));
                    matrix.reportToChunkActive(getMatrixX(), getMatrixY() + yIndex);
                    break;
                }
                yIndex++;
            }
        }
    }

    @Override
    public void step(CellularMatrix matrix) {
        if (vel.y() > -64 && vel.y() < 32) {
            vel.y(-64);
        }
        vel = VectorUtils.add(vel, CellularAutomaton.gravity);
        if (vel.y() < -500) {
            vel.y(-500);
        } else if (vel.y() > 500) {
            vel.y(500);
        }

        int yModifier = vel.y() < 0 ? -1 : 1;
        int xModifier = vel.x() < 0 ? -1 : 1;
        int velYDeltaTime = (int) (Math.abs(vel.y()) * Raylib.GetFrameTime());
        int velXDeltaTime = (int) (Math.abs(vel.x()) * Raylib.GetFrameTime());

        boolean xDiffIsLarger = Math.abs(velXDeltaTime) > Math.abs(velYDeltaTime);

        int upperBound = Math.max(Math.abs(velXDeltaTime), Math.abs(velYDeltaTime));
        int min = Math.min(Math.abs(velXDeltaTime), Math.abs(velYDeltaTime));
        float slope = (min == 0 || upperBound == 0) ? 0 : ((float) (min + 1) / (upperBound + 1));

        int smallerCount;
        Vector3 lastValidLocation = new Vector3().x(getMatrixX()).y(getMatrixY()).z(0);
        for (int i = 1; i <= upperBound; i++) {
            smallerCount = (int) Math.floor(i * slope);

            int yIncrease, xIncrease;
            if (xDiffIsLarger) {
                xIncrease = i;
                yIncrease = smallerCount;
            } else {
                yIncrease = i;
                xIncrease = smallerCount;
            }

            int modifiedMatrixY = getMatrixY() + (yIncrease * yModifier);
            int modifiedMatrixX = getMatrixX() + (xIncrease * xModifier);
            if (matrix.isWithinBounds(modifiedMatrixX, modifiedMatrixY)) {
                Element neighbor = matrix.get(modifiedMatrixX, modifiedMatrixY);
                if (neighbor == this) continue;
                boolean stopped = actOnNeighboringElement(neighbor, modifiedMatrixX, modifiedMatrixY, matrix, i == upperBound, i == 1, lastValidLocation, 0);
                if (stopped) {
                    break;
                }
                lastValidLocation.x(modifiedMatrixX);
                lastValidLocation.y(modifiedMatrixY);

            } else {
                matrix.setElementAtIndex(getMatrixX(), getMatrixY(), ElementType.EMPTYCELL.createElementByMatrix(getMatrixX(), getMatrixY()));
                return;
            }
        }
        modifyColor();
    }

    @Override
    protected boolean actOnNeighboringElement(Element neighbor, int modifiedMatrixX, int modifiedMatrixY, CellularMatrix matrix, boolean isFinal, boolean isFirst, Vector3 lastValidLocation, int depth) {
        if (neighbor instanceof EmptyCell || neighbor instanceof Particle) {
            if (isFinal) {
                swapPositions(matrix, neighbor, modifiedMatrixX, modifiedMatrixY);
            } else {
                return false;
            }
        } else if (neighbor instanceof Liquid || neighbor instanceof Solid) {
            moveToLastValid(matrix, lastValidLocation);
            dieAndReplace(matrix, containedElementType);
            return true;
        } else if (neighbor instanceof Gas) {
            if (isFinal) {
                moveToLastValidAndSwap(matrix, neighbor, modifiedMatrixX, modifiedMatrixY, lastValidLocation);
                return true;
            }
            return false;
        }
        return false;
    }
}