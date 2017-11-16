import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jegern on 02.11.2017.
 */
public class Bullet extends GameObject {

    double velX1;
    double velY1;
    private Handler handler;
    private BufferedImageLoader loader;
    private BufferedImage image;


    public Bullet(int x, int y, ID id, double mx, double my, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        double bulletVelocity = 20.0;

        double angle = Math.atan2(mx - x, my - y);
        velX1 = (bulletVelocity) * Math.sin(angle);
        velY1 = (bulletVelocity) * Math.cos(angle);
        System.out.println(Math.sin(angle));
        loader = new BufferedImageLoader();
        image = loader.loadImage("/bullet.png");



    }


    public void tick() {
        x += velX1;
        y += velY1;

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject blockObject = handler.objects.get(i);

            if(blockObject.getID() == ID.Block){

                if(getBounds().intersects(blockObject.getBounds())){
                    handler.objects.remove(this);
                }
            }





        }

    }


    public void render(Graphics g) {
       g.drawImage(image, x,y, 32, 32, null);
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
