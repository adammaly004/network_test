package cz.cvut.fel.malyada1.squareland.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static cz.cvut.fel.malyada1.squareland.utils.Constants.TILE_SIZE;

public class Map {

    private int[][] mapArray;


    public Map(String filePath) {
        initMap(filePath);
    }

    private void initMap(String filePath){
        try {
            this.mapArray = cz.cvut.fel.malyada1.squareland.utils.MapLoader.loadMap(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            this.mapArray = new int[0][0];
        }
    }

    public void draw(Pane root) {
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                Rectangle tile = new Rectangle(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                switch (mapArray[i][j]) {
                    case 1:
                        tile.setFill(Color.BLUE);
                        break;
                    case 2:
                        tile.setFill(Color.GRAY);
                        break;
                    default:
                        tile.setFill(Color.GREEN);
                        break;
                }
                root.getChildren().add(tile);
            }
        }
    }

    public void updateTile(Pane root, int row, int col) {
        int tileIndex = row * mapArray[0].length + col;
        Rectangle existingTile = (Rectangle) root.getChildren().get(tileIndex);

        switch (mapArray[row][col]) {
            case 1:
                existingTile.setFill(Color.BLUE);
                break;
            case 2:
                existingTile.setFill(Color.GRAY);
                break;
            default:
                existingTile.setFill(Color.GREEN);
                break;
        }
    }

    public void incrementTile(int row, int col) {
        mapArray[row][col] = (mapArray[row][col] + 1) % 3;
    }
    public void setTile(int row, int col, int value) {
        mapArray[row][col] = value;
    }

    public int[][] getMapArray() {
        return mapArray;
    }

    public void setMapArray(String filePath) {
        initMap(filePath);
    }
}
