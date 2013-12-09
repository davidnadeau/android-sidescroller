package com.example.sidescroller.game.level;

import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/23/13.
 */
public class Tile {
    public  Sprite  sprite;
    private boolean solid;
    private boolean bg;
    public static int  TILE_SIZE   = 64;
    public static Tile castleWall  = new Tile(TileSprites.castleWall, false, true);
    public static Tile snowMid     = new Tile(TileSprites.snowMid, true, false);
    public static Tile snowCenter  = new Tile(TileSprites.snowCenter, false, false);
    public static Tile cloudLevel1 = new Tile(TileSprites.cloudLevel1, false, false);
    public static Tile cloudLevel2 = new Tile(TileSprites.cloudLevel2, false, false);
    public static Tile liquidWater = new Tile(TileSprites.liquidWater, false, true);
    public static Tile grass       = new Tile(TileSprites.grass, true, false);
    public static Tile dirt        = new Tile(TileSprites.dirt, false, false);
    //  public static Tile spikes      = new Tile(TileSprites.spikes, true);
    public static Tile bridge      = new Tile(TileSprites.bridge, true, false);
    public static Tile snowLeftCorner      = new Tile(TileSprites.snowLeftCorner, true, false);
    public static Tile snowRightCorner      = new Tile(TileSprites.snowRightCorner, true, false);
    public static Tile errTile     = new Tile(TileSprites.errTile, false, true);


    public Tile(Sprite sprite, boolean solid, boolean bg) {
        this.sprite = sprite;
        this.solid = solid;
        this.bg = bg;
    }
    public boolean isSolid() { return solid; }
    public boolean isBG() { return bg; }
}