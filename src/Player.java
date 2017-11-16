import javax.swing.*;
import java.awt.*;

/**
 * Created by Jegern on 01.11.2017.
 */
public class Player extends GameObject {

    Handler handler;
    private BufferedImageLoader bufferedImageLoader;
    private Image image;
    private Game game;
    private boolean playerDead = true;
    private boolean playerWon = false;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        initImage();
    }

    public void initImage(){
        bufferedImageLoader = new BufferedImageLoader();
        image = bufferedImageLoader.loadImage("/player.png");
    }

    public void tick() {
        x += velX;
        y += velY;
        collision();

        if(handler.isDown())velY = 5;
        else if(!handler.isUp()) velY = 0;

        if(handler.isUp())velY = -5;
        else if(!handler.isDown()) velY = 0;

        if(handler.isLeft())velX = -5;
        else if(!handler.isRight()) velX = 0;

        if(handler.isRight())velX = 5;
        else if(!handler.isLeft()) velX = 0;

        if(playerWon){
            x = 0;
            y = 0;
        }

    }

    public void collision(){


        for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);

            if(temp.getID() == ID.Block){
                if(getBounds().intersects(temp.getBounds())){
                    x += (velX  * -1);
                    y += (velY * -1);
                }
            }else if(temp.getID() == ID.Enemy){
                if(getBounds().intersects(temp.getBounds())){
                    x = 0;
                    y = 0;
                    playerDead = false;

                }

            }


        }

    }
    int k;
    public int checkAliveEnemies(){
        k = 0;
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject object = handler.objects.get(i);
            if(object.getID() == ID.Enemy){
                k++;
            }

        }

        return k;
    }


    public void render(Graphics g) {

        if(!playerDead){
            g.setColor(Color.BLACK);
            g.setFont(new Font(null, 50, 50));
            g.drawString("You died! Hit SPACE to retry!", 100, 100);
        }
        if(checkAliveEnemies() == 0){
            playerWon = true;
            g.setColor(Color.BLACK);
            g.setFont(new Font(null, 50, 50));
            g.drawString("You won! Hit SPACE to retry!", 100, 100);
        }

        g.drawImage(image, x, y, 48, 48, null);
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }


}
