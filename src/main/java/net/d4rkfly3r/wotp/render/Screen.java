package net.d4rkfly3r.wotp.render;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.MainClass;
import net.d4rkfly3r.wotp.level.tile.Tile;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Screen {
    private final int width, height;
    private final Game game;
    private final int[] pixels;
    private final Random random = new Random();
    private int xOffset, yOffset;

    public Screen(Game game) {
        this.game = game;
        width = game.getWidth();
        height = game.getHeight();
        this.pixels = new int[width * height];

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = random.nextInt(0xffffff);
        }
    }

    public void clear() {
        if (MainClass.DEBUG) {
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = random.nextInt(0xffffff);
            }

        } else {
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = 0;
            }
        }
    }

    public int getPixel(int index) {
        return this.pixels[index];
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    void renderImage(int xp, int yp, BufferedImage bufferedImage) {
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height)
                    continue;
                pixels[xa + ya * width] = bufferedImage.getRGB(x, y);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;

        tile.getSprite().render(this, xp, yp, -1, 0);

//        for (int y = 0; y < tile.getSprite().getHeight(); y++) {
//            int ya = y + yp;
//
//            for (int x = 0; x < tile.getSprite().getWidth(); x++) {
//                int xa = x + xp;
//                if (xa < -tile.getSprite().getWidth() || xa >= width || ya < 0 || ya >= height)
//                    break;
//                if (xa < 0)
//                    xa = 0;
//                pixels[xa + ya * width] = tile.getSprite().get.getPixel[x + y * tile.getSprite().getWidth()];
//            }
//        }
    }
}
