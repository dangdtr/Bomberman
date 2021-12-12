package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.info.InfoBar;
import uet.oop.bomberman.info.Score;
import uet.oop.bomberman.maps.GameMap;
import uet.oop.bomberman.menu.*;
import uet.oop.bomberman.modules.Keyboard;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.views.Camera;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.*;


public class Game extends Application {

	public static final int WIDTH = 20;
	public static final int HEIGHT = 13;
	public static int WIDTH_BUFFER = 0;

	private GraphicsContext gc;
	private Canvas canvas;

	public static int LENGTH_OF_FLAME = 1;
	public static int NUMBER_OF_BOMBS = 1;

	public static final int TIME_TO_DISAPPEAR = 100;
	public static final int TIME_TO_EXPLOSION_BOMB = 300; // 2s

	public static final int SPEED_OF_ENEMY = 1;

	public static List<AnimatedEntitiy> entityList = new ArrayList<>();
	public static List<Entity> stillObjects = new ArrayList<>();

	/**
	 * Chứa nhiều entity tại 1 cùng ví trị.
	 * Ví dụ: Tại vị trí (x,y): Grass, Item, Brick.
	 */
	public static Map<Integer, Stack<Entity>> LayeredEntity = new HashMap<>();
	public static List<Bomb> bombList = new ArrayList<Bomb>();

	private Bomber bomberman;
	private TextFlow textFlow = new TextFlow();
	private Score score = new Score();
	private InfoBar infoBar = new InfoBar();

	private final MainMenu mainMenu = new MainMenu();
	private final PauseMenu pauseMenu = new PauseMenu();
	private final HelpOption helpOption = new HelpOption();
	private final AboutOption aboutOption = new AboutOption();
	private final ScoreOption scoreOption = new ScoreOption(score);


	private Camera camera;

	// sound
//	private final String[] fileSounds = {"background", "put_bomb","explosion","enemy_dead", "player_dead","next_level","get_item"};
//	private Sound sound = new Sound(fileSounds);
//
//
	public static boolean isEnemyDead = false;
	public static boolean isPlayerDead = false;
	public static boolean isExplosion = false;
	public static boolean isNextLv = false;
	public static boolean isGetItem = false;
	private Sound sound = new Sound();

	public Game() throws IOException {
	}

	@Override
	public void start(Stage stage) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
		gc = canvas.getGraphicsContext2D();

		Group root = new Group();
		root.getChildren().add(canvas);

		Scene scene = new Scene(root);
		stage.setTitle("BOMBERMAN GAME");

		Scene mainMenuScene = mainMenu.create();
		Scene pauseMenuScene = pauseMenu.create();
		Scene helpOptionScene = helpOption.create();
		Scene aboutOptionScene = aboutOption.create();
		Scene scoreOptionScene = scoreOption.create();

		infoBar.createShowScore(root,textFlow);
		camera = new Camera(0, 0);

		// Them scene vao stage
		stage.setScene(mainMenuScene);
		stage.show();

		boolean[] running = {false};
		sound.getBgSound();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				mainMenuScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (MainMenu.PLAY) {
							stage.setScene(scene);
							MainMenu.PLAY = false;
							running[0] = true;
						}
						if (MainMenu.HELP) {
							stage.setScene(helpOptionScene);
						}
						if (MainMenu.ABOUT) {
							stage.setScene(aboutOptionScene);
							MainMenu.ABOUT = false;
						}
						if (MainMenu.SCORE) {
							scoreOption.updateScore();
							stage.setScene(scoreOptionScene);
							MainMenu.SCORE = false;
						}
					}
				});

				helpOptionScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (HelpOption.HELP_BACK && MainMenu.HELP) {
							stage.setScene(mainMenuScene);
							HelpOption.HELP_BACK = false;
							MainMenu.HELP = false;
						} else if (HelpOption.HELP_BACK && PauseMenu.HELP) {
							stage.setScene(pauseMenuScene);
							HelpOption.HELP_BACK = false;
							PauseMenu.HELP = false;
						}
					}
				});

				aboutOptionScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (AboutOption.ABOUT_BACK) {
							stage.setScene(mainMenuScene);
							AboutOption.ABOUT_BACK = false;
							MainMenu.ABOUT = false;
						}
					}
				});

				scoreOptionScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (ScoreOption.SCORE_BACK) {
							stage.setScene(mainMenuScene);
							ScoreOption.SCORE_BACK = false;
							MainMenu.SCORE = false;
						}
					}
				});

				pauseMenuScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (PauseMenu.CONTINUE) {
							running[0] = true;
							stage.setScene(scene);
							Keyboard.PAUSE = !Keyboard.PAUSE;
							PauseMenu.CONTINUE = false;
						}
						if (PauseMenu.HELP) {
							stage.setScene(helpOptionScene);
						}
						if (PauseMenu.RESTART) {
							try {
								createNewGame();
							} catch (IOException e) {
								e.printStackTrace();
							}
							running[0] = true;
							stage.setScene(scene);
							Keyboard.PAUSE = !Keyboard.PAUSE;
							PauseMenu.RESTART = false;
						}
						if (PauseMenu.MAIN_MAIN) {
							try {
								createNewGame();
							} catch (IOException e) {
								e.printStackTrace();
							}
							running[0] = false;
							stage.setScene(mainMenuScene);
							Keyboard.PAUSE = !Keyboard.PAUSE;
							PauseMenu.MAIN_MAIN = false;
						}

					}
				});

				pauseMenuScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (Keyboard.PAUSE) {
							running[0] = true;
							stage.setScene(scene);
							Keyboard.PAUSE = !Keyboard.PAUSE;
							PauseMenu.CONTINUE = false;
						}
					}
				});

				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						Keyboard.setInputKeyEvent(event);
						//							stage.setScene(menuScene);
						if (running[0] && Keyboard.PAUSE) {
							running[0] = false;
							stage.setScene(pauseMenuScene);
						}

						if (running[0]) {
							if (Keyboard.SPACE && NUMBER_OF_BOMBS != 0) {
								try {
									createBomb();
								} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				});

				scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						//							stage.setScene(menuScene);
						running[0] = !Keyboard.PAUSE;

						if (running[0]) {
							Keyboard.setInputKeyEvent(event);
						}
					}
				});

				if (running[0]) {
					System.out.println("ud");

					try {
						if (isNextLv) sound.getNextLevelSound();
						if (isEnemyDead) sound.getEnemyDeadSound();
						if (isPlayerDead) sound.getPlayerDeadSound();
						if (isGetItem) sound.getItemSound();
						if (isExplosion) sound.getExplosionSound();
						update();
					} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
						e.printStackTrace();
					}
					render();
				}
			}
		};
		timer.start();
		//sound.getBgSound();
		GameMap.initMap();
	}

	/**
	 * Tạo bomb tại vị trí mới và add vào bombList.
	 */
	private void createBomb() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		sound = new Sound();
		sound.getPutBomSound();
		bomberman = getBomber();
		int tmpX = (bomberman.getX() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
		int tmpY = (bomberman.getY() + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
		Bomb bomb = new Bomb(tmpX, tmpY, Sprite.bomb.getFxImage());
		bombList.add(bomb);
		NUMBER_OF_BOMBS--;
	}


	//=================================== update area ===================================//
	public void update() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		entitiesUpdate();
		bombUpdate();
		itemUpdate();
		portalUpdate();

	}

	private void entitiesUpdate() throws IOException {
		Iterator<AnimatedEntitiy> iterator = Game.entityList.iterator();
		while (iterator.hasNext()) {
			AnimatedEntitiy animatedEntitiy = iterator.next();
			if (animatedEntitiy != null && !(animatedEntitiy instanceof Bomber)) {
				animatedEntitiy.update();
				if (((Enemy) animatedEntitiy).isDestroyed()) {
					iterator.remove();
					score.killEnemy();
					infoBar.updateScore(score);
				}
			}
		}
//		if (getBomber().getX() > Game.WIDTH * Sprite.SCALED_SIZE) {
		camera.tick(Objects.requireNonNull(getBomber()));
//		}
		if (!getBomber().isAlive()) {
			score.endGame();
			infoBar.updateScore(score);
		}
		Objects.requireNonNull(getBomber()).update();

	}


	/**
	 * Câp nhật về bomb:
	 * - Bomb nổ hay chưa?
	 * - Nổ vào brick thì thế nào -> cập nhật brick
	 * - Nổ vào người, nổ vào quái.
	 * -
	 */
	int cnt_time_bombsound = 0;
	int cnt_time_enemydead = 0;
	int cnt_time_playerdead = 0;
	private void bombUpdate() {
//		System.out.println(NUMBER_OF_BOMBS);
		Iterator<Bomb> bombIterator = bombList.iterator();
		while (bombIterator.hasNext()) {
			Bomb bomb = bombIterator.next();
			if (bomb != null) {
				bomb.update();
				if (!bomb.isDestroyed() && bomb.isExploding()) {
					cnt_time_bombsound++;
					if (cnt_time_bombsound == 1) isExplosion = true;
					else isExplosion = false;
					for (int i = 0; i < bomb.getFlameList().size(); i++) {
						Flame flame = bomb.getFlameList().get(i);
						flame.update();
						//Kiểm tra và xử lí nếu flame chạm vào người chơi hoặc quái.
						Iterator<AnimatedEntitiy> itr = Game.entityList.iterator();
						AnimatedEntitiy cur;
						while (itr.hasNext()) {
							cur = itr.next();
							if (cur instanceof Enemy) {
								if (Collisions.checkCollision(cur, flame)) {// && bomb.isExploding()) {
									((Enemy) cur).enemyDie();
									cnt_time_enemydead++;
									if (cnt_time_enemydead == 1) isEnemyDead = true;
									else isEnemyDead = false;
								}
							}
							if (cur instanceof Bomber) {
								if (Collisions.checkCollision(cur, flame)) {// && bomb.isExploding()) {
									((Bomber) cur).setAlive(false);
									cnt_time_playerdead++;
									if (cnt_time_playerdead == 1) isPlayerDead = true;
									else isPlayerDead = false;
								}
							}
						}

						//Kiểm tra và xử lí nếu flame chạm vào brick không.
						int xFlame = flame.getX() / Sprite.SCALED_SIZE;
						int yFlame = flame.getY() / Sprite.SCALED_SIZE;
						if (LayeredEntity.containsKey(generateKey(xFlame, yFlame))) {
							Stack<Entity> tiles = LayeredEntity.get(generateKey(xFlame, yFlame));
							if (tiles.peek() instanceof Brick) {
								Brick brick = (Brick) tiles.peek();
								brick.setExploded(true);
								brick.update();
								if (brick.isDestroyed()) {
									tiles.pop();
								}
							}
						}
					}
				}
				if (bomb.isDestroyed()) {// && NUMBER_OF_BOMBS < 1) {
					NUMBER_OF_BOMBS++;
					bombIterator.remove();
					cnt_time_bombsound = 0;
					cnt_time_enemydead = 0;
					cnt_time_playerdead = 0;
				}
			}
		}
	}

	/**
	 * Cập nhật item khi có va chạm với người chơi.
	 */
	int cnt_time_itemsound1 = 0;
	int cnt_time_itemsound2 = 0;
	int cnt_time_itemsound3 = 0;
	private void itemUpdate() {
		if (!LayeredEntity.isEmpty()) {
			for (Integer value : getLayeredEntitySet()) {
				if (LayeredEntity.get(value).peek() instanceof FlameItem
						&& Collisions.checkCollisionWithBuffer(Objects.requireNonNull(getBomber()), LayeredEntity.get(value).peek())) {
					Game.LENGTH_OF_FLAME++;
					LayeredEntity.get(value).pop();
					FlameItem.timeItem = 0;
					FlameItem.isPickUp = true;
				}
				if (LayeredEntity.get(value).peek() instanceof SpeedItem
						&& Collisions.checkCollisionWithBuffer(Objects.requireNonNull(getBomber()), LayeredEntity.get(value).peek())) {
					LayeredEntity.get(value).pop();
					Bomber.setVELOCITY(3);
					SpeedItem.timeItem = 0;
					SpeedItem.isPickUp = true;
				}
				if (LayeredEntity.get(value).peek() instanceof BombItem
						&& Collisions.checkCollisionWithBuffer(Objects.requireNonNull(getBomber()), LayeredEntity.get(value).peek())) {
					LayeredEntity.get(value).pop();
					NUMBER_OF_BOMBS++;
					BombItem.timeItem = 0;
					BombItem.isPickUp = true;
				}
			}
			if (FlameItem.isPickUp) {
				cnt_time_itemsound1 ++;
				FlameItem.timeItem++;
				if (cnt_time_itemsound1 == 1) isGetItem = true;
				else isGetItem = false;
				if (FlameItem.timeItem > 2000) {
					FlameItem.timeItem = 0;
					Game.LENGTH_OF_FLAME = 1;
					FlameItem.isPickUp = false;
				}
			}
			if (SpeedItem.isPickUp) {
				cnt_time_itemsound2++;
				if (cnt_time_itemsound2 == 1) {
					isGetItem = true;
				}
				else isGetItem = false;
				SpeedItem.timeItem++;
				if (SpeedItem.timeItem > 2000) {
					SpeedItem.timeItem = 0;
					Bomber.setVELOCITY(2);
					SpeedItem.isPickUp = false;
				}
			}
			if (BombItem.isPickUp) {
				cnt_time_itemsound3++;
				if (cnt_time_itemsound3 == 1) isGetItem = true;
				else isGetItem = false;
				BombItem.timeItem++;
				if (BombItem.timeItem > 2000) {
					BombItem.timeItem = 0;
					NUMBER_OF_BOMBS = 1;
					BombItem.isPickUp = false;
				}
			}
		}

	}

	int cnt_time_nextlv = 0;
	private void portalUpdate() throws IOException {
		int count_enemy = 0;
		Iterator<AnimatedEntitiy> itr = entityList.iterator();
		while (itr.hasNext()) {
			AnimatedEntitiy e = itr.next();
			if (e instanceof Enemy) {
				count_enemy++;
			}
		}

		if (count_enemy == 0) {
			boolean canNextGame = false;
			if (!LayeredEntity.isEmpty()) {
				for (Integer value : getLayeredEntitySet()) {

					if (LayeredEntity.get(value).peek() instanceof Portal
							&& Collisions.checkCollisionWithBuffer(Objects.requireNonNull(getBomber()), LayeredEntity.get(value).peek())) {
						canNextGame = true;
						break;
					}
				}
			}
			if (canNextGame) {
				GameMap.setGameLevel(GameMap.getGameLevel() + 1);
				GameMap.clear();
				GameMap.initMap();
				score.endGame();
				infoBar.updateScore(score);
			}
		}
	}

	private void createNewGame() throws IOException {
		GameMap.setGameLevel(GameMap.getGameLevel());
		GameMap.clear();
		GameMap.initMap();
		score.endGame();
		infoBar.updateScore(score);
	}
	//=================================== Render area ===================================//

	public void render() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.translate(-camera.getX(), 0);


		for (Entity stillObject : stillObjects) {
			stillObject.render(gc);
		}

		if (!LayeredEntity.isEmpty()) {
			for (Integer value : getLayeredEntitySet()) {
				LayeredEntity.get(value).peek().render(gc);
			}
		}
		bombRender();

		Iterator<AnimatedEntitiy> animatedEntitiyIterator = entityList.iterator();
		while (animatedEntitiyIterator.hasNext()) {
			AnimatedEntitiy animatedEntitiy = animatedEntitiyIterator.next();
			if (animatedEntitiy != null)
				animatedEntitiy.render(gc);
		}
		gc.translate(camera.getX(), 0);
	}

	/**
	 * Render bomb cùng các flame khi nổ.
	 */
	private void bombRender() {
		for (Bomb bomb : bombList) {
			if (bomb != null) {
				if (!bomb.isDestroyed()) {
					bomb.render(gc);
				}
				if (!bomb.isDestroyed() && bomb.isExploding()) {
					for (int i = 0; i < bomb.getFlameList().size(); i++) {
						bomb.getFlameList().get(i).render(gc);
					}
				}
			}
		}
//		if (bombList.size() == 0) {
//			bombList = new ArrayList<Bomb>();
//		}
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

	public static List<Bomb> getBombList() {
		return bombList;
	}

	public static Bomb getBomb() {
		if (bombList.isEmpty()) return null;
		return getBombList().get(0);
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
