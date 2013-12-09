package com.example.sidescroller.game.buttons;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/29/13.
 */
public class MenuButton {
    private int x, y;
    private Sprite sprite;

    public MenuButton(int x, int y) {
        this.x = 50;
        this.y = 10;
        this.sprite = ButtonSprites.menuButton;
    }

    public boolean wasClicked(int x, int y) {
        return x >= this.x
                && x <= this.x + sprite.getSize()
                && y >= this.y
                && y <= this.y + sprite.getSize();
    }

    public void draw(Screen s) { s.draw(x, y, sprite); }
}
