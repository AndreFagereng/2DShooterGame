import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends Canvas implements Runnable {


    private Thread thread;
    private boolean isRunning = false;
    private Handler handler;
    protected BufferedImage level = null;
    protected BufferedImage grass = null;
    protected BufferedImage block = null;
    protected BufferedImage zombie = null;
    protected BufferedImage zombie_sheet = null;
    private Camera camera;
    private Bullet bullet;
    private MouseInput mouseInput;
    private SpriteSheet ss = null;
    private SpriteSheet ss_zombie = null;
    Random rand;
    private Enemy enemy;




    public Game() {
        new Window(1000, 563, "Shooter Game", this);
        start();

        handler = new Handler();
        camera = new Camera(0,0);
        rand = new Random(1000);

        BufferedImageLoader loader = new BufferedImageLoader();

        level = loader.loadImage("/World1.png");
        grass = loader.loadImage("/grass.png");
        block = loader.loadImage("/sprite_sheet.png");
        zombie_sheet = loader.loadImage("/zombie_sprite_sheet.png");

        ss_zombie = new SpriteSheet(zombie_sheet);
        ss = new SpriteSheet(block);


        loadLevel(level);


        this.addKeyListener(new KeyInput(handler,this));
        this.addMouseListener(new MouseInput(handler, camera));

        Timer t = new Timer( );
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {


            }
        }, 1000,5000);

    }


    public void newGame(){
        handler = new Handler();
        camera = new Camera(0,0);
        rand = new Random(1000);

        BufferedImageLoader loader = new BufferedImageLoader();

        level = loader.loadImage("/World1.png");
        grass = loader.loadImage("/grass.png");
        block = loader.loadImage("/sprite_sheet.png");
        zombie_sheet = loader.loadImage("/zombie_sprite_sheet.png");

        ss_zombie = new SpriteSheet(zombie_sheet);
        ss = new SpriteSheet(block);


        loadLevel(level);


        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(new MouseInput(handler, camera));
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        this.requestFocus();
        long lasttime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now-lasttime) / ns;
            lasttime = now;
            while(delta >= 1){
                tick();
                delta--;
                updates++;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;

                System.out.println("FPS: " + frames + "\nUpdates: " + updates + "");


                frames = 0;
                updates = 0;
            }
        }

        stop();

    }

    public void tick(){
        GameObject object;

        for(int i = 0; i < handler.objects.size(); i++){
            object = handler.objects.get(i);
            if(object.getID() == ID.Player){
                camera.tick(handler.objects.get(i));
            }

        }



        handler.tick();
    }



    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        ////////////////////////////////
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 1000, 563);
        g2d.translate(-camera.getX(), -camera.getY());


        for(int i = 0; i < 30*72; i+=32){
            for (int y = 0; y < 30*72; y+=32){
                g.drawImage(grass, i, y, null);
            }
        }

        handler.render(g);


        g2d.translate(camera.getX(), camera.getY());
        ///////////////////////////////

        g.dispose();
        bs.show();

    }


    //loading level

    private void loadLevel(BufferedImage bufferedImage){
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        for(int xx = 0; xx < width; xx++){
            for(int yy = 0; yy < height; yy++){
                int pixel = bufferedImage.getRGB(xx, yy);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(blue == 255)
                    handler.addObject(new Player(xx*32, yy*32, ID.Player, handler));
                if(red == 255)
                    handler.addObject(new Block(xx*32, yy*32, ID.Block,ss));
                if(green == 255)
                    handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler, ss_zombie, 100));
            }
        }
    }



    public static void main(String[] args) {
        new Game();

    }


}
