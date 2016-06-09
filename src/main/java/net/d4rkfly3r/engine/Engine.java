package net.d4rkfly3r.engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Engine {

    private static Engine ourInstance = new Engine();

    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    private ArrayList<Renderable> renderableArrayList;
    private HashMap<Integer, ArrayList<Renderable>> renderableLayerMap;
    private long window;
    private int x = 0, y = 0;
    private EngineState currentEngineState = EngineState.STARTING;


    private Engine() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        renderableArrayList = new ArrayList<>();
        renderableLayerMap = new HashMap<>();
    }

    public static Engine getInstance() {
        return ourInstance;
    }

    public void init(int width, int height, String title) {
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable


        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window...");
        }

        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); // the window will not be resizable


        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window,
                (vidMode.width() - width) / 2,
                (vidMode.height() - height) / 2
        );

        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {

                switch (key) {
                    case GLFW_KEY_ESCAPE:
                        glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
                        break;
                    case GLFW_KEY_LEFT:
                        x -= 5;
                        break;
                    case GLFW_KEY_RIGHT:
                        x += 5;
                        break;
                    case GLFW_KEY_UP:
                        y -= 5;
                        break;
                    case GLFW_KEY_DOWN:
                        y += 5;
                        break;
                    default:
                        break;
                }
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                glOrtho(x, 1200 + x, 900 + y, y, -1, 1);
                glMatrixMode(GL_MODELVIEW);
            }
        });

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        GL.createCapabilities(); // valid for latest build
    }

    public EngineState getCurrentEngineState() {
        return currentEngineState;
    }

    public void setCurrentEngineState(EngineState currentEngineState) {
        this.currentEngineState = currentEngineState;
    }

    public void start() {

        RenderUtils.getClearColor();
        glEnable(GL_TEXTURE_2D);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 1200, 900, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);

        while (!glfwWindowShouldClose(Engine.getInstance().getWindow())) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer


            for (int i = 1; i <= 8; i++) {
                try {
                    renderableLayerMap.get(i).forEach(Renderable::run);
                } catch (Exception e) {
                }
            }

            glfwSwapBuffers(Engine.getInstance().getWindow()); // swap the color buffers
            glfwPollEvents();
        }

    }

    public void addRender(Integer integer, Renderable renderable) {
        if (integer > 8) integer = 8;
        if (integer < 1) integer = 1;
        if (!renderableLayerMap.containsKey(integer)) renderableLayerMap.put(integer, new ArrayList<>());
        renderableLayerMap.get(integer).add(renderable);
    }


    public long getWindow() {
        return window;
    }

    public GLFWKeyCallback getKeyCallback() {
        return keyCallback;
    }

    public GLFWErrorCallback getErrorCallback() {
        return errorCallback;
    }

    public enum EngineState {
        STARTING, INITIALIZING, RUNNING, PAUSED, STOPPING
    }

}
