package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.interfaces.IManager;

import javax.annotation.Nonnull;

/**
 * Created by Joshua on 5/31/2016.
 */
public class ResourceManager implements IManager {
    private final Game game;

    public ResourceManager(@Nonnull final Game game) {
        this.game = game;
    }

    @Override
    public void fireUpdate() {

    }
}
