package net.d4rkfly3r.wotp;

import net.d4rkfly3r.engine.Engine;
import net.d4rkfly3r.engine.RenderUtils;
import net.d4rkfly3r.engine.images.Texture;
import net.d4rkfly3r.engine.world.World;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class MainClass {

    public static final boolean DEBUG = false;
    private static String localhostIP;
    private static Texture texture;

    public static void main(String[] args) {
        try {
            localhostIP = getIP();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(localhostIP);
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            try {
                Engine.getInstance().init(2000, 1300, "War of the Plants");

                World world = new World();
                world.renderWorld();
                try {
                    texture = new Texture(new File("C:\\Users\\Joshua\\Pictures\\Saved Pictures\\repos.png").toURI());
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                Engine.getInstance().addRender(8, () -> {
//                    glBegin(GL_QUADS);
//                    {
//                        RenderUtils.setColor(new Color(127, 127, 255));
//                        glVertex2f(400, 400);
//                        glVertex2f(400 + 200, 400);
//                        glVertex2f(400 + 200, 400 + 200);
//                        glVertex2f(400, 400 + 200);
//                    }
//                    glEnd();
//                });
//
                Engine.getInstance().addRender(8, () -> {
                    RenderUtils.renderStaticSprite(texture, 1000, 1000, texture.getWidth(), texture.getHeight());
                });
                RenderUtils.setClearColor(Color.BLACK);
                Engine.getInstance().start();

                glfwDestroyWindow(Engine.getInstance().getWindow());
                Engine.getInstance().getKeyCallback().free();
            } finally {
                // Terminate GLFW and release the GLFWerrorfun
                glfwTerminate();
                Engine.getInstance().getErrorCallback().free();
            }

            return null; // nothing to return
        });

    }

    public static String getLocalhostIP() {
        return localhostIP;
    }

    private static String getIP() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            return in.readLine();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
