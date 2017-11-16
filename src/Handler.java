import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Jegern on 01.11.2017.
 */
public class Handler {

    LinkedList<GameObject> objects = new LinkedList<>();
    private boolean up = false, down = false, right = false, left = false;

    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObjects = objects.get(i);

            tempObjects.tick();
        }
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    public void render(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObjects = objects.get(i);

            tempObjects.render(g);
        }
    }

    public void addObject(GameObject gameObject){
        objects.add(gameObject);
    }

    public void removeObject(GameObject gameObject){
        objects.remove(gameObject);
    }

}
