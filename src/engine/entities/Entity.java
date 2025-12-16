package engine.entities;

import engine.graphics.Renderer;
import engine.graphics.Sprite;
import engine.levels.Level;
import engine.utilities.Position;


public abstract class Entity {

    protected Position position;
    protected Level level;
    protected Sprite sprite;

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public Position gePosition() {
        return position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract void update();
    
    public void render(Renderer renderer) {

    }


    public void init(Level level) {
        this.level = level;
    }
}
