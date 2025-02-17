package joni.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import joni.Joni;

/**
 * The main window of the GUI application.
 * Handles user interactions and displays dialog boxes.
 */
public class MainWindow extends AnchorPane {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Joni joni;
    private Image userImage = new Image(getClass().getResourceAsStream("/images/User.png"));
    private Image joniImage = new Image(getClass().getResourceAsStream("/images/Joni.png"));

    /**
     * Initializes the main window.
     * Binds the scroll pane to automatically scroll to the latest dialog entry.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Joni chatbot instance for this GUI window.
     *
     * @param j The Joni chatbot instance.
     */
    public void setJoni(Joni j) {
        joni = j;
    }

    /**
     * Displays Joni's welcome message in the dialog container.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getJoniDialog(joni.getWelcomeMessage(), joniImage)
        );
    }

    /**
     * Handles user input by creating dialog boxes for the user's message and Joni's response.
     * Clears the user input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = joni.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJoniDialog(response, joniImage)
        );
        userInput.clear();
    }
}
