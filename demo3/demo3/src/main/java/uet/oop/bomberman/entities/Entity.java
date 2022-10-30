package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity{
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    protected boolean alive;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public boolean isCollisionLeft(Entity entity) {
        return this.x == entity.x + 32 && this.y > entity.y - 32 && this.y < entity.y + 32;
    }
    public boolean isCollisionRight(Entity entity) {
        return this.x == entity.x - 32 && this.y > entity.y - 32 && this.y < entity.y + 32;
    }
    public boolean isCollisionUp(Entity entity) {
        return this.y == entity.y + 32 && this.x > entity.x - 32 && this.x < entity.x + 32;
    }
    public boolean isCollisionDown(Entity entity) {
        return this.y == entity.y - 32 && this.x > entity.x - 32 && this.x < entity.x + 32;
    }
    public boolean isCollision(Entity entity) {
        return isCollisionRight(entity) || isCollisionUp(entity) || isCollisionDown(entity) || isCollisionLeft(entity);
    }
    public boolean checkCollisionUp() {
        boolean check = true;
        for(int i = 0;i < BombermanGame.map.length();i++) {
            Entity entity = BombermanGame.stillObjects.get(i);
            if((BombermanGame.map.charAt(i) == '#' || BombermanGame.map.charAt(i) == '*') && isCollisionUp(entity)) {
                check = false;
                break;
            }
        }
        return check;
    }
    public boolean checkCollisionDown() {
        boolean check = true;
        for(int i = 0;i < BombermanGame.map.length();i++) {
            Entity entity = BombermanGame.stillObjects.get(i);
            if((BombermanGame.map.charAt(i) == '#' || BombermanGame.map.charAt(i) == '*') && isCollisionDown(entity)) {
                check = false;
                break;
            }
        }
        return check;
    }
    public boolean checkCollisionLeft() {
        boolean check = true;
        for(int i = 0;i < BombermanGame.map.length();i++) {
            Entity entity = BombermanGame.stillObjects.get(i);
            if((BombermanGame.map.charAt(i) == '#' || BombermanGame.map.charAt(i) == '*') && isCollisionLeft(entity)) {
                check = false;
                break;
            }
        }
        return check;
    }
    public boolean checkCollisionRight() {
        boolean check = true;
        for(int i = 0;i < BombermanGame.map.length();i++) {
            Entity entity = BombermanGame.stillObjects.get(i);
            if((BombermanGame.map.charAt(i) == '#' || BombermanGame.map.charAt(i) == '*') && isCollisionRight(entity)) {
                check = false;
                break;
            }
        }
        return check;
    }

    public boolean entityDie(Entity entity) {
        return (this.x - 64 < entity.x && this.x + 64 > entity.x && this.y == entity.y)
                || (this.x - 32 < entity.x && this.x + 32 > entity.x && this.y - 32 < entity.y && this.y + 32 > entity.y)
                || (this.x == entity.x && this.y - 64 < entity.y && this.y + 64 > entity.y);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}