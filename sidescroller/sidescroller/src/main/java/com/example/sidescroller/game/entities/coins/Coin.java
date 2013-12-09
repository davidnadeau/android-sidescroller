package com.example.sidescroller.game.entities.coins;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;

/**
 * Created by soote on 12/6/13.
 */
public class Coin extends Entity {
    private int value;

    public Coin(int x, int y, int value) {
        this.x = x;
        this.y = y;

        this.value = value;
        switch (value) {
            case 5000:
                sprite = CoinSprites.gold;
                break;
            default:
                sprite = CoinSprites.gold;
        }
    }
    public void draw(int x, int y, Screen screen) {
        screen.draw(x, y, sprite);
    }

    public int getValue() {
        return value;
    }
}
