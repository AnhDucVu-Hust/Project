package background;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;
import javax.imageio.ImageIO;
public class TilesManage {
    GamePanel gp;
    Tiles[] tile;
    int mapTiles[][];
    public TilesManage(GamePanel gp) {
        this.gp=gp;
        mapTiles = new int[gp.maxScreenCol][gp.maxScreenRow];
        tile=new Tiles[10];
        getTileImage();
        loadMap();
    }
    public void getTileImage(){
    try{
        tile[0]=new Tiles();
        tile[0].image=ImageIO.read(getClass().getResource("/envi/grass.png"));
        tile[1]=new Tiles();
        tile[1].image=ImageIO.read(getClass().getResource("/envi/wall.png"));

    }
    catch (IOException e){
        e.printStackTrace();
    }
}
    public void loadMap(){
        try{
            InputStream a = getClass().getResourceAsStream("/background/mapPreset.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(a));
            int col=0;
            int row=0;
            while (col<gp.maxScreenCol && row<gp.maxScreenRow){
                String line=br.readLine();
                while (col<gp.maxScreenCol){
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTiles[col][row]=num;
                    col++;
                }
            if (col==gp.maxScreenCol){
                col=0;
                row++;
            }  
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        
        for (int i=0;i<gp.maxScreenCol;i++){
            for (int j=0;j<gp.maxScreenRow;j++){
            g2.drawImage(tile[mapTiles[i][j]].image,i*gp.titleSize,j*gp.titleSize,gp.titleSize,gp.titleSize,null);
        }
    }
    }
}
