package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.ControlKeyboard.Controller;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    protected Controller input;
    protected int direction = -1;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        calculateMove();
    }

    @Override
    public void render() {

    }

    // check di chuyen
    protected void calculateMove() {
        // TODO: xử lý nhận tín hiệu điều khiển hướng đi từ input và gọi move() để thực hiện di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ moving khi thay đổi trạng thái di chuyển
        int x = 0, y = 0;
        if (input.up) y--;//len
        if (input.down) y++;//xuong
        if (input.left) x--;//trai
        if (input.right) x++;//phai

        if (x!=0||y!=0)
        {
            move(x, y);
            moving = true;
        } else moving = false;
    }

    public boolean canMove(double _x, double _y) {
        // TODO: kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không
//        for (int c = 0; c<4; c ++){
//            double xt = ((_x+x) + c%2*11)/16;  //16 = Game.tiles_size
//            double yt = ((_y+y) + c/2 *12 -13) / 16;
//
//            Entity a = _board.getEntity(xt,yt,this);
//            //if (!a.collide(this)) return false;
//        }
        return true;
    }

    public void move(double xa, double ya) {
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi tọa độ _x, _y
        // TODO: nhớ cập nhật giá trị _direction sau khi di chuyển
        if (xa >0) direction = 1;
        if (xa <0) direction = 3;
        if (ya >0) direction = 2;
        if (ya <0) direction = 0;

        if (canMove(0,ya)) y += ya;
        if (canMove(xa,0)) x += xa;
    }

    private void chooseSprite() {
        switch (direction) {
            case 0:
                sprite = Sprite.player_up;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                }
                break;
            case 1:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                }
                break;
            case 2:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                }
                break;
            case 3:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                }
                break;
            default:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                }
                break;
        }
    }
}
