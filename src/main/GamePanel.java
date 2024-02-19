package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.keyboardInputs;
import inputs.mouseInputs;
import utils.Constants.DIRECTION;
import utils.Constants.PlayerConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel{
    private mouseInputs mouse;
    private Game game;

    public GamePanel(Game game){
        mouse = new mouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new keyboardInputs(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    //setting window size
    private void setPanelSize() {
        Dimension size = new Dimension(game.GAME_WIDTH,game.GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void updateGame(){
        
    }

    public Game getGame(){
        return game;
    }
    
}
