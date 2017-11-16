import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Jegern on 02.11.2017.
 */
public class MouseInput extends MouseAdapter {

    Handler handler;
    Camera camera;

    public MouseInput(Handler handler, Camera camera){
        this.handler = handler;
        this.camera = camera;
    }



    public void mousePressed(MouseEvent e){

        double mx =  e.getX() + camera.getX();
        double my =  e.getY() +  camera.getY();


        for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);

            if(handler.objects.get(i).getID() == ID.Player){
                handler.objects.add(new Bullet(handler.objects.get(i).getX() + 12, handler.objects.get(i).getY() +14, ID.Bullet, mx, my, handler));
                }



        }

    }


}
