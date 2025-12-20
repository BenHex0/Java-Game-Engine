package game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;

import engine.Engine;
import engine.input.InputHandler;
import engine.ui.UI;
import engine.ui.UIButton;

public class MainMenu extends UI {

    private UIButton[] buttons;
    private int selectedIndex = 0;

    public MainMenu(int screenWidth, int screenHeight, InputHandler input) {
        super(screenWidth, screenHeight, input);

        int buttonWidth = 220;
        int buttonHeight = 55;
        int spacing = 20;

        int startY = screenHeight / 2 - (buttonHeight * 3 + spacing * 2) / 2;
        int centerX = (screenWidth - buttonWidth) / 2;

        buttons = new UIButton[] {
                new UIButton("Start", centerX, startY, buttonWidth, buttonHeight,
                        () -> Engine.current_state = Engine.gameplay_state),
                new UIButton("Score", centerX, startY + (buttonHeight + spacing), buttonWidth, buttonHeight,
                        () -> Engine.setCurrentUI(Engine.scoreScreen)),
                new UIButton("Exit", centerX, startY + 2 * (buttonHeight + spacing), buttonWidth, buttonHeight,
                        () -> System.exit(0))
        };
    }

    @Override
    public void update() {

        if (input.isKeyPressed(InputHandler.Key.UP)) {
            selectedIndex--;
            if (selectedIndex < 0)
                selectedIndex = buttons.length - 1;
        }

        if (input.isKeyPressed(InputHandler.Key.DOWN)) {
            selectedIndex++;
            if (selectedIndex >= buttons.length)
                selectedIndex = 0;
        }

        if (input.isKeyPressed(InputHandler.Key.ENTER)) {
            buttons[selectedIndex].action.run();
        }
    }

    @Override
    public void render(Graphics g) {

        // Background
        g.setColor(new Color(90, 90, 90));
        g.fillRect(0, 0, screenWidth, screenHeight);

        // Title
        g.setFont(new Font("Serif", Font.BOLD, 48));
        g.setColor(new Color(220, 220, 220));

        String title = "ESCAPE THE MAZE";
        FontMetrics fm = g.getFontMetrics();
        int tx = (screenWidth - fm.stringWidth(title)) / 2;
        g.drawString(title, tx, 100);

        // Buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].render(g, i == selectedIndex);
        }

        // Bottom text
        g.setFont(new Font("Serif", Font.ITALIC, 16));
        g.setColor(new Color(200, 200, 200));

        String hint = "Press Enter to confirm";
        int hx = (screenWidth - g.getFontMetrics().stringWidth(hint)) / 2;
        g.drawString(hint, hx, screenHeight - 50);
    }
}
