package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamics.bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;
import uet.oop.bomberman.modules.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private final List<Entity> entities = new ArrayList<>();

    //    private final List<Entity> stillObjects = new ArrayList<>();
    // tạm thời để public static
    public static final List<Entity> stillObjects = new ArrayList<>();


    private final Keyboard keyboard = new Keyboard();// = new Keyboard();

    private Bomber bomberman;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        stage.setTitle("Tutorial");

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        keyboard.setInputKeyEvent(event);
                    }
                });

                scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        keyboard.setInputKeyEvent(event);
                    }
                });

                update();
                render();
            }
        };
        timer.start();

        GameMap.createMap();


        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    }

//    public void createMap() {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                } else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }
//    }
//
//    public void createMap(int level) throws IOException {
//        // xu li file
//        String path = String.format("res/levels/Level%d.txt", level);
//        Path filePath = Paths.get(path);
//        Scanner reader = new Scanner(filePath);
//        List<Integer> integers = new ArrayList<>();
//
//        // doc du lieu tung dong
//        int line = 1;
//        int levels = 0, row = 0, col = 0;
//
//        // xu li dong dau tien
//        char[] data = new char[3];
//        while (line == 1) {
//            levels = Integer.parseInt(reader.next());
//            row = Integer.parseInt(reader.next());
//            col = Integer.parseInt(reader.next());
//            line++;
//        }
//
//        // xu li n dong tiep theo
//        char[][] map = new char[row][col];
//        int i = -1;
//        while (reader.hasNext()) {
//            String line1 = reader.nextLine();
//            for (int t = 0; t < line1.length(); t++) {
//                Entity object;
//                if (line1.charAt(t) == '#') {
//                    object = new Wall(t, i, Sprite.wall.getFxImage());
//                } else if (line1.charAt(t) == '*') {
//                    object = new Brick(t, i, Sprite.brick.getFxImage());
//                } else {
//                    object = new Grass(t, i, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//            i++;
//        }
//    }

    public void update() {
        entities.forEach(Entity::update);

        bomberman.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomberman.render(gc);
    }
}
