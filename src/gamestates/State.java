package gamestates;

import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class State {
    protected Game game;
    
    public State(Game game){
        this.game = game;
    }

    /**
     * 
     * @return true if mouse is inside button
     */ 
    public boolean isIn(MouseEvent e, MenuButton m){
        return m.getBounds().contains(e.getX(), e.getY());
    }

    public Game getGame(){
        return game; 
    }
}
