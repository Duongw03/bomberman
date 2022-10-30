package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy{
    private int direction;
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }
    int h;
    int random;

    public void onealLeft() {
        enemyLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, h++, 16).getFxImage();
    }
    public void onealRight() {
        enemyRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, h++, 16).getFxImage();
    }
    public void onealUp() {
        enemyUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, h++, 16).getFxImage();
    }
    public void onealDown() {
        enemyDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, h++, 16).getFxImage();
    }
    public double distanceToBomberman() {
        return Math.sqrt(Math.pow(Bomber.bomberman.x-this.x,2) +Math.pow(Bomber.bomberman.y-this.y,2));
    }
    public void chooseDirection() {
        if(this.x < Bomber.bomberman.x) {
            if(Bomber.bomberman.x-this.x<Math.abs(Bomber.bomberman.y-this.y)) direction=3;
            else direction=4;
        } else if(this.x >= Bomber.bomberman.x) {
            if(this.x-Bomber.bomberman.x<Math.abs(this.y-Bomber.bomberman.y)) direction=1;
            else direction=2;
        }
    }
    public void randomDirection() {
        Random random = new Random();
        direction = random.nextInt(4) + 1;
        if((direction == 1 && !this.checkCollisionUp()) ||
                (direction == 2 && !this.checkCollisionLeft()) ||
                (direction == 3 && !this.checkCollisionDown()) ||
                (direction == 4 && !this.checkCollisionRight())) {
            direction = random.nextInt(4) + 1;
        }
    }
    public void isInRange() {
        if (distanceToBomberman() < 160) {
            chooseDirection();
            if((direction == 1 && !this.checkCollisionUp()) ||
                    (direction == 2 && !this.checkCollisionLeft()) ||
                    (direction == 3 && !this.checkCollisionDown()) ||
                    (direction == 4 && !this.checkCollisionRight())) {
                randomDirection();
            }
        } else {
            randomDirection();
        }
    }
    public void update() {
        if(random % 32 == 0) {
            isInRange();
        }
        if(random % 4 == 0) {
            if (direction == 1 && this.checkCollisionUp()) {
                onealUp();
            }
            if (direction == 2 && this.checkCollisionLeft()) {
                onealLeft();
            }
            if(direction == 3 && this.checkCollisionDown()) {
                onealDown();
            }
            if(direction == 4 && this.checkCollisionRight()) {
                onealRight();
            }
        }
        random++;
    }
}
