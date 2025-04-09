package cz.cvut.fel.malyada1.squareland.model;

import static cz.cvut.fel.malyada1.squareland.utils.Constants.*;


public class MapEditor {
    private Map map;
    String path = MAP_PATH + "blankMap.txt";
    public MapEditor() {
        map = new Map(path);
    }

    public Map getMap() {
        return map;
    }

}
