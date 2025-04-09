package cz.cvut.fel.malyada1.squareland.controller;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import cz.cvut.fel.malyada1.squareland.model.MapEditor;
import cz.cvut.fel.malyada1.squareland.utils.MapSaver;
import cz.cvut.fel.malyada1.squareland.view.MapEditorView;

import java.io.IOException;

import static cz.cvut.fel.malyada1.squareland.utils.Constants.MAP_PATH;
import static cz.cvut.fel.malyada1.squareland.utils.Constants.TILE_SIZE;

public class MapEditorController {
    private final MapEditor model;
    private final MapEditorView view;
    private final SceneController sceneController;
    private boolean isMousePressed;
    private int lastRow;
    private int lastCol;


    public MapEditorController(MapEditorView view, SceneController sceneController) {
        model = new MapEditor();
        this.sceneController = sceneController;
        this.view = view;
        this.isMousePressed = false;
        lastRow = -1;
        lastCol = -1;

        setMouseHandlers();
        setSaveButtonHandler();
    }

    public void startEditor() {
        view.drawMap(model.getMap());
        view.drawMenu();
    }

    private void setMouseHandlers() {
        view.getRoot().setOnMousePressed(this::handleMousePress);
        view.getRoot().setOnMouseReleased(this::handleMouseRelease);
        view.getRoot().setOnMouseDragged(this::handleMouseDrag);
    }

    private void handleMousePress(MouseEvent event) {
        isMousePressed = true;
        handleMouseEvent(event);
    }

    private void handleMouseRelease(MouseEvent event) {
        isMousePressed = false;
        lastRow = -1;
        lastCol = -1;

    }

    private void handleMouseDrag(MouseEvent event) {
        if (isMousePressed) {
            handleMouseEvent(event);
        }
    }

    private void handleMouseEvent(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        int row = (int) (y / TILE_SIZE);
        int col = (int) (x / TILE_SIZE);

        if (lastRow != row || lastCol != col) {
            if(event.getButton() == MouseButton.PRIMARY) {
                model.getMap().incrementTile(row, col);
            } else if(event.getButton() == MouseButton.SECONDARY) {
                model.getMap().setTile(row, col, 0);
            }
            view.updateTile(model.getMap(), row, col);
            lastRow = row;
            lastCol = col;
        }

    }

    private void setSaveButtonHandler() {
        Button saveButton = view.getSaveButton();
        saveButton.setOnAction(e -> {
            try {
                String filename = view.getTextField().getText();
                MapSaver.saveMap(model.getMap().getMapArray(), MAP_PATH + filename + ".txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}