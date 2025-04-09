package cz.cvut.fel.malyada1.squareland.utils;

import java.util.Arrays;
import java.util.Random;
import static cz.cvut.fel.malyada1.squareland.utils.Constants.*;

public class CreateArray {
    static Random random = new Random();

    static int rows = HEIGHT / TILE_SIZE;
    static int cols = WIDTH / TILE_SIZE;

    public static int[][] createMap(){
        int[][] mapArray = new int[rows][cols];
        // Initialize the mapArray with some values (0, 1, 2)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    mapArray[i][j] = 0; // Border
                } else if (i % 2 == 0 && j % 2 == 0) {
                    mapArray[i][j] = random.nextInt(5); // Some pattern
                } else {
                    mapArray[i][j] = random.nextInt(5); // Default
                }
            }
        }

        return mapArray;
    }
    public static int[][] crateBlankMap(){
        int[][] mapArray = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mapArray[i][j] = 0;
            }
        }
        return mapArray;
    }
}
