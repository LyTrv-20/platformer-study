package ui;

import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.URMButts.*;
import static utils.Constants.UI.VolumeButtons.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.awt.event.MouseEvent;

import gamestates.Gamestates;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;

public class pauseOverlay {

    private Playing playing;

    private BufferedImage bg;
    private int bgX, bgY, bgWidth, bgHeight;
    private soundButton musicButton, sfxButton;
    private urmButtons menuB, replayB, unpausedB;
    private volumeButt volumeB;

    public pauseOverlay(Playing playing){
        this.playing = playing;
        loadBg();
        createSoundButtons();
        createUrmButts();
        createVolumeButts();
    }

    private void createVolumeButts() {
        int vX = (int) (306 * Game.SCALE);
        int vY = (int)(280.5f * Game.SCALE);
        volumeB = new volumeButt(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createUrmButts() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpausedX = (int) (462 * Game.SCALE);
        int bY = (int) (325 *Game.SCALE);

        menuB = new urmButtons(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new urmButtons(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpausedB = new urmButtons(unpausedX, bY, URM_SIZE, URM_SIZE, 0);
    }

    private void createSoundButtons() {
        int soundX = (int)(450 * Game.SCALE);
        int musicY = (int)(145 * Game.SCALE);
        int sfxY = (int)(190 * Game.SCALE);
        musicButton = new soundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new soundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBg() {
        bg = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BG);
        bgWidth = (int) (bg.getWidth() * Game.SCALE);
        bgHeight = (int) (bg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH /2 - bgWidth/2;
        bgY = (int) (30 * Game.SCALE);
    }

    public void update(){
        musicButton.update();
        sfxButton.update();

        menuB.update();
        replayB.update();
        unpausedB.update();

        volumeB.update();
    }

    public void draw(Graphics g){
        g.drawImage(bg, bgX, bgY, bgWidth,bgHeight, null);

        musicButton.draw(g);
        sfxButton.draw(g);

        menuB.draw(g);
        replayB.draw(g);
        unpausedB.draw(g);

        volumeB.draw(g);
    }

    public void mouseDragged(MouseEvent e){
        //gotta make sure slider still slide as long as the vol butt is pressed
        if(volumeB.isMousePressed()){
            volumeB.changeX(e.getX());
        }
    }

    public void mousePressed(MouseEvent e) {
        if(isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if(isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if(isIn(e, menuB))
            menuB.setMousePressed(true);
        else if(isIn(e, replayB))
            replayB.setMousePressed(true);
        else if(isIn(e, unpausedB))
            playing.unpauseGame();
        else if(isIn(e, volumeB))
            volumeB.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if(isIn(e, musicButton)){
            if(musicButton.isMousePressed())
                musicButton.setMuted(!musicButton.isMuted());
        }
        else if(isIn(e, sfxButton)){
            if(sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
        }
        else if(isIn(e, menuB)){
            if(menuB.isMousePressed()){
                Gamestates.state = Gamestates.MENU;
                playing.unpauseGame();
            }
        }
        else if(isIn(e, replayB)){
            if(replayB.isMousePressed())
                System.out.println("lvl replay");
        }
        else if(isIn(e, unpausedB)){
            if(unpausedB.isMousePressed())
                unpausedB.setMousePressed(true);
        }

        musicButton.resetBool();
        sfxButton.resetBool();
        menuB.resetBool();
        replayB.resetBool();
        unpausedB.resetBool();
        volumeB.resetBool();
    }

    public void mouseMoved(MouseEvent e){
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpausedB.setMouseOver(false);
        volumeB.setMouseOver(false);

        if(isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if(isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if(isIn(e, menuB))
            menuB.setMouseOver(true);
        else if(isIn(e, replayB))
            replayB.setMouseOver(true);
        else if(isIn(e, unpausedB))
            unpausedB.setMouseOver(true);
        else if(isIn(e, volumeB))
            volumeB.setMouseOver(true);
    }

    private boolean isIn(MouseEvent e, pauseButton b){
        return b.getBounds().contains(e.getX(),e.getY());  
    }

}
