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
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static String map = "###############################" +
            "#p     ** *  1 * 2 *  * * *   #" +
            "# # # #*# # #*#*# # # #*#*#*# #" +
            "#  x*     ***  *  1   * 2 * * #" +
            "# # # # # #*# # #*#*# # # # #*#" +
            "#f         x **  *  *   1     #" +
            "# # # # # # # # # #*# #*# # # #" +
            "#*  *      *  *      *        #" +
            "# # # # #*# # # #*#*# # # # # #" +
            "#*    **  *       *           #" +
            "# #*# # # # # # #*# # # # # # #" +
            "#           *   *  *          #" +
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
    private final List<Entity> entities = new ArrayList<>();
    private final List<Entity> stillObjects = new ArrayList<>();
    //private static boolean gameStarted = false;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
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


        stage.setTitle("BombermanGame");
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        timeline.play();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
//                if (gameStarted) {
//                    entities.add(Bomber.bomberman);
//                }
            }
        };
        timer.start();

        createMap();

        Entity bomb = new Bomber(-1,-1,Sprite.bomb.getFxImage());
        entities.add(bomb);
        entities.add(Bomber.bomberman);

            scene.setOnKeyPressed(new EventHandler<>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    int x = Bomber.bomberman.getX();
                    int y = Bomber.bomberman.getY();
                    boolean check = true;
                    if (keyEvent.getCode() == KeyCode.UP && y > 1 * Sprite.SCALED_SIZE) {
                        for(int i = 0;i < map.length();i++) {
                            Entity entity = stillObjects.get(i);
                            if((map.charAt(i) == '#' || map.charAt(i) == '*') && Bomber.bomberman.isCollisionUp(entity)) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            Bomber.bomberman.bombermanUp();
                        }
                    } else if (keyEvent.getCode() == KeyCode.DOWN && y < 11 * Sprite.SCALED_SIZE) {
                        for(int i = 0;i < map.length();i++) {
                            Entity entity = stillObjects.get(i);
                            if((map.charAt(i) == '#' || map.charAt(i) == '*') && Bomber.bomberman.isCollisionDown(entity)) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            Bomber.bomberman.bombermanDown();
                        }
                    } else if (keyEvent.getCode() == KeyCode.LEFT && x > 1 * Sprite.SCALED_SIZE) {
                        for(int i = 0;i < map.length();i++) {
                            Entity entity = stillObjects.get(i);
                            if((map.charAt(i) == '#' || map.charAt(i) == '*') && Bomber.bomberman.isCollisionLeft(entity)) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            Bomber.bomberman.bombermanLeft();
                        }
                    } else if (keyEvent.getCode() == KeyCode.RIGHT && x < 29 * Sprite.SCALED_SIZE) {
                        for(int i = 0;i < map.length();i++) {
                            Entity entity = stillObjects.get(i);
                            if((map.charAt(i) == '#' || map.charAt(i) == '*') && Bomber.bomberman.isCollisionRight(entity)) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            Bomber.bomberman.bombermanRight();
                        }
                    } else if (keyEvent.getCode() == KeyCode.SPACE) {
                        bomb.setX(x/32*32);
                        bomb.setY(y/32*32);
                        bomb.setImg(Sprite.bomb.getFxImage());
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
                    Entity balloon = new Bomber(k, i, Sprite.balloom_left1.getFxImage());
                    entities.add(balloon);
                }
                if (map_real[i][k] == '2') {
                    Entity oneal = new Bomber(k, i, Sprite.oneal_left1.getFxImage());
                    entities.add(oneal);
                }
                if (map_real[i][k] == 'f') {
                    Entity flame_item = new Bomber(k, i, Sprite.powerup_flames.getFxImage());
                    entities.add(flame_item);
                }
            }
        }

    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}