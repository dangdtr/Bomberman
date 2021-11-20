package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {
    private int direction = -1;
    private final int v = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void update() {
        switch (getDirection()) {
            case -1:
                break;
            case 0:
                y = y - v;
                break;
            case 1:
                x = x + v;
                break;
            case 2:
                y = y + v;
                break;
            case 3:
                x = x - v;
                break;

        }
    }


    //    @Override
    public void inputKeyHandle(javafx.scene.input.KeyEvent event) {


        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.UP) {
                setDirection(0);
            } else if (event.getCode() == KeyCode.RIGHT) {
                setDirection(1);
            } else if (event.getCode() == KeyCode.DOWN) {
                setDirection(2);
            } else if (event.getCode() == KeyCode.LEFT) {
                setDirection(3);
            }
        } else {
            setDirection(-1);
        }


    }


}
