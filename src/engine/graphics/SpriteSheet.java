package engine.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SpriteSheet {
    private String path;
    public final int SIZE;
    public final int WIDTH;
    public final int HEIGHT;
    public int[] pixels;

    // Playeras
    /////////////////////////////////////////////////////////////
    public static SpriteSheet playerSheet = new SpriteSheet("assets/player/walk/GreensBeans.png", 32, 64);
    public static SpriteSheet playerAnimDown = new SpriteSheet(playerSheet, 0, 0, 2, 1, 16, 16);
    public static SpriteSheet playerAnimUp = new SpriteSheet(playerSheet, 0, 1, 2, 1, 16, 16);
    public static SpriteSheet playerAnimLeft = new SpriteSheet(playerSheet, 0, 2, 2, 1, 16, 16);
    public static SpriteSheet playerAnimRight = new SpriteSheet(playerSheet, 0, 3, 2, 1, 16, 16);

    // Enemy
    /////////////////////////////////////////////////////////////
    public static SpriteSheet enemySheet = new SpriteSheet("assets/Monster/monsterSheet.png", 32, 64);
    public static SpriteSheet enemyAnimDown = new SpriteSheet(enemySheet, 0, 0, 2, 1, 16, 16);
    public static SpriteSheet enemyAnimUp = new SpriteSheet(enemySheet, 0, 1, 2, 1, 16, 16);
    public static SpriteSheet enemyAnimLeft = new SpriteSheet(enemySheet, 0, 2, 2, 1, 16, 16);
    public static SpriteSheet enemyAnimRight = new SpriteSheet(enemySheet, 0, 3, 2, 1, 16, 16);
    /////////////////////////////////////////////////////////////

    // Tile
    /////////////////////////////////////////////////////////////
    public static SpriteSheet tiles = new SpriteSheet("assets/world/Tilemap.png", 192, 160);
    /////////////////////////////////////////////////////////////
    /// 
    private Sprite sprites[];

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteW, int spriteH) {
        int xx = x * spriteW;
        int yy = y * spriteH;
        int w = width * spriteW;
        int h = height * spriteH;

        // Logic for "SIZE" usually implies square, so we set to -1 if non-square
        if (spriteW == spriteH)
            SIZE = spriteW;
        else
            SIZE = -1;

        WIDTH = w;
        HEIGHT = h;
        pixels = new int[w * h];

        // Extract the section of the sheet
        for (int y0 = 0; y0 < h; y0++) {
            int yp = yy + y0;
            for (int x0 = 0; x0 < w; x0++) {
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }

        int frame = 0;
        sprites = new Sprite[width * height];
        for (int ya = 0; ya < height; ya++) {
            for (int xa = 0; xa < width; xa++) {
                // Use specific Width and Height here!
                int[] spritePixels = new int[spriteW * spriteH];
                for (int y0 = 0; y0 < spriteH; y0++) {
                    for (int x0 = 0; x0 < spriteW; x0++) {
                        spritePixels[x0 + y0 * spriteW] = pixels[(x0 + xa * spriteW)
                                + (y0 + ya * spriteH) * WIDTH];
                    }
                }
                // Ensure your Sprite class constructor can handle (pixels, width, height)
                Sprite sprite = new Sprite(spritePixels, spriteW, spriteH);
                sprites[frame++] = sprite;
            }
        }
    }

    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        SIZE = -1;
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[WIDTH * HEIGHT];
        load();
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    private void load() {
        try {
            java.io.File file = new java.io.File(path);
            System.out.println("Loading from: " + file.getAbsolutePath());
            BufferedImage image = ImageIO.read(file);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
            System.out.println("Loaded sprite sheet successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
