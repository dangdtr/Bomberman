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

public class PauseMenu extends Menu {
	public static boolean CONTINUE = false;
	public static boolean HELP = false;
	public static boolean RESTART = false;
	public static boolean MAIN_MAIN = false;


	public PauseMenu() {
		create();
	}

	@Override
	public Scene create(){
		VBox vb = new VBox();
		vb.setAlignment(Pos.BOTTOM_CENTER);
		vb.setPadding(new Insets(20));
		vb.setSpacing(30);
		vb.setBackground(new Background(createImage("file:res/BG.png")));


		Text continueText = new Text("Continue");
		Text helpText = new Text("Help");
		Text restartText = new Text("Restart");
		Text mainMenu = new Text("Main Menu");

		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));


		Font font = Font.loadFont("file:res/emulogic.ttf", 45);

		continueText.setEffect(ds);
		continueText.setFont(font);
		helpText.setEffect(ds);
		helpText.setFont(font);
		restartText.setEffect(ds);
		restartText.setFont(font);
		mainMenu.setEffect(ds);
		mainMenu.setFont(font);

		continueText.setFill(new LinearGradient(0, 0, 1, 1, true,
				CycleMethod.REFLECT,
				new Stop(0.0, Color.RED),
				new Stop(1.0, Color.GOLD)));
		continueText.setStroke(Color.BLUEVIOLET);
		continueText.setStrokeWidth(0.5);

		helpText.setFill(new LinearGradient(0, 0, 1, 1, true,
				CycleMethod.REFLECT,
				new Stop(0.0, Color.RED),
				new Stop(1.0, Color.GOLD)));
		helpText.setStroke(Color.BLUEVIOLET);
		helpText.setStrokeWidth(0.5);

		restartText.setFill(new LinearGradient(0, 0, 1, 1, true,
				CycleMethod.REFLECT,
				new Stop(0.0, Color.RED),
				new Stop(1.0, Color.GOLD)));
		restartText.setStroke(Color.BLUEVIOLET);
		restartText.setStrokeWidth(0.5);

		mainMenu.setFill(new LinearGradient(0, 0, 1, 1, true,
				CycleMethod.REFLECT,
				new Stop(0.0, Color.RED),
				new Stop(1.0, Color.GOLD)));
		mainMenu.setStroke(Color.BLUEVIOLET);
		mainMenu.setStrokeWidth(0.5);


		DropShadow shadow = new DropShadow();
		shadow.setOffsetY(5.0);
		Reflection r = new Reflection();
		r.setFraction(0.7f);
		continueText.setEffect(r);
		helpText.setEffect(r);
		restartText.setEffect(r);
		mainMenu.setEffect(r);

		vb.getChildren().add(continueText);
		vb.getChildren().add(restartText);
		vb.getChildren().add(mainMenu);
		vb.getChildren().add(helpText);


		Scene pauseMenuScene = new Scene(vb, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

		EventHandler<MouseEvent> continueHandle = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				continueHandle(event, continueText);
			}
		};

		EventHandler<MouseEvent> helpHandle = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				helpHandle(event, helpText);
			}
		};

		EventHandler<MouseEvent> restartHandle = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				restartHandle(event, restartText);
			}
		};

		EventHandler<MouseEvent> mainMenuHanlde = new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				mainMenuHanlde(event, mainMenu);
			}
		};

		continueText.addEventFilter(MouseEvent.MOUSE_ENTERED, continueHandle);
		continueText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, continueHandle);
		continueText.addEventFilter(MouseEvent.MOUSE_PRESSED, continueHandle);

		helpText.addEventFilter(MouseEvent.MOUSE_ENTERED, helpHandle);
		helpText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, helpHandle);
		helpText.addEventFilter(MouseEvent.MOUSE_PRESSED, helpHandle);

		restartText.addEventFilter(MouseEvent.MOUSE_ENTERED, restartHandle);
		restartText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, restartHandle);
		restartText.addEventFilter(MouseEvent.MOUSE_PRESSED, restartHandle);

		mainMenu.addEventFilter(MouseEvent.MOUSE_ENTERED, mainMenuHanlde);
		mainMenu.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, mainMenuHanlde);
		mainMenu.addEventFilter(MouseEvent.MOUSE_PRESSED, mainMenuHanlde);

		return pauseMenuScene;
	}

	private static void mainMenuHanlde(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET){
			text.setStyle("-fx-font-size:45");
		}else if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
//			System.out.println("chon");
			MAIN_MAIN = true;
			text.setStyle("-fx-font-size:45");
		}else {
			text.setStyle("-fx-font-size:45");
		}
	}


	private static void restartHandle(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET){
			text.setStyle("-fx-font-size:45");
		}else if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
			RESTART = true;
//			System.out.println("chon");
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

	private static void continueHandle(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET){
			text.setStyle("-fx-font-size:45");
		}else if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
//			System.out.println("chon");
			CONTINUE = true;
			text.setStyle("-fx-font-size:45");
		}else {
			text.setStyle("-fx-font-size:45");
		}
	}


}
