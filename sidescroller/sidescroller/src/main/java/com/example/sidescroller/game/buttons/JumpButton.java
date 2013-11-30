package com.example.sidescroller.game.buttons;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/29/13.
 */
public class JumpButton {
    private int x, y;
    private Sprite sprite;

    public JumpButton(int x, int y, Sprite sprite) {
        this.x = x / 5;
        this.y = y - (y / 5);
        this.sprite = sprite;
    }

    public boolean wasClicked(int x, int y) {
        return x >= this.x - 16 &&
                x <= this.x &&
                y >= this.y - 16 &&
                y <= this.y;
    }

    public void draw(Screen s) { s.draw(x - 16, y - 16, sprite); }

}
