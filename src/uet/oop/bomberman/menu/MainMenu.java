package uet.oop.bomberman.menu;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
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

public class MainMenu extends Menu {
	public MainMenu() {
		create();
	}
	public static boolean PLAY = false;
	public static boolean HELP = false;
	public static boolean ABOUT = false;
	public static boolean SCORE = false;

	@Override

	public Scene create(){
		VBox vb = initVBox();

		Text playText = new Text("Play");
		Text helpText = new Text("Help");
		Text aboutText = new Text("About");
		Text scoreText = new Text("High Score");

		customText(playText);
		customText(helpText);
		customText(aboutText);
		customText(scoreText);

		vb.getChildren().add(playText);
		vb.getChildren().add(scoreText);
		vb.getChildren().add(helpText);
		vb.getChildren().add(aboutText);

		Scene menuScene = new Scene(vb, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

		EventHandler<MouseEvent> playHandle = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				playHandle(event, playText);
			}
		};

		EventHandler<MouseEvent> helpHandle = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				helpHandle(event, helpText);
			}
		};

		EventHandler<MouseEvent> aboutHandle = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				aboutHandle(event, aboutText);
			}
		};

		EventHandler<MouseEvent> scoreHandle = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				scoreHandle(event, scoreText);
			}
		};

		playText.addEventFilter(MouseEvent.MOUSE_ENTERED, playHandle);
		playText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, playHandle);
		playText.addEventFilter(MouseEvent.MOUSE_PRESSED, playHandle);

		helpText.addEventFilter(MouseEvent.MOUSE_ENTERED, helpHandle);
		helpText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, helpHandle);
		helpText.addEventFilter(MouseEvent.MOUSE_PRESSED, helpHandle);

		aboutText.addEventFilter(MouseEvent.MOUSE_ENTERED, aboutHandle);
		aboutText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, aboutHandle);
		aboutText.addEventFilter(MouseEvent.MOUSE_PRESSED, aboutHandle);

		scoreText.addEventFilter(MouseEvent.MOUSE_ENTERED, scoreHandle);
		scoreText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, scoreHandle);
		scoreText.addEventFilter(MouseEvent.MOUSE_PRESSED, scoreHandle);

		return menuScene;
	}

	private void scoreHandle(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED){
			text.setStyle("-fx-font-size:45");
		}else if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
//			System.out.println("chon");
			SCORE = true;
			text.setStyle("-fx-font-size:45");
		}else {
			text.setStyle("-fx-font-size:45");
		}
	}


	private static void aboutHandle(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED){
			text.setStyle("-fx-font-size:45");
		}else if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
//			System.out.println("chon");
			ABOUT = true;
			text.setStyle("-fx-font-size:45");
		}else {
			text.setStyle("-fx-font-size:45");
		}
	}

	private static void helpHandle(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET){
			text.setStyle("-fx-font-size:45");
		}else if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
//			System.out.println("chon");
			HELP = true;
			text.setStyle("-fx-font-size:45");
		}else {
			text.setStyle("-fx-font-size:45");
		}
	}

	private static void playHandle(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET){
			text.setStyle("-fx-font-size:45");
		}else if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
//			System.out.println("chon");
			PLAY = true;
			text.setStyle("-fx-font-size:45");
		}else {
			text.setStyle("-fx-font-size:45");
		}
	}

}
