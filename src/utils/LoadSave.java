package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
    public static final String PLAYER_ATLAS = "entity\\piratewSword.png";
    public static final String LEVEL_ATLAS = "scene\\outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "lvls/level_one_data.png";
    public static final String MENU_BUTTONS = "UI\\button_atlas.png";
    public static final String MENU_BG = "UI\\menu_background.png";
    public static final String PAUSE_BG = "UI\\pause_menu.png";
    public static final String SOUND_BUTTON = "UI\\sound_button.png";
    public static final String URM_BUTTONS = "UI\\urm_buttons.png";
    public static final String VOLUME_BUTONS = "UI\\volume_buttons.png";
    
    
    public static BufferedImage getSpriteAtlas(String dest){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/" + dest);
        
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }

    public static int[][] getLvLData(){
        int[][] data = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH]; //array of the game panel
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA); //img of the lvl

        //transversing thru the lvl img pixel
        for(int j = 0; j < img.getHeight();j++){
            for(int i = 0; i < img.getWidth(); i++){
                Color col = new Color(img.getRGB(i, j)); // get color number at a coordinate
                int value = col.getRed(); // get red number int
                if(value >= 48) { // if out of bound, reset to 0
                    value = 0;
                }
                data[j][i] = value; // assign the tiles in panel to a specific int
            }
        }
        return data;
    }
}
