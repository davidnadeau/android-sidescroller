package com.example.sidescroller.graphics;

/**
 * Created by soote on 11/23/13.
 */
public class TileSprite {
    public static Sprite grass        =  new Sprite(16,0,4,new SpriteSheet());
    public static Sprite flower       =  new Sprite(16,3,1,new SpriteSheet());
    public static Sprite rock         =  new Sprite(16,2,0,new SpriteSheet());
    public static Sprite brick        =  new Sprite(16,2,2,new SpriteSheet());
    public static Sprite rainbowBrick =  new Sprite(16,2,3,new SpriteSheet());
    public static Sprite lightBrick   =  new Sprite(16,2,5,new SpriteSheet());
    public static Sprite lightWood    =  new Sprite(16,4,1,new SpriteSheet());
    public static Sprite darkWood     =  new Sprite(16,4,2,new SpriteSheet());
    public static Sprite waterSprite  =  new Sprite(16,5,2,new SpriteSheet());
    public static Sprite errSprite    =  new Sprite(16,5,5,new SpriteSheet());
}