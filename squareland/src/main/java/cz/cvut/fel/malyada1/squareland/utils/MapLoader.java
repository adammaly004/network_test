package cz.cvut.fel.malyada1.squareland.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {

    public static int[][] loadMap(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            int rows = Integer.parseInt(line);
            int cols = Integer.parseInt(reader.readLine());
            int[][] map = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                line = reader.readLine();
                String[] tokens = line.split(" ");
                for (int j = 0; j < cols; j++) {
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }
            return map;
        }
    }
}