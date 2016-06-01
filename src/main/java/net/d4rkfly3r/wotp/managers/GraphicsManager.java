package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.interfaces.IManager;
import net.d4rkfly3r.wotp.render.Sprite;
import net.d4rkfly3r.wotp.render.SpriteBuilder;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphicsManager implements IManager {
    private static final Sprite NULL_SPRITE = SpriteBuilder.create("null", new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB)).height(200).width(200).build();
    private static Map<String, Sprite> sprites = new HashMap<>();
    private final Game game;
    private final ResourceManager resourceManager;

    public GraphicsManager(@Nonnull final Game game, @Nonnull final ResourceManager resourceManager) {
        this.game = game;
        this.resourceManager = resourceManager;
    }

    @Nonnull
    public static Sprite getSprite(@Nonnull String uniqueName) {
        return sprites.containsKey(uniqueName) ? sprites.get(uniqueName) : NULL_SPRITE; // TODO Make return/generate error sprite
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
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

    private void generateSprite(@Nonnull Path path) {
        System.out.println("Generating sprite from sprite folder: " + path.toString());
        try {
            final Map<String, String> collect = Files.readAllLines(path.resolve(".sprmeta")).stream().map(s -> s.split("=")).filter(sa -> sa.length >= 2).collect(Collectors.toMap(sa -> sa[0], sa -> sa[1]));
            int imageWidth = Integer.decode(collect.get("width"));
            int imageHeight = Integer.decode(collect.get("height"));

            final BufferedImage read = ImageIO.read(path.resolve(".spr").toUri().toURL());
            if (read.getWidth() % imageWidth != 0 || read.getHeight() % imageHeight != 0) {
                System.err.println("Invalid sprite dimensions for given size: " + path.toString());
            }

            try {
                Sprite sprite = SpriteBuilder.create(path.toFile().getName(), read)
                        .width(imageWidth)
                        .height(imageHeight)
                        .build();
                sprites.put(sprite.getUniqueName(), sprite);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Error generating sprite from sprite folder: " + path.toString());
        }
    }

    @Override
    public void fireUpdate() {

    }
}
