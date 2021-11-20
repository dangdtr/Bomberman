package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
<<<<<<< HEAD
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
=======
>>>>>>> 073e818b7a48388044b320811014b0f6b2961742
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected double x;
    protected double y;
    protected boolean moving = false;
    protected Image img;
    protected Sprite sprite;
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
<<<<<<< HEAD

//    public abstract void inputKeyHandle(KeyEvent event);
=======
    public abstract void render();
>>>>>>> 073e818b7a48388044b320811014b0f6b2961742
}
