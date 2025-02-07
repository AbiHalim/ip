package joni;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Joni joni;
    private Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private Image joniImage = new Image(getClass().getResourceAsStream("/images/DaJoni.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    public void setJoni(Joni j) {
        joni = j;
    }

    private void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getJoniDialog(joni.getWelcomeMessage(), joniImage)
        );
    }

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
