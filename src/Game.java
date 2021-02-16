import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.Canvas;

public class Game extends Canvas implements Runnable, KeyListener
{
    public static int WIDTH = 240;
    public static int HEIGHT = 120;
    public static int SCALE = 3;
    public SpriteSheet sheet1;
    public SpriteSheet bg;
    public SpriteSheet bush;
    public SpriteSheet kiss;
    BufferedImage boySp;
    BufferedImage playerSp;
    BufferedImage bgSp;
    BufferedImage bushSp;
    BufferedImage kissSp;
    public BufferedImage layer;
    public static Player player = new Player(60, HEIGHT);
    public static Boy boy = new Boy(240, HEIGHT);
    public static Smooch smch = new Smooch();
    public static int kissy = 0;
    public static int kisses = 0;
    
    public Game() {
        this.sheet1 = new SpriteSheet("res/spr.png");
        this.bg = new SpriteSheet("res/grama.png");
        this.bush = new SpriteSheet("res/bush.png");
        this.kiss = new SpriteSheet("res/heart.png");
        this.boySp = this.sheet1.getSprite(0, 0, 50, 50);
        this.playerSp = this.sheet1.getSprite(50, 0, 50, 50);
        this.bgSp = this.bg.getSprite(0, 0, 720, 360);
        this.bushSp = this.bush.getSprite(0, 0, 720, 360);
        this.kissSp = this.kiss.getSprite(0, 0, 21, 21);
        this.layer = new BufferedImage(Game.WIDTH, Game.HEIGHT, 1);
        this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
        this.addKeyListener(this);
    }
    
    public static void main(final String[] args) {
        final Game game = new Game();
        final JFrame frame = new JFrame("Smooch dat boi");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(game).start();
    }
    
    public void tick() {
        Game.player.tick();
        Game.boy.tick();
        switch (Game.kissy) {
            case 1: {
                Game.smch.tick();
                break;
            }
        }
    }
    
    public void render() {
        final BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = this.layer.getGraphics();
        g = bs.getDrawGraphics();
        g.drawImage(this.layer, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
        g.drawImage(this.bgSp, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
        Game.boy.render(this.boySp, g);
        Game.player.render(this.playerSp, g);
        switch (Game.kissy) {
            case 1: {
                Game.smch.render(this.kissSp, g);
                break;
            }
        }
        g.drawImage(this.bushSp, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
        bs.show();
    }
    
    @Override
    public void run() {
        while (true) {
            this.tick();
            this.render();
            try {
                Thread.sleep(16L);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
            Game.player.up = true;
        }
        else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
            Game.player.down = true;
        }
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
            Game.player.right = true;
        }
        else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
            Game.player.left = true;
        }
        if (e.getKeyCode() == 32) {
            Game.player.kissy = true;
        }
        if (e.getKeyCode() == 88) {
            Game.player.kissyRest = true;
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
            Game.player.up = false;
        }
        else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
            Game.player.down = false;
        }
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
            Game.player.right = false;
        }
        else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
            Game.player.left = false;
        }
        if (e.getKeyCode() == 32) {
            Game.player.kissy = false;
        }
        if (e.getKeyCode() == 88) {
            Game.player.kissyRest = false;
        }
    }
    
}