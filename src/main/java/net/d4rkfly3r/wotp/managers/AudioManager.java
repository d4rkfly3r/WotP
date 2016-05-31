package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.interfaces.IManager;

import javax.annotation.Nonnull;

/**
 * Created by Joshua on 5/31/2016.
 */
public class AudioManager implements IManager {
    private final Game game;
    private final ResourceManager resourceManager;

    public AudioManager(@Nonnull final Game game, @Nonnull final ResourceManager resourceManager) {
        this.game = game;
        this.resourceManager = resourceManager;
    }

    public void playLoadingMusic() {

    }

    @Override
    public void fireUpdate() {

    }
}
