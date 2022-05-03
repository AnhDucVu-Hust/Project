package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        setDefaultValue();
        getPlayerImage();
    }
    public void setDefaultValue(){
        x=100;
        y=100;
        speed=4;
        direction="down";
    }
    public void getPlayerImage () {
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("/animation/player/player_up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/animation/player/player_up2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/animation/player/player_down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/animation/player/player_down2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/animation/player/player_left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/animation/player/player_left2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/animation/player/player_right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/animation/player/player_right2.png"));
        }   
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPressed==true || keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true){
        if (keyH.upPressed==true){
            direction = "up";
            y -= speed;
        }
        else if (keyH.downPressed==true){
            direction= "down";
            y += speed;
        }
        else if (keyH.leftPressed==true){
            direction="left";
            x -= speed;
        }
        else if (keyH.rightPressed==true){
            direction="right";
            x += speed ;
        }
        spriteCounter ++;
        if (spriteCounter>20){
            if ( spriteNum == 1) {
                spriteNum=2;
            }
            else spriteNum=1;
            spriteCounter=0;
        }
    }
}
    
    public void draw(Graphics2D g2){
        BufferedImage image= null;
        switch(direction){
            case "up":
                if (spriteNum==1){
                    image=up1;
                    break;
                }
                else {
                    image=up2;
                    break;
                }
            case "down":
                if (spriteNum==1){
                    image=down1;
                    break;
                }
                else {
                    image=down2;
                    break;
                }
            case "left":
                if (spriteNum==1){
                    image=left1;
                    break;
                }
                else {
                    image=left2;
                    break;
                }
            case "right":
                if (spriteNum==1){
                    image=right1;
                    break;
                }
                else {
                    image=right2;
                    break;
                }
        
        }
        g2.drawImage(image,x, y,gp.titleSize,gp.titleSize,null);   
    }
}
