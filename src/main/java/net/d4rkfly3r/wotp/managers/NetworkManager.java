package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.interfaces.IManager;

import javax.annotation.Nonnull;

public class NetworkManager implements IManager {
    private final Game game;

    public NetworkManager(@Nonnull final Game game) {
        this.game = game;
    }

    @Override
    public void fireUpdate() {

    }
}
