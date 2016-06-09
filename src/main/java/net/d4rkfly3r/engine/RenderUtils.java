package net.d4rkfly3r.engine;

import net.d4rkfly3r.engine.images.Texture;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {

    private static Color clearColor = Color.BLACK;

    /**
     * Non normailized (out of 255)
     */
    public static void setClearColor(Integer r, Integer g, Integer b, Integer a) {
        setClearColor(new Color(r, g, b, a));
    }

    public static void setClearColor(Color cC) {
        clearColor = cC;
    }

    /**
     * Non normailized (out of 255)
     */
    public static void setColor(Integer r, Integer g, Integer b, Integer a) {
        setColor(new Color(r, g, b, a));
    }

    public static void setColor(Color cC) {
        glColor4f(nC(cC.getRed()), nC(cC.getGreen()), nC(cC.getBlue()), nC(cC.getAlpha()));
    }


    public static void getClearColor() {
        glClearColor(nC(clearColor.getRed()), nC(clearColor.getGreen()), nC(clearColor.getBlue()), nC(clearColor.getAlpha()));
    }

    private static float nC(Integer cV) {
        return ((float) cV) / 255f;
    }

    public static void renderSprite(Texture texture, int x, int y, int width, int height) {
        texture.bind();

        float u = 0f;
        float v = 0f;
        float u2 = 1f;
        float v2 = 1f;

        glColor4f(1f, 1f, 1f, 1f);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(u, v);
            glVertex2f(x, y);
            glTexCoord2f(u, v2);
            glVertex2f(x, y + height);
            glTexCoord2f(u2, v2);
            glVertex2f(x + width, y + height);
            glTexCoord2f(u2, v);
            glVertex2f(x + width, y);
        }
        glEnd();
    }

    public static void renderStaticSprite(Texture texture, int x, int y, int width, int height) {

    }
}
