package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    int v = 0;
    int h = 0;
    public void bombermanUp() {
        bomberman.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, v--, 16).getFxImage());
        bomberman.setY(y - Sprite.SCALED_SIZE / 8);
    }
    public void bombermanDown() {
        bomberman.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, v++, 16).getFxImage());
        bomberman.setY(y + Sprite.SCALED_SIZE / 8);
    }
    public void bombermanLeft() {
        bomberman.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, h--, 16).getFxImage());
        bomberman.setX(x - Sprite.SCALED_SIZE / 8);
    }
    public void bombermanRight() {
        bomberman.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, h++, 16).getFxImage());
        bomberman.setX(x + Sprite.SCALED_SIZE / 8);
    }
    public boolean collisionEnemy() {
        boolean check = true;
        for(int i = 0; i < BombermanGame.map.length(); i++) {
            Entity entity = BombermanGame.entities.get(i);
            if(bomberman.isCollisionUp(entity) || bomberman.isCollisionDown(entity)
                || bomberman.isCollisionLeft(entity) || bomberman.isCollisionRight(entity)) {
                check = false;
            }
        }
        return check;
    }


    @Override
    public void update() {

    }
}