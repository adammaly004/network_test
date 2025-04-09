package cz.cvut.fel.malyada1.squareland.view;

import cz.cvut.fel.malyada1.squareland.model.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class GameView {
    private final Pane root;

    public GameView(int width, int height) {
        root = new Pane();
        root.setPrefSize(width, height);
        root.setStyle("-fx-background-color: black;");
    }

    public Pane getRoot() {
        return root;
    }

    public void drawMap(Map map) {
        map.draw(root);
    }

    public void drawPlayers(Player player1, Player player2) {
        root.getChildren().addAll(player1, player2);
    }

    public void drawBullets(List<Bullet> bulletsPlayer1, List<Bullet> bulletsPlayer2) {
        root.getChildren().removeIf(node -> node instanceof Bullet);
        root.getChildren().addAll(bulletsPlayer1);
        root.getChildren().addAll(bulletsPlayer2);
    }
    public void drawItems(List<Rectangle> items) {


        root.getChildren().removeIf(node -> node instanceof AmmunitionPack);
        root.getChildren().removeIf(node -> node instanceof Heal);
        root.getChildren().addAll(items);
    }
}