package utils;

import com.raylib.Colors;
import com.raylib.Raylib;
import com.raylib.Raylib.Color;
// import com.gdx.cellular.util.MaterialMap;
import elements.ElementType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ColorConstants {

    private static final Map<String, Color> colorCache = new ConcurrentHashMap<>();

    // private static final Map<String, MaterialMap> materialsMap = new HashMap<>();

    private static final Map<ElementType, List<Color>> elementColorMap = new HashMap<>();
    private static final Map<String, List<Color>> namedColorMap = new HashMap<>();
    private static final Map<String, List<Color>> effectsColorMap = new HashMap<>();
    private static final Random random = new Random();

    // Movable Solids
    private static final Raylib.Color SAND_1 = createColor(255, 255, 0, 255);
    private static final Raylib.Color SAND_2 = createColor(178, 201, 6, 255);
    private static final Raylib.Color SAND_3 = createColor(233, 252, 90, 255);

    private static final Raylib.Color DIRT_1 = createColor(96, 47, 18, 255);
    private static final Raylib.Color DIRT_2 = createColor(135, 70, 32, 255);
    private static final Raylib.Color DIRT_3 = createColor(79, 38, 15, 255);

    private static final Raylib.Color COAL_1 = createColor(53, 53, 53, 255);
    private static final Raylib.Color COAL_2 = createColor(34, 35, 38, 255);
    private static final Raylib.Color COAL_3 = createColor(65, 65, 65, 255);

    private static final Raylib.Color EMBER = createColor(102, 59, 0, 255);

    private static final Raylib.Color GUNPOWDER_1 = createColor(255, 142, 142, 255);
    private static final Raylib.Color GUNPOWDER_2 = createColor(255, 91, 91, 255);
    private static final Raylib.Color GUNPOWDER_3 = createColor(219, 160, 160, 255);

    private static final Raylib.Color SNOW = createColor(255, 255, 255, 255);

    private static final Raylib.Color PLAYERMEAT = createColor(255, 255, 0, 255);

    // Immovable Solids
    private static final Raylib.Color STONE = createColor(150, 150, 150, 255);

    private static final Raylib.Color BRICK_1 = createColor(188, 3, 0, 255);
    private static final Raylib.Color BRICK_2 = createColor(188, 3, 0, 255);
    private static final Raylib.Color BRICK_3 = createColor(188, 3, 0, 255);
    private static final Raylib.Color BRICK_4 = createColor(188, 3, 0, 255);
    private static final Raylib.Color BRICK_5 = createColor(206, 206, 206, 255);

    private static final Raylib.Color WOOD_1 = createColor(165, 98, 36, 255);
    private static final Raylib.Color WOOD_2 = createColor(61, 33, 7, 255);
    private static final Raylib.Color WOOD_3 = createColor(140, 74, 12, 255);

    private static final Raylib.Color TITANIUM = createColor(234, 234, 234, 255);

    private static final Raylib.Color SLIME_MOLD_1 = createColor(255, 142, 243, 255);
    private static final Raylib.Color SLIME_MOLD_2 = createColor(201, 58, 107, 255);
    private static final Raylib.Color SLIME_MOLD_3 = createColor(234, 35, 213, 255);

    private static final Raylib.Color GROUND = createColor(68, 37, 37, 255);

    // Liquids (with transparency)
    private static final Raylib.Color WATER = createColor(28, 86, 234, 204); // .8f = 204/255

    private static final Raylib.Color OIL = createColor(55, 60, 73, 204); // .8f = 204/255

    private static final Raylib.Color ACID = createColor(0, 255, 0, 255);

    private static final Raylib.Color LAVA = createColor(255, 165, 0, 255);

    private static final Raylib.Color BLOOD = createColor(234, 0, 0, 204); // .8f = 204/255

    private static final Raylib.Color CEMENT = createColor(209, 209, 209, 255);

    // Gasses (with transparency)
    private static final Raylib.Color SMOKE = createColor(147, 147, 147, 127); // 0.5f = 127/255

    private static final Raylib.Color FLAMMABLE_GAS = createColor(0, 255, 0, 127); // 0.5f = 127/255

    private static final Raylib.Color SPARK = createColor(89, 35, 13, 255);

    private static final Raylib.Color STEAM_1 = createColor(204, 204, 204, 204); // 0.8f = 204/255
    private static final Raylib.Color STEAM_2 = createColor(204, 204, 204, 25);  // 0.1f = 25/255
    private static final Raylib.Color STEAM_3 = createColor(204, 204, 204, 115); // 0.45f = 115/255

    // Effects
    private static final String FIRE_NAME = "Fire";
    private static final Raylib.Color FIRE_1 = createColor(89, 35, 13, 255);
    private static final Raylib.Color FIRE_2 = createColor(100, 27, 7, 255);
    private static final Raylib.Color FIRE_3 = createColor(77, 10, 20, 255);

    // Others
    private static final Raylib.Color PARTICLE = createColor(0, 0, 0, 0);
    private static final Raylib.Color BOID_1 = createColor(0, 255, 255, 0);
    private static final Raylib.Color BOID_2 = createColor(200, 0, 255, 0);
    private static final Raylib.Color BOID_3 = createColor(150, 255, 255, 0);
    private static final Raylib.Color EMPTY_CELL = createColor(0, 0, 0, 255);

    private static final String GRASS = "Grass";
    private static final Raylib.Color GRASS_1 = createColor(0, 216, 93, 255); // Fixed alpha and RGB values
    private static final Raylib.Color GRASS_2 = createColor(0, 173, 75, 255); // Fixed alpha and RGB values
    private static final Raylib.Color GRASS_3 = createColor(0, 239, 103, 255); // Fixed alpha and RGB values


    static {
        Arrays.stream(ElementType.values()).forEach(type -> elementColorMap.put(type, new ArrayList<>()));
        elementColorMap.get(ElementType.SAND).add(SAND_1);
        elementColorMap.get(ElementType.SAND).add(SAND_2);
        elementColorMap.get(ElementType.SAND).add(SAND_3);

        // elementColorMap.get(ElementType.DIRT).add(DIRT_1);
        // elementColorMap.get(ElementType.DIRT).add(DIRT_2);
        // elementColorMap.get(ElementType.DIRT).add(DIRT_3);

        // elementColorMap.get(ElementType.COAL).add(COAL_1);
        // elementColorMap.get(ElementType.COAL).add(COAL_2);
        // elementColorMap.get(ElementType.COAL).add(COAL_3);

        // elementColorMap.get(ElementType.GUNPOWDER).add(GUNPOWDER_1);
        // elementColorMap.get(ElementType.GUNPOWDER).add(GUNPOWDER_2);
        // elementColorMap.get(ElementType.GUNPOWDER).add(GUNPOWDER_3);

        // elementColorMap.get(ElementType.PLAYERMEAT).add(PLAYERMEAT);

        // elementColorMap.get(ElementType.EMBER).add(EMBER);

        // elementColorMap.get(ElementType.SNOW).add(SNOW);

        elementColorMap.get(ElementType.STONE).add(STONE);

        // elementColorMap.get(ElementType.BRICK).add(BRICK_1);
        // elementColorMap.get(ElementType.BRICK).add(BRICK_2);
        // elementColorMap.get(ElementType.BRICK).add(BRICK_3);
        // elementColorMap.get(ElementType.BRICK).add(BRICK_4);
        // elementColorMap.get(ElementType.BRICK).add(BRICK_5);


        // elementColorMap.get(ElementType.WOOD).add(WOOD_1);
        // elementColorMap.get(ElementType.WOOD).add(WOOD_2);
        // elementColorMap.get(ElementType.WOOD).add(WOOD_3);

        // elementColorMap.get(ElementType.TITANIUM).add(TITANIUM);

        // elementColorMap.get(ElementType.GROUND).add(GROUND);

        // elementColorMap.get(ElementType.SLIMEMOLD).add(SLIME_MOLD_1);
        // elementColorMap.get(ElementType.SLIMEMOLD).add(SLIME_MOLD_2);
        // elementColorMap.get(ElementType.SLIMEMOLD).add(SLIME_MOLD_3);

        // elementColorMap.get(ElementType.WATER).add(WATER);

        // elementColorMap.get(ElementType.OIL).add(OIL);

        // elementColorMap.get(ElementType.ACID).add(ACID);

        // elementColorMap.get(ElementType.LAVA).add(LAVA);

        // elementColorMap.get(ElementType.BLOOD).add(BLOOD);

        // elementColorMap.get(ElementType.SMOKE).add(SMOKE);

        // elementColorMap.get(ElementType.CEMENT).add(CEMENT);

        // elementColorMap.get(ElementType.STEAM).add(STEAM_1);
        // elementColorMap.get(ElementType.STEAM).add(STEAM_2);
        // elementColorMap.get(ElementType.STEAM).add(STEAM_3);

        // elementColorMap.get(ElementType.FLAMMABLEGAS).add(FLAMMABLE_GAS);

        // elementColorMap.get(ElementType.SPARK).add(SPARK);

        // elementColorMap.get(ElementType.EXPLOSIONSPARK).add(Color.ORANGE);

        elementColorMap.get(ElementType.PARTICLE).add(PARTICLE);

        // elementColorMap.get(ElementType.BOID).add(BOID_1);
        // elementColorMap.get(ElementType.BOID).add(BOID_2);
        // elementColorMap.get(ElementType.BOID).add(BOID_3);

        elementColorMap.get(ElementType.EMPTYCELL).add(EMPTY_CELL);

        effectsColorMap.put(FIRE_NAME, new ArrayList<>());
        effectsColorMap.get(FIRE_NAME).add(FIRE_1);
        effectsColorMap.get(FIRE_NAME).add(FIRE_2);
        effectsColorMap.get(FIRE_NAME).add(FIRE_3);

        namedColorMap.put(GRASS, new ArrayList<>());
        namedColorMap.get(GRASS).add(GRASS_1);
        namedColorMap.get(GRASS).add(GRASS_2);
        namedColorMap.get(GRASS).add(GRASS_3);



        List<ElementType> missingElements = Arrays.stream(ElementType.values()).filter(type -> elementColorMap.get(type).size() == 0).collect(Collectors.toList());
        if (missingElements.size() > 0) {
            throw new IllegalStateException("Elements " + missingElements.toString() + "have no assigned colors");
        }

        // Place custom textures in materialsMap
        // Pixmap stonePixmap = Assets.getPixmap("elementtextures/Stone.png");
        // Pixmap woodPixmap = Assets.getPixmap("elementtextures/Wood.png");
        // Pixmap brickPixmap = Assets.getPixmap("elementtextures/Brick.png");
        // materialsMap.put("STONE", new MaterialMap(stonePixmap));
        // materialsMap.put("WOOD", new MaterialMap(woodPixmap));
        // materialsMap.put("BRICK", new MaterialMap(brickPixmap));
    }

    private static Raylib.Color createColor(int r, int g, int b, int a) {
        Raylib.Color color = new Raylib.Color();
        // Check if these methods exist:
        color.r((byte)(r & 0xFF));  // Mask to handle signed byte conversion
        color.g((byte)(g & 0xFF));  
        color.b((byte)(b & 0xFF));
        color.a((byte)(a & 0xFF));
        return color;
    }

    private int packColor(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static Color getColorByName(String name) {
        return namedColorMap.get(name).get(random.nextInt(namedColorMap.get(name).size()));
    }

    public static Color getColorForElementType(ElementType elementType) {
        List<Color> colorList = elementColorMap.get(elementType);
        return elementColorMap.get(elementType).get(random.nextInt(colorList.size()));
    }

    public static Color getColorForElementType(ElementType elementType, int x, int y) {
        // if (materialsMap.get(elementType.name()) != null) {
        //     int rgb = materialsMap.get(elementType.name()).getRGB(x, y);
        //     return colorCache.computeIfAbsent(String.valueOf(rgb), k-> {
        //         Color color = new Color();
        //         Color.rgba8888ToColor(color, rgb);
        //         return color;
        //     });
        // } else {
            return getColorForElementType(elementType);
        // }
    }


}