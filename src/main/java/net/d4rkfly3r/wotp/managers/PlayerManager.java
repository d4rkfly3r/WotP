package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.entities.ClientPlayer;
import net.d4rkfly3r.wotp.entities.Player;
import net.d4rkfly3r.wotp.interfaces.IManager;

import javax.annotation.Nonnull;

public class PlayerManager implements IManager {
    private final Game game;
    private final Player thisPlayer;

    public PlayerManager(@Nonnull final Game game) {
        this.game = game;
        this.thisPlayer = new ClientPlayer(game);
    }

    @Nonnull
    public Player getThisPlayer() {
        return thisPlayer;
    }

    @Override
    public void fireUpdate() {
    }
}
