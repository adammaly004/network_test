package cz.cvut.fel.malyada1.squareland.utils;

import static cz.cvut.fel.malyada1.squareland.utils.Constants.*;

public class Collisions
{
    private int[][] map;

    public void setMap(int[][] map) {
        this.map = map;
    }

    public boolean isCollidingWithTile(double x, double y, String entity) {
        int row = (int) (y / TILE_SIZE);
        int col = (int) (x / TILE_SIZE);

        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
            return false;
        }

        int tileValue = map[row][col];
        if (entity.equals("player")) {
            return tileValue == 1 || tileValue == 2;
        } else {
            return tileValue == 2;
        }

    }
}
