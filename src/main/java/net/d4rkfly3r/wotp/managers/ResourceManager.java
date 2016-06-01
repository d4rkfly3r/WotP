package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.interfaces.IManager;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Joshua on 5/31/2016.
 */
public class ResourceManager implements IManager {
    private final Game game;
    private Path assetDir;

    public ResourceManager(@Nonnull final Game game) {
        this.game = game;

        this.assetDir = Paths.get("assets");
        cheackAndCreateFolder(this.assetDir);
    }

    private void cheackAndCreateFolder(Path folderPath) {
        if (Files.notExists(folderPath)) {
            try {
                Files.createDirectories(folderPath);
            } catch (IOException e) {
                System.err.println("Error when trying to generate folder: " + folderPath.toString());
            }
        } else {
            // TODO Verify things are as they should be.
        }
    }

    @Override
    public void fireUpdate() {

    }

    public Path getAssetDir() {
        return assetDir;
    }
}
