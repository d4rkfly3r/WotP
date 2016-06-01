package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.interfaces.IManager;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Joshua on 5/31/2016.
 */
public class GraphicsManager implements IManager {
    private final Game game;
    private final ResourceManager resourceManager;

    public GraphicsManager(@Nonnull final Game game, @Nonnull final ResourceManager resourceManager) {
        this.game = game;
        this.resourceManager = resourceManager;
    }

    public void loadTextures() {
        try {
            final Path assetDir = this.resourceManager.getAssetDir().resolve("textures").resolve("sprites");
            Files.list(assetDir)
                    .filter(Files::isDirectory)
                    .forEach(this::generateSprite);
        } catch (IOException e) {
            System.err.println("Error generating sprites: " + e.getMessage());
        }
    }

    private void generateSprite(Path path) {
        System.out.println("Generating sprite from sprite folder: " + path.toString());
        try {
            final Map<String, String> collect = Files.readAllLines(path.resolve(".wotpspr")).stream().map(s -> s.split("=")).collect(Collectors.toMap(sa -> sa[0], sa -> sa[1]));

        } catch (Exception e) {
            System.err.println("Error generating sprite from sprite folder: " + path.toString());
        }
    }

    @Override
    public void fireUpdate() {

    }
}
