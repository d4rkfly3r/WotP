package net.d4rkfly3r.wotp.entities;

import net.d4rkfly3r.wotp.level.Level;
import net.d4rkfly3r.wotp.render.Sprite;

import javax.annotation.Nonnull;
import java.util.UUID;

public abstract class Player extends Entity {
    public int health = 6;
    protected Sprite playerSprite;
    protected UUID uuid;
    protected int speed = 1;
    protected boolean moving = false;
    protected int direction;


    Player(@Nonnull Level startingLevel) {
        super(startingLevel);
    }

    public void move(int xa, int ya) {

        if (xa > 0)
            direction = 1;
        if (xa < 0)
            direction = 3;
        if (ya > 0)
            direction = 2;
        if (ya < 0)
            direction = 0;

        if (!collision(0, ya * this.getSpeed())) {
            yPos += ya * this.getSpeed();
        }

        if (!collision(xa * this.getSpeed(), 0)) {
            xPos += xa * this.getSpeed();
        }
    }

    protected boolean collision(int xa, int ya) {
        boolean test = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((xPos + xa) + c % 2 * 14 - 8) / 16;
            int yt = ((yPos + ya) + c / 2 * 12 + 3) / 16;
            if (currentLevel.getTile(xt, yt).solid())
                test = true;
        }
        return test;
    }

    protected int getSpeed() {
        return speed;
    }
}
