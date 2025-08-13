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
    //TAMANHO DA JANELA
    public static int WIDTH = 240;
    public static int HEIGHT = 120;
    public static int SCALE = 3;

    // IMAGENS
    public SpriteSheet sheet1; BufferedImage boySp; BufferedImage playerSp;
    public SpriteSheet bg; BufferedImage bgSp;
    public SpriteSheet bush; BufferedImage bushSp;
    public SpriteSheet kiss; BufferedImage kissSp;
    public SpriteSheet score; BufferedImage scoreSp;
    public BufferedImage layer;

    // OBJETOS
    public static Player player = new Player(60, HEIGHT);
    public static Boy boy = new Boy(240, HEIGHT);
    public static Smooch smch = new Smooch();
    public static int kissy = 0; // SE 1 = TIRO EXISTE
    public static int kisses = 0; // PONTUAÇÃO
    
    public Game() {
        // COLETANDO SPRITS
        this.sheet1 = new SpriteSheet("res/spr.png");
        this.bg = new SpriteSheet("res/grama.png");
        this.bush = new SpriteSheet("res/bush.png");
        this.kiss = new SpriteSheet("res/heart.png");

        // DESENHANDO SPRITS (X Y W H) TAMANHO DINAMICO
        this.boySp = this.sheet1.getSprite(0, 0, 50, 50);
        this.playerSp = this.sheet1.getSprite(50, 0, 50, 50);
        this.bgSp = this.bg.getSprite(0, 0, 720, 360);
        this.bushSp = this.bush.getSprite(0, 0, 720, 360);
        this.kissSp = this.kiss.getSprite(0, 0, 21, 21);

        // BORDA IMAGEM INTEIRA
        this.layer = new BufferedImage(Game.WIDTH, Game.HEIGHT, 1);

        // TAMANHO DA JANELA DE ACORDO COM AS VARIAVEIS LA EM CIMA
        this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

        // OUVIR TECLA
        this.addKeyListener(this);
    }
    
    public static void main(final String[] args) {
        final Game game = new Game();
        // CRIA A JANELA E DEFINE AS PROPRIEDADES
        final JFrame frame = new JFrame("Smooch dat boi");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // COMEÇA
        new Thread(game).start();
    }
    
    public void tick() {
        // TICKS
        Game.player.tick();
        Game.boy.tick();

        // SÓ TICKA SE EXISTIR TIRO NO MAPA
        switch (Game.kissy) {
            case 1: {
                Game.smch.tick();
                break;
            }
        }
    }
    
    // IMAGEM NA TELA
    public void render() {
        // NÃO SEI PRA Q SERVE ISSO
        final BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        // MOSTRA AS COISA (NÃO SEI OQ É this.layer) NA ORDEM DE CAMADAS FUNDO > EZREAL > ZOE > TIRO > BORDA
        // DESENHA FUNDO DIRETO NA JANELA (FIXO)
        g.drawImage(this.layer, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
        g.drawImage(this.bgSp, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
        // RENDERIZA ALVO E JOGADOR (VARIÁVEL)
        Game.boy.render(this.boySp, g);
        Game.player.render(this.playerSp, g);
        // RENDERIZA TIRO APENAS SE EXISTIR
        switch (Game.kissy) {
            case 1: {
                Game.smch.render(this.kissSp, g);
                break;
            }
        }
        // DESENHA BORDA (FIXO)
        g.drawImage(this.bushSp, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
        // MOSTRA JANELA
        bs.show();
    }
    
    @Override
    public void run() {
        // ENQUANTO TIVER RODANDO VAI RODAR O TICK E O RENDER
        while (true) {
            this.tick();
            this.render();
            // ESPERA 16 MILISSEGUNDOS E RODA DE NOVO
            try {Thread.sleep(16L);}
            catch (InterruptedException ex) {Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }
    
    @Override
    // CONTROLES DO PLAYER (APERTAR TRIGGER)
    public void keyTyped(final KeyEvent e) {

    }
    
    // CONTROLES DO PLAYER (SEGURAR O TRIGGER)
    @Override
    public void keyPressed(final KeyEvent e) {
            // DIRECIONAIS
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {Game.player.up = true;}
        else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {Game.player.down = true;}
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {Game.player.right = true;}
        else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {Game.player.left = true;}
            // TIRO E EXCLUIR TIRO
        if (e.getKeyCode() == 32) {Game.player.kissy = true;} //Espaço
        if (e.getKeyCode() == 88) {Game.player.kissyRest = true;} //X
    }
    
    // CONTROLES DO PLAYER (SOLTAR O TRIGGER)
    @Override
    public void keyReleased(final KeyEvent e) {
            //DIRECIONAL
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {Game.player.up = false;}
        else if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {Game.player.down = false;}
        if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {Game.player.right = false;}
        else if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {Game.player.left = false;}
            // TIRO E EXCLUIR TIRO
        if (e.getKeyCode() == 32) {Game.player.kissy = false;} //Espaço
        if (e.getKeyCode() == 88) {Game.player.kissyRest = false;} //X
    }
    
}