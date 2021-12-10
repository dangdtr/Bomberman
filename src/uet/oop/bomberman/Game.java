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
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;
import uet.oop.bomberman.modules.Keyboard;

import java.io.IOException;
import java.util.*;


public class Game extends Application {

	public static final int WIDTH = 20;
	public static final int HEIGHT = 15;
	private GraphicsContext gc;
	private Canvas canvas;

	public static int LENGTH_OF_FLAME = 1;
	private static int NUMBER_OF_BOMBS = 1;


	public static List<AnimatedEntitiy> entityList = new ArrayList<>();
	public static List<Entity> stillObjects = new ArrayList<>();

	/**
	 * Chứa nhiều entity tại 1 cùng ví trị.
	 * Ví dụ: Tại vị trí (x,y): Grass, Item, Brick.
	 */
	public static Map<Integer, Stack<Entity>> LayeredEntity = new HashMap<>();

	private Bomber bomberman;
	public static List<Bomb> bombList = new ArrayList<Bomb>();


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

		stage.setTitle("BOMBERMAN GAME");

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
				Keyboard.setInputKeyEvent(event);
				if (Keyboard.SPACE && NUMBER_OF_BOMBS != 0) {
					System.out.println("create");
					bomberman = getBomber();
					int tmpX = Math.round((bomberman.getX() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE);
					int tmpY = Math.round((bomberman.getY() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE);// Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
					Bomb bomb = new Bomb(tmpX, tmpY, Sprite.bomb.getFxImage());
					bombList.add(bomb);

					NUMBER_OF_BOMBS--;
				}
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				Keyboard.setInputKeyEvent(event);
			}
		});
		GameMap.createMap();
	}


	//===================================update area ===================================//
	public void update() throws IOException {
		entitiesUpdate();
		bombUpdate();
		itemUpdate();
	}

	private void entitiesUpdate() throws IOException {
		if (!entityList.isEmpty()) {
			for (AnimatedEntitiy animatedEntitiy : entityList) {
				if (animatedEntitiy != null && !(animatedEntitiy instanceof Bomber)) {
					animatedEntitiy.update();
				}
			}
			getBomber().update();
		}
	}

	private void itemUpdate() {
		if (!LayeredEntity.isEmpty()) {
			for (Integer value : getLayeredEntitySet()) {
				if (LayeredEntity.get(value).peek() instanceof FlameItem
						&& Collisions.checkCollision(getBomber(), LayeredEntity.get(value).peek())) {
					Game.LENGTH_OF_FLAME++;
					LayeredEntity.get(value).pop();
					System.out.println("pop");
					FlameItem.timeItem = 0;
					FlameItem.isPickUp = true;
				}
				if (LayeredEntity.get(value).peek() instanceof SpeedItem
						&& Collisions.checkCollision(getBomber(), LayeredEntity.get(value).peek())) {
					LayeredEntity.get(value).pop();
					Bomber.setVELOCITY(2);
					SpeedItem.timeItem = 0;
					SpeedItem.isPickUp = true;
				}
				if (LayeredEntity.get(value).peek() instanceof BombItem
						&& Collisions.checkCollision(getBomber(), LayeredEntity.get(value).peek())) {
					LayeredEntity.get(value).pop();
					NUMBER_OF_BOMBS = 2;
					BombItem.timeItem = 0;
					BombItem.isPickUp = true;
				}
			}
			if (FlameItem.isPickUp) {
				FlameItem.timeItem++;
//                System.out.println(FlameItem.timeItem);
				if (FlameItem.timeItem > 2000) {
					FlameItem.timeItem = 0;
					Game.LENGTH_OF_FLAME--;
//                    System.out.println(Flame.lenOfFlame);

					FlameItem.isPickUp = false;
				}
			}
			if (SpeedItem.isPickUp) {
				SpeedItem.timeItem++;
//                System.out.println(SpeedItem.timeItem);
				if (SpeedItem.timeItem > 2000) {
					SpeedItem.timeItem = 0;
					Bomber.setVELOCITY(1);
					SpeedItem.isPickUp = false;
				}
			}
			if (BombItem.isPickUp) {
				BombItem.timeItem++;
				if (BombItem.timeItem > 2000) {
					BombItem.timeItem = 0;
					NUMBER_OF_BOMBS = 1;
					BombItem.isPickUp = false;
				}
			}

		}
	}

	/**
	 * Câp nhật về bomb:
	 * - Bomb nổ hay chưa?
	 * - Nổ vào brick thì thế nào -> cập nhật brick
	 * - Nổ vào người, nổ vào quái.
	 */
	private void bombUpdate() {
		for (Bomb bomb : bombList) {
			//bomb = GameMap.getBomber();
			if (bomb != null) {
//            System.out.println(numberOfBomb);
				bomb.update();
				if (!bomb._destroyed && bomb._exploding) {
					for (int i = 0; i < bomb.getFlameList().size(); i++) {
						bomb.getFlameList().get(i).update();
						int xFlame = bomb.getFlameList().get(i).getX() / Sprite.SCALED_SIZE;
						int yFlame = bomb.getFlameList().get(i).getY() / Sprite.SCALED_SIZE;
						if (LayeredEntity.containsKey(generateKey(xFlame, yFlame))) {
							Stack<Entity> tiles = LayeredEntity.get(generateKey(xFlame, yFlame));
							if (tiles.peek() instanceof Brick) {
								Brick brick = (Brick) tiles.peek();
								brick.setExploded(true);
								brick.update();
								if (brick._destroyed) {
									System.out.println("___");
									tiles.pop();
								}
							}
						}
					}
				}
				if (bomb._destroyed && NUMBER_OF_BOMBS < 1) {
					NUMBER_OF_BOMBS++;
				}

			}
		}
	}

	//=================================== Render area ===================================//

	public void render() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Entity stillObject : stillObjects) {
			stillObject.render(gc);
		}
		if (!LayeredEntity.isEmpty()) {
			for (Integer value : getLayeredEntitySet()) {
				LayeredEntity.get(value).peek().render(gc);
			}
		}
		for (AnimatedEntitiy g : entityList) {
			g.render(gc);
		}
		bombRender();
	}

	/**
	 * Render bomb cùng các flame khi nổ.
	 */
	private void bombRender() {
		for (Bomb bomb : bombList) {
			if (bomb != null) {
				if (!bomb._destroyed) {
					bomb.render(gc);
				}
				if (!bomb._destroyed && bomb._exploding) {
					for (int i = 0; i < bomb.getFlameList().size(); i++) {
						bomb.getFlameList().get(i).render(gc);
					}
				}
			}
		}
		if (bombList.size() == 0) {
			bombList = new ArrayList<Bomb>();
		}
	}

	/**
	 * Tạo key cho bảng băm LayeredEntity.
	 *
	 * @param x vị trí x cần xét.
	 * @param y vị trí y cần xét.
	 * @return key.
	 */
	public static int generateKey(int x, int y) {
		return x * 100 + y;
	}


	//===================================Getter & Setter area ===================================//
	public static Set<Integer> getLayeredEntitySet() {
		return Game.LayeredEntity.keySet();
	}

	public static List<Bomb> getBomb() {
		return bombList;
	}

	public static Entity getEntityAt(int x, int y) {
		Entity entity = null;
		for (Entity e : Game.entityList) {
			if (e.getX() == x && e.getY() == y) {
				entity = e;
			}
		}
		return entity;
	}

	public static Enemy getEnemy() {
		Iterator<AnimatedEntitiy> itr = Game.entityList.iterator();
		AnimatedEntitiy cur;
		while (itr.hasNext()) {
			cur = itr.next();
			if (cur instanceof Enemy) {
				return (Enemy) cur;
			}
		}
		return null;
	}

	public static Bomber getBomber() {
		Iterator<AnimatedEntitiy> itr = Game.entityList.iterator();

		AnimatedEntitiy cur;
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
		if (Game.LayeredEntity.containsKey(generateKey(_x, _y))) {
			if (Game.LayeredEntity.get(generateKey(_x, _y)).peek() instanceof Item) {
				cur = (Item) Game.LayeredEntity.get(generateKey(_x, _y)).peek();
				return cur;
			}
		}
		return null;
	}
}
