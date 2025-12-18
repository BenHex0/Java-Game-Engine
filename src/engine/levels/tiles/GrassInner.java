package engine.levels.tiles;

import engine.graphics.*;

public class GrassInner extends Tile {

    public GrassInner(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Renderer renderer) {
        renderer.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return false;
    }
}
