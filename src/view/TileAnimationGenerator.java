package view;

import java.awt.image.BufferedImage;
import java.util.*;

public class TileAnimationGenerator {
    private SpriteSheet sheet;
    private Map<Integer, List<BufferedImage>> animations;

    public TileAnimationGenerator(SpriteSheet sheet){
        this.sheet = sheet;
        this.animations = this.generateAnimations();
    }

    public BufferedImage getAnimation(int key, int index){
        if (this.animations.containsKey(key)){
            return this.animations.get(key).get(index);}
        else{return this.sheet.getSprite(130);}
    }

    private Map<Integer, List<BufferedImage>> generateAnimations() {
        Map<Integer, List<BufferedImage>> animations = new HashMap<>();

        // Default: Tiles have 4 static frames (e.g., same tile repeated)
        for (int i = 0; i < 260; i++) {
            animations.put(i, Arrays.asList(sheet.getSprite(i), sheet.getSprite(i), sheet.getSprite(i), sheet.getSprite(i)));
        }

        // Define special cases with patterns
        List<Integer> greenPonds = Arrays.asList(270, 297, 324, 351);
        for (int i : greenPonds) {
            for (int j = i; j < i + 4; j++) {
                animations.put(j, generateFrames(j, 108, 4));
            }
        }

        List<Integer> threeByThrees = Arrays.asList(274, 277, 282, 287, 292);
        for (int i : threeByThrees) {
            for (int j : Arrays.asList(i, i + 1, i + 2, i + 27, i + 28, i + 29, i + 54, i + 55, i + 56)) {
                animations.put(j, generateFrames(j, 81, 4));
            }
        }

        for (int each : Arrays.asList(280, 281, 285, 286, 307, 308, 312, 313)) {
            animations.put(each, generateFrames(each, 54, 4));
        }

        for (int each : Arrays.asList(290, 291, 295, 296, 317, 318, 322, 323)) {
            animations.put(each, generateFrames(each, 81, 4));
        }

        animations.put(598, animations.put(598, Arrays.asList(sheet.getSprite(598), sheet.getSprite(599), sheet.getSprite(600), sheet.getSprite(601))));

        // Static tiles from 702 to 1019
        for (int i = 702; i < 1019; i++) {
            animations.put(i, Arrays.asList(sheet.getSprite(i), sheet.getSprite(i), sheet.getSprite(i), sheet.getSprite(i)));
        }

        return animations;
    }

    // Helper method to generate frames for a tile
    private List<BufferedImage> generateFrames(int start, int step, int frameCount) {
        List<BufferedImage> frames = new ArrayList<>();
        for (int k = 0; k < frameCount; k++) {
            frames.add(sheet.getSprite(start + (step * k)));
        }
        return frames;
    }

}
