package view;

import java.util.Map;

class AnimationLibrary {
    static final Map<String, int[]> MY_MAP = Map.of(
            "stand", new int[]{0, 1},
            "walk", new int[]{2, 3},
            "melee", new int[]{4, 5, 6, 7},
            "ranged", new int[]{8, 9, 10, 11},
            "magic", new int[]{12, 13, 14},
            "punch", new int[]{15, 16, 17},
            "damage", new int[]{18, 19, 20},
            "die", new int[]{21, 22, 23}
    );
}
