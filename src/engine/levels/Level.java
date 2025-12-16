package engine.levels;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import engine.graphics.*;
import engine.levels.tiles.*;

public class Level {
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
        System.out.println("Generating level...");
    }

    private Random random = new Random();

    protected void generateLevel() {
        for (int i = 0; i < width * height; i++) {
            tiles[i] = random.nextInt(8);
        }
    }

    protected void loadLevel(String path) {
         try {
            java.io.File file = new java.io.File(path);
            BufferedImage image = ImageIO.read(file);
            width = image.getWidth();
            height = image.getHeight();
            tiles = new int[width * height];
            image.getRGB(0, 0, width, height, tiles, 0, width);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }

    public void render(int xScroll, int yScroll, Renderer renderer) {
        int tileSize = 16;
        renderer.setOffset(xScroll, yScroll);
        // System.out.println("X: " + xScroll + " Y:" + yScroll);

        // FIX FOR X0 and Y0:
        // Integer division xScroll / tileSize correctly finds the index of the tile
        // that the camera is currently looking at. However, if the scroll is
        // positive (e.g., 17) and not a multiple of 16, we missed the tile at index 0.

        // We want to ensure we draw the first visible tile, even if it's partially
        // off-screen (left/top).
        // The calculation xScroll / tileSize already works for this if xScroll > 0.
        int x0 = xScroll >> tileSize;
        int y0 = yScroll >> tileSize;

        // Use a modulus check to see if we need to adjust, but since xScroll is always
        // positive
        // and represents the offset, the division usually handles the visible tile
        // correctly.
        // However, the *real* problem usually occurs when xScroll is not exactly 0.

        // The standard *safe* approach for positive offsets is:
        // For x0, simply start at the index the offset is currently at.

        // For x1 and y1, you need the ceiling function to ensure the last partial tile
        // is included.
        // The '+ tileSize' in the numerator achieves the ceiling effect for integer
        // division.
        int x1 = (xScroll + renderer.screenWidth + tileSize - 1) / tileSize;
        int y1 = (yScroll + renderer.screenHeight + tileSize - 1) / tileSize;

        // The most common and robust way to guarantee the first partially visible tile
        // is:

        // Ensure the loop is clamped by the level boundaries
        if (x0 < 0)
            x0 = 0;
        if (y0 < 0)
            y0 = 0;
        if (x1 > width)
            x1 = width;
        if (y1 > height)
            y1 = height;

        // ... rest of the loop remains the same ...
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                // NOTE: The tile.render method must now convert (x, y) index back to pixel
                // position
                getTile(x, y).render(x, y, renderer);
            }
        }
    }

    // grass0 = green => 0xff1CC809
    // grass1 = green + yello => 0xffF4E80B
    // tree = brown => 0xff703405

    Tile[] grassTiles = {
            Tile.grass0,
            Tile.grass1,
            Tile.grass2,
            Tile.grass3,
            Tile.grass4,
            Tile.grass5,
            Tile.grass6,
            Tile.grass7,
            Tile.grass8
    };

    public Tile getTile(int x, int y) {

        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.voidTile;
        if (tiles[x + y * width] == 0)
            return grassTiles[0];
        if (tiles[x + y * width] == 1)
            return grassTiles[1];
        if (tiles[x + y * width] == 2)
            return grassTiles[2];
        if (tiles[x + y * width] == 3)
            return grassTiles[3];
        if (tiles[x + y * width] == 4)
            return grassTiles[4];
        if (tiles[x + y * width] == 5)
            return grassTiles[5];
        if (tiles[x + y * width] == 6)
            return grassTiles[6];
        if (tiles[x + y * width] == 7)
            return grassTiles[7];
        if (tiles[x + y * width] == 8)
            return grassTiles[8];
        // if (tiles[x + y * width] == 0xff1CC809)
        // return Tile.grass0;
        // if (tiles[x + y * width] == 0xffF4E80B)
        // return Tile.grass1;
        // if (tiles[x + y * width] == 0xff703405)
        // return Tile.tree;
        return Tile.voidTile;
    }
}