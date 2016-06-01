package net.d4rkfly3r.wotp.render;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Sprite {
    private final String uniqueName;
    private final String name;
    private final BufferedImage[][] subs;
    private final int metaCount;
    private final int animCount;
    private final int imageWidth;
    private final int imageHeight;
    private final int defaultMeta;

    public Sprite(String uniqueName, String name, BufferedImage[][] subs, int metaCount, int animCount, int imageWidth, int imageHeight, int defaultMeta) {

        this.uniqueName = uniqueName;
        this.name = name;
        this.subs = subs;
        this.metaCount = metaCount;
        this.animCount = animCount;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.defaultMeta = defaultMeta;
    }

    @Override
    public String toString() {
        return "Sprite{" +
                "uniqueName='" + uniqueName + '\'' +
                ", name='" + name + '\'' +
                ", subs=" + Arrays.toString(subs) +
                ", metaCount=" + metaCount +
                ", animCount=" + animCount +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                ", defaultMeta=" + defaultMeta +
                '}';
    }

    public void render(Screen screen, int x, int y, int metaCount, int animCount) {
        if (metaCount == -1 || metaCount > this.metaCount) metaCount = defaultMeta;
        if (animCount > this.animCount) animCount = this.animCount;

        screen.renderImage(x, y, subs[metaCount][animCount]);

    }

    public String getUniqueName() {
        return uniqueName;
    }
}
