package com.example.sidescroller.game.entities.coins;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 12/9/13.
 */
public class Life extends Entity{

    public Life(int x, int y, Sprite sprite) {
        this.x=x;this.y=y;
        this.sprite = sprite;
    }
    public void draw(int x, int y, Screen screen) {
        screen.draw(x, y, sprite);
    }
}
