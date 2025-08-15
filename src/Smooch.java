import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Smooch
{
    // Tamanhos
    public static int height;
    public static int width;

    // Coordenadas
    public static double x, y;
    public double dx, dy;
    public double speed = 7.0;

    // Variáveis
    int ptj = 0;
    int pte = 0;
    double angle;
    int rest;
    int bounceCd = 0;
    
    public Smooch() {
        // ANGULO INICIAL EU ACHO
        this.angle = Math.random() * Math.PI * 2.0;
        this.dx = Math.cos(Math.toRadians(angle));
        this.dy = Math.sin(Math.toRadians(angle));
    }
    
    public void tick() {

        // NÃO DEIXA SAIR DA TELA DO JOGO
        if (Smooch.y + this.dy * this.speed + Smooch.height >= Game.HEIGHT * Game.SCALE) {this.dy *= -1.0;}
        if (Smooch.y + this.dy * this.speed <= 0.0) {this.dy *= -1.0;}
        if (Smooch.x + this.dx * this.speed + Smooch.width >= Game.WIDTH * Game.SCALE) {this.dx *= -1.0;}
        if (Smooch.x + this.dx * this.speed <= 0.0) {this.dx *= -1.0;}

        final Rectangle bounds = new Rectangle((int)(Smooch.x + this.dx * this.speed), (int)(Smooch.y + this.dy * this.speed), Smooch.width, Smooch.height); // COLISÃO DO CORAÇÃO
        final Rectangle boundsPlayer = new Rectangle((int)Player.x, (int)Player.y, 50, 50); //COLISÃO PLAYER
        final Rectangle boundsBoy = new Rectangle((int)Boy.x, (int)Boy.y, 50, 50); // COLISÃO DO EZREAL

        // SE ACERTAR O EZREAL GANHA PONTO E APAGA O CORAÇÃO
        if (bounds.intersects(boundsBoy)) {
            Game.player.kissyRest = true;
            ++Game.kisses;
        }

        // SE ACERTAR O PLAYER VAI IR PRA UM LADO ALEATÓRIO
        if (bounceCd == 0 && bounds.intersects(boundsPlayer)) {
            this.angle = Math.random() * Math.PI * 2.0;
            // CALCULOS MILENARES
            this.dx = Math.cos(this.angle);
            this.dy = Math.sin(this.angle);
            // EMPURRA UM POUCO PRA FORA PRA NÃO GRUDAR NO PLAYER
            Smooch.x += (int)(this.dx * 2.0);
            Smooch.y += (int)(this.dy * 2.0);
            // COOLDOWN DA BATIDA PRA NÃO FICAR DOIDO
            bounceCd = 6;
        }

        if (bounceCd > 0) {bounceCd--;}

        // ALTERA O X E Y DE ACORDO COM A VELOCIDADE
        Smooch.x += (int)(this.dx * this.speed);
        Smooch.y += (int)(this.dy * this.speed);}


    // DESENHA O CORAÇÃO
    public void render(final BufferedImage sp, final Graphics g) {
        g.drawImage(sp, ((int)Smooch.x), ((int)Smooch.y), Smooch.width, Smooch.height, null);
    }
    
    // UE PQ O TAMANHO DO SPRITE TA AQUI EM BAIXO?
    static {
        Smooch.height = 21;
        Smooch.width = 21;
        
    }
}