package game.levels;

import engine.Engine;
import engine.database.Database;
import engine.entities.Entity;
import engine.input.InputHandler;
import engine.levels.Level;
import engine.sound.Sound;
import engine.utilities.TileCoordinate;
import game.entities.*;

public class Level03 extends Level {

    private int timer = 0;
    boolean startOnce = true;
    Database database;
    Sound sound;
    Player player;
    Enemy enemy;
    TileCoordinate end;

    public Level03(int width, int height, InputHandler input) {
        super(width, height, input);
        start();
    }

    public Level03(String path, InputHandler input) {
        super(path, input);
        start();
    }

    void start() {
        deleteAllEntities();
        sound = new Sound();
        TileCoordinate playerPosition = new TileCoordinate(6, 15);
        player = new Player(playerPosition.x(), playerPosition.y(), input);
        TileCoordinate enemyPosition = new TileCoordinate(6, 8);
        enemy = new Enemy(enemyPosition.x(), enemyPosition.y());
        database = new Database();
        add(player);
        add(enemy);
        enemy.target(player);
        end = new TileCoordinate(43, 37);
        sound.setFile(0);
    }

    void startOnce() {
        sound.loop();
    }

    @Override
    public void currentLevelUpdate() {

        if (startOnce) {
            startOnce();
        }

        timer++;
        kill(player, enemy);

        if (player.die) {
            sound.stop();
            if (timer % 120 == 0) {
                Engine.setCurrentUI(3);
                Engine.current_state = Engine.gamePause_state;
            }
        }

        // end of the level
        if (player.getPviot().getX() / 16 == end.getXInTile() && player.getPviot().getY() / 16 == end.getYInTile()) {
            // database.saveScore(50);
            database.close();
            stop = true;
            System.out.println("win!");
            Engine.current_state = Engine.ui_state;
            sound.stop();
            Engine.setCurrentUI(Engine.winScreen);
            Engine.setCurrentLevel(Engine.level4);
        }

    }

    @Override
    public void restartLevel() {
        start();
    }

    boolean isColliding(Entity a, Entity b) {
        return a.getX() < b.getX() + b.getSprite().getWidth()
                && a.getX() + a.getSprite().getWidth() > b.getX()
                && a.getY() < b.getY() + b.getSprite().getHeight()
                && a.getY() + a.getSprite().getHeight() > b.getY();
    }

    void kill(Entity e1, Entity e2) {
        if (isColliding(e1, e2)) {
            player.die = true;
        }
    }

}
