package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestates;
import utils.LoadSave;
import utils.Constants.UI.Buttons; 

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = Buttons.B_WIDTH /2;
    private Gamestates state;
    private BufferedImage[] img;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestates state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImage();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos,Buttons.B_WIDTH,Buttons.B_HEIGHT);
    }

    private void loadImage() {
        img = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i<img.length;i++) {
            img[i] = temp.getSubimage(i* Buttons.B_WIDTH_DEFAULT, rowIndex * Buttons.B_HEIGHT_DEFAULT, Buttons.B_WIDTH_DEFAULT, Buttons.B_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics g){
        g.drawImage(img[index], xPos - xOffsetCenter, yPos, Buttons.B_WIDTH, Buttons.B_HEIGHT,null);
    }

    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void applyGamestate(){
        Gamestates.state = state;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
}
