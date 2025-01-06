package view;

import java.awt.image.BufferedImage;

public class RenderedTile {
    private BufferedImage sprite;
    private int currentFrame;

    RenderedTile(SpriteSheet sheet, int id){
        this.currentFrame = 0;
        this.sprite = sheet.getSprite(id);

    }

    BufferedImage getSprite() {
        return sprite;
    }
}
