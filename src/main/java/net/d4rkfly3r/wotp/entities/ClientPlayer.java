package net.d4rkfly3r.wotp.entities;

import net.d4rkfly3r.wotp.Game;
import net.d4rkfly3r.wotp.managers.InputManager;
import net.d4rkfly3r.wotp.managers.GraphicsManager;
import net.d4rkfly3r.wotp.render.Screen;

import javax.annotation.Nonnull;
import java.util.UUID;

public class ClientPlayer extends Player {

    private final InputManager inputManager;

    public ClientPlayer(@Nonnull Game game) {
        super(game.getLevelManager().getCurrentLevel());
        this.inputManager = game.getInputManager();
        this.playerSprite = GraphicsManager.getSprite("clientplayer");
        this.direction = 180;
        this.uuid = UUID.randomUUID();
    }

    public void update() {
        int xa = 0, ya = 0;
        if (this.inputManager.isUp()) {
            ya++;
        }
        if (this.inputManager.isDown()) {
            ya--;
        }
        if (this.inputManager.isLeft()) {

            xa--;
        }
        if (this.inputManager.isRight()) {
            xa++;
        }
        if (this.inputManager.isCrouch()) {
        }
        if (this.inputManager.isExit()) {
            //TODO Call exit instead..
            System.exit(0);
        }
        if (this.inputManager.isTabDown()) {
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
        } else {
            moving = false;
        }
    }

    @Override
    public void render(@Nonnull Screen screen) {

    }
/*

    public void render(Screen screen) {
        int flip = 0;
        if (direction == 0) {
            sprite = Sprite.player_foward;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_foward_1;
                } else {
                    sprite = Sprite.player_foward_2;
                }
            }
        }
        if (direction == 90) {
            sprite = Sprite.player_side;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_side_1;
                } else {
                    sprite = Sprite.player_side_2;
                }
            }

        }
        if (direction == 180) {
            sprite = Sprite.player_back;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_back_1;
                } else {
                    sprite = Sprite.player_back_2;
                }
            }

        }
        if (direction == -90) {
            flip = 1;
            sprite = Sprite.player_side;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_side_1;
                } else {
                    sprite = Sprite.player_side_2;
                }
            }
        }
//		sprite = test.getSprite();
//        screen.renderPlayer(x - 16, y - 16, sprite, flip);

    }
*/

}
