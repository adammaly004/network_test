package cz.cvut.fel.malyada1.squareland.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Heal extends Rectangle {
    private int healAmount;

    public Heal(double x, double y) {
        super(x, y, 10, 10);
        setFill(Color.DEEPPINK);
        this.healAmount = 1; // Example heal amount
    }

    public int getAddedLives() {
        return healAmount;
    }
}