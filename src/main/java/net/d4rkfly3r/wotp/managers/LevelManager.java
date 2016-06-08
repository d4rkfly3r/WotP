package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.interfaces.IManager;
import net.d4rkfly3r.wotp.level.Level;

import javax.annotation.Nonnull;

public class LevelManager implements IManager {
    private final Game game;
    private final ResourceManager resourceManager;
    private Level currentLevel;

    public LevelManager(@Nonnull final Game game, @Nonnull final ResourceManager resourceManager) {
        this.game = game;
        this.resourceManager = resourceManager;
        this.currentLevel = new Level(500, 500);
    }

    @Override
    public void fireUpdate() {

    }

    @Nonnull
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(@Nonnull Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}
