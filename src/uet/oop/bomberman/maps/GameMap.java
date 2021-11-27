package uet.oop.bomberman.maps;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.entities.statics.Wall;
import uet.oop.bomberman.entities.statics.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMap {
    private static int gameLevel = 1;
    // cheat qua
    public static char[][] map;
    private static int height = 20;
    private static int width = 20;

    public static int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        GameMap.gameLevel = gameLevel;
    }

    public static void createMap() throws IOException {
        //TODO: cập nhật level rồi tạo
        createMap(getGameLevel());
    }

    public static void createMap(int gameLevel) throws IOException {
        fileLoad(gameLevel);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char c = map[i][j];
                Entity obj;
                switch (c) {
                    case '#':
                        obj = new Wall(j, i, Sprite.wall.getFxImage());
                        BombermanGame.stillObjects.add(obj);
                        break;
                    case '*':
                        obj = new Brick(j, i, Sprite.brick.getFxImage());
                        BombermanGame.stillObjects.add(obj);
                        break;
                    default:
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        BombermanGame.stillObjects.add(obj);
                        break;
                }
            }
        }

    }

    public static void fileLoad(int gameLevel) throws IOException {
        String path = String.format("res/levels/Level%d.txt", gameLevel);
        FileReader filePath = new FileReader(path);
        Scanner reader = new Scanner(filePath);

        int level = reader.nextInt();
        height = reader.nextInt();
        width = reader.nextInt();

        map = new char[height][width];
        reader.nextLine();

        for (int i = 0; i < height; i++) {
            String line = reader.nextLine();
            for (int j = 0; j < width; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        reader.close();
        filePath.close();
    }

    public static Entity getEntity(int x, int y) {

        return null;
    }
}
