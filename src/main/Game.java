package main;

import java.awt.Graphics;

import Levels.LevelHandler;
import entities.Player;
import gamestates.Gamestates;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable{
    private GameWindow gameWin;
    private GamePanel panel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 250;

    private Playing playing;
    private Menu menu; 

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.07f;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TITLES_SIZE = (int) (TILES_DEFAULT_SIZE *SCALE);
    public static final int GAME_WIDTH = TITLES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT= TITLES_SIZE * TILES_IN_HEIGHT;   

    public Game(){
        initClasses();

        panel = new GamePanel(this);
        gameWin = new GameWindow(panel);
        panel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
       menu = new Menu(this);
       playing = new Playing(this);
    }

    public void render(Graphics g){
        switch (Gamestates.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /** update game based on different game state */
    public void update(){
        switch (Gamestates.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 /FPS_SET;
        double timePerUpdates = 1000000000.0/UPS_SET;

        long preTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - preTime)/timePerUpdates;
            deltaF += (currentTime - preTime)/timePerFrame;
            preTime = currentTime;

            if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                panel.repaint();
                frames++;
                deltaF--;
            }
        
        if(System.currentTimeMillis() - lastCheck >= 1000){
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames +" | UPS: " + updates);
            frames = 0;
            updates =0;
        }
    }
}
    public void windowLostFocus() {
        if(Gamestates.state == Gamestates.PLAYING)
            playing.getPlayer().resetBool();
    }
    
    public Menu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }
}
