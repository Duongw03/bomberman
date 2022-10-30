package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity{
    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    int time = 0;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static Flame flameUp = new Flame(-1,- 1, Sprite.explosion_vertical.getFxImage());
    public static Flame flameDown = new Flame(-1, -1, Sprite.explosion_vertical.getFxImage());
    public static Flame flameLeft = new Flame( -1, -1, Sprite.explosion_horizontal.getFxImage());
    public static Flame flameRight = new Flame(-1, -1, Sprite.explosion_horizontal.getFxImage());

    public void explode() {
        int xx = Bomb.bomb.getX();
        int yy = Bomb.bomb.getY();
        if(Bomb.bomb.checkCollisionUp()) {
            flameUp.img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                    Sprite.explosion_vertical_top_last2, time, 32).getFxImage();
            Flame.flameUp.setX(xx);
            Flame.flameUp.setY(yy - 32);
        }
        if(Bomb.bomb.checkCollisionDown()) {
            flameDown.img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                    Sprite.explosion_vertical_down_last2, time, 32).getFxImage();
            Flame.flameDown.setX(xx);
            Flame.flameDown.setY(yy + 32);
        }
        if(Bomb.bomb.checkCollisionLeft()) {
            flameLeft.img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                    Sprite.explosion_horizontal_left_last2, time, 32).getFxImage();
            Flame.flameLeft.setX(xx-32);
            Flame.flameLeft.setY(yy);
        }
        if(Bomb.bomb.checkCollisionRight()) {
            flameRight.img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,
                    Sprite.explosion_horizontal_right_last2, time, 32).getFxImage();
            Flame.flameRight.setX(xx+32);
            Flame.flameRight.setY(yy);
        }
        time++;
    }



    public void update() {
        if (time < 192 && time > 96){
            explode();
        } else {
            time++;
            flameLeft.setX(-100);
            flameLeft.setY(-100);
            flameDown.setX(-100);
            flameDown.setY(-100);
            flameUp.setX(-100);
            flameUp.setY(-100);
            flameRight.setX(-100);
            flameRight.setY(-100);
        }
    }
}
