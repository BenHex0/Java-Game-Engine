package game.entities;

import engine.entities.Entity;
import engine.graphics.Animation;
import engine.graphics.Renderer;
import engine.graphics.Sprite;
import engine.graphics.SpriteSheet;
import engine.input.InputHandler;


public class Player extends Entity {

    private InputHandler input;
    private int xAxis, yAxis;
    private Animation anim_down = new Animation(SpriteSheet.playerAnimDown, 16, 16, 2);
    private boolean walking = false;
    private double speed = 3.5;
    public boolean die = false;

    public Player(double x, double y, InputHandler inputHandler) {
        this.x = x;
        this.y = y;
        this.input = inputHandler;
        sprite = Sprite.player;
    }

    public void update() {
        xAxis = 0;
        yAxis = 0;
        control();

        if (walking) {
            anim_down.update();
        }
        move(xAxis, yAxis, speed);
    }

    void control() {
        if (input.isKeyPressed(InputHandler.Key.UP)) {
            // System.out.println("W");
            // move up
            yAxis = 1;
        }
        if (input.isKeyPressed(InputHandler.Key.DOWN)) {
            // System.out.println("S");
            // move down
            yAxis = -1;
        }
        if (input.isKeyPressed(InputHandler.Key.LEFT)) {
            // System.out.println("A");
            // move left
            xAxis = -1;
        }
        if (input.isKeyPressed(InputHandler.Key.RIGHT)) {
            // System.out.println("D");
            // move right
            xAxis = 1;
        }
        
    }

    void move(int xAxis, int yAxis, double speed) {

        if (xAxis > 0) {
            dir = Direction.RIGHT;
            walking = true;
        }
        else if (xAxis < 0) {
            dir = Direction.LEFT;
            walking = true;
        }
        else if (yAxis > 0) {
            dir = Direction.DOWN;
            walking = true;
        }
        else if (yAxis < 0) {
            dir = Direction.UP;
            walking = true;
        }
        else {
            walking = false;
        }

        // Move X
        if (xAxis != 0) {
            x += (Math.signum(xAxis) * speed);
        }

        // Move Y
        if (yAxis != 0) {
            y += ((Math.signum(yAxis) * speed) * -1);
        }
    }

    @Override
    public void render(Renderer renderer) {
        sprite = anim_down.getSprite();
        renderer.camera.cameraTarget(x, y, sprite.getWidth(), sprite.getHeight());
        int drawX = (int) Math.round(x - renderer.camera.getxOffset());
        int drawY = (int) Math.round(y - renderer.camera.getyOffset());
        renderer.renderSprite(drawX, drawY, sprite, false);
    }
}
