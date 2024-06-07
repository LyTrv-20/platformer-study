package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.Gamestates;
import main.GamePanel;

public class mouseInputs implements MouseListener, MouseMotionListener{
    private GamePanel panel;

    public mouseInputs(GamePanel panel) { 
        this.panel = panel;
     }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Gamestates.state) {
            case PLAYING:
                panel.getGame().getPlaying().mouseDragged(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestates.state) {
            case PLAYING:
                panel.getGame().getPlaying().mouseMoved(e);
                break;
            case MENU:
                panel.getGame().getMenu().mouseMoved(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestates.state) {
            case PLAYING:
                panel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestates.state) {
            case PLAYING:
                panel.getGame().getPlaying().mousePressed(e);
                break;
            case MENU:
                panel.getGame().getMenu().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestates.state) {
            case PLAYING:
                panel.getGame().getPlaying().mouseReleased(e);
                break;
            case MENU:
                panel.getGame().getMenu().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    
}
