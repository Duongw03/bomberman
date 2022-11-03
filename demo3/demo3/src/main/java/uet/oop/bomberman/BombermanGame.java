package uet.oop.bomberman;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.Menu.MenuController;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.music.Sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static String map = "###############################" +
            "#p     ** * 1  *   *  * * *   #" +
            "# # # #*# # #*#*# # # #*#*#*# #" +
            "#  x*     ***1 *2     *   * * #" +
            "# # # # # #*# # #*#*# # # # #*#" +
            "#f         x ** 1*  *         #" +
            "# # # # # # # # # #*# #*# # # #" +
            "#*  *      *  * 1    *        #" +
            "# # # # #*# # # #*#*# # # # # #" +
            "#*    **  *       *           #" +
            "# #*# # # # # # #*# # # # # # #" +
            "#         3 *  g*  *          #" +
            "###############################";

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static char[][] map_real = new char[HEIGHT][WIDTH];

    static {
        int d = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                map_real[i][j] = map.charAt(d);
                d++;
            }
        }
    }

    private GraphicsContext gc;
    private Canvas canvas;
    public static final List<Entity> entities = new ArrayList<>();
    public static final List<Entity> stillObjects = new ArrayList<>();
    public static final List<Enemy> enemies = new ArrayList<>();
    public static final List<PowerUp> powerUps = new ArrayList<>();
    public static Stage stageLV1;
    public static Stage stageMenu;
    public static boolean winGame = false;

    //private static boolean gameStarted = false;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao stageMenu
        stageMenu = new Stage();
        MenuController menuController = new MenuController();
        menuController.RunMenu(stageMenu);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e ->render()));
        //canvas.setOnMouseClicked(e -> gameStarted = true);

        // Tao scene
        Scene scene = new Scene(root);


        // Them scene vao stage
        stageLV1 = new Stage();
        stageLV1.setTitle("BombermanGame");
        // Them scene vao stage
        stageLV1.setScene(scene);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                if (winGame) {
                    try {
                        MenuController.RunSceneWin();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();

        createMap();
        entities.add(Flame.flameUp);
        entities.add(Flame.flameDown);
        entities.add(Flame.flameLeft);
        entities.add(Flame.flameRight);
        entities.add(Bomb.bomb);
        entities.add(Portal.portal);
        entities.add(Bomber.bomberman);

        scene.setOnKeyPressed(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                int x = Bomber.bomberman.getX();
                int y = Bomber.bomberman.getY();
                if(Bomber.bomberman.isAlive()) {
                    if (keyEvent.getCode() == KeyCode.W) {
                        Bomber.bomberman.bombermanUp();
                    } else if (keyEvent.getCode() == KeyCode.S) {
                        Bomber.bomberman.bombermanDown();
                    } else if (keyEvent.getCode() == KeyCode.A) {
                        Bomber.bomberman.bombermanLeft();
                    } else if (keyEvent.getCode() == KeyCode.D) {
                        Bomber.bomberman.bombermanRight();
                    } else if (keyEvent.getCode() == KeyCode.SPACE && Bomb.bomb.canBomb) {
                        Sound.PlacedBomb();
                        Bomb.bomb.setTime(0);
                        Bomb.bomb.setX(x / 32 * 32);
                        Bomb.bomb.setY(y / 32 * 32);
                        Flame.flameUp.setTime(0);
                        Flame.flameDown.setTime(0);
                        Flame.flameLeft.setTime(0);
                        Flame.flameRight.setTime(0);
                    } else if (keyEvent.getCode() == KeyCode.M) {
                        Sound.onPlayAudio();
                    }
                }
            }
        });
    }

    public void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity object;
                if (map_real[i][j] == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (map_real[i][j] == '*') {
                    object = new Wall(j, i, Sprite.brick.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
            for (int k = 0; k < WIDTH; k++) {
                if (map_real[i][k] == '1') {
                    Enemy balloon = new Balloon(k, i, Sprite.balloom_left1.getFxImage());
                    enemies.add(balloon);
                }
                if (map_real[i][k] == '2') {
                    Enemy oneal = new Oneal(k, i, Sprite.oneal_left1.getFxImage());
                    enemies.add(oneal);
                }
                if (map_real[i][k] == '3') {
                    Enemy doll = new Doll(k, i, Sprite.doll_left1.getFxImage());
                    enemies.add(doll);
                }
                if (map_real[i][k] == 'f') {
                    PowerUp speed_item = new SpeedUp(k, i, Sprite.powerup_speed.getFxImage());
                    powerUps.add(speed_item);
                }
                if (map_real[i][k] == 'g') {
                    PowerUp wall_pass_item = new WallPass(k, i, Sprite.powerup_wallpass.getFxImage());
                    powerUps.add(wall_pass_item);
                }
            }
        }

    }

    public void update() {
        enemies.forEach(Enemy::update);
        entities.forEach(Entity::update);
        powerUps.forEach(PowerUp::update);
        if(enemies.size() == 0) {
            Portal.portal.setActivate(true);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        powerUps.forEach((g -> g.render(gc)));

    }
}