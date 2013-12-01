package com.example.sidescroller.game.level;

import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/23/13.
 */
public class TileSprites {
    public static Sprite grass        = new Sprite(Tile.TILE_SIZE, 0, 4);
    public static Sprite flower       = new Sprite(Tile.TILE_SIZE, 3, 1);
    public static Sprite rock         = new Sprite(Tile.TILE_SIZE, 2, 0);
    public static Sprite brick        = new Sprite(Tile.TILE_SIZE, 2, 2);
    public static Sprite rainbowBrick = new Sprite(Tile.TILE_SIZE, 2, 3);
    public static Sprite lightBrick   = new Sprite(Tile.TILE_SIZE, 2, 5);
    public static Sprite lightWood    = new Sprite(Tile.TILE_SIZE, 4, 1);
    public static Sprite darkWood     = new Sprite(Tile.TILE_SIZE, 4, 2);
    public static Sprite waterSprite  = new Sprite(Tile.TILE_SIZE, 5, 2);
    public static Sprite errSprite    = new Sprite(Tile.TILE_SIZE, 5, 5);

}