package com.example.sidescroller.game.level;

import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/23/13.
 */
public class TileSprites {
    public static Sprite castleWall  = new Sprite(Tile.TILE_SIZE, 1, 0);
    public static Sprite snowMid     = new Sprite(Tile.TILE_SIZE, 1, 1);
    public static Sprite snowCenter  = new Sprite(Tile.TILE_SIZE, 1, 2);
    public static Sprite liquidWater = new Sprite(Tile.TILE_SIZE, 0, 3);
    public static Sprite snowLeftCorner  = new Sprite(Tile.TILE_SIZE, 1, 4);
    public static Sprite snowRightCorner  = new Sprite(Tile.TILE_SIZE, 1, 3);
    public static Sprite grass       = new Sprite(Tile.TILE_SIZE, 2, 3);
    public static Sprite grassLeftCorner  = new Sprite(Tile.TILE_SIZE, 6, 0);
    public static Sprite grassRightCorner  = new Sprite(Tile.TILE_SIZE, 6, 1);
    public static Sprite dirt        = new Sprite(Tile.TILE_SIZE, 3, 3);
    public static Sprite cloudLevel1 = new Sprite(Tile.TILE_SIZE, 3, 2);
    public static Sprite cloudLevel2 = new Sprite(Tile.TILE_SIZE, 2, 2);
    public static Sprite bridge      = new Sprite(Tile.TILE_SIZE, 0, 4);
    public static Sprite cloudLevel3 = new Sprite(Tile.TILE_SIZE, 5, 5);
    public static Sprite floor_lv3   = new Sprite(Tile.TILE_SIZE, 4, 5);
}