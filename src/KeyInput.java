import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Jegern on 01.11.2017.
 */
public class KeyInput extends KeyAdapter {

    Handler handler;
    Game game;

    public KeyInput(Handler handler, Game game){

        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getID() == ID.Player){
                if(key == KeyEvent.VK_W) handler.setUp(true);
                if(key == KeyEvent.VK_S) handler.setDown(true);
                if(key == KeyEvent.VK_A) handler.setLeft(true);
                if(key == KeyEvent.VK_D) handler.setRight(true);
                if(key == KeyEvent.VK_SPACE) game.newGame();

                }
            }

        }



    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getID() == ID.Player){
                if(key == KeyEvent.VK_W) handler.setUp(false);
                if(key == KeyEvent.VK_S) handler.setDown(false);
                if(key == KeyEvent.VK_A) handler.setLeft(false);
                if(key == KeyEvent.VK_D) handler.setRight(false);
            }

        }
    }


}
