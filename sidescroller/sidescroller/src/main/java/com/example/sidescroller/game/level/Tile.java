package com.example.sidescroller.game.level;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/23/13.
 */
public class Tile {
    public int x, y;
    public  Sprite  sprite;
    private boolean solid;
    public static int TILE_SIZE = 16;
    public static Tile grass        = new Tile(TileSprites.grass, false);
    public static Tile flower       = new Tile(TileSprites.flower, false);
    public static Tile rock         = new Tile(TileSprites.rock, true);
    public static Tile brick        = new Tile(TileSprites.brick, true);
    public static Tile rainbowBrick = new Tile(TileSprites.rainbowBrick, true);
    public static Tile lightBrick   = new Tile(TileSprites.lightBrick, true);
    public static Tile lightWood    = new Tile(TileSprites.lightWood, false);
    public static Tile darkWood     = new Tile(TileSprites.darkWood, false);
    public static Tile waterTile    = new Tile(TileSprites.waterSprite, true);
    public static Tile errTile      = new Tile(TileSprites.errSprite, true);

    public Tile(Sprite sprite, boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    public void draw(int x, int y, Screen s) { s.drawTile(x << 4, y << 4, sprite); }
    public boolean isSolid() { return solid; }
}