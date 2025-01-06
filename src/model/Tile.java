package model;

class Tile {
    /*
    Represents an individual map tile.
    Stores properties like position, walkability, terrain type, and movement cost.
     */
    private final int x, y; // Grid position
    private final int terrainType; // Index for terrain in spritesheet

    Tile(int x, int y, int terrainType) {
        this.x = x;
        this.y = y;
        this.terrainType = terrainType;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getTerrainType() {
        return terrainType;
    }
}
