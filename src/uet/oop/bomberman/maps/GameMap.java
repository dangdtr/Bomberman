package uet.oop.bomberman.maps;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamics.DynamicEntity;
import uet.oop.bomberman.entities.dynamics.bomber.Bomber;
import uet.oop.bomberman.entities.dynamics.enemy.Balloom;
import uet.oop.bomberman.entities.dynamics.enemy.Enemy;
import uet.oop.bomberman.entities.dynamics.enemy.Oneal;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.entities.statics.Tile;
import uet.oop.bomberman.entities.statics.Wall;
import uet.oop.bomberman.entities.statics.destroyable.Brick;
import uet.oop.bomberman.entities.statics.item.FlameItem;
import uet.oop.bomberman.entities.statics.item.Item;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameMap {
    private static int gameLevel = 1;
    private static char[][] map;// = new char[BombermanGame.HEIGHT][BombermanGame.HEIGHT];
    private static int heightMap;
    private static int widthMap;
    public static List<DynamicEntity> entityList = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static Map<Integer, Stack<Tile>> brickList = new HashMap<>();

    // cheat qua
//    private static final char[][] map = new char[13][31];

    public static int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        GameMap.gameLevel = gameLevel;
    }

    public static int getHeightMap() {
        return heightMap;
    }
    public static int getWidthMap() {
        return widthMap;
    }
    public static void createMap() throws IOException {
        System.out.println("__");
        //TODO: cập nhật level rồi tạo
        createMap(getGameLevel());
    }

    public static void initMap() {
        entityList = new ArrayList<>();
        stillObjects = new ArrayList<>();
        brickList = new HashMap<>();
        map = new char[getHeightMap()][getWidthMap()];
    }

    public static void createMap(int gameLevel) throws IOException {
        fileLoad(gameLevel);
        for (int i = 0; i < heightMap; i++) {
            for (int j = 0; j < widthMap; j++) {
                char c = map[i][j];
                Entity obj;
                Stack<Tile> layer = new Stack<>();
                switch (c) {
                    case '#':
                        obj = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(obj);
                        break;
                    case '*':
                        layer.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        layer.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        brickList.put(generateKey(j, i), layer);
                        break;
                    case 'p':
                        Bomber bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        entityList.add(bomber);
                        stillObjects.add(obj);
                        break;
                    case 'f':
                        layer.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        layer.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        layer.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        brickList.put(generateKey(j,i), layer);
                        break;
                    case '1':
                        Balloom balloom = new Balloom(j, i, Sprite.oneal_right1.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        entityList.add(balloom);
                        stillObjects.add(obj);
                        break;
                    case '2':
                        Oneal oneal = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        entityList.add(oneal);
                        stillObjects.add(obj);
                        break;
                    default:
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(obj);
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

    // support for hashtable brickList// khá tệ
    public static int generateKey(int x, int y) {
        return x * 100 + y;
    }
    public static Set<Integer> getBrickSet() {
        return brickList.keySet();
    }
    //

    public static char[][] getMap() {
        return map;
    }

    public static Entity getEntityAt(int x, int y) {
        Entity entity = null;
        for (Entity e : entityList) {
            if (e.getX() == x && e.getY() == y) {
                entity = e;
            }
        }
        return entity;
    }

    public static Enemy getEnemy() {
        Iterator<DynamicEntity> itr = entityList.iterator();
        DynamicEntity cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur instanceof Enemy) {
                return (Enemy) cur;
            }
        }
        return null;
    }

    public static Bomber getBomber() {
        Iterator<DynamicEntity> itr = entityList.iterator();

        DynamicEntity cur;
        while (itr.hasNext()) {
            cur = itr.next();

            if (cur instanceof Bomber) {
                return (Bomber) cur;
            }
        }

        return null;
    }

    public static Item getItem(int _x, int _y) {
        Item cur;
        if (brickList.containsKey(generateKey(_x,_y))) {
            if (brickList.get(generateKey(_x, _y)).peek() instanceof Item) {
                cur = (Item) brickList.get(generateKey(_x, _y)).peek();
                return cur;
            }
        }
        return null;
    }
}