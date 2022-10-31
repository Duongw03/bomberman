package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class WallPass extends PowerUp{
    public WallPass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void usePowerUp() {
        if (Bomber.bomberman.isCollision(this)) {
            BombermanGame.powerUps.remove(this);
        }
    }

    public void update() {
        usePowerUp();
    }
}
