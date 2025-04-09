package cz.cvut.fel.malyada1.squareland.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapSaver {

    public static void saveMap(int[][] map, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(map.length + "\n");
            writer.write(map[0].length + "\n");
            for (int[] row : map) {
                for (int tile : row) {
                    writer.write(tile + " ");
                }
                writer.write("\n");
            }
        }
    }
}