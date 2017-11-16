import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jegern on 01.11.2017.
 */
public class Block extends GameObject {



    private BufferedImage block_image;


    public Block(int x, int y, ID id, SpriteSheet ss) {

        super(x, y, id);

        block_image = ss.getImage(1, 1, 32, 32);
    }



    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
