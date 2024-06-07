package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Levels.LevelHandler;
import entities.Player;
import main.Game;
import ui.pauseOverlay;

public class Playing extends State implements StateMethods{
    private Player player;
    private LevelHandler levelManager;
    private pauseOverlay pauseScreen;
    private boolean paused = false;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelHandler(game);
        player = new Player(200, 200, (int)(60*Game.SCALE), (int)(44*Game.SCALE));
        player.loadLvlData(levelManager.getCurrLevel().getLvlData());
        pauseScreen = new pauseOverlay(this);
    }

    @Override
    public void update() {
        if(!paused){
            levelManager.update();
            player.update();
        }else 
            pauseScreen.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);

        if(paused)
            pauseScreen.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    public void mouseDragged(MouseEvent e){
        if(paused)
            pauseScreen.mouseDragged(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(paused)
            pauseScreen.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(paused)
            pauseScreen.mouseReleased(e);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if(paused)
            pauseScreen.mouseMoved(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setL(true);
                break;
            case KeyEvent.VK_D:
                player.setR(true);
                break;
            case KeyEvent.VK_W:
                player.setJump(true);
                break;
            case KeyEvent.VK_Q:
                player.setAttack(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setL(false);
                break;
            case KeyEvent.VK_D:
                player.setR(false);
                break;
            case KeyEvent.VK_W:
                player.setJump(false);
                break;
            case KeyEvent.VK_Q:
                player.setAttack(false);
                break;
        }
    }

    public void unpauseGame(){
        paused = false;
    }

    public Player getPlayer(){
        return player;
    }

    public void windowLostFocus() {
        player.resetBool();
    }
}
