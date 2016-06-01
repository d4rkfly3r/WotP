package net.d4rkfly3r.wotp.render;

import net.d4rkfly3r.wotp.managers.GraphicsManager;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;

public class SpriteBuilder {

    private BufferedImage read;
    private int imageWidth;
    private int imageHeight;
    private int animCount;
    private int metaCount;

    private int defaultMeta = 0;
    private String name;
    private String uniqueName;

    private SpriteBuilder() {
    }

    @Nonnull
    public static SpriteBuilder create(@Nonnull String uniqueName, @Nonnull final BufferedImage read) {
        final SpriteBuilder data = new SpriteBuilder();
        data.read = read;
        data.uniqueName = uniqueName;
        return data;
    }

    @Nonnull
    public Sprite build() {

        BufferedImage[][] subs = new BufferedImage[metaCount][animCount];
        for (int i = 0; i < metaCount; i++) {
            for (int j = 0; j < animCount; j++) {
                subs[i][j] = GraphicsManager.resize(this.read.getSubimage(j * imageWidth, i * imageHeight, imageWidth, imageHeight), 50, 50);
            }
        }

        return new Sprite(uniqueName, name != null ? name : uniqueName, subs, metaCount, animCount, imageWidth, imageHeight, defaultMeta);
    }

    @Nonnull
    public SpriteBuilder width(final int imageWidth) {
        this.imageWidth = imageWidth;
        this.animCount = read.getWidth() / imageWidth;
        return this;
    }

    public SpriteBuilder defaultMeta(final int defaultMeta) {
        this.defaultMeta = defaultMeta;
        return this;
    }

    @Nonnull
    public SpriteBuilder height(final int imageHeight) {
        this.imageHeight = imageHeight;
        this.metaCount = read.getHeight() / imageHeight;
        return this;
    }
}
