package cz.cvut.fel.malyada1.squareland.controller;

import cz.cvut.fel.malyada1.squareland.view.MenuView;



public class MenuController {
    private final MenuView view;
    private final SceneController screenController;

    public MenuController(MenuView view, SceneController screenController) {
        this.view = view;
        this.screenController = screenController;

        view.getStartButton().setOnAction(e -> screenController.startGame());
        view.getExitButton().setOnAction(e -> screenController.exitGame());
        view.getEditorButton().setOnAction(e-> screenController.startEditor());
        view.getChooseFileButton().setOnAction(e -> screenController.chooseMapFile());

    }
}