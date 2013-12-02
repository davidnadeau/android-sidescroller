package com.example.sidescroller.game.level;

import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/23/13.
 */
public class TileSprites {
    public static Sprite castleWall = new Sprite(Tile.TILE_SIZE, 1, 0);
    public static Sprite snowMid    = new Sprite(Tile.TILE_SIZE, 1, 1);
    public static Sprite snowCenter = new Sprite(Tile.TILE_SIZE, 1, 2);
    public static Sprite errTile    = new Sprite(Tile.TILE_SIZE, 0, 0);
}