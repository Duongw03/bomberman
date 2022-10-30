package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity{
    private boolean activate = false;
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public static Portal portal = new Portal(-100,-100, Sprite.portal.getFxImage());

    public void collisionPortal() {
        if(portal.isActivate()) {
            if(Bomber.bomberman.isCollision(portal)) {
                BombermanGame.winGame = true;
            }
        }
    }

    public void update() {
        collisionPortal();
    }
}
