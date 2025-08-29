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

    private static final Map<ElementType, List<Integer>> elementColorMap = new HashMap<>();
    private static final Map<String, List<Integer>> namedColorMap = new HashMap<>();
    private static final Map<String, List<Integer>> effectsColorMap = new HashMap<>();
    private static final Random random = new Random();

    public static final int SAND_1 = packColor(255, 255, 0, 255);
    public static final int SAND_2 = packColor(178, 201, 6, 255);
    public static final int SAND_3 = packColor(233, 252, 90, 255);

    public static final int DIRT_1 = packColor(96, 47, 18, 255);
    public static final int DIRT_2 = packColor(135, 70, 32, 255);
    public static final int DIRT_3 = packColor(79, 38, 15, 255);

    public static final int COAL_1 = packColor(53, 53, 53, 255);
    public static final int COAL_2 = packColor(34, 35, 38, 255);
    public static final int COAL_3 = packColor(65, 65, 65, 255);

    public static final int EMBER = packColor(102, 59, 0, 255);

    public static final int GUNPOWDER_1 = packColor(255, 142, 142, 255);
    public static final int GUNPOWDER_2 = packColor(255, 91, 91, 255);
    public static final int GUNPOWDER_3 = packColor(219, 160, 160, 255);

    public static final int SNOW = packColor(255, 255, 255, 255);

    public static final int PLAYERMEAT = packColor(255, 255, 0, 255);

    // Immovable Solids
    public static final int STONE = packColor(150, 150, 150, 255);

    public static final int BRICK_1 = packColor(188, 3, 0, 255);
    public static final int BRICK_2 = packColor(188, 3, 0, 255);
    public static final int BRICK_3 = packColor(188, 3, 0, 255);
    public static final int BRICK_4 = packColor(188, 3, 0, 255);
    public static final int BRICK_5 = packColor(206, 206, 206, 255);

    public static final int WOOD_1 = packColor(165, 98, 36, 255);
    public static final int WOOD_2 = packColor(61, 33, 7, 255);
    public static final int WOOD_3 = packColor(140, 74, 12, 255);

    public static final int TITANIUM = packColor(234, 234, 234, 255);

    public static final int SLIME_MOLD_1 = packColor(255, 142, 243, 255);
    public static final int SLIME_MOLD_2 = packColor(201, 58, 107, 255);
    public static final int SLIME_MOLD_3 = packColor(234, 35, 213, 255);

    public static final int GROUND = packColor(68, 37, 37, 255);

    // Liquids (with transparency)
    public static final int WATER = packColor(28, 86, 234, 204); // 80% opacity
    public static final int OIL = packColor(55, 60, 73, 204); // 80% opacity
    public static final int ACID = packColor(0, 255, 0, 255);
    public static final int LAVA = packColor(255, 165, 0, 255);
    public static final int BLOOD = packColor(234, 0, 0, 204); // 80% opacity
    public static final int CEMENT = packColor(209, 209, 209, 255);

    // Gasses (with transparency)
    public static final int SMOKE = packColor(147, 147, 147, 127); // 50% opacity
    public static final int FLAMMABLE_GAS = packColor(0, 255, 0, 127); // 50% opacity
    public static final int SPARK = packColor(89, 35, 13, 255);
    public static final int STEAM_1 = packColor(204, 204, 204, 204); // 80% opacity
    public static final int STEAM_2 = packColor(204, 204, 204, 25);  // 10% opacity
    public static final int STEAM_3 = packColor(204, 204, 204, 115); // 45% opacity

    // Effects
    public static final String FIRE_NAME = "FIRE";
    public static final int FIRE_1 = packColor(89, 35, 13, 255);
    public static final int FIRE_2 = packColor(100, 27, 7, 255);
    public static final int FIRE_3 = packColor(77, 10, 20, 255);

    // Others
    public static final int PARTICLE = packColor(0, 0, 0, 0);
    public static final int BOID_1 = packColor(0, 255, 255, 0);
    public static final int BOID_2 = packColor(200, 0, 255, 0);
    public static final int BOID_3 = packColor(150, 255, 255, 0);
    public static final int EMPTY_CELL = packColor(0, 0, 0, 255);

    // Grass variants
    public static final String GRASS = "GRASS";
    public static final int GRASS_1 = packColor(0, 216, 93, 255);
    public static final int GRASS_2 = packColor(0, 173, 75, 255);
    public static final int GRASS_3 = packColor(0, 239, 103, 255);


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

    private static int packColor(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static Integer getColorByName(String name) {
        return namedColorMap.get(name).get(random.nextInt(namedColorMap.get(name).size()));
    }

    public static Integer getColorForElementType(ElementType elementType) {
        List<Integer> colorList = elementColorMap.get(elementType);
        return elementColorMap.get(elementType).get(random.nextInt(colorList.size()));
    }

    public static Integer getColorForElementType(ElementType elementType, int x, int y) {
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