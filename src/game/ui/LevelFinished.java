package game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import engine.ui.UI;
import engine.Engine;
import engine.input.InputHandler;

public class LevelFinished extends UI{
    
    public LevelFinished(int screenWidth, int screenHeight, InputHandler input) {
        super(screenWidth, screenHeight, input);
    }

    @Override
    public void update() {
        if (input.isKeyPressed(InputHandler.Key.ENTER)) {
            Engine.currentLevel.restartLevel();
            Engine.gameState = Engine.gameplay;
        }
    }

    @Override
    public void render(Graphics g) {

        String text = "You Win";
        int fontSize = 100;

        // Background
        g.setColor(new Color(25, 25, 35));
        g.fillRect(0, 0, screenWidth, screenHeight);

        // Font
        Font font = new Font("Arial", Font.BOLD, fontSize);
        g.setFont(font);
        g.setColor(Color.CYAN);

        // Measure text
        FontMetrics fm = g.getFontMetrics();

        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        // Center position
        int x = (screenWidth - textWidth) / 2;
        int y = (screenHeight - textHeight) / 2 + fm.getAscent();
        g.drawString(text, x, y);

        g.setFont(new Font("Serif", Font.ITALIC, 16));
        g.setColor(new Color(200, 200, 200));
        String hint = "Press Enter to restart";
        int hx = (screenWidth - g.getFontMetrics().stringWidth(hint)) / 2;
        g.drawString(hint, hx, screenHeight - 50);
    }
}
