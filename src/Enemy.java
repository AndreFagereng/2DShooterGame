import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.UnrecoverableKeyException;
import java.util.Random;

/**
 * Created by Jegern on 03.11.2017.
 */
public class Enemy extends GameObject {

    Random rand = new Random();
    int number = 0;
    private Handler handler;
    private BufferedImage zombie;
    private int HP;
    private long preTime;
    private int enemiesLeft = 0;

    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss, int HP) {

        super(x, y, id);
        this.handler = handler;
        this.HP = HP;


        zombie = ss.getImage(1, 1, 32, 64);

        preTime = System.currentTimeMillis() / 1000000000;
    }



    public void collision(){

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempblock = handler.objects.get(i);

            if(tempblock.getID() == ID.Block){
                if(getBigBounds().intersects(tempblock.getBounds())){
                    x += (velX *2) * -1;
                    y += (velY *2) * -1;
                    velX *= -1;
                    velY *= -1;
                }
            }
            GameObject tempBullet = handler.objects.get(i);

            if(tempBullet.getID() == ID.Bullet){
                if (getBounds().intersects(tempBullet.getBounds())) {
                    HP -= 20;

                }
                if(HP == 0){
                    handler.removeObject(this);
                }
            }


        }

    }




    public void tick() {
        x += velX;
        y += velY;

        number = rand.nextInt(10);
        if(number == 1){
            velX = (rand.nextInt(4 - -4) + -4);
            velY = (rand.nextInt(4 - -4) + -4);
        }

        collision();

    }


    public void render(Graphics g) {


        g.drawImage(zombie, x, y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public Rectangle getBigBounds(){
        return new Rectangle(x-16, y-16, 64, 64);
    }
}
