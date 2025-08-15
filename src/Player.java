//import java.awt.image.ImageObserver;
//import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player
{
    // VARIÁVEIS DE ESTADO
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean kissy;
    public boolean kissyRest;
    public boolean correr;

    // VARIÁVEIS DE POSIÇÃO
    public static int x;
    public static int y;
    public static int currentX;
    public static int currentY;
    int velocidadeMinima = 6;
    int velocidadeAtual = velocidadeMinima;
    int velocidadeMaxima = 9;
    public double lastAngle = 0; 

    // VARIÁVEIS DE TAMANHO
    public static final int height = 50;
    public static final int width = 50;
    public static int maxHEIGHT = (Game.HEIGHT * Game.SCALE) - height;
    public static int maxWIDTH = (Game.WIDTH * Game.SCALE) - width;

    // A SER IMPLEMENTADO
    public static int angle;

    // RECEBE POSIÇÃO INICIAL
    public Player(final int x, final int y) {
        Player.x = x;
        Player.y = y;
    }
    
    // 
    public void tick() {
        // MOVIMENTAÇÃO
        if (this.correr) { velocidadeAtual = velocidadeMaxima;}
        else {velocidadeAtual = velocidadeMinima;}
        if (this.up && Player.y > 0) {Player.y -= this.velocidadeAtual;}
        if (this.down && Player.y < maxHEIGHT) {Player.y += this.velocidadeAtual;}
        if (this.left && Player.x > 0) {Player.x -= this.velocidadeAtual;}
        if (this.right && Player.x < maxWIDTH) {Player.x += this.velocidadeAtual;}

        // CRIA O TIRO NAS COORDENADAS DO PLAYER APENAS SE NÃO EXISTIR TIRO NENHUM
        if (this.kissy && Game.kissy == 0) {
            Smooch.x = Player.x;
            Smooch.y = Player.y;
            Game.kissy = 1;
        }

        // DELETA O TIRO SE EXISTIR
        if (this.kissyRest && Game.kissy == 1) {
            Game.kissy = 0;
            this.kissyRest = false;
        }
    }
    
    public void render(final BufferedImage sp, final Graphics2D g) {
        double dx = 0, dy = 0;
        if (up) dy -= 1;
        if (down) dy += 1;
        if (left) dx -= 1;
        if (right) dx += 1;
        if (dx != 0 || dy != 0) {
            lastAngle = Math.atan2(dy, dx) - Math.PI/2;
            
        }
        java.awt.geom.AffineTransform old = g.getTransform();
        g.translate(Player.x + width/2, Player.y + height/2);
        g.rotate(lastAngle);
        g.drawImage(sp, -width/2, -height/2, width, height, null);
        g.setTransform(old);
    }
}