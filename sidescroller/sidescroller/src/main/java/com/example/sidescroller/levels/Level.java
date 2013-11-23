package com.example.sidescroller.levels;

import com.example.sidescroller.graphics.Surface;

/**
 * Created by soote on 11/23/13.
 */
public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    public Level(int width, int height){
        this.width = width;
        this.height = height;
        tilesInt = new int[width*height];

        generateLevel();
    }

    public Level(int level){
        loadLevel(level);
        generateLevel();

    }

    protected void generateLevel(){

    }

    protected void loadLevel(int level){

    }

    public void update() {

    }

    private void time(){

    }
    public void render(int xScroll, int yScroll, Surface s){
        s.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + s.getWidth() + 16)>>4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + s.getHeight() + 16)>>4;

        for(int y = y0; y<y1; y++){
            for(int x = x0;x<x1;x++){
                getTile(x,y).render(x, y, s);
            }
        }
    }

    public Tile getTile(int x, int y){
        if(x<0||y<0||x>=width||y>=height)return Tile.waterTile;
        else if(tiles[x+y*width] == 0xff00ff00)return Tile.grass;
        else if(tiles[x+y*width] == 0xffffff00)return Tile.flower;
        else if(tiles[x+y*width] == 0xff808100)return Tile.rock;
        else if(tiles[x+y*width] == 0xff70818c)return Tile.brick;
        else if(tiles[x+y*width] == 0xff43545e)return Tile.rainbowBrick;
        else if(tiles[x+y*width] == 0xffc9b992)return Tile.lightBrick;
        else if(tiles[x+y*width] == 0xffcb3000)return Tile.lightWood;
        else if(tiles[x+y*width] == 0xff572803)return Tile.darktWood;
        else return Tile.errTile;
    }

    public int getWidth() { return width *16; }
    public int getHeight() { return height*16; }
}