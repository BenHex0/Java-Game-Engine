package engine.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;

public class UIButton {
    public String text;
    public int x, y, width, height;
    public Runnable action;

    public UIButton(String text, int x, int y, int w, int h, Runnable action) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.action = action;
    }

    public void render(Graphics g, boolean selected) {
        g.setColor(selected ? new Color(80, 80, 80) : new Color(60, 60, 60));
        g.fillRect(x, y, width, height);

        g.setColor(new Color(180, 180, 180));
        g.drawRect(x, y, width, height);

        g.setFont(new Font("Serif", Font.BOLD, 22));
        g.setColor(Color.WHITE);

        FontMetrics fm = g.getFontMetrics();
        int tx = x + (width - fm.stringWidth(text)) / 2;
        int ty = y + (height - fm.getHeight()) / 2 + fm.getAscent();

        g.drawString(text, tx, ty);
    }
}
