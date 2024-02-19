package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelHandler {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level lvlOne;

    public LevelHandler(Game game){
        this.game = game;
        // this.img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
        lvlOne = new Level(LoadSave.getLvLData());
    }

    public void draw(Graphics g){
        for(int j = 0; j < Game.TILES_IN_HEIGHT; j++){
            for(int i = 0; i< Game.TILES_IN_WIDTH; i++){
                int index = lvlOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TITLES_SIZE*i, Game.TITLES_SIZE*j,Game.TITLES_SIZE,Game.TITLES_SIZE, null);
            }
        }
    }

    public void update(){

    }

    public Level getCurrLevel(){
        return lvlOne;
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];

        for(int i = 0; i < 4; i++){ // cut sprite into small subimg in an array
            for (int j = 0; j < 12; j++){
                int index = i*12 + j;
                levelSprite[index] = img.getSubimage(j*32, i*32, 32, 32);
            }
        }

    }
}
