import java.awt.Color;
import java.awt.Graphics;

public class Obstacles
{
    // COORDENADAS
    public int x;
    public int y;

    // TAMANHO
    public int height;
    public int width;
    
    public Obstacles(final int x, final int y, final int height, final int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void tick() {
        // A LÓGICA É PRA FICAR AQUI
    }
    
    public void render(final Graphics g) {
        // DESENHA QUADRADO :D
        g.setColor(Color.yellow);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}