package uet.oop.bomberman.maps;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.entities.statics.Wall;
import uet.oop.bomberman.entities.statics.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class GameMap {
    private static int gameLevel = 1;
    private static char[][] map;// = new char[BombermanGame.HEIGHT][BombermanGame.HEIGHT];
    private static int heightMap;
    private static int widthMap;

    // cheat qua
//    private static final char[][] map = new char[13][31];

    public static int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        GameMap.gameLevel = gameLevel;
    }

    public static void createMap() throws IOException {
        System.out.println("__");
        //TODO: cập nhật level rồi tạo
        createMap(getGameLevel());
    }

    public static void createMap(int gameLevel) throws IOException {
        fileLoad(gameLevel);
        for (int i = 0; i < heightMap; i++) {
            for (int j = 0; j < widthMap; j++) {
//                System.out.print(map[i][j]);
                char c = map[i][j];
                Entity obj;
                switch (c) {
                    case '#':
                        obj = new Wall(j, i, Sprite.wall.getFxImage());
                        BombermanGame.stillObjects.add(obj);
                        break;
                    case '*':
                        obj = new Brick(j, i, Sprite.brick.getFxImage());
//                        BombermanGame.stillObjects.add(obj);
//                        BombermanGame.brickList.put(generateKey(j, i), obj);

                        BombermanGame.stillObjects.add( new Grass(j, i, Sprite.grass.getFxImage()));
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
        heightMap = reader.nextInt(); // 13 // x
        widthMap = reader.nextInt();  // 31 // y

        map = new char[heightMap][widthMap];
        reader.nextLine();

        for (int i = 0; i < heightMap; i++) {
            String line = reader.nextLine();
            for (int j = 0; j < widthMap; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        reader.close();
        filePath.close();
    }

    // support for hashtable brickList
//    public static int generateKey(int x, int y) {
//        return x * 100 + y;
//    }
//    public static Set<Integer> getBrickSet() {
//        return BombermanGame.brickList.keySet();
//    }

    public static char[][] getMap() {
        return map;
    }
}
