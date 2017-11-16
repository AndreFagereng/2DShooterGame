import java.awt.image.BufferedImage;

/**
 * Created by Jegern on 03.11.2017.
 */
public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage getImage(int col, int row, int width, int height){
        return image.getSubimage(col, row, width, height);
    }

}

