package com.example.sidescroller.levels;

import com.example.sidescroller.graphics.Sprite;
import com.example.sidescroller.graphics.Surface;
import com.example.sidescroller.graphics.TileSprite;

/**
 * Created by soote on 11/23/13.
 */
public class Tile {
    public int x,y;
    public Sprite sprite;
    private boolean solid;
    public String tileType="default";
    public static Tile grass        =   new Tile(TileSprite.grass,false);
    public static Tile flower       =   new Tile(TileSprite.flower,false);
    public static Tile rock         =   new Tile(TileSprite.rock,true);
    public static Tile brick        =   new Tile(TileSprite.brick,true);
    public static Tile rainbowBrick =   new Tile(TileSprite.rainbowBrick,true);
    public static Tile lightBrick   =   new Tile(TileSprite.lightBrick,true);
    public static Tile lightWood    =   new Tile(TileSprite.lightWood,false);
    public static Tile darktWood    =   new Tile(TileSprite.darkWood,false);
    public static Tile waterTile    =   new Tile(TileSprite.waterSprite,true);
    public static Tile errTile      =   new Tile(TileSprite.errSprite,true);

    public Tile(Sprite sprite,boolean solid){
        this.sprite = sprite;
        this.solid = solid;
    }
    public void render(int x, int y, Surface s){
        s.drawTile(x << 4, y << 4, this);
    }
    public boolean isSolid(){
        return solid;
    }
}