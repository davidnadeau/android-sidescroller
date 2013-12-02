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
    public static int  TILE_SIZE    = 64;
    public static Tile castleWall   = new Tile(TileSprites.castleWall, false);
    public static Tile snowMid      = new Tile(TileSprites.snowMid, true);
    public static Tile snowCenter   = new Tile(TileSprites.snowCenter, false);
    public static Tile cloud        = new Tile(TileSprites.cloud, false);
    public static Tile cloud2       = new Tile(TileSprites.cloud2, false);
    public static Tile spikes       = new Tile(TileSprites.spikes, true);
    public static Tile errTile      = new Tile(TileSprites.errTile, false);


    public Tile(Sprite sprite, boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    public void draw(int x, int y, Screen s) { s.drawTile(x * TILE_SIZE, y * TILE_SIZE, sprite); }
    public boolean isSolid() { return solid; }
}