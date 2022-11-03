package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends Enemy{
    private int direction;
    public Doll(int x, int y, Image img) {
        super(x, y, img);
    }
    int random;
    int h = 0;
    public void dollLeft() {
        x -= Sprite.SCALED_SIZE;
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, h++, 16).getFxImage();
    }
    public void dollRight() {
        x += Sprite.SCALED_SIZE;
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, h++, 16).getFxImage();
    }
    public void dollUp() {
        y -= Sprite.SCALED_SIZE;
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, h++, 16).getFxImage();
    }
    public void dollDown() {
        y += Sprite.SCALED_SIZE;
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, h++, 16).getFxImage();
    }

    public void randomDirection() {
        Random random = new Random();
        direction = random.nextInt(4);
    }
    public void update() {
        if(random % 64 == 0) {
            randomDirection();
        }
        if(random % 16 == 0) {
            if (direction == 0 && this.checkCollisionLeft()) {
                dollLeft();
            }
            if (direction == 1 && this.checkCollisionRight()) {
                dollRight();
            }
            if (direction == 2 && this.checkCollisionUp()) {
                dollUp();
            }
            if (direction == 3 && this.checkCollisionDown()) {
                dollDown();
            }
        }
        random++;
    }
}
