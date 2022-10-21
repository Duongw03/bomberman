package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Enemy extends Entity{
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    public void enemyUp() {
        y -= Sprite.SCALED_SIZE/8;
    }
    public void enemyDown() {
        y += Sprite.SCALED_SIZE/8;
    }
    public void enemyLeft() {
        x -= Sprite.SCALED_SIZE / 8;
    }
    public void enemyRight() {
        x += Sprite.SCALED_SIZE / 8;
    }
    public void update() {

    }
}
