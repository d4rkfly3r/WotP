package net.d4rkfly3r.wotp.display;

import net.d4rkfly3r.wotp.Game;

import java.util.Random;

/**
 * Created by Joshua on 5/31/2016.
 */
public class Screen {
    private final Game game;
    private final int[] pixels;
    private final Random random = new Random();
    private int xOffset, yOffset;

    public Screen(Game game) {
        this.game = game;
        this.pixels = new int[game.getWidth() * game.getHeight()];

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = random.nextInt(0xffffff);
        }
    }

    public void clear() {
//        for (int i = 0; i < pixels.length; i++) {
//            pixels[i] = 0;
//        }
    }

    public int getPixel(int index) {
        return this.pixels[index];
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
