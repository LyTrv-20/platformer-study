package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import utils.HelpMethods;
import utils.LoadSave;

import static utils.Constants.DIRECTION.*;
import static utils.Constants.PlayerConstants.*;


public class Player extends Entity{
    //animation based
    private BufferedImage[][] anim;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    //movement based
    private float speed = 0.75f;
    private boolean left, right, up,down, jump;
    private boolean isMoving = false, attack =false;
    //hitbox based +lvl
    private int[][] lvlData; 
    private float xOffset = 21 * Game.SCALE;
    private float yOffset = 4 * Game.SCALE;
    //jump/gravity
    private float gravity = 0.035f*Game.SCALE;
    private float airSpeed = 0f;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f *Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y,width,height);
        loadAnimation();
        initHitbox(x,y, 20*Game.SCALE, 27 * Game.SCALE);
         
    }
    
    public void update(){
        updatePos();
        updateAniTicks();
        updateAnimation();
    }

    public void render(Graphics g){
        g.drawImage(anim[playerAction][aniIndex], (int) (hitbox.x - xOffset), (int)(hitbox.y - yOffset), 70,46,null );
        drawHitbox(g);
    }

    private void loadAnimation() {
          BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

            anim = new BufferedImage[12][6];
        
            for(int j = 0; j <anim.length; j++){
                for (int i = 0; i < anim[j].length; ++i) {
                anim[j][i] = img.getSubimage(i*66, j*44, 64, 40);
            }
        }
        
    }

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
    }

    private void updatePos() {
        isMoving = false;

        if(jump)
            jump();
        if(!left && !right && !inAir)
            return;

        float xspeed = 0;

        if(left)
        xspeed -= speed;

        if(right)
        xspeed += speed;

        if(!inAir)
            if(!HelpMethods.ISEntityOnFloor(hitbox, lvlData))
                inAir = true;
        
        if(inAir){
            if(HelpMethods.canMoveHere(hitbox.x, hitbox.y+airSpeed, hitbox.width, hitbox.height, lvlData)){
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xspeed);
            }else{
                hitbox.y = HelpMethods.GetEntityYPosUnderRoofOrAboveFloor(hitbox,airSpeed);
                if(airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xspeed);
            }
        }else
            updateXPos(xspeed);
            
        isMoving = true;
    }

    private void jump() {
        if(inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xspeed) {
        if(HelpMethods.canMoveHere(hitbox.x + xspeed, hitbox.y, hitbox.width,hitbox.height,lvlData)){
            hitbox.x += xspeed;
            isMoving = true; 
        }else{
            hitbox.x = HelpMethods.GetEntityPosNextToWall(hitbox, xspeed);
        }
        System.out.println(airSpeed + "|| "+ inAir);
    }

    //change animation
    private void updateAnimation() {
        int startAni = playerAction;

        if(isMoving)
            playerAction = RUN;
        
        else
            playerAction = IDLE;
        
        if(inAir){
            if(airSpeed < 0)
                playerAction = JUMP;
            else 
                playerAction = FALL;
        }
        if(attack)
            playerAction = ATK1;

        if(startAni != playerAction)
            resetTicks();
    }

    private void resetTicks() {
        aniTick = 0;
        aniIndex =0;
    }

    private void updateAniTicks() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick =0;
            aniIndex++;
            if(aniIndex >= GetAnimAmount(playerAction)){
                aniIndex=0;
                attack = false;
            }
        }
    }
    public void setAttack(boolean atk){
        this.attack = atk;
    }

    public boolean getL(){
        return left;
    }
    public boolean getR(){
        return right;
    }
    public void setL(boolean l){
        left =l ;
    }
    public void setR(boolean l){
        right =l ;
    }
    public void setJump(boolean l){
        jump = l;
    }
    

    public void resetBool() {
        left = false;
        right = false;
    }
}
