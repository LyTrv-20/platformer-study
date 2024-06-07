package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utils.LoadSave;

public class Menu extends State implements StateMethods{

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage bgImg;
    private int menuX,menuY,menuWidth,menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        bgImg = LoadSave.getSpriteAtlas(LoadSave.MENU_BG);
        menuWidth=(int)(bgImg.getWidth() * Game.SCALE);
        menuHeight=(int)(bgImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth/2;
        menuY = (int)(45 * Game.SCALE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestates.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestates.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestates.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton m : buttons){
            m.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bgImg, menuX, menuY, menuWidth,menuHeight,null);
        for(MenuButton m : buttons){
            m.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //set boolean if mouseEvent triggered and mouse is inside button
        for (MenuButton m : buttons){
            if(isIn(e,m)){
                m.setMousePressed(true);
                break;
            }
        }
    }

    
    @Override
    public void mouseReleased(MouseEvent e) {
        //apply state associate with button to gamestate if mouse is inside button and pressed
        for (MenuButton m : buttons){
            if(isIn(e,m)){
                if(m.isMousePressed())
                    m.applyGamestate();
                break;
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e){
        for (MenuButton m:buttons)
            m.setMouseOver(false);

        for(MenuButton m:buttons)
            if(isIn(e, m)){
                m.setMouseOver(true);
                break;
            }
    }

    private void resetButtons() {
        for(MenuButton m : buttons)
            m.resetBools();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        //switch screen to play screen if enter
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            Gamestates.state = Gamestates.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
