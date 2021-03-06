package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import background.TilesManage;
import entity.Player;

import java.awt.Dimension;
public class GamePanel extends JPanel implements Runnable{
    final int originalTitleSize=16;
    final int scale=3;
    public final int  titleSize = originalTitleSize * scale; // 48
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    final int screenWidth= titleSize * maxScreenCol; //768
    final int screenHeight= titleSize * maxScreenRow; //576
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    TilesManage tileM=new TilesManage(this);
    Player player= new Player(this,keyH);
    // player position,speed
    int playerX=100;
    int playerY=100;
    int playerSpeed=3;
    int FPS=60;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run() {
        double drawInterval=1e9/FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime=System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            timer += currentTime-lastTime;
            lastTime=currentTime;
            if (delta >=1) {
                // update game forward
                update();
                // repaint everything in game
                repaint();
                delta--;
                drawCount ++;
            }
            if (timer>=1000000000) {
                System.out.println("FPS " + drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
    }
    public void update() {
       player.update();
        }

}


