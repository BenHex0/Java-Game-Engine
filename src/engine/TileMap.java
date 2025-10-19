import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TileMap {
    Tile tiles[];
    int mapTileNumber[];

    public TileMap() {
        tiles = new Tile[10];
        // mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        // getTileImage();
        // loadMap("assets/maps/world02.txt");
    }

    public void loadMap(String pathName) {
        try {
            File file = new File(pathName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            int col = 0;
            int row = 0;

            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");

                for (col = 0; col < gamePanel.maxWorldCol && col < numbers.length; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                }

                row++;
            }

            br.close();
            System.out.println("Map loaded successfully: " + pathName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
