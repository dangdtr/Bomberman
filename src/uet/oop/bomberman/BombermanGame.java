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
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamics.DynamicEntity;
import uet.oop.bomberman.entities.dynamics.bomb.Bomb;
import uet.oop.bomberman.entities.dynamics.bomb.Flame;
import uet.oop.bomberman.entities.dynamics.bomber.Bomber;
import uet.oop.bomberman.entities.statics.Tile;
import uet.oop.bomberman.entities.statics.destroyable.Brick;
import uet.oop.bomberman.entities.statics.item.FlameItem;
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

    //---------------// temp var to megre
    private Bomber bomberman;
    private static
    Bomb bomb;
    private int numberOfBomb = 1;
    //---------------//

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
                if (Keyboard.SPACE && numberOfBomb != 0) {
                    System.out.println("create");
                    bomberman = GameMap.getBomber();
                    int tmpX = Math.round((bomberman.getX() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE);
                    int tmpY = Math.round((bomberman.getY() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE);// Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
                    bomb = new Bomb(tmpX, tmpY, Sprite.bomb.getFxImage());
//              entities.add(bomb);
                    numberOfBomb--;
                }            }
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
        bombUpdate();
        itemUpdate();
    }

    private void itemUpdate() {
        if (!brickList.isEmpty()) {
            for (Integer value : GameMap.getBrickSet()) {
                if (brickList.get(value).peek() instanceof FlameItem && Collisions.checkCollision(getBomber(),brickList.get(value).peek())) {
                    Flame.lenOfFlame++;
                    brickList.get(value).pop();
                    FlameItem.timeItem = 0;
                    FlameItem.isPickUp = true;
                }
            }
            if (FlameItem.isPickUp){
                FlameItem.timeItem++;
                System.out.println(FlameItem.timeItem);
                if (FlameItem.timeItem > 2000) {
                    FlameItem.timeItem = 0;
                    Flame.lenOfFlame--;
                    FlameItem.isPickUp = false;

                }
            }

        }
    }

    public static Bomb getBomb() {
        return bomb;
    }

    private void bombUpdate() {
        //bomb = GameMap.getBomber();
        if (bomb != null) {
//            System.out.println(numberOfBomb);

            bomb.update();
            if (!bomb.done && bomb.explosion) {
                for (int i = 0; i < bomb.flameList.size(); i++) {
                    bomb.flameList.get(i).update();
                    int xFlame = bomb.flameList.get(i).getX() / Sprite.SCALED_SIZE;
                    int yFlame = bomb.flameList.get(i).getY() / Sprite.SCALED_SIZE;
                    if(brickList.containsKey(GameMap.generateKey(xFlame, yFlame))) {
                        Stack<Entity> tiles = brickList.get(GameMap.generateKey(xFlame, yFlame));
                        if (tiles.peek() instanceof Brick) {
                            Brick brick = (Brick) tiles.peek();
                            brick.setExploded(true);
                            brick.update();
                            if (brick.done) {
                                System.out.println("___");
                                tiles.pop();
                            }
                        }
                    }
                }
            }
            if (bomb.done && numberOfBomb < 1){
                numberOfBomb++;
            }

        }

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Entity stillObject : stillObjects) {
            stillObject.render(gc);
        }
        if (!brickList.isEmpty()) {
            for (Integer value : GameMap.getBrickSet()) {
                brickList.get(value).peek().render(gc);
            }
        }
        for (DynamicEntity g : entityList) {
            g.render(gc);
        }
        bombRender();

    }

    private void bombRender() {
        if (bomb != null) {
            if (!bomb.done) {
                bomb.render(gc);
            }
            if (!bomb.done && bomb.explosion) {
                for (int i = 0; i < bomb.flameList.size(); i++) {
                    bomb.flameList.get(i).render(gc);
                }
            }
        }
    }
}
