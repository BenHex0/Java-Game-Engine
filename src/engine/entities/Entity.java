package engine.entities;

import engine.graphics.Renderer;
import engine.graphics.Sprite;
import engine.levels.Level;
import engine.utilities.Vector2i;

public abstract class Entity {
    // position in pixels
    protected double x, y;
    protected Level level;
    protected Sprite sprite;
    public Vector2i pviot;

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected Direction dir;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2i getPviot() {
        int xPivot = sprite.getSpritPivot().getX() + (int) x;
        int yPivot = sprite.getSpritPivot().getY() + (int) y;
        return new Vector2i(xPivot, yPivot);
    }

    public abstract void update();

    public void render(Renderer renderer) {

    }

    public void init(Level level) {
        this.level = level;
    }

    public boolean collision(double xAxis, double yAxis) {
        boolean solid = false;

        for (int c = 0; c < 4; c++) {
            double xt = ((x + xAxis) - (c % 2) * 10 + 4) / 16;
            double yt = ((y + yAxis) - (c / 2) * 8 + 7) / 16;
            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);
            if (c % 2 == 0)
                ix = (int) Math.floor(xt);
            if (c / 2 == 0)
                iy = (int) Math.floor(yt);
            if (level.getTile(ix, iy).solid()) {
                solid = true;
            }
        }

        return solid;
    }
}
