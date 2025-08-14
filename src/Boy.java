//import java.awt.image.ImageObserver;
//import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Boy
{
    // POSIÇÃO E TAMANHO
    public static int x;
    public static int y;
    public static final int height = 50;
    public static final int width = 50;
    public static final int maxHEIGHT = (Game.HEIGHT * Game.SCALE) - height;
    public static final int maxWIDTH = (Game.WIDTH * Game.SCALE) - width;

    // MOVIMENTAÇÃO
    public static int angle;
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
        // VIRAR SE BATER NA PAREDE
        if (Boy.y + this.dy * this.speed >= maxHEIGHT) {this.dy *= -(new Random().nextInt(1) + 1);}
        if (Boy.y + this.dy * this.speed <= 0.0) {this.dy *= -(new Random().nextInt(1) + 1);}
        if (Boy.x + this.dx * this.speed >= maxWIDTH) {this.dx *= -(new Random().nextInt(1) + 1);}
        if (Boy.x + this.dx * this.speed <= 0.0) {this.dx *= -(new Random().nextInt(1) + 1);}

        // ANDAR
        Boy.x += (int)(this.dx * this.speed);
        Boy.y += (int)(this.dy * this.speed);
    }
    
    public void render(final BufferedImage sp, final Graphics g) {
        // DESENHAR
        g.drawImage(sp, Boy.x, Boy.y, 50, 50, null);
    }
}