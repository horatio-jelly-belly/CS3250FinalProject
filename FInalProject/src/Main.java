import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws Exception{
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameBorderPane pane = new GameBorderPane();
		Scene scene = new Scene(pane, 1000, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
