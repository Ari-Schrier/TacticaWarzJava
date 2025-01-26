import model.ModelInterface;
import view.CharacterView;
import view.GameRenderer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import org.json.JSONObject;
import org.json.JSONArray;
import view.SpriteSheet;

public class Main {
    static int tilesize = 16;
    static int scale = 2;

    private static int[] handleMouseClick(int mouseX, int mouseY) {
        // Convert mouse coordinates to tile coordinates
        int tileX = mouseX / (tilesize * scale);
        int tileY = mouseY / (tilesize * scale);

        return new int[]{tileX, tileY};
    }

    public static void main(String[] args) {
        try {
            String content = new String(Files.readAllBytes(new File("src/assets/maps/testmap.tmj").toPath()));
            JSONObject json = new JSONObject(content);

            int width = json.getInt("width");
            int height = json.getInt("height");
            JSONArray layers = json.getJSONArray("layers");
            JSONArray _base = layers.getJSONObject(0).getJSONArray("data");
            JSONArray _blocks = layers.getJSONObject(1).getJSONArray("data");
            int[] base = new int[height * width];
            int[] blocks = new int[height * width];
            for (int i = 0; i < height * width; i++) {
                base[i] = _base.getInt(i);
                blocks[i] = _blocks.getInt(i);
            }

            ModelInterface model = new ModelInterface(width, height, base, blocks);

            GameRenderer renderer = new GameRenderer(base, blocks, height, width, scale);

            // Setup window
            JFrame frame = new JFrame("Tactical RPG");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);

            SpriteSheet character = new SpriteSheet("src/assets/images/characters/Soldier-Blue.png", 32, 32);
            CharacterView me = new CharacterView(character);
            me.turnTo(6);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    renderer.render(g, me);
                }
            };

            // Add mouse listener to capture clicks
            panel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int x = e.getX() / (16 * scale);
                    int y = e.getY() / (16 * scale);
                    System.out.println("Tile clicked: (" + x + ", " + y + ")");
                    boolean walkable = model.getWalkable(x, y);
                    if (walkable){renderer.setSelectableTiles(model.getMoveableTiles(x,y));}
                    else{System.out.println("Not walkable!");}
                    me.turn();
                }
            });

            frame.add(panel);
            frame.setVisible(true);

            // Redraw periodically
            Timer timer = new Timer(250, e -> panel.repaint());
            timer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
