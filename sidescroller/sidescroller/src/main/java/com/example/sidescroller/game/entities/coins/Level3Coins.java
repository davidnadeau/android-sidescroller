package com.example.sidescroller.game.entities.coins;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.level.Level;


/**
 * Created by soote on 12/6/13.
 */
public class Level3Coins {
    public Level3Coins(Screen screen) {
        Level.coins.add(new Coin(500, 200, 5000, screen));
        Level.coins.add(new Coin(800, 300, 5000, screen));
        Level.coins.add(new Coin(1200, 400, 5000, screen));
        Level.coins.add(new Coin(1400, 300, 5000, screen));
        Level.coins.add(new Coin(2000, 400, 5000, screen));
        Level.coins.add(new Coin(2400, 330, 5000, screen));
        Level.coins.add(new Coin(2800, 335, 5000, screen));
        Level.coins.add(new Coin(3000, 200, 5000, screen));
        Level.coins.add(new Coin(3500, 300, 5000, screen));
        Level.coins.add(new Coin(4200, 400, 5000, screen));
        Level.coins.add(new Coin(5400, 300, 5000, screen));
        Level.coins.add(new Coin(6000, 400, 5000, screen));
        Level.coins.add(new Coin(7400, 330, 5000, screen));
        Level.coins.add(new Coin(8800, 335, 5000, screen));
    }
}
