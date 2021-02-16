import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.Random;

public class Smooch
{
    public static int x;
    public static int y;
    public static int height;
    public static int width;
    public double dx;
    public double dy;
    public double speed;
    int ptj;
    int pte;
    int angle;
    
    public Smooch() {
        this.speed = 7.0;
        this.ptj = 0;
        this.pte = 0;
        final int angle = new Random().nextInt(360);
        this.dx = Math.cos(Math.toRadians(angle));
        this.dy = Math.cos(Math.toRadians(angle));
    }
    
    public void tick() {
        if (Smooch.y + this.dy * this.speed + Smooch.height >= Game.HEIGHT * 3) {
            this.dy *= -1.0;
        }
        if (Smooch.y + this.dy * this.speed <= 0.0) {
            this.dy *= -1.0;
        }
        if (Smooch.x + this.dx * this.speed + Smooch.width >= Game.WIDTH * 3) {
            this.dx *= -1.0;
        }
        else if (Smooch.x + this.dx * this.speed <= 0.0) {
            this.dx *= -1.0;
        }
        final Rectangle bounds = new Rectangle((int)(Smooch.x + this.dx * this.speed), (int)(Smooch.y + this.dy * this.speed), Smooch.width, Smooch.height);
        final Player player = Game.player;
        final int x = Player.x;
        final Player player2 = Game.player;
        final int y = Player.y;
        final Player player3 = Game.player;
        final int width = 50;
        final Player player4 = Game.player;
        final Rectangle boundsPlayer = new Rectangle(x, y, width, 50);
        final Boy boy = Game.boy;
        final int x2 = Boy.x;
        final Boy boy2 = Game.boy;
        final int y2 = Boy.y;
        final Boy boy3 = Game.boy;
        final int width2 = 50;
        final Boy boy4 = Game.boy;
        final Rectangle boundsBoy = new Rectangle(x2, y2, width2, 50);
        if (bounds.intersects(boundsPlayer)) {
            this.angle = new Random().nextInt(359);
            this.dx = Math.cos(Math.toRadians(this.angle));
            this.dy = Math.cos(Math.toRadians(this.angle));
            this.dx *= -1.0;
            this.dy *= -1.0;
        }
        if (bounds.intersects(boundsBoy)) {
            Game.player.kissyRest = true;
            ++Game.kisses;
        }
        Smooch.x += (int)(this.dx * this.speed);
        Smooch.y += (int)(this.dy * this.speed);
    }
    
    public void render(final BufferedImage sp, final Graphics g) {
        g.drawImage(sp, Smooch.x, Smooch.y, Smooch.width, Smooch.height, null);
    }
    
    static {
        Smooch.height = 21;
        Smooch.width = 21;
    }
}