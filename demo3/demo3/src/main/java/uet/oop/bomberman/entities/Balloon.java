package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends Enemy {
    private int direction;
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }
    int random = 0;
    int h = 0;

    public void balloonLeft() {
        enemyLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, h++, 16).getFxImage();
    }
    public void balloonRight() {
        enemyRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, h++, 16).getFxImage();
    }

    public void randomDirection() {
        Random random = new Random();
        direction = random.nextInt(2);
    }


    public void update() {
        if(random % 32 == 0) {
            randomDirection();
        }
        if(random % 4 == 0) {
            if (direction == 0 && this.checkCollisionLeft()) {
                balloonLeft();
            }
            if (direction == 1 && this.checkCollisionRight()) {
                balloonRight();
            }
        }
        random++;
    }
}
