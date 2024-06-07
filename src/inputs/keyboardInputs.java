package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestates;
import main.GamePanel;

import utils.Constants.DIRECTION;

public class keyboardInputs implements KeyListener{
    private GamePanel panel;

    public keyboardInputs(GamePanel panel){
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestates.state) {
            case PLAYING:
                panel.getGame().getPlaying().keyPressed(e);
                break;
            case MENU:
                panel.getGame().getMenu().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestates.state) {
            case MENU:
                panel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                panel.getGame().getPlaying().keyReleased(e);
            default:
                break;
        }
    }
    
}
