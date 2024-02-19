package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

import utils.Constants.DIRECTION;

public class keyboardInputs implements KeyListener{
    private GamePanel panel;

    public keyboardInputs(GamePanel panel){
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                panel.getGame().getPlayer().setL(true);
                break;
            case KeyEvent.VK_D:
                panel.getGame().getPlayer().setR(true);
                break;
            case KeyEvent.VK_W:
                panel.getGame().getPlayer().setJump(true);
                break;
            case KeyEvent.VK_S:
                
                break;
            case KeyEvent.VK_Q:
                panel.getGame().getPlayer().setAttack(true);
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                panel.getGame().getPlayer().setL(false);
                break;
            case KeyEvent.VK_D:
                panel.getGame().getPlayer().setR(false);
                break;
            case KeyEvent.VK_W:
                panel.getGame().getPlayer().setJump(false);
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_Q:
                panel.getGame().getPlayer().setAttack(false);
                break;
        }
    }
    
}
