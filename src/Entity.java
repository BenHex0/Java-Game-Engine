public abstract class Entity {
    Position position;
    Sprite sprite;
    InputHandler input;
    Camera camera;

    Entity() {
        position = new Position();
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public Position gePosition() {
        return position;
    }

    public void move(int x, int y) {
        position.x += x;
        position.y += y;
    }

    public void start() {
    }

    public void update() {
    }

    public void addSpriteComponent(Sprite sp) {
        this.sprite = sp;
        if (sprite != null) {
            sprite.setPosition(position.x, position.y);
        }
    }

    public void addInputHandlerComponent() {
        input = new InputHandler();
    }

    public void addCameraComponent() {
        camera = new Camera(0, 0);
    }
}