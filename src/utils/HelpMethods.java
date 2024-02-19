package utils;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import main.Game;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData){
        if (!isSolid(x, y, lvlData))
			if (!isSolid(x + width, y + height, lvlData))
				if (!isSolid(x + width, y, lvlData))
					if (!isSolid(x, y + height, lvlData))
                        return true;
        return false; 
    }

    private static boolean isSolid(float x, float y, int[][] lvlData){
        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TITLES_SIZE;
        float yIndex = y / Game.TITLES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }

    public static float GetEntityPosNextToWall(Rectangle2D.Float hitbox, float xspeed){
        int currentTile = (int) hitbox.x / Game.TITLES_SIZE;
        if(xspeed > 0){
            int tileXPos = currentTile * Game.TITLES_SIZE;
            int xOffset = (int) (Game.TITLES_SIZE - hitbox.width);
            return tileXPos + xOffset -1;
        }else{
            return currentTile *Game.TITLES_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int)(hitbox.y / Game.TITLES_SIZE);
        if(airSpeed > 0){
            //fall
            int tileYPos = currentTile * Game.TITLES_SIZE;
            int yOffset = (int)(Game.TITLES_SIZE - hitbox.height);
            return tileYPos + yOffset-1;
        }else{
            //jump
            return currentTile * Game.TITLES_SIZE;
        }
    }

    public static boolean ISEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData){
        //check pixel below 
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
			if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
                return false;
                
        return true;
    }
}
