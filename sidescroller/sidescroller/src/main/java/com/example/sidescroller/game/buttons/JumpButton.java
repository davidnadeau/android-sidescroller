package com.example.sidescroller.game.buttons;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;
import com.example.sidescroller.game.level.Tile;

/**
 * Created by soote on 11/29/13.
 */
public class JumpButton {
    private int x, y;
    private Sprite sprite;

    public JumpButton(int x, int y, Sprite sprite) {
        this.x = x / 8;
        this.y = y - (y / 8);
        this.sprite = sprite;
    }

    public boolean wasClicked(int x, int y) {
        return x >= this.x
                && x <= this.x + sprite.getSize()
                && y >= this.y
                && y <= this.y + sprite.getSize();
    }

    public void draw(Screen s) { s.draw(x - Tile.TILE_SIZE, y - Tile.TILE_SIZE, sprite); }

}
