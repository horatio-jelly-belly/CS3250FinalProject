import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * All comments in every file of this final project were created with AI.
 * I uploaded my files and asked AI to generate comments for them.
 * 
 * I also used AI to help me understand how to separate the UI and business logic.
 * I was uncertain as to which parts were supposed to go into the UI and which were not.
 * I typed the code out and then feed what I had typed into AI and it guided me 
 * through which parts go in which file and why.
 * 
 * All sprites came from craftpix.net
 */

/**
 * Entry point for the JavaFX application.
 * Launches the game and creates the main window.
 * Extends Application to integrate with JavaFX framework.
 */
public class Main extends Application {

    /**
     * Main method - the program's entry point.
     * Launches the JavaFX application framework.
     * @param args Command line arguments (not used)
     * @throws Exception If application fails to launch
     */
    public static void main(String[] args) throws Exception{
        // Calls Application.launch() which starts JavaFX
        // This will eventually call the start() method below
        launch(args);
    }

    /**
     * JavaFX application start method.
     * Called automatically after JavaFX initialization.
     * Creates and displays the main game window.
     * @param primaryStage The main window provided by JavaFX
     * @throws Exception If window creation fails
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the main game interface container
        // This contains all UI elements and the game canvas
        GameBorderPane pane = new GameBorderPane();
        
        // Create a Scene to hold the game interface
        // Scene connects the UI to the window
        Scene scene = new Scene(pane);
        
        // Attach the scene to the main window
        primaryStage.setScene(scene);
        
        // Display the window to the user
        // Makes the game visible and interactive
        primaryStage.show();
    }
}