package net.d4rkfly3r.wotp.entities;

import net.d4rkfly3r.wotp.level.Level;
import net.d4rkfly3r.wotp.render.Screen;

import javax.annotation.Nonnull;

public abstract class Entity {
    protected int xPos, yPos;
    protected boolean isRemoved;
    protected Level currentLevel;

    Entity(@Nonnull Level startingLevel) {
        this.currentLevel = startingLevel;
    }

    public abstract void update();

    public abstract void render(@Nonnull Screen screen);

    public void remove() {
        isRemoved = true;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void changeLevel(@Nonnull Level newLevel) {
        this.currentLevel = newLevel;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
}
