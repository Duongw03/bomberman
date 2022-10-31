package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    int time = 0;
    int v;
    int h;
    private int speed = 8;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void bombermanUp() {
        bomberman.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, Math.abs(v--), 16).getFxImage());
        if(bomberman.checkCollisionUp()) {
            bomberman.setY(y - Sprite.SCALED_SIZE / speed);
        }
    }
    public void bombermanDown() {
        bomberman.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, Math.abs(v++), 16).getFxImage());
        if(bomberman.checkCollisionDown()) {
            bomberman.setY(y + Sprite.SCALED_SIZE / speed);
        }
    }
    public void bombermanLeft() {
        bomberman.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, Math.abs(h--), 16).getFxImage());
        if (bomberman.checkCollisionLeft()) {
            bomberman.setX(x - Sprite.SCALED_SIZE / speed);
        }
    }
    public void bombermanRight() {
        bomberman.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, Math.abs(h++), 16).getFxImage());
        if (bomberman.checkCollisionRight()) {
            bomberman.setX(x + Sprite.SCALED_SIZE / speed);
        }
    }
    public boolean isCollisionLeft(Entity entity) {
        return this.x == entity.x + 32 && this.y > entity.y - 32 && this.y < entity.y + 32;
    }
    public boolean isCollisionRight(Entity entity) {
        return this.x == entity.x - 24 && this.y > entity.y - 32 && this.y < entity.y + 32;
    }
    public boolean isCollisionUp(Entity entity) {
        return this.y == entity.y + 32 && this.x > entity.x - 24 && this.x < entity.x + 32;
    }
    public boolean isCollisionDown(Entity entity) {
        return this.y == entity.y - 32 && this.x > entity.x - 24 && this.x < entity.x + 32;
    }
    public boolean collisionEnemy() {
        boolean check = true;
        for(int i = 0; i < BombermanGame.enemies.size(); i++) {
            Enemy enemy = BombermanGame.enemies.get(i);
            if(isCollision(enemy)) {
                check = false;
            }
        }
        return check;
    }

    public void bombermanDie() {
        bomberman.setAlive(false);
        Bomber.bomberman.setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, time, 32).getFxImage());
    }


    @Override
    public void update() {
        if (!collisionEnemy()) {
            time++;
            bombermanDie();
            speed = 4;
            SpeedUp.isSpeedUp = false;
        }
        if(SpeedUp.isSpeedUp) {
            speed = 4;
        }
    }
}