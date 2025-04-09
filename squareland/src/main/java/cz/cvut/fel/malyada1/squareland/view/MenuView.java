package cz.cvut.fel.malyada1.squareland.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class MenuView {
    private final Pane root;
    private final Button startButton;
    private final Button editorButton;
    private final Button exitButton;
    private final Button chooseFileButton;

    public MenuView(int width, int height) {
        root = new Pane();
        root.setPrefSize(width, height);
        root.setStyle("-fx-background-color: #2a2a2a;");

        VBox menuItems = new VBox(20);
        menuItems.setAlignment(Pos.CENTER);

        Text title = new Text("Square Game Survival");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.WHITE);

        startButton = new Button("Start Game");
        startButton.setPrefSize(200, 50);
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        editorButton = new Button("Edit Map");
        editorButton.setPrefSize(200, 50);
        editorButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        chooseFileButton = new Button("Choose File");
        chooseFileButton.setPrefSize(200, 50);
        chooseFileButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        exitButton = new Button("Exit");
        exitButton.setPrefSize(200, 50);
        exitButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        menuItems.getChildren().addAll(title, startButton, editorButton, chooseFileButton, exitButton);
        root.getChildren().add(menuItems);

        // Center the menu items
        menuItems.layoutXProperty().bind(root.widthProperty().subtract(menuItems.widthProperty()).divide(2));
        menuItems.layoutYProperty().bind(root.heightProperty().subtract(menuItems.heightProperty()).divide(2));
    }

    public Pane getRoot() {
        return root;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
    public Button getEditorButton() {
        return editorButton;
    }
    public Button getChooseFileButton() {
        return chooseFileButton;
    }
}