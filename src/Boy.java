import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Boy
{
    public static int x;
    public static int y;
    public static int angle;
    public static final int height = 50;
    public static final int width = 50;
    public double dx;
    public double dy;
    public double speed;
    
    public Boy(final int x, final int y) {
        this.speed = 7.0;
        Boy.x = x;
        Boy.y = y;
        Boy.angle = new Random().nextInt(75) + 120;
        this.dx = Math.cos(Math.toRadians(Boy.angle));
        this.dy = Math.cos(Math.toRadians(Boy.angle));
    }
    
    public void tick() {
        if (Boy.y + this.dy * this.speed + 50.0 >= Game.HEIGHT * 3) {
            this.dy *= -(new Random().nextInt(1) + 1);
        }
        if (Boy.y + this.dy * this.speed <= 0.0) {
            this.dy *= -(new Random().nextInt(1) + 1);
        }
        if (Boy.x + this.dx * this.speed + 50.0 >= Game.WIDTH * 3) {
            this.dx *= -(new Random().nextInt(1) + 1);
        }
        else if (Boy.x + this.dx * this.speed <= 0.0) {
            this.dx *= -(new Random().nextInt(1) + 1);
        }
        Boy.x += (int)(this.dx * this.speed);
        Boy.y += (int)(this.dy * this.speed);
    }
    
    public void render(final BufferedImage sp, final Graphics g) {
        g.drawImage(sp, Boy.x, Boy.y, 50, 50, null);
    }
}