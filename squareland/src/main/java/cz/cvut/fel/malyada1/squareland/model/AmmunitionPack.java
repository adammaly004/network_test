package cz.cvut.fel.malyada1.squareland.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AmmunitionPack extends Rectangle
{
    private int addedAmmo;
    public AmmunitionPack(double x, double y) {
        super(x, y, 10, 10);
        setFill(Color.YELLOW);
        this.addedAmmo = 1;
    }
    public int getAddedAmmo() {
        return addedAmmo;
    }
}
