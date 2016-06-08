package net.d4rkfly3r.wotp;

import net.d4rkfly3r.wotp.entities.Player;
import net.d4rkfly3r.wotp.managers.InputManager;
import net.d4rkfly3r.wotp.managers.*;
import net.d4rkfly3r.wotp.render.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public final class Game implements Runnable {

    private final Font verdana = new Font("Verdana", 0, 25);
    private final int width = 600;
    private final int height = width / 3 * 2;
    private final Dimension frameSize;
    private final JFrame gameFrame;
    private final ResourceManager resourceManager;
    private final AudioManager audioManager;
    private final LevelManager levelManager;
    private final GraphicsManager graphicsManager;
    private final NetworkManager networkManager;
    private final PlayerManager playerManager;
    private final InputManager inputManager;
    private final Player thisPlayer;
    private String title;
    private GameState gameState;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Screen screen;

    Game() {

        this.gameState = GameState.STARTING;
        this.frameSize = new Dimension(width, height);
        this.title = "WotP | Loading...";

        this.gameFrame = new JFrame();
        this.gameFrame.setSize(this.frameSize);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.resourceManager = new ResourceManager(this);
        this.audioManager = new AudioManager(this, this.resourceManager);
        this.graphicsManager = new GraphicsManager(this, this.resourceManager);

        // Primaries loaded, show loading screen and start audio.
        // TODO Show loading screen.
        this.audioManager.playLoadingMusic();

        this.inputManager = new InputManager(this);
        this.screen = new Screen(this);
        this.levelManager = new LevelManager(this, this.resourceManager);
        this.networkManager = new NetworkManager(this);
        this.playerManager = new PlayerManager(this);
        this.thisPlayer = this.playerManager.getThisPlayer();

        this.graphicsManager.loadTextures();

        this.gameFrame.addKeyListener(inputManager.getListenerHandler());
        this.gameFrame.addMouseListener(inputManager.getListenerHandler());
        this.gameFrame.addMouseMotionListener(inputManager.getListenerHandler());
        this.gameFrame.addMouseWheelListener(inputManager.getListenerHandler());

        this.gameFrame.addWindowFocusListener(inputManager.getListenerHandler());
    }

    void start() {
        this.gameState = GameState.RUNNING;
        this.gameFrame.setVisible(true);
        run();
    }

    @Override
    public void run() {
        this.gameState = GameState.PLAYING_GAME;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 100.0; // Change second number to change the expected UPS
        double delta = 0;
        int frames = 0;
        int updates = 0;
        gameFrame.requestFocus();
        while (gameState == GameState.PLAYING_GAME) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                fireUpdate();
                updates++;
                delta--;
            }
            render();
            frames++;

            // one second || 1000 ns = 1 sec
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                gameFrame.setTitle(title + "  | " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void fireUpdate() {
        if (gameState == GameState.PLAYING_GAME) {
            this.inputManager.fireUpdate();
            this.playerManager.fireUpdate();
            this.thisPlayer.update();
            this.levelManager.fireUpdate();
        }
    }

    private void render() {
        BufferStrategy bs = this.gameFrame.getBufferStrategy();
        if (bs == null) {
            this.gameFrame.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        screen.clear();

        int xScroll = this.thisPlayer.getXPos() - screen.getWidth() / 2;
        int yScroll = this.thisPlayer.getYPos() - screen.getHeight() / 2;
        levelManager.getCurrentLevel().render(xScroll, yScroll, screen);


        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixel(i);
        }

        g.drawImage(image, 0, 0, width, height, null);

        g.setColor(Color.yellow);
        g.setFont(verdana);
        g.drawString("Used Memory: " + (Runtime.getRuntime().totalMemory() / 1000000 - Runtime.getRuntime().freeMemory() / 1000000) + "(MB) / " + Runtime.getRuntime().totalMemory() / 1000000 + "(MB)", 20, 60);
        g.dispose();
        bs.show();
    }

    private void stop() {
        gameState = GameState.STOPPING;
        System.exit(0);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public GameState getGameState() {
        return gameState;
    }
}
