package model;

class Tile {
    /*
    Represents an individual map tile.
    Stores properties like position, walkability, terrain type, and movement cost.
     */
    private final int x, y; // Grid position
    private boolean walkable;
    private final int terrainType; // Index for terrain in spritesheet
    private Character occupant;

    Tile(int x, int y, int terrainType) {
        this.x = x;
        this.y = y;
        this.terrainType = terrainType;
        this.occupant = null;
        this.walkable = true;
    }

    boolean isWalkable(){
        return this.walkable && this.occupant == null;
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

    Character getOccupant(){
        return this.occupant;
    }

    void setOccupant(Character c){
        this.occupant = c;
    }

    void setWalkable(boolean b) {
        this.walkable = b;
    }
}
