package engine.graphics;

public class Animation extends Sprite {

    private int frame = 0;
    private Sprite sprite;
    private int rate = 7;
    private int animationSize = 0;
    private int length = -1;
    private int time = 0;

    public Animation(SpriteSheet sheet, int width, int height, int length) {
        super(sheet, width, height);
        this.length = length;
        sprite = sheet.getSprites()[0];
        if (length > sheet.getSprites().length)
            System.err.println("Error! length of animaion is too long");
    }

    public void update() {
        time++;
        if (time % rate == 0) {
            if (frame >= length - 1)
                frame = 0;
            else
                frame++;
            sprite = sheet.getSprites()[frame]; // change zero back to frame

        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int index) {
        if (index > sheet.getSprites().length - 1) {
            System.err.println("Index out of bounds in " + this);
            return;
        }
        sprite = sheet.getSprites()[index];
    }
}
