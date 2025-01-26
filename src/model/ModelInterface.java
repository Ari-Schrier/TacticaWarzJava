package model;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ModelInterface {
    /*
    Access point for model
     */
    private final Map map;

    public ModelInterface(int width, int height, int[] tiles, int[] blockers) {
        this.map = new Map(width, height, tiles, blockers);

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

    public boolean getWalkable(int x, int y) {
        return this.map.isWalkable(x, y);
    }

    private Character getOccupant(int x, int y){
        return map.getTile(x, y).getOccupant();
    }

    public boolean isValidAction(int x, int y){
        return this.getOccupant(x, y) == null;
    }

    public int[][] getMoveableTiles(int startX, int startY){
        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        Character targeted_character = this.getOccupant(startX, startY);
        Set<int[]> walkableTiles = new HashSet<>() {
            @Override
            public boolean add(int[] ints) {
                for (int[] element : this){
                    if (Arrays.equals(element, ints)){return false;}
                }
                return super.add(ints);
            }
        };
        walkableTiles.add(new int[]{startX, startY});
        for (int i = 0; i < 3; i++){
            Set<int[]> toAdd = new HashSet<>();
            for(int[] tile : walkableTiles){
                int x = tile[0];
                int y = tile[1];
                for(int[] dir:directions){
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (getWalkable(newX, newY)){
                        toAdd.add(new int[]{newX, newY});
                    }
                }
            }
            walkableTiles.addAll(toAdd);
        }
        for (int[] tile : walkableTiles){System.out.println("(" + tile[0] +", " + tile[1] + " )");}
        return walkableTiles.toArray(new int[0][]);
    }

    public void moveCharacter(int oldX, int oldY, int newX, int newY){
        Character target = this.getOccupant(oldX, oldY);
        this.map.getTile(oldX, oldY).setOccupant(null);
        this.map.getTile(newX, newY).setOccupant(target);
    }
}
