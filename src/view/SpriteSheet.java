package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
    private final BufferedImage sheet;
    private final int spriteWidth;
    private final int spriteHeight;

    /**
     * Constructor for SpriteSheet.
     *
     * @param filename    Path to the spritesheet image.
     * @param spriteWidth Width of each sprite in the sheet.
     * @param spriteHeight Height of each sprite in the sheet.
     * @throws IOException if the file cannot be read.
     */
    public SpriteSheet(String filename, int spriteWidth, int spriteHeight) throws IOException {
        this.sheet = ImageIO.read(new File(filename));
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    /**
     * Gets a sprite from the spritesheet at a specified index.
     *
     * @param index The index of the sprite (row-major order).
     * @return A BufferedImage of the sprite.
     */
    BufferedImage getSprite(int index) {
        int spritesPerRow = sheet.getWidth() / spriteWidth;
        int x = (index % spritesPerRow) * spriteWidth;
        int y = (index / spritesPerRow) * spriteHeight;

        return sheet.getSubimage(x, y, spriteWidth, spriteHeight);
    }

    BufferedImage getCharacter(int facing, int index){return sheet.getSubimage(index * spriteWidth, facing * spriteHeight, spriteWidth, spriteHeight);}

}
