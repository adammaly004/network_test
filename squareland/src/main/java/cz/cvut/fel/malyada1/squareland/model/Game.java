package cz.cvut.fel.malyada1.squareland.model;

import cz.cvut.fel.malyada1.squareland.utils.ItemSpawner;
import javafx.stage.FileChooser;
import cz.cvut.fel.malyada1.squareland.utils.Collisions;

import java.io.File;

import static cz.cvut.fel.malyada1.squareland.utils.Constants.MAP_PATH;


public class Game {
    private Player player1;
    private Player player2;
    private Map map;
    private Collisions collision;
    private String filePath = "map.txt";
    private ItemSpawner itemSpawner;

    public Game() {

        map = new Map(MAP_PATH + filePath);
        collision = new Collisions();
        collision.setMap(map.getMapArray());
        player1 = new Player(0, 0, 20, 20, 20, "player1", collision);
        player2 = new Player(40, 40, 20, 20, 20, "player2", collision);
        itemSpawner = new ItemSpawner(map.getMapArray());
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public Map getMap() {
        return map;
    }
    public ItemSpawner getItemSpawner() {
        return itemSpawner;
    }
    public String getFilePath() {return filePath;}
    public void chooseMapFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(MAP_PATH));
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.getPath();
        } else {
            filePath = MAP_PATH + "map.txt";
        }
        map.setMapArray(filePath);
        collision.setMap(map.getMapArray());
        itemSpawner.setMap(map.getMapArray());
    }
    public void reset() {
        player1.reset();
        player2.reset();
    }
}