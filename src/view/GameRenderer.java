package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameRenderer {
    /*
    Central class for rendering the game.
    Draws the map, characters, overlays, and other visual elements.
    */
    static int scale = 2;
    private int[][] baseTiles;
    private int[][] blockTiles;
    private TileAnimationGenerator tileAnimations;
    private int mapWidth;
    private int mapHeight;
    private int activeFrame;
    public GameRenderer(int[] base, int[] blocks, int height, int width){
        try{

            SpriteSheet terrain = new SpriteSheet("src/assets/images/tiles/punyworld-overworld-tileset.png", 16, 16);
            this.tileAnimations = new TileAnimationGenerator(terrain);
            this.mapWidth = width;
            this.mapHeight = height;
            this.baseTiles = new int[mapHeight][mapWidth];
            this.blockTiles = new int[mapHeight][mapWidth];
            this.activeFrame = 0;
            int i = 0;
            for (int y = 0; y < mapHeight; y++) {
                for (int x = 0; x < mapWidth; x++) {
                    int foundNumber = base[i];
                    this.baseTiles[y][x] = foundNumber-1;
                    foundNumber = blocks[i];
                    this.blockTiles[y][x] = foundNumber-1;
                    i += 1;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g, CharacterView person) {
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                if (this.baseTiles[y][x] >=0){
                BufferedImage terrainSprite = this.tileAnimations.getAnimation(this.baseTiles[y][x], this.activeFrame);

                g.drawImage(terrainSprite, x * 16 * scale, y * 16 * scale, 16 * scale, 16 * scale, null);}
                if (this.blockTiles[y][x]>=0){
                BufferedImage terrainSprite = this.tileAnimations.getAnimation(this.blockTiles[y][x], this.activeFrame);
                g.drawImage(terrainSprite, x * 16 * scale, y * 16 * scale, 16 * scale, 16 * scale, null);}
            }
        }
        g.drawImage(person.getSprite(), person.getX() * 16 * scale, person.getY() * 16 * scale, 32 * scale, 32 * scale, null);
        this.activeFrame += 1;
        if (this.activeFrame >3){this.activeFrame=0;}
    }
}
