package com.example.sidescroller.game.buttons;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/29/13.
 */
public class JumpButton {
    private int x, y;
    private Sprite sprite;

    public JumpButton(int x, int y) {
        this.x = x / 8;
        this.y = y - (y / 8);
        this.sprite = ButtonSprites.jumpButtonUp;
    }

    public boolean wasClicked(int x, int y) {
        return x >= this.x
                && x <= this.x + sprite.getSize()
                && y >= this.y
                && y <= this.y + sprite.getSize();
    }

    public void down() {
        this.sprite = ButtonSprites.jumpButtonDown;
    }
    public void up() {
        this.sprite = ButtonSprites.jumpButtonUp;
    }
    public void draw(Screen s) { s.draw(x, y, sprite); }

}
