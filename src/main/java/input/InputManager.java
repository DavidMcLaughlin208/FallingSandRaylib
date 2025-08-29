package input;

import static com.raylib.Raylib.GetMousePosition;
import static com.raylib.Raylib.IsKeyPressed;
import static com.raylib.Raylib.IsMouseButtonDown;
import static com.raylib.Raylib.KEY_ONE;
import static com.raylib.Raylib.KEY_TWO;
import static com.raylib.Raylib.MOUSE_BUTTON_LEFT;

import com.raylib.Raylib;
import com.raylib.Raylib.Vector3;

import elements.ElementType;
import matrix.CellularAutomaton;
import matrix.CellularMatrix;

public class InputManager {
    
    private Boolean touchedLastFrame = false;
    private ElementType currentElement = ElementType.STONE;
    private Vector3 lastTouchedLocation = null;
    private int brushSize = 10;
    private BRUSHTYPE selectedBrushType = BRUSHTYPE.CIRCLE;

    public enum BRUSHTYPE {
        CIRCLE,
        SQUARE,
        RECTANGLE;
    }



    public void processInputs(matrix.CellularMatrix matrix) {
        processMouseInputs(matrix);
        processKeyboardInput();

    }

    private void processMouseInputs(CellularMatrix matrix) {
        Raylib.Vector2 mousePos = GetMousePosition();
        Raylib.Vector3 mousePosV3 = new Raylib.Vector3().x(mousePos.x()).y(mousePos.y()).z(0);
        int gridX = (int)(mousePos.x() / CellularAutomaton.pixelSizeModifier);
        int gridY = (int)(mousePos.y() / CellularAutomaton.pixelSizeModifier);

        if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            if (touchedLastFrame) {
                matrix.spawnElementBetweenTwoPoints(lastTouchedLocation, mousePosV3, currentElement, brushSize, selectedBrushType);
            } else {
                matrix.spawnElementByPixelWithBrush((int) mousePos.x(), (int) mousePos.y(), currentElement, brushSize, selectedBrushType);
            }
            lastTouchedLocation = mousePosV3;
            touchedLastFrame = true;
        } else {
            touchedLastFrame = false;
        }
    }

    public void processKeyboardInput() {
        if (IsKeyPressed(KEY_ONE)) {
            currentElement = ElementType.STONE;
        } else if (IsKeyPressed(KEY_TWO)) {
            currentElement = ElementType.SAND;
        }
    }
}
