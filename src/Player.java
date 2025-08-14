//import java.awt.image.ImageObserver;
//import java.awt.Image;
import java.awt.Graphics;
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

    // VARIÁVEIS DE POSIÇÃO
    public static int x;
    public static int y;
    public static int currentX;
    public static int currentY;
    int velocidade = 6;

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
        if (this.up && Player.y > 0) {Player.y -= 6;}
        if (this.down && Player.y < maxHEIGHT) {Player.y += this.velocidade;}
        if (this.left && Player.x > 0) {Player.x -= 6;}
        if (this.right && Player.x < maxWIDTH) {Player.x += this.velocidade;}

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
    
    public void render(final BufferedImage sp, final Graphics g) {
        // DESENHA O PLAYER
        g.drawImage(sp, Player.x, Player.y, 50, 50, null);
    }
}