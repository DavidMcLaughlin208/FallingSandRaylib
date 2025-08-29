package matrix;

import com.raylib.Colors;
import com.raylib.Raylib;
import com.raylib.Raylib.Color;
// import com.badlogic.gdx.ApplicationAdapter;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.*;
// import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.raylib.Raylib.Vector2;
import com.raylib.Raylib.Vector3;
import input.InputManager;
import matrix.CellularMatrix;
import utils.DrawChunk;
import utils.ElementColumnStepper;
// import com.badlogic.gdx.physics.box2d.*;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.utils.Array;
// import com.badlogic.gdx.utils.viewport.FitViewport;
// import com.badlogic.gdx.utils.viewport.Viewport;
// import com.gdx.cellular.box2d.ShapeFactory;
// import com.gdx.cellular.elements.ElementType;
// import com.gdx.cellular.input.InputManager;
// import com.gdx.cellular.input.InputProcessors;
// import com.gdx.cellular.ui.MatrixActor;
// import com.gdx.cellular.util.ElementColumnStepper;
// import com.gdx.cellular.util.GameManager;

import static com.raylib.Raylib.BeginDrawing;
import static com.raylib.Raylib.ClearBackground;
import static com.raylib.Raylib.DrawFPS;
import static com.raylib.Raylib.DrawTextureEx;
import static com.raylib.Raylib.DrawTexturePro;
import static com.raylib.Raylib.EndDrawing;
import static com.raylib.Raylib.GenImageColor;
import static com.raylib.Raylib.ImageDrawPixel;
import static com.raylib.Raylib.ImageFormat;
import static com.raylib.Raylib.LoadImageColors;
import static com.raylib.Raylib.LoadTextureFromImage;
import static com.raylib.Raylib.PIXELFORMAT_UNCOMPRESSED_R8G8B8A8;
import static com.raylib.Raylib.UnloadTexture;
import static com.raylib.Raylib.UpdateTexture;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class CellularAutomaton {
	public static int screenWidth = 1280; // 480;
	public static int screenHeight = 800; //800;
	public static int pixelSizeModifier = 6;
	public static int box2dSizeModifier = 10;
    public static Vector3 gravity = new Vector3().x(0f).y(-5f).z(0f);
    public static BitSet stepped = new BitSet(1);
    
    // Grid settings
    private static final int GRID_WIDTH = screenWidth/pixelSizeModifier;
    private static final int GRID_HEIGHT = screenHeight/pixelSizeModifier;

    public CellularMatrix matrix;

    private int numThreads = 12;
    private boolean useMultiThreading = true;

    private InputManager inputManager;

	public static int frameCount = 0;
	public boolean useChunks = true;
    public Raylib.Image image;
    public Raylib.Texture texture;
	// public World b2dWorld;
	// public Box2DDebugRenderer debugRenderer;
	// public InputProcessors inputProcessors;
	// public GameManager gameManager;


	public void create() {
		inputManager = new InputManager();

		// b2dWorld = new World(new Vector2(0, -100), true);

		matrix = new CellularMatrix(screenWidth, screenHeight, pixelSizeModifier);//, b2dWorld);
		matrix.generateShuffledIndexesForThreads(numThreads);

		// ShapeFactory.initialize(b2dWorld);
		// debugRenderer = new Box2DDebugRenderer();

		// setUpBasicBodies();

		// this.gameManager = new GameManager(this);
		// gameManager.createPlayer(matrix.innerArraySize/2, matrix.outerArraySize/2);
		// inputProcessors = new InputProcessors(inputManager, matrix, camera, gameManager);

        this.image = GenImageColor(GRID_WIDTH, GRID_HEIGHT, Colors.BLACK);
        ImageFormat(image, PIXELFORMAT_UNCOMPRESSED_R8G8B8A8);
        this.texture = LoadTextureFromImage(image);
	}

	public void step() {
        stepped.flip(0);

        incrementFrameCount();

		matrix.resetChunks();

        // Detect and act on input
        // numThreads = inputManager.adjustThreadCount(numThreads);
        // useMultiThreading = inputManager.toggleThreads(useMultiThreading);
        // useChunks = inputManager.toggleChunks(useChunks);
		// inputManager.save(matrix);
		// inputManager.load(matrix);

		matrix.reshuffleXIndexes();
		matrix.reshuffleThreadXIndexes(numThreads);
		matrix.calculateAndSetThreadedXIndexOffset();

		// boolean isPaused = inputManager.getIsPaused();
		// if (isPaused) {
		// 	matrix.useChunks = false;
		// 	useChunks = false;
		// 	matrixStage.draw();
		// 	matrix.drawPhysicsElementActors(shapeRenderer);
		// 	Array<Body> bodies = new Array<>();
		// 	b2dWorld.getBodies(bodies);
		// 	matrix.drawBox2d(shapeRenderer, bodies);
		// 	debugRenderer.render(b2dWorld, camera.combined);
		// 	return;
		// }

		// matrix.spawnFromSpouts();
		matrix.useChunks = useChunks;



        matrix.reshuffleThreadXIndexes(numThreads);
        List<Thread> threads = new ArrayList<>(numThreads);

        for (int t = 0; t < numThreads; t++) {
            Thread newThread = new Thread(new ElementColumnStepper(matrix, t));
            threads.add(newThread);
        }
        if (stepped.get(0)) {
            startAndWaitOnOddThreads(threads);
            startAndWaitOnEvenThreads(threads);
        } else {
            startAndWaitOnEvenThreads(threads);
            startAndWaitOnOddThreads(threads);
        }
        // Create a thread for each chunk in the matrix
        List<Thread> drawingThreads = new ArrayList<>(matrix.chunks.size() * matrix.chunks.get(0).size());
        for (int t = 0; t < numThreads; t++) {
            Thread newThread = new Thread(new DrawChunk(matrix, t, image));
            drawingThreads.add(newThread);
        }

		// matrix.executeExplosions();

		// b2dWorld.step(1/120f, 10, 6);
		// b2dWorld.step(1/120f, 10, 6);
		// matrix.stepPhysicsElementActors();

		// matrix.drawPhysicsElementActors(shapeRenderer);

		// List<Body> bodies = new ArrayList<>();
		// b2dWorld.getBodies(bodies);
		// matrix.drawBox2d(shapeRenderer, bodies);
		// debugRenderer.render(b2dWorld, camera.combined);

		// inputManager.drawMenu();
		// inputManager.drawCursor();

		// inputManager.weatherSystem.enact(this.matrix);
		// gameManager.stepPlayers(this.matrix);
        inputManager.processInputs(matrix);
        // matrix.updatePixels(image);
        startAndWaitOnDrawThreads(drawingThreads);
                    
        // Update texture with new image data
        UpdateTexture(texture, LoadImageColors(image));

        
        // Draw everything
        BeginDrawing();
        ClearBackground(Colors.BLACK);
        
        Raylib.Rectangle source = new Raylib.Rectangle();
        source.x(0); source.y(0); 
        source.width(GRID_WIDTH); source.height(GRID_HEIGHT);
        
        Raylib.Rectangle dest = new Raylib.Rectangle();
        dest.x(0); dest.y(0);
        dest.width(screenWidth); dest.height(screenHeight);
        
        Raylib.Vector2 origin = new Raylib.Vector2();
        origin.x(0); origin.y(0);
        
        DrawTexturePro(texture, source, dest, origin, 0.0f, Colors.WHITE);
        
        DrawFPS(10, 10);
        EndDrawing();

	}

	private void incrementFrameCount() {
		frameCount = frameCount == 3 ? 0 : frameCount + 1;
	}

	// private void setUpBasicBodies() {
	// 	BodyDef groundBodyDef = new BodyDef();

	// 	inputManager.spawnPhysicsRect(matrix, new Vector3((camera.viewportWidth/2/box2dSizeModifier/8) * 10, 150, 0),
	// 			new Vector3((camera.viewportWidth/2/box2dSizeModifier - camera.viewportWidth/2/box2dSizeModifier/8) * 20, 50, 0),
	// 			ElementType.STONE,
	// 			BodyDef.BodyType.StaticBody);
	// }

	private void startAndWaitOnEvenThreads(List<Thread> threads) {
		try {
			for (int t = 0; t < threads.size(); t++) {
				if (t % 2 == 0) {
					threads.get(t).start();
				}
			}
			for (int t = 0; t < threads.size(); t++) {
				if (t % 2 == 0) {
					threads.get(t).join();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void startAndWaitOnOddThreads(List<Thread> threads) {
		try {
			for (int t = 0; t < threads.size(); t++) {
				if (t % 2 != 0) {
					threads.get(t).start();
				}
			}
			for (int t = 0; t < threads.size(); t++) {
				if (t % 2 == 0) {
					threads.get(t).join();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    private void startAndWaitOnDrawThreads(List<Thread> threads) {
        try {
            for (int t = 0; t < threads.size(); t++) {
                threads.get(t).start();
            }
            for (int t = 0; t < threads.size(); t++) {
                threads.get(t).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}