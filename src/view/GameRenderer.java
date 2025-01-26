package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameRenderer {
    /*
    Central class for rendering the game.
    Draws the map, characters, overlays, and other visual elements.
    */
    private int scale;
    private int[][] baseTiles;
    private int[][] blockTiles;
    private TileAnimationGenerator tileAnimations;
    private int mapWidth;
    private int mapHeight;
    private int activeFrame;
    private int[][] selectableTiles;
    public GameRenderer(int[] base, int[] blocks, int height, int width, int scale){
        try{

            this.scale = scale;
            SpriteSheet terrain = new SpriteSheet("src/assets/images/tiles/punyworld-overworld-tileset.png", 16, 16);
            this.tileAnimations = new TileAnimationGenerator(terrain);
            this.mapWidth = width;
            this.mapHeight = height;
            this.selectableTiles = new int[][] {};
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

    public void setSelectableTiles(int[][] tiles){
        this.selectableTiles = tiles;
    }

    public void playAnimation(String todo, CharacterView person){
        int[] action = AnimationLibrary.MY_MAP.get(todo);
        person.setAnimation(action);
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
        int tile_counter = 0;
        for (int[] tile : selectableTiles){
            g.drawImage(this.tileAnimations.getAnimation(129, 0), tile[0]*16*scale, tile[1]*16*scale, 16*scale, 16*scale, null);
            tile_counter += 1;
        }
        //System.out.println("Printed a total of " + tile_counter + " tiles!");
        g.drawImage(person.getSprite(), (person.getX() * 16 * scale) - (8*scale), (person.getY() * 16 * scale) - (8*scale), 32 * scale, 32 * scale, null);
        this.activeFrame += 1;
        if (this.activeFrame >3){this.activeFrame=0;}
    }
}
