package net.d4rkfly3r.wotp.level;

import net.d4rkfly3r.wotp.LevelObject;
import net.d4rkfly3r.wotp.level.tile.Tile;
import net.d4rkfly3r.wotp.level.tile.Tiles;
import net.d4rkfly3r.wotp.render.Screen;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {


    protected final Map<Class<?>, List<LevelObject>> objects;
    protected int width, height;
    protected int[] tilesInt, tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.tilesInt = new int[width * height];
        this.tiles = new int[width / 16 * height / 16];
        this.objects = new HashMap<>();
        generateLevel();
    }

    public Level(Path path) {
        this.objects = new HashMap<>();
        loadLevel(path);
    }

    private void generateLevel() {
        for (int i = 0; i < tilesInt.length; i++) {
//            tilesInt[i] = i % 2 == 0 ? 0x00000001 : 0x00000000;

        }
    }

    private void loadLevel(Path path) {

    }

    public <T extends LevelObject> void addObject(T t) {
        t.init();
        if (!objects.containsKey(t.getClass())) {
            objects.put(t.getClass(), new ArrayList<>());
        }
        objects.get(t.getClass()).add(t);
    }

    public void update() {
        objects.values().forEach(levelObjects -> levelObjects.forEach(LevelObject::update));
    }

    public void render(int xScroll, int yScroll, Screen screen) {

        screen.setOffset(xScroll, yScroll);

        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.getWidth() + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.getHeight() + 16) >> 4;

//        System.out.println(x1 + " | " + y1 + " | " + x1 * y1 + " | " + tiles.length);

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
                // 256 = 16 * 16
                // if (x + y * 16 < 0 || x + y * 16 >= 256) {
                // Tile.spawn_void.render(x, y, screen);
                // // continue;
                // } else {
                // tiles[x + y * 16].render(x, y, screen);
                // }
            }
        }

//        objects.values().stream().flatMap(List::stream).filter(LevelObject::shouldRender).forEach(LevelObject::render);
    }

    public Tile getTile(int x, int y) {


        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tiles.of(0x00000000);
        }

        return Tiles.of(0x00000001);

//        return Tiles.of(tiles[x + y * width]);
        /*grass*/
//        if (tiles[x + y * width] == Tile.col_spawn_grass1)
//            return Tile.spawn_grass1;
//        if (tiles[x + y * width] == Tile.col_spawn_grass2)
//            return Tile.spawn_grass2;
//        if (tiles[x + y * width] == Tile.col_spawn_grass3)
//            return Tile.spawn_grass3;
//        if (tiles[x + y * width] == Tile.col_spawn_grass4)
//            return Tile.spawn_grass4;
//        if (tiles[x + y * width] == Tile.col_spawn_grass5)
//            return Tile.spawn_grass5;
//        if (tiles[x + y * width] == Tile.col_spawn_grass6)
//            return Tile.spawn_grass6;
         /*floor*/
//        if (tiles[x + y * width] == Tile.col_spawn_floor1)
//            return Tile.spawn_floor1;
//        if (tiles[x + y * width] == Tile.col_spawn_floor2)
//            return Tile.spawn_floor2;
//        if (tiles[x + y * width] == Tile.col_spawn_floor3)
//            return Tile.spawn_floor3;
//        if (tiles[x + y * width] == Tile.col_spawn_floor4)
//            return Tile.spawn_floor4;
//        if (tiles[x + y * width] == Tile.col_spawn_floor5)
//            return Tile.spawn_floor5;
//        if (tiles[x + y * width] == Tile.col_spawn_floor6)
//            return Tile.spawn_floor6;
//
         /*wall*/
//        if (tiles[x + y * width] == Tile.col_spawn_wall1)
//            return Tile.spawn_wall1;
//        if (tiles[x + y * width] == Tile.col_spawn_wall2)
//            return Tile.spawn_wall2;
//        if (tiles[x + y * width] == Tile.col_spawn_wall3)
//            return Tile.spawn_wall3;
//        if (tiles[x + y * width] == Tile.col_spawn_wall4)
//            return Tile.spawn_wall4;
//        if (tiles[x + y * width] == Tile.col_spawn_wall5)
//            return Tile.spawn_wall5;
//        if (tiles[x + y * width] == Tile.col_spawn_wall6)
//            return Tile.spawn_wall6;
//        if (tiles[x + y * width] == Tile.col_spawn_wall7)
//            return Tile.spawn_wall7;
         /*hedge*/
//        if (tiles[x + y * width] == Tile.col_spawn_hedge1)
//            return Tile.spawn_hedge1;
//        if (tiles[x + y * width] == Tile.col_spawn_hedge2)
//            return Tile.spawn_hedge2;
         /*water*/
//
//        if (tiles[x + y * width] == Tile.col_spawn_water1)
//            return Tile.spawn_water1;
//        if (tiles[x + y * width] == Tile.col_spawn_water2)
//            return Tile.spawn_water2;
//        if (tiles[x + y * width] == Tile.col_spawn_water3)
//            return Tile.spawn_water3;
//        if (tiles[x + y * width] == Tile.col_spawn_water4)
//            return Tile.spawn_water4;
         /*misc*/
//        if (tiles[x + y * width] == Tile.col_spawn_teleporter1)
//            return Tile.spawn_teleporter1;
//        if (tiles[x + y * width] == Tile.col_spawn_teleporter2)
//            return Tile.spawn_teleporter2;
//
//        return Tile.spawn_grass1;
    }

    public boolean tileCollision(double x, double y, double nx, double ny, int size) {
        boolean test = false;
        for (int c = 0; c < 4; c++) {
            int xt = (((int) x + (int) nx) + c % 2 * size - 4) / 16;
            int yt = (((int) y + (int) ny) + c / 2 * size) / 16;
            if (getTile(xt, yt).solid())
                test = true;
        }
        return test;
    }

}