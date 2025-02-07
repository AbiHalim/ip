package joni;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main driver class for the GUI application
 */
public class Main extends Application {
    private Joni joni = new Joni();

    /**
     * Starts the JavaFX application.
     * Loads the FXML layout, initializes the scene, and displays the main window.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJoni(joni);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.setTitle("Joni");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
