package cz.cvut.fel.malyada1.squareland.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import cz.cvut.fel.malyada1.squareland.model.Map;

public class MapEditorView {
    private final Pane root;
    private final Button saveButton;
    private final TextField textField;
    //private final VBox vbox;

    public MapEditorView(int width, int height) {
        root = new Pane();
        root.setPrefSize(width, height);
        root.setStyle("-fx-background-color: rgba(0,0,0,0.5);");

        saveButton = new Button("Save Map");
        saveButton.setPrefSize(100, 50);

        textField = new TextField("newMap");
        textField.setPromptText("Enter file name");
        textField.setPrefSize(100, 50);
        textField.setLayoutX(100);

        //root.getChildren().add(saveButton);
    }
    public Pane getRoot() {
        return root;
    }

    public Button getSaveButton() {
        return saveButton;
    }
    public TextField getTextField() {
        return textField;
    }

    public void drawMap(Map map){
        map.draw(root);
    }

    public void drawMenu(){
        root.getChildren().addAll(saveButton, textField);
    }

    public void updateTile(Map map, int row, int col){
        map.updateTile(root, row, col);
    }
}
