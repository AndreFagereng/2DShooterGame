/**
 * Created by Jegern on 02.11.2017.
 */
public class Camera {

    public float x, y;

    public Camera(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object){

        x += ((object.getX() - x ) - 1000/2) * 0.05f;
        y += ((object.getY() - y ) - 563/2) * 0.05f;

        if(x <= 0) x = 0;
        if(x >= 1032+18) x=1032+18;
        if(y <= 0) y = 0;
        if(y >= 554) y = 554;


    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
