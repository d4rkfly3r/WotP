package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.entities.Player;
import net.d4rkfly3r.wotp.interfaces.IManager;

import javax.annotation.Nonnull;

/**
 * Created by Joshua on 5/31/2016.
 */
public class PlayerManager implements IManager {
    private final Game game;

    public PlayerManager(@Nonnull final Game game) {
        this.game = game;
    }

    @Nonnull
    public Player getThisPlayer() {
        return null;
    }

    @Override
    public void fireUpdate() {
    }
}
