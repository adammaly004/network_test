package cz.cvut.fel.malyada1.squareland;


import javafx.application.Application;
import javafx.stage.Stage;

import cz.cvut.fel.malyada1.squareland.controller.SceneController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneController sceneController = new SceneController(primaryStage);
        sceneController.showMenu();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}