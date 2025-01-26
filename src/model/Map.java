package model;

class Map {
    /*
    Represents the game map as a grid of Tile objects.
    Provides utility methods for pathfinding and retrieving tiles by coordinates.
     */
    private final int width, height;
    private final Tile[][] tiles;

    Map(int width, int height, int[] tile_values, int[] blocking_values) {
        this.width = width;
        this.height = height;
        Tile[][] ArrayInProgress = new Tile[height][width];
        int i = 0;
        for (int y = 0; y < height; y++){
            for (int x = 0; x <width; x++) {
                ArrayInProgress[y][x] = new Tile(x, y, tile_values[i]);
                if (blocking_values[i]!=0){ArrayInProgress[y][x].setWalkable(false);}
                i += 1;
            }
        }
        this.tiles = ArrayInProgress;
    }

    Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return tiles[y][x];
    }

    boolean isWalkable(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return tiles[y][x].isWalkable();
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }
}
