import com.raylib.Raylib;

import matrix.CellularAutomaton;

import java.util.Random;
import com.raylib.Colors;

import static com.raylib.Raylib.*;

public class FallingSand {
    // Window dimensions
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 800;
    
    // Grid settings
    private static final int GRID_WIDTH = 400;
    private static final int GRID_HEIGHT = 300;
    
    private static Random random = new Random();
    
    public static void main(String[] args) {
        // Initialize the window
        System.out.println("=== MAIN METHOD STARTED ===");
        System.out.flush();
        System.out.println("Window should be opening...");
        InitWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Random Pixels - Raylib Java");
        System.out.println("Window initialized");
        SetTargetFPS(60);
        
        // Create an image to draw pixels on
        Raylib.Image image = GenImageColor(GRID_WIDTH, GRID_HEIGHT, Colors.BLACK);
        Raylib.Texture texture = LoadTextureFromImage(image);

        CellularAutomaton automaton = new CellularAutomaton();
        automaton.create();
        
        // Main game loop
        while (!WindowShouldClose()) {
            automaton.step();
        }
        
        // Cleanup
        UnloadTexture(texture);
        UnloadImage(image);
        CloseWindow();
    }
    
    private static void updatePixels(Raylib.Image image) {
        // Generate random pixels each frame
        for (int i = 0; i < 1000; i++) {
            int x = random.nextInt(GRID_WIDTH);
            int y = random.nextInt(GRID_HEIGHT);
            
            // Create color using GetColor function
            Raylib.Color color = GetColor(0xFF000000 | 
                (random.nextInt(205) + 50) << 16 |  // Red
                (random.nextInt(205) + 50) << 8 |   // Green
                (random.nextInt(205) + 50));        // Blue

            
            // Set pixel in image
            ImageDrawPixel(image, x, y, color);
        }
    }
}