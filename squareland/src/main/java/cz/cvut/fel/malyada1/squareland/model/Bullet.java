package cz.cvut.fel.malyada1.squareland.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Bullet extends Circle {
    private final double dx;
    private final double dy;
    private final double speed;
    private final double damage;

    public Bullet(double x, double y, double dx, double dy, double speed, double damage) {
        super(x, y, 5); // Set the radius of the bullet
        setFill(Color.YELLOW);
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        this.damage = damage;
    }

    public void move() {
        setCenterX(getCenterX() + dx * speed);
        setCenterY(getCenterY() + dy * speed);
    }

    public double getDamage() {
        return damage;
    }

}