package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

public abstract class Entity {
	//Tọa độ X tính từ góc trái trên trong Canvas
	protected int x;

	//Tọa độ Y tính từ góc trái trên trong Canvas
	protected int y;

	protected Image img;
	protected Sprite sprite;

	protected int xTile;
	protected int yTile;

	//Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
	public Entity(int xUnit, int yUnit, Image img) {
		this.x = xUnit * Sprite.SCALED_SIZE;
		this.y = yUnit * Sprite.SCALED_SIZE;
		this.img = img;
		xTile = xUnit;
		yTile = yUnit;
	}


	public void render(GraphicsContext gc) {
		gc.drawImage(img, x, y);
	}
	public abstract void update() throws IOException;


	public Image getImg() {
		return img;
	}

	/**
	 * set display img for entity
	 *
	 * @param img Sprite.(status).getFxImage()
	 */
	public void setImg(Image img) {
		this.img = img;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXTile() {
		return xTile;
	}

	public int getYTile() {
		return yTile;
	}
}
