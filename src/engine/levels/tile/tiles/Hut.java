package engine.levels.tile.tiles;

import engine.graphics.*;
import engine.levels.tile.Tile;

public class Hut extends Tile {

    public Hut(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Renderer renderer) {
        renderer.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return false;
    }
}
