import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player
{
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean kissy;
    public boolean kissyRest;
    public static int x;
    public static int y;
    public static int currentX;
    public static int currentY;
    public static int angle;
    public static final int height = 50;
    public static final int width = 50;
    
    public Player(final int x, final int y) {
        Player.x = x;
        Player.y = y;
    }
    
    public void tick() {
        if (this.up && Player.y > 0) {
            Player.y -= 6;
            Player.angle = 180;
        }
        if (this.down && Player.y < 310) {
            Player.y += 6;
            Player.angle = 0;
        }
        if (this.left && Player.x > 0) {
            Player.x -= 6;
            Player.angle = 360;
        }
        if (this.right && Player.x < 670) {
            Player.x += 6;
            Player.angle = 90;
        }
        if (this.kissy && Game.kissy == 0) {
            Smooch.x = Player.x;
            Smooch.y = Player.y;
            Game.kissy = 1;
        }
        if (this.kissyRest && Game.kissy == 1) {
            Game.kissy = 0;
            this.kissyRest = false;
        }
    }
    
    public void render(final BufferedImage sp, final Graphics g) {
        g.drawImage(sp, Player.x, Player.y, 50, 50, null);
    }
}