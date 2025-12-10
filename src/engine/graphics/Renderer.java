package engine.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Renderer {
    public int screenWidth, screenHeight;
    public int[] screenBuffer;
    public int xOffset, yOffset;
    public BufferedImage frame;



    public Renderer(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
        screenBuffer = new int[width * height];
        frame = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        screenBuffer = ((DataBufferInt) frame.getRaster().getDataBuffer()).getData();
    }

    public void clear() {
        for (int i = 0; i < screenBuffer.length; i++) {
            screenBuffer[i] = 0;
        }
    }

    
    public void renderSprite(int xPosition, int yPosition, Sprite sprite, boolean fixed) {
        if (fixed) {
            xPosition -= xOffset;
            yPosition -= yOffset;
        }

        for (int y = 0; y < sprite.getHeight(); y++) {
            int yAbsolute = y + yPosition;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xAbsolute = x + xPosition;
                if (xAbsolute < 0 || xAbsolute >= screenWidth || yAbsolute < 0 || yAbsolute >= screenHeight)
                    continue;
                screenBuffer[x + y * screenWidth] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }

    }


    public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int x = xp; x < xp + width; x++) {
            if (x < 0 || x >= this.screenWidth || yp >= this.screenHeight)
                continue;
            if (yp > 0)
                screenBuffer[x + yp * this.screenWidth] = color;
            if (yp + height >= this.screenHeight)
                continue;
            if (yp + height > 0)
                screenBuffer[x + (yp + height) * this.screenWidth] = color;
        }

        for (int y = yp; y <= yp + height; y++) {
            if (xp >= this.screenWidth || y < 0 || y >= this.screenHeight)
                continue;
            if (xp > 0)
                screenBuffer[xp + y * this.screenWidth] = color;
            if (xp + width >= this.screenWidth)
                continue;
            if (xp + width > 0)
                screenBuffer[(xp + width) + y * this.screenWidth] = color;
        }
    }

}
