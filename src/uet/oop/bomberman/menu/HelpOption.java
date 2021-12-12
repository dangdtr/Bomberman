package uet.oop.bomberman.menu;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Game.HEIGHT;
import static uet.oop.bomberman.Game.WIDTH;

public class HelpOption extends Menu{
	public static boolean HELP_BACK = false;

	public HelpOption() {
		create();
	}

	@Override
	public Scene create(){
		VBox vb = initVBox();

		Text backText = new Text("Back");

		customText(backText);

		vb.getChildren().add(backText);

		Scene helpOptionScene = new Scene(vb, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

		EventHandler<MouseEvent> playHandle = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				playHandle(event, backText);
			}
		};


		backText.addEventFilter(MouseEvent.MOUSE_ENTERED, playHandle);
		backText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, playHandle);
		backText.addEventFilter(MouseEvent.MOUSE_PRESSED, playHandle);

		return helpOptionScene;
	}


	private static void playHandle(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET){
			text.setStyle("-fx-font-size:45");
		}else if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
			System.out.println("chon");
			HELP_BACK = true;
			text.setStyle("-fx-font-size:45");
		}else {
			text.setStyle("-fx-font-size:45");
		}
	}

}
