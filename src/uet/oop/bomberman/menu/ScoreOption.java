package uet.oop.bomberman.menu;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.info.Score;

import java.io.IOException;

import static uet.oop.bomberman.Game.HEIGHT;
import static uet.oop.bomberman.Game.WIDTH;

public class ScoreOption extends Menu {
	public static boolean SCORE_BACK = false;
	private final Score score;
	private String str = "0";
	private VBox vb;
	private Text backText;
	private Text scoreInfo;
	private TextFlow area;
	public ScoreOption(Score score) throws IOException {
		this.score = score;
		create();
	}

	public void updateScore() {
		str = "";
		for (int i = 0; i < score.getHighScore().size(); i++) {
			str += i + 1 + ". " + score.getHighScore().get(i) + "\n";
		}
		scoreInfo.setText(str);
	}


	@Override
	public Scene create() {
		 vb = initVBox();
		 backText = new Text("Back");
		 area = new TextFlow();
		 scoreInfo = new Text(str);

		customText(backText);
		customText(scoreInfo);
//		scoreInfo.setStyle("-fx-font-size:25");
		scoreInfo.setEffect(null);

		area.getChildren().add(scoreInfo);
		vb.getChildren().add(area);
		vb.getChildren().add(backText);
		VBox.setMargin(area, new Insets(100, 200, 0, 300));
		vb.setFillWidth(true);

		Scene scoreOption = new Scene(vb, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

		EventHandler<MouseEvent> playHandle = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				playHandle(event, backText);
			}
		};


		backText.addEventFilter(MouseEvent.MOUSE_ENTERED, playHandle);
		backText.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, playHandle);
		backText.addEventFilter(MouseEvent.MOUSE_PRESSED, playHandle);

		return scoreOption;
	}


	private static void playHandle(MouseEvent event, Text text) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			text.setStyle("-fx-font-size:60");
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED_TARGET) {
			text.setStyle("-fx-font-size:45");
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			System.out.println("chon");
			SCORE_BACK = true;
			text.setStyle("-fx-font-size:45");
		} else {
			text.setStyle("-fx-font-size:45");
		}
	}

}
