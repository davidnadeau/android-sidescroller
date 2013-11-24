package com.example.sidescroller.graphics.level;

import com.example.sidescroller.graphics.Screen;

/**
 * Created by soote on 11/23/13.
 */
public class Level {

    protected int IMAGE_WIDTH, IMAGE_HEIGHT;
    protected int[] tilesInt;
    protected int[] tiles;

    public Level(int width, int height) {
        this.IMAGE_WIDTH = width;
        this.IMAGE_HEIGHT = height;
        tilesInt = new int[width * height];

        generateLevel();
    }

    public Level() {
        loadLevel();
        generateLevel();

    }

    protected void generateLevel() {

    }

    protected void loadLevel() {

    }

    public void update() {

    }

    private void time() {

    }
    public void render(int xScroll, int yScroll, Screen s) {
        s.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + s.getWidth() + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + s.getHeight() + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                //draw every tile to Screen
                getTile(x, y).draw(x, y, s);
            }
        }
    }

    public Tile getTile(int x, int y) {
        //if out of bounds
        if (x < 0 || y < 0 || x >= IMAGE_WIDTH || y >= IMAGE_HEIGHT)
            return Tile.waterTile;

        //each int holds the colour of the pixel in ARGB format: aarrggbb
        switch (tiles[x + y * IMAGE_WIDTH]) {
            case 0xff00ff00:
                return Tile.grass;
            case 0xffffff00:
                return Tile.flower;
            case 0xff808100:
                return Tile.rock;
            default:
                return Tile.errTile;
        }
    }
}