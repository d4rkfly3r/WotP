package net.d4rkfly3r.wotp.level;

public class TileCoordinate {

    private final int TILE_SIZE = 16;
    private int x, y;

    public TileCoordinate(int x, int y) {
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] xy() {
        int[] s = new int[2];
        s[0] = x;
        s[1] = y;
        return s;
    }
}
