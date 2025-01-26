package view;

import java.awt.image.BufferedImage;

public class CharacterView {
    /*
    Represents the visual state of a character.
    Handles sprite animations and interpolates character positions during movement.
     */
    private int x;
    private int y;
    private SpriteSheet sheet;
    private int facing;
    private int[] animation;
    private int frame;
    private Boolean clockwise;

    public CharacterView(SpriteSheet sheet){
        this.x = 3;
        this.y = 3;
        this.sheet = sheet;
        this.facing = 0;
        this.clockwise = false;
        this.animation = new int[] {4, 5, 6, 7};
        this.frame = 0;
    }

    public int getY() {
        return this.y;
    }
    public int getX() {
        return this.x;
    }

    public void setAnimation(int[] frames){
        this.animation = frames;
    }

    public int turnTo(int newDirection){
        int clockwise = (this.facing-newDirection) % 8;
        int counterclockwise = (newDirection-this.facing) %8;
        if (clockwise > counterclockwise){this.clockwise = false;}
        else{this.clockwise = true;}
        return Math.min(clockwise, counterclockwise);
    }

    public void turn(){
        if (this.clockwise){
            this.facing -= 1;
            if (this.facing < 0){this.facing = 7;}
        }
        else {
            this.facing += 1;
            if (this.facing > 7){this.facing = 0;}
        }
    }

    public BufferedImage getSprite(){
        BufferedImage img = this.sheet.getCharacter(this.facing, this.animation[this.frame]);
        this.frame += 1;
        if (this.frame >= this.animation.length){
            this.frame = 0;
            this.animation = new int[] {0, 1};
        }
        return img;
    }

}
