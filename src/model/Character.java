package model;

class Character {
    /*
        Represents a unit in the game.
        Stores logical attributes like position, health, movement range, and stats.
         */
    private int x;
    private int y;
    private int movement_range;
    public int[] getXY(){
        int[] coords = {this.x, this.y};
        return coords;
    }
}
