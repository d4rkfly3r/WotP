package net.d4rkfly3r.wotp.input;

import net.d4rkfly3r.wotp.Game;

import javax.annotation.Nonnull;
import java.awt.event.*;

/**
 * Created by Joshua on 5/31/2016.
 */
public class Input {
    private final Game game;

    private final ListenerHandler listenerHandler;

    public Input(@Nonnull final Game game) {
        this.game = game;
        this.listenerHandler = new ListenerHandler();
    }

    private void reset() {

    }

    public void fireUpdate() {

    }

    @Nonnull
    public ListenerHandler getListenerHandler() {
        return listenerHandler;
    }

    private class ListenerHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, WindowFocusListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

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
