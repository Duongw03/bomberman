package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    int time = 0;
    public boolean canBomb = true;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static Bomb bomb = new Bomb(-1, -1, Sprite.bomb.getFxImage());

    public void placeBomb() {
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, time, 32).getFxImage();
    }

    public void explode() {
        img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, time, 32).getFxImage();
    }

    public void collisionBrick() {
        int brick = 0;
        for (int i = 0; i < BombermanGame.stillObjects.size(); i++) {
            if (BombermanGame.map.charAt(i) == '*') {
                brick++;
                if(isCollision(BombermanGame.stillObjects.get(i))) {
                    BombermanGame.stillObjects.get(i).setImg(Sprite.grass.getFxImage());
                    String newMap = BombermanGame.map.substring(0, i) + ' ' + BombermanGame.map.substring(i + 1);
                    BombermanGame.map = newMap;
                    if (brick == 9) {
                        Portal.portal.setX(BombermanGame.stillObjects.get(i).getX());
                        Portal.portal.setY(BombermanGame.stillObjects.get(i).getY());
                    }
                }
            }
        }
    }


    public void bombCollisionEnemy() {
        for (int i = 0; i < BombermanGame.enemies.size(); i++) {
            Enemy e = BombermanGame.enemies.get(i);
            if (entityDie(e)) {
                BombermanGame.enemies.remove(BombermanGame.enemies.get(i));
            }
        }
    }

    public void bombKillBomberman() {
        if (this.entityDie(Bomber.bomberman)) {
            Bomber.bomberman.setAlive(false);
            Bomber.bomberman.setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, time, 32).getFxImage());
        }
    }

    public void update() {
        if (time < 96) {
            canBomb = false;
            placeBomb();
            time++;
        } else if (time < 192) {
            canBomb = false;
            explode();
            collisionBrick();
            bombCollisionEnemy();
            bombKillBomberman();
            time++;
        } else {
            if (!Bomber.bomberman.isAlive()) {
                Bomber.bomberman.setX(32);
                Bomber.bomberman.setY(32);
                Bomber.bomberman.setImg(Sprite.player_right.getFxImage());
            }
            Bomber.bomberman.setAlive(true);
            bomb.setX(-100);
            bomb.setY(-100);
            bomb.setImg(Sprite.bomb.getFxImage());
            canBomb = true;
        }
    }
}
