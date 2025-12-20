package game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import engine.input.InputHandler;
import engine.ui.UI;
import engine.Engine;

public class DeathScreen extends UI {

    public DeathScreen(int screenWidth, int screenHeight, InputHandler input) {
        super(screenWidth, screenHeight, input);
    }

    @Override
    public void update() {
        if (input.isKeyPressed(InputHandler.Key.ENTER)) {
            Engine.currentLevel.restartLevel();
            Engine.current_state = Engine.gameplay_state;
        }
    }

    @Override
    public void render(Graphics g) {

        String text = "DEAD";
        int fontSize = 100;

        // Background
        g.setColor(new Color(25, 25, 35));
        g.fillRect(0, 0, screenWidth, screenHeight);

        // Font
        Font font = new Font("Arial", Font.BOLD, fontSize);
        g.setFont(font);
        g.setColor(Color.RED);

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
