package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedUp extends PowerUp {
    public SpeedUp(int x, int y, Image img) {
        super(x, y, img);
    }

    public static boolean isSpeedUp = false;

    public  void usePowerUp() {
            if (Bomber.bomberman.isCollision(this)) {
                //BombermanGame.powerUps.remove(this);
                isSpeedUp = true;
        }
    }

    public void update() {
        usePowerUp();
    }
}
