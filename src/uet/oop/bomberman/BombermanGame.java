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
import uet.oop.bomberman.entities.dynamics.DynamicEntity;
import uet.oop.bomberman.entities.dynamics.bomb.Bomb;
import uet.oop.bomberman.entities.dynamics.bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;
import uet.oop.bomberman.modules.Keyboard;

import java.io.IOException;
import java.util.*;

import static uet.oop.bomberman.maps.GameMap.*;


public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    private GraphicsContext gc;
    private Canvas canvas;

    private final Keyboard keyboard = new Keyboard();

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
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                render();
            }
        };
        timer.start();


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
        GameMap.createMap();
    }


    public void update() throws IOException {
        if (!stillObjects.isEmpty()) {
            for (Entity stillObject : stillObjects) {
                if (stillObject != null) {
                    stillObject.update();
                }
            }
        }
        if (!entityList.isEmpty()) {
            for (DynamicEntity dynamicEntity : entityList) {
                if (dynamicEntity != null) {
                    dynamicEntity.update();
                }
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Entity stillObject : stillObjects) {
            stillObject.render(gc);
        }
        for (DynamicEntity g : entityList) {
            g.render(gc);
        }
        if (!brickList.isEmpty()) {
            for (Integer value : GameMap.getBrickSet()) {
                brickList.get(value).render(gc);
            }
        }
    }
}
