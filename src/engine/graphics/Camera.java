package engine.graphics;

public class Camera {
    private double xOffset, yOffset;
    private int screenWidth, screenHeight;

    public Camera(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public double getxOffset() {
        return xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void cameraTarget(double x, double y, int width, int height) {
        xOffset = x - screenWidth / 2 + width / 2;
        yOffset = y - screenHeight / 2 + height / 2;
    }
}
