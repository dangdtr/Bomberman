package uet.oop.bomberman.maps;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.statics.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.entities.statics.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    public void createMap(int level) throws IOException {
        // xu li file
        String path = String.format("res/levels/Level%d.txt", level);
        Path filePath = Paths.get(path);
        Scanner reader = new Scanner(filePath);
        List<Integer> integers = new ArrayList<>();

        // doc du lieu tung dong
        int line = 1;
        int levels = 0, row = 0, col = 0;

        // xu li dong dau tien
        char[] data = new char[3];
        while (line == 1) {
            levels = Integer.parseInt(reader.next());
            row = Integer.parseInt(reader.next());
            col = Integer.parseInt(reader.next());
            line++;
        }

        // xu li n dong tiep theo
        char[][] map = new char[row][col];
        int i = -1;
        while (reader.hasNext()) {
            String line1 = reader.nextLine();
            for (int t = 0; t < line1.length(); t++) {
                Entity object;
                if (line1.charAt(t) == '#') {
                    object = new Wall(t, i, Sprite.wall.getFxImage());
                } else if (line1.charAt(t) == '*') {
                    System.out.println();
                    object = new Brick(t, i, Sprite.brick.getFxImage());
                } else {
                    object = new Grass(t, i, Sprite.grass.getFxImage());
                }
                BombermanGame.stillObjects.add(object);
            }
            i++;
        }
    }
}
