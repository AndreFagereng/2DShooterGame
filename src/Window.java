import javax.swing.*;
import java.awt.*;

/**
 * Created by Jegern on 01.11.2017.
 */
public class Window {




    public Window(int width, int height, String title, Game game){

        JFrame jFrame = new JFrame(title);



        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setMaximumSize(new Dimension(width, height));
        jFrame.setMinimumSize(new Dimension(width, height));

        jFrame.add(game);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }


}
