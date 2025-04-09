package cz.cvut.fel.malyada1.squareland.utils;

import cz.cvut.fel.malyada1.squareland.model.AmmunitionPack;
import cz.cvut.fel.malyada1.squareland.model.Heal;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static cz.cvut.fel.malyada1.squareland.utils.Constants.TILE_SIZE;

public class ItemSpawner {
    private static final int ITEM_SPAWN_RATE = 10; // Adjust this value to control the spawn rate
    private static final List<int[]> validTiles = new ArrayList<>();
    private static final List<AmmunitionPack> items = new ArrayList<>();


    public ItemSpawner(int[][] map) {
        setValidTiles(map);
    }

    public void setMap(int[][] map) {
        validTiles.clear();
        setValidTiles(map);
    }

    private void setValidTiles(int[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == 0) { // Assuming 0 represents a valid tile
                    validTiles.add(new int[]{row, col});
                }
            }
        }
    }

    public Rectangle spawnItems() {
        if (!validTiles.isEmpty()) {
            Random random = new Random();
            int[] selectedTile = validTiles.get(random.nextInt(validTiles.size()));

            // Calculate the position for the AmmunitionPack
            double x = selectedTile[1] * TILE_SIZE + TILE_SIZE / 2.0 - 5; // Centered on the tile
            double y = selectedTile[0] * TILE_SIZE + TILE_SIZE / 2.0 - 5;

            // Create and add the AmmunitionPack to the map
            if (random.nextBoolean()) {
                return new AmmunitionPack(x, y);
            } else {
                return new Heal(x, y);
            }
            //return new AmmunitionPack(x, y);
        }
        return null;
    }
}
