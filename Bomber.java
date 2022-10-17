package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    int v = 0;
    int h = 0;
    public void bombermanUp() {
        if (v % 12 == 1 || v % 12 == 2 || v % 12 == 3 || v % 12 == 4) {
            bomberman.setImg(Sprite.player_up.getFxImage());
        } else if (v % 12 == 5 || v % 12 == 6 || v % 12 == 7 || v % 12 == 8) {
            bomberman.setImg(Sprite.player_up_1.getFxImage());
        } else if (v % 12 == 9 || v % 12 == 10 || v % 12 == 11 || v % 12 == 0) {
            bomberman.setImg(Sprite.player_up_2.getFxImage());
        }
        v--;
        bomberman.setY(y - Sprite.SCALED_SIZE / 8);
    }
    public void bombermanDown() {
        if (v % 12 == 1 || v % 12 == 2 || v % 12 == 3 || v % 12 == 4) {
            bomberman.setImg(Sprite.player_down.getFxImage());
        } else if (v % 12 == 5 || v % 12 == 6 || v % 12 == 7 || v % 12 == 8) {
            bomberman.setImg(Sprite.player_down_1.getFxImage());
        } else if (v % 12 == 9 || v % 12 == 10 || v % 12 == 11 || v % 12 == 0) {
            bomberman.setImg(Sprite.player_down_2.getFxImage());
        }
        v++;
        bomberman.setY(y + Sprite.SCALED_SIZE / 8);
    }
    public void bombermanLeft() {
        if (h % 12 == 1 || h % 12 == 2 || h % 12 == 3 || h % 12 == 4) {
            bomberman.setImg(Sprite.player_left.getFxImage());
        } else if (h % 12 == 5 || h % 12 == 6 || h % 12 == 7 || h % 12 == 8) {
            bomberman.setImg(Sprite.player_left_1.getFxImage());
        } else if (h % 12 == 9 || h % 12 == 10 || h % 12 == 11 || h % 12 == 0) {
            bomberman.setImg(Sprite.player_left_2.getFxImage());
        }
        h--;
        bomberman.setX(x - Sprite.SCALED_SIZE / 8);
    }
    public void bombermanRight() {
        if (h % 12 == 1 || h % 12 == 2 || h % 12 == 3 || h % 12 == 4) {
            bomberman.setImg(Sprite.player_right.getFxImage());
        } else if (h % 12 == 5 || h % 12 == 6 || h % 12 == 7 || h % 12 == 8) {
            bomberman.setImg(Sprite.player_right_1.getFxImage());
        } else if (h % 12 == 9 || h % 12 == 10 || h % 12 == 11 || h % 12 == 0) {
            bomberman.setImg(Sprite.player_right_2.getFxImage());
        }
        h++;
        bomberman.setX(x + Sprite.SCALED_SIZE / 8);
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

    @Override
    public void update() {

    }
}