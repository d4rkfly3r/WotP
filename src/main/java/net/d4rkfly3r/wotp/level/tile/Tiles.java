package net.d4rkfly3r.wotp.level.tile;

import net.d4rkfly3r.wotp.managers.GraphicsManager;

import java.util.HashMap;
import java.util.Map;

public class Tiles {

    private static Tile VOID_TILE = new Tile(GraphicsManager.getSprite("void"));
    private static Map<Integer, Tile> tiles = new HashMap<Integer, Tile>() {{
        put(0x00000000, VOID_TILE);
        put(0x00000001, new Tile(GraphicsManager.getSprite("grass")));
    }};

//    public static Tile of(String name); // TODO Finish this;
    public static Tile of(int i) {
        return tiles.getOrDefault(i, VOID_TILE);
    }
}
