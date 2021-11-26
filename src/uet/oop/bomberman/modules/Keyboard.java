package uet.oop.bomberman.modules;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * sự kiện bàn phím
 */
public class Keyboard {

    public static boolean UP, LEFT, RIGHT, DOWN;

    public void setInputKeyEvent(javafx.scene.input.KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.UP) {
                UP = true;
            } else if (event.getCode() == KeyCode.LEFT) {
                LEFT = true;
            } else if (event.getCode() == KeyCode.DOWN) {
                DOWN = true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                RIGHT = true;
            }
        } else {
            UP = false;
            LEFT = false;
            DOWN = false;
            RIGHT = false;
        }
    }

}
