package net.d4rkfly3r.wotp.level.tile;

import net.d4rkfly3r.wotp.render.Screen;
import net.d4rkfly3r.wotp.render.Sprite;

import javax.annotation.Nonnull;

public class Tile {
    protected int x, y;
    protected Sprite sprite;

    public Tile(@Nonnull Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, @Nonnull Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return false;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
