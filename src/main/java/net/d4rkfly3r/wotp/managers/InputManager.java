package net.d4rkfly3r.wotp.managers;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.interfaces.IManager;

import javax.annotation.Nonnull;
import java.awt.event.*;
import java.util.Arrays;

public class InputManager implements IManager {
    private final Game game;

    private final ListenerHandler listenerHandler;

    private boolean isTabDown;
    private boolean[] keys = new boolean[525];
    private boolean up, down, left, right, exit, crouch, zoomIn;
    private boolean speedUp, speedDown;
    private boolean boss;

    public InputManager(@Nonnull final Game game) {
        this.game = game;
        this.listenerHandler = new ListenerHandler();
    }

    public boolean isTabDown() {
        return isTabDown;
    }

    public void setTabDown(boolean tabDown) {
        isTabDown = tabDown;
    }

    public boolean[] getKeys() {
        return keys;
    }

    public void setKeys(boolean[] keys) {
        this.keys = keys;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isCrouch() {
        return crouch;
    }

    public boolean isZoomIn() {
        return zoomIn;
    }

    public boolean isSpeedUp() {
        return speedUp;
    }

    public boolean isSpeedDown() {
        return speedDown;
    }

    public boolean isBoss() {
        return boss;
    }

    private void reset() {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
    }

    @Override
    public void fireUpdate() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_A];
        exit = keys[KeyEvent.VK_ESCAPE];
        speedUp = keys[KeyEvent.VK_UP];// || keys[KeyEvent.VK_W];
        speedDown = keys[KeyEvent.VK_DOWN];
        crouch = keys[KeyEvent.VK_SHIFT];
        boss = keys[KeyEvent.VK_ENTER];
    }

    @Nonnull
    public ListenerHandler getListenerHandler() {
        return listenerHandler;
    }

    @Override
    public String toString() {
        return "InputManager{" +
                "game=" + game +
                ", listenerHandler=" + listenerHandler +
                ", isTabDown=" + isTabDown +
                ", keys=" + Arrays.toString(keys) +
                ", up=" + up +
                ", down=" + down +
                ", left=" + left +
                ", right=" + right +
                ", exit=" + exit +
                ", crouch=" + crouch +
                ", zoomIn=" + zoomIn +
                ", speedUp=" + speedUp +
                ", speedDown=" + speedDown +
                ", boss=" + boss +
                '}';
    }

    private class ListenerHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, WindowFocusListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            keys[e.getKeyCode()] = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keys[e.getKeyCode()] = false;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }

        @Override
        public void windowGainedFocus(WindowEvent e) {

        }

        @Override
        public void windowLostFocus(WindowEvent e) {
            reset();
        }
    }
}
