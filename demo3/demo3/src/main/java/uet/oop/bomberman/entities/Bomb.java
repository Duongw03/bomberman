package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }
    int time = 0;
    Flame flameRight = new Flame(x+1, y, null);
    public void explode() {
        flameRight.setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last,Sprite.explosion_horizontal_right_last1
                ,Sprite.explosion_horizontal_right_last2, time, 32).getFxImage());
    }
    public void update() {
        if (time < 32) {
            explode();
            time++;
            System.out.print(time);
        } else {
            BombermanGame.stillObjects.remove(this);
        }
    }
}
