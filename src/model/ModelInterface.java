package model;

public class ModelInterface {
    /*
    Access point for model
     */
    private final Map map;

    public ModelInterface(int width, int height, int[][] tiles) {
        this.map = new Map(width, height, tiles);
    }

    public int getMapWidth() {
        return map.getWidth();
    }

    public int getMapHeight() {
        return map.getHeight();
    }

    public int getTileTerrainType(int x, int y) {
        Tile tile = map.getTile(x, y);
        return tile != null ? tile.getTerrainType() : -1; // Return -1 if the tile doesn't exist
    }

}
